package utils;

import java.sql.*;

public class DBConnection {

    private Connection conn;
    String url = "jdbc:mysql://localhost:3306/tunitroc";
    String user = "root";
    String pwd = "";
    private static DBConnection instance;

    private DBConnection() {
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie!!!");
        } catch (SQLException ex) {
            System.out.println("probleme de Connexion");
        }
    }

    public static DBConnection getInstance() {
        if (DBConnection.instance == null) {
            DBConnection.instance = new DBConnection();
        }
        return DBConnection.instance;
    }

    public static Connection getConnection() {
        return DBConnection.getInstance().conn;
    }
}