/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Fidelite;
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
 * @author Hedi
 */
public class CRUDFidelite implements InterfaceCRUDFidelite{
Connection TuniTrocDB = DBConnection.getConnection();
    @Override
    public void ajouterFidelite(Fidelite fidelite) throws SQLException {        
        PreparedStatement stmt = TuniTrocDB.prepareStatement("INSERT INTO fidelite(valeur,id_user) VALUES(?, ?)");
        stmt.setInt(1, fidelite.getValeur());
        stmt.setInt(2, fidelite.getId_user());
        stmt.executeUpdate();
    }

    @Override
    public void modifierFidelite(Fidelite fidelite, int id) throws SQLException {
 PreparedStatement stmt = TuniTrocDB.prepareStatement("UPDATE fidelite SET valeur=?, id_user=? WHERE id=?");
        stmt.setInt(1, fidelite.getValeur());
        stmt.setInt(2, fidelite.getId_user());
        stmt.setInt(3,id);  
        stmt.executeUpdate();    
    }

    @Override
    public void supprimerFidelite(int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("DELETE FROM fidelite WHERE id=?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    @Override
    public List<Fidelite> afficherFidelites() throws SQLException {
        List<Fidelite> fidelites = new ArrayList<>();
        Statement stmt = TuniTrocDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM fidelite");
        while (rs.next()) {
            int id = rs.getInt("id");
            int valeur = rs.getInt("valeur");
            int id_user = rs.getInt("id_user");
            Fidelite fidelite = new Fidelite(id, valeur, id_user);
            fidelites.add(fidelite);
        }
        System.out.println(fidelites);
        return fidelites;
    }
 
}
