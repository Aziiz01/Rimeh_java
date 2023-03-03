/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;


/**
 *
 * @author kheir
 */
public class Panier {
private Integer panierid;
    private String etat;
    private Date  date;
    private Integer produits;
    private Integer produitr;        
    private Integer transporteur;
    

    public Panier(Integer panierid, String etat, Date date,Integer produits,Integer produitr,Integer transporteur){
         this.panierid =panierid;   
         this.etat =etat; 
         this.date =date; 
         this.produits =produits; 
         this.produitr =produitr; 
         this.transporteur =transporteur; 
         
         
        

    }

    public Integer getPanierid() {
        return panierid;
    }

    public String getEtat() {
        return etat;
    }

    public Date getDate() {
        return date;
    }

    public Integer getProduits() {
        return produits;
    }

    public Integer getProduitr() {
        return produitr;
    }

    public Integer getTransporteur() {
        return transporteur;
    }

    public void setPanierid(Integer panierid) {
        this.panierid = panierid;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setProduits(Integer produits) {
        this.produits = produits;
    }

    public void setProduitr(Integer produitr) {
        this.produitr = produitr;
    }

    public void setTransporteur(Integer transporteur) {
        this.transporteur = transporteur;
    }

    @Override
    public String toString() {
        return "Panier{" + "panierid=" + panierid + ", etat=" + etat + ", date=" + date + ", produits=" + produits + ", produitr=" + produitr + ", transporteur=" + transporteur + '}';
    }
    
    

}