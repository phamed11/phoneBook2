package com.phoneBook2.services;

import com.phoneBook2.exceptions.ContactNotFoundException;
import com.phoneBook2.models.Contact;
import com.phoneBook2.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService, HasLogger {

  ContactRepository contactRepository;

  @Autowired
  public ContactServiceImpl(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  @Override
  public List<Contact> allContacts() {
    return contactRepository.findAll();
  }

  public void addContact(Contact contact) {
    if (contact == null) {
      throw new ContactNotFoundException("Contact not provided!");
    }
    if (!contactExistsByName(contact.getName())) {
      contactRepository.save(contact);
      getLogger().info("Contact created");
    }
  }

  public boolean contactExistsByName(String name) {
    if (name == null || "".equals(name)) {
      throw new ContactNotFoundException("Name not provided");
    }
      List<Contact> contactsFound = allContacts()
          .stream()
          .filter(contact -> name.equals(contact.getName()))
          .collect(Collectors.toList());

    return contactsFound.size() != 0;
  }
}
