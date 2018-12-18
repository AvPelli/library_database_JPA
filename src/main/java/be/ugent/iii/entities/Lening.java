/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entities;

import java.io.Serializable;
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
    
    @ManyToOne
    private Lid lid;
    @OneToOne()
    @MapsId
    @JoinColumn(name = "BOEK")
    private Boek boek;

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
        this.lid = lid;
    }

    public Boek getBoek() {
        return boek;
    }

    public void setBoek(Boek boek) {
        this.boek = boek;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.Id;
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
        return this.Id == other.Id;
    }

    @Override
    public String toString() {
        return "Lening{" + "Id=" + Id + ", lid=" + lid + ", boek=" + boek + '}';
    }
}
