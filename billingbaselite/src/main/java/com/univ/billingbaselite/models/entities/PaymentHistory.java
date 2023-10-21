package com.univ.billingbaselite.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PAYMENT_HISTORY")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "PRODUCT_ID")
    private UUID productId;

    @Column(name = "PAY_DATE")
    private Date payDate;

    @Column(name = "PAY_VALUE")
    private BigDecimal payValue;

    public PaymentHistory(BaseProduct product) {
        this.productId = product.getId();
        this.payDate = new Date();
        this.payValue = product.getCost();
    }
}
