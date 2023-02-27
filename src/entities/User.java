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
public class User {

    private int id;
    private String email;
    private String pwd;
    private String nom;
    private String prenom;
    private byte[] photo;
    private String num_tel;
    private String ville;
    private int valeur_fidelite;
    private boolean role;
    private String salt;
    private String token;
    private EtatUser etat;

    public User() {
    }

    public User(String email, String pwd, String nom, String prenom, byte[] photo, String num_tel, String ville, int valeur_fidelite, boolean role) {
        this.email = email;
        this.pwd = pwd;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.num_tel = num_tel;
        this.ville = ville;
        this.valeur_fidelite = valeur_fidelite;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getValeur_fidelite() {
        return valeur_fidelite;
    }

    public void setValeur_fidelite(int valeur_fidelite) {
        this.valeur_fidelite = valeur_fidelite;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EtatUser getEtat() {
        return etat;
    }

    public void setEtat(EtatUser etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", pwd=" + pwd + ", nom=" + nom + ", prenom=" + prenom + ", photo=" + photo + ", num_tel=" + num_tel + ", ville=" + ville + ", valeur_fidelite=" + valeur_fidelite + ", role=" + role + ", salt=" + salt + ", token=" + token + ", etat=" + etat + '}';
    }

    public enum EtatUser {
        ACTIF,
        INACTIF,
        BLOQUE,
        NONBLOQUE,
        ENATTENTECONFIRMATION
    }

    public boolean isLoggedIn() {
        return token != null;
    }
}
