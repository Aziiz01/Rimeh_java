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
public class Post {
    private int id;
    private String titre;
    private String contenu;
    private LocalDateTime date;
    private int id_user;

    public Post() {
    }

    public Post(int id, String titre, String contenu, LocalDateTime date, int id_user) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", titre=" + titre + ", contenu=" + contenu + ", date=" + date + ", id_user=" + id_user + '}';
    }
    
}
