/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunitrockhayri;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author kheir
 */
public class AjouterpanierController implements Initializable {

    @FXML
    private Button btnajtaccP3;
    @FXML
    private Button btnajtaccPN3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redbtnajtaccP3(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Gestionproduit1.fxml"));
            Parent root = loader.load();
            btnajtaccP3.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    
    }

    @FXML
    private void redbtnajtaccPN3(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Gestionpanier1.fxml"));
            Parent root = loader.load();
            btnajtaccPN3.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    
    }
    
}
