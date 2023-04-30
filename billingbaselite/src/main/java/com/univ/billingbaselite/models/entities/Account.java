package com.univ.billingbaselite.models.entities;

import com.univ.billingbaselite.models.dtos.AccountDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ACCOUNT_STATUS")
    private String accountStatus;

    public Account(AccountDTO accountDTO) {
        this.accountStatus = accountDTO.getAccountStatus();
    }
}
