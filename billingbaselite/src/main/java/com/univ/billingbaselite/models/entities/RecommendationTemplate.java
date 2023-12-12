package com.univ.billingbaselite.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RECOMENDATION_TEMPLATE")
public class RecommendationTemplate {

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

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "VERSION")
    private Integer version;

    @OneToMany(mappedBy = "recommendationTemplate", fetch = FetchType.LAZY)
    private List<AccountRecommendation> accountRecommendations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECOMENDATION_TYPE_ID", nullable = false)
    private RecommendationType recommendationType;

}
