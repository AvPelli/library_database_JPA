/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author axeld
 */
@Entity
@Table(name = "AUTEURS")
public class Auteur extends Persoon {
    
    /*
    @ManyToMany
    @JoinTable(name = "BOEKEN_PER_AUTEUR",
            joinColumns = @JoinColumn(name = "AUTEUR"),
            inverseJoinColumns = @JoinColumn(name = "BOEK"))
    */
    
    
    // n-n bidirectionele relatie tussen auteurs en boeken
    @ManyToMany
    @JoinTable(name = "BOEKEN_AUTEURS",
            joinColumns = @JoinColumn(name = "AUTEUR_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOEK_ID"))
    private final List<Boek> boeken = new ArrayList<>();

    /*
    public boolean add(Boek boek) {
        return boeken.add(boek);
    }
    */

    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    public List<Boek> getBoeken() {
        return boeken;
    }

    public void addBoek(Boek boek) {
        // oneindige lus vermijden
        if (boeken.contains(boek)) {
            return;
        }
        // nieuw boek toevoegen
        boeken.add(boek);
        // nieuwe auteur toevoegen
        boek.addAuteur(this);
    }   
    
    public void addAllBoeken(Collection<Boek> boeken) {
        System.out.println(boeken);
        for (Boek boek : boeken) {
            addBoek(boek);
        }
    }
    
    public void removeBoek(Boek boek) {
        // oneindige lus vermijden
        if (!boeken.contains(boek)) {
            return;
        }
        // boek verwijderen
        boeken.remove(boek);
        // auteur verwijderen
        boek.removeAuteur(this);
    }
    
    public void removeAllBoeken(Collection<Boek> boeken) {
        for (Boek boek : boeken) {
            removeBoek(boek);
        }
    }
    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="other boilerplate code">
    @Override
    public String toString() {
        return "Auteur{" + super.toString();
    }
    // </editor-fold>
    
}
