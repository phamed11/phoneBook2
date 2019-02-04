package com.phoneBook2.repositories;

import com.phoneBook2.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Long, Contact> {
}
