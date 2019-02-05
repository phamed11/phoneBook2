package com.phoneBook2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

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

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", country='" + country + '\'' +
        ", zipCode='" + zipCode + '\'' +
        ", city='" + city + '\'' +
        ", street='" + street + '\'' +
        '}';
  }
}