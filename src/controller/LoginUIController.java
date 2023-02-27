/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import entities.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import services.CRUDUser;

/**
 * FXML Controller class
 *
 * @author ZeroS TF
 */
public class LoginUIController implements Initializable {

    public User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @FXML
    private Button Button_se_connecter;
    @FXML
    private TextField TF_Email_login;
    @FXML
    private TextField TF_Paswword_login;
    @FXML
    private Button button_inscrire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ON_seconnecter_clicked(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.NONE);
        String email = TF_Email_login.getText();
        String password = TF_Paswword_login.getText();
        CRUDUser sa = new CRUDUser();
        User user = sa.getUserByEmail(email);
        System.out.println("logging in...");
        if (sa.login(email, password)) {
            if (user.isRole()) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Admin connecté");
                alert.show();

                TableUserController tableUserController = new TableUserController();
                tableUserController.setI(user.getId());

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
                            sa.logout(user.getEmail()); // Appelle la fonction supp()
                        } catch (SQLException ex) {
                            Logger.getLogger(TableUserController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    stage.show();

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Utilisateur connecté");
                alert.show();
                //TODO PARTIE CLIENT DE L'APPLICATION//////////////////////
                //////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////

            }
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Email ou Mot de passe incorrect");
            alert.show();
            System.out.println("erreur login.");
        }
    }

    @FXML
    private void ON_inscrire_clicked(ActionEvent event) {
        InscriptionController crl = new InscriptionController();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Inscription.fxml"));

            // set the controller instance
            loader.setController(crl);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
