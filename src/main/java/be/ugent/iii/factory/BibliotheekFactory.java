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

    //<editor-fold defaultstate="collapsed" desc="methodes om bibliotheken te maken">
    public Bibliotheek maaBibliotheek(String naam, Adres adres) {
        Bibliotheek bib = new Bibliotheek();
        bib.setNaam(naam);
        bib.setAdres(adres);
        return bib;
    }

    public List<Bibliotheek> maakBibliotheken(String[] namen) {
        List<Bibliotheek> lijst = new ArrayList<>();
        for (String naam : namen) {
            lijst.add(maaBibliotheek(naam, null));
        }
        return lijst;
    }

    public Bibliotheek maakDeKrook() {
        Adres adres = new Adres();
        adres.setStraatNaam("Makebaplein");
        adres.setHuisNr(1);
        adres.setPostcode("9000");
        adres.setGemeente("Gent");
        adres.setLand("België");
        return maaBibliotheek("De Krook", adres);
    }

    public Bibliotheek maakDeKrookMetCollecties() {
        Bibliotheek bib = maakDeKrook();
        List<Collectie> collecties = maakCollectiesMetBoeken();
        bib.addAllCollecties(collecties);
        return bib;
    }

    public Bibliotheek maakDeKrookVolledig() {
        Bibliotheek bib = maakDeKrook();
        List<Collectie> collecties = new ArrayList<>();
        collecties.add(maakLiteratuurCollectieMetBoeken());
        collecties.add(maakInformaticaCollectieMetBoeken());
        collecties.add(maakGeschiedenisCollectieMetBoeken());
        collecties.add(maakWetenschappenCollectieMetBoeken());
        collecties.add(maakCollectie("Sociale Wetenschappen"));
        bib.addAllCollecties(collecties);
        return bib;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="methodes om collecties te maken">
    public Collectie maakCollectie(String klasse) {
        Collectie collectie = new Collectie();
        collectie.setKlasse(klasse);
        return collectie;
    }

    public List<Collectie> maakCollecties(String[] klasses) {
        List<Collectie> collecties = new ArrayList<>();
        for (String klasse : klasses) {
            collecties.add(maakCollectie(klasse));
        }
        return collecties;
    }

    public Collectie maakCollectieMetBoeken(String klasse, List<Boek> boeken) {
        Collectie collectie = maakCollectie(klasse);
        collectie.addAllBoeken(boeken);
        return collectie;
    }

    public List<Collectie> maakCollectiesMetBoeken() {
        List<Collectie> collecties = new ArrayList<>();
        collecties.add(maakInformaticaCollectie());
        collecties.add(maakLiteratuurCollectie());
        collecties.add(maakCollectie("Social sciences"));
        return collecties;
    }

    public Collectie maakInformaticaCollectie() {
        return maakCollectieMetBoeken("Computerwetenschappen", maakInformaticaBoeken());
    }

    public Collectie maakLiteratuurCollectie() {
        return maakCollectieMetBoeken("Literatuur", maakOrwellBoeken());
    }

    public Collectie maakInformaticaCollectieMetBoeken() {
        return maakCollectieMetBoeken("Informatica", maakInformaticaBoekenMetAuteurs());
    }

    public Collectie maakLiteratuurCollectieMetBoeken() {
        return maakCollectieMetBoeken("Literatuur", maakLiteratuurBoekenMetAuteurs());
    }

    public Collectie maakWetenschappenCollectieMetBoeken() {
        return maakCollectieMetBoeken("Wetenschappen", maakWetenschappenBoekMetAuteurs());
    }

    public Collectie maakGeschiedenisCollectieMetBoeken() {
        return maakCollectieMetBoeken("Geschiedenis", maakGeschiedenisBoekenMetAuteurs());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="methodes om boeken aan te maken">
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

    public List<Boek> maakOrwellBoeken() {
        String[] titels = {"Animal Farm", "1984"};
        String[] talen = {"EN", "EN"};
        int[] jaren = {1945, 1948};
        return maakBoeken(titels, talen, jaren);
    }

    public List<Boek> maakInformaticaBoeken() {
        String[] titels = {"Computer Networking", "Learning Perl"};
        String[] talen = {"en", "en"};
        int[] jaren = {2013, 2017};
        return maakBoeken(titels, talen, jaren);
    }

    public List<Boek> maakGeschiedenisBoeken() {
        String[] titels = {"Sapiens", "Congo", "Guns, Germs & Steel", "The Rise And Fall Of The Dinosaurs"};
        String[] talen = {"EN", "NL", "EN", "EN"};
        int[] jaren = {2011, 2010, 1997, 2018};
        return maakBoeken(titels, talen, jaren);
    }

    public List<Boek> maakWetenschappenBoeken() {
        String[] titels = {"A Brief History Of Time", "The Selfish Gene"};
        String[] talen = {"EN", "EN"};
        int[] jaren = {1988, 1989};
        return maakBoeken(titels, talen, jaren);
    }

    public List<Boek> maakLiterauurBoeken() {
        String[] titels = {"The Handmaid's Tale", "Lord Of The Flies"};
        String[] talen = {"EN", "EN"};
        int[] jaren = {1954, 1985};
        List<Boek> boeken = maakBoeken(titels, talen, jaren);
        boeken.addAll(maakOrwellBoeken());
        return boeken;
    }

    public List<Boek> maakGeschiedenisBoekenMetAuteurs() {
        String[] voornamen = {"Yuval", "David", "Jared", "Steve"};
        String[] achternamen = {"Harari", "Van Reybrouck", "Diamond", "Brusatte"};
        char[] geslacht = {'M', 'M', 'M', 'M'};
        List<Auteur> auteurs = maakAuteurs(voornamen, achternamen, geslacht);
        List<Boek> boeken = maakGeschiedenisBoeken();
        int i = 0;
        for (Auteur auteur : auteurs) {
            auteur.addBoek(boeken.get(i));
            i++;
        }
        return boeken;
    }

    public List<Boek> maakWetenschappenBoekMetAuteurs() {
        String[] voornamen = {"Stephen", "Richard"};
        String[] achternamen = {"Hawling", "Dawkins"};
        char[] geslacht = {'M', 'M'};
        List<Auteur> auteurs = maakAuteurs(voornamen, achternamen, geslacht);
        List<Boek> boeken = maakWetenschappenBoeken();
        int i = 0;
        for (Auteur auteur : auteurs) {
            auteur.addBoek(boeken.get(i));
            i++;
        }
        return boeken;
    }

    public List<Boek> maakLiteratuurBoekenMetAuteurs() {
        String[] voornamen = {"Margaret", "William", "George"};
        String[] achternamen = {"Atwood", "Goulding", "Orwell"};
        char[] geslacht = {'V', 'M', 'M'};
        List<Auteur> auteurs = maakAuteurs(voornamen, achternamen, geslacht);
        List<Boek> boeken = maakLiterauurBoeken();
        int i = 0;
        for (Auteur auteur : auteurs) {
            auteur.addBoek(boeken.get(i));
            i++;
        }
        auteurs.get(2).addBoek(boeken.get(3));
        return boeken;
    }

    public List<Boek> maakInformaticaBoekenMetAuteurs() {
        List<Boek> boeken = new ArrayList<>();
        Boek boek1 = maakBoek("Modern Operating Systems", "EN", 2014);
        Boek boek2 = maakBoek("Structured Computer Organization", "EN", 2012);
        Auteur a1 = maakAuteur("Andrew", "Tanenbaum", 'M');
        Auteur a2 = maakAuteur("Herbert", "Bos", 'M');
        Auteur a3 = maakAuteur("Todd", "Austin", 'M');
        boek1.addAuteur(a1);
        boek1.addAuteur(a2);
        boek2.addAuteur(a1);
        boek2.addAuteur(a3);
        boeken.add(boek1);
        boeken.add(boek2);
        boek1 = maakBoek("Learning Perl", "EN", 2016);
        boek2 = maakBoek("Intermediate Perl", "EN", 2006);
        a1 = maakAuteur("Randal", "Schwartz", 'M');
        a2 = maakAuteur("Brian", "Foy", 'M');
        boek1.addAuteur(a1);
        boek1.addAuteur(a2);
        boek2.addAuteur(a1);
        boek2.addAuteur(a2);
        boeken.add(boek1);
        boeken.add(boek2);
        return boeken;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="methodes om auteurs aan te maken">
    public Auteur maakAuteur(String voornaam, String achternaam, char geslacht) {
        Auteur auteur = new Auteur();
        auteur.setVoorNaam(voornaam);
        auteur.setAchterNaam(achternaam);
        auteur.setGeslacht(geslacht);
        return auteur;
    }

    public List<Auteur> maakAuteurs(String[] voornamen, String[] achternamen, char[] geslacht) {
        List<Auteur> auteurs = new ArrayList<>();
        for (int i = 0; i < voornamen.length; i++) {
            auteurs.add(maakAuteur(voornamen[i], achternamen[i], geslacht[i]));
        }
        return auteurs;
    }

    public Auteur maakGeorgeOrwell() {
        Auteur auteur = maakAuteur("George", "Orwell", 'M');
        auteur.addAllBoeken(maakOrwellBoeken());
        //auteur.getBoeken().addAll(maakLiteratuurBoeken());
        return auteur;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="methodes om leningen aan te maken">
    public Lening maakLening(Lid l, Boek b) {
        Lening lening = new Lening();
        lening.setBoek(b);
        lening.setLid(l);
        l.addLening(lening);
        return lening;
    }
    //</editor-fold>
    
    public Lid maakLid(){
        Lid lid = new Lid();
        lid.setVoorNaam("voornaam");
        lid.setAchterNaam("achternaam");
        return lid;
    }
}
