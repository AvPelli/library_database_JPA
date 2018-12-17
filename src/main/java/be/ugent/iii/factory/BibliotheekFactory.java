/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.factory;

import be.ugent.iii.entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author axeld
 */
public class BibliotheekFactory { 
    
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
    
    public Bibliotheek maakBibliotheekMetCollecties() {
        Bibliotheek bib = maakBibliotheek();
        List<Collectie> collecties = maakCollectiesMetBoeken();
        bib.setCollecties(collecties);
        for (Collectie c : collecties) {
            c.setBib(bib);
        }
        return bib;
    }
    
    public List<Bibliotheek> maakBibliotheken(String[] namen) {
        List<Bibliotheek> lijst = new ArrayList<>();
        for (String naam : namen) {
            Bibliotheek bib = new Bibliotheek();
            bib.setNaam(naam);
            lijst.add(bib);
        }
        return lijst;
    }
    
    public Collectie maakCollectie(String klasse) {
        Collectie collectie = new Collectie();
        collectie.setKlasse(klasse);
        return collectie;
    }
    
    public Collectie maakInformaticaCollectie() {
        return maakCollectieMetBoeken("Computerwetenschappen", maakInformaticaBoeken());
    }
    
    public Collectie maakLiteratuurCollectie() {
        return maakCollectieMetBoeken("Literatuur", maakLiteratuurBoeken());
    }
    
    public Collectie maakCollectieMetBoeken(String klasse, List<Boek> boeken) {
        Collectie collectie = maakCollectie(klasse);
        collectie.setBoeken(boeken);
        for (Boek boek : boeken) {
            boek.setCollectie(collectie);
        }
        return collectie;
    }
    
    public List<Collectie> maakCollectiesMetBoeken() {
        List<Collectie> collecties = new ArrayList<>();
        collecties.add(maakInformaticaCollectie());
        collecties.add(maakLiteratuurCollectie());
        collecties.add(maakCollectie("Social sciences"));
        return collecties;
    }
    
    public Boek maakBoek(String titel, String taal, int jaar) {
        Boek boek = new Boek();
        boek.setTitel(titel);
        boek.setTaal(taal);
        boek.setJaarVanUitgave(jaar);
        boek.setISBN((new Random()).nextInt(1000));
        return boek;
    }
    
    public List<Boek> maakBoeken(String[] titels, String[] talen, int[] jaren) {
        List<Boek> boeken = new ArrayList<>();
        for (int i = 0; i < titels.length; i++) {
            boeken.add(maakBoek(titels[i], talen[i], jaren[i]));
        }
        return boeken;
    }
    
    public List<Boek> maakInformaticaBoeken() {
        String[] titels = {"Computer Networking", "Learning Perl"};
        String[] talen = {"en", "en"};
        int[] jaren = {2013, 2017};
        return maakBoeken(titels, talen, jaren);
    }
    
    public List<Boek> maakLiteratuurBoeken() {
        String[] titels = {"Animal Farm", "1984"};
        String[] talen = {"en", "en"};
        int[] jaren = {1945, 1948};
        return maakBoeken(titels, talen, jaren);
    }
}
