package com.univ.billingbaselite.services;

import com.univ.billingbaselite.models.dtos.ServicePlanDTO;

import java.util.List;

public interface ServicePlanService {

    ServicePlanDTO create(ServicePlanDTO servicePlanDTO);

    List<ServicePlanDTO> getAll();

}
