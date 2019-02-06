package com.phoneBook2.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.phoneBook2.exceptions.JsonConverterFileNotFoundException;
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
public class JsonConverterService implements HasLogger {

  ContactRepository contactRepository;
  public static final Type COLLECTION_TYPE = new TypeToken<Collection<Contact>>() {
  }.getType();

  @Autowired
  public JsonConverterService(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  public void contactsToJson(List<Contact> contacts, Path path) {
    try {
      if (!Files.exists(path.getParent())) {
        Files.createDirectories(path.getParent());
        getLogger().info("Directory created");
      }
      Files.write(path, new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(contacts, COLLECTION_TYPE).getBytes());
    } catch (IOException e) {
      e.getMessage();
    }
  }

  private List<Contact> jsonToContacts(Path path) {
    if (path == null) {
      getLogger().error("File is missing!");
      throw new JsonConverterFileNotFoundException("File is missing!");
    }
    String content = "";
    try {
      content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
      getLogger().info("Json parsed to Contacts");
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

  public String sendAllContactsToJson() {
    return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting()
        .create().toJson(contactRepository.findAll(), COLLECTION_TYPE);
  }

  public List<Contact> dbToJsonToContactList() {
    return new Gson().fromJson(sendAllContactsToJson(), COLLECTION_TYPE);
  }
}
