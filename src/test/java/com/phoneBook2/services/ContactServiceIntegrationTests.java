package com.phoneBook2.services;

import com.phoneBook2.exceptions.ContactNotFoundException;
import com.phoneBook2.exceptions.ParamaterNotProvidedException;
import com.phoneBook2.models.Address;
import com.phoneBook2.models.Contact;
import com.phoneBook2.repositories.ContactRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceIntegrationTests {

  private static final long TEST_DATA_SIZE = 12;
  @Autowired
  private ContactService contactService;
  @Autowired
  private ContactRepository contactRepository;
  @Autowired
  private JsonConverterService jsonConverterService;

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
  public static final String FROM_DATA_JSON = "src/test/resources/testData.json";


  private List<Contact> getAllContacts() {
    return contactRepository.findAll();
  }

  @Before
  public void setUp() throws Exception {
    jsonConverterService.saveJsonToDB(Paths.get(FROM_DATA_JSON));
  }

  @After
  public void tearDown() throws Exception {
    contactRepository.deleteAll();
  }

  @Test
  public void allContacts() {
    List<Contact> contacts = contactService.allContacts();
    contactRepository.deleteAll();
    List<Contact> cohortsAfterDelete = getAllContacts();

    Assert.assertEquals(TEST_DATA_SIZE, contacts.size());
    Assert.assertEquals(0, cohortsAfterDelete.size());

  }

  @Test
  public void addContact() {
    List<Contact> contactsBefore = getAllContacts();
    contactService.addContact(testContact);
    List<Contact> contactsAfter = getAllContacts();

    Assert.assertEquals(TEST_DATA_SIZE, contactsBefore.size());
    Assert.assertEquals(TEST_DATA_SIZE + 1, contactsAfter.size());
  }

  @Test
  public void contactExistsByName() {
    List<Contact> contacts = getAllContacts();
    String nameExists = "John Doe";
    String nameDoesntExist = "Kis Doe";

    Assert.assertTrue(contactService.contactExistsByName(nameExists));
    Assert.assertFalse(contactService.contactExistsByName(nameDoesntExist));
  }

  @Test
  public void deleteContactIfExists() {
    List<Contact> contactsBefore = getAllContacts();
    Contact contact = getAllContacts().get(0);
    contactService.deleteContact(contact);
    List<Contact> contactsAfter = getAllContacts();

    Assert.assertEquals(TEST_DATA_SIZE, contactsBefore.size());
    Assert.assertEquals(TEST_DATA_SIZE - 1, contactsAfter.size());
  }

  @Test
  public void findByLastNameFirstNameTitle_firstNameANDLastName() {
    List<Contact> results = contactService.findByLastNameFirstNameTitle("Doe", "John", null);

    Assert.assertEquals(1L, results.size());
  }

  @Test
  public void findByLastNameFirstNameTitle_firstNameANDTitle() {
    List<Contact> results = contactService.findByLastNameFirstNameTitle(null, "John", "Mr");

    Assert.assertEquals(0, results.size());
  }

  @Test
  public void findByFirstName() {
    List<Contact> results = contactService.findByFirstName("John");

    Assert.assertEquals(2, results.size());
  }

  @Test(expected = ParamaterNotProvidedException.class)
  public void findByFirstName_emptyString() {
    List<Contact> results = contactService.findByFirstName("");
  }

  @Test(expected = ParamaterNotProvidedException.class)
  public void findByLastName_Null() {
    List<Contact> results = contactService.findByLastName(null);
  }

  @Test
  public void findByLastName_Success() {
    List<Contact> results = contactService.findByLastName("Doe");

    Assert.assertEquals(2, results.size());
  }

  @Test
  public void findBytitle() {
    List<Contact> results = contactService.findBytitle("Mr");

    Assert.assertEquals(3, results.size());

  }

  @Test
  public void findByPhoneNumber() {
    List<Contact> results = contactService.findByPhoneNumber("1-202-555-0189");

    Assert.assertEquals(1, results.size());
  }

  @Test
  public void findByDateOfBirth() {
    List<Contact> results = contactService.findByDateOfBirth(19771212, 19891112);

    Assert.assertEquals(1, results.size());
  }

  @Test
  public void findByName() {
    Contact results = contactService.findByName("Jane Doe");

    Assert.assertEquals(results.fullName(), "Jane Doe");

  }

  @Test
  public void addBulkContact() {
  }

  @Test
  public void deleteBulkContact() {
  }

  @Test
  public void findByAddress() {
  }
}