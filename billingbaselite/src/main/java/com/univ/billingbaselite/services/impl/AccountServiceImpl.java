package com.univ.billingbaselite.services.impl;

import com.univ.billingbaselite.models.dtos.AccountDTO;
import com.univ.billingbaselite.models.entities.Account;
import com.univ.billingbaselite.models.entities.BaseProduct;
import com.univ.billingbaselite.repositories.AccountRepository;
import com.univ.billingbaselite.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
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
        return new AccountDTO(getAccountById(id));
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        Account account = getAccountById(accountDTO.getId());
        account.update(accountDTO);
        return new AccountDTO(accountRepository.save(account));
    }

    private Account getAccountById(String id) {
        return accountRepository.findById(UUID.fromString(id)).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Account with id %s not found", id))
        );
    }

    @Override
    public void deleteById(String id) {
        accountRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public AccountDTO test() {
        return null;
    }

    @Override
    public boolean checkProductsDowngrade(String id) {
        return false;
    }

    @Override
    public void updateAccounts(List<Account> accounts) {
        accountRepository.saveAll(accounts);
    }

    @Override
    public List<Account> getAllNonRatedAccountsWithPositiveBalance() {
        return accountRepository.getAllNonRatedAccountsWithPositiveBalance();
    }

    @Override
    public List<Account> getAllNonRatedAccountsWithNegativeBalance() {
        return accountRepository.getAllNonRatedAccountsWithNegativeBalance();
    }

    @Override
    public List<BaseProduct> getRecommendationProducts(UUID id) {
        return null;
    }

    @Override
    public List<BaseProduct> getSuspensionProducts(UUID id) {
        return null;
    }
}
