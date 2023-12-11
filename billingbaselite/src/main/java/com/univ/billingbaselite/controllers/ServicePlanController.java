package com.univ.billingbaselite.controllers;

import com.univ.billingbaselite.config.GlobalConstants;
import com.univ.billingbaselite.models.dtos.ServicePlanDTO;
import com.univ.billingbaselite.services.ServicePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(GlobalConstants.URL_PATH + "/ServicePlan")
public class ServicePlanController {

    ServicePlanService servicePlanService;

    @Autowired
    public ServicePlanController(ServicePlanService servicePlanService) {
        this.servicePlanService = servicePlanService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ServicePlanDTO> getAll() {
        return servicePlanService.getAll();
    }


    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ServicePlanDTO createServicePlan(ServicePlanDTO servicePlanDTO) {
        return servicePlanService.create(servicePlanDTO);
    }


}
