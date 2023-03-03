/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Hedi
 */
public class Fidelite {

    @Override
    public String toString() {
        return "Fidelite{" + "id=" + id + ", valeur=" + valeur + ", id_user=" + id_user + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
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
        final Fidelite other = (Fidelite) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Fidelite(int id, int valeur, int id_user) {
        this.id = id;
        this.valeur = valeur;
        this.id_user = id_user;
    }

    public Fidelite() {
    }
    private int id;
    private int valeur;
    private int id_user;
}
