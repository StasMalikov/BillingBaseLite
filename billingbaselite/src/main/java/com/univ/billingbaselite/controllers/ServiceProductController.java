package com.univ.billingbaselite.controllers;

import com.univ.billingbaselite.config.GlobalConstants;
import com.univ.billingbaselite.models.dtos.ServiceProductDTO;
import com.univ.billingbaselite.services.ServiceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(GlobalConstants.URL_PATH + "/ServiceProduct")
public class ServiceProductController {

    ServiceProductService serviceProductService;

    @Autowired
    public ServiceProductController(ServiceProductService serviceProductService) {
        this.serviceProductService = serviceProductService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ServiceProductDTO> getAll() {
        return serviceProductService.getAll();
    }


    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ServiceProductDTO createServiceProduct(ServiceProductDTO serviceProductDTO) {
        return serviceProductService.create(serviceProductDTO);
    }
}
