package com.univ.billingbaselite.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SERVICE_PLAN")
public class ServicePlan extends BaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @OneToMany(mappedBy = "servicePlan", fetch = FetchType.LAZY)
    private List<ServiceProduct> serviceProducts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ACCOUNT_SERVICE_PLAN",
            joinColumns = @JoinColumn(name = "PLAN_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACCOUNT_ID"))
    private List<Account> servicedAccounts;

    @Override
    public UUID getId() {
        return id;
    }

}
