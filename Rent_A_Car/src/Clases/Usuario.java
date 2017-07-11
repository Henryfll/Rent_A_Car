/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import conexion.Conectar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ACJ
 */
public class Usuario {
    public String usuario;
    public String contrasena;
    Statement st;    //variable para ejecutar la instruccion SQL
    ResultSet rs;    //variable para almacenar el resultado de una consulta
    Conectar objconex = new Conectar();

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    
    public void crearUsuario() {

        try {
            objconex.conexion();//conectamos a la BDD
            String cadena = "insert into USUARIO (USER, CONTRASENA) values ('" //cadena para guardar en la BDD
                    + this.usuario + "','"
                    + this.contrasena + "')";
            System.out.println(cadena);
            st = objconex.getConect().createStatement();
            st.executeUpdate(cadena);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
        }

    }
}
