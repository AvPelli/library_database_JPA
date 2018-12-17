/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.unittests;

import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entities.*;
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
    public void tearDown() throws Exception {
        dao.close();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void BilbiotheekToevoegen() {
        int aantalVoor = dao.getBibliotheken().size();
        Bibliotheek bib = factory.maakBibliotheek();
        dao.addBibliotheek(bib);
        int id = bib.getID();
        
        List<Bibliotheek> lijst = dao.getBibliotheken();
        int aantalNa = dao.getBibliotheken().size();
        
        Bibliotheek bibOpId = dao.zoekBib(id);
        
        assertEquals("Aantal bibliotheken geïncrementeerd?", aantalVoor + 1, aantalNa);
        assertTrue("Bibliotheek toegevoegd?", lijst.contains(bib));
        assertEquals("Bibliotheek gevonden met ID?", bib, bibOpId);
    }
}
