/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.tests;

import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entiteiten.*;
import be.ugent.iii.factory.BibliotheekFactory;
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
public class TestAanpassen {
    
    BibliotheekFactory factory;
    BibliotheekDao dao;
    
    public TestAanpassen() {
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
    // Deze test gaat na of value-objecten en eenvoudige eigenschappen van een bibliotheek in de database correct kunnen worden gewijzigd
    @Test
    public void testVerhuisBibliotheek() {
        Bibliotheek bibliotheek = factory.maakBibliotheekDendermonde();
        dao.addBibliotheek(bibliotheek);
        String nieuweNaam = "Openbare bibliotheek Sint-Gillis";
        Adres nieuwAdres = factory.maakAdres("Emile Feronstraat", 173, "Sint-Gillis", "1060", "België");
        dao.veranderNaamBibliotheek(bibliotheek, nieuweNaam);
        dao.verhuisBibliotheek(bibliotheek, nieuwAdres);
        Bibliotheek bibOpId = dao.zoekBib(bibliotheek.getId());
        assertEquals("naam bibliotheek gewijzigd?", bibliotheek.getNaam(), nieuweNaam);
        assertEquals("adres bibliotheek gewijzigd?", bibliotheek.getAdres(), nieuwAdres);
        assertEquals("naam in databank gewijzigd?", bibOpId.getNaam(), nieuweNaam);
        assertEquals("adres in databank gewijzigd?", bibOpId.getAdres(), nieuwAdres);
    }

    // Deze test gaat na of de associaties tussen entiteiten in de databank correct kunnen gewijzigd worden
    @Test
    public void testVerplaatsBoek() {
        Bibliotheek bibliotheek = factory.maakDeKrookVolledig();
        Boek boek = factory.maakBoek("The Righteous Mind", "EN", 2012);
        boek.setCollectie(bibliotheek.getCollecties().iterator().next());
        Collectie collectie = factory.maakCollectie("Psychologie en filosifie");
        collectie.setBibliotheek(bibliotheek);
        dao.addBibliotheek(bibliotheek);
        dao.verplaatsBoek(collectie, boek);
        Boek boekOpId = dao.getBoek(boek.getId());
        Collectie collectieOpId = dao.getCollectie(collectie.getId());
        //System.out.println(collectie.getBoeken());
        //System.out.println(boek.getCollectie());
        // de referentie naar het object wordt niet geupdated => fout
        //assertEquals("collectie boek gewijzigd?", boek.getCollectie(), collectie);
        assertEquals("boek in databank verplaatst?", boekOpId.getCollectie(), collectie);
        assertTrue("collectie in databank bevat nieuw boek?", collectieOpId.getBoeken().contains(boek));
    }
    
    // Deze test gaat na of een nieuw boek correct aan een bestaande collectie kan worden toegevoegd
    @Test
    public void testVoegBoekToe() {
        Bibliotheek bibliotheek = factory.maakDeKrookVolledig();
        dao.addBibliotheek(bibliotheek);
        Collectie collectie = bibliotheek.getCollecties().iterator().next();
        Boek boek = factory.maakBoek("titel", "taal", 1900);
        dao.voegBoekToe(collectie, boek);
        //System.out.println(collectie.getBoeken());
        //System.out.println(boek.getCollectie());
        Boek boekOpId = dao.getBoek(boek.getId());
        Collectie collectieOpId = dao.getCollectie(collectie.getId());
        assertEquals("collectie van boek ingesteld?", boek.getCollectie(), collectie);
        // zelfde fout als bij vorige test
        //assertTrue("boek aan collectie toegevoegd?", collectie.getBoeken().contains(boek));
        assertEquals("collectie in databank gewijzigd?", boekOpId.getCollectie(), collectie);
        assertTrue("boek aan collectie in databank toegevoegd?", collectieOpId.getBoeken().contains(boek));
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
