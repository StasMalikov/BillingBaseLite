package com.univ.billingbaselite.repositories;

import com.univ.billingbaselite.models.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID>  {
}
