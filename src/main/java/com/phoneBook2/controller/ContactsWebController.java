package com.phoneBook2.controller;

import com.phoneBook2.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactsWebController {

  private ContactService contactService;

  @Autowired
  public ContactsWebController(ContactService contactService) {
    this.contactService = contactService;
  }

  @GetMapping("/")
  public String mainPage(Model model){
    model.addAttribute("allContacts", contactService.allContacts());
    return "index";
  }
}
