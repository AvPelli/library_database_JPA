/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Arthur
 * We kiezen voor de strategie joined : elke afgeleide klasse zal een aparte tabel krijgen en verwijzen naar de oudertabel (persoon)
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="personen")
public abstract class Persoon implements Serializable{
    

    private int id;

    private String naam;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     
}
