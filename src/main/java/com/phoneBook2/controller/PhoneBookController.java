package com.phoneBook2.controller;

import com.phoneBook2.models.Contact;
import com.phoneBook2.services.ContactService;
import com.phoneBook2.services.JsonConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class PhoneBookController {

  public static final String TO_DATA_JSON = "Assets/data.json";
  private ContactService contactService;
  private JsonConverterService jsonConverterService;

  @Autowired
  public PhoneBookController(ContactService contactService, JsonConverterService jsonConverterService) {
    this.contactService = contactService;
    this.jsonConverterService = jsonConverterService;
  }

  @GetMapping
  public List<Contact> main(){
    return contactService.allContacts();
  }

  @GetMapping("/file")
  public String sendFile() {
    return jsonConverterService.contactsToJson(contactService.allContacts(), Paths.get(TO_DATA_JSON));
  }
}
