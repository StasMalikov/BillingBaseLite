package com.univ.billingbaselite.services.impl;

import com.univ.billingbaselite.models.dtos.ServicePlanDTO;
import com.univ.billingbaselite.models.dtos.ServiceProductDTO;
import com.univ.billingbaselite.models.entities.ServicePlan;
import com.univ.billingbaselite.models.entities.ServiceProduct;
import com.univ.billingbaselite.repositories.ServicePlanRepository;
import com.univ.billingbaselite.repositories.ServiceProductRepository;
import com.univ.billingbaselite.services.ServicePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServicePlanServiceImpl implements ServicePlanService {

    ServicePlanRepository servicePlanRepository;
    ServiceProductRepository serviceProductRepository;

    @Autowired
    public ServicePlanServiceImpl(ServicePlanRepository servicePlanRepository, ServiceProductRepository serviceProductRepository) {
        this.servicePlanRepository = servicePlanRepository;
        this.serviceProductRepository = serviceProductRepository;
    }


    @Override
    public ServicePlanDTO create(ServicePlanDTO servicePlanDTO) {
        List<UUID> uuids = servicePlanDTO.getServiceProducts().stream().map(ServiceProductDTO::getId).map(UUID::fromString).toList();
        List<ServiceProduct> list = serviceProductRepository.findAllById(uuids);
        ServicePlan servicePlan = new ServicePlan(servicePlanDTO, list);
        return new ServicePlanDTO(servicePlanRepository.save(servicePlan));
    }

    @Override
    public List<ServicePlanDTO> getAll() {
        return servicePlanRepository.findAll().stream().map(ServicePlanDTO::new).collect(Collectors.toList());
    }
}
