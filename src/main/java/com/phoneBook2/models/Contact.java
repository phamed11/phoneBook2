package com.phoneBook2.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Contact implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String firstName;
  private String lastName;
  private String dateOfBirth;
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "phoneNumber")
  private List<String> phoneNumber = new ArrayList<>();
  @ManyToMany(cascade=CascadeType.ALL)
  @LazyCollection(LazyCollectionOption.FALSE)
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
