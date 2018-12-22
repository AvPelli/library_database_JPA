# groep-9

# Datalaag Bibliotheek 

## Instellingen en installatie

- Project clonen
- Rechts klikken op het project in Netbeans "build with dependencies"
- GUI: rechts klikken op BibliotheekGUI "run file"

### Database properties
- url = jdbc:mysql://localhost:3306/jpadb?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC

- username: iii

- password: iiipwd

## Overzicht activiteiten

14-12-2018: ~~Communiceren met database via HTTP requests~~ 

15-12-2018: Eerste versie GUI, overleg structuur database

17-12-2018: Entiteiten toegevoegd + DAO klasse met entitymanager ipv controller met http requests

18-12-2018: GUI die gebruik maakt van de nieuwe DAO klasse, verder uitwerken database

19-12-2018: Uitzoeken transient/detached state + verder uitwerken database en unit tests

20 t.e.m 22-12-2018: Debuggen, unit tests schrijven en GUI maken


## Structuur
Zie klassendiagram
- Een bibliotheek bevat leden en collecties
- Een collectie bevat boeken en behoort tot een bibliotheek
- Een lid heeft leningen, die bestaan uit een lid-ID en een boek-ID
- Een boek heeft één of meerdere auteurs en behoort tot een collectie
- Een auteur heeft één of meerde boeken

* 1-1 relatie
  * Boek - lening
* 1-n relatie
  * Bibliotheek - Collecties
  * Bibliotheek - Leden
  * Collectie - Boeken
  * Lid - Leningen
* n - n relaties
  * Boeken - Auteurs
* Relatie met cascade
  * o.a. Bibliotheek -> Collectie -> Boek
* Relatie zonder cascade
  * o.a. Boek -> Boek
* Overerving: Lid en Auteur zijn afgeleid van Persoon, we hebben voor table per class strategie gekozen
* Value-object: Adres voor Bibliotheek en Lid
* Lazy opvraging: o.a. Collectie met geassocieerde Boeken
* Eager opvraging: Bibliotheek met geassocieerde Collecties

### Functionaliteiten datalaag
We hebben unittests voorzien om objecten toe te voegen, aan te passen, op te zoeken en te verwijderen.
* Bibliotheek
  * Verhuizen (naam/adres wijziging)
  * Volledig met collecties en boeken opvragen
  * Leden opvragen 
* Collectie
  * Opvragen
* Auteur
  * Boeken van auteur opvragen
* Boeken
  * Toevoegen/verwijderen
  * Verhuizen van bibliotheek/collectie 
  * Opzoeken op taal/auteur
* Lid
  * Toevoegen en verwijderen
* Lening
  * Toevoegen en verwijderen
  
  ##

## GUI functionaliteiten
- Lid registreren met voor- en achternaam
- Inloggen met user ID
- Opzoeken bibliotheken,collecties,auteurs,... : alle, op ID of op naam (met enkele uitzonderingen)
- Bij het zoeken naar boeken worden apart ook de beschikbare boeken getoond

## Bugs
``` 
GUI werkt niet volledig, een bug die we niet konden oplossen i.v.m. "lazy initialization"
zorgt ervoor dat een boek uitlenen en terugbrengen in de GUI niet lukt (wel in de unit tests).
Deze error zal zichtbaar zijn in de console wanneer op de "leen" knop geduwd wordt
```
