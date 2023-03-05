/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.tunitrockhayri;

import services.ProduitCrud;
import entities.Produit;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author kheir
 */
public class ProduitinterfaceController implements Initializable {


    @FXML
    private javafx.scene.control.Button addproduit_btn;

    
    @FXML
    private javafx.scene.control.TextField addproduitlibelle;

    @FXML
    private javafx.scene.control.TextField addproduittype;


    @FXML
    private javafx.scene.control.TextField addproduitville;

    @FXML
    private javafx.scene.control.Button ajtproduit_btn;

    @FXML
    private javafx.scene.control.Button restproduit_btn;


    @FXML
    private javafx.scene.control.Button modproduit_btn;
    

    @FXML
    private TableView<Produit> addproduittable;

    private TableColumn<Produit, String> addproduittabletitle;

    private TableColumn<Produit, String> addproduittabletype;

    private TableColumn<Produit, String> addproduittablecate;

    private TableColumn<Produit, String> addproduittableville;
    
    
    private TableColumn<Produit, String> addproduittablelibelle;
    


    @FXML
    private javafx.scene.control.TextField searchproduit;
    @FXML
    private ImageView addproduitview;
    @FXML
    private javafx.scene.control.TextField addproduitcate;
    @FXML
    private javafx.scene.control.Button boutique_btn;
    @FXML
    private javafx.scene.control.Button produi_btn;
    @FXML
    private javafx.scene.control.Button panier_btn;
    @FXML
    private javafx.scene.control.TextField addproduitnom;
    @FXML
    private TableColumn<Produit, String> viewtype;
    @FXML
    private TableColumn<Produit, String> viewcateg;
    @FXML
    private TableColumn<Produit, String> viewville;
    @FXML
    private TableColumn<Produit, String> viewlib;
    @FXML
    private TableColumn<Produit, String> viewnom;
    @FXML
    private javafx.scene.control.Button supproduit_btn;
    @FXML
    private javafx.scene.control.Button quitter;
    
    private void handleButtonAction(ActionEvent event) {        
        
        
            
    }
    List<Produit> Produit;
    ProduitCrud pcd = new ProduitCrud();
    
    public void actualiser(){
        Produit =pcd.affich();
        addproduittable.getItems().setAll(Produit);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //showBooks();
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
    
     public void showProduit(){
    ObservableList<Produit> list = getProduitList();
    
    viewtype.setCellValueFactory(new PropertyValueFactory<Produit, String>("type"));
    viewcateg.setCellValueFactory(new PropertyValueFactory<Produit, String>("categorie"));
    viewnom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
    viewville.setCellValueFactory(new PropertyValueFactory<Produit, String>("ville"));
    viewlib.setCellValueFactory(new PropertyValueFactory<Produit, String>("libelle"));

    addproduittable.setItems(list);
}
    

    @FXML
    /*private void reset(javafx.event.ActionEvent event) {
        addproduitlibelle.clear();

addproduitville.clear();

addproduitcate.clear();

addproduittype.clear();

addproduitnom.clear();
    }*/
    private void reset1() {
        addproduitlibelle.clear();

addproduitville.clear();

addproduitcate.clear();

addproduittype.clear();

addproduitnom.clear();
    }

    /*@FXML
    private void ajouterproduit(javafx.event.ActionEvent event) {
        try{ ProduitCrud pc = new ProduitCrud();
        Produit p =new Produit();  
        p.setId(0);
        p.setType(addproduittype.getText());
        p.setCategorie(addproduitcate.getText());
        p.setNom(addproduitnom.getText());
        p.setLibelle(addproduitlibelle.getText());
        p.setPhoto("sszsss");
         //p.setPhoto(addproduitview.file_path());
        
        p.setVille(addproduitville.getText());
        pc.ajouterproduit(p);
        actualiser();
        reset1();
        System.out.println("ajouté");}
        catch(Exception ex){
            System.out.println(ex);
       }
            
    }

    @FXML
    private void supprimerProduit(javafx.event.ActionEvent event) {
       ProduitCrud  eccd = new  ProduitCrud ();
        Produit p;   
        p= addproduittable.getSelectionModel().getSelectedItem();
        eccd.supprimerProduit(p);
        actualiser();
        //Alert alert = new Alert(Alert.AlertType.ERROR);
         
        
    
        
        
        
    }

    /*@FXML
    private void modifierProduit(javafx.event.ActionEvent event) {
        ProduitCrud pc = new ProduitCrud();
        
        Produit p ;
        p=addproduittable.getSelectionModel().getSelectedItem();
        p.setType(addproduittype.getText());
        p.setCategorie(addproduitcate.getText());
        p.setNom(addproduitnom.getText());
        p.setLibelle(addproduitlibelle.getText());
        
        p.setVille(addproduitville.getText());
        pc.modifierProduit(p,addproduittype.getText(),addproduitcate.getText(),addproduitnom.getText(),"azeee",addproduitville.getText());
        actualiser();
        reset1();
    }*/

    @FXML
    private void redirection(javafx.event.ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            Parent root = loader.load();
            quitter.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    
    
    
}
