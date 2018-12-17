/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.factory;

import be.ugent.iii.dao.BibliotheekDao;
import be.ugent.iii.entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author axeld
 */
public class BibliotheekFactory {
    
    private static final Random r = new Random();
    
    public void maakDatabaseAan(BibliotheekDao dao) {
        dao.addBibliotheek(maakBibliotheek());
        dao.addCollecties(maakCollecties());
        dao.addBoeken(maakBoeken());
    }
    
    public Bibliotheek maakBibliotheek() {
        Bibliotheek bib = new Bibliotheek();
        bib.setNaam("De Krook");
        Adres adres = new Adres();
        adres.setStraatNaam("Makebaplein");
        adres.setHuisNr(1);
        adres.setPostcode("9000");
        adres.setGemeente("Gent");
        adres.setLand("België");
        bib.setAdres(adres);
        return bib;
    }
    
    public List<Collectie> maakCollectiesMetBibliotheek(Bibliotheek bib) {
        List<Collectie> collecties = maakCollecties();
        for (Collectie collectie : collecties) {
            collectie.setBib(bib);
        }
        return collecties;
    }
    
    public Collectie maakCollectie(String klasse) {
        Collectie collectie = new Collectie();
        collectie.setKlasse(klasse);
        //collectie.setBib(maakBibliotheek());
        return collectie;
    }
    
    public List<Collectie> maakCollecties() {
        List<Collectie> collecties = new ArrayList<>();
        collecties.add(maakCollectie("Computerwetenschappen"));
        collecties.add(maakCollectie("Literatuur"));
        return collecties;
    }
    
    public Boek maakBoek(String titel, String taal, int jaarVanUitgave, int isbn) {
        Boek boek = new Boek();
        boek.setTitel(titel);
        boek.setTaal(taal);
        boek.setJaarVanUitgave(jaarVanUitgave);
        boek.setISBN(isbn);
        return boek;
    }
    
   public  List<Boek> maakBoeken() {
        List<Boek> boeken = new ArrayList<>(); 
        String[] titels = {"Programmeren voor dummies", "Computer Networking", "C# Design Patterns", "Learning Perl", "Animal Farm"};
        String[] talen = {"nl", "en", "en", "en", "en"};
        int[] jaren = {2018, 2013, 2017, 2007, 1945};
        for (int i = 0; i < titels.length; i++) {
            boeken.add(maakBoek(titels[i], talen[i], jaren[i], i + 1));
        }
        return boeken;
    }
   
   public Bibliotheek maakBibliotheekMetCollecties() {
       Bibliotheek bib = maakBibliotheek();
       List<Collectie> collecties = maakCollectiesMetBoeken();
       bib.setCollecties(collecties);
       for (Collectie c : collecties) {
           c.setBib(bib);
           for (Boek b : c.getBoeken()) {
               b.setCollectie(c);
           }
       }
       return bib;
   }
   
   public List<Collectie> maakCollectiesMetBoeken() {
      List<Collectie> collecties = new ArrayList<>();
      
      String[] titels = {"Computer Networking", "Learning Perl"};
      String[] talen = {"en", "en"};
      int[] jaren = {2013, 2007};
      Collectie collectie = maakCollectie("Computerwetenschappen");
      collectie.setBoeken(maakBoeken(titels, talen, jaren));
      collecties.add(collectie);
      
      titels = new String[]{"Animal Farm", "1984"};
      talen = new String[]{"en", "en"};
      jaren = new int[]{1945, 1948};
      collectie = maakCollectie("Literatuur");
      collectie.setBoeken(maakBoeken(titels, talen, jaren));
      collecties.add(collectie);
      
      return collecties;
   }
   
   public List<Boek> maakBoeken(String[] titels, String[] talen, int[] jaren) {
       List<Boek> boeken = new ArrayList<>();
       for (int i = 0; i < titels.length; i++) {
           boeken.add(maakBoek(titels[i], talen[i], jaren[i], 0 + r.nextInt(10000)));
       }
       return boeken;
   }
}
