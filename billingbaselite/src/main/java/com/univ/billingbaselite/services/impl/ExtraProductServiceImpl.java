package com.univ.billingbaselite.services.impl;

import com.univ.billingbaselite.models.dtos.ExtraProductDTO;
import com.univ.billingbaselite.models.entities.ExtraProduct;
import com.univ.billingbaselite.repositories.ExtraProductRepository;
import com.univ.billingbaselite.services.ExtraProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExtraProductServiceImpl implements ExtraProductService {

    ExtraProductRepository extraProductRepository;

    @Autowired
    public ExtraProductServiceImpl(ExtraProductRepository extraProductRepository) {
        this.extraProductRepository = extraProductRepository;
    }

    @Override
    public ExtraProductDTO create(ExtraProductDTO extraProductDTO) {
        return new ExtraProductDTO(extraProductRepository.save(new ExtraProduct(extraProductDTO)));
    }

    @Override
    public List<ExtraProductDTO> getAll() {
        return extraProductRepository.findAll().stream().map(ExtraProductDTO::new).collect(Collectors.toList());
    }
}
