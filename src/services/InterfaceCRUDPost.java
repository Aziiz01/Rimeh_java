/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Post;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ZeroS TF
 */
public interface InterfaceCRUDPost {
    public void ajouterPost(Post post) throws SQLException;

    public void modifierPost(Post post, int id) throws SQLException;

    public void supprimerPost(int id) throws SQLException;

    public List<Post> afficherPosts() throws SQLException;
}
