/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.factory;

import be.ugent.iii.entiteiten.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author axeld
 */
public class BibliotheekFactory {
    
    //<editor-fold defaultstate="collapsed" desc="methodes om bibliotheken aan te maken">  
    // 1 biblioteek zonder associaties
    public Bibliotheek maakBibliotheek(String naam, Adres adres) {
        Bibliotheek bib = new Bibliotheek();
        bib.setNaam(naam);
        bib.setAdres(adres);
        return bib;
    }
    
    
    public Bibliotheek maakBibliotheekDendermonde() {
        Adres adres = new Adres();
        adres.setStraatNaam("Kerkstraat");
        adres.setHuisNr(111);
        adres.setGemeente("Dendermonde");
        adres.setPostcode("9200");
        adres.setLand("België");
        Bibliotheek bibliotheek = maakBibliotheek("Openbare Bibliotheek Dendermonde", adres);
        Collectie collectie = maakCollectie("Psychologie en filosofie");
        collectie.setBibliotheek(bibliotheek);
        Lid lid = maakLid("anoniem", "", 'x', null);
        bibliotheek.add(lid);
        Auteur auteur = maakHaidt();
        for (Boek boek : auteur.getBoeken()) {
            boek.setCollectie(collectie);
            leenBoekAanLid(lid, boek);
        }
        return  bibliotheek;
    }
    
    // een aantal bibliotheken zonder associaties
    public List<Bibliotheek> maakBibliotheken(String[] namen) {
        List<Bibliotheek> lijst = new ArrayList<>();
        for (String naam : namen) {
            lijst.add(maakBibliotheek(naam, null));
        }
        return lijst;
    }
    
    // bibliotheek De Krook zonder associaties
    public Bibliotheek maakDeKrook() {
        Adres adres = new Adres();
        adres.setStraatNaam("Makebaplein");
        adres.setHuisNr(1);
        adres.setPostcode("9000");
        adres.setGemeente("Gent");
        adres.setLand("België");
        return maakBibliotheek("De Krook", adres);
    }
    
    // bibliotheek De Krook met enkel collecties
    public Bibliotheek maakDeKrookMetCollecties() {
        Bibliotheek bibliotheek = maakDeKrook();
        bibliotheek.add(maakCollectie("Informatica"));
        bibliotheek.add(maakCollectie("Wetenschappen"));
        bibliotheek.add(maakCollectie("Literatuur"));
        bibliotheek.add(maakCollectie("Geschiedenis"));
        bibliotheek.add(maakCollectie("Sociale Wetenschappen"));
        return bibliotheek;
    }
    
    // bibliotheek De Krook met collecties en boeken
    public Bibliotheek maakDeKrookMetCollectiesEnBoeken() {
        Bibliotheek bibliotheek = maakDeKrook();
        bibliotheek.add(maakCollectieMetBoeken("Informatica", maakInformaticaBoeken()));
        bibliotheek.add(maakCollectieMetBoeken("Wetenschappen", maakWetenschappenBoeken()));
        bibliotheek.add(maakCollectieMetBoeken("Literatuur", maakLiteratuurBoeken()));
        bibliotheek.add(maakCollectieMetBoeken("Sociale Wetenschappen", null));
        bibliotheek.add(maakCollectieMetBoeken("Geschiedenis", maakGeschiedenisBoeken()));
        return bibliotheek;
    }
    
    // bibliotheek De Krook met collecties, boeken en auteurs
    public Bibliotheek maakDeKrookMetCollectiesEnBoekenEnAuteurs() {
        Bibliotheek bibliotheek = maakDeKrook();
        bibliotheek.add(maakCollectieMetBoeken("Informatica", maakInformaticaBoekenMetAuteurs()));
        bibliotheek.add(maakCollectieMetBoeken("Wetenschappen", maakWetenschappenBoekenMetAuteurs()));
        bibliotheek.add(maakCollectieMetBoeken("Literatuur", maakLiteratuurBoekenMetAuteurs()));
        bibliotheek.add(maakCollectieMetBoeken("Sociale Wetenschappen", null));
        bibliotheek.add(maakCollectieMetBoeken("Geschiedenis", maakGeschiedenisBoekenMetAuteurs()));
        return bibliotheek;
    }
    
    // bibliotheek De Krook met collecties, boeken, auteurs en leden
    public Bibliotheek maakDeKrookMetLeden() {
        Bibliotheek bibliotheek = maakDeKrookMetCollectiesEnBoekenEnAuteurs();
        List<Lid> leden = maakLedenDeKrook();
        for (Lid lid : leden) {
            bibliotheek.add(lid);
        }
        return bibliotheek;
    }
    
    // bibliotheek De Krook met collecties, boeken, auteurs, leden en leningen
    public Bibliotheek maakDeKrookVolledig() {
        Bibliotheek bibliotheek = maakDeKrookMetCollectiesEnBoekenEnAuteurs();
        Adres a1 = maakAdres("Voskenslaan", 270, "Gent", "9000", "België");
        Adres a2 = maakAdres("Generic Street", 0, "Generic town", "0000", "Imaginationland");
        Lid[] leden = {maakLid("Axel", "De Decker", 'M', a1), maakLid("Arthur", "Van Pellicom", 'M', a1), maakLid("John", "Doe", 'M', a2), maakLid("Jane", "Doe", 'V', a2)};
        for (Lid lid : leden) {
            bibliotheek.add(lid);
        }
        for (Boek boek : bibliotheek.getBoeken()) {
            int ontleend = new Random().nextInt(2);
            int i = new Random().nextInt(leden.length);
            if (ontleend == 1) {
                leenBoekAanLid(leden[i], boek);
            }
        }
        return bibliotheek;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="methodes om adressen aan te maken">
    // 1 value-object adres
    public Adres maakAdres(String straatNaam, int huisNr, String gemeente, String postcode, String land) {
        Adres adres = new Adres();
        adres.setStraatNaam(straatNaam);
        adres.setHuisNr(huisNr);
        adres.setGemeente(gemeente);
        adres.setPostcode(postcode);
        adres.setLand(land);
        return adres;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="methodes om collecties aan te maken">
    // 1 collectie zonder associaties
    public Collectie maakCollectie(String klasse) {
        Collectie collectie = new Collectie();
        collectie.setKlasse(klasse);
        return collectie;
    }
    
    // een aantal collecties zonder associaties
    public List<Collectie> maakCollecties(String[] klasses) {
        List<Collectie> collecties = new ArrayList<>();
        for (String klasse : klasses) {
            collecties.add(maakCollectie(klasse));
        }
        return collecties;
    }
    
    // 1 collectie met boeken
    public Collectie maakCollectieMetBoeken(String klasse, List<Boek> boeken) {
        Collectie collectie = maakCollectie(klasse);
        if (boeken !=  null) {
            for (Boek boek : boeken) {
            collectie.add(boek);
            }
        }
        return collectie;
    }
    
    // 1 collectie met boeken en auteurs
    public List<Collectie> maakCollectiesMetBoekenEnAuteurs() {
        List<Collectie> collecties = new ArrayList<>();
        collecties.add(maakCollectieMetBoeken("Geschiedenis", maakGeschiedenisBoekenMetAuteurs()));
        collecties.add(maakCollectieMetBoeken("Wetenschappen", maakWetenschappenBoekenMetAuteurs()));
        return collecties;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="methodes om boeken aan te maken">
    // 1 boek zonder associaties
    public Boek maakBoek(String titel, String taal, int jaar) {
        Boek boek = new Boek();
        boek.setTitel(titel);
        boek.setTaal(taal);
        boek.setJaarVanUitgave(jaar);
        boek.setISBN((new Random()).nextInt(10000));
        return boek;
    }
    
    // een aantal boeken zonder associaties
    public List<Boek> maakBoeken(String[] titels, String[] talen, int[] jaren) {
        List<Boek> boeken = new ArrayList<>();
        for (int i = 0; i < titels.length; i++) {
            boeken.add(maakBoek(titels[i], talen[i], jaren[i]));
        }
        return boeken;
    }
    
    // een aantal boeken van George Orwell zonder associaties
    public List<Boek> maakOrwellBoeken() {
        String[] titels = {"Animal Farm", "1984"};
        String[] talen = {"EN", "EN"};
        int[] jaren = {1945, 1948};
        return maakBoeken(titels, talen, jaren);
    }
    
    // een aantal informatica boeken zonder associaties
    public List<Boek> maakInformaticaBoeken() {
        String[] titels = {"Modern Operating Systems", "Structured Computer Organization", "Learning Perl", "Intermediate Perl"};
        String[] talen = {"EN", "EN", "EN", "EN"};
        int[] jaren = {2014, 2012, 2016, 2006};
        return maakBoeken(titels, talen, jaren);
    }
    
    // een aantal geschiedenis boeken zonder associaties
    public List<Boek> maakGeschiedenisBoeken() {
        String[] titels = {"Sapiens", "Congo", "Guns, Germs & Steel", "The Rise And Fall Of The Dinosaurs"};
        String[] talen = {"EN", "NL", "EN", "EN"};
        int[] jaren = {2011, 2010, 1997, 2018};
        return maakBoeken(titels, talen, jaren);
    }

    // een aantal wetenschappen boeken zonder associaties
    public List<Boek> maakWetenschappenBoeken() {
        String[] titels = {"A Brief History Of Time", "The Selfish Gene"};
        String[] talen = {"EN", "EN"};
        int[] jaren = {1988, 1989};
        return maakBoeken(titels, talen, jaren);
    }

    // een aantal literatuur boeken zonder associaties
    public List<Boek> maakLiteratuurBoeken() {
        String[] titels = {"The Handmaid's Tale", "Lord Of The Flies", "Papillon"};
        String[] talen = {"EN", "EN", "FR"};
        int[] jaren = {1954, 1985, 1969};
        List<Boek> boeken = maakBoeken(titels, talen, jaren);
        boeken.addAll(maakOrwellBoeken());
        return boeken;
    }
    
    // een aantal informatica boeken met auteurs
    public List<Boek> maakInformaticaBoekenMetAuteurs() {
        List<Boek> boeken = new ArrayList<>();
        Boek b1 = maakBoek("Modern Operating Systems", "EN", 2014);
        Boek b2 = maakBoek("Structured Computer Organization", "EN", 2012);
        Auteur a1 = maakAuteur("Andrew", "Tanenbaum", 'M');
        Auteur a2 = maakAuteur("Herbert", "Bos", 'M');
        Auteur a3 = maakAuteur("Todd", "Austin", 'M');
        b1.add(a1); b1.add(a2);
        b2.add(a1); b2.add(a3);
        boeken.add(b1); boeken.add(b2);
        b1 = maakBoek("Learning Perl", "EN", 2016);
        b2 = maakBoek("Intermediate Perl", "EN", 2006);
        a1 = maakAuteur("Randal", "Schwartz", 'M');
        a2 = maakAuteur("Brian", "Foy", 'M');
        b1.add(a1); b1.add(a2);
        b2.add(a1); b2.add(a2);
        boeken.add(b1); boeken.add(b2);
        return boeken;
    }
    
    // een aantal wetenschappen boeken met auteurs
    public List<Boek> maakWetenschappenBoekenMetAuteurs() {
        String[] voornamen = {"Stephen", "Richard"};
        String[] achternamen = {"Hawking", "Dawkins"};
        char[] geslacht = {'M', 'M'};
        List<Auteur> auteurs = maakAuteurs(voornamen, achternamen, geslacht);
        List<Boek> boeken = maakWetenschappenBoeken();
        int i = 0;
        for (Boek boek : boeken) {
            boek.add(auteurs.get(i));
            i++;
        }
        return boeken;
    }
    
    // een aantal literatuur boeken met auteurs
    public List<Boek> maakLiteratuurBoekenMetAuteurs() {
        String[] voornamen = {"Margaret", "William", "Henri"};
        String[] achternamen = {"Atwood", "Goulding", "Charrière"};
        char[] geslacht = {'V', 'M', 'M'};
        List<Auteur> auteurs = maakAuteurs(voornamen, achternamen, geslacht);
        Auteur orwell = maakGeorgeOrwell();
        auteurs.add(orwell); auteurs.add(orwell);
        List<Boek> boeken = maakLiteratuurBoeken();
        int i = 0;
        for (Boek boek : boeken) {
            boek.add(auteurs.get(i));
            i++;
        }
        return boeken;
    }
    
    
    // een aantal geschiedenis boeken met auteurs
    public List<Boek> maakGeschiedenisBoekenMetAuteurs() {
        String[] voornamen = {"Yuval", "David", "Jared", "Steve"};
        String[] achternamen = {"Harari", "Van Reybrouck", "Diamond", "Brusatte"};
        char[] geslacht = {'M', 'M', 'M', 'M'};
        List<Auteur> auteurs = maakAuteurs(voornamen, achternamen, geslacht);
        List<Boek> boeken = maakGeschiedenisBoeken();
        int i = 0;
        for (Boek boek : boeken) {
            boek.add(auteurs.get(i));
            i++;
        }
        return boeken;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="methodes om auteurs aan te maken">
    // 1 auteur zonder associaties
    public Auteur maakAuteur(String voornaam, String achternaam, char geslacht) {
        Auteur auteur = new Auteur();
        auteur.setVoorNaam(voornaam);
        auteur.setAchterNaam(achternaam);
        auteur.setGeslacht(geslacht);
        return auteur;
    }
    
    // een aantal auteurs zonder associaties
    public List<Auteur> maakAuteurs(String[] voornamen, String[] achternamen, char[] geslacht) {
        List<Auteur> auteurs = new ArrayList<>();
        for (int i = 0; i < voornamen.length; i++) {
            auteurs.add(maakAuteur(voornamen[i], achternamen[i], geslacht[i]));
        }
        return auteurs;
    }
    
    // George Orwell zonder associaties
    public Auteur maakGeorgeOrwell() {
        Auteur auteur = maakAuteur("George", "Orwell", 'M');
        return auteur;
    }
    
    public Auteur maakHaidt() {
        Auteur auteur = maakAuteur("Jonathan", "Haidt", 'M');
        Boek boek1 = maakBoek("The Righteous Mind", "EN", 2012);
        Boek boek2 = maakBoek("The Happiness Hypothesis", "EN", 2006);
        auteur.add(boek1);
        auteur.add(boek2);
        return auteur;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="methodes om leden aan te maken">
    // 1 lid zonder associaties
    public Lid maakLid(String voorNaam, String achterNaam, char geslacht, Adres adres) {
        Lid lid = new Lid();
        lid.setVoorNaam(voorNaam);
        lid.setAchterNaam(achterNaam);
        lid.setGeslacht(geslacht);
        lid.setAdres(adres);
        return lid;
    }
    
    // een aantal leden zonder associaties
    public List<Lid> maakLeden(String[] voorNamen, String[] achterNamen, char[] geslacht) {
        List<Lid> leden = new ArrayList<>();
        for (int i = 0; i< voorNamen.length; i++) {
            leden.add(maakLid(voorNamen[i], achterNamen[i], geslacht[i], null));
        }
        return leden;
    }
    
    // een aantal leden van de Krook zonder associaties
    public List<Lid> maakLedenDeKrook() {
        String[] voorNamen = {"Axel", "Arthur", "John", "Jane"};
        String[] achterNamen = {"De Decker", "Van Pellicom", "Doe", "Doe"};
        char[] geslacht = {'M', 'M', 'M', 'V'};
        List<Lid> leden = maakLeden(voorNamen, achterNamen, geslacht);
        return leden;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="methodes om leningen aan te maken">
    // 1 lening met lid en boek (noodzakelijk)
    public Lening leenBoekAanLid(Lid lid, Boek boek) {
        Lening lening = new Lening();
        lening.setCheckOutDatum(maakRandomDatum());
        lening.setLid(lid);
        lening.setBoek(boek);
        return lening;
    }
    
    // hulpmethode om random datum te genereren
    private Date maakRandomDatum() {
        return new Date(118, new Random().nextInt(12), new Random().nextInt(29));
    }
    //</editor-fold>
    
}
