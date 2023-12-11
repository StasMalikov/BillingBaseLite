package com.univ.billingbaselite.models.dtos;

import com.univ.billingbaselite.models.entities.BaseProduct;
import com.univ.billingbaselite.models.entities.ExtraProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraProductDTO implements Serializable {

    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;
    private ExtraProduct.ExtraProductCategory category;
    private String name;
    private String description;
    private BaseProduct.ProductStatus status;
    private BigDecimal cost;
    private Integer version;

    public ExtraProductDTO(ExtraProduct extraProduct) {
        this.id = extraProduct.getId().toString();
        this.startDate = extraProduct.getStartDate();
        this.endDate = extraProduct.getEndDate();
        this.category = extraProduct.getCategory();
        this.name = extraProduct.getName();
        this.description = extraProduct.getDescription();
        this.status = extraProduct.getStatus();
        this.cost = extraProduct.getCost();
        this.version = extraProduct.getVersion();
    }
}
