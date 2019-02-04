package com.phoneBook2.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  private String country;
  private String zipCode;
  private String city;
  private String street;

  public Address(Long id, String country, String zipCode, String city, String street) {
    Id = id;
    this.country = country;
    this.zipCode = zipCode;
    this.city = city;
    this.street = street;
  }
}