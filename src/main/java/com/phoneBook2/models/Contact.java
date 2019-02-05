package com.phoneBook2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String firstName;
  private String lastName;
  private String dateOfBirth;
  @ElementCollection
  @CollectionTable(name = "phoneNumber")
  private List<String> phoneNumber = new ArrayList<>();
  @ManyToMany(cascade=CascadeType.ALL)
  private List<Address> address;


  public Contact() {
  }

  public Contact(String title, String firstName, String lastName, String dateOfBirth, List<String> phoneNumber, List<Address> address) {
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  public Contact(String firstName, String lastName, String dateOfBirth, List<String> phoneNumber, List<Address> address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

}
