/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.CRUDUser;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class InscriptionController implements Initializable {
    
    public byte[] uploadedImage = null;
    
    

    @FXML
    private TextField TF_email;

    @FXML
    private PasswordField TF_mdp;

    @FXML
    private TextField TF_nom;

    @FXML
    private TextField TF_prenom;

    @FXML
    private Button btn_upload;

    @FXML
    private TextField TF_num;

    @FXML
    private ComboBox<String> villes_box;

    @FXML
    private Button Button_inscrire;

    @FXML
    private Button Button_retour;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        villes_box.setItems(FXCollections.observableArrayList(
                "Ariana",
                "Beja",
                "Ben Arous",
                "Bizerte",
                "Gabes",
                "Gafsa",
                "Jendouba",
                "Kairouan",
                "Kasserine",
                "Kebili",
                "Kef",
                "Mahdia",
                "Manouba",
                "Médenine",
                "Monastir",
                "Nabeul",
                "Sfax",
                "Sidi Bouzid",
                "Siliana",
                "Sousse",
                "Tataouine",
                "Tozeur",
                "Tunis",
                "Zaghouan"));
    }

    @FXML
    void click_ajout(MouseEvent event) throws SQLException{
        CRUDUser sa = new CRUDUser();

        // Get the values from the input fields
        String nom = TF_nom.getText();
        String prenom = TF_prenom.getText();
        String email = TF_email.getText();
        String password = TF_mdp.getText();
        String numTel = TF_num.getText();
        String ville = villes_box.getSelectionModel().getSelectedItem();
        boolean r=false;

        // Validate the input values
        boolean inputValid = true;
        String errorMessage = "";

        // Check if email is in a valid format
        if (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
            inputValid = false;
            errorMessage += "Le champ email doit être au format d'un email valide.\n";
        }

        // Check if email is already in use
        if (sa.emailExists(email)) {
            inputValid = false;
            errorMessage += "Cet email est déjà utilisé par un autre utilisateur.\n";
        }

        // Check if numTel has 8 digits
        if (numTel.length() != 8 || !numTel.matches("\\d{8}")) {
            inputValid = false;
            errorMessage += "Le champ numéro de téléphone doit être composé de 8 chiffres.\n";
        }

        // Check if password has at least 6 characters
        if (password.length() < 6) {
            inputValid = false;
            errorMessage += "Le champ mot de passe doit contenir au moins 6 caractères.\n";
        }

        // Check if any field is empty
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty() || numTel.isEmpty() || ville == null) {
            inputValid = false;
            errorMessage += "Tous les champs doivent être remplis.\n";
        }

        if (inputValid) {
            // Create a new user with the input values
            User user = new User(email, password, nom, prenom, uploadedImage, numTel, ville, 0, r);

            // Call the method to add the user to the database
            sa.ajouterUser(user);

            // Show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Création de compte");
            alert.setHeaderText(null);
            alert.setContentText("Compte créé avec succés !");
            alert.showAndWait();

            LoginUIController logc = new LoginUIController();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/LoginUI.fxml"));

                // set the controller instance
                loader.setController(logc);

                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Scene scene = new Scene(root);

                stage.setScene(scene);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            // Show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }

    }

    @FXML
    private void click_upload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(btn_upload.getScene().getWindow());
        if (selectedFile != null) {
            try {
                uploadedImage = Files.readAllBytes(selectedFile.toPath());
                // Do something with the image bytes (e.g. pass them to a method that saves them to the database)
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void ON_retour_clicked(MouseEvent event) {
        LoginUIController logc = new LoginUIController();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/LoginUI.fxml"));

                // set the controller instance
                loader.setController(logc);

                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Scene scene = new Scene(root);

                stage.setScene(scene);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

}
