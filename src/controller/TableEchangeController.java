/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.BaseColor;
import entities.Echange;
import entities.User;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
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
import services.CRUDEchange;
import services.CRUDUser;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import utils.DBConnection;

/**
 * FXML Controller class
 *
 * @author ZeroS TF
 */
public class TableEchangeController implements Initializable {

    public int i;
    public CRUDUser cr7=new CRUDUser();
    public User currentUser;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    
    private ObservableList<Echange> echangeList = FXCollections.observableArrayList();

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
    private Button btn_pdf;

    @FXML
    private Button btn_events;

    @FXML
    private Button btn_echanges;

    @FXML
    private Button btn_transporteurs;
    
    @FXML
    private Button btn_archive;

    @FXML
    private TableView<Echange> table_echanges;

    @FXML
    private TableColumn<Echange, String> id_echange;
    
    @FXML
    private TableColumn<Echange, String> panier_echange;

    @FXML
    private TableColumn<Echange, String> etat_echange;

    @FXML
    private TableColumn<Echange, String> transp_echange;

    @FXML
    private TextField searchBox;

    @FXML
    private Button btn_ajout_echange;

    @FXML
    private Button btn_supprimer;

    @FXML
    private Button btn_modifier;

    @FXML
    void click_ajout(MouseEvent event) {
        AjoutEchangeController ctrl = new AjoutEchangeController();
        ctrl.setI(i);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjoutEchange.fxml"));

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
    void click_modif(MouseEvent event) {
       // ModifEchangeController ctrl = new ModifEchangeController();
       // ctrl.setI(i);

        Echange selectedEchange = table_echanges.getSelectionModel().getSelectedItem();
        if (selectedEchange == null) {
            // Aucun evenement sélectionné, afficher un message d'avertissement
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun échange sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un échange à modifier.");
            alert.showAndWait();
        } else {
            ModifEchangeController modifechangecontroller = new ModifEchangeController();
            modifechangecontroller.setI(i);
            modifechangecontroller.setEchange_e(selectedEchange);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ModifEchange.fxml"));

                // set the controller instance
                loader.setController(modifechangecontroller);

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
    void click_supp(MouseEvent event) {
        Echange selectedEchange = table_echanges.getSelectionModel().getSelectedItem();
        if (selectedEchange == null) {
            // Aucun utilisateur sélectionné, afficher un message d'avertissement
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun échange sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un échange à supprimer.");
            alert.showAndWait();
        } else {
            // Afficher une boîte de dialogue de confirmation avant de supprimer l'utilisateur
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer l'échange sélectionné ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    CRUDEchange cr = new CRUDEchange();
                    cr.supprimerEchange(selectedEchange.getId());
                    table_echanges.getItems().remove(selectedEchange);
                    Alert confirmation = new Alert(Alert.AlertType.INFORMATION, "L'échange a été supprimé avec succès.");
                    confirmation.showAndWait();
                } catch (SQLException e) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Une erreur s'est produite lors de la suppression de l'échange.");
                    error.showAndWait();
                    e.printStackTrace();
                }
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
 void archive(MouseEvent event) throws IOException {
    Parent archiveParent = FXMLLoader.load(getClass().getResource("/GUI/TableArchive.fxml"));
    Scene archiveScene = new Scene(archiveParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(archiveScene);
    window.show();
}


 @FXML
void PDF(MouseEvent event) {
    Connection TuniTrocDB = DBConnection.getConnection();
    try {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:/Users/azizn/OneDrive/Documents/pdfs/pdfEchange.pdf"));
        document.open();

        // Add the title to the document
        Paragraph title = new Paragraph("Liste des échanges", new Font(FontFamily.HELVETICA, 14, Font.BOLDITALIC, BaseColor.RED));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add some spacing between the title and the table
        document.add(new Paragraph(" "));

        // Create a table with four columns
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        // Add column headers to the table
        PdfPCell cellId = new PdfPCell(new Phrase("ID", new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED)));
        PdfPCell cellEtat = new PdfPCell(new Phrase("Etat", new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED)));
        PdfPCell cellIdPanier = new PdfPCell(new Phrase("ID Panier", new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED)));
        PdfPCell cellTransporteur = new PdfPCell(new Phrase("Transporteur", new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED)));

        table.addCell(cellId);
        table.addCell(cellEtat);
        table.addCell(cellIdPanier);
        table.addCell(cellTransporteur);

        // Retrieve data from the database
        Statement stmt = TuniTrocDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM echange");

        // Loop through the data and add it to the table
        while (rs.next()) {
            int id = rs.getInt("id");
            String etat = rs.getString("etat");
            int id_panier = rs.getInt("id_panier");
            int transporteur = rs.getInt("id_transporteur");

            // Add data to the table
            table.addCell(new PdfPCell(new Phrase(String.valueOf(id))));
            table.addCell(new PdfPCell(new Phrase(etat)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(id_panier))));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(transporteur))));
        }

        // Add the table to the document
        document.add(table);

        document.close();

        // Open the PDF file
        File file = new File("C:/Users/azizn/OneDrive/Documents/pdfs/pdfEchange.pdf");
        if (file.exists()) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Desktop is not supported");
            }
        } else {
            System.out.println("File does not exist");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


  
    
    
    
    
    
   @Override
public void initialize(URL location, ResourceBundle resources) {
    try {
        currentUser = cr7.getUserById(i);
    } catch (SQLException ex) {
        Logger.getLogger(AjoutEventController.class.getName()).log(Level.SEVERE, null, ex);
    }

    label_nomUser.setText(currentUser.getPrenom() + " " + currentUser.getNom());
    InputStream inputStream = new ByteArrayInputStream(currentUser.getPhoto());
    Image image = new Image(inputStream);
    img_user.setImage(image);
    img_user.setPreserveRatio(true);

    CRUDEchange sa = new CRUDEchange();

    List<Echange> echangesListFromDatabase = null;
    try {
        echangesListFromDatabase = sa.afficherEchanges();
    } catch (SQLException ex) {
        Logger.getLogger(TableEchangeController.class.getName()).log(Level.SEVERE, null, ex);
    }
    ObservableList<Echange> echList = FXCollections.observableArrayList();
    echList.addAll(echangesListFromDatabase);

    id_echange.setCellValueFactory(new PropertyValueFactory<>("id"));
    panier_echange.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
    etat_echange.setCellValueFactory(new PropertyValueFactory<>("etat"));
    transp_echange.setCellValueFactory(new PropertyValueFactory<>("id_transporteur"));

    table_echanges.setItems(echList);

    // Add listener to the search box
    searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue.trim().isEmpty()) { // Show all echanges if search box is empty
            echList.clear();
            try {
                echList.addAll(sa.afficherEchanges());
            } catch (SQLException ex) {
                Logger.getLogger(TableEchangeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { // Call recherche method to show filtered echanges
            int id_e = Integer.parseInt(newValue);
            List<Echange> filteredList = null;
            try {
                filteredList = sa.recherche(id_e);
            } catch (SQLException ex) {
                Logger.getLogger(TableEchangeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            echList.clear();
            echList.addAll(filteredList);
        }
    });
}

}
