package com.phoneBook2.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phoneBook2.models.Contact;
import com.phoneBook2.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JsonConverterService {

  ContactRepository contactRepository;
  public static final Type COLLECTION_TYPE = new TypeToken<List<Contact>>() {
  }.getType();

  @Autowired
  public JsonConverterService(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  public String contactsToJson(List<Contact> contacts) {
    return new Gson().toJson(contacts);
  }

  public List<Contact> jsonToContacts(Path path) {
    String content = "";
    try {
      content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    } catch (NoSuchFileException e) {
      System.out.println("File is missing!");
    } catch (IOException e) {
      System.out.println("File issues");
      e.printStackTrace();
    }
    return new Gson().fromJson(content, COLLECTION_TYPE);
  }

  public List<Contact> saveJsonToDB(Path path) {
    List<Contact> allContacts = jsonToContacts(path);
    return allContacts.stream()
        .map(contact -> contactRepository.save(contact))
        .collect(Collectors.toList());
  }

  public void saveToJsonFile(Path path) {
    try {
      Files.write(path, contactsToJson(contactRepository.findAll()).getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
