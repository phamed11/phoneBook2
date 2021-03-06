package com.phoneBook2.repositories;

import com.phoneBook2.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

  @Query("select c from Contact c where (c.lastName = :lastName or :lastName is null) and " +
      "(c.firstName = :firstName or :firstName is null) and (c.title = :title or :title is null)")
  List<Contact> filter(@Param("lastName") String lastName, @Param("firstName") String firstName, @Param("title") String title);

  List<Contact> findByFirstName(String firstName);

  List<Contact> findByLastName(String lastName);

  List<Contact> findByTitle(String title);

  List<Contact> findByPhoneNumber(String phoneNumber);


  @Query("select c from Contact c where concat(c.firstName,' ',c.lastName) = :name")
  Contact findbyName(@Param("name") String name);


  @Query("select c from Contact c join c.address a where a.country like :address or a.city " +
      "like :address or a.zipCode like :address or a.street like :address")
  List<Contact> findByAllAddress(@Param("address") String address);


}

