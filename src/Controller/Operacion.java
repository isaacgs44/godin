/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author lenovo
 */
public class Operacion {
    private String id;
    private String ctaCargo;
    private String ctaAbono;
    private Double monto;
    private String fecha;
    private String desc;
    private String montoS; //monto en string para busqueda

    public String getMontoS() {
        return montoS;
    }

    public void setMontoS(String montoS) {
        this.montoS = montoS;
    }
    
    public Operacion(){
        this.id = null;
        this.ctaCargo = null;
        this.ctaAbono = null;
        this.monto = null;
        this.fecha = null;       
        this.desc = null;
    }
    
    public Operacion(String ctaC, String ctaA, Double monto, String desc){
        this.ctaCargo = ctaC;
        this.ctaAbono = ctaA;
        this.monto = monto;
        this.fecha = obtenerFecha();       
        this.desc = desc;
    }
    
     private String obtenerFecha(){
        Calendar c1 = new GregorianCalendar();
        Integer dia = c1.get(Calendar.DATE);
        String diaS;
        String mesS;
        Integer mes = c1.get(Calendar.MONTH)+1; //se suma 1 por que el 0 es enero
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
        return anio.toString() + mesS + diaS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCtaCargo() {
        return ctaCargo;
    }

    public void setCtaCargo(String ctaCargo) {
        this.ctaCargo = ctaCargo;
    }

    public String getCtaAbono() {
        return ctaAbono;
    }

    public void setCtaAbono(String ctaAbono) {
        this.ctaAbono = ctaAbono;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
