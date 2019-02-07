package com.phoneBook2.controller;

import com.phoneBook2.models.Contact;
import com.phoneBook2.services.ContactService;
import com.phoneBook2.services.JsonConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PhoneBookRestController {

  private ContactService contactService;
  private JsonConverterService jsonConverterService;

  @Autowired
  public PhoneBookRestController(ContactService contactService, JsonConverterService jsonConverterService) {
    this.contactService = contactService;
    this.jsonConverterService = jsonConverterService;
  }

  @PostMapping("/add")
  public ResponseEntity<?> main(@RequestBody Contact contact) {
    boolean exists = contactService.contactExistsByName(contact.fullName());
    contactService.addContact(contact);
    return ResponseEntity.ok(exists ? "Contact already exists" : "Contact with name: " + contact.fullName() + " created.");
  }

  @GetMapping("/db")
  public String sendFileFromDB() {
    return jsonConverterService.sendAllContactsToJson();
  }

  @GetMapping("/all")
  public List<Contact> allContact() {
    return contactService.allContacts();
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteContact(@RequestBody Contact contact) {
    if (contactService.contactExistsByName(contact.fullName())) {
      contactService.deleteContact(contact);
      return ResponseEntity.status(200).body("Contact with name: " + contact.fullName() + " deleted");
    } else {
      return ResponseEntity.status(404).body("Contact not found");
    }
  }

  @GetMapping("/filter")
  public List<Contact> findByLastNameOrFirstNameOrTitle(@RequestParam(value = "lastName", required = false) String lastName,
                                                        @RequestParam(value = "firstName", required = false) String firstName,
                                                        @RequestParam(value = "title", required = false) String title) {
    return contactService.findByLastNameFirstNameTitle(lastName, firstName, title);
  }

  @GetMapping("/fname")
  public List<Contact> findByFirstName(@RequestParam(value = "firstName", required = true) String firstName) {
    return contactService.findByFirstName(firstName);
  }

  @GetMapping("/lname")
  public List<Contact> findByLastName(@RequestParam(value = "lastName", required = true) String lastName) {
    return contactService.findByLastName(lastName);
  }

  @GetMapping("/title")
  public List<Contact> findByTitle(@RequestParam(value = "title", required = true) String title) {
    return contactService.findBytitle(title);
  }

  @GetMapping("/name")
  public Contact findByName(@RequestParam(value = "name", required = true) String name) {
    return contactService.findByName(name);
  }

  @GetMapping("/phone")
  public List<Contact> findByPhoneNumber(@RequestParam(value = "phone", required = true) String phoneNumber) {
    return contactService.findByPhoneNumber(phoneNumber);
  }

  @DeleteMapping("/bdelete")
  public ResponseEntity<?> bulkDelete(@RequestBody List<Contact> contactList) {
    contactService.deleteBulkContact(contactList);
    return ResponseEntity.status(200).body("deleted");
  }

  @PostMapping("/badd")
  public void bulkAdd(@RequestBody List<Contact> contactList) {
    contactService.addBulkContact(contactList);
  }

  @GetMapping("/bDate")
  public List<Contact> findByDateOfBirth(@RequestParam (value = "fromDate", required = true) Integer fromDate,
                                         @RequestParam (value = "toDate", required = true) Integer toDate) {
    return contactService.findByDateOfBirth(fromDate, toDate);
  }

  @GetMapping("/address")
  public List<Contact> findbyAddress(@RequestParam (value = "address", required = true) String address) {
    return contactService.findByAddress(address);
  }
}

