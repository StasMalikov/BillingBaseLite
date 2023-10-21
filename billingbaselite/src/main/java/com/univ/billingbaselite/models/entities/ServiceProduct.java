package com.univ.billingbaselite.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SERVICE_PRODUCT")
public class ServiceProduct{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_PLAN_ID", nullable = false)
    private ServicePlan servicePlan;

    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "VALUE_TYPE")
    private ProductValueType valueType;

    public enum ProductValueType {
        sms,
        unlimited,
        gigabytes,
        minutes
    }

}
