/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.projectJPA;

import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entities.*;
import be.ugent.iii.factory.BibliotheekFactory;

/**
 *
 * @author axeld
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        BibliotheekFactory factory = new BibliotheekFactory();
        BibliotheekDao dao = new BibliotheekDao();
        Bibliotheek bib = factory.maakBibliotheekMetCollecties();
        dao.addBibliotheek(bib);
        System.out.println(bib);
        for (Collectie c : bib.getCollecties()) {
            System.out.println(c);
            for (Boek b : c.getBoeken()) {
                System.out.println(b);
            }
        }
        Bibliotheek other = dao.getBibliotheek("De Krook");
        System.out.println(other);
        for (Collectie c : other.getCollecties()) {
            System.out.println(c);
        }
        dao.close();
    }
    
}
