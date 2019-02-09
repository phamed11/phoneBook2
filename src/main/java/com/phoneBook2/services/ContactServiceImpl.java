package com.phoneBook2.services;

import com.phoneBook2.exceptions.ContactAlreadyExistsException;
import com.phoneBook2.exceptions.ContactNotFoundException;
import com.phoneBook2.exceptions.ContactNotProvidedException;
import com.phoneBook2.exceptions.ParamaterNotProvidedException;
import com.phoneBook2.models.Contact;
import com.phoneBook2.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

  public String addContact(Contact contact) throws ContactNotProvidedException {
    if (contact == null) {
      throw new ContactNotProvidedException("Contact not provided!");
    }
    String response = "";
    if (!contactExistsByName(contact.fullName())) {
      contactRepository.save(contact);
      response = (contact.fullName() + " added");
      getLogger().info(contact.fullName() + " added");
    } else {
      response = (contact.fullName() + " already exists");
      getLogger().error(contact.fullName() + " already exists");
    }
    return response;
  }

  public boolean contactExistsByName(String name) throws ParamaterNotProvidedException {
    if (name == null || "".equals(name)) {
      throw new ParamaterNotProvidedException("Name not provided");
    }
    return contactRepository.findbyName(name) != null;
  }

  public String deleteContact(Contact contact) throws ContactNotProvidedException {
    if (contact == null) {
      throw new ContactNotProvidedException("Contact not provided!");
    }
    String response = "";
    if (contactExistsByName(contact.fullName())) {
      contactRepository.delete(contactRepository.findbyName(contact.fullName()));
      response = (contact.fullName() + " deleted");
      getLogger().info(contact.fullName() + " deleted");
    } else {
      response = (contact.fullName() + " does not exist");
      getLogger().info(contact.fullName() + " does not exists");

    }
    return response;
  }

  @Override
  public List<Contact> findByLastNameFirstNameTitle(String lastName, String firstName, String title) {
    return contactRepository.filter(lastName, firstName, title);
  }

  @Override
  public List<Contact> findByFirstName(String firstName) throws ParamaterNotProvidedException {
    if (firstName == null || "".equals(firstName)) {
      throw new ParamaterNotProvidedException("First name not provided");
    }
    return contactRepository.findByFirstName(firstName);
  }

  @Override
  public List<Contact> findByLastName(String lastName) throws ParamaterNotProvidedException {
    if (lastName == null || "".equals(lastName)) {
      throw new ParamaterNotProvidedException("Last name not provided");
    }
    return contactRepository.findByLastName(lastName);
  }

  @Override
  public List<Contact> findBytitle(String title) throws ParamaterNotProvidedException {
    if (title == null || "".equals(title)) {
      throw new ParamaterNotProvidedException("Title not provided");
    }
    return contactRepository.findByTitle(title);
  }

  @Override
  public List<Contact> findByPhoneNumber(String phoneNumber) throws ParamaterNotProvidedException {
    if (phoneNumber == null || "".equals(phoneNumber)) {
      throw new ParamaterNotProvidedException("Phone number not provided");
    }
    return contactRepository.findByPhoneNumber(phoneNumber);
  }

  @Override
  public List<Contact> findByDateOfBirth(Integer fromDate, Integer toDate) throws ParamaterNotProvidedException {
    if (fromDate == null || toDate == null) {
      throw new ParamaterNotProvidedException("Date not provided");
    }
    return allContacts().stream()
        .filter(contact -> Integer.parseInt(contact.getDateOfBirth()) > fromDate
            && (Integer.parseInt(contact.getDateOfBirth()) < toDate))
        .collect(Collectors.toList());
  }

  @Override
  public Contact findByName(String name) throws ParamaterNotProvidedException {
    if (name == null || "".equals(name)) {
      throw new ParamaterNotProvidedException("Name not provided");
    }
    return contactRepository.findbyName(name);
  }

  @Override
  public List<String> addBulkContact(List<Contact> contactList) throws ContactNotProvidedException {
    if (contactList == null || contactList.size() == 0) {
      throw new ContactNotProvidedException("Empty or non existent contactlist");
    }
    List<String> response = new ArrayList<>();
    for (Contact contact : contactList) {
      response.add(addContact(contact));
    }
    return response;
  }

  @Override
  public List<String> deleteBulkContact(List<Contact> contactList) throws
      ContactNotFoundException, ContactNotProvidedException {
    if (contactList == null || contactList.size() == 0) {
      throw new ContactNotProvidedException("Empty or non existent contacts");
    }
    List<String> response = new ArrayList<>();
    for (Contact contact : contactList) {
      response.add(deleteContact(contact));
    }
    return response;
  }

  @Override
  public List<Contact> findByAddress(String address) throws ParamaterNotProvidedException {
    if (address == null || "".equals(address)) {
      throw new ParamaterNotProvidedException("Parameter not provided");
    }
    return contactRepository.findByAllAddress(address);
  }
}



