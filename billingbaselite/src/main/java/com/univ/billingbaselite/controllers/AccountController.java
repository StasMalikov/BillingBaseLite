package com.univ.billingbaselite.controllers;

import com.univ.billingbaselite.config.GlobalConstants;
import com.univ.billingbaselite.models.dtos.AccountDTO;
import com.univ.billingbaselite.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(GlobalConstants.URL_PATH + "/accounts")
public class AccountController {
    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<AccountDTO> getAll() {
        return accountService.getAll();
    }

    @GetMapping(value = "/{accountId}", produces = APPLICATION_JSON_VALUE)
    public AccountDTO getById(@PathVariable("accountId") String accountId) {
        return accountService.getById(accountId);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public AccountDTO createAccount(AccountDTO accountDTO) {
        return accountService.create(accountDTO);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public AccountDTO updateAccount(AccountDTO accountDTO) {
        return accountService.update(accountDTO);
    }

    @DeleteMapping(value = "/{accountId}")
    public void deleteAccount(@PathVariable("accountId") String accountId) {
        accountService.deleteById(accountId);
    }


//    @GetMapping(produces = APPLICATION_JSON_VALUE)
//    @RequestMapping("test")
//    public AccountDTO test() {
//        return accountService.test();
//    }
}
