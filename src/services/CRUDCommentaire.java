/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import utils.DBConnection;

/**
 *
 * @author ZeroS TF
 */
public class CRUDCommentaire implements InterfaceCRUDCommentaire{
    Connection TuniTrocDB = DBConnection.getConnection();
}
