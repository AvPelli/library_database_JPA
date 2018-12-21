/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entiteiten;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author axeld
 */
@Entity
@Table(name = "BIBLIOTHEKEN")
public class Bibliotheek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Basic
    @Column(name = "NAAM")
    private String naam;
    
    // value object
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "straatNaam",
                column = @Column(name = "STRAAT", nullable = true)),
        @AttributeOverride(name = "huisNr",
                column = @Column(name = "HUIS_NR", nullable = true)),
        @AttributeOverride(name = "postcode",
                column = @Column(name = "POSTCODE", nullable = true)),
        @AttributeOverride(name = "gemeente",
                column = @Column(name = "GEMEENTE", nullable = true)),
        @AttributeOverride(name = "land",
                column = @Column(name = "LAND", nullable = true))
    })
    private Adres adres;
    
    // GEBRUIK CascadeType.ALL => geassocieerde collecties worden samen met de bibliotheek verwijderd
    // 1-veel bidirectionele compositie met als ouder bibliotheek
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliotheek")
    private final Set<Collectie> collecties = new HashSet<>();
    
    // GEBRUIK CascadeType.ALL => geassocieerde leden worden samen met de bibliotheek verwijderd
    // 1-veel bidirectionele compositie met als ouder bibliotheek
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bibliotheek")
    private final Set<Lid> leden = new HashSet<>();

    // <editor-fold defaultstate="collapsed" desc="getters/setters + add/remove">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Collectie> getCollecties() {
        return collecties;
    }

    public boolean add(Collectie collectie) {
        if (collectie.getBibliotheek() != this) {
            collectie.setBibliotheek(this);
        }
        return collecties.add(collectie);
    }
    
    public boolean remove(Collectie collectie) {
        if (collectie.getBibliotheek() == this) {
            collectie.setBibliotheek(null);
        }
        return collecties.remove(collectie);
    }

    public Set<Lid> getLeden() {
        return leden;
    }
    
    public boolean add(Lid lid) {
        if (lid.getBibliotheek() != this) {
            lid.setBibliotheek(this);
        }
        return leden.add(lid);
    }
    
    public boolean remove(Lid lid) {
        if (lid.getBibliotheek() == this) {
            lid.setBibliotheek(null);
        }
        return leden.remove(lid);
    }
    
    public List<Boek> getBoeken() {
        List<Boek> boeken = new ArrayList<>();
        for (Collectie collectie : collecties) {
            boeken.addAll(collectie.getBoeken());
        }
        return boeken;
    }
    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="hashCode + equals + toString">
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bibliotheek)) {
            return false;
        }
        Bibliotheek other = (Bibliotheek) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bibliotheek{" + "id=" + id + ", naam=" + naam + ", adres=" + adres + '}';
    } 
    // </editor-fold>
    
}
