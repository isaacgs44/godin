/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author lenovo
 */
public class LibroMayor {
    private List <Cuenta> cuentas;
    private Query query;
    DecimalFormat df = new DecimalFormat("###,###.##");
    public LibroMayor(){
        cuentas = new ArrayList <>();
        this.cuentas = obtenerCuentas();
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
    
    public List <Cuenta> obtenerCuentas(){ //CUENTAS PRINCIPALES
        this.cuentas.clear();
        ResultSet res = null;
        query = new Query();
        query.seleccion("*", "cuenta");
        res = query.getRes();
        try {
            while (res.next()){
                Cuenta c = new Cuenta();
                c.setId(res.getString("id_cta"));
                c.setNombre(res.getString("nombre_cta"));
                c.setDeber(Double.parseDouble(res.getString("deber_cta")));
                c.setHaber(Double.parseDouble(res.getString("haber_cta")));
                c.setSaldo(Double.parseDouble(res.getString("saldo_cta")));
                this.cuentas.add(c);
            }
            query.Desconectar();
            return this.cuentas;
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
             query.Desconectar();
            return null;
        }
        
    }
    
     public List <Cuenta> obtenerCuentasYsub(){ //CUENTAS PRINCIPALES Y SUBCUENTAS
        this.cuentas.clear();
        List <Cuenta> ctas = new ArrayList <>();
        List <Cuenta> subCtas = new ArrayList <>();
        ResultSet res = null;
        query = new Query();
        query.seleccion("*", "cuenta");
        res = query.getRes();
        try {
            while (res.next()){
                Cuenta c = new Cuenta();
                c.setId(res.getString("id_cta"));
                c.setNombre(res.getString("nombre_cta"));
                c.setDeber(Double.parseDouble(res.getString("deber_cta")));
                c.setHaber(Double.parseDouble(res.getString("haber_cta")));
                c.setSaldo(Double.parseDouble(res.getString("saldo_cta")));
                ctas.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
        
        query.seleccion("*", "subcuenta");
         res = query.getRes();
        try {
            while (res.next()){
                Cuenta c = new Cuenta();
                c.setId(res.getString("id_sub"));
                c.setNombre(res.getString("nom_sub"));
                c.setDeber(Double.parseDouble(res.getString("deb_sub")));
                c.setHaber(Double.parseDouble(res.getString("hab_sub")));
                c.setSaldo(Double.parseDouble(res.getString("saldo_sub")));
                subCtas.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
             query.Desconectar();
            return null;
        }
        
        for(Cuenta c : ctas){
            this.cuentas.add(c);
            for(Cuenta s : subCtas){
                if(c.getId().substring(0,5).equals(s.getId().substring(0,5))){
                    this.cuentas.add(s);
                }
            }
        }
        query.Desconectar();
        return this.cuentas;
    }
    
    public TableModel creaModeloCtas(List <Cuenta> ctas){
        TablaCuentas modelo1 = new TablaCuentas(ctas.size(),4);  //numero de filas del ResultSet como parametro del constructor
        int i=0;
        for (Cuenta c : ctas) {
              modelo1.setValueAt(c.getId(), i, 0);
              modelo1.setValueAt(c.getNombre(), i, 1);
              modelo1.setValueAt(c.obtenerTipo(c.getId()),i,2);
              modelo1.setValueAt(df.format(c.getSaldo()),i,3);              
              i++;
        }
        return modelo1;
        
    }
    
    public Cuenta obtenerCuenta(String idCuenta, String opc) {
        ResultSet res = null;
        query = new Query();
        String cond;
        switch(opc){
            case "c":
                cond = "WHERE id_cta = '" + idCuenta + "'";
                query.seleccion("*","cuenta",cond);
            break;
            
            case "s":
                cond = "WHERE id_sub = '" + idCuenta + "'";
                query.seleccion("*","subcuenta",cond);
            break;
        }
        
        res = query.getRes();
        try {
            while (res.next()){
                Cuenta c = new Cuenta();
                c.setId(res.getString(1));
                c.setNombre(res.getString(2));
                c.setDeber(Double.parseDouble(res.getString(3)));
                c.setHaber(Double.parseDouble(res.getString(4)));
                c.setSaldo(Double.parseDouble(res.getString(5)));
                query.Desconectar();
                return c;
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
           
        }
         query.Desconectar();
         return null;
    }

    //obtener cuenta complementaria para el tipo de operacion
    public List<Cuenta> obtenerCuentasComp(String idCuenta, String tipo) { 
        this.cuentas.clear();
        List <Cuenta> ctas = new ArrayList <>();
        List <Cuenta> subCtas = new ArrayList <>();
        ResultSet res = null;
        query = new Query();
        String cond1 = "";
        String cond2 = "";
        String opc = idCuenta.substring(0,2);
        System.out.println(opc);
        switch(opc){ 
            case "AC": case "AF": case "AD": //Activo
                if(tipo.equals("cargo")){ // tipo - cargo (+)
                    cond1 = "WHERE (id_cta LIKE 'A%' OR id_cta LIKE 'CC%' OR id_cta LIKE 'P%' OR id_cta LIKE 'IN%') AND id_cta != '" + idCuenta +"'"; //opciones de abono: Activo, Pasivo, Capital e Ingreso
                    cond2 = "WHERE (id_sub LIKE 'A%' OR id_sub LIKE 'CC%' OR id_sub LIKE 'P%' OR id_sub LIKE 'IN%') AND id_sub != '" + idCuenta +"'"; //opciones de abono: Activo, Pasivo, Capital e Ingreso
                }else if(tipo.equals("abono")){ // tipo - abono (-)
                    cond1 = "WHERE (id_cta LIKE 'A%' OR id_cta LIKE 'CC%' OR id_cta LIKE 'P%' OR id_cta LIKE 'CO%' OR id_cta LIKE 'GA%') AND id_cta != '" + idCuenta +"'"; //opciones de cargo: Activo, Pasivo, Capital y Egreso
                    cond2 = "WHERE (id_sub LIKE 'A%' OR id_sub LIKE 'CC%' OR id_sub LIKE 'P%' OR id_sub LIKE 'CO%' OR id_sub LIKE 'GA%') AND id_sub != '" + idCuenta +"'"; //opciones de cargo: Activo, Pasivo, Capital y Egreso
                }
            break;
            
            case "PC": case "PF": case "PD": //Pasivo
                    cond1 = "WHERE (id_cta LIKE 'A%' OR id_cta LIKE 'P%')AND id_cta != '" + idCuenta +"'"; //opciones de abono y cargo: Activo y Pasivo
                    cond2 = "WHERE (id_sub LIKE 'A%' OR id_sub LIKE 'P%')AND id_sub != '" + idCuenta +"'"; //opciones de abono y cargo: Activo y Pasivo
            break;
            
            case "IN": case "GA": case "CO": case "CC": // Ingreso, Egreso y Capital
                 cond1 = "WHERE (id_cta LIKE 'A%') AND id_cta != '" + idCuenta +"'"; //opciones de abono y cargo:  Activo
                 cond2 = "WHERE (id_sub LIKE 'A%') AND id_sub != '" + idCuenta +"'"; //opciones de abono y cargo:  Activo
            break;
        }
        query.seleccion("*", "cuenta", cond1);
        res = query.getRes();
        try {
            while (res.next()){
                Cuenta c = new Cuenta();
                c.setId(res.getString("id_cta"));
                c.setNombre(res.getString("nombre_cta"));
                c.setDeber(Double.parseDouble(res.getString("deber_cta")));
                c.setHaber(Double.parseDouble(res.getString("haber_cta")));
                c.setSaldo(Double.parseDouble(res.getString("saldo_cta")));
                ctas.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
             query.Desconectar();
            return null;
        }
        
        query.seleccion("*", "subcuenta", cond2);
         res = query.getRes();
        try {
            while (res.next()){
                Cuenta c = new Cuenta();
                c.setId(res.getString("id_sub"));
                c.setNombre(res.getString("nom_sub"));
                c.setDeber(Double.parseDouble(res.getString("deb_sub")));
                c.setHaber(Double.parseDouble(res.getString("hab_sub")));
                c.setSaldo(Double.parseDouble(res.getString("saldo_sub")));
                subCtas.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
             query.Desconectar();
            return null;
        }
         Boolean bandera;
        for(Cuenta c : ctas){
            bandera = true;
            for(Cuenta s : subCtas){
                if(c.getId().substring(0,5).equals(s.getId().substring(0,5))){
                    bandera = false;
                    System.out.println(s.getNombre());
                    this.cuentas.add(s);
                }
            }
            
            if(bandera){
                System.out.println(c.getNombre());
                this.cuentas.add(c);
            }
        }
         query.Desconectar();
        return this.cuentas;
    }

    public Boolean actualizarCta(Cuenta cta) {
        query = new Query();
        Boolean valor;
        String consulta;
        if(cta.getId().length()>6){
            consulta = "UPDATE subcuenta SET deb_sub=" + cta.getDeber().toString() + ", hab_sub=" + cta.getHaber().toString() + ", saldo_sub=" + cta.getSaldo().toString() + " WHERE id_sub='" + cta.getId() + "';"; 
        }else{
             consulta = "UPDATE cuenta SET deber_cta=" + cta.getDeber().toString() + ", haber_cta=" + cta.getHaber().toString() + ", saldo_cta=" + cta.getSaldo().toString() + " WHERE id_cta='" + cta.getId() + "';"; 
        }
        valor = query.ejecuta(consulta);
        query.Desconectar();
        return valor;
    }

    public List<Cuenta> buscarCuentasYsub(String text) {
        this.cuentas.clear();
        List <Cuenta> ctas = new ArrayList <>();
        List <Cuenta> subCtas = new ArrayList <>();
        ResultSet res = null;
        query = new Query();
        Boolean bandera = false;
        String cond1 = "WHERE nombre_cta LIKE '%" + text + "%'";
        String cond2 = "WHERE nom_sub LIKE '%" + text + "%'";
        query.seleccion("*", "cuenta", cond1);
        
        res = query.getRes();
        try {
            while (res.next()){
                Cuenta c = new Cuenta();
                c.setId(res.getString("id_cta"));
                c.setNombre(res.getString("nombre_cta"));
                c.setDeber(Double.parseDouble(res.getString("deber_cta")));
                c.setHaber(Double.parseDouble(res.getString("haber_cta")));
                c.setSaldo(Double.parseDouble(res.getString("saldo_cta")));
                ctas.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
        
        query.seleccion("*", "subcuenta", cond2);
        res = query.getRes();
        try {
            while (res.next()){
                Cuenta c = new Cuenta();
                c.setId(res.getString("id_sub"));
                c.setNombre(res.getString("nom_sub"));
                c.setDeber(Double.parseDouble(res.getString("deb_sub")));
                c.setHaber(Double.parseDouble(res.getString("hab_sub")));
                c.setSaldo(Double.parseDouble(res.getString("saldo_sub")));
                subCtas.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
        
        for(Cuenta c : ctas){
            bandera=true;
            this.cuentas.add(c);
            for(Cuenta s : subCtas){
                if(c.getId().equals(s.getId().substring(0,5))){
                    this.cuentas.add(s);
                }
            }
        }
        
        if(this.cuentas.isEmpty() || bandera){
            for(Cuenta s: subCtas){
                this.cuentas.add(s);
            }
        }
        query.Desconectar();
        return this.cuentas;
    }

    public List<Cuenta> PorTipoCtsYsub(String opc) {
        this.cuentas.clear();
        List <Cuenta> ctas = new ArrayList <>();
        List <Cuenta> subCtas = new ArrayList <>();
        ResultSet res = null;
        query = new Query();
        String cond1 = "";
        String cond2 = "";
        switch(opc){
            case "Activo":
                cond1 = "WHERE id_cta LIKE 'A%'";
                cond2 = "WHERE id_sub LIKE 'A%'";
            break;
            case "Pasivo":
                cond1 = "WHERE id_cta LIKE 'P%'";
                cond2 = "WHERE id_sub LIKE 'P%'";
            break;
            case "Capital":
                cond1 = "WHERE id_cta LIKE 'CC%'";
                cond2 = "WHERE id_sub LIKE 'CC%'";
            break;
            case "Egreso":
                cond1 = "WHERE id_cta LIKE 'CO%' OR id_cta LIKE 'GA%'";
                cond2 = "WHERE id_sub LIKE 'CO%' OR id_sub LIKE 'GA%'";
            break;
            case "Ingreso":
                cond1 = "WHERE id_cta LIKE 'I%'";
                cond2 = "WHERE id_sub LIKE 'I%'";
            break;
        }
        query.seleccion("*", "cuenta", cond1);
        res = query.getRes();
        try {
            while (res.next()){
                Cuenta c = new Cuenta();
                c.setId(res.getString("id_cta"));
                c.setNombre(res.getString("nombre_cta"));
                c.setDeber(Double.parseDouble(res.getString("deber_cta")));
                c.setHaber(Double.parseDouble(res.getString("haber_cta")));
                c.setSaldo(Double.parseDouble(res.getString("saldo_cta")));
                ctas.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
        
        query.seleccion("*", "subcuenta", cond2);
        res = query.getRes();
        try {
            while (res.next()){
                Cuenta c = new Cuenta();
                c.setId(res.getString("id_sub"));
                c.setNombre(res.getString("nom_sub"));
                c.setDeber(Double.parseDouble(res.getString("deb_sub")));
                c.setHaber(Double.parseDouble(res.getString("hab_sub")));
                c.setSaldo(Double.parseDouble(res.getString("saldo_sub")));
                subCtas.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
        
        for(Cuenta c : ctas){
            this.cuentas.add(c);
            for(Cuenta s : subCtas){
                if(c.getId().substring(0,5).equals(s.getId().substring(0,5))){
                    this.cuentas.add(s);
                }
            }
        }
        query.Desconectar();
        return this.cuentas;
    }

    public boolean borrarCta(String idCta) {
        query = new Query();
        Boolean valor;
        String consulta;
        if(idCta.length() > 6){ //es subcuenta
            consulta = "DELETE FROM subcuenta WHERE id_sub = '" + idCta +"';";
            valor = query.ejecuta(consulta);
        }else{  //es cuenta principal
            consulta = "DELETE FROM cuenta WHERE id_cta = '" + idCta +"';";
            valor = query.ejecuta(consulta);
        }
        query.Desconectar();
        return valor;
    }

    public boolean resetNominales(){
        query = new Query();
        String cons = "UPDATE cuenta SET deber_cta=0, haber_cta=0, saldo_cta=0  WHERE id_cta LIKE 'IN%' OR id_cta LIKE 'CO%' OR id_cta LIKE 'GA%';";   
        if(query.ejecuta(cons)){
            String cons2 = "UPDATE subcuenta SET deb_sub=0, hab_sub=0, saldo_sub=0  WHERE id_sub LIKE 'IN%' OR id_sub LIKE 'CO%' OR id_sub LIKE 'GA%';";   
            if(query.ejecuta(cons2)){
                query.Desconectar();
                return true;
            }else{
                System.out.println("Error al limpiar subcuenta");
                query.Desconectar();
                return false;
            }
        }else{
            System.out.println("Error al limpiar cuenta");
            query.Desconectar();
            return false;
        }
    }

    public List<Cuenta> buscarCuentasYsub(String text, String idCuenta) {
         this.cuentas.clear();
        List <Cuenta> ctas = new ArrayList <>();
        List <Cuenta> subCtas = new ArrayList <>();
        ResultSet res = null;
        query = new Query();
        Boolean bandera = false;
        String cond1 = "WHERE (nombre_cta LIKE '%" + text + "%') AND id_cta != '" + idCuenta + "'";
        String cond2 = "WHERE (nom_sub LIKE '%" + text + "%') AND id_sub != '" + idCuenta + "'";
        query.seleccion("*", "cuenta", cond1);
        
        res = query.getRes();
        try {
            while (res.next()){
                Cuenta c = new Cuenta();
                c.setId(res.getString("id_cta"));
                c.setNombre(res.getString("nombre_cta"));
                c.setDeber(Double.parseDouble(res.getString("deber_cta")));
                c.setHaber(Double.parseDouble(res.getString("haber_cta")));
                c.setSaldo(Double.parseDouble(res.getString("saldo_cta")));
                ctas.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
        
        query.seleccion("*", "subcuenta", cond2);
        res = query.getRes();
        try {
            while (res.next()){
                Cuenta c = new Cuenta();
                c.setId(res.getString("id_sub"));
                c.setNombre(res.getString("nom_sub"));
                c.setDeber(Double.parseDouble(res.getString("deb_sub")));
                c.setHaber(Double.parseDouble(res.getString("hab_sub")));
                c.setSaldo(Double.parseDouble(res.getString("saldo_sub")));
                subCtas.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
        
        for(Cuenta c : ctas){
            bandera=true;
            this.cuentas.add(c);
            for(Cuenta s : subCtas){
                if(c.getId().equals(s.getId().substring(0,5))){
                    this.cuentas.add(s);
                }
            }
        }
        
        if(this.cuentas.isEmpty() || bandera){
            for(Cuenta s: subCtas){
                this.cuentas.add(s);
            }
        }
        query.Desconectar();
        return this.cuentas;
    }
}