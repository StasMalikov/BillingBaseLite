package com.univ.billingbaselite.repositories;

import com.univ.billingbaselite.models.entities.ServiceProduct;
import com.univ.billingbaselite.models.entities.TreatmentTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TreatmentTemplateRepository extends JpaRepository<TreatmentTemplate, UUID> {
}
