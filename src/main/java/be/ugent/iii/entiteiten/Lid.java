/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entiteiten;

import java.util.*;
import javax.persistence.*;

/**
 *
 * @author axeld
 */
@Entity
@Table(name = "LEDEN")
public class Lid extends Persoon {
    
    // value object
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

    // GEEN CascadeType.ALL gebruiken => inclusief DETACH, geassocieerde bibliotheek zou tezamen met een lid verwijderd worden
    // 1-veel bidirectionele compositie met als ouder bibliotheek
    @ManyToOne
    @JoinColumn(name = "BIBLIOTHEEK")
    private Bibliotheek bibliotheek;
    
    // GEBRUIK CascadeType.ALL => geassocieerde leningen worden samen met het lid verwijderd
    // 1-veel bidirectionele compositie met als ouder lid
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "lid", fetch = FetchType.EAGER)
    private final Set<Lening> leningen = new HashSet<>();
    
    //<editor-fold defaultstate="collapsed" desc="getters/setters + add/remove">
    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
    
    public Bibliotheek getBibliotheek() {
        return bibliotheek;
    }

    public void setBibliotheek(Bibliotheek bibliotheek) {
        this.bibliotheek = bibliotheek;
        if (bibliotheek != null && !bibliotheek.getLeden().contains(this)) {
            bibliotheek.add(this);
        }
    }

    public Set<Lening> getLeningen() {
        return leningen;
    }
    
    public boolean add(Lening lening) {
        if (lening.getLid() != this) {
            lening.setLid(this);
        }
        return leningen.add(lening);
    }
    
    public boolean remove(Lening lening) {
        if (lening.getLid() == this) {
            lening.setLid(null);
        }
        return leningen.remove(lening);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString() {
        return "Lid{" + super.toString() + '}';
    }
    //</editor-fold>

}
