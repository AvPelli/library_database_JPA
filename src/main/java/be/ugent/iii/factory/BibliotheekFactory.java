/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.factory;

import be.ugent.iii.entities.Boek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author axeld
 */
public class BibliotheekFactory {
    public Boek maakBoek(String titel, String taal, LocalDate uitgaveDatum, int isbn) {
        Boek boek = new Boek();
        boek.setTitel(titel);
        boek.setTaal(taal);
        boek.setUitgaveDatum(uitgaveDatum);
        boek.setISBN(isbn);
        return boek;
    }
    
   public  List<Boek> maakBoeken() {
        List<Boek> boeken = new ArrayList<>();
        LocalDate date = LocalDate.of(2018, 12, 16);
        boeken.add(maakBoek("Programmeren voor dummies", "nl", date, 1));
        boeken.add(maakBoek("Computer Networking", "en", date, 2));
        boeken.add(maakBoek("C# Design Patterns", "en", date, 3));
        boeken.add(maakBoek("Learning Perl", "en", date, 4));
        boeken.add(maakBoek("Animal Farm", "en", date, 5));
        return boeken;
    }
}
