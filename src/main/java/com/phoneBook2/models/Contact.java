package com.phoneBook2.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@Setter
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  private String title;
  private String firstName;
  private String lastName;
  private String dateOfBirth;
  private List<String> phoneNumber;
  private List<Address> address;

  public Contact(Long id, String title, String firstName, String lastName, String dateOfBirth, List<String> phoneNumber, List<Address> address) {
    Id = id;
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  public Contact(Long id, String firstName, String lastName, String dateOfBirth, List<String> phoneNumber, List<Address> address) {
    Id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }
}
