package com.univ.billingbaselite.controllers;

import com.univ.billingbaselite.config.GlobalConstants;
import com.univ.billingbaselite.models.dtos.JobHistoryDTO;
import com.univ.billingbaselite.services.JobService;
import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(GlobalConstants.URL_PATH + "/jobs")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Page<JobHistoryDTO> getAll(
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "sort", required = false) String orders,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit) {
        return jobService.getAllJobs();
    }

    @GetMapping(value = "/metaData", produces = APPLICATION_JSON_VALUE)
    public SchedulerMetaData getMetaData() throws SchedulerException {
        return jobService.getMetaData();
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public JobHistoryDTO createJob(@RequestBody(required = true) JobHistoryDTO jobHistoryDTO) {
        return jobService.createJob(jobHistoryDTO);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public JobHistoryDTO updateJob(@RequestBody(required = true) JobHistoryDTO jobHistoryDTO) {
        return jobService.updateJob(jobHistoryDTO);
    }

    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public void deleteJob(@PathVariable("id") String id) {
        jobService.deleteJob(id);
    }

    @PostMapping(value = "/{id}:pause", produces = APPLICATION_JSON_VALUE)
    public void pauseJob(@PathVariable("id") String id) {
        jobService.pauseJob(id);
    }

    @PostMapping(value = "/{id}:interrupt", produces = APPLICATION_JSON_VALUE)
    public void interruptJob(@PathVariable("id") String id) {
        jobService.interruptJob(id);
    }

    @PostMapping(value = "/{id}:resume", produces = APPLICATION_JSON_VALUE)
    public void resumeJob(@PathVariable("id") String id) {
        jobService.resumeJob(id);
    }

    @PostMapping(value = "/{id}:start", produces = APPLICATION_JSON_VALUE)
    public void startJob(@PathVariable("id") String id) {
        jobService.startJobNow(id);
    }

}
