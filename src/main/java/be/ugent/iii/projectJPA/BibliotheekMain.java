/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.projectJPA;

import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entiteiten.*;
import be.ugent.iii.factory.BibliotheekFactory;
import java.util.List;

/**
 *
 * @author axeld
 */
public class BibliotheekMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BibliotheekFactory factory = new BibliotheekFactory();
        BibliotheekDao dao = new BibliotheekDao();
        Bibliotheek bibliotheek = factory.maakDeKrookVolledig();
        dao.addBibliotheek(bibliotheek);
        /*
        Bibliotheek bibliotheek2 = factory.maakBibliotheek("Stadsbibliotheek Dendermonde", null);
        Collectie collectie = factory.maakCollectie("Sociale Wetenschappen");
        collectie.setBibliotheek(bibliotheek2);
        Boek boek = factory.maakTheRighteousMind();
        boek.setCollectie(collectie);
        dao.addBoek(boek);
        int id = boek.getId();
        dao.VerwijderBoek(id);
        //dao.addCollectie(collectie);
        //System.out.println(boek.getCollectie().getBibliotheek().getId());
        */
        toonBibliotheek(bibliotheek);
        //dao.VerwijderLid("Axel", "De Decker");
        /*
        dao.VerwijderBoek(1);
        dao.VerwijderBoek(2);
        dao.VerwijderBoek(3);
        dao.VerwijderBoek(4);
        dao.VerwijderBoek(5);
        */
        Bibliotheek bibliotheek1 = factory.maakBibliotheekDendermonde();
        dao.addBibliotheek(bibliotheek1);
        Lid lid = dao.getLid(21);
        Boek boek = dao.getBoek(17);
        System.out.println(lid);
        System.out.println(boek);
        dao.BoekTeruggebracht(lid, boek);
        System.out.println(boek);
        System.out.println(dao.getBoek(17));
        dao.close();
        toonBibliotheek(bibliotheek);
        toonBibliotheek(bibliotheek1);
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
        System.out.println("");
        System.out.println("-----Leden-----");
        for (Lid lid : bibliotheek.getLeden()) {
            System.out.println(lid);
            for (Lening lening : lid.getLeningen()) {
                System.out.println("\t" + lening);
            }
        }
        System.out.println("-----Einde leden-----");
        System.out.println("-----EINDE BIBLIOTHEEK-----");
    }
    
}
