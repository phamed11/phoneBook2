package com.phoneBook2.services;

import com.phoneBook2.models.Contact;

import java.util.List;

public interface ContactService {

  List<Contact> allContacts();
  void addContact(Contact contact);
}
