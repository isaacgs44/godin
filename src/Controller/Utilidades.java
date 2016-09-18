/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.DialogoModificarProd;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author lenovo
 */
public class Utilidades {
    
    public String obtFechaStringDMA(){
        Calendar c1 = new GregorianCalendar();
        Integer dia = c1.get(Calendar.DATE);
        String diaS;
        String mesS;
        Integer mes =  c1.get(Calendar.MONTH)+1; //se suma 1 por que el 0 es enero
        Integer anio = c1.get(Calendar.YEAR);
        if(dia<10){
            diaS = "0"+dia.toString();
        }else{
            diaS = dia.toString();
        }
        if(mes<10){
            mesS = "0"+mes.toString();
        }else{
            mesS = mes.toString();
        }        
        return diaS + "-" + mesS + "-" + anio.toString();
    }
    
    public Integer obtDia(){
        Calendar c1 = new GregorianCalendar();
        return c1.get(Calendar.DATE);
    }
    
    public Integer obtMes(){
        Calendar c1 = new GregorianCalendar();
        return c1.get(Calendar.MONTH)+1;
    }
    
    public Integer obtAnio(){
        Calendar c1 = new GregorianCalendar();
        return c1.get(Calendar.YEAR);
    }
    
    public Boolean CompararStrings(String cad1, String cad2){ //cadena 1 dentro de cadena 2
        System.out.println("cadena: " + cad1 + " - dentro de: " + cad2);
        return (Pattern.matches("^([a-z,A-Z,0-9,-,_,:, ,.])*(" + cad1 + ")([a-z,A-Z,0-9,-,_,:, ,.])*$",cad2));   
    }
    
    public String formatDinero(Double cantidad){
        DecimalFormat df = new DecimalFormat("$###,###.##");
        return df.format(cantidad);
    }
    
     public String formatPorcentaje(Double cantidad){
        DecimalFormat df = new DecimalFormat("%##.##");
        return df.format(cantidad);
    }

    public String format2(Double cantidad) {
        DecimalFormat df = new DecimalFormat("##.##");
        return df.format(cantidad);
    }
    
    public void ventanaError(javax.swing.JFrame ventana, String mensaje, String titulo){
        JOptionPane.showOptionDialog(ventana, mensaje, titulo, JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK");
    }

    public void ventanaInfo(javax.swing.JFrame ventana, String mensaje, String titulo) {
        JOptionPane.showOptionDialog(ventana, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "},"OK");         
    }
}

