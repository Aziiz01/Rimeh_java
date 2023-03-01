/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Echange;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

/**
 *
 * @author ZeroS TF
 */
public class CRUDEchange implements InterfaceCRUDEchange {

    Connection TuniTrocDB = DBConnection.getConnection();
    @Override
    public void ajouterEchange(Echange echange) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("INSERT INTO echange(id_panier, etat,id_transporteur) VALUES(?, ?, ?)");
        stmt.setInt(1, echange.getId_panier());
        stmt.setString(2, echange.getEtat());
        stmt.setInt(3, echange.getId_transporteur());
        stmt.executeUpdate();
    }

    @Override
    public void modifierEchange(Echange echange, int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("UPDATE echange SET id_panier=?, etat=?, id_transporteur=? WHERE id=?");
        stmt.setInt(1, echange.getId_panier());
        stmt.setString(2, echange.getEtat());
        stmt.setInt(3, echange.getId_transporteur());
        stmt.setInt(4, id);
        stmt.executeUpdate();
    }

    @Override
    public void supprimerEchange(int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("DELETE FROM echange WHERE id=?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    @Override
    public List<Echange> afficherEchanges() throws SQLException {
        List<Echange> echanges = new ArrayList<>();
        Statement stmt = TuniTrocDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM echange");
        while (rs.next()) {
            int id = rs.getInt("id");
            int id_panier=rs.getInt("id_panier");
            String etat = rs.getString("etat");
            int id_transporteur = rs.getInt("id_transporteur");
            Echange echange = new Echange(id, id_panier, etat, id_transporteur);
            echanges.add(echange);
        }
        System.out.println(echanges);
        return echanges;
    }
}
