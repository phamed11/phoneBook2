package com.phoneBook2.services;

import com.phoneBook2.exceptions.ContactNotFoundException;
import com.phoneBook2.exceptions.ContactNotProvidedException;
import com.phoneBook2.models.Contact;

import java.util.List;

public interface ContactService {

  List<Contact> allContacts();
  void addContact(Contact contact) throws ContactNotProvidedException;
  boolean contactExistsByName(String name) throws ContactNotFoundException;
  void deleteContact(Contact contact) throws ContactNotProvidedException;
  List<Contact> findContact(String firstName, String lastName);
  List<Contact> findContactName(String lastName, String firstName);
}
