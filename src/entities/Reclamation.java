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
public class Reclamation  {

    private int id;
    private int id_userS;
    private int id_userR;
    private String cause;
    private boolean etat;
    
    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_userS=" + id_userS + ", id_userR=" + id_userR + ", cause=" + cause + ", etat=" + etat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_userS(int id_userS) {
        this.id_userS = id_userS;
    }

    public void setId_userR(int id_userR) {
        this.id_userR = id_userR;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public int getId_userS() {
        return id_userS;
    }

    public int getId_userR() {
        return id_userR;
    }

    public String getCause() {
        return cause;
    }

    public boolean IsEtat() {
        return etat;
    }

    public Reclamation(int id, int id_userS, int id_userR, String cause, boolean etat) {
        this.id = id;
        this.id_userS = id_userS;
        this.id_userR = id_userR;
        this.cause = cause;
        this.etat = etat;
    }

    public Reclamation() {
    }
    
}
