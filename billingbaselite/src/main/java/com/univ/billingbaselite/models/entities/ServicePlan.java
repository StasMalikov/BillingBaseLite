package com.univ.billingbaselite.models.entities;

import com.univ.billingbaselite.models.dtos.ServicePlanDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SERVICE_PLAN")
public class ServicePlan  {

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

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.STRING)
    private BaseProduct.ProductStatus status;

    @Column(name = "COST")
    private BigDecimal cost;

    @Column(name = "VERSION")
    private Integer version;

    @OneToMany(mappedBy = "servicePlan", fetch = FetchType.LAZY)
    private List<WriteOff> writeOffs;

    public ServicePlan(ServicePlanDTO servicePlanDTO, List<ServiceProduct> serviceProducts) {
        this.serviceProducts = serviceProducts;
        this.name = servicePlanDTO.getName();
        this.description = servicePlanDTO.getDescription();
        this.status = servicePlanDTO.getStatus();
        this.cost = servicePlanDTO.getCost();
        this.version = servicePlanDTO.getVersion();
    }
}
