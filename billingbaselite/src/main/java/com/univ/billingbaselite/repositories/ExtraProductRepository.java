package com.univ.billingbaselite.repositories;

import com.univ.billingbaselite.models.entities.ExtraProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExtraProductRepository extends JpaRepository<ExtraProduct, UUID> {
}
