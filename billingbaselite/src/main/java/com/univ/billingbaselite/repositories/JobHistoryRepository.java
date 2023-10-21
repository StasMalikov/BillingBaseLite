package com.univ.billingbaselite.repositories;

import com.univ.billingbaselite.models.entities.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
}
