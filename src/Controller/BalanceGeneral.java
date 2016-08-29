/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.TableModel;

/**
 *
 * @author lenovo
 */
public class BalanceGeneral {
    private List <Cuenta> cuentas;
    private Integer numCtsAct;
    private Integer numCtsPas;
    private Integer numCtsCap;
    private Double sumaAct;
    private Double sumaPas;
    private Double sumaCap;
    private Double dif;
    private String periodo; 
    private Double utCurso;
    private DecimalFormat df = new DecimalFormat("###,###.##");
    private String cadenaAux;
    private Utilidades uts;
    
    public BalanceGeneral(){
        LibroMayor m = new LibroMayor();
        cuentas = m.obtenerCuentas();
        numCtsAct = 0;
        numCtsPas = 0;
        numCtsCap = 0;
        sumaAct = 0.0;
        sumaPas = 0.0;
        sumaCap = 0.0;
        utCurso = 0.0;
        calculaValores();
        cadenaAux = "Ut. en curso: ";
         uts = new Utilidades();
    }
    
    public BalanceGeneral(String periodo){
       Historico h = new Historico();
       this.cuentas = h.obtenerHistorico(periodo);
        numCtsAct = 0;
        numCtsPas = 0;
        numCtsCap = 0;
        sumaAct = 0.0;
        sumaPas = 0.0;
        sumaCap = 0.0;
        utCurso = 0.0;
        calculaValores();
        uts = new Utilidades();
    }
    
    
    public final void calculaValores(){
        for(Cuenta c : cuentas){
            switch(c.getId().substring(0,2)){
                case "AF": case "AC": case "AD":
                    sumaAct += c.getSaldo();
                    numCtsAct ++;
                break;
                case "PC": case "PF": case "PD":
                   sumaPas += c.getSaldo();
                   numCtsPas ++;
                break;
                case "CC":
                    sumaCap += c.getSaldo();
                    numCtsCap ++;
                break;
                case "IN":
                    utCurso += c.getSaldo();
                break;
                case "CO" : case "GA":
                    utCurso -= c.getSaldo();
                break;
            }
        }
        if(sumaAct == (sumaCap + sumaPas)){
            this.dif = 0.0;
        }else if(sumaAct > (sumaCap + sumaPas)){
            dif = sumaAct - (sumaCap + sumaPas);
        }else if(sumaAct < (sumaCap + sumaPas)){
            dif = (sumaCap + sumaPas) - sumaAct;
        }
    }

    public TableModel creaModeloTab(BalanceGeneral balGen){
        TblBalGen tabla;
        if(balGen.getNumCtsAct() > (balGen.getNumCtsPas() + balGen.getNumCtsCap())){  
            tabla = new TblBalGen(balGen.getNumCtsAct()+ 5, 4);
        }else{
            tabla = new TblBalGen((balGen.getNumCtsPas() + balGen.getNumCtsCap()) + 5, 4);
        }
        int i = 0;
        for(Cuenta c : balGen.getCuentas()){ //busco cuentas de activo
             if(i == 0){ // titulo
                tabla.setValueAt("ACTIVO", i, 0);
                i++;
            }
            switch(c.getId().substring(0,2)){ 
              case "AC": case "AF": case "AD":
                tabla.setValueAt(c.getNombre(), i, 0);
                tabla.setValueAt(df.format(c.getSaldo()), i, 1);
                i++;
              break;
            }
        }
        i++;
        tabla.setValueAt("Total A:", i, 0);
        tabla.setValueAt(df.format(balGen.getSumaAct()), i, 1);
        
        int j = 0;
        for(Cuenta c : balGen.getCuentas()){ //busco ctas pasivo
             if(j == 0){ // titulo
                tabla.setValueAt("PASIVO    Y", j, 2);
                tabla.setValueAt("CAPITAL", j, 3);
                j++;
            }
            switch(c.getId().substring(0,2)){
                case "PC": case "PF": case "PD":
                    tabla.setValueAt(c.getNombre(), j, 2);
                    tabla.setValueAt(df.format(c.getSaldo()), j, 3);
                    j++;
                break;
            }
        }
                
        for(Cuenta c : balGen.getCuentas()){ //busco ctas capital
            switch(c.getId().substring(0,2)){
                case "CC":
                    tabla.setValueAt(c.getNombre(), j, 2);
                    tabla.setValueAt(df.format(c.getSaldo()), j, 3);
                    j++;
                break;
            }
        }
        Double utCurso = 0.0;
        for(Cuenta c : balGen.getCuentas()){
            switch(c.getId().substring(0,2)){
                case "IN":
                    utCurso += c.getSaldo();
                break;
                case "CO" : case "GA":
                    utCurso -= c.getSaldo();
                break;
            }
        }
        this.utCurso = utCurso;
        tabla.setValueAt(cadenaAux,j, 2);
        tabla.setValueAt(df.format(utCurso), j, 3);
        j++;
        tabla.setValueAt("Total PC: ", i, 2);
        tabla.setValueAt(df.format(balGen.getSumaPas() + balGen.getSumaCap() + utCurso) , i, 3);
        if(!uts.format2(balGen.getSumaPas() + balGen.getSumaCap() + utCurso).equals(uts.format2(balGen.getSumaAct()))){
           i+=2;
           tabla.setValueAt("Diferencia: ", i, 1);
           tabla.setValueAt(df.format(balGen.getSumaAct() - (balGen.getSumaPas() + balGen.getSumaCap() + utCurso)) , i, 2);
        }
        return tabla;
    }
    
    public TableModel creaModeloHistorico(BalanceGeneral balGen){
        TblBalGen tabla;
        if(balGen.getNumCtsAct() > (balGen.getNumCtsPas() + balGen.getNumCtsCap())){  
            tabla = new TblBalGen(balGen.getNumCtsAct()+ 5, 4);
        }else{
            tabla = new TblBalGen((balGen.getNumCtsPas() + balGen.getNumCtsCap()) + 5, 4);
        }
        int i = 0;
        for(Cuenta c : balGen.getCuentas()){ //busco cuentas de activo
             if(i == 0){ // titulo
                tabla.setValueAt("ACTIVO", i, 0);
                i++;
            }
            switch(c.getId().substring(0,2)){ 
              case "AC": case "AF": case "AD":
                tabla.setValueAt(c.getNombre(), i, 0);
                tabla.setValueAt(df.format(c.getSaldo()), i, 1);
                i++;
              break;
            }
        }
        i++;
        tabla.setValueAt("Total A:", i, 0);
        tabla.setValueAt(df.format(balGen.getSumaAct()), i, 1);
        
        int j = 0;
        for(Cuenta c : balGen.getCuentas()){ //busco ctas pasivo
             if(j == 0){ // titulo
                tabla.setValueAt("PASIVO    Y", j, 2);
                tabla.setValueAt("CAPITAL", j, 3);
                j++;
            }
            switch(c.getId().substring(0,2)){
                case "PC": case "PF": case "PD":
                    tabla.setValueAt(c.getNombre(), j, 2);
                    tabla.setValueAt(df.format(c.getSaldo()), j, 3);
                    j++;
                break;
            }
        }
                
        for(Cuenta c : balGen.getCuentas()){ //busco ctas capital
            switch(c.getId().substring(0,2)){
                case "CC":
                    tabla.setValueAt(c.getNombre(), j, 2);
                    tabla.setValueAt(df.format(c.getSaldo()), j, 3);
                    j++;
                break;
            }
        }
        
        j++;
        tabla.setValueAt("Total PC: ", i, 2);
        tabla.setValueAt(df.format(balGen.getSumaPas() + balGen.getSumaCap()) , i, 3);
        if(!uts.format2(balGen.getSumaPas() + balGen.getSumaCap()).equals(uts.format2(balGen.getSumaAct()))){
           i+=2;
           tabla.setValueAt("Diferencia: ", i, 1);
           tabla.setValueAt(df.format(balGen.getSumaAct() - (balGen.getSumaPas() + balGen.getSumaCap())) , i, 2);
        }
        return tabla;
    }
    
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Integer getNumCtsAct() {
        return numCtsAct;
    }

    public void setNumCtsAct(Integer numCtsAct) {
        this.numCtsAct = numCtsAct;
    }

    public Integer getNumCtsPas() {
        return numCtsPas;
    }

    public void setNumCtsPas(Integer numCtsPas) {
        this.numCtsPas = numCtsPas;
    }

    public Integer getNumCtsCap() {
        return numCtsCap;
    }

    public void setNumCtsCap(Integer numCtsCap) {
        this.numCtsCap = numCtsCap;
    }

    public Double getSumaAct() {
        return sumaAct;
    }

    public void setSumaAct(Double sumaAct) {
        this.sumaAct = sumaAct;
    }

    public Double getSumaPas() {
        return sumaPas;
    }

    public void setSumaPas(Double sumaPas) {
        this.sumaPas = sumaPas;
    }

    public Double getSumaCap() {
        return sumaCap;
    }

    public void setSumaCap(Double sumaCap) {
        this.sumaCap = sumaCap;
    }

    public Double getDif() {
        return dif;
    }

    public void setDif(Double dif) {
        this.dif = dif;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Double getUtCurso() {
        return utCurso;
    }
    
    
}
