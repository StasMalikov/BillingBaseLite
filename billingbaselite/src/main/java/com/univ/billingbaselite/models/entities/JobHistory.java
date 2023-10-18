package com.univ.billingbaselite.models.entities;

import com.univ.billingbaselite.config.GlobalConstants;
import com.univ.billingbaselite.models.dtos.JobHistoryDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "JOB_HISTORY")
public class JobHistory implements Serializable {

    @Id
    @SequenceGenerator( name = "jpaSequence", sequenceName = "JPA_SEQUENCE", allocationSize = 1, initialValue = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    @Column(name = "ID")
    private Long jobId;

    @Column(name = "JOB_NAME")
    private String jobName;
    @Column(name = "JOB_GROUP")
    private String jobGroup;
    @Column(name = "JOB_STATUS")
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
    @Column(name = "JOB_CLASS")
    private String jobClass;
    @Column(name = "CRON_EXPRESSION")
    private String cronExpression;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "REPEAT_INTERVAL")
    private Long repeatInterval;
    @Column(name = "REPEAT_COUNT")
    private Integer repeatCount;
    @Column(name = "ACTUAL_REPEAT_VALUE")
    private Integer actualRepeatValue;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "ERROR_MSG", length = 4000)
    private String errorMsg;
    @Column(name = "STATUS_DATE")
    private Date statusDate;

    public JobHistory(JobHistoryDTO jobInfoDTO) {
        this.jobId = jobInfoDTO.getJobId();
        this.jobName = jobInfoDTO.getJobName();
        this.jobGroup = jobInfoDTO.getJobGroup();
        this.jobClass = jobInfoDTO.getJobClass();
        this.cronExpression = jobInfoDTO.getCronExpression();
        this.description = jobInfoDTO.getDescription();
        this.repeatInterval = jobInfoDTO.getRepeatInterval();
        this.startDate = jobInfoDTO.getStartDate();
        this.repeatCount = jobInfoDTO.getRepeatCount();
        this.actualRepeatValue = 0;
        this.jobStatus = JobStatus.SCHEDULED;
        this.statusDate = new Date();
    }

    public void update(JobHistoryDTO jobHistoryDTO) {
        this.cronExpression = jobHistoryDTO.getCronExpression();
        this.repeatInterval = jobHistoryDTO.getRepeatInterval();
        this.startDate = jobHistoryDTO.getStartDate();
        this.repeatCount = jobHistoryDTO.getRepeatCount();
        this.statusDate = new Date();
    }

    public enum JobStatus {
        SCHEDULED,
        PAUSED,
        STARTED,
        FAILED,
        INTERRUPTED,
        COMPLETED
    }
}
