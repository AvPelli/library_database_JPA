/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ugent.iii.entiteiten;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

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

    // <editor-fold defaultstate="collapsed" desc="hashCode + equals + toString">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.straatNaam);
        hash = 29 * hash + this.huisNr;
        hash = 29 * hash + Objects.hashCode(this.postcode);
        hash = 29 * hash + Objects.hashCode(this.gemeente);
        hash = 29 * hash + Objects.hashCode(this.land);
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
        final Adres other = (Adres) obj;
        if (this.huisNr != other.huisNr) {
            return false;
        }
        if (!Objects.equals(this.straatNaam, other.straatNaam)) {
            return false;
        }
        if (!Objects.equals(this.postcode, other.postcode)) {
            return false;
        }
        if (!Objects.equals(this.gemeente, other.gemeente)) {
            return false;
        }
        if (!Objects.equals(this.land, other.land)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return ""+straatNaam+" "+huisNr+"-"+postcode+" "+gemeente;
        //return "Adres{" + "straatNaam=" + straatNaam + ", huisNr=" + huisNr + ", postCode=" + postcode + ", gemeente=" + gemeente + ", land=" + land + '}';
    }
    // </editor-fold>
    
}
