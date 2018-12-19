/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.projectJPA;

import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entities.*;
import be.ugent.iii.factory.BibliotheekFactory;
import java.util.ArrayList;
import java.util.List;

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
     /*
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
        //List<Boek> boeken = factory.maakLiteratuurBoeken();
        //dao.addAuteur(boeken);
        
        dao.close();
        */
     /*
        Boek b1  = new Boek();
        Boek b2 = new Boek();
        System.out.println(b1);
        System.out.println(b2);
        
        List<Auteur> auteurs = factory.maakGeschiedenisAuteurs();
        auteurs.addAll(factory.maakWetenschappenAuteurs());
        auteurs.addAll(factory.maakLiteratuurAuteurs());
        System.out.println(auteurs);
        dao.addAuteurs(auteurs);

        List<Boek> boeken = factory.maakGeschiedenisBoekenMetAuteurs();
        boeken.addAll(factory.maakInformaticaBoekenMetAuteurs());
        boeken.addAll(factory.maakLiteratuurBoekenMetAuteurs());
        boeken.addAll(factory.maakWetenschappenBoekMetAuteurs());
        for (Boek b : boeken) {
            System.out.println(b);
            System.out.println(b.getAuteurs());
        }
        */
        Bibliotheek bib = factory.maakDeKrookVolledig();
        System.out.println(bib);
        for (Collectie c : bib.getCollecties()) {
            System.out.println("\t" + c);
            for (Boek b : c.getBoeken()) {
                System.out.println("\t\t" + b);
                for (Auteur a : b.getAuteurs()) {
                    System.out.println("\t\t\t" + a);
                }
            }
        }
        dao.addBibliotheek(bib);
        dao.close();
    }
    
}
