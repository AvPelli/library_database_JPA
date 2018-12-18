/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author axeld
 */
@Entity
@Table(name = "AUTEURS")
public class Auteur extends Persoon {

    @ManyToMany
    @JoinTable(name = "BOEKEN_PER_AUTEUR",
            joinColumns = @JoinColumn(name = "AUTEUR"),
            inverseJoinColumns = @JoinColumn(name = "BOEK"))
    private Set<Boek> boeken;

    public boolean add(Boek boek) {
        return boeken.add(boek);
    }

    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    public Set<Boek> getBoeken() {
        return boeken;
    }

    private void setBoeken(Set<Boek> boeken) {
        this.boeken = boeken;
    }
    
    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="other boilerplate code">
    @Override
    public String toString() {
        return "Auteur{" + super.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.boeken);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Auteur other = (Auteur) obj;
        if (!Objects.equals(this.boeken, other.boeken)) {
            return false;
        }
        return true;
    }
    // </editor-fold>

}
