/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDate;


/**
 *
 * @author kheir
 */
public class Panier {
    private int id;
    private LocalDate date;
    private int produit_s;
    private int produit_r;
    private boolean transporteurB;
    

    public Panier(int id, LocalDate date ,int produit_s, int produit_r, boolean transporteurB) {
        this.id = id;
        this.date = date;
        this.produit_s = produit_s;
        this.produit_r = produit_r;
        this.transporteurB = transporteurB;
        
        
    }

   
    
    

    public int getId() {
        return id;
    }

    public int getProduit_s() {
        return produit_s;
    }

    public int getProduit_r() {
        return produit_r;
    }

    public boolean getTransporteurB() {
        return transporteurB;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduit_s(int produit_s) {
        this.produit_s = produit_s;
    }

    public void setProduit_r(int produit_r) {
        this.produit_r = produit_r;
    }

    public void setTransporteurB(boolean transporteurB) {
        this.transporteurB = transporteurB;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
     public Panier() {
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", date=" + date +", produit_s=" + produit_s + ", produit_r=" + produit_r + ", transporteurB=" + transporteurB +  '}';
    }

    
    
    
}


