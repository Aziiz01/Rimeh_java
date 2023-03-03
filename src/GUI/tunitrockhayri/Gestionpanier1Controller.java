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
public class Gestionpanier1Controller implements Initializable {

    @FXML
    private Button btnproduit1;
    @FXML
    private Button btnajouterpan;
    @FXML
    private Button btnmodifierpan;
    @FXML
    private Button Dashborad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redbtnproduit1(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Gestionproduit1.fxml"));
            Parent root = loader.load();
            btnproduit1.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    private void redbtnajouterpan(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Ajouterpanier.fxml"));
            Parent root = loader.load();
            btnajouterpan.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
        
    

    @FXML
    private void redbtnmodifierpan(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Modifierpanier.fxml"));
            Parent root = loader.load();
            btnmodifierpan.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    @FXML
    private void redDashborad(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("acceuil1.fxml"));
            Parent root = loader.load();
            Dashborad.getScene().setRoot(root);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }
    
 }
    

