package com.phoneBook2.services;

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
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
//@TestPropertySource(
//    locations = "classpath:application.properties")
public class proba {

  private static final long TEST_DATA_SIZE = 12;
  @Autowired
  private ContactService contactService;
  @Autowired
  private ContactRepository contactRepository;
  @Autowired
  private JsonConverterService jsonConverterService;

  public static final String FROM_DATA_JSON = "src/test/resources/testData.json";



  private List<Contact> getAllContacts() {
    List<Contact> contacts = new ArrayList<>();
    contactRepository.findAll().forEach(contacts::add);
    return contacts;
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
    List<Contact> contacts = getAllContacts();
    contactRepository.deleteAll();
    List<Contact> cohortsAfterDelete = getAllContacts();

    Assert.assertEquals(TEST_DATA_SIZE, contacts.size());
    Assert.assertEquals(0, cohortsAfterDelete.size());
  }

  @Test
  public void addContact() {
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