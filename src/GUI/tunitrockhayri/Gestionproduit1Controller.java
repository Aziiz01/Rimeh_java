/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.tunitrockhayri;

import services.ProduitCrud;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author kheir
 */
public class Gestionproduit1Controller implements Initializable {

    @FXML
    private Button btnpanier1;
    @FXML
    private Button btnajouterprod;
    @FXML
    private Button btnmodifierprod;
    @FXML
    private Button Dashborad1;

   
    @FXML
    private TableView<Produit> addproduittable;
    @FXML
    private TableColumn<Produit, String> viewtype;
    @FXML
    private TableColumn<Produit, String> viewcateg;
    @FXML
    private TableColumn<Produit, String> viewnom;
    @FXML
    private TableColumn<Produit, String> viewlib;
    @FXML
    private TableColumn<Produit, String> viewville;
    
    
    List<Produit> Produit;
    ProduitCrud pcd = new ProduitCrud();
    @FXML
    private TextField searchField;
    @FXML
    private TableColumn<Produit, Integer> viewiduser;
    
    public void actualiser(){
        Produit =pcd.affich();
        addproduittable.getItems().setAll(Produit);
    }
    private ObservableList<Produit> produitsList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    produitsList = getProduitList();

       showProduit();       
    FilteredList<Produit> filteredList = new FilteredList<>(produitsList, p -> true);
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        filterProduits(filteredList, newValue);
        addproduittable.setItems(filteredList);
    });
       
     
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
    
    public ObservableList<Produit> getProduitList(){
    ObservableList<Produit> produitsList = FXCollections.observableArrayList();
    Connection conn = getConnection();
    String query = "SELECT * FROM produit";
    Statement st;
    ResultSet rs;

    try{
        st = conn.createStatement();
        rs = st.executeQuery(query);
        Produit produit;
        while(rs.next()){
            produit = new Produit(rs.getInt("id"), rs.getString("type"), rs.getString("categorie"), rs.getString("nom"), rs.getString("libelle"), rs.getString("photo"), rs.getString("ville"), rs.getInt("id_user"));
            produitsList.add(produit);
        }
    } catch(Exception ex){
        ex.printStackTrace();
    }
    return produitsList;
}
        private ObservableList<Produit> list;

  public void showProduit(){
    ObservableList<Produit> list = getProduitList();
    
    viewtype.setCellValueFactory(new PropertyValueFactory<Produit, String>("type"));
    viewcateg.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie"));
    viewnom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
    viewville.setCellValueFactory(new PropertyValueFactory<Produit, String>("ville"));
    viewlib.setCellValueFactory(new PropertyValueFactory<Produit, String>("libelle"));
    viewiduser.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id_user"));

    addproduittable.setItems(list);
}
    
    
    @FXML
    private void Redbtnpanier1(ActionEvent event) {
         try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Gestionpanier1.fxml"));
            Parent root = loader.load();
            btnpanier1.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    private void redbtnajouterprod(ActionEvent event) {
         try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Ajouterproduit.fxml"));
            Parent root = loader.load();
            btnajouterprod.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    private void redbtnmodifierprod(ActionEvent event) {
         try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Modifierproduit.fxml"));
            Parent root = loader.load();
            btnmodifierprod.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    private void redDashborad1(ActionEvent event) {
         try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("acceuil1.fxml"));
            Parent root = loader.load();
            Dashborad1.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
    

private void filterProduits(FilteredList<Produit> filteredList, String searchValue) {
    filteredList.setPredicate(produit -> {
        if (searchValue == null || searchValue.isEmpty()) {
            return true;
        }
        String lowerCaseFilter = searchValue.toLowerCase();
        if (produit.getNom().toLowerCase().contains(lowerCaseFilter)) {
            return true; // filtre sur le nom du produit
        } else if (produit.getType().toLowerCase().contains(lowerCaseFilter)) {
            return true; // filtre sur le type du produit
        }
        return false; // ne correspond pas au filtre
    });
}

    

   

   
    
}
