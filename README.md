# groep-9

# Datalaag Bibliotheek 

## Instellingen en installatie

### Database properties
url = jdbc:mysql://localhost:3306/jpadb?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC

username: iii

password: iiipwd

## Overzicht activiteiten

14-12-2018: Communiceren met database via HTTP requests 

15-12-2018: Eerste versie GUI, overleg structuur database

17-12-2018: Entiteiten toegevoegd + DAO klasse met entitymanager ipv controller met http requests

18-12-2018: GUI die gebruik maakt van de nieuwe DAO klasse, verder uitwerken database

19-12-2018: Uitzoeken transient/detached state + verder uitwerken database en unit tests

## To Do
- Functionaliteiten toevoegen:
  - Objecten opvragen ("lazy" + niet "lazy")
  - Objecten aanpassen
- Unittests schrijven
  - speciale gevallen bedenken en testen
- Eventueel:
  - GUI afwerken

## Bugs
- objecten met associaties toevoegen geeft errors van het type:
```
object references an unsaved transient instance...
```
Deze error is te wijten doordat het object een verwijzing bevat naar een ander object die zich in transient state bevindt (new Object())
Om deze te vermijden moet men eerst deze referentie naar de persistent state omzetten via entitymanager.persist
```
detached entity passed to persist
```
Deze error is te wijten aan een entity die al door de entitymanager opgeslagen is in de database (typisch na commit()), ze detached en dus niet meer beschikbaar voor de entitymanager
Oplossing: entity pas committen als er geen andere entities meer beroep op doen.

- entiteiten kunnen niet via hun ID vergeleken worden voor ze in de database worden opgeslagen (nog niet toegekend door de entitymanager)
 
