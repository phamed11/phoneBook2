package com.phoneBook2.controller;

import com.phoneBook2.exceptions.ContactNotFoundException;
import com.phoneBook2.exceptions.ContactNotProvidedException;
import com.phoneBook2.exceptions.ParamaterNotProvidedException;
import com.phoneBook2.models.Contact;
import com.phoneBook2.services.ContactService;
import com.phoneBook2.services.JsonConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    try {
      if (contactService.contactExistsByName(contact.fullName())) {
        contactService.deleteContact(contact);
        return ResponseEntity.status(200).body("Contact with name: " + contact.fullName() + " deleted");
      } else {
        return ResponseEntity.status(404).body("Contact not found");
      }
    } catch (ContactNotProvidedException e) {
      return new ResponseEntity<>("No contact provided", HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/filter")
  public ResponseEntity<?> findByLastNameOrFirstNameOrTitle(@RequestParam(value = "lastName", required = false) String lastName,
                                                        @RequestParam(value = "firstName", required = false) String firstName,
                                                        @RequestParam(value = "title", required = false) String title) {
    try {
      if (contactService.findByLastNameFirstNameTitle(lastName, firstName, title).size() != 0) {
        return new ResponseEntity<>(contactService.findByLastNameFirstNameTitle(lastName, firstName, title), HttpStatus.OK);
      } else {
        return new ResponseEntity<>("No contact not found", HttpStatus.NOT_FOUND);
      }
    } catch (ParamaterNotProvidedException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }  }

  @GetMapping("/firstName")
  public ResponseEntity<?> findByFirstName(@RequestParam(value = "firstName", required = false) String firstName) {
    try {
      if (contactService.findByFirstName(firstName).size() != 0) {
        return new ResponseEntity<>(contactService.findByFirstName(firstName), HttpStatus.OK);
      } else {
        return new ResponseEntity<>("No contact not found", HttpStatus.NOT_FOUND);
      }
    } catch (ParamaterNotProvidedException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }


  @GetMapping("/lastName")
  public ResponseEntity<?> findByLastName(@RequestParam(value = "lastName", required = false) String lastName) {
    try {
      if (contactService.findByLastName(lastName).size() != 0) {
        return new ResponseEntity<>(contactService.findByLastName(lastName), HttpStatus.OK);
      } else {
        return new ResponseEntity<>("No contact not found", HttpStatus.NOT_FOUND);
      }
    } catch (ParamaterNotProvidedException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/title")
  public ResponseEntity<?> findByTitle(@RequestParam(value = "title", required = false) String title) {
    try {
      if (contactService.findBytitle(title).size() != 0) {
        return new ResponseEntity<>(contactService.findBytitle(title), HttpStatus.OK);
      } else {
        return new ResponseEntity<>("No contact not found", HttpStatus.NOT_FOUND);
      }
    } catch (ParamaterNotProvidedException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/name")
  public ResponseEntity<?> findByName(@RequestParam(value = "name", required = false) String name) {
    try {
      if (contactService.findByName(name) != null) {
        return new ResponseEntity<>(contactService.findByName(name), HttpStatus.OK);
      } else {
        return new ResponseEntity<>("No contact not found", HttpStatus.NOT_FOUND);
      }
    } catch (ParamaterNotProvidedException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/phone")
  public ResponseEntity<?> findByPhoneNumber(@RequestParam(value = "phone", required = false) String phoneNumber) {
    try {
      if (contactService.findByPhoneNumber(phoneNumber).size() != 0) {
        return new ResponseEntity<>(contactService.findByPhoneNumber(phoneNumber), HttpStatus.OK);
      } else {
        return new ResponseEntity<>("No contact not found", HttpStatus.NOT_FOUND);
      }
    } catch (ParamaterNotProvidedException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/bulkDelete")
  public ResponseEntity<?> bulkDelete(@RequestBody List<Contact> contactList) {
    contactService.deleteBulkContact(contactList);
    return ResponseEntity.status(200).body("deleted");
  }

  @PostMapping("/bulkAdd")
  public void bulkAdd(@RequestBody List<Contact> contactList) {
    contactService.addBulkContact(contactList);
  }

  @GetMapping("/birthDate")
  public List<Contact> findByDateOfBirth(@RequestParam(value = "fromDate", required = false) Integer fromDate,
                                         @RequestParam(value = "toDate", required = false) Integer toDate) {
    return contactService.findByDateOfBirth(fromDate, toDate);
  }

  @GetMapping("/address")
  public ResponseEntity<?> findByAddress(@RequestParam(value = "address", required = false) String address) {
    try {
      if (contactService.findByAddress(address).size() != 0) {
        return new ResponseEntity<>(contactService.findByAddress(address), HttpStatus.OK);
      } else {
        return new ResponseEntity<>("No contact not found", HttpStatus.NOT_FOUND);
      }
    } catch (ParamaterNotProvidedException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }  }
}

