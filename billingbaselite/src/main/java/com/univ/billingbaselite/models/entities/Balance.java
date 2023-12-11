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
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BALANCE")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "VALUE")
    private BigDecimal value;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "BALANCE_DATE")
    private Date balanceDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCT_ID", nullable = false)
    private Account account;

    @OneToMany(mappedBy = "balance", fetch = FetchType.LAZY)
    private List<Payment> payments;

    @OneToMany(mappedBy = "balance", fetch = FetchType.LAZY)
    private List<WriteOff> writeOffs;

}
