package com.univ.billingbaselite.controllers;

import com.univ.billingbaselite.config.GlobalConstants;
import com.univ.billingbaselite.models.dtos.AccountJobDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(GlobalConstants.URL_PATH + "/accountJobs")
public class AccountJobsController {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<AccountJobDTO> getAll(
            @RequestParam(value = "jobStatus", required = false) String jobStatus,
            @RequestParam(value = "accountNumber", required = false) String accountNumber) {
        return null;
    }

    @GetMapping(value = "/{accountJobId}", produces = APPLICATION_JSON_VALUE)
    public AccountJobDTO getById(@PathVariable("accountJobId") String accountJobId) {
        return null;
    }

    @PostMapping(value = "/{accountJobId}:approve", produces = APPLICATION_JSON_VALUE)
    public AccountJobDTO approveAccountJobDTO(AccountJobDTO accountJobDTO) {
        return null;
    }

    @PostMapping(value = "/{accountJobId}:pause", produces = APPLICATION_JSON_VALUE)
    public AccountJobDTO pauseAccountJobDTO(AccountJobDTO accountJobDTO) {
        return null;
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public AccountJobDTO modifyAccountJobDTO(AccountJobDTO accountJobDTO) {
        return null;
    }
}
