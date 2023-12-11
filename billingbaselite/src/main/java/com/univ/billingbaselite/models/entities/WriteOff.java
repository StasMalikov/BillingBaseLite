package com.univ.billingbaselite.models.entities;

import com.univ.billingbaselite.models.dtos.ServicePlanDTO;
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
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WRITE_OFF")
public class WriteOff {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "PAY_DATE")
    private Date payDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BALANCE_ID", nullable = false)
    private Balance balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXTRA_PRODUCT_ID")
    private ExtraProduct extraProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TARIFF_ID")
    private ServicePlan servicePlan;

}
