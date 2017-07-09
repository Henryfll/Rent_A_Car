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
 * @author HenryF
 */
public class Vehiculo {
    public int idoficina;
    public String modelo;
    public String descripcion;
    public String marca;
    public String color;
    public int alquilado;
    public String placa;
    Statement st;    //variable para ejecutar la instruccion SQL
    ResultSet rs;    //variable para almacenar el resultado de una consulta
    Conectar objconex = new Conectar();

    public Vehiculo(int idoficina, String modelo, String descripcion, String marca, String color, int alquilado, String placa) {
        this.idoficina = idoficina;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.marca = marca;
        this.color = color;
        this.alquilado = alquilado;
        this.placa = placa;
    }
    public void NuevoVehiculo() {

        try {
            objconex.conexion();//conectamos a la BDD
            String cadena = "insert into VEHICULO (IDOFICINA, MODELO, DESCRIPCION, MARCA, COLOR, ALQUILADO, PLACA) values (" //cadena para guardar en la BDD
                    + this.idoficina + ",'"
                    + this.modelo + "','"
                    + this.descripcion + "','"
                    + this.marca + "','"
                    + this.color + "','"
                    + this.alquilado + "','"
                    + this.placa + "')";
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
             String cadena = "delete from VEHICULO where IDVEHICULO='" + Id +"'";
            st=objconex.getConect().createStatement();
            st.executeUpdate(cadena);
            JOptionPane.showMessageDialog(null,"Registro Eliminado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error::::"+ex);
            
        }
    }
    public String BuscarVehiculo(String Id){
        String datos="";
         try {
            objconex.conexion();
             String cadena="select * from VEHICULO where IDVEHICULO='"+Id+"'";
            st=objconex.getConect().createStatement();
            rs=st.executeQuery(cadena);
            while(rs.next()){
                datos=datos+rs.getInt("IDOFICINA")+"/"+
                        rs.getString("MODELO")+"/"+
                        rs.getString("DESCRIPCION")+"/"+
                        rs.getString("MARCA")+"/"+
                        rs.getString("COLOR")+"/"+
                        rs.getBoolean("ALQUILADO")+"/"+
                        rs.getString("PLACA");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error::::"+ex);
            
        }
         
        
        return(datos);
    }
    public String[] ConsultaGeneral(){
        String[] arrayVehiculos=null;
        String datos="";
         try {
            objconex.conexion();
             String cadena="select * from VEHICULO";
            st=objconex.getConect().createStatement();
            rs=st.executeQuery(cadena);
            
           int numero_Registros= 0;
           while(rs.next()){
               numero_Registros++;
           }
           System.out.println("NUMERO DE REGISTOS "+numero_Registros);
           arrayVehiculos = new String [numero_Registros];
           int pos=0;
           rs.beforeFirst();
            while(rs.next()){
                datos=datos+rs.getInt("IDVEHICULO")+"/"+
                        rs.getInt("IDOFICINA")+"/"+
                        rs.getString("MODELO")+"/"+
                        rs.getString("DESCRIPCION")+"/"+
                        rs.getString("MARCA")+"/"+
                        rs.getString("COLOR")+"/"+
                        rs.getBoolean("ALQUILADO")+"/"+
                        rs.getString("PLACA");
                arrayVehiculos[pos]=datos;
                pos++;
                datos="";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error::::"+ex);
            
        }
        return (arrayVehiculos);
    }
}
