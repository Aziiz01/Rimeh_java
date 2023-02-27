/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.twilio.Twilio;
import entities.User;
import java.net.PasswordAuthentication;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import utils.DBConnection;

/**
 *
 * @author ZeroS TF
 */
public class CRUDUser implements InterfaceCRUDUser {

    Connection TuniTrocDB = DBConnection.getConnection();

    @Override
    public void ajouterUser(User user) throws SQLException {

        // generating a random string (salt)
        byte[] chars = new byte[7];
        new Random().nextBytes(chars);
        String salt = new String(chars, Charset.forName("US-ASCII"));
        user.setSalt(salt);

        PreparedStatement stmt = TuniTrocDB.prepareStatement("INSERT INTO user(email,pwd, nom, prenom, photo, num_tel, ville, valeur_fidelite, role, salt, token, etat) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, user.getEmail());
        stmt.setString(2, hashPassword(user.getPwd(), salt));
        stmt.setString(3, user.getNom());
        stmt.setString(4, user.getPrenom());
        stmt.setBytes(5, user.getPhoto());
        stmt.setString(6, user.getNum_tel());
        stmt.setString(7, user.getVille());
        stmt.setInt(8, user.getValeur_fidelite());
        stmt.setBoolean(9, user.isRole());
        stmt.setString(10, salt);
        stmt.setString(11, null);
        stmt.setString(12, User.EtatUser.INACTIF.toString());
        stmt.executeUpdate();
        // Envoyer un email à l'utilisateur
        String subject = "Bienvenue sur TuniTroc";
        String body = "Cher/Chère " + user.getPrenom() + ",\n\nBienvenue sur TuniTroc !\n\nVotre compte a été créé avec succès.\n\nMeilleures salutations,\nL'équipe de TuniTroc";
        sendEmail(user.getEmail(), subject, body);
    }

    @Override
    public void modifierUser(User user, String email) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("UPDATE user SET email=?, pwd=?, nom=?, prenom=?, photo=?, num_tel=?, ville=?, valeur_fidelite=?, role=?, salt=?, token=?, etat=? WHERE email=?");
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getPwd());
        stmt.setString(3, user.getNom());
        stmt.setString(4, user.getPrenom());
        stmt.setBytes(5, user.getPhoto());
        stmt.setString(6, user.getNum_tel());
        stmt.setString(7, user.getVille());
        stmt.setInt(8, user.getValeur_fidelite());
        stmt.setBoolean(9, user.isRole());
        stmt.setString(10, user.getSalt());
        stmt.setString(11, user.getToken());
        stmt.setString(12, user.getEtat().toString());
        stmt.setString(13, email);
        stmt.executeUpdate();
    }

    @Override
    public void supprimerUser(String email) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("DELETE FROM user WHERE email=?");
        stmt.setString(1, email);
        stmt.executeUpdate();
    }

    @Override
    public List<User> afficherUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Statement stmt = TuniTrocDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
        while (rs.next()) {
            userList.add(getUserFromResultSet(rs));
        }
        System.out.println(userList);
        return userList;
    }

    public User getUserByEmail(String email) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("SELECT * FROM user WHERE email=?");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return getUserFromResultSet(rs);
        } else {
            return null;
        }
    }

    public User getUserById(int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("SELECT * FROM user WHERE id=?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return getUserFromResultSet(rs);
        } else {
            return null;
        }
    }

    public boolean login(String email, String password) throws SQLException {
        User user = getUserByEmail(email);
        if (user != null && user.getEtat() != User.EtatUser.ACTIF) {
            String hashedPassword = hashPassword(password, user.getSalt());
            String token = generateToken();
            user.setToken(token);
            user.setEtat(User.EtatUser.ACTIF);
            modifierUser(user, email);
            return hashedPassword.equals(user.getPwd());
        } else {
            return false;
        }
    }

    public String setToken(String email) throws SQLException {
        User user = getUserByEmail(email);
        if (user != null && user.getEtat() != User.EtatUser.ACTIF) {
            String token = generateToken();
            user.setToken(token);
            user.setEtat(User.EtatUser.INACTIF);
            modifierUser(user, email);
            return token;
        } else {
            return null;
        }
    }

    public void logout(String email) throws SQLException {
        User user = getUserByEmail(email);
        if (user != null) {
            user.setToken(null);
            user.setEtat(User.EtatUser.INACTIF);
            modifierUser(user, email);
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setPwd(rs.getString("pwd"));
        user.setNom(rs.getString("nom"));
        user.setPrenom(rs.getString("prenom"));
        user.setPhoto(rs.getBytes("photo"));
        user.setNum_tel(rs.getString("num_tel"));
        user.setVille(rs.getString("ville"));
        user.setValeur_fidelite(rs.getInt("valeur_fidelite"));
        user.setRole(rs.getBoolean("role"));
        user.setSalt(rs.getString("salt"));
        user.setToken(rs.getString("token"));
        user.setEtat(User.EtatUser.valueOf(rs.getString("etat")));
        return user;
    }

    private String hashPassword(String password, String salt) {
        // Implement a secure password hashing algorithm, e.g. bcrypt
        // simple SHA-256 hash
        String saltedPassword = salt + password;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not hash password", e);
        }
    }

    private String generateToken() {
        // Generate a secure random token (simple UUID)
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
        try (PreparedStatement stmt = TuniTrocDB.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
            return false;
        }
    }

    public List<User> recherche(String nom) throws SQLException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user WHERE nom LIKE ? OR prenom LIKE ?";
        PreparedStatement stmt = TuniTrocDB.prepareStatement(query);
        stmt.setString(1, "%" + nom + "%");
        stmt.setString(2, "%" + nom + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            userList.add(getUserFromResultSet(rs));
        }
        System.out.println(userList);
        return userList;
    }

    public void sendEmail(String recipient, String subject, String body) {
        String host = "smtp.gmail.com";
        String username = "tunitrocPI@gmail.com";
        String password = "lhnscnmnkwypandz";
        String from = "TuniTroc <tunitrocPI@gmail.com>";
        String to = recipient;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    public Map<String, Integer> getUserCountByCity() throws SQLException {
        Map<String, Integer> result = new HashMap<>();
        String query = "SELECT ville, COUNT(*) AS count FROM user GROUP BY ville";
        PreparedStatement stmt = TuniTrocDB.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String ville = rs.getString("ville");
            int count = rs.getInt("count");
            result.put(ville, count);
        }
        return result;
    }

    public void envoyerSMS(String numero, String message) {
        // Vos identifiants Twilio
        String ACCOUNT_SID = "ACa2599aaf0f9316cec0efc7e95b15a183";
        String AUTH_TOKEN = "c55d24299bebdfa4faf7bf0dcd55e879";
        // Initialisation du client Twilio
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Envoi du SMS
        Message m = Message.creator(
                new PhoneNumber(numero), // Numéro de téléphone du destinataire
                new PhoneNumber("+12766378892"), // Numéro Twilio
                message) // Contenu du message
                .create();
    }

}
