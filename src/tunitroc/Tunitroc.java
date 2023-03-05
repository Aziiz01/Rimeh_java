/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunitroc;

import entities.Evenement;
import entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import javax.swing.JOptionPane;
import services.CRUDEvenement;
import services.CRUDUser;
import utils.DBConnection;

/**
 *
 * @author ZeroS TF
 */
public class Tunitroc {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
       DBConnection TuniTrocDB = DBConnection.getInstance();
        Connection con = null;
        public static Connection connectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/tunitroc", "root", "");
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
          
                }
    
   