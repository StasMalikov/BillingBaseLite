package com.univ.billingbaselite.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT_RECOMENDATION")
public class AccountRecommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "STAGE")
    private String stage;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "VALUES")
    private String values;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCT_ID", nullable = false)
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "JOB_HISTORY_ID", referencedColumnName = "ID")
    private JobHistory jobHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECOMENDATION_TEMPLATE_ID", nullable = false)
    private RecommendationTemplate recommendationTemplate;
}
