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
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
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
public class ModifUserController implements Initializable {

    //CONSTANT STUFF TO COPY
    public User user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    private Button btn_upload;

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
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private TextField txt_email;
    @FXML
    private PasswordField txt_pwd;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_numtel;
    @FXML
    private ComboBox<String> cbx_ville;
    @FXML
    private ComboBox<String> cbx_role;
    @FXML
    private ComboBox<String> cbx_etat;
    @FXML
    private Button btn_modif;

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

        txt_email.setText(user.getEmail());
        txt_email.setEditable(false);
        txt_pwd.setText(user.getPwd());
        txt_pwd.setEditable(false);
        txt_nom.setText(user.getNom());
        txt_prenom.setText(user.getPrenom());
        txt_numtel.setText(user.getNum_tel());
        cbx_ville.setValue(user.getVille());
        String r = "";
        if (user.isRole()) {
            r = "Admin";
        } else {
            r = "Client";
        }
        cbx_role.setValue(r);
        cbx_etat.setValue(user.getEtat().toString());
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
        cbx_etat.setItems(FXCollections.observableArrayList(
                "ACTIF",
                "INACTIF",
                "BLOQUE",
                "NONBLOQUE",
                "ENATTENTECONFIRMATION"));
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
    private void click_modif(MouseEvent event) throws SQLException {
        CRUDUser sa = new CRUDUser();
// Get the values from the input fields
        String nom = txt_nom.getText();
        String prenom = txt_prenom.getText();
        String email = txt_email.getText();
        String password = txt_pwd.getText();
        String numTel = txt_numtel.getText();
        String ville = cbx_ville.getSelectionModel().getSelectedItem();
        String role = cbx_role.getSelectionModel().getSelectedItem();
        String etat = cbx_etat.getSelectionModel().getSelectedItem();
        boolean r = role.equals("Admin");

// Validate the input values
        boolean inputValid = true;
        String errorMessage = "";

// Check if numTel has 8 digits
        if (numTel.length() != 8 || !numTel.matches("\\d{8}")) {
            inputValid = false;
            errorMessage += "Le champ numéro de téléphone doit être composé de 8 chiffres.\n";
        }

// Check if any field is empty
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty() || numTel.isEmpty() || ville == null || role == null) {
            inputValid = false;
            errorMessage += "Tous les champs doivent être remplis.\n";
        }

        if (inputValid) {
            // Create a new user with the input values
            User user = new User();
            user.setId(this.user.getId());
            user.setEmail(this.user.getEmail());
            user.setPwd(this.user.getPwd());
            user.setNom(nom);
            user.setPrenom(prenom);
            if (uploadedImage == null) {
                user.setPhoto(this.user.getPhoto());
            } else {
                user.setPhoto(uploadedImage);
            }
            user.setNum_tel(numTel);
            user.setVille(ville);
            user.setRole(r);
            user.setEtat(User.EtatUser.valueOf(etat));
            user.setSalt(this.user.getSalt());
            if(etat=="ACTIF"){
            user.setToken(this.user.getToken());
            }
            else{
                user.setToken(null);
            }

            // Show a confirmation message before updating the user
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de modification");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous vraiment modifier cet utilisateur ?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Call the method to update the user in the database
                sa.modifierUser(user, this.user.getEmail());

                // Show a success message
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Modification utilisateur");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Utilisateur modifié avec succès !");
                successAlert.showAndWait();

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
