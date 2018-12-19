/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author axeld
 */
@Entity
@Table(name = "COLLECTIES")
public class Collectie implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Basic
    @Column(name = "KLASSE")
    private String klasse;
    
    // 1-n bidirectionele relatie tussen bibliotheken en collecties
    @ManyToOne
    @JoinColumn(name = "BIBLIOTHEEK_ID")
    private Bibliotheek bib;
    
    // 1-n bidirectionele relatie tussen boeken en collecties
    @OneToMany(mappedBy = "collectie")
    private final List<Boek> boeken = new ArrayList<>();
    
    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    private boolean isSameAsFormer(Bibliotheek bib) {
        return this.bib == null ? bib == null : this.bib.equals(bib);
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public Bibliotheek getBib() {
        return bib;
    }

    public void setBib(Bibliotheek bib) {
        // oneindige lus vermijden
        if (isSameAsFormer(bib)) {
            return;
        }
        // nieuwe bib instellen
        Bibliotheek oudeBib = this.bib;
        this.bib = bib;
        // collectie van oude bib verwijderen
        if (oudeBib != null) {
            oudeBib.removeCollectie(this);
        }
        // collectie aan nieuwe bib toevoegen
        if (bib != null) {
            bib.addCollectie(this);
        }
    }

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
        // nieuwe collectie instellen
        boek.setCollectie(this);
    }
    
    public void addAllBoeken(Collection<Boek> boeken) {
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
        // collectie verwijderen
        boek.setCollectie(null);
    }
    
    public void removeAllBoeken(Collection<Boek> boeken) {
        for (Boek boek : boeken) {
            removeBoek(boek);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="other boilerplate code">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.ID;
        hash = 41 * hash + Objects.hashCode(this.klasse);
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
        final Collectie other = (Collectie) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.klasse, other.klasse)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Collectie{" + "ID=" + ID + ", klasse=" + klasse + '}';
    }
    // </editor-fold>
    
}
