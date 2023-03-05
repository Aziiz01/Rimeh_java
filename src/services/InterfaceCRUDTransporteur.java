/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Transporteur;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author azizn
 */
public interface InterfaceCRUDTransporteur {
    public void ajouterTransporteur(Transporteur transporteur) throws SQLException;

    public void modifierTransporteur(Transporteur transporteur, int id) throws SQLException;

    public void supprimerTransporteur(int id) throws SQLException;

    public List<Transporteur> afficherTransporteurs() throws SQLException;
    
}
