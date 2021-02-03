# Datalayer for Library 

## Settings and installation

- Clone project
- Right click on project in Netbeans "build with dependencies"
- GUI: right click on BibliotheekGUI "run file"

### Database properties
- url = jdbc:mysql://localhost:3306/jpadb?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC

- username: iii

- password: iiipwd

## Overview of activities

14-12-2018: ~~Communicate with database using HTTP~~ 

15-12-2018: First version GUI, discuss database structure

17-12-2018: Add entities + DAO class with entitymanager instead of controller with HTTP requests

18-12-2018: GUI using new DAO class, further implementation database

19-12-2018: Research on transient/detached state + further implementation database and unit tests

20 t.e.m 22-12-2018: Debugging, unit tests and GUI 


## Database structure
See klassendiagram.pdf
- A library contains members and collections
- A collection contains books and belongs to a library
- A member has loans, those consist of a member-id and a book-id
- A book has one or more authors and belongs to a collection
- An author has one or more books

* 1-1 relation
  * Book - loan
* 1-n relation
  * Library - Collections
  * Library - Members
  * Collections - Books
  * Member - Loans
* n - n relaties
  * Books - Authors
* Relations with cascading
  * Library -> Collection -> Book
* Relations without cascading
  * Book -> Collection
* Inheritance: Member and Author are derived from Person, we chose for the table-per-class database strategy
* Value-object: The Adress object in Library and Member objects
* Lazy loading: Collection with associated Books
* Eager loading: Library with associated Collections

## Functionalities of datalayer
We provided unittests for the CRUD operations on objects.
* Library
  * Moving to new place (name/adress update)
  * Requesting all collections and books
  * Requesting all members 
* Collection
  * Requesting 
* Author
  * Requesting all books of an author
* Books
  * Adding/removing
  * Changing library/collection 
  * Searching on language/author
* Member
  * Adding and removing
* Loans
  * Adding and removing
  
  ##

## GUI functionalities
- Register member with first name and surname
- Logging in with user ID
- Searching for libraries,collections,authors,... : all, by ID or by name
- Display available books
