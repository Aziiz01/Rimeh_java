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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
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
import services.CRUDReclamation;
import services.CRUDUser;

/**
 * FXML Controller class
 *
 * @author ZeroS TF
 */
public class TableReclamationController implements Initializable {

//    public User currentUser;
//
//    public User getCurrentUser() {
//        return currentUser;
//    }
//
//    public void setCurrentUser(User currentUser) {
//        this.currentUser = currentUser;
//    }
    
    @FXML
    private TextField searchBox;
    
    @FXML
    private Button btn_disconnect;

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
    private TableView<Reclamation> table_reclamations;

    @FXML
    private TableColumn<Reclamation, String> id_rec;

    @FXML
    private TableColumn<Reclamation, String> expediteur_rec;

    @FXML
    private TableColumn<Reclamation, String> dest_rec;

    @FXML
    private TableColumn<Reclamation, String> cause_rec;

    @FXML
    private TableColumn<Reclamation, String> etat_rec;

    @FXML
    private Button btn_ajout_rec;

    @FXML
    private Button btn_supp_rec;

    @FXML
    private Button btn_modif_rec;
    
    @FXML
    private Button btn_stat;
    
    @FXML
    private Button btn_export;
    
    @FXML
    void click_export(MouseEvent event) {
        List<Reclamation> items = table_reclamations.getItems();
        CRUDReclamation c= new CRUDReclamation();
    try {
        c.export(items);
        // Show a success message to the user
    } catch (Exception e) {
        // Show an error message to the user
        e.printStackTrace();
    }
    }
    
    @FXML
    void click_stat(MouseEvent event) {
        StatistiqueController statcontroller = new StatistiqueController();
        //ajoutreccontroller.setCurrentUser(currentUser);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Statistique.fxml"));

            // set the controller instance
            loader.setController(statcontroller);

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
    void click_ajout_reclamation(MouseEvent event) {
        AjoutReclamationController ajoutreccontroller = new AjoutReclamationController();
        //ajoutreccontroller.setCurrentUser(currentUser);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutReclamation.fxml"));

            // set the controller instance
            loader.setController(ajoutreccontroller);

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
    void click_fids(MouseEvent event) {
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
    void click_modifier_reclamation(MouseEvent event) {
         Reclamation selectedRec = table_reclamations.getSelectionModel().getSelectedItem();
        if (selectedRec == null) {
            // Aucun utilisateur sélectionné, afficher un message d'avertissement
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune reclamation sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une reclamation à modifier.");
            alert.showAndWait();
        } else {
            ModifReclamationController modifreccontroller = new ModifReclamationController();
            //modifusercontroller.setCurrentUser(currentUser);

            modifreccontroller.setRec_e(selectedRec);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifReclamation.fxml"));

                // set the controller instance
                loader.setController(modifreccontroller);

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

    @FXML
    void click_recs(MouseEvent event) {
        //        label_nomUser.setText(currentUser.getPrenom() + " " + currentUser.getNom());
//        InputStream inputStream = new ByteArrayInputStream(currentUser.getPhoto());
//        Image image = new Image(inputStream);
//        img_user.setImage(image);
//        img_user.setPreserveRatio(true);
        CRUDReclamation sa = new CRUDReclamation();

        List<Reclamation> reclamationsListFromDatabase = null;
        try {
            reclamationsListFromDatabase = sa.afficherReclamations();
        } catch (SQLException ex) {
            Logger.getLogger(TableReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Reclamation> reclamationList = FXCollections.observableArrayList();
        reclamationList.addAll(reclamationsListFromDatabase);

        id_rec.setCellValueFactory(new PropertyValueFactory<>("id"));
        expediteur_rec.setCellValueFactory(new PropertyValueFactory<>("id_userS"));
        dest_rec.setCellValueFactory(new PropertyValueFactory<>("id_userR"));
        cause_rec.setCellValueFactory(new PropertyValueFactory<>("cause"));
        etat_rec.setCellValueFactory(cellData -> {
            Boolean isEtat = cellData.getValue().IsEtat();
            String etatS = isEtat ? "Traitée" : "En cours";
            return new ReadOnlyStringWrapper(etatS);
        });
        table_reclamations.setItems(reclamationList);
    }

    @FXML
    void click_supprimer_reclamation(MouseEvent event) {
        // Récupération de la reclamation sélectionnée
        Reclamation selectedRec = table_reclamations.getSelectionModel().getSelectedItem();
        if (selectedRec == null) {
            // Aucun utilisateur sélectionné, afficher un message d'avertissement
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune reclamation sélectionnée");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une reclamation à supprimer.");
            alert.showAndWait();
        } else {
            // Afficher une boîte de dialogue de confirmation avant de supprimer la reclamation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer la reclamation sélectionnée ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    CRUDReclamation cr = new CRUDReclamation();
                    cr.supprimerReclamation(selectedRec.getId());
                    table_reclamations.getItems().remove(selectedRec);
                    Alert confirmation = new Alert(Alert.AlertType.INFORMATION, "La reclamation a été supprimée avec succès.");
                    confirmation.showAndWait();
                } catch (SQLException e) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Une erreur s'est produite lors de la suppression de la reclamation.");
                    error.showAndWait();
                    e.printStackTrace();
                }
            }
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
    public void initialize(URL location, ResourceBundle resources) {
//        label_nomUser.setText(currentUser.getPrenom() + " " + currentUser.getNom());
//        InputStream inputStream = new ByteArrayInputStream(currentUser.getPhoto());
//        Image image = new Image(inputStream);
//        img_user.setImage(image);
//        img_user.setPreserveRatio(true);

        CRUDReclamation sa = new CRUDReclamation();
        CRUDUser cruduser = new CRUDUser();

        List<Reclamation> reclamationsListFromDatabase = null;
        try {
            reclamationsListFromDatabase = sa.afficherReclamations();
        } catch (SQLException ex) {
            Logger.getLogger(TableReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Reclamation> reclamationList = FXCollections.observableArrayList();
        reclamationList.addAll(reclamationsListFromDatabase);
        id_rec.setCellValueFactory(new PropertyValueFactory<>("id"));
        expediteur_rec.setCellValueFactory(cellData -> {
            int userId = cellData.getValue().getId_userS();
            User user = new User();
            try {
                user = cruduser.getUserById(userId);
            } catch (SQLException ex) {
                Logger.getLogger(TableReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String fullName = user.getId()+" "+user.getPrenom() + " " + user.getNom();
            return new SimpleStringProperty(fullName);
        });
        dest_rec.setCellValueFactory(cellData -> {
            int userId = cellData.getValue().getId_userR();
            User user = new User();
            try {
                user = cruduser.getUserById(userId);
            } catch (SQLException ex) {
                Logger.getLogger(TableReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String fullName = user.getId()+" "+user.getPrenom() + " " + user.getNom();
            return new SimpleStringProperty(fullName);
        });
        cause_rec.setCellValueFactory(new PropertyValueFactory<>("cause"));
        etat_rec.setCellValueFactory(cellData -> {
            Boolean isEtat = cellData.getValue().IsEtat();
            String etatS = isEtat ? "Traitée" : "En cours";
            return new ReadOnlyStringWrapper(etatS);
        });
        table_reclamations.setItems(reclamationList);
        
        // Set up search box listener
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase();
            if (searchText.isEmpty()) {
                // If search box is empty, show all items
                table_reclamations.setItems(reclamationList);
            } else {
                // Filter the list based on the search text
                ObservableList<Reclamation> filteredList = FXCollections.observableArrayList();
                for (Reclamation reclamation : reclamationList) {
                    if (expediteur_rec.getCellData(reclamation).toLowerCase().contains(searchText)
                            || dest_rec.getCellData(reclamation).toLowerCase().contains(searchText)
                            || etat_rec.getCellData(reclamation).toLowerCase().contains(searchText)) {
                        filteredList.add(reclamation);
                    }
                }
                table_reclamations.setItems(filteredList);
            }
        });
    }
}
