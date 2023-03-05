/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Echange;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ZeroS TF
 */
public interface InterfaceCRUDEchange {
    public void ajouterEchange(Echange echange) throws SQLException;

    public void modifierEchange(Echange echange, int id) throws SQLException;

    public void supprimerEchange(int id) throws SQLException;

    public List<Echange> afficherEchanges() throws SQLException;
}
