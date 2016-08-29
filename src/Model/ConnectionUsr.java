/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class ConnectionUsr {
private final String svr = "localhost"; //servidor o 127.0.0.1
private final String bd = "sistemaventas";  // nombre de la base de datos
private final String usr = "root";    // usuario
private final String pass = "isaacgs44";  // contraseña
private final String nomPaq= "com.mysql.jdbc.Driver"; //Nombre del paquete donde esta el Driver. 
private final String cadUrl= "jdbc:mysql://" + svr + "/" + bd; 
private Connection cnx;  //objeto conexion

    public Connection getCnx() {
        return cnx;
    }

    public ConnectionUsr(){        
        try {
            Class.forName(nomPaq); //cargamos la instancia de la clase - ?
          
            //DriverManager intenta hacer la conexión 
            this.cnx = DriverManager.getConnection(cadUrl, usr, pass); //se hace la conexion
            System.out.println("\nConexion establecida");
          
        }
        catch(ClassNotFoundException ex){ //Excepción de que no se encuentra la clase
            System.out.println("No puede leer el driver");
        }
        catch(SQLException ex){ //Excepción SQL - no se puede conectar
            System.out.println("No se puede conectar a la base");
        }
    }
    
    public void Desconectar(){
        try{
            this.cnx.close();
            System.out.println("\nConexión cerrada");
        }catch (SQLException ex){
            System.out.println("No se puede cerrar la conexión");
        }
    }
}
