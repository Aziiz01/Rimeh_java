/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.tunitrockhayri;

import services.PanierCrud;
import services.ProduitCrud;
import entities.Panier;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author kheir
 */
public class AjouterpanierController implements Initializable {

    @FXML
    private Button btnajtaccP3;
    @FXML
    private Button btnajtaccPN3;
    @FXML
     private ComboBox<String> produitSBox;

    @FXML
    private ComboBox<String> produitRBox;

    private ComboBox<String> transporteurBox;
    @FXML
    private DatePicker dateBox;
    @FXML
    private TableView<Panier> panierTableView;
private TableColumn<Panier, Integer> idColumn;
    @FXML
    private TableColumn<Panier, Integer> produitSColumn;
    @FXML
    private TableColumn<Panier, Integer> produitRColumn;
    @FXML
    private TableColumn<Panier, Boolean> transporteurBColumn;
    @FXML
    private TableColumn<Panier, LocalDate> dateColumn;
    @FXML
    private Button ajouterButton;
    @FXML
    private CheckBox transporteurBCheckBox;

    /**
     * Initializes the controller class.
     */
     List<Panier> Panier;
    PanierCrud pcd = new PanierCrud();
     
    
    
   public void actualiser(){
        Panier =pcd.affich();
        panierTableView.getItems().setAll(Panier);
    }  
    
    
   private HashMap<String, Integer> userMap = new HashMap<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherPaniers();
        ObservableList<String> userNameList = FXCollections.observableArrayList();
Connection conn = getConnection();
String query = "SELECT * FROM produit";
Statement st;
ResultSet rs;

try{
    st = conn.createStatement();
    rs = st.executeQuery(query);
    while(rs.next()){
        int id = rs.getInt("id");
        String nom = rs.getString("nom");
        userNameList.add(nom);
        userMap.put(nom, id);
    }
} catch(Exception ex){
    ex.printStackTrace();
} 
produitSBox.setItems(userNameList);
produitRBox.setItems(userNameList);
    }    

    @FXML
    private void redbtnajtaccP3(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Gestionproduit1.fxml"));
            Parent root = loader.load();
            btnajtaccP3.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    
    }

    @FXML
    private void redbtnajtaccPN3(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Gestionpanier1.fxml"));
            Parent root = loader.load();
            btnajtaccPN3.getScene().setRoot(root);
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
            
            //p.setId(rs.getInt("id"));
           /*p.setDate(rs.getDate("date").toLocalDate());
            p.setProduit_s(rs.getInt("produit_s"));
            p.setProduit_r(rs.getInt("produit_r"));  
            p.setTransporteurB(rs.getBoolean("transporteur_b"));
            paniersList.add(panier);*/
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

     /* @FXML
       private void ajouterPanier(javafx.event.ActionEvent event) {
        Produit produitS = produitSBox.getValue();
        Produit produitR = produitRBox.getValue();
        LocalDate date = dateBox.getValue();
        boolean transporteurB = transporteurBCheckBox.isSelected();
        PanierCrud panierCrud = new PanierCrud();
        panierCrud.ajouterPanier(produitS.getId(), produitR.getId(), date, transporteurB);
        // actualiser le contenu de la tableview des paniers
        afficherPaniers();
    }*/       



private void reset1() {
    
    dateBox.setValue(null);
    produitSBox.setValue(null);
    produitRBox.setValue(null);
    transporteurBCheckBox.setSelected(false);
}
 


     @FXML
private void ajouterPanier(javafx.event.ActionEvent event) {
try {
PanierCrud pc = new PanierCrud();
Panier p = new Panier();
p.setDate(dateBox.getValue());
//p.setDate(LocalDate.valueOf(dateBox.getValue().toString()));

p.setTransporteurB(transporteurBCheckBox.isSelected());

           // obtenir l'ID utilisateur sélectionné à partir de la ComboBox
    String produitName = produitSBox.getSelectionModel().getSelectedItem();
    String produitName2 = produitRBox.getSelectionModel().getSelectedItem();
    int produitId = userMap.get(produitName);
    int produitId1 = userMap.get(produitName2);
    p.setProduit_s(produitId);
    p.setProduit_r(produitId1);
    pc.ajouterPanier(p,dateBox.getValue(), produitId, produitId1, transporteurBCheckBox.isSelected());
    
           
    actualiser();
    reset1();
        System.out.println("ajouté");
    } catch(Exception ex) {
        System.out.println(ex);
    }
}   
       
       
       
       
       
       
       
       
       
       
       
       
       



}