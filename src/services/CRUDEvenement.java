/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.DBConnection;

/**
 *
 * @author ZeroS TF
 */
public class CRUDEvenement implements InterfaceCRUDEvenement {

    Connection TuniTrocDB = DBConnection.getConnection();

    @Override
    public void ajouterEvenement(Evenement evenement) throws SQLException {

        PreparedStatement stmt = TuniTrocDB.prepareStatement("INSERT INTO evenement(nom,description, date_d, date_f) VALUES(?, ?, ?, ?)");
        stmt.setString(1, evenement.getNom());
        stmt.setString(2, evenement.getDescription());
        stmt.setDate(3, Date.valueOf(evenement.getDate_d()));
        stmt.setDate(4, Date.valueOf(evenement.getDate_f()));
        stmt.executeUpdate();
    }

    @Override
    public void modifierEvenement(Evenement evenement, int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("UPDATE evenement SET nom=?, description=?, date_d=?, date_f=? WHERE id=?");
        stmt.setString(1, evenement.getNom());
        stmt.setString(2, evenement.getDescription());
        stmt.setDate(3, Date.valueOf(evenement.getDate_d()));
        stmt.setDate(4, Date.valueOf(evenement.getDate_f()));
        stmt.setInt(5, id);
        stmt.executeUpdate();
    }

    @Override
    public void supprimerEvenement(int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("DELETE FROM evenement WHERE id=?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    @Override
    public List<Evenement> afficherEvenements() throws SQLException {
        List<Evenement> evenements = new ArrayList<>();
        Statement stmt = TuniTrocDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM evenement");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String description = rs.getString("description");
            LocalDate dateDebut = rs.getDate("date_d").toLocalDate();
            LocalDate dateFin = rs.getDate("date_f").toLocalDate();
            Evenement evenement = new Evenement(id, nom, description, dateDebut, dateFin);
            evenements.add(evenement);
        }
        System.out.println(evenements);
        return evenements;
    }

    @Override
    public boolean existeEvenement(String nom) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM evenement WHERE nom = ?");
        ps.setString(1, nom);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count > 0;
    }
    
    public List<Evenement> recherche(String nom_e) throws SQLException {
        List<Evenement> eventList = new ArrayList<>();
        String query = "SELECT * FROM evenement WHERE nom LIKE ?";
        PreparedStatement stmt = TuniTrocDB.prepareStatement(query);
        stmt.setString(1, "%" + nom_e + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String description = rs.getString("description");
            LocalDate dateDebut = rs.getDate("date_d").toLocalDate();
            LocalDate dateFin = rs.getDate("date_f").toLocalDate();
            Evenement evenement = new Evenement(id, nom, description, dateDebut, dateFin);
            eventList.add(evenement);
        }
        System.out.println(eventList);
        return eventList;
    }

}
