package com.univ.billingbaselite.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EXTRA_PRODUCT")
public class ExtraProduct extends BaseProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "CATEGORY")
    @Enumerated(value = EnumType.STRING)
    private ExtraProductCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCT_ID")
    private Account account;

    public enum ExtraProductCategory {
        DAILY_WEATHER_FORECAST,
        TRAFFIC_NOTIFICATION,
        NUMBERS_CHECKER
    }

    @Override
    public UUID getId() {
        return id;
    }
}
