/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Element;
import entities.Reclamation;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.DBConnection;
import java.io.FileOutputStream;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javax.swing.text.Document;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



/**
 *
 * @author Hedi
 */
public class CRUDReclamation implements InterfaceCRUDReclamation{
    Connection TuniTrocDB = DBConnection.getConnection();
    @Override
    public void ajouterReclamation(Reclamation reclamation) throws SQLException {      
        PreparedStatement stmt = TuniTrocDB.prepareStatement("INSERT INTO reclamation(id_userS, id_userR, cause,etat) VALUES(?, ?, ?, ?)");
        stmt.setInt(1, reclamation.getId_userS());
        stmt.setInt(2, reclamation.getId_userR());
        stmt.setString(3, reclamation.getCause());
        stmt.setBoolean(4, reclamation.IsEtat());
        stmt.executeUpdate();
    }

    @Override
    public void modifierReclamation(Reclamation reclamation, int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("UPDATE reclamation SET id_userS=?, id_userR=?, cause=?, etat=? WHERE id=?");
        stmt.setInt(1, reclamation.getId_userS());
        stmt.setInt(2, reclamation.getId_userR());
        stmt.setString(3, reclamation.getCause());
        stmt.setBoolean(4, reclamation.IsEtat());
        stmt.setInt(5, id);
        stmt.executeUpdate();
    }

    @Override
    public void supprimerReclamation(int id) throws SQLException {
        PreparedStatement stmt = TuniTrocDB.prepareStatement("DELETE FROM reclamation WHERE id=?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    @Override
    public List<Reclamation> afficherReclamations() throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        Statement stmt = TuniTrocDB.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM reclamation");
        while (rs.next()) {
            int id = rs.getInt("id");
            int id_userS = rs.getInt("id_userS");
            int id_userR = rs.getInt("id_userR");
            String cause = rs.getString("cause");
            boolean etat= rs.getBoolean("etat");
            Reclamation reclamation = new Reclamation(id, id_userS, id_userR, cause, etat);
            reclamations.add(reclamation);
        }
        System.out.println(reclamations);
        return reclamations;
    }
    
    public Map<String, Integer> getReclamationStats() throws SQLException {
    Map<String, Integer> statsMap = new HashMap<>();

    String query = "SELECT etat, COUNT(*) AS count FROM reclamation GROUP BY etat";

         Statement statement = TuniTrocDB.createStatement();
         ResultSet resultSet = statement.executeQuery(query) ;
        while (resultSet.next()) {
            String etat = resultSet.getBoolean("etat") ? "Traitée" : "En cours";
            int count = resultSet.getInt("count");
            statsMap.put(etat, count);
        }
    

    return statsMap;
}

    public void export(List<Reclamation> reclamations) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier PDF");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        PdfPCell cell;

        cell = new PdfPCell(new Paragraph("ID"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Emetteur"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Récepteur"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Cause"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Etat"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        for (Reclamation reclamation : reclamations) {
            table.addCell(String.valueOf(reclamation.getId()));
            table.addCell(String.valueOf(reclamation.getId_userS()));
            table.addCell(String.valueOf(reclamation.getId_userR()));
            table.addCell(reclamation.getCause());
            table.addCell(reclamation.IsEtat() ? "Traitée" : "En cours");
        }

        document.add(table);
        document.close();
    }
}


}