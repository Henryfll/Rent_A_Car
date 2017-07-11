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
 * @author Diego Claudio
 */
public class Oficina {
    public String nombre;
    public String ciudad;
    public String direccion;
    public String encargado;
    Statement st;    //variable para ejecutar la instruccion SQL
    ResultSet rs;    //variable para almacenar el resultado de una consulta
    Conectar objconex = new Conectar();

    public Oficina(String nombre, String ciudad, String direccion, String encargado) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.encargado = encargado;
    }

    public Oficina() {
    }
        
    public void CrearOficina() {

        try {
            objconex.conexion();//conectamos a la BDD
            String cadena = "insert into OFICINA (NOMBRE, CIUDAD, DIRECCION, ENCARGADO) values ('" //cadena para guardar en la BDD
                    + this.nombre + "','"
                    + this.ciudad + "','"
                    + this.direccion + "','"
                    + this.encargado + "')";
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
             String cadena = "delete from OFICINA where IDOFICINA='" + Id +"'";
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
        String  cadena = "update OFICINA set NOMBRE='"+ this.nombre + "',"
                +"CIUDAD='" + this.ciudad+ "',"
                +"DIRECCION='" + this.direccion + "'," 
                +"ENCARGADO='"+ this.encargado + "' " 
                + "where IDOFICINA='" + id + "'";
             System.out.println(cadena);
            st=objconex.getConect().createStatement();
            st.executeUpdate(cadena);
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error::::"+ex);
            
        }
        
    }
    public String BuscarOficina(String Id){
        String datos="";
         try {
            objconex.conexion();
             String cadena="select * from OFICINA where IDOFICINA='"+Id+"'";
            st=objconex.getConect().createStatement();
            rs=st.executeQuery(cadena);
            while(rs.next()){
                datos=datos+rs.getString("NOMBRE")+"/"+
                        rs.getString("CIUDAD")+"/"+
                        rs.getString("DIRECCION")+"/"+
                        rs.getString("ENCARGADO");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Oficina.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error::::"+ex);
            
        }
         
        
        return(datos);
    }
    
    public String[] ConsultaGeneral(){
        String[] arrayOficinas=null;
        String datos="";
         try {
            objconex.conexion();
             String cadena="select * from OFICINA";
            st=objconex.getConect().createStatement();
            rs=st.executeQuery(cadena);
            
           int numero_Registros= 0;
           while(rs.next()){
               numero_Registros++;
           }
           System.out.println("NUMERO DE REGISTOS "+numero_Registros);
           arrayOficinas = new String [numero_Registros];
           int pos=0;
           rs.beforeFirst();
            while(rs.next()){
                datos=datos+rs.getInt("IDOFICINA")+"/"+
                        rs.getString("NOMBRE")+"/"+
                        rs.getString("CIUDAD")+"/"+
                        rs.getString("DIRECCION")+"/"+
                        rs.getString("ENCARGADO");
                arrayOficinas[pos]=datos;
                pos++;
                datos="";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Oficina.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error::::"+ex);
            
        }
        return (arrayOficinas);
    }
    
    public String[] ConsultaOficinas(){
        String[] arrayOficinas=null;
        String datos="";
         try {
            objconex.conexion();
             String cadena="select * from OFICINA";
            st=objconex.getConect().createStatement();
            rs=st.executeQuery(cadena);
            
           int numero_Registros= 0;
           while(rs.next()){
               numero_Registros++;
           }
           System.out.println("NUMERO DE REGISTOS "+numero_Registros);
           arrayOficinas = new String [numero_Registros];
           int pos=0;
           rs.beforeFirst();
            while(rs.next()){
                datos=datos+rs.getInt("IDOFICINA")+"/"+
                        rs.getString("NOMBRE");
                arrayOficinas[pos]=datos;
                pos++;
                datos="";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Oficina.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error::::"+ex);
            
        }
        return (arrayOficinas);
    }
}
