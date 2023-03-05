/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Evenement;
import entities.Fidelite;
import entities.Reclamation;
import entities.User;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.CRUDEvenement;
import services.CRUDFidelite;
import services.CRUDReclamation;
import services.CRUDUser;

/**
 * FXML Controller class
 *
 * @author Hedi
 */
public class ModifFideliteController implements Initializable {
    public Fidelite fid_e;
    //CONSTANT STUFF TO COPY

    public Fidelite getFid_e() {
        return fid_e;
    }

    public void setFid_e(Fidelite fid_e) {
        this.fid_e = fid_e;
    }
    //CONSTANT STUFF TO COPY

//    public User currentUser;
//    public User getCurrentUser() {
//        return currentUser;
//    }
//
//    public void setCurrentUser(User currentUser) {
//        this.currentUser = currentUser;
//    }
   @FXML
    private ComboBox<String> cbx_utilisateur;

    @FXML
    private TextField txt_valeur;

    @FXML
    private Button btn_modif;

    @FXML
    private Button btn_disconnect1;

    @FXML
    private Label label_nomUser;

    @FXML
    private Button btn_users;

    @FXML
    private Button btn_events;

    @FXML
    private Button btn_reclamations;

    @FXML
    private Button btn_fidelites;

    @FXML
    void click_modif(MouseEvent event) throws SQLException {
        String utilisateur = cbx_utilisateur.getSelectionModel().getSelectedItem();
        String valeur = txt_valeur.getText();

        if (utilisateur == null || valeur == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        CRUDUser cruduser = new CRUDUser();
        int utilId = Integer.parseInt(utilisateur.split(" ")[0]);
        int val = Integer.parseInt(valeur);
        User util = cruduser.getUserById(utilId);

        Fidelite fidelite = new Fidelite(0, val, utilId);
        CRUDFidelite crudFidelite = new CRUDFidelite();
        crudFidelite.modifierFidelite(fidelite,fid_e.getId());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("La fidelité a été ajoutée avec succès !");
        alert.showAndWait();

        TableFideliteController tableFidController = new TableFideliteController();
        //tableRecController.setCurrentUser(currentUser);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableFidelite.fxml"));

            // set the controller instance
            loader.setController(tableFidController);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void click_disconnect(MouseEvent event) {

    }

    @FXML
    void click_events(MouseEvent event) {

    }

    @FXML
    void click_fidelite(MouseEvent event) {
        TableFideliteController tableFideliteController = new TableFideliteController();
        //tableFideliteController.setCurrentUser(currentUser);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableFidelite.fxml"));

            // set the controller instance
            loader.setController(tableFideliteController);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void click_reclamation(MouseEvent event) {
        TableReclamationController tableRecController = new TableReclamationController();
        //tableRecController.setCurrentUser(currentUser);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableReclamation.fxml"));

            // set the controller instance
            loader.setController(tableRecController);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void click_users(MouseEvent event) {

    }

    @FXML
    void mEnter(MouseEvent event) {
        Button btn = (Button) event.getSource();
        if (btn.equals(btn_reclamations)) {
            btn_reclamations.setStyle("-fx-background-color: rgb(232, 171, 0); -fx-text-fill: white;");
        } else if (btn.equals(btn_fidelites)) {
            btn_fidelites.setStyle("-fx-background-color: rgb(232, 171, 0); -fx-text-fill: white;");
        }
    }

    @FXML
    void mExit(MouseEvent event) {
        Button btn = (Button) event.getSource();
        if (btn.equals(btn_reclamations)) {
            btn_reclamations.setStyle("-fx-background-color: rgb(252, 215, 69); -fx-text-fill: white;");
        } else if (btn.equals(btn_fidelites)) {
            btn_fidelites.setStyle("-fx-background-color: rgb(252, 215, 69); -fx-text-fill: white;");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        label_nomUser.setText(currentUser.getPrenom() + " " + currentUser.getNom());
//        InputStream inputStream = new ByteArrayInputStream(currentUser.getPhoto());
//        Image image = new Image(inputStream);
//        img_user.setImage(image);
//        img_user.setPreserveRatio(true);
        CRUDUser cruduser = new CRUDUser();
        User u=null;
        try {
            u = cruduser.getUserById(fid_e.getId_user());
        } catch (SQLException ex) {
            Logger.getLogger(ModifReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String fullName = u.getId() + " " + u.getPrenom() + " " + u.getNom();
        cbx_utilisateur.setValue(fullName);
        cbx_utilisateur.setEditable(false);
        txt_valeur.setText(Integer.toString(fid_e.getValeur()));
    }
}
