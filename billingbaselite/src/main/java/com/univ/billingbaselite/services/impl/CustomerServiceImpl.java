package com.univ.billingbaselite.services.impl;

import com.univ.billingbaselite.models.dtos.AccountDTO;
import com.univ.billingbaselite.models.dtos.CustomerDTO;
import com.univ.billingbaselite.models.entities.Customer;
import com.univ.billingbaselite.repositories.CustomerRepository;
import com.univ.billingbaselite.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        return new CustomerDTO(customerRepository.save(new Customer(customerDTO)));
    }

    @Override
    public List<CustomerDTO> createList(List<CustomerDTO> customerDTOList) {
        return customerRepository.saveAll(customerDTOList.stream().map(Customer::new)
                .toList()).stream().map(CustomerDTO::new).toList();
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll().stream().map(CustomerDTO::new).toList();
    }

    @Override
    public CustomerDTO getById(String id) {
        Customer customer = getCustomerById(id);
        return new CustomerDTO(customer, customer.getAccounts().stream().map(AccountDTO::new).toList());
    }

    private Customer getCustomerById(String id) {
        return customerRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Customer with id %s not found", id))
        );
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        return new CustomerDTO(customerRepository.save(getCustomerById(customerDTO.getId()).update(customerDTO)));
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(UUID.fromString(id));
    }
}
