/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunitrockhayri;

import entities.Panier;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author kheir
 */
public class Gestionpanier1Controller implements Initializable {

    @FXML
    private Button btnproduit1;
    @FXML
    private Button btnajouterpan;
    @FXML
    private Button btnmodifierpan;
    @FXML
    private Button Dashborad;
    @FXML
    private TableColumn<Panier, Integer> produitSColumn;
    @FXML
    private TableColumn<Panier, Integer> produitRColumn;
    @FXML
    private TableColumn<Panier, LocalDate> dateColumn;
    @FXML
    private TableColumn<Panier,Boolean> transporteurBColumn;
    @FXML
    private TableView<Panier> panierTableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherPaniers();
    }    

    @FXML
    private void redbtnproduit1(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Gestionproduit1.fxml"));
            Parent root = loader.load();
            btnproduit1.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    private void redbtnajouterpan(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Ajouterpanier.fxml"));
            Parent root = loader.load();
            btnajouterpan.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
        
    

    @FXML
    private void redbtnmodifierpan(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Modifierpanier.fxml"));
            Parent root = loader.load();
            btnmodifierpan.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    private void redDashborad(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("acceuil1.fxml"));
            Parent root = loader.load();
            Dashborad.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
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
    
    public ObservableList<Panier> getPanierList() {
    ObservableList<Panier> paniersList = FXCollections.observableArrayList();
    Connection conn = getConnection();
    
        String query = "SELECT * FROM panier";
        Statement st ;
        ResultSet rs;
        try {
             st = conn.createStatement();
             rs = st.executeQuery(query);
             Panier  panier;
        while (rs.next()) {
             panier = new Panier(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getInt("produit_s"), rs.getInt("produit_r"), rs.getBoolean("transporteurB"));
            paniersList.add(panier);
                
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return paniersList;
}
    
    
    public void afficherPaniers() {
   ObservableList<Panier> list = getPanierList();

    dateColumn.setCellValueFactory(new PropertyValueFactory<Panier, LocalDate>("date"));
    produitSColumn.setCellValueFactory(new PropertyValueFactory<Panier, Integer>("produit_s"));
    produitRColumn.setCellValueFactory(new PropertyValueFactory<Panier, Integer>("produit_r"));
    transporteurBColumn.setCellValueFactory(new PropertyValueFactory<Panier, Boolean>("transporteurB"));

    panierTableView.setItems(list);
}
    
 }
    

