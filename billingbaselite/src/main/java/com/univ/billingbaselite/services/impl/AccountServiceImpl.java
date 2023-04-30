package com.univ.billingbaselite.services.impl;

import com.univ.billingbaselite.models.dtos.AccountDTO;
import com.univ.billingbaselite.models.entities.Account;
import com.univ.billingbaselite.repositories.AccountRepository;
import com.univ.billingbaselite.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO create(AccountDTO accountDTO) {
        return new AccountDTO(accountRepository.save(new Account(accountDTO)));
    }

    @Override
    public List<AccountDTO> getAll() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    @Override
    public AccountDTO getById(String id) {

        return null;
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public AccountDTO test() {
        return null;
    }
}
