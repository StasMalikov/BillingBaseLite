package com.univ.billingbaselite.services.impl;

import com.univ.billingbaselite.models.dtos.ServiceProductDTO;
import com.univ.billingbaselite.models.entities.ServiceProduct;
import com.univ.billingbaselite.repositories.ServiceProductRepository;
import com.univ.billingbaselite.services.ServiceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceProductServiceImpl implements ServiceProductService {

    ServiceProductRepository serviceProductRepository;

    @Autowired
    public ServiceProductServiceImpl(ServiceProductRepository serviceProductRepository) {
        this.serviceProductRepository = serviceProductRepository;
    }

    @Override
    public ServiceProductDTO create(ServiceProductDTO serviceProductDTO) {
        return new ServiceProductDTO(serviceProductRepository.save(new ServiceProduct(serviceProductDTO)));
    }

    @Override
    public List<ServiceProductDTO> getAll() {
        return serviceProductRepository.findAll().stream().map(ServiceProductDTO::new).collect(Collectors.toList());
    }
}
