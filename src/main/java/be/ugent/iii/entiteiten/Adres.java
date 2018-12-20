/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entiteiten;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author axeld
 */
@Embeddable
public class Adres implements Serializable {
    
    private String straatNaam;
    private int huisNr;
    private String postcode;
    private String gemeente;
    private String land;

    // <editor-fold defaultstate="collapsed" desc="getters/setters">
    public String getStraatNaam() {
        return straatNaam;
    }

    public void setStraatNaam(String straatNaam) {
        this.straatNaam = straatNaam;
    }

    public int getHuisNr() {
        return huisNr;
    }

    public void setHuisNr(int huisNr) {
        this.huisNr = huisNr;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public void setGemeente(String gemeente) {
        this.gemeente = gemeente;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString">
    @Override
    public String toString() {
        return "Adres{" + "straatNaam=" + straatNaam + ", huisNr=" + huisNr + ", postCode=" + postcode + ", gemeente=" + gemeente + ", land=" + land + '}';
    }
    // </editor-fold>
    
}
