package com.univ.billingbaselite.repositories;

import com.univ.billingbaselite.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> getFirstByAccountStatus(String accountStatus);
    List<Account> getAllByAccountStatus(String accountStatus);
}
