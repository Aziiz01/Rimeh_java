package services;

import entities.User;
import java.util.List;
import java.sql.SQLException;

public interface InterfaceCRUDUser {

    public void ajouterUser(User user) throws SQLException;

    public void modifierUser(User user, String email) throws SQLException;

    public void supprimerUser(String email) throws SQLException;

    public List<User> afficherUsers() throws SQLException;
    
    public boolean emailExists(String email) throws SQLException;

}
