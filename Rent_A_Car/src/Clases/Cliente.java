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
public class Cliente {
    public String nombre;
    public String apellido;
    public String direccion;
    public String telefono;
    Statement st;    //variable para ejecutar la instruccion SQL
    ResultSet rs;    //variable para almacenar el resultado de una consulta
    Conectar objconex = new Conectar();

    public Cliente(String nombre, String apellido, String direccion, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public void crearCliente() {

        try {
            objconex.conexion();//conectamos a la BDD
            String cadena = "insert into CLIENTE (NOMBRE, APELLIDO, DIRECCION, TELEFONO) values ('" //cadena para guardar en la BDD
                    + this.nombre + "','"
                    + this.apellido + "','"
                    + this.direccion + "','"
                    + this.telefono + "')";
            System.out.println(cadena);
            st = objconex.getConect().createStatement();
            st.executeUpdate(cadena);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
        }

    }
}
