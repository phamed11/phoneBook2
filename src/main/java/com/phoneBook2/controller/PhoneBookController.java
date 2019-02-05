package com.phoneBook2.controller;

import com.phoneBook2.models.Contact;
import com.phoneBook2.services.ContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneBookController {

  private ContactService contactService;

  public PhoneBookController(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping
  public List<Contact> main(){
    return contactService.allContacts();
  }
}
