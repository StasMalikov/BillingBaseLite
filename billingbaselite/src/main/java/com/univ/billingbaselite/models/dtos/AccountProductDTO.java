package com.univ.billingbaselite.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountProductDTO {

    String productId;

    String productName;

    String productDesc;

    String productType;

    Double cost;

    String status;

    String replacementProductId;
}
