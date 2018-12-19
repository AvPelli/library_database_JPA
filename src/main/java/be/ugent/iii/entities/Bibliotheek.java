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
@Table(name = "BIBLIOTHEKEN")
public class Bibliotheek implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Basic
    @Column(name = "NAAM")
    private String naam;
    
    // adres is een value-object
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "straatNaam",
                column = @Column(name = "STRAAT")),
        @AttributeOverride(name = "huisNr",
                column = @Column(name = "HUIS_NR")),
        @AttributeOverride(name = "postcode",
                column = @Column(name = "POSTCODE")),
        @AttributeOverride(name = "gemeente",
                column = @Column(name = "GEMEENTE")),
        @AttributeOverride(name = "land",
                column = @Column(name = "LAND"))
    })
    private Adres adres;
    
    //@OneToMany(cascade = CascadeType.REMOVE ,mappedBy = "bib")
    // 1-n bidirectionele relatie tussen bibliotheken en collecties
    @OneToMany(mappedBy = "bib")
    private final List<Collectie> collecties = new ArrayList<>();
    
    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<Collectie> getCollecties() {
        return collecties;
    }

    public void addCollectie(Collectie collectie) {
        // oneindige lus vermijden
        if (collecties.contains(collectie)) {
            return;
        }
        // nieuwe collectie toevoegen
        collecties.add(collectie);
        // nieuwe bibliotheek instellen
        collectie.setBib(this);
    }
    
    public void addAllCollecties(Collection<Collectie> collecties) {
        for (Collectie c : collecties) {
            addCollectie(c);
        }
    }
    
    public void removeCollectie(Collectie collectie) {
        // oneindige lus vermijden
        if (!collecties.contains(collectie)) {
            return;
        }
        // collectie verwijderen
        collecties.remove(collectie);
        // bibliotheek verwijderen
        collectie.setBib(null);
    }
    
    public void removeAllCollecties(Collection<Collectie> collecties) {
        for (Collectie c : collecties) {
            removeCollectie(c);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="other boilerplate code">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.ID;
        hash = 19 * hash + Objects.hashCode(this.naam);
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
        final Bibliotheek other = (Bibliotheek) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Bibliotheek{" + "ID=" + ID + ", naam=" + naam + ", adres=" + adres + '}';
    }
    // </editor-fold>
    
}
