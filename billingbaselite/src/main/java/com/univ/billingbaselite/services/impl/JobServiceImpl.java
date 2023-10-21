package com.univ.billingbaselite.services.impl;

import com.univ.billingbaselite.config.CMBusinessException;
import com.univ.billingbaselite.config.ErrorMessages;
import com.univ.billingbaselite.config.quartz.JobScheduleCreator;
import com.univ.billingbaselite.models.dtos.JobHistoryDTO;
import com.univ.billingbaselite.models.entities.JobHistory;
import com.univ.billingbaselite.repositories.JobHistoryRepository;
import com.univ.billingbaselite.services.JobService;
import com.univ.billingbaselite.services.impl.jobs.CreateAccountJob;
import com.univ.billingbaselite.services.impl.jobs.SimpleCronJob;
import com.univ.billingbaselite.services.impl.jobs.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class JobServiceImpl implements JobService {

    private final Scheduler scheduler;
    private final JobHistoryRepository jobHistoryRepository;
    private final JobScheduleCreator scheduleCreator;

    private final List<String> availableJobs = Arrays.asList(
            SimpleJob .class.getName(),
            SimpleCronJob .class.getName(),
            CreateAccountJob .class.getName());

    @Autowired
    public JobServiceImpl(Scheduler scheduler,
                          JobHistoryRepository jobHistoryRepository,
                          JobScheduleCreator scheduleCreator) {
        this.scheduler = scheduler;
        this.jobHistoryRepository = jobHistoryRepository;
        this.scheduleCreator = scheduleCreator;
    }

    @Override
    public Page<JobHistoryDTO> getAllJobs() {
        List<JobHistory> jobList = jobHistoryRepository.findAll();


//        Pageable pageable = ServiceUtils.getValidPageable(page, limit, orders);
//        if (CollectionUtils.isEmpty(filters)) {
//            jobList = jobHistoryRepository.findAll(pageable);
//        } else {
//            FilterSpecification<JobHistory> specification = new FilterSpecification<>(JobHistory.class);
//            specification.addAll(filters);
//            jobList = jobHistoryRepository.findAll(specification, pageable);
//        }
//        List<JobHistoryDTO> jobDTO = jobList.getContent().stream().map(JobHistoryDTO::new).collect(Collectors.toList());
        return new PageImpl<>(jobList.stream().map(JobHistoryDTO::new).collect(Collectors.toList()));
    }

    @Override
    public SchedulerMetaData getMetaData() throws SchedulerException {
        SchedulerMetaData metaData = scheduler.getMetaData();
        return metaData;
    }

    @Override
    public JobHistoryDTO createJob(JobHistoryDTO jobHistoryDTO) {
        if (!availableJobs.contains(jobHistoryDTO.getJobClass())) {
            throw new CMBusinessException(String.format(ErrorMessages.QRTZ_INVALID_CLASS_NAME , jobHistoryDTO.getJobClass()));
        }

        JobHistory jobHistory = new JobHistory(jobHistoryDTO);

        try {
            JobKey jobKey = new JobKey(jobHistory.getJobName(), jobHistory.getJobGroup());
            if (!scheduler.checkExists(jobKey)) {

                JobDataMap jobDataMap = new JobDataMap(jobHistoryDTO.getInputParams());

                jobHistory.setJobStatus(JobHistory.JobStatus.SCHEDULED);
                jobHistory = jobHistoryRepository.save(jobHistory);

                JobDetail jobDetail = JobBuilder.newJob((Class<? extends QuartzJobBean>) Class.forName(jobHistory.getJobClass()))
                        .withIdentity(jobHistory.getJobName(), jobHistory.getJobGroup())
                        .setJobData(jobDataMap)
                        .storeDurably(false)
                        .withDescription(jobHistory.getJobId().toString())
                        .build();

                Trigger trigger;

                if (StringUtils.isNotEmpty(jobHistory.getCronExpression())) {
                    trigger = scheduleCreator.createCronTrigger(jobHistory.getJobName(), new Date(),
                            jobHistory.getCronExpression(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
                } else {
                    trigger = scheduleCreator.createTrigger(jobHistory);
                }

                scheduler.scheduleJob(jobDetail, trigger);
                return new JobHistoryDTO(jobHistory);

            } else {
                throw new CMBusinessException(String.format(ErrorMessages.QRTZ_JOB_KEY_ALREADY_EXISTS, jobKey));
            }
        } catch (ClassNotFoundException e) {
            throw new CMBusinessException(String.format(ErrorMessages.QRTZ_JOB_RUN_CLASS_NOT_FOUND, jobHistory.getJobClass()));
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            throw new CMBusinessException(e.getMessage());
        }
    }

    @Override
    public JobHistoryDTO updateJob(JobHistoryDTO jobHistoryDTO) {
        JobHistory jobHistory = jobHistoryRepository.findById(jobHistoryDTO.getJobId()).orElseThrow(
                () -> new CMBusinessException(String.format(ErrorMessages.QRTZ_JOB_NOT_FOUND, jobHistoryDTO.getJobId())));

        jobHistory.update(jobHistoryDTO);

        Trigger newTrigger;
        if (StringUtils.isNotEmpty(jobHistory.getCronExpression())) {
            newTrigger = scheduleCreator.createCronTrigger(jobHistory.getJobName(), new Date(),
                    jobHistory.getCronExpression(), SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        } else {
            newTrigger = scheduleCreator.createTrigger(jobHistory);
        }
        try {
            scheduler.rescheduleJob(TriggerKey.triggerKey(jobHistory.getJobName()), newTrigger);
            return new JobHistoryDTO(jobHistoryRepository.save(jobHistory));
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            throw new CMBusinessException(e.getMessage());
        }
    }


    @Override
    public void deleteJob(String id) {
        Long jobId = Long.valueOf(id);
        try {
            JobHistory getJobInfo = jobHistoryRepository.findById(jobId).orElseThrow(
                    () -> new CMBusinessException(String.format(ErrorMessages.QRTZ_JOB_NOT_FOUND, jobId)));

            scheduler.deleteJob(new JobKey(getJobInfo.getJobName(), getJobInfo.getJobGroup()));
            jobHistoryRepository.delete(getJobInfo);
        } catch (SchedulerException e) {
            log.error(e.getMessage());
            throw new CMBusinessException(e.getMessage());
        }
    }

    @Override
    public void pauseJob(String id) {
        Long jobId = Long.valueOf(id);
        try {
            JobHistory getJobInfo = jobHistoryRepository.findById(jobId).orElseThrow(
                    () -> new CMBusinessException(String.format(ErrorMessages.QRTZ_JOB_NOT_FOUND, jobId)));

            scheduler.pauseJob(new JobKey(getJobInfo.getJobName(), getJobInfo.getJobGroup()));

            getJobInfo.setJobStatus(JobHistory.JobStatus.PAUSED);
            jobHistoryRepository.save(getJobInfo);
        } catch (SchedulerException e) {
            throw new CMBusinessException(String.format(ErrorMessages.QRTZ_FAILED_TO_PAUSE_JOB, jobId));
        }
    }

    @Override
    public void resumeJob(String id) {
        Long jobId = Long.valueOf(id);
        try {
            JobHistory getJobInfo = jobHistoryRepository.findById(jobId).orElseThrow(
                    () -> new CMBusinessException(String.format(ErrorMessages.QRTZ_JOB_NOT_FOUND, jobId)));

            scheduler.resumeJob(new JobKey(getJobInfo.getJobName(), getJobInfo.getJobGroup()));

            getJobInfo.setJobStatus(JobHistory.JobStatus.SCHEDULED);
            jobHistoryRepository.save(getJobInfo);
        } catch (SchedulerException e) {
            throw new CMBusinessException(String.format(ErrorMessages.QRTZ_FAILED_TO_RESUME_JOB, jobId));
        }
    }

    @Override
    public void startJobNow(String id) {
        Long jobId = Long.valueOf(id);
        try {
            JobHistory getJobInfo = jobHistoryRepository.findById(jobId).orElseThrow(
                    () -> new CMBusinessException(String.format(ErrorMessages.QRTZ_JOB_NOT_FOUND, jobId)));

            scheduler.triggerJob(new JobKey(getJobInfo.getJobName(), getJobInfo.getJobGroup()));
        } catch (SchedulerException e) {
            throw new CMBusinessException(String.format(ErrorMessages.QRTZ_FAILED_TO_START_JOB, jobId));
        }
    }

    @Override
    public void interruptJob(String id) {
        Long jobId = Long.valueOf(id);
        try {
            JobHistory getJobInfo = jobHistoryRepository.findById(jobId).orElseThrow(
                    () -> new CMBusinessException(String.format(ErrorMessages.QRTZ_JOB_NOT_FOUND, jobId)));

            scheduler.interrupt(new JobKey(getJobInfo.getJobName(), getJobInfo.getJobGroup()));

        } catch (SchedulerException e) {
            throw new CMBusinessException(String.format(ErrorMessages.QRTZ_FAILED_TO_INTERRUPT_JOB, jobId));
        }
    }
}

