package com.univ.billingbaselite.models.dtos;

import com.univ.billingbaselite.models.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String id;
    private Account.AccountStatus accountStatus;

    public AccountDTO(Account account) {
        this.id = account.getId().toString();
        this.accountStatus = account.getAccountStatus();
    }
}
