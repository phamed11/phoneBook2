[![CircleCI](https://circleci.com/gh/phamed11/phoneBook2.svg?style=svg)](https://circleci.com/gh/phamed11/phoneBook2) 
[![Build Status](https://travis-ci.org/phamed11/phoneBook2.svg?branch=master)](https://travis-ci.org/phamed11/phoneBook2)


# Phone book Rest application

Simple command-line application to store phone numbers in Json format.

## Getting Started

### Prerequisites
 * Intellij IDEA
 
#### Set up Lombok
 
 Our project uses [Lombok](https://projectlombok.org/), to enable it in IntelliJ you have to add its plugin 
  * follow these [instructions](https://projectlombok.org/setup/intellij)
  * restart IntelliJ IDEA
  * make sure that annotation processing is enabled in `Settings>Build>Compiler>Annotation processors`
  
  
#### Environment variables

**Database connection**

| Key | Value |
| --- | ----- | 
|DB_NAME_LO | *jdbc:mysql://localhost:3306/''create it locally* |
|DB_USERNAME | *your local mysql username* |
|DB_PASSWORD | *your local mysql password* |


**Spring profiles**

| Key | Value |
| --- | ----- | 
|ACTIVE_PROFILE | local  |

#### (Optional) Add datasource to Intellij IDEA

Open `View > Tool Windows > Database` and connect to your previously created database

  
  



