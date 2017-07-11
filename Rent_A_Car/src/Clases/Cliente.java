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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public Cliente() {
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
    
    public void EliminarDatos(String Id) {
       
        try {
            objconex.conexion();
             String cadena = "delete from CLIENTE where IDCLIENTE='" + Id +"'";
            st=objconex.getConect().createStatement();
            st.executeUpdate(cadena);
            JOptionPane.showMessageDialog(null,"Registro Eliminado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error::::"+ex);
            
        }
    }
    public void ModificarDatos(String id) {
        try {
            objconex.conexion();
        String  cadena = "update CLIENTE set NOMBRE='"+ this.nombre+ "',"
                +"APELLIDO='" + this.apellido+ "',"
                +"DIRECCION='" + this.direccion + "'," 
                +"TELEFONO='"+ this.telefono+ "' " 
                + "where IDCLIENTE='" + id + "'";
             System.out.println(cadena);
            st=objconex.getConect().createStatement();
            st.executeUpdate(cadena);
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error::::"+ex);   
        }
    }
    public String BuscarCliente(String Id){
        String datos="";
         try {
            objconex.conexion();
             String cadena="select * from CLIENTE where IDCLIENTE='"+Id+"'";
            st=objconex.getConect().createStatement();
            rs=st.executeQuery(cadena);
            while(rs.next()){
                datos=datos+rs.getInt("IDCLIENTE")+"/"+
                        rs.getString("NOMBRE")+"/"+
                        rs.getString("APELLIDO")+"/"+
                        rs.getString("DIRECCION")+"/"+
                        rs.getString("TELEFONO");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error::::"+ex);
            
        }
        return(datos);
    }
    public String[] ConsultaGeneral(){
        String[] arrayClientes=null;
        String datos="";
         try {
            objconex.conexion();
             String cadena="select * from CLIENTE";
            st=objconex.getConect().createStatement();
            rs=st.executeQuery(cadena);
            
           int numero_Registros= 0;
           while(rs.next()){
               numero_Registros++;
           }
           System.out.println("NUMERO DE REGISTOS "+numero_Registros);
           arrayClientes = new String [numero_Registros];
           int pos=0;
           rs.beforeFirst();
            while(rs.next()){
                datos=datos+rs.getInt("IDCLIENTE")+"/"+
                        rs.getString("NOMBRE")+"/"+
                        rs.getString("APELLIDO")+"/"+
                        rs.getString("DIRECCION")+"/"+
                        rs.getString("TELEFONO");
                arrayClientes[pos]=datos;
                pos++;
                datos="";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error::::"+ex);
            
        }
        return (arrayClientes);
    }
}
