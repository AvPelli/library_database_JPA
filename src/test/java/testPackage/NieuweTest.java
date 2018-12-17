/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testPackage;

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
public class NieuweTest {
    
    BibliotheekFactory factory;
    BibliotheekDao dao;
    
    public NieuweTest() {
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
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testAddBibliotheekMetCollecties() {
        Bibliotheek bib = factory.maakBibliotheekMetCollecties();
        List<Collectie> collecties = bib.getCollecties();
        
        dao.addBibliotheek(bib);
        dao.addCollecties(collecties);
        
        assertEquals(dao.getBibliotheken().size(), 1);
    }
}
