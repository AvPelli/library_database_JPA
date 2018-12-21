/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.tests;

import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entiteiten.*;
import be.ugent.iii.factory.BibliotheekFactory;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arthur
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
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testGetBibliotheekMetCatalogus() {
        Bibliotheek bibliotheek = factory.maakDeKrookVolledig();
        dao.addBibliotheek(bibliotheek);
        Bibliotheek resultaat = dao.getBibliotheekMetCatalogus(bibliotheek.getId());
        System.out.println(resultaat.getCollecties());
        System.out.println(resultaat);
        toonBibliotheek(bibliotheek);
        System.out.println(bibliotheek.getCollecties());
        System.out.println(resultaat.getCollecties());
        assertEquals(bibliotheek.getCollecties().size(), resultaat.getCollecties().size());
        //assertThat(bibliotheek.getCollecties(), is(resultaat.getCollecties()));
    }
    
    @Test
    public void GeefBoekenVanAuteur() {
        //Andrew Tanenbaum krijgt 2 boeken in deze factory methode:
        Bibliotheek b = factory.maakDeKrookVolledig();
        dao.addBibliotheek(b);

        List<Integer> boeken = dao.getIdOfBoekenByAuteur("Andrew", "Tanenbaum");

        assertEquals(boeken.size(), 2);
    }

    @Test
    public void ZoekBoekenMetTaal() {
        //Test engels
        List<Boek> resultEngels = dao.getBoekenByTaal("EN");

        boolean allesEngels = true;
        for (Boek b : resultEngels) {
            if (!b.getTaal().equalsIgnoreCase("EN")) {
                allesEngels = false;
                break;
            }
        }
        assertTrue("ALLES ENGELS: OK", allesEngels);

        //Test nederlands
        List<Boek> resultNederlands = dao.getBoekenByTaal("NL");

        boolean allesNederlands = true;
        for (Boek b : resultNederlands) {
            if (!b.getTaal().equalsIgnoreCase("NL")) {
                allesNederlands = false;
                break;
            }
        }
        assertTrue("ALLES NEDERLANDS: OK", allesNederlands);
    }
    
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