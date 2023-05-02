package com.univ.billingbaselite.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public abstract class Product {

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESC")
    private String description;

    @Column(name = "PRODUCT_VALUE")
    private String value;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private ProductStatus status;

    public enum ProductStatus {
        OK,
        TERMINATED
    }
}
