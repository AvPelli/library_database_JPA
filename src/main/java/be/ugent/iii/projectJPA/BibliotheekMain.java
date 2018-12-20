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
        List<Boek> resultaat1 = dao.zoekBoekenOpTitel("Congo");
        List<Boek> resultaat2 = dao.zoekBoekenOpTaal("FR");
        List<Boek> resultaat3 = dao.zoekBoekenOpTaal("NL");
        List<Boek> resultaat4 = dao.zoekBoekenOpTaal("EN");
        List<Boek> resultaat5 = dao.zoekBoekenOpTaal("DE");
        /*
        System.out.println(resultaat1);
        System.out.println(resultaat2);
        System.out.println(resultaat3);
        System.out.println(resultaat4);
        System.out.println(resultaat5);
        */
        toonBibliotheek(bibliotheek);
        bibliotheek = factory.maakBibliotheek("Stadsbibliotheek Dendermonde", new Adres());
        Collectie collectie = factory.maakCollectie("Filosofie en psychologie");
        collectie.setBibliotheek(bibliotheek);
        dao.addCollectie(collectie);
        toonBibliotheek(bibliotheek);
        dao.close();
    }
    
    private static void toonBibliotheek(Bibliotheek bibliotheek) {
        System.out.println("-----BIBLIOTHEEK-----");
        System.out.println(bibliotheek);
        System.out.println("");
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
        System.out.println("");
        System.out.println("-----EINDE BIBLIOTHEEK-----");
    }
    
}
