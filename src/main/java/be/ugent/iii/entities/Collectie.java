/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    
    @ManyToOne
    @JoinColumn(name = "BIBLIOTHEEK")
    private Bibliotheek bib;
    
    @OneToMany(mappedBy = "collectie")
    List<Boek> boeken = new ArrayList<>();
    
    /*
    @Basic
    @Column(name = "DIVISIE")
    private String divisie;
    @Basic
    @Column(name = "SECTIE")
    private String sectie;
    */
    
    // <editor-fold defaultstate="collapsed" desc="getters/setters">
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
        this.bib = bib;
    }

    public List<Boek> getBoeken() {
        return boeken;
    }

    public void setBoeken(List<Boek> boeken) {
        this.boeken = boeken;
    }

    /*
    public String getDivisie() {
        return divisie;
    }

    public void setDivisie(String divisie) {
        this.divisie = divisie;
    }

    public String getSectie() {
        return sectie;
    }

    public void setSectie(String sectie) {
        this.sectie = sectie;
    }
    */
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="other boilerplate code">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.ID;
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
        return this.ID == other.ID;
    }
    
    @Override
    public String toString() {
        return "Collectie{" + "ID=" + ID + ", klasse=" + klasse + '}';
    }
    // </editor-fold>
}
