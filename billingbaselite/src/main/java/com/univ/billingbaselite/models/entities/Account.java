package com.univ.billingbaselite.models.entities;

import com.univ.billingbaselite.models.dtos.AccountDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST_ID", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Contact> contacts;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<ExtraProduct> extraProducts;

    @ManyToMany(mappedBy = "servicedAccounts", fetch = FetchType.LAZY)
    private List<ServicePlan> servicePlans;

    public Account(AccountDTO accountDTO) {
        this.accountStatus = accountDTO.getAccountStatus();
        this.createdDate = new Date();
    }

    public Account update(AccountDTO accountDTO) {
        this.accountStatus = accountDTO.getAccountStatus();
        return this;
    }

    public enum AccountStatus {
        NORMAL,
        ON_POSITIVE_RECOMMENDATION,
        ON_SUSPENSION,
        ON_SUSPENSION_AND_RECOMMENDATION,
        TERMINATED,
        ON_CONTROL,
        ARCHIVED
    }
}
