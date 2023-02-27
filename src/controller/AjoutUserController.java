/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.User;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.CRUDUser;

/**
 * FXML Controller class
 *
 * @author ZeroS TF
 */
public class AjoutUserController implements Initializable {

    public byte[] uploadedImage = null;
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

    @FXML
    private TextField txt_email;
    @FXML
    private PasswordField txt_pwd;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private Button btn_upload;
    @FXML
    private TextField txt_numtel;
    @FXML
    private ComboBox<String> cbx_ville;
    @FXML
    private ComboBox<String> cbx_role;
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
    private ImageView img_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        cbx_ville.setItems(FXCollections.observableArrayList(
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

        cbx_role.setItems(FXCollections.observableArrayList(
                "Admin",
                "Client"));
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
    private void click_ajout(MouseEvent event) throws SQLException {
        CRUDUser sa = new CRUDUser();

        // Get the values from the input fields
        String nom = txt_nom.getText();
        String prenom = txt_prenom.getText();
        String email = txt_email.getText();
        String password = txt_pwd.getText();
        String numTel = txt_numtel.getText();
        String ville = cbx_ville.getSelectionModel().getSelectedItem();
        String role = cbx_role.getSelectionModel().getSelectedItem();
        boolean r;
        if (role == "Admin") {
            r = true;
        } else {
            r = false;
        }

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
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty() || numTel.isEmpty() || ville == null || role == null) {
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
            alert.setTitle("Ajout utilisateur");
            alert.setHeaderText(null);
            alert.setContentText("Utilisateur ajouté avec succès !");
            alert.showAndWait();

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
    private void click_disconnect(MouseEvent event) throws SQLException {
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
    void mEnter(MouseEvent event) {
        Button btn = (Button) event.getSource();
        if (btn.equals(btn_users)) {
            btn_users.setStyle("-fx-background-color: rgb(232, 171, 0); -fx-text-fill: white;");
        } else if (btn.equals(btn_events)) {
            btn_events.setStyle("-fx-background-color: rgb(232, 171, 0); -fx-text-fill: white;");
        }
    }

    @FXML
    void mExit(MouseEvent event) {
        Button btn = (Button) event.getSource();
        if (btn.equals(btn_users)) {
            btn_users.setStyle("-fx-background-color: rgb(252, 215, 69); -fx-text-fill: white;");
        } else if (btn.equals(btn_events)) {
            btn_events.setStyle("-fx-background-color: rgb(252, 215, 69); -fx-text-fill: white;");
        }
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

}
