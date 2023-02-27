/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDateTime;

/**
 *
 * @author ZeroS TF
 */
public class Commentaire {
    private int id;
    private String contenu;
    private LocalDateTime date;
    private int id_post;
    private int id_user;

    public Commentaire() {
    }

    public Commentaire(int id, String contenu, LocalDateTime date, int id_post, int id_user) {
        this.id = id;
        this.contenu = contenu;
        this.date = date;
        this.id_post = id_post;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", contenu=" + contenu + ", date=" + date + ", id_post=" + id_post + ", id_user=" + id_user + '}';
    }
    
}
