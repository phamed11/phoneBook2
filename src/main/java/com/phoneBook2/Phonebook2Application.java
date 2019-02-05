package com.phoneBook2;

import com.phoneBook2.models.Address;
import com.phoneBook2.models.Contact;
import com.phoneBook2.services.ContactService;
import com.phoneBook2.services.JsonConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Paths;
import java.util.Arrays;


@SpringBootApplication
public class Phonebook2Application implements CommandLineRunner {

  public static final String TO_DATA_JSON = "Assets/data.json";
  public static final String FROM_DATA_JSON = "src/main/resources/testData.json";
  private JsonConverterService jsonConverterService;
  private ContactService contactService;

  @Autowired
  public Phonebook2Application(JsonConverterService jsonConverterService, ContactService contactService) {
    this.jsonConverterService = jsonConverterService;
    this.contactService = contactService;
  }

  public static void main(String[] args) {
    SpringApplication.run(Phonebook2Application.class, args);


  }

  @Override
  public void run(String... args) throws Exception {

    jsonConverterService.saveJsonToDB(Paths.get(FROM_DATA_JSON));

    Contact peter = new Contact("peter", "antal", "19771126", (Arrays.asList("1-445-444-44444")),
        (Arrays.asList(new Address("Hungary", "1136", "Budapest", "Gabor Aron"))));

    contactService.addContact(peter);

    jsonConverterService.contactsToJson(contactService.allContacts(), Paths.get(TO_DATA_JSON));

  }
}

