/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Fidelite;
import entities.Reclamation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Hedi
 */
public interface InterfaceCRUDFidelite {
    
    public void ajouterFidelite(Fidelite fidelite) throws SQLException;

    public void modifierFidelite(Fidelite fidelite , int id) throws SQLException;

    public void supprimerFidelite(int id) throws SQLException;

    public List<Fidelite> afficherFidelites() throws SQLException;
    
}
