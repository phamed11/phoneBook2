package com.phoneBook2.controller;

import com.phoneBook2.models.Contact;
import com.phoneBook2.services.ContactService;
import com.phoneBook2.services.JsonConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PhoneBookRestController {

  private ContactService contactService;
  private JsonConverterService jsonConverterService;

  @Autowired
  public PhoneBookRestController(ContactService contactService, JsonConverterService jsonConverterService) {
    this.contactService = contactService;
    this.jsonConverterService = jsonConverterService;
  }

  @PostMapping("/api/add")
  public ResponseEntity<?> main(@RequestBody Contact contact) {
    boolean exists = contactService.contactExistsByName(contact.getName());
    contactService.addContact(contact);
    return ResponseEntity.ok(exists ? "Contact already exists" : "Contact with name: " + contact.getName() + " created.");
  }

  @GetMapping("/api/db")
  public String sendFileFromDB() {
    return jsonConverterService.sendAllContactsToJson();
  }
}
