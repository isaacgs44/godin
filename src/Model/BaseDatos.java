/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author toshiba
 */
public class BaseDatos {
    private final String svr = "localhost"; //servidor o 127.0.0.1
private final String usr = "root";    // usuario
private final String pass = "isaacgs44";  // contraseña
private final String nomPaq= "com.mysql.jdbc.Driver"; //Nombre del paquete donde esta el Driver. 
private final String cadUrl= "jdbc:mysql://" + svr; 
private Connection cnx;  //objeto conexion

public BaseDatos(){
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

public void crearBD(String nombre){
     String s = "CREATE DATABASE "+nombre+";";
        try{
          Statement stat = cnx.createStatement(); // 
          System.out.println("\nConsulta: "+ s);
          stat.execute(s);  //ejecutamos la consulta
          System.out.println("Base de datos creada");
          
          s = "use "+ nombre + "; ";
          stat.execute(s);  //ejecutamos la consulta

          s="CREATE TABLE almacen (" +
            " SKU int(4) AUTO_INCREMENT PRIMARY KEY," +
            " codProd_alm varchar(50) COLLATE utf8_spanish_ci NOT NULL," +
            " idPed_alm int(4) NOT NULL," +
            " cant_alm int(3) NOT NULL," +
            " precioC_alm double NOT NULL);";
          stat.execute(s);  //ejecutamos la consulta
          System.out.println("Creada tabla Almacen");
          
          s = "CREATE TABLE cliente (" +
            " id_cl varchar(10) COLLATE utf8_spanish_ci PRIMARY KEY," +
            " nombre_cl varchar(30) COLLATE utf8_spanish_ci NOT NULL," +
            " referencia_cl varchar(100) COLLATE utf8_spanish_ci NOT NULL," +
            " dir_cl varchar(100) COLLATE utf8_spanish_ci NOT NULL," +
            " compras_cl int(4) NOT NULL," +
            " ctaClientes_cl varchar(15) COLLATE utf8_spanish_ci NOT NULL" +
            ")";
          stat.execute(s);
          System.out.println("Creada tabla cliente");
          
          s="CREATE TABLE compra (" +
            " id_comp varchar(10) COLLATE utf8_spanish_ci PRIMARY KEY," +
            " idCl_comp varchar(10) COLLATE utf8_spanish_ci NOT NULL," +
            " neto_comp double NOT NULL," +
            " costoV_comp double NOT NULL," +
            " vend_comp varchar(20) COLLATE utf8_spanish_ci NOT NULL," +
            " dia int(2) NOT NULL," +
            " mes int(2) NOT NULL," +
            " anio int(4) NOT NULL," +
            " registro_historico tinyint(1) NOT NULL DEFAULT '0'" +
            ")";
          stat.execute(s);
          System.out.println("Creada tabla compra");
          
          s="CREATE TABLE cuenta (" +
            " id_cta varchar(5) COLLATE utf8_spanish_ci PRIMARY KEY," +
            " nombre_cta varchar(40) COLLATE utf8_spanish_ci NOT NULL," +
            " deber_cta double NOT NULL," +
            " haber_cta double NOT NULL," +
            " saldo_cta double NOT NULL" +
            ")";
          stat.execute(s);
          System.out.println("Creada tabla compra");
          
          s="CREATE TABLE devolucion (" +
            " id_dev int(11) AUTO_INCREMENT PRIMARY KEY," +
            " idComp_dev varchar(10) COLLATE utf8_spanish_ci NOT NULL," +
            " codB_dev varchar(50) COLLATE utf8_spanish_ci NOT NULL" +
            " )";
          stat.execute(s);
          System.out.println("Creada tabla devolucion");
          
          s= "CREATE TABLE historico ("+
            "id_his int(11) AUTO_INCREMENT PRIMARY KEY,"+
            "idCta_his varchar(9) COLLATE utf8_spanish_ci NOT NULL,"+
            "nomCta_his varchar(30) COLLATE utf8_spanish_ci NOT NULL,"+
            "saldoCta_his double NOT NULL,"+
            "periodo_his varchar(30) COLLATE utf8_spanish_ci NOT NULL"+
            ")";
          stat.execute(s);
          System.out.println("Creada tabla historico");
          
          s="CREATE TABLE indicadores (" +
            " id_ind int(11) AUTO_INCREMENT PRIMARY KEY," +
            " nom_ind varchar(50) COLLATE utf8_spanish_ci NOT NULL," +
            " valor_ind varchar(30) COLLATE utf8_spanish_ci NOT NULL," +
            " periodo_ind varchar(50) COLLATE utf8_spanish_ci NOT NULL" +
            ")";
          stat.execute(s);
          System.out.println("Creada tabla indicadores");
          
          s="CREATE TABLE listacompra (" +
            " id_list varchar(8) COLLATE utf8_spanish_ci PRIMARY KEY," +
            " idComp_list varchar(10) COLLATE utf8_spanish_ci NOT NULL," +
            " codB_list varchar(50) COLLATE utf8_spanish_ci NOT NULL," +
            " cant_list int(3) NOT NULL," +
            " precioV_list double NOT NULL," +
            " precioC_list double NOT NULL" +
            " )";
          stat.execute(s);
          System.out.println("Creada tabla listacompra");
          
          s="CREATE TABLE operacion (" +
            " id_op int(5) AUTO_INCREMENT PRIMARY KEY," +
            " fecha_op date NOT NULL," +
            " idCtaC_op varchar(9) COLLATE utf8_spanish_ci NOT NULL," +
            " idCtaA_op varchar(9) COLLATE utf8_spanish_ci NOT NULL," +
            " desc_op varchar(50) COLLATE utf8_spanish_ci NOT NULL," +
            " monto_op double NOT NULL" +
            ")";
          stat.execute(s);
          System.out.println("Creada tabla operacion");
          
          s="CREATE TABLE pago (" +
            " id_pago int(4) AUTO_INCREMENT PRIMARY KEY," +
            " idCl_pago varchar(10) COLLATE utf8_spanish_ci NOT NULL," +
            " monto_pago double NOT NULL," +
            " dia_pago int(11) NOT NULL," +
            " mes_pago int(11) NOT NULL," +
            " anio_pago int(11) NOT NULL" +
            ")";
          stat.execute(s);
          System.out.println("Creada tabla pago");
          
          s="CREATE TABLE pedido (" +
            " id_ped int(4) PRIMARY KEY," +
            " idProv_ped varchar(13) COLLATE utf8_spanish_ci NOT NULL," +
            " fecha_ped date NOT NULL," +
            " neto_ped double NOT NULL," +
            " envio_ped double NOT NULL," +
            " desc_ped varchar(1000) COLLATE utf8_spanish_ci NOT NULL" +
            ")";
          stat.execute(s);
          System.out.println("Creada tabla pedido");
          
          s="CREATE TABLE producto (" +
            " codB_prod varchar(50) COLLATE utf8_spanish_ci PRIMARY KEY," +
            " nombre_prod varchar(80) COLLATE utf8_spanish_ci NOT NULL," +
            " marca_prod varchar(20) COLLATE utf8_spanish_ci NOT NULL," +
            " tipo_prod varchar(20) COLLATE utf8_spanish_ci NOT NULL," +
            " precioV_prod double NOT NULL," +
            " ultPrecioC_prod double DEFAULT NULL," +
            " ventas_prod int(4) NOT NULL," +
            " stockMin_prod int(3) DEFAULT NULL" +
            ")";
          stat.execute(s);
          System.out.println("Creada tabla producto");
          
          s="CREATE TABLE proveedor (" +
            " id_prov varchar(13) COLLATE utf8_spanish_ci PRIMARY KEY," +
            " razonS_prov varchar(50) COLLATE utf8_spanish_ci NOT NULL," +
            " direccion_prov varchar(100) COLLATE utf8_spanish_ci NOT NULL," +
            " tel_prov varchar(15) COLLATE utf8_spanish_ci NOT NULL," +
            " email_prov varchar(30) COLLATE utf8_spanish_ci NOT NULL," +
            " ctaAlmacen_prov varchar(15) COLLATE utf8_spanish_ci NOT NULL" +
            ")";
          stat.execute(s);
          System.out.println("Creada tabla proveedor");
          
          s="CREATE TABLE subcuenta (" +
            " id_sub varchar(9) COLLATE utf8_spanish_ci PRIMARY KEY," +
            " nom_sub varchar(30) COLLATE utf8_spanish_ci NOT NULL," +
            " deb_sub double NOT NULL," +
            " hab_sub double NOT NULL," +
            " saldo_sub double NOT NULL" +
            ")";
          stat.execute(s);
          System.out.println("Creada tabla subcuenta");
          
          s="CREATE TABLE vendedor (" +
            " id_vend int(11) AUTO_INCREMENT PRIMARY KEY," +
            " nombre_vend varchar(40) COLLATE utf8_spanish_ci NOT NULL," +
            " salario_vend double NOT NULL," +
            " comision_vend double NOT NULL," +
            " num_vent int(5) NOT NULL," +
            " ctaSalario_vend varchar(15) COLLATE utf8_spanish_ci NOT NULL" +
            ")";
          stat.execute(s);
          System.out.println("Creada tabla vendedor");
          
          cnx.close();

          // falta probar que las tablas se crean bien
          
          
      }
      catch(SQLException ex){ //Excepción SQL
           System.out.println("\nConsulta: "+ s);
          System.out.println("Error al creal BDD");
      }
      
     
    
       
}


}
