package com.univ.billingbaselite.controllers;

import com.univ.billingbaselite.config.GlobalConstants;
import com.univ.billingbaselite.models.dtos.CustomerDTO;
import com.univ.billingbaselite.services.CustomerService;
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

//@RestController
//@RequestMapping(GlobalConstants.URL_PATH + "/customers")
public class CustomerController {

    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAll() {
        return customerService.getAll();
    }

    @GetMapping(value = "/{customerId}", produces = APPLICATION_JSON_VALUE)
    public CustomerDTO getById(@PathVariable("customerId") String customerId) {
        return customerService.getById(customerId);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public CustomerDTO createCustomer(CustomerDTO CustomerDTO) {
        return customerService.create(CustomerDTO);
    }


    @PostMapping(value = "list", produces = APPLICATION_JSON_VALUE)
    public List<CustomerDTO> createCustomerList(List<CustomerDTO> customerDTOList) {
        return customerService.createList(customerDTOList);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public CustomerDTO updateCustomer(CustomerDTO CustomerDTO) {
        return customerService.update(CustomerDTO);
    }

    @DeleteMapping(value = "/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") String customerId) {
        customerService.delete(customerId);
    }

}
