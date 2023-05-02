package com.univ.billingbaselite.repositories;

import com.univ.billingbaselite.models.entities.ServicePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ServicePlanRepository extends JpaRepository<ServicePlan, UUID> {
}
