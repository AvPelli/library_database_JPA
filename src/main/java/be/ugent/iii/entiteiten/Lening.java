/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entiteiten;

import java.io.Serializable;
import java.sql.Date;
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
    private int id;
    
    @Basic
    @Column(name = "CHECK_OUT_DATUM")
    private Date checkOutDatum;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "LID")
    private Lid lid;
    
    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "BOEK")
    private Boek boek;
    
    /*
    @Basic
    @Column(name = "LEENTERMIJN")
    private int leentermijn;
    
    @Basic
    @Column(name = "BOETE_PER_DAG")
    private double boetePerDag;
    */
    
    //<editor-fold defaultstate="collapsed" desc="getters/setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckOutDatum() {
        return checkOutDatum;
    }

    public void setCheckOutDatum(Date checkOutDatum) {
        this.checkOutDatum = checkOutDatum;
    }
    
    public void setCheckOutDatum(int jaar, int maand, int dag) {
        checkOutDatum = new Date(jaar - 1900, maand - 1, dag);
    }

    public Lid getLid() {
        return lid;
    }

    public void setLid(Lid lid) {
        this.lid = lid;
        if (lid != null && !lid.getLeningen().contains(this)) {
            lid.add(this);
        }
    }

    public Boek getBoek() {
        return boek;
    }

    public void setBoek(Boek boek) {
        this.boek = boek;
        if (boek.getOntlening() != this) {
            boek.setOntlening(this);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode + equals + toString">
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.lid);
        hash = 89 * hash + Objects.hashCode(this.boek);
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
        return "Lening{" + "checkOutDatum=" + checkOutDatum + ", boek=" + boek.getTitel() + '}';
    }
    //</editor-fold>
    
}
