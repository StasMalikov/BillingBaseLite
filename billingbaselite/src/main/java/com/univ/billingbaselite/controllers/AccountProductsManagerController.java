package com.univ.billingbaselite.controllers;

import com.univ.billingbaselite.config.GlobalConstants;
import com.univ.billingbaselite.models.dtos.AccountProductDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(GlobalConstants.URL_PATH + "/accountProductsManager")
public class AccountProductsManagerController {


    @GetMapping(value = "/{accountNumber}/currentProducts", produces = APPLICATION_JSON_VALUE)
    public List<AccountProductDTO> getCurrentProducts(@PathVariable("accountNumber") String accountNumber) {

        List<AccountProductDTO> result = new ArrayList<>();
        AccountProductDTO r1 = new AccountProductDTO("95f14fd8-e572-4c47-a2cf-f6ccfad2cb27", "name1", "desc1", "tariff", 500.00, "active", null);
        AccountProductDTO r2 = new AccountProductDTO("d291234a-a994-4ee5-b82a-a46c603dad4a", "name2", "desc2", "extraProduct", 45.50, "active", null);
        AccountProductDTO r3 = new AccountProductDTO("2d5ce836-10c1-4f97-99b2-97cbee645ec4", "name3", "desc3", "tariff", 700.00, "active", null);
        AccountProductDTO r4 = new AccountProductDTO("c717ec56-5173-43f2-9dc9-7f4b6b36f328", "name4", "desc4", "extraProduct", 99.99, "active", null);

        result.add(r1);
        result.add(r2);
        result.add(r3);
        result.add(r4);

        return result;
    }

    @GetMapping(value = "/{accountNumber}/recommendationProducts", produces = APPLICATION_JSON_VALUE)
    public List<AccountProductDTO> getRecommendationProducts(@PathVariable("accountNumber") String accountNumber) {
        List<AccountProductDTO> result = new ArrayList<>();
        AccountProductDTO r1 = new AccountProductDTO("2ad1dd76-a47f-4539-aa6a-943359da0555", "name11", "desc11", "tariff", 750.50, "recommended", "95f14fd8-e572-4c47-a2cf-f6ccfad2cb27");
        AccountProductDTO r2 = new AccountProductDTO("269c9539-0969-45b9-bc96-9aefcfc132d0", "name22", "desc22", "extraProduct", 87.99, "recommended", "d291234a-a994-4ee5-b82a-a46c603dad4a");

        result.add(r1);
        result.add(r2);
        return result;
    }

    @PostMapping(value = "/{accountNumber}:registerProduct", produces = APPLICATION_JSON_VALUE)
    public AccountProductDTO registerProduct(@PathVariable("accountNumber") String accountNumber, AccountProductDTO accountProductDTO) {
        return null;
    }

    @PostMapping(value = "/{accountNumber}:disconnectProduct", produces = APPLICATION_JSON_VALUE)
    public AccountProductDTO disconnectProduct(@PathVariable("accountNumber") String accountNumber, AccountProductDTO accountProductDTO) {
        return null;
    }

}
