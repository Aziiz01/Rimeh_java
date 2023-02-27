/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

/**
 *
 * @author ZeroS TF
 */
public class CRUDPost implements InterfaceCRUDPost{
    Connection TuniTrocDB = DBConnection.getConnection();
    @Override
    public void ajouterPost(Post post) throws SQLException {
     PreparedStatement stmt = TuniTrocDB.prepareStatement("INSERT INTO post(titre,contenu, date, id_user) VALUES(?, ?, ?, ?)");
        stmt.setString(1, post.getTitre());
        stmt.setString(2, post.getContenu());
        stmt.setTimestamp(3, Timestamp.valueOf(post.getDate()));
        stmt.setInt(4, post.getId_user());
        stmt.executeUpdate();
    }

    @Override
    public void modifierPost(Post post, int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("UPDATE post SET titre=?, contenu=?, date=?, id_user=? WHERE id=?");
       stmt.setString(1, post.getTitre());
        stmt.setString(2, post.getContenu());
        stmt.setTimestamp(3, Timestamp.valueOf(post.getDate()));
        stmt.setInt(4, post.getId_user());
        stmt.setInt(5, id);
        stmt.executeUpdate();
    }

    @Override
    public void supprimerPost(int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("DELETE FROM post WHERE id=?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    @Override
    public List<Post> afficherPosts() throws SQLException {
        List<Post> posts = new ArrayList<>();
        Statement stmt = TuniTrocDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM post");
        while (rs.next()) {
            int id = rs.getInt("id");
            String titre = rs.getString("titre");
            String contenu = rs.getString("contenu");
            LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
            int id_user = rs.getInt("id_user");
            Post post = new Post(id, titre, contenu, date, id_user);
            posts.add(post);
        }
        System.out.println(posts);
        return posts;
    }
    }
    

