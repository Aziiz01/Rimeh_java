/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author ZeroS TF
 */
public class Evenement {
    private int id;
    private String nom;
    private String description;
    private LocalDate date_d;
    private LocalDate date_f;

    public Evenement() {
    }

    public Evenement(int id, String nom, String description, LocalDate date_d, LocalDate date_f) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date_d = date_d;
        this.date_f = date_f;
    }

    public Evenement(String nom, String description, LocalDate date_d, LocalDate date_f) {
        this.nom = nom;
        this.description = description;
        this.date_d = date_d;
        this.date_f = date_f;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate_d() {
        return date_d;
    }

    public void setDate_d(LocalDate date_d) {
        this.date_d = date_d;
    }

    public LocalDate getDate_f() {
        return date_f;
    }

    public void setDate_f(LocalDate date_f) {
        this.date_f = date_f;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", date_d=" + date_d + ", date_f=" + date_f + '}';
    }

}
