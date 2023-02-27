/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Evenement;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.CRUDEvenement;
import services.CRUDUser;

/**
 * FXML Controller class
 *
 * @author ZeroS TF
 */
public class TableEventController implements Initializable {

    public int i;
    public CRUDUser cr7=new CRUDUser();
    public User currentUser;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    
    private ObservableList<Evenement> eventList = FXCollections.observableArrayList();

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }


    @FXML
    private ImageView img_user;

    @FXML
    private Label label_nomUser;

    @FXML
    private Button btn_users;

    @FXML
    private Button btn_events;

    @FXML
    private Button btn_ajout_event;

    @FXML
    private Button btn_modifier;

    @FXML
    private Button btn_disconnect;

    @FXML
    private Button btn_supprimer;

    @FXML
    private TableView<Evenement> table_events;

    @FXML
    private TableColumn<Evenement, String> id_event;

    @FXML
    private TableColumn<Evenement, String> nom_event;

    @FXML
    private TableColumn<Evenement, String> description_event;

    @FXML
    private TableColumn<Evenement, String> dd_event;

    @FXML
    private TableColumn<Evenement, String> df_event;
    
    @FXML
    private TextField searchBox;

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
    void click_events(MouseEvent event) {
        CRUDEvenement sa = new CRUDEvenement();

        List<Evenement> eventsListFromDatabase = null;
        try {
            eventsListFromDatabase = sa.afficherEvenements();
        } catch (SQLException ex) {
            Logger.getLogger(TableEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Evenement> eventList = FXCollections.observableArrayList();
        eventList.addAll(eventsListFromDatabase);

        id_event.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_event.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description_event.setCellValueFactory(new PropertyValueFactory<>("description"));
        dd_event.setCellValueFactory(new PropertyValueFactory<>("date_d"));
        df_event.setCellValueFactory(new PropertyValueFactory<>("date_f"));

        table_events.setItems(eventList);
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
    void click_modif_event(MouseEvent event) {
         Evenement selectedEvent = table_events.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            // Aucun evenement sélectionné, afficher un message d'avertissement
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun événement sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un événement à modifier.");
            alert.showAndWait();
        } else {
            ModifEventController modifeventcontroller = new ModifEventController();
            modifeventcontroller.setI(i);
            modifeventcontroller.setEvent_e(selectedEvent);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifEvent.fxml"));

                // set the controller instance
                loader.setController(modifeventcontroller);

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
    void click_supp_event(MouseEvent event) {
        // Récupération de l'utilisateur sélectionné
        Evenement selectedEvent = table_events.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            // Aucun utilisateur sélectionné, afficher un message d'avertissement
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun événement sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un événement à supprimer.");
            alert.showAndWait();
        } else {
            // Afficher une boîte de dialogue de confirmation avant de supprimer l'utilisateur
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer l'événement sélectionné ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    CRUDEvenement cr = new CRUDEvenement();
                    cr.supprimerEvenement(selectedEvent.getId());
                    table_events.getItems().remove(selectedEvent);
                    Alert confirmation = new Alert(Alert.AlertType.INFORMATION, "L'événement a été supprimé avec succès.");
                    confirmation.showAndWait();
                } catch (SQLException e) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Une erreur s'est produite lors de la suppression de l'événement.");
                    error.showAndWait();
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void click_ajout_event(MouseEvent event) {
        AjoutEventController ajouteventcontroller = new AjoutEventController();
        ajouteventcontroller.setI(i);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutEvent.fxml"));

            // set the controller instance
            loader.setController(ajouteventcontroller);

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
    public void initialize(URL location, ResourceBundle resources) {
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

        CRUDEvenement sa = new CRUDEvenement();

        List<Evenement> eventsListFromDatabase = null;
        try {
            eventsListFromDatabase = sa.afficherEvenements();
        } catch (SQLException ex) {
            Logger.getLogger(TableEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Evenement> eventList = FXCollections.observableArrayList();
        eventList.addAll(eventsListFromDatabase);

        id_event.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_event.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description_event.setCellValueFactory(new PropertyValueFactory<>("description"));
        dd_event.setCellValueFactory(new PropertyValueFactory<>("date_d"));
        df_event.setCellValueFactory(new PropertyValueFactory<>("date_f"));

        table_events.setItems(eventList);
        
        //RECHERCHE//////////////////////////
        // Set up search box listener
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                eventList.clear();
                eventList.addAll(sa.recherche(newValue));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
