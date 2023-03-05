/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author azizn
 */
import entities.Transporteur;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.CRUDTransporteur;
import services.CRUDUser;

public class ModifTransporteurController implements Initializable {

    public User user;
    public Transporteur transp_t;
     public byte[] uploadedImage = null;
    public int i;
    public CRUDUser cr7=new CRUDUser();
    public User currentUser;

      public Transporteur getTransp_t() {
        return transp_t;
    }

    public void setTransp_t(Transporteur transp_t) {
        this.transp_t = transp_t;
    }
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
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;

    @FXML
    private Button btn_upload;

    @FXML
    private TextField txt_numtel;

    @FXML
    private Button btn_modif;

    @FXML
    private Button btn_disconnect;

    @FXML
    private ImageView img_user;

    @FXML
    private Label label_nomUser;

    @FXML
    private Button btn_users;

    @FXML
    private Button btn_events;

    @FXML
    private Button btn_echanges;

    @FXML
    private Button btn_transporteurs;

    

  

    @FXML
    void click_events(MouseEvent event) {
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
    void click_modif(MouseEvent event) throws SQLException {
        CRUDTransporteur sa = new CRUDTransporteur();
// Get the values from the input fields
        String nom = txt_nom.getText();
        String prenom = txt_prenom.getText();
String numTelString = txt_numtel.getText();
// Validate the input values
boolean inputValid = true;
String errorMessage = "";

// Check if numTel has 8 digits
int numTel = 0;
try {
    numTel = Integer.parseInt(numTelString);
    if (numTelString.length() != 8) {
        throw new NumberFormatException();
    }
} catch (NumberFormatException e) {
    inputValid = false;
    errorMessage += "Le champ numéro de téléphone doit être composé de 8 chiffres.\n";
}

// Check if any field is empty
if (nom.isEmpty() || prenom.isEmpty() || numTelString.isEmpty()) {
    inputValid = false;
    errorMessage += "Tous les champs doivent être remplis.\n";
}

if (inputValid) {
    // Create a new user with the input values
    Transporteur transporteur = new Transporteur();
    transporteur.setId(this.transp_t.getId());
    transporteur.setNom(nom);
    transporteur.setPrenom(prenom);
    if (uploadedImage == null) {
        transporteur.setPhoto(this.transp_t.getPhoto());
    } else {
        transporteur.setPhoto(uploadedImage);
    }
    transporteur.setNum_tel(numTel);

    // Show a confirmation message before updating the user
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de modification");
    alert.setHeaderText(null);
    alert.setContentText("Voulez-vous vraiment modifier ce transporteur ?");
    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Call the method to update the user in the database
        sa.modifierTransporteur(transporteur, this.transp_t.getId());

        // Show a success message
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Modification Transporteur");
        successAlert.setHeaderText(null);
        successAlert.setContentText("transporteur modifié avec succès !");
        successAlert.showAndWait();

        TableTransporteurController tabletranspController = new TableTransporteurController();
        tabletranspController.setI(i);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableTransporteur.fxml"));

            // set the controller instance
            loader.setController(tabletranspController);

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
    void mEnter(MouseEvent event) {
        Button btn = (Button) event.getSource();
            btn.setStyle("-fx-background-color: rgb(232, 171, 0); -fx-text-fill: white;");
    }

    @FXML
    void mExit(MouseEvent event) {
        Button btn = (Button) event.getSource();
            btn.setStyle("-fx-background-color: rgb(252, 215, 69); -fx-text-fill: white;");

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
    void click_transporteurs(MouseEvent event) throws SQLException {
TableTransporteurController tabletransporteurController = new TableTransporteurController();
        tabletransporteurController.setI(i);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableTransporteur.fxml"));

            // set the controller instance
            loader.setController(tabletransporteurController);

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

        txt_nom.setText(transp_t.getNom());
        txt_prenom.setText(transp_t.getPrenom());
      //  txt_numtel.setText(transp_t.getNum_tel());
       
         }

}
