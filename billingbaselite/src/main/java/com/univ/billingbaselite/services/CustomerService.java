package com.univ.billingbaselite.services;

import com.univ.billingbaselite.models.dtos.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO create(CustomerDTO customerDTO);
    List<CustomerDTO> createList(List<CustomerDTO> customerDTOList);
    List<CustomerDTO> getAll();
    CustomerDTO getById(String id);
    CustomerDTO update(CustomerDTO customerDTO);
    void delete(String id);
}
