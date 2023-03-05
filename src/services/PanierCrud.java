/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Panier;
import entities.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import static java.sql.DriverManager.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

/**
 *
 * @author kheir
 */
public class PanierCrud {
    Connection TuniTrocDB = DBConnection.getConnection();
 
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tunitroc1", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
 public void ajouterPanier(Panier p,LocalDate date,int produit_s, int produit_r,  boolean transporteurB) {
    try {
        //Connection conn = Connexion.getInstance().getCnx();
        String requete ="INSERT INTO panier (date, produit_s, produit_r, transporteurB) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = TuniTrocDB.prepareStatement(requete);
        pst.setDate(1, Date.valueOf(date));
        pst.setInt(2, produit_s);
        pst.setInt(3, produit_r);
        pst.setBoolean(4, transporteurB);
        pst.executeUpdate();
        System.out.println("Le panier a été ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    
    public List<Panier> affich() {
    List<Panier> myList = new ArrayList<>();

    try {
        String requete = "SELECT * FROM panier";
        Statement st = TuniTrocDB.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
           Panier p = new Panier();
         p.setId(rs.getInt("id"));
         p.setDate(rs.getDate("date").toLocalDate());
         p.setProduit_s(rs.getInt("produit_s"));
         p.setProduit_r(rs.getInt("produit_r"));
         p.setTransporteurB(true);
//p.setIdUser(rs.getInt("id_user"));
            myList.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
    }
     //Modifier un panier
    /*public void modifierPanier(Panier p){
        Connection conn = getConnection();
        try {
            String requete = "UPDATE panier SET date=? , produit_s=?, produit_r=?, transporteurB=?,  WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, p.getProduitS().);
            pst.setInt(2, p.getProduitR().getId());
            pst.setBoolean(3, p.isAvecTransporteurB());
            pst.setDate(4, java.sql.Date.valueOf(p.getDate()));
            pst.setInt(5, p.getId());
            pst.executeUpdate();
            System.out.println("Panier modifié avec succès!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    //Supprimer un panier
    public void supprimerPanier(int id){
        Connection conn = getConnection();
        try {
            String requete = "DELETE FROM panier WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Panier supprimé avec succès!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }*/
    
    

}  // Autres méthodes de la classe PanierCrud
 

     
    
