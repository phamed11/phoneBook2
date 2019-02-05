package com.phoneBook2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String country;
  private String zipCode;
  private String city;
  private String street;

  public Address(String country, String zipCode, String city, String street) {
    this.country = country;
    this.zipCode = zipCode;
    this.city = city;
    this.street = street;
  }
}