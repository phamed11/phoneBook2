package com.phoneBook2.services;

import com.phoneBook2.exceptions.ContactNotFoundException;
import com.phoneBook2.exceptions.ParamaterNotProvidedException;
import com.phoneBook2.models.Contact;

import java.util.List;

public interface ContactService {

  List<Contact> allContacts();

  String addContact(Contact contact) throws ParamaterNotProvidedException;

  boolean contactExistsByName(String name) throws ParamaterNotProvidedException;

  String deleteContact(Contact contact) throws ParamaterNotProvidedException, ContactNotFoundException;

  List<Contact> findByLastNameFirstNameTitle(String lastName, String firstName, String title);

  List<Contact> findByFirstName(String firstName);

  List<Contact> findByLastName(String lastName);

  List<Contact> findBytitle(String title);

  List<Contact> findByPhoneNumber(String phoneNumber);

  List<Contact> findByDateOfBirth(Integer fromDate, Integer toDate);

  Contact findByName(String Name);

  List<String> addBulkContact(List<Contact> contactList);

  List<String> deleteBulkContact(List<Contact> contactList);

  List<Contact> findByAddress(String address);


}
