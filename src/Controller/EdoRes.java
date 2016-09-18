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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author lenovo
 */
public class EdoRes {
    private Double ventas;
    private Double costos;
    private Double gastos;
    private Double utB;
    private Double utN;
    private Query query;
    private DecimalFormat df = new DecimalFormat("$ ###,###.##");
    
    public EdoRes(String periodo){
        ventas = 0.0;
        costos = 0.0;
        gastos = 0.0;
        utN = 0.0;
        utB = 0.0;
        ResultSet res = null;
        query = new Query();
        String cond = "WHERE periodo_his = '" + periodo + "'";
        query.seleccion("*", "historico",cond);
        res = query.getRes();
        try {
            while (res.next()){
                switch(res.getString("idCta_his").substring(0,2)){
                    case "CO":
                        costos += Double.parseDouble(res.getString("saldoCta_his"));
                    break;
                    case "GA":
                        gastos += Double.parseDouble(res.getString("saldoCta_his"));
                    break;
                    case "IN":
                        ventas += Double.parseDouble(res.getString("saldoCta_his"));
                    break;
                }
            }
            utB = ventas - costos;
            utN = ventas - costos - gastos;
            query.Desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(LibroDiario.class.getName()).log(Level.SEVERE,null, ex);
            query.Desconectar();
        }
    }
    
    public TableModel creaModeloEdoRes(){
        TablaEdoRes modelo1 = new TablaEdoRes(5,3);  //numero de filas del ResultSet como parametro del constructor
        int i=0;
            modelo1.setValueAt("Ventas", 0, 0);
            modelo1.setValueAt(df.format(ventas), 0, 2);
            modelo1.setValueAt("Costos", 1, 0);
            modelo1.setValueAt(df.format(costos), 1, 2);
            modelo1.setValueAt("Utilidad Bruta", 2, 0);
            modelo1.setValueAt(df.format(utB), 2, 2);
            modelo1.setValueAt("Gastos", 3, 0);
            modelo1.setValueAt(df.format(gastos), 3, 2);
            modelo1.setValueAt("Utilidad Neta", 4, 0);
            modelo1.setValueAt(df.format(utN), 4, 2);
        return modelo1;
        
    }

    public Double getUtB() {
        return utB;
    }

    public Double getUtN() {
        return utN;
    }
    
    public Double getMargen(){
        return (utB/ventas)*100;
    }
    

    
}
