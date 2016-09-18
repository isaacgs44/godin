/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thinkpad
 */
public class Query {

    private ResultSet res = null; //objeto ResultSet (Resultado de consulta)
    private ResultSetMetaData metadatos = null;
    private Statement statSet = null; //objeto Statement para mandar sentencias al servidor
    private final String svr = "localhost"; //servidor o 127.0.0.1
    private final String bd = "sistemaventas";  // nombre de la base de datos
    private final String usr = "root";    // usuario
    private final String pass = "isaacgs44";  // contraseña
    private final String nomPaq = "com.mysql.jdbc.Driver"; //Nombre del paquete donde esta el Driver. 
    private final String cadUrl = "jdbc:mysql://" + svr + "/" + bd;
    private Connection cnx;  //objeto conexion

    public Connection getCnx() {
        return cnx;
    }

    public Query() {
        try {
            Class.forName(nomPaq); //cargamos la instancia de la clase - ?

            //DriverManager intenta hacer la conexión 
            this.cnx = DriverManager.getConnection(cadUrl, usr, pass); //se hace la conexion
            System.out.println("\nConexion establecida");

        } catch (ClassNotFoundException ex) { //Excepción de que no se encuentra la clase
            System.out.println("No puede leer el driver");
        } catch (SQLException ex) { //Excepción SQL - no se puede conectar
            System.out.println("No se puede conectar a la base");
        }
    }

    public void Desconectar() {
        try {
            this.cnx.close();
            System.out.println("\nConexión cerrada");
        } catch (SQLException ex) {
            System.out.println("No se puede cerrar la conexión");
        }
    }

    public ResultSet getRes() {
        return res;
    }

    //INSERT IN TO tabla (val1,val2) VALUES (1,2)
    public boolean insertar(String tabla, String cadena) {
        String query = "INSERT INTO " + tabla + " " + cadena + ";";

        try {
            Statement stat = this.getCnx().createStatement(); // --- ?
            System.out.println("\nConsulta: " + query);
            stat.execute(query);  //ejecutamos la consulta
            System.out.println("Registro insertado");
            return true;
        } catch (SQLException ex) { //Excepción SQL
            System.out.println("\nConsulta: " + query);
            System.out.println("Error al insertar");
            return false;
        }

    }

    //SELECT * FROM tabla
    public boolean seleccion(String columna, String tabla) {
        // * - TODO
        String query = "SELECT " + columna + " FROM " + tabla + " ";
        try {
            Statement st = this.getCnx().createStatement();
            this.res = st.executeQuery(query);
            System.out.println("\nConsulta: " + query);
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validarResult();
    }

    //SELECT * FROM tabla WHERE id = 0
    public boolean seleccion(String columna, String tabla, String condicion) {
        String query = "SELECT " + columna + " FROM " + tabla + " " + condicion;
        try {
            Statement st = this.getCnx().createStatement();
            this.res = st.executeQuery(query);
            System.out.println("\nConsulta: " + query);
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validarResult();
    }

    public boolean validarResult() {
        try {
            if (this.res.next()) {
                this.res.beforeFirst();
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //consulta cualquiera
    public boolean consulta(String query) {
        try {
            Statement stat = this.getCnx().createStatement(); // --- ?
            System.out.println("\nConsulta: " + query);
            this.res = stat.executeQuery(query);
        } catch (SQLException ex) { //Excepción SQL
            System.out.println("Error");
        }
        return validarResult();
    }

    //ejecutar cualquiera
    public boolean ejecuta(String cadena) {
        String query = cadena;
        try {
            Statement stat = this.getCnx().createStatement(); // --- ?
            System.out.println("\nConsulta: " + query);
            stat.execute(query);  //ejecutamos la consulta
            System.out.println("Realizado con éxito");
            return true;
        } catch (SQLException ex) { //Excepción SQL
            System.out.println("Error al ejecutar");
            return false;
        }
    }

    public Boolean update(String tabla, String valores, String cond) {
        String query = "UPDATE " + tabla + " SET " + valores + " " + cond + ";";
        try {
            Statement stat = this.getCnx().createStatement(); // --- ?
            System.out.println("\nConsulta: " + query);
            stat.execute(query);  //ejecutamos la consulta
            System.out.println("Realizado con éxito");
            return true;
        } catch (SQLException ex) { //Excepción SQL
            System.out.println("Error al ejecutar");
            return false;
        }
    }

}
