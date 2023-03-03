/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import entities.Produit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilis.Connexion;

/**
 *
 * @author kheir
 */
public class ProduitCrud {
    
  public void ajouterproduit (Produit p){
      try{
          String requete="INSERT INTO produit ( type , categorie , nom , libelle,photo,ville)"
                  +"VALUES (?,?,?,?,?,?)";
          PreparedStatement pst= Connexion.getInstance().getCnx().prepareStatement(requete);
          pst.setString(1,p.getType());
          pst.setString(2,p.getCategorie());
          pst.setString(3,p.getNom());
          pst.setString(4,p.getLibelle());
          pst.setString(5,p.getPhoto());
          pst.setString(6,p.getVille());
          pst.executeUpdate();
          System.out.println("Produit ajouté");
      }catch(SQLException ex){
          System.out.println(ex.getMessage());   
      }   
  }
    
    public List<Produit> affich(){
        List<Produit> myList = new ArrayList<>();
       
        try
        {
            String requete="SELECT * FROM produit";
            Statement st=Connexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            System.out.println(rs);
            while (rs.next()){
                Produit p = new Produit();
                p.setId(rs.getInt(1));
                p.setType(rs.getString(2));
                p.setCategorie(rs.getString(3));
                p.setNom(rs.getString(4));
                p.setLibelle(rs.getString(5));
                p.setPhoto(rs.getString(6));
                p.setVille(rs.getString(7));
                myList.add(p);
            } 
        }catch(SQLException ex){
    System.out.println(ex.getMessage());  
    }
        return myList;
    }
    
    public void supprimerProduit(Produit t){
        
        String query = "DELETE FROM produit WHERE produit.id="+ t.getId()+" ";
        try{
            Statement st =Connexion.getInstance().getCnx().createStatement();
            st.executeUpdate(query);
            System.out.println("Produit supprimé ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void modifierProduit(Produit t ,String x,String y,String z,String a,String b ){
            try{
        
        String requte ="update produit set type=?,categorie=?,nom=?,libelle=?,photo=?,ville=? where id="+ t.getId()+"";
        PreparedStatement pst= Connexion.getInstance().getCnx().prepareStatement(requte);
        pst.setString(1, t.getType());
        pst.setString(2, t.getCategorie());
        pst.setString(3, t.getNom());
        pst.setString(4, t.getLibelle());
        pst.setString(5, t.getPhoto());
        pst.setString(6, t.getVille());
        
        pst.executeUpdate();
        System.out.println("Produit Modifié");
    }catch (SQLException ex ){
            System.out.println(ex.getMessage());

    }
    

    }  
}
