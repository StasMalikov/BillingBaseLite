package com.univ.billingbaselite.controllers;

import com.univ.billingbaselite.config.GlobalConstants;
import com.univ.billingbaselite.models.dtos.ExtraProductDTO;
import com.univ.billingbaselite.services.ExtraProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(GlobalConstants.URL_PATH + "/ExtraProduct")
public class ExtraProductController {

    ExtraProductService extraProductService;

    @Autowired
    public ExtraProductController(ExtraProductService extraProductService) {
        this.extraProductService = extraProductService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ExtraProductDTO> getAll() {
        return extraProductService.getAll();
    }


    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ExtraProductDTO createCustomer(ExtraProductDTO extraProductDTO) {
        return extraProductService.create(extraProductDTO);
    }
}
