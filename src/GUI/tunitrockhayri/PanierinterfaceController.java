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
public class PanierinterfaceController implements Initializable {

    @FXML
    private Button boutique_btn;
    @FXML
    private Button produi_btn;
    @FXML
    private Button panier_btn;
    @FXML
    private Button quitterp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirectionAcceuil(ActionEvent event) {
        
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            Parent root = loader.load();
            quitterp.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
    
}
