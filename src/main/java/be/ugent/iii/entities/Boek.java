/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
    @Column(unique = true, nullable = false)
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
    
    // 1-n bidirectionele relatie tussen boeken en collecties
    @ManyToOne
    @JoinColumn(name = "COLLECTIE_ID")
    private Collectie collectie;
    
    // 1-1 bidirectionele relatie tussen boeken en leningen
    @OneToOne(mappedBy = "boek")
    private Lening lening;
    
    //MappedBy refers to the name of the Entity property (be.ugent.iii.entities.Auteur.boeken)
    // n-n bidirectionele relatie tussen auteurs en boeken
    @ManyToMany(mappedBy = "boeken")
    private final List<Auteur> auteurs = new ArrayList<>();
   
    /*
    //ManyToMany: add method required
    public boolean add(Auteur auteur){
        return auteurs.add(auteur);
    }
    */
    
    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    private boolean isSameAsFormer(Collectie collectie) {
        return this.collectie == null ? collectie == null : this.collectie.equals(collectie);
    }
    
    private boolean isSameAsFormer(Lening lening) {
        return this.lening == null ? lening == null : this.lening.equals(lening);
    }
    
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
        // oneindige lus vermijden
        if (isSameAsFormer(collectie)) {
            return;
        }
        // nieuwe collectie instellen
        Collectie oudeCollectie = this.collectie;
        this.collectie = collectie;
        // boek uit oude collectie verwijderen
        if (oudeCollectie != null) {
            oudeCollectie.removeBoek(this);
        }
        // boek aan nieuwe collectie toevoegen
        if (collectie != null) {
            collectie.addBoek(this);
        }
    }

    public Lening getLening() {
        return lening;
    }

    public void setLening(Lening lening) {
        // oneindige lus vermijden
        if (isSameAsFormer(lening)) {
            return;
        }
        // nieuwe lening instellen
        Lening oudeLening = this.lening;
        this.lening = lening;
        // boek verwijderen uit oude lening
        if (oudeLening != null) {
            oudeLening.setBoek(null);
        }
        // boek instellen voor nieuwe lening
        if (lening != null) {
            lening.setBoek(this);
        }
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void addAuteur(Auteur auteur) {
        // oneindige lus vermijden
        if (auteurs.contains(auteur)) {
            return;
        }
        // nieuwe auteur toevoegen
        auteurs.add(auteur);
        // nieuw boek toevoegen
        auteur.addBoek(this);
    }
    
    public void addAllAuteurs(Collection<Auteur> auteurs) {
        for (Auteur auteur : auteurs) {
            addAuteur(auteur);
        }
    }
    
    public void removeAuteur(Auteur auteur) {
        // oneindige lus vermijden
        if (!auteurs.contains(auteur)) {
            return;
        }
        // auteur verwijderen
        auteurs.remove(auteur);
        // boek verwijderen
        auteur.removeBoek(this);
    }
    
    public void removeAllAuteurs(Collection<Auteur> auteurs) {
        for (Auteur auteur : auteurs) {
            removeAuteur(auteur);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="other boilerplate code">
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.ID;
        hash = 97 * hash + Objects.hashCode(this.titel);
        hash = 97 * hash + Objects.hashCode(this.taal);
        hash = 97 * hash + this.jaarVanUitgave;
        hash = 97 * hash + this.ISBN;
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
        if (this.ID != other.ID) {
            return false;
        }
        if (this.jaarVanUitgave != other.jaarVanUitgave) {
            return false;
        }
        if (this.ISBN != other.ISBN) {
            return false;
        }
        if (!Objects.equals(this.titel, other.titel)) {
            return false;
        }
        if (!Objects.equals(this.taal, other.taal)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Boek{" + "ID=" + ID + ", titel=" + titel + ", taal=" + taal + ", jaarVanUitgave=" + jaarVanUitgave + ", ISBN=" + ISBN + '}';
    }
    // </editor-fold>
    
}
