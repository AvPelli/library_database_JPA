/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.tests;

import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entiteiten.*;
import be.ugent.iii.factory.BibliotheekFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author axeld
 */
public class TestOpzoeken {

    BibliotheekFactory factory;
    BibliotheekDao dao;

    public TestOpzoeken() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        factory = new BibliotheekFactory();
        dao = new BibliotheekDao();
    }

    @After
    public void tearDown() {
        try {
            dao.close();
        } catch (Exception ex) {
            System.out.println("Kan entitymanager niet sluiten!");
        }
    }

    // TODO add test methods here.
    // Deze test gaat na of alle collecties uit de database correct kunnen worden opgevraagd
    @Test
    public void testGetCollecties() {
        List<Collectie> verwacht = dao.getCollecties();
        Bibliotheek bibliotheek = factory.maakDeKrookVolledig();
        verwacht.addAll(bibliotheek.getCollecties());
        dao.addBibliotheek(bibliotheek);
        List<Collectie> werkelijk = dao.getCollecties();
        assertEquals("alle collecties opgevraagd?", new HashSet<>(verwacht), new HashSet<>(werkelijk));
    }
    
    // Deze test gaat na of een bibliotheek samen met haar collecties (EAGER) en met haar boeken (LAZY) kan worden opgevraagd
    @Test
    public void testGetBibliotheekMetCatalogus() {
        Bibliotheek bibliotheek = factory.maakDeKrookVolledig();
        dao.addBibliotheek(bibliotheek);
        Bibliotheek bibOpId = dao.zoekBibMetBoeken(bibliotheek.getId());
        assertEquals("collecties mee opgevraagd?", new HashSet<>(bibliotheek.getCollecties()), new HashSet<>(bibOpId.getCollecties()));
        assertEquals("boeken mee opgevraagd?", new HashSet<>(bibOpId.getBoeken()), new HashSet<>(bibliotheek.getBoeken()));
    }
     
    // Deze test gaat na of een bibliotheek correct op id kan opgevraagd worden 
    @Test
    public void testGetBibliotheek() {
        int aantalVoor = dao.getBibliotheken().size();
        Bibliotheek bibliotheek = factory.maakDeKrookVolledig();
        dao.addBibliotheek(bibliotheek);
        int aantalNa = dao.getBibliotheken().size();
        Bibliotheek resultaat = dao.zoekBib(bibliotheek.getId());
        assertEquals("bibliotheek toegevoegd?", aantalVoor + 1, aantalNa);
        assertEquals("bilbiotheek opgevraagd op id?", resultaat, bibliotheek);
    }
    
    // Deze test gaat na of een bib samen met haar leden correct LAZY wordt opgevraagd
    @Test
    public void testGetBibliotheekMetLedenLazy() {
        Bibliotheek bibliotheek = factory.maakDeKrookVolledig();
        dao.addBibliotheek(bibliotheek);
        Bibliotheek bibOpId = dao.zoekBib(bibliotheek.getId());
        assertEquals("alle leden opgevraagd?", new HashSet<>(bibliotheek.getLeden()), new HashSet<>(bibOpId.getLeden()));
        
    }
    
    // Deze test gaat na of boeken correct kunnen worden opgevraagd via
    // de naam en voornaam van één van hun auteurs
    // Opmerking: we gaan er hier (wat onvoorzichtig) vanuit dat geen 2 verschillende auteurs dezelfde naam hebben in de databank
    @Test
    public void testGeefBoekenVanAuteur() {
        int aantalVoor = dao.getIdOfBoekenByAuteur("Andrew", "Tanenbaum").size();
        //Andrew Tanenbaum krijgt 2 boeken in deze factory methode:
        dao.addBibliotheek(factory.maakDeKrookVolledig());
        int aantalNa = dao.getIdOfBoekenByAuteur("Andrew", "Tanenbaum").size();
        assertEquals(aantalVoor + 2, aantalNa);
    }

    // Deze test gaat na of een collectie boeken kan worden opgevraagd via hun taal
    @Test
    public void testZoekBoekenMetTaal() {
        int aantalEngels = dao.getBoekenByTaal("EN").size();
        int aantalNederlands = dao.getBoekenByTaal("NL").size();
        Bibliotheek bibliotheek = factory.maakDeKrookVolledig();
        for (Boek boek : bibliotheek.getBoeken()) {
            if (boek.getTaal().equals("EN")) {
                aantalEngels++;
            }
            else if (boek.getTaal().equals("NL")) {
                aantalNederlands++;
            }
        }
        dao.addBibliotheek(bibliotheek);
        assertEquals("engelstalige boeken toegevoegd?", dao.getBoekenByTaal("EN").size(), aantalEngels);
        assertEquals("nederlandstalige boeken toegevoegd?", dao.getBoekenByTaal("NL").size(), aantalNederlands);
       
        //Test engels
        List<Boek> resultEngels = dao.getBoekenByTaal("EN");
        boolean allesEngels = true;
        for (Boek b : resultEngels) {
            if (!b.getTaal().equalsIgnoreCase("EN")) {
                allesEngels = false;
                break;
            }
        }
        assertTrue("alle boeken engelstalig?", allesEngels);

        //Test nederlands
        List<Boek> resultNederlands = dao.getBoekenByTaal("NL");

        boolean allesNederlands = true;
        for (Boek b : resultNederlands) {
            if (!b.getTaal().equalsIgnoreCase("NL")) {
                allesNederlands = false;
                break;
            }
        }
        assertTrue("alle boeken nederlandstalig?", allesNederlands);
    }
    
    
    // hulpmethode
    private static void toonBibliotheek(Bibliotheek bibliotheek) {
        System.out.println("-----BIBLIOTHEEK-----");
        System.out.println(bibliotheek);
        System.out.println("-----Catalogus-----");
        for (Collectie collectie : bibliotheek.getCollecties()) {
            System.out.println(collectie);
            for (Boek boek : collectie.getBoeken()) {
                System.out.println("\t" + boek);
                for (Auteur auteur : boek.getAuteurs()) {
                    System.out.println("\t\t" + auteur);
                }
            }
        }
        System.out.println("-----Einde catalogus-----");
        System.out.println("-----EINDE BIBLIOTHEEK-----");
    }
}
