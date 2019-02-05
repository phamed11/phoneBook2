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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JsonConverterService {

  ContactRepository contactRepository;
  public static final Type COLLECTION_TYPE = new TypeToken<Collection<Contact>>() {
  }.getType();

  @Autowired
  public JsonConverterService(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  public void contactsToJson(List<Contact> contacts, Path path) {
    // TODO exceptions to make
    try {
      Files.write(path, new Gson().toJson(contacts, COLLECTION_TYPE).getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
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
    // TODO exceptions to make
    List<Contact> allContacts = jsonToContacts(path);
    return allContacts.stream()
        .map(contact -> contactRepository.save(contact))
        .collect(Collectors.toList());
  }

}
