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
import services.CRUDFidelite;
import services.CRUDReclamation;
import services.CRUDUser;

/**
 * FXML Controller class
 *
 * @author hedi
 */
public class TableFideliteController implements Initializable {

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
    private TableView<Fidelite> table_fidelites;

    @FXML
    private TableColumn<Fidelite, String> id_fid;

    @FXML
    private TableColumn<Fidelite, String> valeur_fid;

    @FXML
    private TableColumn<Fidelite, String> user_fid;

    @FXML
    private Button btn_ajout_fid;

    @FXML
    private Button btn_supp_fid;

    @FXML
    private Button btn_modif_fid;

    @FXML
    void click_ajout_fidelite(MouseEvent event) {
        AjoutFideliteController ajoutfidcontroller = new AjoutFideliteController();
        //ajoutreccontroller.setCurrentUser(currentUser);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutFidelite.fxml"));

            // set the controller instance
            loader.setController(ajoutfidcontroller);

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
        CRUDFidelite sa = new CRUDFidelite();

        List<Fidelite> fidelitesListFromDatabase = null;
        try {
            fidelitesListFromDatabase = sa.afficherFidelites();
        } catch (SQLException ex) {
            Logger.getLogger(TableFideliteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Fidelite> fideliteList = FXCollections.observableArrayList();
        fideliteList.addAll(fidelitesListFromDatabase);

        id_fid.setCellValueFactory(new PropertyValueFactory<>("id"));
        valeur_fid.setCellValueFactory(new PropertyValueFactory<>("valeur"));
        user_fid.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        table_fidelites.setItems(fideliteList);

    }

    @FXML
    void click_modifier_fidelite(MouseEvent event) {
        Fidelite selectedFid = table_fidelites.getSelectionModel().getSelectedItem();
        if (selectedFid == null) {
            // Aucun fidelité sélectionné, afficher un message d'avertissement
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune fidelité sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une fidelité à modifier.");
            alert.showAndWait();
        } else {
            ModifFideliteController modiffidcontroller = new ModifFideliteController();
            //modifusercontroller.setCurrentUser(currentUser);

            modiffidcontroller.setFid_e(selectedFid);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifFidelite.fxml"));

                // set the controller instance
                loader.setController(modiffidcontroller);

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
        TableReclamationController tableReclamationController = new TableReclamationController();
        //tableReclamationController.setCurrentUser(currentUser);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/TableReclamation.fxml"));

            // set the controller instance
            loader.setController(tableReclamationController);

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
    void click_supprimer_fidelite(MouseEvent event) {
        // Récupération de la fidelité sélectionnée
        Fidelite selectedFid = table_fidelites.getSelectionModel().getSelectedItem();
        if (selectedFid == null) {
            // Aucune fidelité sélectionnée, afficher un message d'avertissement
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune fidelité sélectionnée");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une fidelité à supprimer.");
            alert.showAndWait();
        } else {
            // Afficher une boîte de dialogue de confirmation avant de supprimer la reclamation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer la fidelité sélectionnée ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    CRUDFidelite cr = new CRUDFidelite();
                    cr.supprimerFidelite(selectedFid.getId());
                    table_fidelites.getItems().remove(selectedFid);
                    Alert confirmation = new Alert(Alert.AlertType.INFORMATION, "La fidelité a été supprimée avec succès.");
                    confirmation.showAndWait();
                } catch (SQLException e) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Une erreur s'est produite lors de la suppression de la fidelité.");
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

        CRUDFidelite sa = new CRUDFidelite();
        CRUDUser cruduser = new CRUDUser();

        List<Fidelite> fidelitesListFromDatabase = null;
        try {
            fidelitesListFromDatabase = sa.afficherFidelites();
        } catch (SQLException ex) {
            Logger.getLogger(TableFideliteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Fidelite> fideliteList = FXCollections.observableArrayList();
        fideliteList.addAll(fidelitesListFromDatabase);
        id_fid.setCellValueFactory(new PropertyValueFactory<>("id"));
        valeur_fid.setCellValueFactory(new PropertyValueFactory<>("valeur"));
        user_fid.setCellValueFactory(cellData -> {
            int userId = cellData.getValue().getId_user();
            User user = new User();
            try {
                user = cruduser.getUserById(userId);
            } catch (SQLException ex) {
                Logger.getLogger(TableReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String fullName = user.getId() + " " + user.getPrenom() + " " + user.getNom();
            return new SimpleStringProperty(fullName);
        });
        table_fidelites.setItems(fideliteList);
        // Set up search box listener
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchText = newValue.toLowerCase();
            if (searchText.isEmpty()) {
                // If search box is empty, show all items
                table_fidelites.setItems(fideliteList);
            } else {
                // Filter the list based on the search text
                ObservableList<Fidelite> filteredList = FXCollections.observableArrayList();
                for (Fidelite fidelite : fideliteList) {
                    if (    String.valueOf(fidelite.getValeur()).toLowerCase().contains(searchText)
                            || user_fid.getCellData(fidelite).toLowerCase().contains(searchText)) {
                        filteredList.add(fidelite);
                    }
                }
                table_fidelites.setItems(filteredList);
            }
        });
    }
}
