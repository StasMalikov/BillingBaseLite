package com.univ.billingbaselite.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "JOB_TEMPLATE")
public class JobTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @ManyToMany(mappedBy = "jobTemplates", fetch = FetchType.LAZY)
    private List<Policy> policies;

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
    @Column(name = "JOB_GROUP")
    private String jobGroup;
    @Column(name = "TEMPLATE_STATUS")
    private String templateStatus;

    @OneToMany(mappedBy = "jobTemplate", fetch = FetchType.LAZY)
    private List<JobHistory> jobHistories;
}
