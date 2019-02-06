package com.phoneBook2.services;

import com.phoneBook2.exceptions.ContactNotFoundException;
import com.phoneBook2.exceptions.ParamaterNotProvidedException;
import com.phoneBook2.models.Contact;

import java.util.List;

public interface ContactService {

  List<Contact> allContacts();

  void addContact(Contact contact) throws ParamaterNotProvidedException;

  boolean contactExistsByName(String name) throws ParamaterNotProvidedException;

  void deleteContact(Contact contact) throws ParamaterNotProvidedException, ContactNotFoundException;

  List<Contact> findByLastNameFirstNameTitle(String lastName, String firstName, String title);

  List<Contact> findByFirstName(String firstName);

  List<Contact> findByLastName(String lastName);

  List<Contact> findBytitle(String title);

  List<Contact> findByPhoneNumber(String phoneNumber);

  List<Contact> findByDateOfBirth(Integer fromDate, Integer toDate);

  Contact findByName(String Name);

  void addBulkContact(List<Contact> contactList);

  void deleteBulkContact(List<Contact> contactList);

  List<Contact> findByAddress(String address);


}
