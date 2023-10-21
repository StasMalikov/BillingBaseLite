package com.univ.billingbaselite.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public abstract class BaseProduct {

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESC")
    private String description;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private ProductStatus status;

    @Column(name = "COST")
    private BigDecimal cost;

    @Column(name = "VERSION")
    private Integer version;

    public enum ProductStatus {
        ACTIVE,
        ARCHIVED,
        PLANNED
    }

    public UUID getId() {
        return null;
    }
}
