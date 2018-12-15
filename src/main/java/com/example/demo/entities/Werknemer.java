/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Arthur
 */
@Entity
@Table(name = "werknemers")
@PrimaryKeyJoinColumn(name = "ID")
public class Werknemer extends Persoon{
    private double salaris;

    public double getSalaris() {
        return salaris;
    }

    public void setSalaris(double salaris) {
        this.salaris = salaris;
    }
    
}
