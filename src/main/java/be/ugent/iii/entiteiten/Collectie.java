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
@Table(name = "COLLECTIES")
public class Collectie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Basic
    @Column(name = "KLASSE")
    private String klasse;
    
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "BIBLIOTHEEK")
    private Bibliotheek bibliotheek;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "collectie")
    private final Set<Boek> boeken = new HashSet<>();
    
    // <editor-fold defaultstate="collapsed" desc="getters/setters + add/remove">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public Bibliotheek getBibliotheek() {
        return bibliotheek;
    }

    public void setBibliotheek(Bibliotheek bibliotheek) {
        this.bibliotheek = bibliotheek;
        if (bibliotheek != null && !bibliotheek.getCollecties().contains(this)) {
            bibliotheek.add(this);
        }
    }

    public Set<Boek> getBoeken() {
        return boeken;
    }
    
    public boolean add(Boek boek) {
        if (boek.getCollectie() != this) {
            boek.setCollectie(this);
        }
        return boeken.add(boek);
    }
    
    public boolean remove(Boek boek) {
        if (boek.getCollectie() == this) {
            boek.setCollectie(null);
        }
        return boeken.remove(boek);
    }
    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="hashCode + equals + toString">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.klasse);
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
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.klasse, other.klasse)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Collectie{" + "id=" + id + ", klasse=" + klasse + '}';
    }
    // </editor-fold>
    
}
