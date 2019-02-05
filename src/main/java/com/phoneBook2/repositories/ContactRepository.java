package com.phoneBook2.repositories;

import com.phoneBook2.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

  @Query("select c from Contact c where c.firstName = :firstName or c.lastName = :lastName")
  List<Contact> findByLastnameOrFirstname(@Param("lastName") String lastName,
                                          @Param("firstName") String firstName);

  List<Contact> findByLastNameOrFirstName(String lastName, String firstName);
}

