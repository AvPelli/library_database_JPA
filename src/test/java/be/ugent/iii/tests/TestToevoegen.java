/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.tests;

import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entiteiten.*;
import be.ugent.iii.factory.BibliotheekFactory;
import java.util.ArrayList;
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
    // Deze test gaat na of een bibliotheek correc aan de database kan worden toegevoegd
    @Test
    public void BilbiotheekToevoegen() throws Exception {
        int aantalVoor = dao.getBibliotheken().size();
        Bibliotheek bib = factory.maakDeKrook();
        dao.addBibliotheek(bib);
        int id = bib.getId();

        List<Bibliotheek> lijst = dao.getBibliotheken();
        int aantalNa = dao.getBibliotheken().size();

        Bibliotheek bibOpId = dao.zoekBib(id);

        assertEquals("Aantal bibliotheken geïncrementeerd?", aantalVoor + 1, aantalNa);
        assertTrue("Bibliotheek toegevoegd?", lijst.contains(bib));
        assertEquals("Bibliotheek gevonden met ID?", bib, bibOpId);
    }

    // Deze test gaat na of een collectie van bibliotheken op een correcte manier aan de database wordt toegevoegd
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
        assertTrue("Alle bibliotheken toegevoegd?", lijstNa.containsAll(lijstVoor));
    }

    // Deze test gaat na of alle collecties van een bibliotheek correct aan de database worden toegevoegd
    @Test
    public void BibliothekenToevoegenMetCollecties() {
        int aantalVoor = dao.getCollecties().size();
        Bibliotheek bib = factory.maakDeKrookMetCollecties();
        dao.addBibliotheek(bib);
        int aantalNa = dao.getCollecties().size();
        Set<Collectie> collecties = bib.getCollecties();
        assertEquals("Aantal collecties verhoogd?", aantalVoor + bib.getCollecties().size(), aantalNa);
        assertTrue("Alle collecties toegevoegd?", dao.getCollecties().containsAll(collecties));
    }
    
    // Deze test gaat na of een boek correct wordt toegevoegd aan een bestaande collectie in de database
    @Test
    public void BoekToevoegen() throws Exception {
        Bibliotheek bibliotheek = factory.maakBibliotheekDendermonde();
        Collectie collectie = factory.maakCollectie("Literatuur");
        collectie.setBibliotheek(bibliotheek);
        dao.addBibliotheek(bibliotheek);
        
        int aantalBoekenVoor = dao.getBoeken().size();
        List<Boek> boeken = factory.maakOrwellBoeken();
        
        assertFalse(dao.getBoeken().containsAll(boeken));
        
        for (Boek boek : boeken) {
            boek.setCollectie(collectie);
            dao.addBoek(boek);
        }
        
        assertTrue("alle boeken toegevoegd?", dao.getBoeken().containsAll(boeken));
    }

    // Deze test gaat na of een lid correct wordt toegevoegd aan een bestaande bibliotheek in de database
    @Test
    public void VoegLidToe() {
        Bibliotheek b = factory.maakDeKrookVolledig();
        dao.addBibliotheek(b);

        List<Lid> ledenVoor = dao.getLeden();
        Lid lid = factory.maakLid("TestLid", "TestLid", 'M', null);
        lid.setBibliotheek(b);
        dao.addLid(lid);

        assertEquals(ledenVoor.size() + 1, dao.getLeden().size());
        assertNotNull(dao.getLid("TestLid", "TestLid"));
    }

    // Deze test gaat na of een lid correct wordt toegevoegd aan een bestaande bibliotheek in de database
    @Test
    public void LeningToevoegen() {
        int leningenVoor = dao.getLeningen().size();

        // lid en boeken zijn nu in Transient state
        List<Boek> boeken = factory.maakOrwellBoeken();
        Lid l = factory.maakLid("TestLid", "TestLid", 'M', null);

        Lening lening = factory.leenBoekAanLid(l, boeken.get(0));
        dao.addLening(lening);
        int leningenNa = dao.getLeningen().size();

        assertEquals("Aantal leningen correct?", leningenVoor + 1, leningenNa);
    }

}
