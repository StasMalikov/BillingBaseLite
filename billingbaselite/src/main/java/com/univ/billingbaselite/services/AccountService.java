package com.univ.billingbaselite.services;

import com.univ.billingbaselite.models.dtos.AccountDTO;
import com.univ.billingbaselite.models.entities.Account;
import com.univ.billingbaselite.models.entities.BaseProduct;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountDTO create(AccountDTO accountDTO);
    List<AccountDTO> getAll();
    AccountDTO getById(String id);
    AccountDTO update(AccountDTO accountDTO);

    void updateAccounts(List<Account> accounts);

    void deleteById(String id);

    AccountDTO test();

    boolean checkProductsDowngrade(String id);

    List<Account> getAllNonRatedAccountsWithPositiveBalance();
    List<Account> getAllNonRatedAccountsWithNegativeBalance();

    List<BaseProduct> getRecommendationProducts(UUID id);
    List<BaseProduct> getSuspensionProducts(UUID id);
}
