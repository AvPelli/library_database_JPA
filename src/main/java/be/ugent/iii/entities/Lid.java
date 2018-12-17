/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entities;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author axeld
 */
@Entity
@Table(name = "LEDEN")
public class Lid extends Persoon {
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
    
    @ManyToMany
    private List<Bibliotheek> bibs;

    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<Bibliotheek> getBibs() {
        return bibs;
    }

    public void setBibs(List<Bibliotheek> bibs) {
        this.bibs = bibs;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="other boilerplate code">
    @Override
    public String toString() {
        return "Lid{" + super.toString() + ", adres=" + adres + '}';
    }
    // </editor-fold>
}
