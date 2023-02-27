/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ZeroS TF
 */
public interface InterfaceCRUDEvenement {
    public void ajouterEvenement(Evenement evenement) throws SQLException;

    public void modifierEvenement(Evenement evenement, int id) throws SQLException;

    public void supprimerEvenement(int id) throws SQLException;

    public List<Evenement> afficherEvenements() throws SQLException;
    
    public boolean existeEvenement(String nom) throws SQLException;
}
