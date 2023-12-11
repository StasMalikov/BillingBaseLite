package com.univ.billingbaselite.services;

import com.univ.billingbaselite.models.dtos.ExtraProductDTO;

import java.util.List;

public interface ExtraProductService {

    ExtraProductDTO create(ExtraProductDTO extraProductDTO);

    List<ExtraProductDTO> getAll();
}
