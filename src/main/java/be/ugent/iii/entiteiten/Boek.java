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
@Table(name = "BOEKEN")
public class Boek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
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
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "COLLECTIE")
    private Collectie collectie;
    
    // GEEN CascadeType.ALL gebruiken => inclusief DETACH, alle geassocieerde auteurs zouden tezamen met een boek verwijderd worden
    // veel-veel bidirectionele associatie
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "AUTEURS_PER_BOEK",
            joinColumns = @JoinColumn(name = "BOEK"),
            inverseJoinColumns = @JoinColumn(name = "AUTEUR"))
    private final Set<Auteur> auteurs = new HashSet<>();
    
    // 1-1 bidirectionele compositie met als ouder compositie
    @OneToOne(mappedBy = "boek", optional = true, orphanRemoval = true)
    private Lening ontlening;
    
    public boolean isBeschikbaar() {
        return ontlening == null;
    }

    // <editor-fold defaultstate="collapsed" desc="getters/setters + add/remove">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (collectie != null && !collectie.getBoeken().contains(this)) {
            collectie.add(this);
        }
    }

    public Set<Auteur> getAuteurs() {
        return auteurs;
    }
    
    public boolean add(Auteur auteur) {
        if (!auteur.getBoeken().contains(this)) {
            auteur.add(this);
        }
        return auteurs.add(auteur);
    }
    
    public boolean remove(Auteur auteur) {
        if (auteur.getBoeken().contains(this)) {
            auteur.remove(this);
        }
        return auteurs.remove(auteur);
    }

    public Lening getOntlening() {
        return ontlening;
    }

    public void setOntlening(Lening ontlening) {
        this.ontlening = ontlening;
        if (ontlening != null && ontlening.getBoek() != this) {
            ontlening.setBoek(this);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="hashCode + equals + toString">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.titel);
        hash = 37 * hash + this.ISBN;
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
        if (this.id != other.id) {
            return false;
        }
        if (this.ISBN != other.ISBN) {
            return false;
        }
        if (!Objects.equals(this.titel, other.titel)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return ""+id+" "+titel+" "+taal+" "+jaarVanUitgave;
        //return "Boek{" + "id=" + id + ", titel=" + titel + ", taal=" + taal + ", jaarVanUitgave=" + jaarVanUitgave + ", ISBN=" + ISBN + ", beschikbaar=" + isBeschikbaar() + '}';
    }
    // </editor-fold>
    
}
