package com.phoneBook2.controller;

import com.phoneBook2.exceptions.ContactNotFoundException;
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
    if(contactService.contactExistsByName(contact.fullName())) {
      contactService.deleteContact(contact);
      return ResponseEntity.status(200).body("Contact with name: " + contact.fullName() + " deleted");
    } else {
      return ResponseEntity.status(404).body("Contact not found");
    }
  }
}

