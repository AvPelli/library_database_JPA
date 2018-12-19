/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author axeld
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Persoon implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected int ID;
    @Basic
    @Column(name = "VOORNAAM")
    protected String voorNaam;
    @Column(name = "ACHTERNAAM")
    protected String achterNaam;
    @Basic
    @Column(name = "GESLACHT")
    protected char geslacht;
    
    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    public void setVoorNaam(String voorNaam) {
        this.voorNaam = voorNaam;
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public void setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
    }

    public char getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(char geslacht) {
        this.geslacht = geslacht;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="other boilerplate code">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.ID;
        hash = 41 * hash + Objects.hashCode(this.voorNaam);
        hash = 41 * hash + Objects.hashCode(this.achterNaam);
        hash = 41 * hash + this.geslacht;
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
        final Persoon other = (Persoon) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.geslacht != other.geslacht) {
            return false;
        }
        if (!Objects.equals(this.voorNaam, other.voorNaam)) {
            return false;
        }
        if (!Objects.equals(this.achterNaam, other.achterNaam)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "ID=" + ID + ", voorNaam=" + voorNaam + ", achterNaam=" + achterNaam + ", geslacht=" + geslacht;
    }
    // </editor-fold>
    
}
