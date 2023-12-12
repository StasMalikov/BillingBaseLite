package com.univ.billingbaselite.repositories;

import com.univ.billingbaselite.models.entities.Account;
import com.univ.billingbaselite.models.entities.AccountRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRecommendationRepository extends JpaRepository<AccountRecommendation, UUID> {
}
