package com.univ.billingbaselite.controllers;

import com.univ.billingbaselite.config.GlobalConstants;
import com.univ.billingbaselite.models.dtos.PolicyDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(GlobalConstants.URL_PATH + "/policies")
public class PolicyController {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<PolicyDTO> getAll()  {
        return null;
    }

    @GetMapping(value = "/{policyId}", produces = APPLICATION_JSON_VALUE)
    public PolicyDTO getById(@PathVariable("policyId") String policyId) {
        return null;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public PolicyDTO createPolicy(PolicyDTO policyDTO) {
        return null;
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public PolicyDTO updatePolicy(PolicyDTO policyDTO) {
        return null;
    }

    @DeleteMapping(value = "/{policyId}", produces = APPLICATION_JSON_VALUE)
    public PolicyDTO deletePolicy(@PathVariable("policyId") String policyId) {
        return null;
    }

}
