/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testPackage;

import be.ugent.iii.factory.BibliotheekFactory;
import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entities.Boek;
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
public class BoekUnitTest {
    
    BibliotheekFactory factory;
    BibliotheekDao dao;
    
    public BoekUnitTest() {
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
        //dao.close();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
      
    @Test
    public void testVoegBoekenToe() {
        List<Boek> boeken = factory.maakBoeken();
        dao.addBoeken(boeken);
        for (Boek boek : dao.getBoeken()) {
            System.out.println(boek);
        }
        assertEquals(dao.getBoeken().size(), 5);
    }
    
    @Test
    public void testZoekBoek() {
        List<Boek> boeken = factory.maakBoeken();
        dao.addBoeken(boeken);
        
        Boek boek = new Boek();
        boek.setID(1);
        Boek other = dao.getBoek(boek.getID());
        System.out.println(boek);
        System.out.println(other);
        assertEquals(boek, other);    
    }
}
