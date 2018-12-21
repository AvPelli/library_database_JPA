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
public class TestVerwijderen {

    BibliotheekFactory factory;
    BibliotheekDao dao;

    public TestVerwijderen() {
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
    public void VerwijderBoekMetId() {
        //Verwijderen boek in tabel "boeken", in tabel "collectie
        Bibliotheek b = factory.maakDeKrookVolledig();
        dao.addBibliotheek(b);

        List<Integer> boeken = dao.getIdOfBoekenByAuteur("Andrew", "Tanenbaum");
        assertEquals(2, boeken.size());

        for (Integer id : boeken) {
            assertTrue(dao.VerwijderBoek(id));
        }

        //Alle boeken van Andrew Tanenbaum zijn verwijderd:
        List<Integer> result = dao.getIdOfBoekenByAuteur("Andrew", "Tanenbaum");
        assertNull(result);
    }

    @Test
    public void VerwijderLidMetId() {
        Bibliotheek b = factory.maakDeKrookVolledig();
        dao.addBibliotheek(b);

        List<Lid> ledenVoor = dao.getLeden();
        dao.VerwijderLid("Axel", "De Decker");

        assertEquals(ledenVoor.size() - 1, dao.getLeden().size());
        assertNull(dao.getLid("Axel", "De Decker"));

    }

    @Test
    public void LeningVerwijderen() {
        int leningenVoor = dao.getLeningen().size();

        //Lid en Boek aanmaken + uitlenen
        Lid l = factory.maakLid("TestLid", "TestLid", 'M', null);
        Boek b = factory.maakBoek("Test lening", "NL", 1995);
        Lening lening = factory.leenBoekAanLid(l, b);
        dao.addLening(lening);

        //test of lening gelukt is:
        assertEquals(leningenVoor + 1, dao.getLeningen().size());

        //Lening verwijderen:
        dao.BoekTeruggebracht(l, b);

        //test of terugbrengen gelukt is:
        assertEquals(leningenVoor, dao.getLeningen().size());
    }
    
}
