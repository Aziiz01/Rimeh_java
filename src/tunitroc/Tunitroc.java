/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunitroc;

import entities.Evenement;
import entities.User;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import services.CRUDEvenement;
import services.CRUDUser;
import utils.DBConnection;

/**
 *
 * @author ZeroS TF
 */
public class Tunitroc {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
//        DBConnection TuniTrocDB = DBConnection.getInstance();
//
//        Scanner scanner = new Scanner(System.in);
//        int choice = 0;
//       System.out.println("Partie User");
//       LocalDate d1=null;
//       LocalDate d2=null;
//                    Evenement event= new Evenement(0,"nom","description",d1,d2);
//                    //User user = new User("jjdoe@example.com", "password", "Johnny", "Doe",  "https://example.com/profile.jpg",51591222, "Tunis", 0,false);
//
//                    do {
//                        System.out.println("\n==== Menu CRUD Users ====");
//                        System.out.println("1. Ajouter User");
//                        System.out.println("2. Supprimer User");
//                        System.out.println("3. Modifier User");
//                        System.out.println("4. Afficher Users");
//                        System.out.println("5. Login");
//                        System.out.println("6. Logout");
//                        System.out.print("\nChoisissez une option: ");
//
//                        try {
//                            choice = Integer.parseInt(scanner.nextLine());
//                        } catch (NumberFormatException e) {
//                            System.out.println("Invalid choice. Please enter a number.");
//                            continue;
//                        }
//                        CRUDUser crudUser = new CRUDUser();
//                        CRUDEvenement crudevent=new CRUDEvenement();
//
//                        switch (choice) {
//                            case 1:
//                                System.out.println("Ajout d'un utilisateur..");
//                                //crudUser.ajouterUser(user);
//                                crudevent.ajouterEvenement(event);
//                                break;
//
//                            case 2:
//                                System.out.println("You chose Option 2.");
//                                crudUser.supprimerUser("jjdoe@example.com");
//                                break;
//
//                            case 3:
//                                System.out.println("You chose Option 3.");
//                                user.setNom("notJoe");
//                                crudUser.modifierUser(user,"jjdoe@example.com");
//                                break;
//
//                            case 4:
//                                System.out.println("You chose Option 4.");
//                                crudUser.afficherUsers();
//                                break;
//
//                            case 5:
//                                System.out.println("logging in...");
//                                if (crudUser.login(user.getEmail(), user.getPwd())) {
//                                    crudUser.setToken(user.getEmail());
//                                    user.setEtat(User.EtatUser.ACTIF);
//                                    crudUser.modifierUser(user,"jjdoe@example.com");
//                                } else {
//                                    System.out.println("erreur login.");
//                                }
//                                break;
//
//                            case 6:
//                                System.out.println("logging out...");
//                                crudUser.logout(user.getEmail());
//                                user.setEtat(User.EtatUser.INACTIF);
//                                break;
//
//                            default:
//                                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
//                                break;
//                        }
//
//                    } while (choice != 7);
                }
    }
   