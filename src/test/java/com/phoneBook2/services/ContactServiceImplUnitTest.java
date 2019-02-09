package com.phoneBook2.services;

import com.phoneBook2.exceptions.ContactAlreadyExistsException;
import com.phoneBook2.exceptions.ContactNotFoundException;
import com.phoneBook2.exceptions.ContactNotProvidedException;
import com.phoneBook2.exceptions.ParamaterNotProvidedException;
import com.phoneBook2.models.Address;
import com.phoneBook2.models.Contact;
import com.phoneBook2.repositories.ContactRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceImplUnitTest {
  private ContactService contactService;

  @Mock
  private ContactRepository contactRepository;

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private String firstName = "Joe";
  private String lastName = "Smith";
  private String title = "`Mr";
  private String dateOfBirth = "`19891123";
  private List<String> phoneNumbers = Arrays.asList("1-123-123-1234");
  private String country = "USA";
  private String city = "New York";
  private String zipCode = "112233";
  private String street = "Elm Street 9.";
  private String fullName = firstName + " " + lastName;
  private List<Address> addresses = Arrays.asList(new Address(country, zipCode, city, street));
  private Contact testContact = new Contact(firstName, lastName, dateOfBirth, phoneNumbers, addresses);


  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    contactService = new ContactServiceImpl(contactRepository);
  }


  @Test
  public void allContactsFound() {
    List<Contact> contactList = new ArrayList<>(Arrays.asList(new Contact()));
    when(contactRepository.findAll()).thenReturn(contactList);
    assertEquals(contactList, contactService.allContacts());
  }

  @Test
  public void addContactSuccess() throws ContactNotProvidedException {
    when(contactRepository.save(any(Contact.class))).thenReturn(testContact);
    contactService.addContact(testContact);
  }

  @Test(expected = ContactNotProvidedException.class)
  public void addContactContactNull() throws ContactNotProvidedException {
    contactService.addContact(null);
  }

  @Test
  public void addContactContactSuccess() throws ContactAlreadyExistsException {
    when(contactRepository.findbyName(fullName)).thenReturn(null);
    contactService.contactExistsByName(fullName);
    contactService.addContact(testContact);
  }

  @Test(expected = ParamaterNotProvidedException.class)
  public void contactExistsByNameEmptyString() throws ParamaterNotProvidedException {
    contactService.contactExistsByName("");
  }

  @Test(expected = ParamaterNotProvidedException.class)
  public void contactExistsByNameNull() throws ParamaterNotProvidedException {
    contactService.contactExistsByName(null);
  }

  @Test
  public void contactExistsByNameTrue() throws ParamaterNotProvidedException {
    when(contactRepository.findbyName(fullName)).thenReturn(testContact);
    assertTrue(contactService.contactExistsByName(fullName));
  }

  @Test
  public void contactExistsByNameFalse() throws ParamaterNotProvidedException {
    when(contactRepository.findbyName(fullName)).thenReturn(null);
    assertFalse(contactService.contactExistsByName(fullName));
  }

  @Test(expected = ContactNotProvidedException.class)
  public void deleteContactContactNull() {
    contactService.deleteContact(null);
  }

  @Test
  public void deleteContactContactContactExists() {
    when(contactRepository.findbyName(testContact.fullName())).thenReturn(testContact);
    contactService.contactExistsByName(testContact.fullName());
    contactService.deleteContact(testContact);
  }

  @Test(expected = ParamaterNotProvidedException.class)
  public void findByFirstName() {
    contactService.findByFirstName(null);
  }

  @Test(expected = ParamaterNotProvidedException.class)
  public void findByLastName() {
    contactService.findByLastName("");
  }

  @Test(expected = ParamaterNotProvidedException.class)
  public void findBytitle() {
    contactService.findBytitle(null);
  }

  @Test(expected = ParamaterNotProvidedException.class)
  public void findByPhoneNumber() {
    contactService.findByPhoneNumber("");
  }

  @Test(expected = ParamaterNotProvidedException.class)
  public void findByDateOfBirth() {
    contactService.findByDateOfBirth(null, 19921126);
  }

  @Test(expected = ParamaterNotProvidedException.class)
  public void findByName() {
    contactService.findByName("");
  }
}