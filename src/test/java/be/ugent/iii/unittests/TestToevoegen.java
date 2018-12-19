/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.unittests;

import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entities.*;
import be.ugent.iii.factory.BibliotheekFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.is;
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
public class TestToevoegen {

    BibliotheekFactory factory;
    BibliotheekDao dao;

    public TestToevoegen() {
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
    // <editor-fold defaultstate="collapsed" desc="Tests Bibliotheken">
    @Test
    public void BilbiotheekToevoegen() throws Exception {
        int aantalVoor = dao.getBibliotheken().size();
        Bibliotheek bib = factory.maakDeKrook();
        dao.addBibliotheek(bib);
        int id = bib.getID();

        List<Bibliotheek> lijst = dao.getBibliotheken();
        int aantalNa = dao.getBibliotheken().size();

        Bibliotheek bibOpId = dao.zoekBib(id);

        assertEquals("Aantal bibliotheken geïncrementeerd?", aantalVoor + 1, aantalNa);
        System.out.println(bib);
        System.out.println(lijst);
        assertTrue("Bibliotheek toegevoegd?", lijst.contains(bib));
        assertEquals("Bibliotheek gevonden met ID?", bib, bibOpId);
        dao.close();
    }

    @Test
    public void BibliothekenToevoegen() throws Exception {
        int aantalVoor = dao.getBibliotheken().size();
        String[] namen = {"Bibliotheek Dendermonde", "Boston Public Library", "Seattle Central Parlement", "Vatican Library", "National Library Of St. Marks"};
        List<Bibliotheek> lijstVoor = factory.maakBibliotheken(namen);

        //add bibliotheken to database
        dao.addBibliotheken(lijstVoor);

        List<Bibliotheek> lijstNa = dao.getBibliotheken();
        int aantalNa = lijstNa.size();

        assertEquals("Aantal bibliotheken verhoogd?", aantalVoor + lijstVoor.size(), aantalNa);
        assertEquals(lijstVoor, lijstNa);
        dao.close();
    }

    @Test
    public void BibliothekenToevoegenMetCollecties() {
        Bibliotheek bib = factory.maakDeKrookMetCollecties();
        dao.addBibliotheek(bib);
        assertTrue(true);
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tests Auteurs">
    @Test
    public void AuteurToevoegen() throws Exception {
        int aantalAuteursVoor = dao.getAuteurs().size();
        int aantalBoekenVoor = dao.getBoeken().size();
        Auteur a = factory.maakGeorgeOrwell();
        System.out.println(a.getBoeken());
        /*
        omegekeerde volgorde geeft een error, waarom?
         */
        //dao.addBoeken(a.getBoeken());
        dao.addAuteur(a);
        int aantalAuteursNa = dao.getAuteurs().size();
        int aantalBoekenNa = dao.getBoeken().size();

        assertEquals(aantalAuteursVoor + 1, aantalAuteursNa);
        assertEquals(aantalBoekenVoor + a.getBoeken().size(), aantalBoekenNa);
        dao.close();
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tests leningen">
    @Test
    public void LeningenToevoegen() {
        int leningenVoor = dao.getLeningen().size();

        // lid en boeken zijn nu in Transient state
        List<Boek> boeken = factory.maakOrwellBoeken();
        Lid lid = factory.maakLid();

        ArrayList<Lening> leningen = new ArrayList<>();
        for (Boek boek : boeken) {
            Lening l = factory.maakLening(lid, boek);
            leningen.add(l);
        }

        dao.addLeningen(leningen);

        int leningenNa = dao.getLeningen().size();

        assertEquals("Aantal leningen correct?", leningenVoor + boeken.size(), leningenNa);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test Query met parameter">
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
        assertTrue("ALLES ENGELS: OK",allesEngels);

        //Test nederlands
        List<Boek> resultNederlands = dao.getBoekenByTaal("NL");

        boolean allesNederlands = true;
        for (Boek b : resultNederlands) {
            if (!b.getTaal().equalsIgnoreCase("NL")) {
                allesNederlands = false;
                break;
            }
        }
        assertTrue("ALLES NEDERLANDS: OK",allesNederlands);
    }
    //</editor-fold>
}
