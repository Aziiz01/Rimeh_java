/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Hedi
 */
public interface InterfaceCRUDReclamation {
    public void ajouterReclamation(Reclamation reclamation) throws SQLException;

    public void modifierReclamation(Reclamation reclamation, int id) throws SQLException;

    public void supprimerReclamation(int id) throws SQLException;

    public List<Reclamation> afficherReclamations() throws SQLException;
}
