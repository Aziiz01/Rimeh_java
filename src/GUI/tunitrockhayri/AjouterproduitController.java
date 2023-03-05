/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tunitrockhayri;

import services.ProduitCrud;
import entities.Produit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author kheir
 */
public class AjouterproduitController implements Initializable {
private Image image;
    @FXML
    private Button btnajtaccP2;
    @FXML
    private Button btnajtaccPN2;
    @FXML
    private Button ajtproduit_btn;
    @FXML
    private TextField addproduitlibelle;
    @FXML
    private TextField addproduitville;
    @FXML
    private TextField addproduitnom;
    @FXML
    private TextField addproduitcate;
    @FXML
    private TextField addproduittype;
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

     private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    List<Produit> Produit;
    ProduitCrud pcd = new ProduitCrud();
    @FXML
    private AnchorPane main_form;
    @FXML
    private Button availableBooks_importBtn;
    @FXML
    private ImageView availableBooks_imageView;
    @FXML
    private AreaChart<Produit, String> dashboard_incomeChart;
    
   
    @FXML
    private TableColumn<?, ?> viewlib1;
    @FXML
    private TableColumn<Produit, Integer> viewiduser;
    @FXML
    private ComboBox<String> addproduituser;
    
    public void actualiser(){
        Produit =pcd.affich();
        addproduittable.getItems().setAll(Produit);
    }
    
    
    private HashMap<String, Integer> userMap = new HashMap<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //produitsList = getProduitList();
        showProduit();
        //dashboardIncomeChart();
    ObservableList<String> userNameList = FXCollections.observableArrayList();
Connection conn = getConnection();
String query = "SELECT * FROM user";
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
addproduituser.setItems(userNameList);

    }    

    @FXML
    private void redbtnajtaccP2(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Gestionproduit1.fxml"));
            Parent root = loader.load();
            btnajtaccPN2.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    
    }

    @FXML
    private void redbtnajtaccPN2(ActionEvent event) {
try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Gestionpanier1.fxml"));
            Parent root = loader.load();
            btnajtaccP2.getScene().setRoot(root);
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
    viewiduser.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id_user"));

    addproduittable.setItems(list);
}
      
    private void reset1() {
addproduitlibelle.clear();

addproduitville.clear();

addproduitcate.clear();

addproduittype.clear();

addproduitnom.clear();


    }
 
    
 

// ...


    
    
    
    
    
    
    @FXML
private void ajouterproduit(javafx.event.ActionEvent event) {
try {
ProduitCrud pc = new ProduitCrud();
Produit p = new Produit();
p.setId(0);
p.setType(addproduittype.getText());
p.setCategorie(addproduitcate.getText());
p.setNom(addproduitnom.getText());
p.setLibelle(addproduitlibelle.getText());
p.setPhoto("sszsss");
p.setVille(addproduitville.getText());
        
        // obtenir l'ID utilisateur sélectionné à partir de la ComboBox
    String userName = addproduituser.getSelectionModel().getSelectedItem();
    int userId = userMap.get(userName);
    p.setId_user(userId);
    pc.ajouterproduit(p, userId);
    actualiser();
    reset1();
        System.out.println("ajouté");
    } catch(Exception ex) {
        System.out.println(ex);
    }
}


    


    
}
