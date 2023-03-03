/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Evenement;
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
import services.CRUDReclamation;
import services.CRUDUser;

/**
 * FXML Controller class
 *
 * @author Hedi
 */
public class ModifReclamationController implements Initializable {
    public Reclamation rec_e;
    //CONSTANT STUFF TO COPY

    public Reclamation getRec_e() {
        return rec_e;
    }

    public void setRec_e(Reclamation rec_e) {
        this.rec_e = rec_e;
    }

//    public User currentUser;
//    public User getCurrentUser() {
//        return currentUser;
//    }
//
//    public void setCurrentUser(User currentUser) {
//        this.currentUser = currentUser;
//    }
    @FXML
    private ComboBox<String> cbx_expediteur;

    @FXML
    private ComboBox<String> cbx_destinataire;

    @FXML
    private TextArea txt_cause;

    @FXML
    private ComboBox<String> cbx_etat;

    @FXML
    private Button btn_ajout;

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
        String etatStr = cbx_etat.getSelectionModel().getSelectedItem();

        CRUDUser cruduser = new CRUDUser();
        int expediteurId = rec_e.getId_userS();
        int destinataireId = rec_e.getId_userR();
        String cause=rec_e.getCause();

        boolean etat;
        if (etatStr.equals("Traitée")) {
            etat = true;
        } else {
            etat = false;
        }

        Reclamation reclamation = new Reclamation(0, expediteurId, destinataireId, cause, etat);
        CRUDReclamation crudReclamation = new CRUDReclamation();
        crudReclamation.modifierReclamation(reclamation,rec_e.getId());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("La réclamation a été modifiée avec succès !");
        alert.showAndWait();

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
        
        cbx_etat.setItems(FXCollections.observableArrayList(
                "Traitée",
                "En cours"));
        User u=null;
        try {
            u = cruduser.getUserById(rec_e.getId_userS());
        } catch (SQLException ex) {
            Logger.getLogger(ModifReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String fullName = u.getId() + " " + u.getPrenom() + " " + u.getNom();
        cbx_expediteur.setValue(fullName);
        cbx_expediteur.setEditable(false);
        try {
            u=cruduser.getUserById(rec_e.getId_userR());
        } catch (SQLException ex) {
            Logger.getLogger(ModifReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fullName = u.getId() + " " + u.getPrenom() + " " + u.getNom();
        cbx_destinataire.setValue(fullName);
        cbx_destinataire.setEditable(false);
        txt_cause.setText(rec_e.getCause());
        txt_cause.setEditable(false);
        if(rec_e.IsEtat())
        {
            cbx_etat.setValue("Traitée");
        }
        else{
            cbx_etat.setValue("En cours");
        }
        

    }
}
