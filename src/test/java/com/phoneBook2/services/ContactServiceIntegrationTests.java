package com.phoneBook2.services;

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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
//@TestPropertySource(
//    locations = "classpath:application.properties")
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
  }

  @Test
  public void deleteContact() {
  }

  @Test
  public void findByLastNameFirstNameTitle() {
  }

  @Test
  public void findByFirstName() {
  }

  @Test
  public void findByLastName() {
  }

  @Test
  public void findBytitle() {
  }

  @Test
  public void findByPhoneNumber() {
  }

  @Test
  public void findByDateOfBirth() {
  }

  @Test
  public void findByName() {
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