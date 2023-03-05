/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ZeroS TF
 */
public class Echange {
    private int id;
    private int id_panier;
    private String etat;
    private int id_transporteur;

    
    public Echange() {
    }

    public Echange(int id_panier){
    this.id_panier=id_panier;
    } 
    
    public Echange(int id_panier, String etat, int id_transporteur) {
        this.id_panier = id_panier;
        this.etat = etat;
        this.id_transporteur = id_transporteur;
    }

    public Echange(int id, int id_panier, String etat, int id_transporteur) {
        this.id = id;
        this.id_panier = id_panier;
        this.etat = etat;
        this.id_transporteur = id_transporteur;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }


  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId_transporteur() {
        return id_transporteur;
    }

    public void setId_transporteur(int id_transporteur) {
        this.id_transporteur = id_transporteur;
    }

}