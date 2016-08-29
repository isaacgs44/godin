/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class Cuenta {
    private String id;
    private String nombre;
    private Double deber;
    private Double haber;
    private Double saldo;
    private Query query;
    private String tipo;
    private String periodo; //para el historico
    
    public Cuenta(String nombre, String tipo){
        this.nombre = nombre;
        this.id = generaId(tipo);
        this.tipo = obtenerTipo(id);
    }

    public Cuenta() {
         this.nombre = null;
         this.deber = null;
         this.haber = null;
         this.tipo = null;
         this.saldo = null;
         this.id = null;
         
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    public String obtenerTipo(String id){
        String opc = id.substring(0, 2);
        switch(opc){
            case "AC": 
                return "Activo C"; 
            case "AF": 
                return "Activo F"; 
            case "AD": 
                return "Activo D"; 
            case "PC":
                return "Pasivo C";
            case "PF":
                return "Pasivo F";
            case "PD":
                return "Pasivo D";
            case "GA":
                return "Gasto";
            case "IN":
                return "Ingreso";
            case "CC":
                return "Capital";
            case "CO":
                return "Costo";
            default:
                return "invalido";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getDeber() {
        return deber;
    }

    public void setDeber(Double deber) {
        this.deber = deber;
    }

    public Double getHaber() {
        return haber;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
    private String generaId(String tipo){
        String id = "";
        switch (tipo){
            case "Activo Circulante": id += "AC";
            break;
            case "Activo Fijo" : id += "AF";
            break; 
            case "Activo Diferido": id += "AD";
            break;
            case "Pasivo Fijo": id += "PF";
            break; 
            case "Pasivo Circulante": id += "PC";
            break;
            case "Pasivo Diferido": id += "PD";
            break;
            case "Ingreso": id += "IN";
            break;
            case "Costo":id += "CO";
            break;
            case "Gasto": id += "GA";
            break;
            case "Capital": id += "CC";
            break;
        }        
        
        query = new Query();
        String idNuevo = id;
       
        ResultSet res;
        if(query.seleccion("id_cta", "cuenta", "WHERE id_cta LIKE '" + id + "%'")){
            res = query.getRes();
            try {
                while (res.next()){
                    id=res.getString("id_cta");
                }
                Integer numero = Integer.parseInt(id.substring(2,5));
                numero++;      
                idNuevo += obtenerNuevoNumero(numero); 
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }                                
        }else{
             idNuevo+= "000";
        }
        query.Desconectar();
        return idNuevo;
    }
    
     private String obtenerNuevoNumero(Integer num){
        if(num<10){
            return "00"+num.toString();
        }else if(num<100){
            return "0"+num.toString();
        }
        return null;
    }
    
    private String generaIdSub(String cta){
        String id = cta + "-";     
        query = new Query();
        String idNuevo = cta + "-";
        ResultSet res;
        if(query.seleccion("id_sub", "subcuenta", "WHERE id_sub LIKE '" + cta + "%'")){
            res = query.getRes();
            try {
                while (res.next()){
                    id=res.getString("id_sub");
                }
                Integer numero = Integer.parseInt(id.substring(6,9));
                numero++;      
                idNuevo += obtenerNuevoNumero(numero); 
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }                                
        }else{
             idNuevo+= "000";
        }
        query.Desconectar();
        return idNuevo;
    }
     
    public Boolean registrarCta(){
        query = new Query();
        Boolean valor;
        String valores = "(id_cta, nombre_cta, deber_cta, haber_cta, saldo_cta) VALUES ('" + this.id + "','" + this.nombre + "','0.0','0.0','0.0')";
        valor = query.insertar("cuenta", valores);
        query.Desconectar();
        return valor;
    }

    public Boolean agregarSubCta(String cta, String nombre) {
        Query query1;
        query1 = new Query();
        Boolean valor;
        this.id = generaIdSub(cta);
        String valores = "(id_sub, nom_sub, deb_sub, hab_sub, saldo_sub) VALUES ('" + id + "','" + nombre + "','0.0','0.0','0.0')";
        valor =  query1.insertar("subcuenta", valores);
        query1.Desconectar();
        return valor;
    }
    
    public Boolean agregarSubCta(String cta, String nombre, Double saldo) {
        Query query1;
        query1 = new Query();
        Boolean valor;
        this.id = generaIdSub(cta);
        String valores = "(id_sub, nom_sub, deb_sub, hab_sub, saldo_sub) VALUES ('" + id + "','" + nombre + "','"+ saldo +"','0.0','"+ saldo +"')";
        valor =  query1.insertar("subcuenta", valores);
        query1.Desconectar();
        return valor;
    }
}