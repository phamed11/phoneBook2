package com.phoneBook2.services;

import com.phoneBook2.exceptions.ContactNotFoundException;
import com.phoneBook2.exceptions.ContactNotProvidedException;
import com.phoneBook2.exceptions.ParamaterNotProvidedException;
import com.phoneBook2.models.Contact;
import com.phoneBook2.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public boolean contactExistsByName(String name) throws ParamaterNotProvidedException {
    if (name == null || "".equals(name)) {
      throw new ParamaterNotProvidedException("Name not provided");
    }
    return contactRepository.findbyName(name) != null;
  }

  public void deleteContact(Contact contact) throws ParamaterNotProvidedException, ContactNotFoundException {
    if (contact == null) {
      throw new ParamaterNotProvidedException("Contact not provided!");
    }
    if (contactExistsByName(contact.fullName())) {
      contactRepository.delete(contactRepository.findbyName(contact.fullName()));
    } else {
      throw new ContactNotFoundException("Contact not found");
    }
  }

  @Override
  public List<Contact> findByLastNameFirstNameTitle(String lastName, String firstName, String title) {
    if (lastName == null || "".equals(lastName) || firstName == null || "".equals(firstName)
        || title == null || "".equals(title)) {
      throw new ParamaterNotProvidedException("Paramater is not provided");
    }
    return contactRepository.filter(lastName, firstName, title);
  }

  @Override
  public List<Contact> findByFirstName(String firstName) {
    if (firstName == null || "".equals(firstName)) {
      throw new ParamaterNotProvidedException("First name not provided");
    }
    return contactRepository.findByFirstName(firstName);
  }

  @Override
  public List<Contact> findByLastName(String lastName) {
    if (lastName == null || "".equals(lastName)) {
      throw new ParamaterNotProvidedException("Last name not provided");
    }
    return contactRepository.findByLastName(lastName);
  }

  @Override
  public List<Contact> findBytitle(String title) {
    if (title == null || "".equals(title)) {
      throw new ParamaterNotProvidedException("Title not provided");
    }
    return contactRepository.findByTitle(title);
  }

  @Override
  public Contact findByName(String name) {
    if (name == null || "".equals(name)) {
      throw new ParamaterNotProvidedException("Name not provided");
    }
    return contactRepository.findbyName(name);
  }

}


