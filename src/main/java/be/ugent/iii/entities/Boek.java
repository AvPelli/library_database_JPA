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
@Table(name = "BOEKEN")
public class Boek implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Basic
    @Column(name = "TITEL")
    private String titel;
    @Basic
    @Column(name = "TAAL")
    private String taal;
    @Basic
    @Column(name = "JAAR_UITGAVE")
    private int jaarVanUitgave;
    @Basic
    @Column(name = "ISBN")
    private int ISBN;
    @ManyToOne
    @JoinColumn(name = "COLLECTIE")
    private Collectie collectie;
    
    @ManyToMany
    List<Auteur> auteurs = new ArrayList<>();
    
    @Transient
    private String samenvatting;
    
    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getTaal() {
        return taal;
    }

    public void setTaal(String taal) {
        this.taal = taal;
    }

    public int getJaarVanUitgave() {
        return jaarVanUitgave;
    }

    public void setJaarVanUitgave(int jaarVanUitgave) {
        this.jaarVanUitgave = jaarVanUitgave;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public Collectie getCollectie() {
        return collectie;
    }

    public void setCollectie(Collectie collectie) {
        this.collectie = collectie;
    }
    
    public String getSamenvatting() {
        return samenvatting;
    }

    public void setSamenvatting(String samenvatting) {
        this.samenvatting = samenvatting;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="other boilerplate code">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.ID;
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
        final Boek other = (Boek) obj;
        return this.ID == other.ID;
    }
    
    @Override
    public String toString() {
        return "Boek{" + "ID=" + ID + ", titel=" + titel + ", taal=" + taal + ", jaarVanUitgave=" + jaarVanUitgave + ", ISBN=" + ISBN + '}';
    }
    // </editor-fold>
}
