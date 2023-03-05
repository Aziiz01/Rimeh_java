/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author azizn
 */
public class Transporteur {
     private int id;
    private String nom;
    private String prenom;
    private int num_tel;
    private byte[]  photo;

    public Transporteur() {
    }

    public Transporteur(int id, String nom, String prenom, int num_tel, byte[] photo) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.photo = photo;
    }

    public Transporteur(String nom, String prenom, byte[] photo,int num_tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.photo = photo;
    }

   
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    
    
    
    
    
}
