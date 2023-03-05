/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.tunitrockhayri;
import javafx.scene.input.MouseEvent;
import services.ProduitCrud;
import entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author kheir
 */
public class ModifierproduitController implements Initializable {

    @FXML
    private Button btnmdfaccP;
    @FXML
    private Button btnmdfaccPN;
    @FXML
    private Button addproduit_btn;
    @FXML
    private Button modproduit_btn;
    @FXML
    private Button supprimer_btn;
    private TextField addproduitlibelle;
    private TextField addproduitville;
    private TextField addproduitnom;
    private TextField addproduitcate;
    private TextField addproduittype;
    
    @FXML
    private TableColumn<Produit, String > viewtype;
    @FXML
    private TableColumn<Produit, String> viewcateg;
    @FXML
    private TableColumn<Produit, String> viewnom;
    @FXML
    private TableColumn<Produit, String> viewlib;
    @FXML
    private TableColumn<Produit, String> viewville;
    @FXML
    private TableView<Produit> addproduittable;

    /**
     * Initializes the controller class.
     */
    
    
    List<Produit> Produit;
    ProduitCrud pcd = new ProduitCrud();
    @FXML
    private TableColumn<Produit, Integer> viewuser;
    @FXML
    private ComboBox<String> addproduituser;
    @FXML
    private TextField libelleCol;
    @FXML
    private TextField villeCol;
    @FXML
    private TextField nomCol;
    @FXML
    private TextField cateCol;
    @FXML
    private TextField typeCol;
   
    
    public void actualiser(){
        Produit =pcd.affich();
        addproduittable.getItems().setAll(Produit);
    }
        private HashMap<String, Integer> userMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showProduit();
        
    }    

    @FXML
    private void redbtnmdfaccP(ActionEvent event) {
         try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Gestionproduit1.fxml"));
            Parent root = loader.load();
            btnmdfaccP.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    private void redbtnmdfaccPN(ActionEvent event) {
         try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Gestionpanier1.fxml"));
            Parent root = loader.load();
            btnmdfaccPN.getScene().setRoot(root);
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
    viewuser.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id_user"));

    addproduittable.setItems(list);
}

    private void reset1() {
        libelleCol.clear();

        villeCol.clear();

        cateCol.clear();

        typeCol.clear();

        nomCol.clear();
    } 
    



@FXML
    private void supprimerProduit(javafx.event.ActionEvent event) {
       ProduitCrud  eccd = new  ProduitCrud ();
        Produit p;   
        p= addproduittable.getSelectionModel().getSelectedItem();
        eccd.supprimerProduit(p);
        actualiser();  
    }




 @FXML
    private void modifierProduit(javafx.event.ActionEvent event) {
        ProduitCrud pc = new ProduitCrud();
        
        Produit p ;
        p=addproduittable.getSelectionModel().getSelectedItem();
        p.setType(typeCol.getText());
        p.setCategorie(cateCol.getText());
        p.setNom(nomCol.getText());
        p.setLibelle(libelleCol.getText());
        
        p.setVille(villeCol.getText());
        pc.modifierProduit(p,typeCol.getText(),cateCol.getText(),nomCol.getText(),"azeee",villeCol.getText());
        actualiser();
        reset1();
    }

}



    


