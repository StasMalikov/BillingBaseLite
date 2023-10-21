package com.univ.billingbaselite.services;

import com.univ.billingbaselite.models.dtos.JobHistoryDTO;
import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;
import org.springframework.data.domain.Page;

public interface JobService {
    SchedulerMetaData getMetaData() throws SchedulerException;

    JobHistoryDTO createJob(JobHistoryDTO jobHistoryDTO);

    JobHistoryDTO updateJob(JobHistoryDTO jobHistoryDTO);

    void deleteJob(String id);

    void pauseJob(String id);

    void resumeJob(String id);

    void startJobNow(String id);;

    void interruptJob(String id);

    Page<JobHistoryDTO> getAllJobs();
}
