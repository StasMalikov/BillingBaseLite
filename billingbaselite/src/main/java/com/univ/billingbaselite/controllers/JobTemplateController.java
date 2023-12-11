package com.univ.billingbaselite.controllers;

import com.univ.billingbaselite.config.GlobalConstants;
import com.univ.billingbaselite.models.dtos.JobTemplateDTO;
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
@RequestMapping(GlobalConstants.URL_PATH + "/jobTemplates")
public class JobTemplateController {


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<JobTemplateDTO> getAll()  {
        return null;
    }

    @GetMapping(value = "/{jobTemplateId}", produces = APPLICATION_JSON_VALUE)
    public JobTemplateDTO getById(@PathVariable("jobTemplateId") String jobTemplateId) {
        return null;
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public JobTemplateDTO createJobTemplate(JobTemplateDTO jobTemplateDTO) {
        return null;
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public JobTemplateDTO updateJobTemplate(JobTemplateDTO jobTemplateDTO) {
        return null;
    }

    @DeleteMapping(value = "/{jobTemplateId}", produces = APPLICATION_JSON_VALUE)
    public JobTemplateDTO deletePolicy(@PathVariable("jobTemplateId") String jobTemplateId) {
        return null;
    }

}
