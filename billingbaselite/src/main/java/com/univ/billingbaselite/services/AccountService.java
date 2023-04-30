package com.univ.billingbaselite.services;

import com.univ.billingbaselite.models.dtos.AccountDTO;

import java.util.List;

public interface AccountService {
    AccountDTO create(AccountDTO accountDTO);
    List<AccountDTO> getAll();
    AccountDTO getById(String id);
    AccountDTO update(AccountDTO accountDTO);
    void deleteById(String id);

    AccountDTO test();
}
