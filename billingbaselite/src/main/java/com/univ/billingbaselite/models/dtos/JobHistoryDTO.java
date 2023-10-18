package com.univ.billingbaselite.models.dtos;

import com.univ.billingbaselite.models.entities.JobHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobHistoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long jobId;
    private String jobName;
    private String jobGroup;
    private JobHistory.JobStatus jobStatus;
    private String jobClass;
    private String cronExpression;
    private String description;
    private Long repeatInterval;
    private Integer repeatCount;
    private Date startDate;
    private Integer actualRepeatValue;
    private Map<String, Object> inputParams;
    private Date statusDate;

    public JobHistoryDTO(JobHistory jobHistory) {
        this.jobId = jobHistory.getJobId();
        this.jobName = jobHistory.getJobName();
        this.jobGroup = jobHistory.getJobGroup();
        this.jobStatus = jobHistory.getJobStatus();
        this.jobClass = jobHistory.getJobClass();
        this.cronExpression = jobHistory.getCronExpression();
        this.description = jobHistory.getDescription();
        this.repeatInterval = jobHistory.getRepeatInterval();
        this.repeatCount = jobHistory.getRepeatCount();
        this.actualRepeatValue = jobHistory.getActualRepeatValue();
        this.startDate = jobHistory.getStatusDate();
        this.statusDate = jobHistory.getStatusDate();
    }

    public JobHistoryDTO(String jobName, String jobGroup, String jobClass,
                         Map<String, Object> inputParams, Date startDate) {
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.jobClass = jobClass;
        this.inputParams = inputParams;
        this.repeatInterval = 1l;
        this.repeatCount = 0;
        this.startDate = startDate;
    }
}
