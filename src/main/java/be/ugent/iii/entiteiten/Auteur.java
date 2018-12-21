/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entiteiten;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author axeld
 */
@Entity
@Table(name = "AUTEURS")
public class Auteur extends Persoon implements Serializable {
    
    /*
        "auteurs_per_boek" tabel: bijvoorbeeld 3 auteurs voor 1 boek = 3 rijen in de tabel
        Auteur verwijderen = alle entries van de auteur in "auteurs_per_boek" verwijderen
        Auteur toevoegen = alle boeken toevoegen in "auteurs_per_boek"
        => Cascade.ALL
    */
    // boeken van auteurs worden niet automatisch toegevoegd
    @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "auteurs")
    private final Set<Boek> boeken = new HashSet<>();
    
    // <editor-fold defaultstate="collapsed" desc="getters/setters + add/remove">
    public Set<Boek> getBoeken() {
        return boeken;
    }
    
    public void add(Boek boek) {
        boeken.add(boek);
        if (!boek.getAuteurs().contains(this)) {
            boek.add(this);
        }
    }
    
    public boolean remove(Boek boek) {
        if (boek.getAuteurs().contains(this)) {
            boek.remove(this);
        }
        return boeken.remove(boek);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString() {
        return "Auteur{" + super.toString() + '}';
    }
    // </editor-fold>
    
}
