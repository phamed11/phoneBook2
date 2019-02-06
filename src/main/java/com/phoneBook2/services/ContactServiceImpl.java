package com.phoneBook2.services;

import com.phoneBook2.exceptions.ContactNotFoundException;
import com.phoneBook2.exceptions.ContactNotProvidedException;
import com.phoneBook2.models.Contact;
import com.phoneBook2.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService, HasLogger {

  private ContactRepository contactRepository;

  @Autowired
  public ContactServiceImpl(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  @Override
  public List<Contact> allContacts() {
    return contactRepository.findAll();
  }

  public void addContact(Contact contact) throws ContactNotProvidedException {
    if (contact == null) {
      throw new ContactNotProvidedException("Contact not provided!");
    }
    if (!contactExistsByName(contact.fullName())) {
      contactRepository.save(contact);
      getLogger().info("Contact created");
    }
  }

  public boolean contactExistsByName(String name) throws ContactNotFoundException {
    if (name == null || "".equals(name)) {
      throw new ContactNotFoundException("Name not provided");
    }
    List<Contact> contactsFound = allContacts()
        .stream()
        .filter(contact -> name.equals(contact.fullName()))
        .collect(Collectors.toList());

    return contactsFound.size() != 0;
  }

  public void deleteContact(Contact contact) throws ContactNotProvidedException, ContactNotFoundException {
    if (contact == null) {
      throw new ContactNotProvidedException("Contact not provided!");
    }
    if (contactExistsByName(contact.fullName())) {
      Contact toDelete = allContacts().stream()
          .filter(contact1 -> contact1.fullName().equals(contact.fullName()))
          .findAny()
          .orElse(null);
      contactRepository.delete(toDelete);
    }
  }

  @Override
  public List<Contact> findByLastNameFirstNameTitle(String lastName, String firstName, String title) {
    return contactRepository.filter(lastName, firstName, title);
  }

}


