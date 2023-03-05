/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Echange;
import entities.User;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.CRUDEchange;
import services.CRUDUser;
import static tunitroc.Tunitroc.connectDb;

/**
 * FXML Controller class
 *
 * @author ZeroS TF
 */
public class ModifEchangeController implements Initializable {

     //CONSTANT STUFF TO COPY
    public Echange echange_e;
    public int i;
    public CRUDUser cr7=new CRUDUser();
    public User currentUser;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
     public Echange getEchange_e() {
        return echange_e;
    }

    public void setEchange_e(Echange echange_e) {
        this.echange_e = echange_e;
    }

    @FXML
    private Label label_nomUser;

    @FXML
    private Button btn_users;

    @FXML
    private Button btn_events;

    @FXML
    private Button btn_disconnect;
    @FXML
    private ImageView img_user;
    
    @FXML
    private Button btn_echanges;

    @FXML
    private Button btn_transporteurs;
    @FXML
    private Button btn_modif;
    
    @FXML
    private ComboBox<Integer> cbx_panier;

    @FXML
    private ComboBox<String> cbx_etat;

    @FXML
    private ComboBox<Integer> cbx_transp;

    @FXML
    void click_modif(MouseEvent event) throws SQLException {
int id_panier = cbx_panier.getValue();
String etat = cbx_etat.getValue();
int id_transporteur = cbx_transp.getValue();

// Vérification que tous les champs sont remplis
if (etat.isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Tous les champs sont obligatoires");
    alert.showAndWait();
    return;
}




// Demander une confirmation avant la modification
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Voulez-vous vraiment modifier cet échange ?");
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Echange newEchange = new Echange(id_panier,etat,id_transporteur);
            CRUDEchange crudEchange = new CRUDEchange();
            crudEchange.modifierEchange(newEchange, echange_e.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Echange modifié avec succès");
            alert.showAndWait();

            TableEchangeController tableEchangeController = new TableEchangeController();
            tableEchangeController.setI(i);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableEchange.fxml"));

                // set the controller instance
                loader.setController(tableEchangeController);

                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setOnCloseRequest(e -> {
                
                try {
                    CRUDUser cr7=new CRUDUser();
                    cr7.logout(currentUser.getEmail()); // Appelle la fonction supp()
                } catch (SQLException ex) {
                    Logger.getLogger(TableUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
                stage.show();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }

    @FXML
    void click_disconnect(MouseEvent event) throws SQLException {
        CRUDUser sa = new CRUDUser();
        sa.logout(currentUser.getEmail());
        LoginUIController loginUIController = new LoginUIController();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/LoginUI.fxml"));

            // set the controller instance
            loader.setController(loginUIController);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setOnCloseRequest(e -> {
                
                try {
                    CRUDUser cr7=new CRUDUser();
                    cr7.logout(currentUser.getEmail()); // Appelle la fonction supp()
                } catch (SQLException ex) {
                    Logger.getLogger(TableUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void click_echanges(MouseEvent event) {
        TableEchangeController tableEchangeController = new TableEchangeController();
        tableEchangeController.setI(i);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableEchange.fxml"));

            // set the controller instance
            loader.setController(tableEchangeController);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setOnCloseRequest(e -> {
                
                try {
                    CRUDUser cr7=new CRUDUser();
                    cr7.logout(currentUser.getEmail()); // Appelle la fonction supp()
                } catch (SQLException ex) {
                    Logger.getLogger(TableUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void click_transporteurs(MouseEvent event) {

    }

    @FXML
    void click_users(MouseEvent event) {
        TableUserController tableUserController = new TableUserController();
        tableUserController.setI(i);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableUser.fxml"));

            // set the controller instance
            loader.setController(tableUserController);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setOnCloseRequest(e -> {
                
                try {
                    CRUDUser cr7=new CRUDUser();
                    cr7.logout(currentUser.getEmail()); // Appelle la fonction supp()
                } catch (SQLException ex) {
                    Logger.getLogger(TableUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void click_events(MouseEvent event) {
        TableEventController tableEventController = new TableEventController();
        tableEventController.setI(i);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableEvent.fxml"));

            // set the controller instance
            loader.setController(tableEventController);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setOnCloseRequest(e -> {
                
                try {
                    CRUDUser cr7=new CRUDUser();
                    cr7.logout(currentUser.getEmail()); // Appelle la fonction supp()
                } catch (SQLException ex) {
                    Logger.getLogger(TableUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void mEnter(MouseEvent event) {
        Button btn = (Button) event.getSource();
            btn.setStyle("-fx-background-color: rgb(232, 171, 0); -fx-text-fill: white;");
    }

    @FXML
    void mExit(MouseEvent event) {
        Button btn = (Button) event.getSource();
            btn.setStyle("-fx-background-color: rgb(252, 215, 69); -fx-text-fill: white;");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

Connection con = connectDb();
       
        try {
            currentUser=cr7.getUserById(i);
        } catch (SQLException ex) {
            Logger.getLogger(AjoutEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        label_nomUser.setText(currentUser.getPrenom() + " " + currentUser.getNom());
        InputStream inputStream = new ByteArrayInputStream(currentUser.getPhoto());
        Image image = new Image(inputStream);
        img_user.setImage(image);
        img_user.setPreserveRatio(true);
        
       // cbx_panier.setItems(FXCollections.observableArrayList(
          //      ""));
           try {
              ResultSet rs= con.createStatement().executeQuery("select id_panier from echange");
              ObservableList list_p = FXCollections.observableArrayList();
              while(rs.next()){
list_p.add(Integer.valueOf(rs.getString("id_panier"))); 
              } 
              cbx_panier.setItems(list_p);
          } catch(NumberFormatException | SQLException exception){
            }
        cbx_etat.setItems(FXCollections.observableArrayList(
                "par defaut", "Archivé", "Non Archivé"));
        try {
              ResultSet rs= con.createStatement().executeQuery("select id from transporteur");
              ObservableList list_t = FXCollections.observableArrayList();
              while(rs.next()){
list_t.add(Integer.valueOf(rs.getString("id"))); 
              } 
              cbx_transp.setItems(list_t);
          } catch(NumberFormatException | SQLException exception){
            }
        
    
    }

}
