package com.univ.billingbaselite.services;

import com.univ.billingbaselite.models.dtos.ServiceProductDTO;

import java.util.List;

public interface ServiceProductService {

    ServiceProductDTO create(ServiceProductDTO serviceProductDTO);

    List<ServiceProductDTO> getAll();
}
