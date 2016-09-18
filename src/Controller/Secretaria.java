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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author lenovo
 */
public class Secretaria {
    private LibroMayor lMayor;
    private LibroDiario lDiario;
    private Query query;
    private DecimalFormat df = new DecimalFormat("#.##");
    public Secretaria(){
        lMayor = new LibroMayor();
        lDiario = new LibroDiario();
    }
    
    public void registrarVenta(Venta venta){
    
    }
    
    public void registrarPedido(Pedido pedido){
    
    }
    
    public void registrarPago(Cliente cliente, Double monto, Cuenta cta){
    
    }
    
    public void registrarRetiro(Cliente cliente, Double monto, Cuenta cta){
    
    }
    
    public void reporteVentas(String periodo){
    
    }
    
    public void reporteInventario(char opc){ // todo - Xtipo
    
    }
    
    public void reporteBalanceGral(){
    
    }
    
    public void reporteEdoRes(String periodo){
    
    }

    public Boolean registrarOperacion(String idctaC, String idctaA,Double monto, String desc) {
        query = new Query();
        Boolean bandera;
        Operacion op = new Operacion(idctaC, idctaA, monto, desc);
         String valores = "(fecha_op,idCtaC_op,idCtaA_op,desc_op,monto_op)"
                   + " VALUES ('" + op.getFecha() + "','" + op.getCtaCargo() + "','" + op.getCtaAbono() + "','" + op.getDesc() + "','" + op.getMonto().toString() + "')";                                   
        actualizaCtas(op);
        
        bandera = query.insertar("operacion", valores);
        query.Desconectar();
        return bandera;
    }
    
    private void actualizaCtas(Operacion op){
        LibroMayor m = new LibroMayor();
        Cuenta cargo;
        Cuenta abono;
        Cuenta prinC = null;
        Cuenta prinA = null;
        Boolean subCargo = false;
        Boolean subAbono = false;
        
        //CARGO - Deber - Debito
        if(op.getCtaCargo().length()>6){ //cta - cargo es subcuenta
            cargo = m.obtenerCuenta(op.getCtaCargo(), "s");
            LibroMayor m1 = new LibroMayor();
            prinC = m1.obtenerCuenta(cargo.getId().substring(0, 5), "c");
            prinC.setDeber(Double.parseDouble(df.format(prinC.getDeber() + op.getMonto())));
            
            if(prinC.getDeber() > prinC.getHaber()){ //saldo deudor  
                prinC.setSaldo(Double.parseDouble(df.format(prinC.getDeber() - prinC.getHaber())));
                switch(prinC.getId().substring(0,2)){
                    case "PC": case "PD": case "PF": case "CC": 
                        prinC.setSaldo(prinC.getSaldo() * -1);
                    break;
                }
            }else{ //saldo acredor
                prinC.setSaldo(Double.parseDouble(df.format(prinC.getHaber() - prinC.getDeber())));
                 switch(prinC.getId().substring(0,2)){
                    case "AF": case "AC": case "AD":
                        prinC.setSaldo(prinC.getSaldo() * -1);
                    break;
                }
            }
             subCargo = true;
        }else {
            cargo = m.obtenerCuenta(op.getCtaCargo(), "c");
            subCargo = false;
        }
        
        //ABONO - haber -crédito
        if(op.getCtaAbono().length()>6){ // es subcuenta
            abono = m.obtenerCuenta(op.getCtaAbono(), "s");
            LibroMayor m1 = new LibroMayor();
            prinA = m1.obtenerCuenta(abono.getId().substring(0, 5), "c"); //principal
            prinA.setHaber(Double.parseDouble(df.format(prinA.getHaber() + op.getMonto())));
            if(prinA.getDeber() > prinA.getHaber()){ //saldo deudor
                prinA.setSaldo(Double.parseDouble(df.format(prinA.getDeber() - prinA.getHaber())));
                  switch(prinA.getId().substring(0,2)){
                   case "PC": case "PD": case "PF": case "CC": 
                        prinA.setSaldo(prinA.getSaldo() * -1);
                    break;
                }
            }else{ //saldo acredor
                prinA.setSaldo(Double.parseDouble(df.format(prinA.getHaber() - prinA.getDeber())));
                switch(prinA.getId().substring(0,2)){
                    case "AF": case "AC": case "AD":
                        prinA.setSaldo(prinA.getSaldo() * -1);
                    break;
                }
            }
            subAbono = true;
        }else {
            abono = m.obtenerCuenta(op.getCtaAbono(), "c");
            subAbono = false;
        }
          
        //cuenta cargo
        System.out.println(cargo);
        System.out.println(cargo.getDeber());
        cargo.setDeber(Double.parseDouble(df.format(cargo.getDeber() + op.getMonto())));
        if(cargo.getDeber() > cargo.getHaber()){ //saldo deudor
            cargo.setSaldo(Double.parseDouble(df.format(cargo.getDeber() - cargo.getHaber())));
             switch(cargo.getId().substring(0,2)){
                   case "PC": case "PD": case "PF": case "CC": 
                        cargo.setSaldo(cargo.getSaldo() * -1);
                    break;
                }
        }else{ //saldo acredor
            cargo.setSaldo(Double.parseDouble(df.format(cargo.getHaber() - cargo.getDeber())));
            switch(cargo.getId().substring(0,2)){
                    case "AF": case "AC": case "AD":
                        cargo.setSaldo(cargo.getSaldo() * -1);
                    break;
                }
        }
         
        //cta abono
        abono.setHaber(Double.parseDouble(df.format(abono.getHaber() + op.getMonto())));
        if(abono.getHaber() > abono.getDeber()){ //saldo acredor
            abono.setSaldo(Double.parseDouble(df.format(abono.getHaber() - abono.getDeber())));
             switch(abono.getId().substring(0,2)){
                case "AF": case "AC": case "AD":
                  abono.setSaldo(abono.getSaldo() * -1);
                break;
                }
        }else{ //saldo deudor
            abono.setSaldo(Double.parseDouble(df.format(abono.getDeber() - abono.getHaber())));
            switch(abono.getId().substring(0,2)){
                case "PC": case "PD": case "PF": case "CC": 
                    abono.setSaldo(abono.getSaldo() * -1);
                break;
                }
        }
        
        if(m.actualizarCta(cargo)){ //cuenta cargo
            System.out.println("cargo a cuenta: " + cargo.getNombre());
        }
        
        if(m.actualizarCta(abono)){ //cuenta abono
             System.out.println("abono a cuenta: " + abono.getNombre());
        }

        // si las subcuentas pertenecen a la misma principal, no se actualiza la principal
        if(!cargo.getId().substring(0, 5).equals(abono.getId().substring(0, 5))){ 
            if(subCargo){ //principal de cargo
                if(m.actualizarCta(prinC)){
                    System.out.println("cargo a cuenta principal: " + prinC.getNombre());
                }
            }
        
            if(subAbono){ //principal de abono
                if(m.actualizarCta(prinA)){
                    System.out.println("abono a cuenta principal: " + prinA.getNombre());
                }
            }
        }
    }

    public boolean cierraPeriodo(String fechaF) {
        Historico h = new Historico();
        String periodo = h.getFechaI()+"/"+fechaF;
        
        //abonarComision(periodo); // abono de comision para aguinaldo
        Double utN = calculaUtPeriodo(); //luego ya calcula utilidad
        registrarIndicadores(periodo);
        registrarUtilidad(utN);
        System.out.println("Utilidad: " + utN);
        System.out.println("Fecha inicio : " + periodo);
        registrarHistorico(periodo); 
        
        return true;
    }
    
    private Double calculaUtPeriodo(){
        LibroMayor m = new LibroMayor();
        List <Cuenta> cts = m.obtenerCuentas();
        Double ingresos = 0.0;
        Double egresos = 0.0;
        for (Cuenta c : cts){
            if(c.getId().substring(0, 2).equals("IN")){
                ingresos += c.getSaldo();
            }else if (c.getId().substring(0,2).equals("GA")){
                egresos += c.getSaldo();
            }else if(c.getId().substring(0,2).equals("CO")){
                egresos += c.getSaldo();
            }
        }
        return ingresos - egresos;
    }
/*
    private void abonarComision(String periodo) {
        Vendedor v = new Vendedor();
        List <Vendedor> vends = v.obtenerVendedores();
        for (Vendedor v1 : vends){
            Double comision = obtenerComision(v1, periodo);
            if(comision != 0.0){
                registrarOperacionSimple("AC004-001",comision, "Comisión "+ v1.getComision().toString() + " - " + v1.getNombre(),"a"); // AC004-001 cuenta alma
            }
        }
    }

    private Double obtenerComision(Vendedor v1, String periodo) {
        query = new Query();
        Double ut = 0.0;
        String diaI = periodo.substring(0,2);
        String mesI = periodo.substring(3,5);
        String anioI = periodo.substring(6,10);
        String diaF = periodo.substring(11,13);
        String mesF = periodo.substring(14,16);
        String anioF = periodo.substring(17,21);
        if(query.seleccion("*", "compra", "WHERE (vend_comp = '" + v1.getNombre() + "') AND (dia >=" + diaI + " AND mes >= " + mesI + " AND anio >= " + anioI + ") AND ( dia <= " + diaF + " AND mes <= " + mesF + " AND anio <= " + anioF + " ) AND registro_historico = 0")){
            ResultSet res = query.getRes();
            try {
                while(res.next()){
                    Double neto = Double.parseDouble(res.getString("neto_comp")); 
                    Double costoV = Double.parseDouble(res.getString("costoV_comp"));
                    ut += (neto-costoV);
                }
               
            } catch (SQLException ex) {
                Logger.getLogger(Secretaria.class.getName()).log(Level.SEVERE, null, ex);
            }
            query.Desconectar();
            return (ut * (v1.getComision()/100));
        }else{
            query.Desconectar();
            return 0.0;
        }
    }*/

    private void registrarHistorico(String periodo) {
        query = new Query();
        LibroMayor m = new LibroMayor();
        List <Cuenta> cts = m.obtenerCuentas();
        String valores;
        
        for (Cuenta c : cts){
            switch(c.getId().substring(0,2)){
                
                case "AC": case "AF": case "AD": case "PC": case "PF": case "PD": case "CC":
                   valores = "(idCta_his,nomCta_his,saldoCta_his,periodo_his)"
                   + " VALUES ('" + c.getId() + "','" + c.getNombre() + "','" + c.getSaldo().toString() + "','" + periodo + "')";                                   
                   if(query.insertar("historico", valores))
                       System.out.println("historico de cuentas reales registrado");
                   else
                       System.out.println("Error al registrar histórico de cuentas reales");
                break;
                case "IN": case "GA": case "CO":
                   valores = "(idCta_his,nomCta_his,saldoCta_his,periodo_his)"
                   + " VALUES ('" + c.getId() + "','" + c.getNombre() + "','" + c.getSaldo().toString() + "','" + periodo + "')";                                   
                    
                   if(query.insertar("historico", valores))
                       System.out.println("historico de cuentas nominales registrado");
                   else
                       System.out.println("Error al registrar histórico de cuentas nominales");
                   
                    if(m.resetNominales())
                       System.out.println("Cuentas Nominales limpias");
                   else
                       System.out.println("Error al limpiar cuentas nominales");
                break;
            }    
        }
        Venta v = new Venta();
        v.marcaRegHistorico(); // marco como registradas las ventas al momento
        query.Desconectar();
    }

    private void registrarUtilidad(Double abonoS) {
        LibroMayor m = new LibroMayor();
        Cuenta utC;
        Cuenta utS;
        Double abonoC = abonoS; 
        utS = m.obtenerCuenta("CC001-000", "s"); // CC001-000 - cuenta Utilidades acumuladas
        utC = m.obtenerCuenta("CC001", "c"); // cuenta principal Utilidad
        if(utS.getSaldo() != 0.0){
            abonoS += utS.getHaber();
        }          
        utS.setHaber(abonoS);
        utS.setSaldo(abonoS);
        if(utC.getSaldo() != 0.0){
            abonoC += utC.getHaber();
        }          
        utC.setHaber(abonoC);
        utC.setSaldo(abonoC);
        if(m.actualizarCta(utS)){ 
            m.actualizarCta(utC);
            System.out.println("UTILIDAD REGSTRADA: " + utS.getNombre());
             
        }else{
            System.out.println("ERROR AL REGISTRAR UTILIDAD");
    }
    }

    public boolean registrarOperacionSimple(String idCta, Double monto, String desc, String tipo) {
        query = new Query();
        Boolean bandera;
        Operacion op;
        String valores;
        switch(tipo){
            case "c":
                 op = new Operacion(idCta, "-", monto, desc);
                 valores = "(fecha_op,idCtaC_op,idCtaA_op,desc_op,monto_op)"
                   + " VALUES ('" + op.getFecha() + "','" + op.getCtaCargo() + "','" + op.getCtaAbono() + "','" + op.getDesc() + "','" + op.getMonto().toString() + "')";       
                actualizaCta(op);
                bandera = query.insertar("operacion", valores);
                query.Desconectar();
                return bandera;
           
            case "a":
                 op = new Operacion("-",idCta, monto, desc);
                 valores = "(fecha_op,idCtaC_op,idCtaA_op,desc_op,monto_op)"
                   + " VALUES ('" + op.getFecha() + "','" + op.getCtaCargo() + "','" + op.getCtaAbono() + "','" + op.getDesc() + "','" + op.getMonto().toString() + "')";       
                actualizaCta(op);
                bandera = query.insertar("operacion", valores);
                query.Desconectar();
                return bandera;
        }
        return false;
    }

    private void actualizaCta(Operacion op) {
        LibroMayor m = new LibroMayor();
        Cuenta cargo;
        Cuenta abono;
        Cuenta prinC = null;
        Cuenta prinA = null;
        Boolean bandera1 = false;
        Boolean bandera2 = false;
        
        //CARGO - Deber - Debito
        if(op.getCtaCargo().length()>6){ //cta - cargo es subcuenta
            cargo = m.obtenerCuenta(op.getCtaCargo(), "s");
            LibroMayor m1 = new LibroMayor();
            prinC = m1.obtenerCuenta(cargo.getId().substring(0, 5), "c");
            prinC.setDeber(Double.parseDouble(df.format(prinC.getDeber() + op.getMonto())));
            
            if(prinC.getDeber() > prinC.getHaber()){ //saldo deudor  
                prinC.setSaldo(Double.parseDouble(df.format(prinC.getDeber() - prinC.getHaber())));
                switch(prinC.getId().substring(0,2)){
                    case "PC": case "PD": case "PF": case "CC": 
                        prinC.setSaldo(prinC.getSaldo() * -1);
                    break;
                }
            }else{ //saldo acredor
                prinC.setSaldo(Double.parseDouble(df.format(prinC.getHaber() - prinC.getDeber())));
                 switch(prinC.getId().substring(0,2)){
                    case "AF": case "AC": case "AD":
                        prinC.setSaldo(prinC.getSaldo() * -1);
                    break;
                }
            }
             bandera1 = true;
        }else if (op.getCtaCargo().length()==5){
            cargo = m.obtenerCuenta(op.getCtaCargo(), "c");
            bandera1 = false;
        }else {
            cargo=null;
        }
        
        //ABONO - haber -crédito
        if(op.getCtaAbono().length()>6){ // es subcuenta
            abono = m.obtenerCuenta(op.getCtaAbono(), "s");
            LibroMayor m1 = new LibroMayor();
            prinA = m1.obtenerCuenta(abono.getId().substring(0, 5), "c"); //principal
            prinA.setHaber(Double.parseDouble(df.format(prinA.getHaber() + op.getMonto())));
            if(prinA.getDeber() > prinA.getHaber()){ //saldo deudor
                prinA.setSaldo(Double.parseDouble(df.format(prinA.getDeber() - prinA.getHaber())));
                  switch(prinA.getId().substring(0,2)){
                   case "PC": case "PD": case "PF": case "CC": 
                        prinA.setSaldo(prinA.getSaldo() * -1);
                    break;
                }
            }else{ //saldo acredor
                prinA.setSaldo(Double.parseDouble(df.format(prinA.getHaber() - prinA.getDeber())));
                switch(prinA.getId().substring(0,2)){
                    case "AF": case "AC": case "AD":
                        prinA.setSaldo(prinA.getSaldo() * -1);
                    break;
                }
            }
            bandera2 = true;
        }else if (op.getCtaAbono().length()==5){
            abono = m.obtenerCuenta(op.getCtaAbono(), "c");
            bandera2 = false;
        }else{
            abono = null;
        }
          
        if(cargo!=null){
            //cuenta cargo
            cargo.setDeber(Double.parseDouble(df.format(cargo.getDeber() + op.getMonto())));
            if(cargo.getDeber() > cargo.getHaber()){ //saldo deudor
                cargo.setSaldo(Double.parseDouble(df.format(cargo.getDeber() - cargo.getHaber())));
                switch(cargo.getId().substring(0,2)){
                    case "PC": case "PD": case "PF": case "CC": 
                        cargo.setSaldo(cargo.getSaldo() * -1);
                    break;
                }
            }else{ //saldo acredor
                cargo.setSaldo(Double.parseDouble(df.format(cargo.getHaber() - cargo.getDeber())));
                switch(cargo.getId().substring(0,2)){
                    case "AF": case "AC": case "AD":
                        cargo.setSaldo(cargo.getSaldo() * -1);
                    break;
                }
            }
            if(m.actualizarCta(cargo)){ //cuenta cargo
                System.out.println("cargo a cuenta: " + cargo.getNombre());
            }
        } 
        //cta abono
        if(abono!=null){
            abono.setHaber(Double.parseDouble(df.format(abono.getHaber() + op.getMonto())));
            if(abono.getHaber() > abono.getDeber()){ //saldo acredor
                abono.setSaldo(Double.parseDouble(df.format(abono.getHaber() - abono.getDeber())));
                switch(abono.getId().substring(0,2)){
                    case "AF": case "AC": case "AD":
                      abono.setSaldo(abono.getSaldo() * -1);
                    break;
                }
            }else{ //saldo deudor
                abono.setSaldo(Double.parseDouble(df.format(abono.getDeber() - abono.getHaber())));
                switch(abono.getId().substring(0,2)){
                    case "PC": case "PD": case "PF": case "CC": 
                        abono.setSaldo(abono.getSaldo() * -1);
                    break;
                }
            }
             if(m.actualizarCta(abono)){ //cuenta abono
                System.out.println("abono a cuenta: " + abono.getNombre());
            }
        }

        if(bandera1){ //principal de cargo
            if(m.actualizarCta(prinC)){
                System.out.println("cargo a cuenta principal: " + prinC.getNombre());
            }
        }
        
        if(bandera2){ //principal de abono
            if(m.actualizarCta(prinA)){
                System.out.println("abono a cuenta principal: " + prinA.getNombre());
            }
        }
    }

    private void registrarIndicadores(String periodo) {
        query = new Query();
        Indicadores ind = new Indicadores();
        String valores;
        valores = "(nom_ind,valor_ind,periodo_ind)"
                   + " VALUES ('Efectivo','" + df.format(ind.getEfectivo()) + "','" + periodo + "'),"
                   + "('Deudores','" + df.format(ind.getDeben()) + "','" + periodo + "'),"
                   + "('Inversion','" + df.format(ind.getInversion()) + "','" + periodo + "'),"
                   + "('Pasivo','" + df.format(ind.getPasivo()) + "','" + periodo + "'),"
                   + "('Liquidez','" + df.format(ind.getLiquidez()) + "','" + periodo + "'),"
                   + "('Utilidad promedio','" + df.format(ind.getUtProm()) + "','" + periodo + "'),"
                   + "('Pagos del periodo','" + df.format(ind.getPagosCurso()) + "','" + periodo + "'),"
                   + "('Clientes activos','" + ind.getNumClActivos().toString() + "','" + periodo + "'),"
                   + "('Ventas del periodo','" + ind.getNumVen().toString() + "','" + periodo + "'),"
                   + "('Rendimiento sobre activo','" + df.format(ind.getRendSInv()) + "','" + periodo + "'),"
                   + "('Rendimiento sobre capital','" + df.format(ind.getRendSCap()) + "','" + periodo + "'),"
                   + "('Multiplicador de capital','" + df.format(ind.getMultiCapital()) + "','" + periodo + "'),"
                   + "('Margen neto','" + df.format(ind.getMargenGanancia()) + "','" + periodo + "'),"
                   + "('Rentabilidad Dupont','" + df.format(ind.getIndDupont()) + "','" + periodo + "'),"
                   + "('Inventario promedio','" + df.format(ind.getInventarioProm()) + "','" + periodo + "'),"
                   + "('Rotacion de inventario','" + df.format(ind.getRotInventario()) + "','" + periodo + "'),"
                   + "('Dias para vender inv','" + df.format(ind.getDiasVentaInv()) + "','" + periodo + "'),"
                   + "('Deuda promedio clientes','" + df.format(ind.getDeudaPromClientes()) + "','" + periodo + "'),"
                   + "('Rotacion cartera promedio','" + df.format(ind.getRotCarProm()) + "','" + periodo + "'),"
                   + "('Periodo de credito promedio','" + df.format(ind.getDiasParaCobrar()) + "','" + periodo + "'),"
                   + "('Ventas pendientes por recuperar','" + df.format(ind.getVentasPendientesDCobro()) + "','" + periodo + "'),"
                   + "('Periodo de credito','" + df.format(ind.getRotCar()) + "','" + periodo + "'),"
                   + "('Total de ventas','" + df.format(ind.getMontoVentas()) + "','" + periodo + "'),"
                   + "('Monto en sueldos','" + df.format(ind.getSalarios()) + "','" + periodo + "'),"
                   + "('Clientes inactivos','" + ind.getNumCl().toString() + "','" + periodo + "')"
                ;
        
                   if(query.insertar("indicadores", valores))
                       System.out.println("historico de indicadores registrado");
                   else
                       System.out.println("Error al registrar histórico de indicadores");
        query.Desconectar();
    }
}
