/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author axeld
 */
@Entity
@Table(name = "LEDEN")
public class Lid extends Persoon {
    
    // Adres is een value-object
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
    
    // 1-n bidirectionele relatie tussen leden en leningen
    //orphanRemoval: bij het verwijderen van een boek uit de lening tabel
    //wordt enkel dit boek verwijderd en niet het lid (lid kan nog andere boeken hebben)
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "lid", orphanRemoval = true)
    private final List<Lening> leningen = new ArrayList<>();
    /*
    @ManyToMany
    private List<Bibliotheek> bibs;
    */
    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<Lening> getLeningen() {
        return leningen;
    }

    public void addLening(Lening lening) {
        // oneindige lus vermijden
        if (leningen.contains(lening)) {
            return;
        }
        // nieuwe lening toevoegen
        leningen.add(lening);
        // nieuw lid instellen
        lening.setLid(this);
    }
    
    public void addAllLeningen(Collection<Lening> leningen) {
        for (Lening lening : leningen) {
            addLening(lening);
        }
    }
    
    public void removeLening(Lening lening) {
        // oneindige lus vermijden
        if (!leningen.contains(lening)) {
            return;
        }
        // lening verwijderen
        leningen.remove(lening);
        // lid verwijderen
        lening.setLid(null);
    }
    
    public void removeAllLeningen(Collection<Lening> leningen) {
        for (Lening lening : leningen) {
            removeLening(lening);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="other boilerplate code">
    @Override
    public String toString() {
        return "Lid{" + super.toString() + ", adres=" + adres + '}';
    }
    // </editor-fold>
}
