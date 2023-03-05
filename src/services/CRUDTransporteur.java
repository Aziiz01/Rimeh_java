/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Echange;
import entities.Transporteur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.DBConnection;
import java.util.ArrayList;


/**
 *
 * @author azizn
 */
public class CRUDTransporteur implements InterfaceCRUDTransporteur {
    
        Connection TuniTrocDB = DBConnection.getConnection();
        
public void ajouterTransporteur(Transporteur transporteur) throws SQLException {
    String query = "INSERT INTO transporteur (nom, prenom, num_tel, photo) VALUES (?, ?, ?, ?)";
    PreparedStatement stmt = TuniTrocDB.prepareStatement(query);
    stmt.setString(1, transporteur.getNom());
    stmt.setString(2, transporteur.getPrenom());
    stmt.setInt(3, transporteur.getNum_tel());
    stmt.setBytes(4, transporteur.getPhoto());
    stmt.executeUpdate();
}


public void modifierTransporteur(Transporteur transporteur, int id) throws SQLException {
    String query = "UPDATE transporteur SET nom=?, prenom=?, num_tel=?, photo=? WHERE id=?";
    PreparedStatement stmt = TuniTrocDB.prepareStatement(query);
        stmt.setString(1, transporteur.getNom());
    stmt.setString(2, transporteur.getPrenom());
    stmt.setInt(3, transporteur.getNum_tel());
    stmt.setBytes(4, transporteur.getPhoto());
    stmt.setInt(5, transporteur.getId());
stmt.executeUpdate();
    
}
public void supprimerTransporteur(int id) throws SQLException{
String query = "DELETE FROM transporteur WHERE id=?";
    PreparedStatement stmt = TuniTrocDB.prepareStatement(query);
    stmt.setInt(1, id);
    stmt.executeUpdate();
}

 public List<Transporteur> afficherTransporteurs() throws SQLException{
 
 List<Transporteur> TranspList = new ArrayList<>();
        Statement stmt = TuniTrocDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM transporteur");
        while (rs.next()) {
            TranspList.add(getTranspFromResultSet(rs));
        }
        System.out.println(TranspList);
        return TranspList;
 
 
 
 }

 private Transporteur getTranspFromResultSet(ResultSet rs) throws SQLException {
        Transporteur transporteur= new Transporteur();
        transporteur.setId(rs.getInt("id"));
        transporteur.setNom(rs.getString("nom"));
        transporteur.setPrenom(rs.getString("prenom"));
        transporteur.setPhoto(rs.getBytes("photo"));
        transporteur.setNum_tel(rs.getInt("num_tel"));
       
        return transporteur;
    }
public List<Transporteur> recherchee(String nom) throws SQLException {
        List<Transporteur> transpList = new ArrayList<>();
        String query = "SELECT * FROM transporteur WHERE nom LIKE ? OR prenom LIKE ?";
        PreparedStatement stmt = TuniTrocDB.prepareStatement(query);
        stmt.setString(1, "%" + nom + "%");
        stmt.setString(2, "%" + nom + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            transpList.add(getTranspFromResultSet(rs));
        }
        System.out.println(transpList);
        return transpList;
    }

    public Echange[] afficherTransporteur() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}




