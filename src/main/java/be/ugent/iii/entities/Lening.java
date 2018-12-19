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
@Table(name = "LENINGEN")
public class Lening implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    
    // 1-n bidirectionele relatie tussen leden en leningen
    
    // Lazy fetching: enkel wanneer gevraagd wordt om de leden zal hibernate ze opvragen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LID_ID")
    private Lid lid;
    
    // 1-1 bidirectionele relatie tussen boeken en leningen
    @OneToOne
    @JoinColumn(name = "BOEK_ID")
    @MapsId
    private Boek boek;

    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    private boolean isSameAsFormer(Lid lid) {
        return this.lid == null ? lid == null : this.lid.equals(lid);
    }
    
    private boolean isSameAsFormer(Boek boek) {
        return this.boek == null ? boek == null : this.boek.equals(boek);
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Lid getLid() {
        return lid;
    }

    public void setLid(Lid lid) {
        // oneindige lus vermijden
        if (isSameAsFormer(lid)) {
            return;
        }
        // nieuw lid instellen
        Lid oudLid = this.lid;
        this.lid = lid;
        // lening van oud lid verwijderen
        if (oudLid != null) {
            oudLid.removeLening(this);
        }
        // lening aan nieuw lid toevoegen
        if (lid != null) {
            lid.addLening(this);
        }
    }

    public Boek getBoek() {
        return boek;
    }

    public void setBoek(Boek boek) {
        // oneindige lus vermijden
        if (isSameAsFormer(boek)) {
            return;
        }
        // nieuw boek instellen
        Boek oudBoek = this.boek;
        this.boek = boek;
        // lening verwijderen van oud boek
        if (oudBoek != null) {
            oudBoek.setLening(null);
        }
        // lening toevoegen aan nieuw boek
        if (boek != null) {
            boek.setLening(this);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="other boilerplate code">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.Id;
        hash = 97 * hash + Objects.hashCode(this.lid);
        hash = 97 * hash + Objects.hashCode(this.boek);
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
        final Lening other = (Lening) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.lid, other.lid)) {
            return false;
        }
        if (!Objects.equals(this.boek, other.boek)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lening{" + "Id=" + Id + '}';
    }
    // </editor-fold>
    
}
