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

18-12-2018: GUI die gebruik maakt van de nieuwe DAO klasse

## To Do
- Functionaliteiten toevoegen:
  - Objecten opvragen ("lazy" + niet "lazy")
  - Objecten opvragen via parameters
  - Objecten aanpassen
- Unittests schrijven
  - speciale gevallen bedenken en testen
- Eventueel:
  - GUI afwerken

## Bugs
- objecten met associaties toevoegen geeft errors van het type:
```
object references an unsaved transient instance...
of 
detached entity passed to persist
```
- entiteiten kunnen niet via hun ID vergeleken worden voor ze in de database worden opgeslagen (nog niet toegekend door de entitymanager)
 
