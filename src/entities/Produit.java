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

    public class Produit {
 
    private int id;
    private String type;
    private String categorie;
    private String nom;
    private String libelle;        
    private String ville;
    private String photo;
    private int id_user;

    public Produit(int id, String type, String categorie, String nom, String libelle, String photo, String ville, int id_user) {
         this.id = id;   
         this.type = type; 
         this.categorie = categorie; 
         this.nom = nom; 
         this.libelle = libelle; 
         this.photo = photo; 
         this.ville = ville;
         this.id_user = id_user;
    }

    

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getNom() {
        return nom;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getVille() {
        return ville;
    }

    public String getPhoto() {
        return photo;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Produit() {
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", type=" + type + ", categorie=" + categorie + ", nom=" + nom + ", libelle=" + libelle + ", photo=" + photo + ", ville=" + ville + ", id_user=" + id_user + '}';
    }   
}


