/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Echange;
import entities.Transporteur;
import entities.User;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.CRUDTransporteur;
import services.CRUDUser;

/**
 *
 * @author azizn
 */
public class TableTransporteurController implements Initializable {

    

     public int i;
    public CRUDUser cr7=new CRUDUser();
    public User currentUser;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    
    private ObservableList<Transporteur> TranspList = FXCollections.observableArrayList();

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

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
    private TableView<Transporteur> table_transp;

    @FXML
    private TableColumn<Transporteur, String> id_transporteur;

    @FXML
    private TableColumn<Transporteur, String> nom_transporteur;

    @FXML
    private TableColumn<Transporteur, String> prenom_transporteur;

    @FXML
    private TableColumn<Transporteur, byte[]> photo_transporteur;

    @FXML
    private TableColumn<Transporteur, String> num_transporteur;

    @FXML
    private TextField searchBox;

    @FXML
    private Button btn_ajouter;

    @FXML
    private Button btn_supprimer;

    @FXML
    private Button btn_modifier;

    @FXML
    void click_ajout_transp(MouseEvent event) {
 AjoutTransporteurController ctrl = new AjoutTransporteurController();
        ctrl.setI(i);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutTransporteur.fxml"));

            // set the controller instance
            loader.setController(ctrl);

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setOnCloseRequest(e -> {
                
                try {
                    CRUDUser cr7=new CRUDUser();
                    cr7.logout(currentUser.getEmail()); 
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
                    cr7.logout(currentUser.getEmail()); 
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
    void click_modif_transp(MouseEvent event) {
 Transporteur selectedTransp = table_transp.getSelectionModel().getSelectedItem();
        if (selectedTransp == null) {
            // Aucun evenement sélectionné, afficher un message d'avertissement
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun transporteur sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un transporteur à modifier.");
            alert.showAndWait();
        } else {
            ModifTransporteurController modiftransporteurcontroller = new ModifTransporteurController();
            modiftransporteurcontroller.setI(i);
            modiftransporteurcontroller.setTransp_t(selectedTransp);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifTransporteur.fxml"));

                // set the controller instance
                loader.setController(modiftransporteurcontroller);

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
    void click_supp_transp(MouseEvent event) {
 Transporteur selectedTransporteur = table_transp.getSelectionModel().getSelectedItem();
        if (selectedTransporteur == null) {
            // Aucun utilisateur sélectionné, afficher un message d'avertissement
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun transporteur sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un transporteur à supprimer.");
            alert.showAndWait();
        } else {
            // Afficher une boîte de dialogue de confirmation avant de supprimer l'utilisateur
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer le transporteur sélectionné ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    CRUDTransporteur cr = new CRUDTransporteur();
                    cr.supprimerTransporteur(selectedTransporteur.getId());
                    table_transp.getItems().remove(selectedTransporteur);
                    Alert confirmation = new Alert(Alert.AlertType.INFORMATION, "Le transporteur a été supprimé avec succès.");
                    confirmation.showAndWait();
                } catch (SQLException e) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Une erreur s'est produite lors de la suppression de le transporteur.");
                    error.showAndWait();
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void click_transporteurs(MouseEvent event) {
TableTransporteurController tableTranspController = new TableTransporteurController();
        tableTranspController.setI(i);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableTransporteur.fxml"));

            // set the controller instance
            loader.setController(tableTranspController);

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



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            CRUDUser cr7 = new CRUDUser();
            currentUser = cr7.getUserById(i);
        } catch (SQLException ex) {
            Logger.getLogger(AjoutEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        label_nomUser.setText(currentUser.getPrenom() + " " + currentUser.getNom());
        InputStream inputStream = new ByteArrayInputStream(currentUser.getPhoto());
        Image image = new Image(inputStream);
        img_user.setImage(image);
        img_user.setPreserveRatio(true);

        CRUDTransporteur sa = new CRUDTransporteur();

        List<Transporteur> transporteurListFromDatabase = null;
        try {
            transporteurListFromDatabase = sa.afficherTransporteurs();
        } catch (SQLException ex) {
            Logger.getLogger(TableUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Transporteur> TransporteurList = FXCollections.observableArrayList();
        TransporteurList.addAll(transporteurListFromDatabase);

        id_transporteur.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_transporteur.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_transporteur.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       photo_transporteur.setCellValueFactory(new PropertyValueFactory<>("photo"));
      photo_transporteur.setCellFactory(column -> {
    return new TableCell<Transporteur, byte[]>() {
        private final ImageView imageView = new ImageView();

        protected void updateItem(byte[] item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            } else {
                // Load the image and resize it
                Image image = new Image(new ByteArrayInputStream(item));
                imageView.setImage(image);
                imageView.setFitWidth(50); // set the width of the ImageView
                imageView.setFitHeight(50); // set the height of the ImageView
                setGraphic(imageView);
            }
        }
    };
});

       
      


        num_transporteur.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        
        table_transp.setItems(TransporteurList);

          // Add listener to the search box
    searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue.trim().isEmpty()) { // Show all echanges if search box is empty
            TransporteurList.clear();
            try {
                TransporteurList.addAll(sa.afficherTransporteurs());
            } catch (SQLException ex) {
                Logger.getLogger(TableTransporteurController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { // Call recherche method to show filtered echanges
String nom_t = String.valueOf(newValue);
            List<Transporteur> filteredList = null;
            try {
                filteredList = sa.recherchee(nom_t);
            } catch (SQLException ex) {
                Logger.getLogger(TableTransporteurController.class.getName()).log(Level.SEVERE, null, ex);
            }
            TransporteurList.clear();
            TransporteurList.addAll(filteredList);
        }
    });

    }
}

