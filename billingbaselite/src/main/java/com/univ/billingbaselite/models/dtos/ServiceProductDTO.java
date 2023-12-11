package com.univ.billingbaselite.models.dtos;

import com.univ.billingbaselite.models.entities.ServiceProduct;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ServiceProductDTO implements Serializable {

    private String id;
    private BigDecimal value;
    private ServiceProduct.ProductValueType valueType;

    public ServiceProductDTO(ServiceProduct serviceProduct) {
        this.id = serviceProduct.getId().toString();
        this.value = serviceProduct.getValue();
        this.valueType = serviceProduct.getValueType();
    }
}
