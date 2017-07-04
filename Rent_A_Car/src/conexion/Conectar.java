/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author ACJ
 */
public class Conectar {
    
    Connection conect = null;

    public Connection conexion() {
        try {
            //Cargamos el Driver MySQL
            Class.forName("org.gjt.mm.mysql.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://64.62.211.131/komjona_rentcarsa", "komjona_carsa", "kom12345678");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        return conect;
    }
}
