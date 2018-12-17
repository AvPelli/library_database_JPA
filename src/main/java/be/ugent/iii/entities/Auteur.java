/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author axeld
 */
@Entity
@Table(name = "AUTEURS")
public class Auteur extends Persoon {
    @ManyToMany
    private List<Boek> boeken = new ArrayList<>();

    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    public List<Boek> getBoeken() {
        return boeken;
    }

    public void setBoeken(List<Boek> boeken) {
        this.boeken = boeken;
    }
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="other boilerplate code">
    @Override
    public String toString() {
        return "Auteur{" + super.toString();
    }
    // </editor-fold>
}
