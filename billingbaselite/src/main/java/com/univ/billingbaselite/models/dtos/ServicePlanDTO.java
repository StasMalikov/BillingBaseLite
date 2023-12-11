package com.univ.billingbaselite.models.dtos;

import com.univ.billingbaselite.models.entities.BaseProduct;
import com.univ.billingbaselite.models.entities.ServicePlan;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ServicePlanDTO implements Serializable {

    private String id;
    private List<ServiceProductDTO> serviceProducts;
    private String name;
    private String description;
    private BaseProduct.ProductStatus status;
    private BigDecimal cost;
    private Integer version;

    public ServicePlanDTO(ServicePlan servicePlan) {
        this.id = servicePlan.getId().toString();
        this.serviceProducts = servicePlan.getServiceProducts().stream().map(ServiceProductDTO::new).collect(Collectors.toList());
        this.name = servicePlan.getName();
        this.description = servicePlan.getDescription();
        this.status = servicePlan.getStatus();
        this.cost = servicePlan.getCost();
        this.version = servicePlan.getVersion();
    }
}
