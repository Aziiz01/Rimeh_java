/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Echange;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author ZeroS TF
 */
public class CRUDEchange implements InterfaceCRUDEchange {
 

    Connection TuniTrocDB = DBConnection.getConnection();
        public ObservableList<Echange> echangeList = FXCollections.observableArrayList();

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
       // checkAndUpdateEchangeState();
    }

    @Override
    public void supprimerEchange(int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("DELETE FROM echange WHERE id=?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public List<Echange> afficherEchanges() throws SQLException {
    List<Echange> echanges = new ArrayList<>();
    Statement stmt = TuniTrocDB.createStatement();
    //stmt.executeUpdate("INSERT INTO echange (id_panier) SELECT id FROM panier WHERE etat = 'validé'");
   // stmt.executeUpdate("DELETE FROM panier WHERE etat = 'validé'");

    ResultSet rs = stmt.executeQuery("SELECT * FROM echange WHERE etat IN ('par defaut', 'Non Archivé');");
    while (rs.next()) {
        int id = rs.getInt("id");
        int id_panier = rs.getInt("id_panier");
        String etat = rs.getString("etat");
        int id_transporteur = rs.getInt("id_transporteur");
        Echange echange = new Echange(id, id_panier, etat, id_transporteur);
        echanges.add(echange);
    }
    System.out.println(echanges);
   // checkAndUpdateEchangeState();
    return echanges;
}
    
 public List<Echange> afficherArchive() throws SQLException {
    List<Echange> echanges = new ArrayList<>();
    Statement stmt = TuniTrocDB.createStatement();
   

    ResultSet rs = stmt.executeQuery("SELECT * FROM echange WHERE etat ='Archivé';");
    while (rs.next()) {
        int id = rs.getInt("id");
        int id_panier = rs.getInt("id_panier");
        String etat = rs.getString("etat");
        int id_transporteur = rs.getInt("id_transporteur");
        Echange echange = new Echange(id, id_panier, etat, id_transporteur);
        echanges.add(echange);
    }
    System.out.println(echanges);
    return echanges;
}
    
    
    
     public List<Echange> getAllEchanges() throws SQLException {
    List<Echange> echangeList = new ArrayList<>();
    String query = "SELECT * FROM echange";
    PreparedStatement stmt = TuniTrocDB.prepareStatement(query);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
        echangeList.add(getEchangeFromResultSet(rs));
    }
    return echangeList;
}
 public List<Echange> searchEchanges(String searchQuery) throws SQLException {
    String sql = "SELECT * FROM echange WHERE id LIKE ?";
    PreparedStatement statement = TuniTrocDB.prepareStatement(sql);
    statement.setString(1, "%" + searchQuery + "%");
    ResultSet resultSet = statement.executeQuery();

    List<Echange> echanges = new ArrayList<>();
    while (resultSet.next()) {
        int id = resultSet.getInt("id");
        int idPanier = resultSet.getInt("id_panier");
        String etat = resultSet.getString("etat");
        int idTransporteur = resultSet.getInt("id_transporteur");

        Echange echange = new Echange(id, idPanier, etat, idTransporteur);
        echanges.add(echange);
    }
    return echanges;
}





     
     private Echange getEchangeFromResultSet(ResultSet rs) throws SQLException {
    Echange echange = new Echange();
    echange.setId(rs.getInt("id"));
    echange.setEtat(rs.getString("etat"));
    echange.setId_panier(rs.getInt("id_panier"));
    echange.setId_transporteur(rs.getInt("id_transporteur"));
    return echange;
}
     
     
     public void AutoArchive() throws SQLException {
    LocalDate thresholdDate = LocalDate.now().minusDays(15);
    String sql = "UPDATE echange SET etat='Archivé' WHERE etat='par defaut' AND id_panier IN (SELECT id FROM panier WHERE date <= ?)";
    try (PreparedStatement preparedStatement = TuniTrocDB.prepareStatement(sql)) {
        preparedStatement.setDate(1, new java.sql.Date(thresholdDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000L));
        preparedStatement.executeUpdate();
    }}
    
     public List<Echange> recherche(int id_e) throws SQLException {
        List<Echange> echangeList = new ArrayList<>();
        String query = "SELECT * FROM echange WHERE id LIKE ?";
        PreparedStatement stmt = TuniTrocDB.prepareStatement(query);
        stmt.setInt(1, id_e );
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
              int id_panier = rs.getInt("id_panier");
            String etat = rs.getString("etat");
            int id_transporteur = rs.getInt("id_transporteur");

            Echange echange = new Echange(id, id_panier, etat, id_transporteur);
            echangeList.add(echange);
        }
        System.out.println(echangeList);
        return echangeList;
    }

    
    
    
    
}



   


