
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author lenovo
 */
public class Indicadores {
    private Double efectivo;
    private Double inversion;
    private Double pasivo;
    private Double deben;
    private Double RendSInv; // Rendimiento sobre Inversión
    private Double RendSCap; // Rendimiento sobre capital
    private Double MargenGanancia; //margen de ganancia
    private Double MultiCapital; //multiplicador de capital
    private Double RotInventario; //ROtacion de inventario
    private Double RotAct; //Rotación de activo
    private Double RotCarProm; // Rotación de cartera promedio (se toma el saldo pormedio de las cuentas por cobrar)
    private Double RotCar; // Rotación de cartera (saldo al fin del periodo de la cuenta por cobrar)
    private Double DiasVentaInv; //Dias para venta de inventario
    private Double UtProm; //Utilidad promedio
    private Double PorInv; //Porcentaje de inversión
    private Double PorDeb; //Porcentaje que deben
    private Double PorLiq; //Porcentaje de liquidez
    private Double PorPasivo; //Porcentaje de liquidez
    private Double PagosCurso; //Pagos promedio
    private Integer NumCl; //numero de clientes
    private Integer NumVen; //Numero de ventas
    private Double Salarios; //Dinero en salarios
    private List <Cuenta> ctasHistorico;
    private Integer NumPeriodos;
    private BalanceGeneral balgen;
    private Query query;
    private Integer numClActivos; //número de clientes activos
    private Double montoVentas; //monto $ total de ventas
    private Double indDupont; // indicador de rentabilidad
    private Double inventarioProm;
    private Double deudaPromClientes;
    private Double DiasParaCobrar;
    private Double ventasPendientesDCobro;
    private Double sumaVentas;
    private Double sumaClientes;
    private String ultPeriodo;
    
    public Indicadores(){
        calcularIndicadores();
    }

    private void calcularIndicadores() {
        Historico h = new Historico();
        ctasHistorico = h.obtenerHistorico();
        this.NumPeriodos  = h.calculaNumPeriodos();
        balgen = new BalanceGeneral();
         calculaEfectivo();
         calculaPasivo();
         calculaInv();
         calculaDeben();
        if(NumPeriodos != 0){
            this.UtProm = calcUtPromedio();
            this.PorInv = calcPorInversion();
            this.PorDeb = calcPorDeben();
            this.PorPasivo = calcPorPasivo();
            calcPorLiquidez();
            this.PagosCurso = calcPagosEnCurso();
            calcNumeroClientes();
            calcNumVentas();
            this.RendSInv = RendSobreInv();
            this.RendSCap = RendSobreCap();
            this.MultiCapital = MultDCap();
            this.MargenGanancia = MargenNeto();
            this.RotAct = RotacionAct();
            this.indDupont = IndDupont();
            this.RotInventario = RotacionInventario();
            this.RotCarProm = RotacionCarteraProm();
            this.ventasPendientesDCobro = ventasPendentesDCobro();
            this.RotCar = RotacionCartera();
        }
    }
    
    private void calculaInv() {
        LibroMayor m = new LibroMayor();
         Cuenta almacen = m.obtenerCuenta("AC002", "c"); // ALmacen
         Cuenta cctv = m.obtenerCuenta("AC005", "c"); // CCTV
         Cuenta btc = m.obtenerCuenta("AC006", "c"); // bitcoin
         this.inversion = almacen.getSaldo() + cctv.getSaldo() + btc.getSaldo();
    }
    
    private void calculaDeben() {
        LibroMayor m = new LibroMayor();
         Cuenta clientes = m.obtenerCuenta("AC003", "c"); // clientes
         Cuenta deuDiv = m.obtenerCuenta("AC004", "c"); // deudores diversos
         this.deben = clientes.getSaldo() + deuDiv.getSaldo();
    }
    
    private void calculaPasivo(){
         Double pasivo = 0.0;
         LibroMayor m = new LibroMayor();
         List <Cuenta> cts = m.obtenerCuentas();
         for(Cuenta c : cts){
             switch(c.getId().substring(0, 2)){
                 case "PC":
                     pasivo += c.getSaldo();
                 break;
             }
         }
         this.pasivo = pasivo;
    }
    
    private void calculaEfectivo(){
         LibroMayor m = new LibroMayor();
         Cuenta caja = m.obtenerCuenta("AC000", "c"); // caja
         Cuenta bancos = m.obtenerCuenta("AC001", "c"); // bancos
         this.efectivo = caja.getSaldo() + bancos.getSaldo();
    }

    private Double calcUtPromedio() { //Calcula Utilidad promedio
        LibroMayor m = new LibroMayor();
        Cuenta utilidad = m.obtenerCuenta("CC001", "c"); //CC001 - Cuenta de Utilidad
        return (utilidad.getSaldo()/this.NumPeriodos);
    }

    private Double calcPorInversion() { //calcula porcentaje de inversión
        LibroMayor m = new LibroMayor();
        Cuenta almacen = m.obtenerCuenta("AC002", "c"); //AC002 - Cuenta de Almacen
        Cuenta cctv = m.obtenerCuenta("AC005", "c"); //AC005 - CCTV
        Cuenta bitc = m.obtenerCuenta("AC006", "c"); //AC006 - bitcoins 
        return ((almacen.getSaldo() + cctv.getSaldo() + bitc.getSaldo())/this.balgen.getSumaAct());
    }
    
     private Double calcPorPasivo(){
             return this.pasivo / (balgen.getSumaCap()+balgen.getSumaPas()+balgen.getUtCurso());
    }

    private Double calcPorDeben() {
        LibroMayor m = new LibroMayor();
        Cuenta clientes = m.obtenerCuenta("AC003", "c"); // "AC003" - Cuenta de Clientes
        Cuenta deudores = m.obtenerCuenta("AC004", "c"); // "AC004" - Deudores diversos3
        return (clientes.getSaldo()+deudores.getSaldo())/this.balgen.getSumaAct();
    }

    private void calcPorLiquidez() { // calcula Porcentaje de liquidez
        LibroMayor m = new LibroMayor();
        
        this.PorLiq =  (efectivo / this.balgen.getSumaAct());
    }

    private Double calcPagosEnCurso() { // Calcula el monto promedio de pagos
        query = new Query();
        ResultSet res;
        Historico h = new Historico();
        h.buscaPeriodos();
        String[] periodos = h.getPeriodos();
        String ultFecha = periodos[periodos.length-1].substring(9,19);
        this.ultPeriodo = ultFecha;
        Integer dia = Integer.parseInt(ultFecha.substring(0,2));
        Integer mes = Integer.parseInt(ultFecha.substring(3,5));
        Integer anio = Integer.parseInt(ultFecha.substring(6,10));
        
       
        String cond = "WHERE (dia_pago >= " + dia + " AND mes_pago >= " + mes + " AND anio_pago >= " + anio + ") OR (mes_pago > " + mes + " AND anio_pago >= " + anio +")";
        query.seleccion("monto_pago", "pago", cond);
        res = query.getRes();
        Double totalPagos = 0.0;
            try {
                while(res.next()){
                   totalPagos += Double.parseDouble(res.getString("monto_pago"));
                }   
            } catch (SQLException ex) {
                Logger.getLogger(Indicadores.class.getName()).log(Level.SEVERE, null, ex);
            }
            query.Desconectar();
            return totalPagos;
    }

    private void calcNumeroClientes() {
        query = new Query();
        query.consulta("SELECT cliente.id_cl, cliente.ctaClientes_cl, subcuenta.saldo_sub FROM cliente JOIN subcuenta WHERE cliente.ctaClientes_cl = subcuenta.id_sub;");
        ResultSet res = query.getRes();
        Integer clientes =0;
        Integer clientesActivos =0;
            try {
                while(res.next()){
                   
                    if(Double.parseDouble(res.getString("saldo_sub"))>0){
                        clientesActivos++;
                    }else{
                     clientes++;
                    }
                }  
                this.NumCl = clientes;
                this.numClActivos = clientesActivos;
            } catch (SQLException ex) {
                Logger.getLogger(Indicadores.class.getName()).log(Level.SEVERE, null, ex);
            }
            query.Desconectar();
    }

    private void calcNumVentas() {
        query = new Query();
         Historico h = new Historico();
        h.buscaPeriodos();
        String[] periodos = h.getPeriodos();
        String ultFecha = periodos[periodos.length-1].substring(9,17);
        Integer dia = Integer.parseInt(ultFecha.substring(0,2));
        Integer mes = Integer.parseInt(ultFecha.substring(3,5));
        Integer anio = Integer.parseInt("20" + ultFecha.substring(6,8));
        String cond = "WHERE dia >= " + dia + " AND mes >= " + mes + " AND anio >= " + anio;
        query.seleccion("*", "compra",cond);
        ResultSet res = query.getRes();
        Integer numVentas = 0;
        try {
            while(res.next()){
                numVentas ++;
            }
        this.NumVen = numVentas;
        } catch (SQLException ex) {
            Logger.getLogger(Indicadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        query.Desconectar();
    }
    
    private Double RendSobreInv() {
        LibroMayor m = new LibroMayor();
        Cuenta utilidad = m.obtenerCuenta("CC001", "c"); //CC001 - Cuenta de Utilidad               
        return ((utilidad.getSaldo() + balgen.getUtCurso())/this.balgen.getSumaAct());
    }
    
  
    
    private Double RendSobreCap() {
        LibroMayor m = new LibroMayor();
        Cuenta utilidad = m.obtenerCuenta("CC001", "c"); //CC001 - Cuenta de Utilidad               
        Cuenta capital = m.obtenerCuenta("CC000", "c"); // CC000 - capital contable
        return ((utilidad.getSaldo() + balgen.getUtCurso() ) / capital.getSaldo());
    }
    
    private Double MultDCap() {
        return (this.balgen.getSumaAct()/(this.balgen.getSumaCap() + balgen.getUtCurso()));
    } 
    
    private Double MargenNeto() {
        LibroMayor m = new LibroMayor();
        Cuenta utilidad = m.obtenerCuenta("CC001", "c"); //CC001 - Cuenta de Utilidad 
        Double utNeta = utilidad.getSaldo() + this.balgen.getUtCurso();
        
        Historico h = new Historico();
        List<Cuenta> cts = h.obtenerHistorico();
        Double sumaVentas = 0.0;
        Double sumaSueldo = 0.0;
        for(Cuenta c: cts){
            if(c.getId().equals("IN000")){
                sumaVentas += c.getSaldo();
            }
            if(c.getId().equals("GA000")){
                sumaSueldo += c.getSaldo();
            }
        }
        Cuenta ventas = m.obtenerCuenta("IN000", "c");
        sumaVentas += ventas.getSaldo();
        this.Salarios = sumaSueldo;
        this.montoVentas = sumaVentas;
        return (utNeta/sumaVentas);
    }
    
    private Double RotacionInventario() {
        Historico h = new Historico();
        List<Cuenta> cts = h.obtenerHistorico();
        Double inventarios = 0.0;
        Double costoV = 0.0;
        for(Cuenta c: cts){
            if(c.getId().equals("AC002")){
                inventarios += c.getSaldo();
            }
            if(c.getId().equals("CO000")){
                costoV += c.getSaldo();
            }
            
        }
        this.inventarioProm = inventarios/this.NumPeriodos;
        this.DiasVentaInv = (this.NumPeriodos * 30)/(costoV/inventarioProm);
        return (costoV/inventarioProm);
    }
    
    private Double RotacionCarteraProm() {
        Historico h = new Historico();
        List<Cuenta> cts = h.obtenerHistorico();
        Double clientes = 0.0;
        Double ventas = 0.0;
        for(Cuenta c: cts){
            if(c.getId().equals("AC003")){
                clientes += c.getSaldo();
            }
            if(c.getId().equals("IN000")){
                ventas += c.getSaldo();
            }
            
        }
        this.sumaClientes = clientes;
        this.sumaVentas = ventas;
        this.deudaPromClientes = clientes/this.NumPeriodos;
        this.DiasParaCobrar = (this.NumPeriodos * 30)/(ventas/deudaPromClientes);
        return (ventas/deudaPromClientes);
    }
    
    
    private Double ventasPendentesDCobro() {
        LibroMayor m = new LibroMayor();
        Double clientes = m.obtenerCuenta("AC003", "c").getSaldo();
        Double ventas = m.obtenerCuenta("IN000", "c").getSaldo();
        return ((clientes) / (sumaVentas + ventas));
    }
    
     private Double RotacionCartera() {
         return (this.ventasPendientesDCobro * (this.NumPeriodos*30));
     }
    
    private Double RotacionAct() {
        LibroMayor m = new LibroMayor();
        
        Historico h = new Historico();
        List<Cuenta> cts = h.obtenerHistorico();
        Double sumaVentas = 0.0;
        for(Cuenta c: cts){
            if(c.getId().equals("IN000")){
                sumaVentas += c.getSaldo();
            }
        }
        Cuenta ventas = m.obtenerCuenta("IN000", "c");
        sumaVentas += ventas.getSaldo();
        
        return (sumaVentas/balgen.getSumaAct());
    }
    
    private Double IndDupont() {
        return this.MargenGanancia * this.RotAct * this.MultiCapital;
    }
    
     public TableModel creaModeloInd(){
        Utilidades uts = new Utilidades();
        TablaIndicadores modelo1 = new TablaIndicadores(28);  //numero de filas del ResultSet como parametro del constructor
        modelo1.setValueAt("Efectivo:", 0, 0);
        modelo1.setValueAt(uts.formatDinero(efectivo) +" -> " + uts.formatPorcentaje(PorLiq), 0, 1);
        modelo1.setValueAt("Deudores:", 1, 0);
        modelo1.setValueAt(uts.formatDinero(deben) + " -> " + uts.formatPorcentaje(PorDeb), 1, 1);
        modelo1.setValueAt("Inversión:", 2, 0);
        modelo1.setValueAt(uts.formatDinero(inversion) + " -> " + uts.formatPorcentaje(PorInv), 2, 1);
        modelo1.setValueAt("Pasivo:", 3, 0);
        modelo1.setValueAt(uts.formatDinero(pasivo) + " -> " + uts.formatPorcentaje(PorPasivo), 3, 1);
        modelo1.setValueAt("Liquidez:", 4, 0);
        modelo1.setValueAt(uts.formatDinero(efectivo - pasivo), 4, 1);
        modelo1.setValueAt("Utilidad promedio:", 5, 0);
        modelo1.setValueAt(uts.formatDinero(this.UtProm), 5, 1);
        modelo1.setValueAt("Pagos en curso:", 6, 0);
        modelo1.setValueAt(uts.formatDinero(this.PagosCurso), 6, 1);
        modelo1.setValueAt("# Periodos:", 7, 0);
        modelo1.setValueAt(this.NumPeriodos, 7, 1);
        modelo1.setValueAt("Clientes activos:", 8, 0);
        modelo1.setValueAt(this.numClActivos, 8, 1);
        modelo1.setValueAt("# Ventas en curso:", 9, 0);
        modelo1.setValueAt(this.NumVen, 9, 1);
        modelo1.setValueAt("Rendimiento sobre Activo:", 10, 0);
        modelo1.setValueAt(uts.format2(this.RendSInv), 10, 1);
        modelo1.setValueAt("Rendimiento sobre Capital:", 11, 0);
        modelo1.setValueAt(uts.format2(this.RendSCap), 11, 1);
        modelo1.setValueAt("Multiplicador de Capital:", 12, 0);
        modelo1.setValueAt(uts.format2(this.MultiCapital), 12, 1);
        modelo1.setValueAt("Margen de gananica:", 13, 0);
        modelo1.setValueAt(uts.formatPorcentaje(this.MargenGanancia), 13, 1);
        modelo1.setValueAt("Rotacion de activos:", 14, 0);
        modelo1.setValueAt(uts.format2(this.RotAct), 14, 1); 
        modelo1.setValueAt("Rentabilidad DUPONT:", 15, 0);
        modelo1.setValueAt(uts.formatPorcentaje(this.indDupont), 15, 1);
        modelo1.setValueAt("Inventario promedio:", 16, 0);
        modelo1.setValueAt(uts.formatDinero(this.inventarioProm), 16, 1);
        modelo1.setValueAt("Rotación de inventario:", 17, 0);
        modelo1.setValueAt(uts.format2(this.RotInventario), 17, 1);
        modelo1.setValueAt("Dias para vender inventario:", 18, 0);
        modelo1.setValueAt(uts.format2(this.DiasVentaInv), 18, 1);
        modelo1.setValueAt("Deuda promedio clientes:", 19, 0);
        modelo1.setValueAt(uts.formatDinero(this.deudaPromClientes), 19, 1);
        modelo1.setValueAt("Rotación cartera promedio:", 20, 0);
        modelo1.setValueAt(uts.format2(this.RotCarProm), 20, 1);
        modelo1.setValueAt("Periodo de crédito promedio:", 21, 0);
        modelo1.setValueAt(uts.format2(this.DiasParaCobrar) + " dias", 21, 1);
        modelo1.setValueAt("Ventas pendientes por recuperar: ", 22, 0);
        modelo1.setValueAt(uts.formatPorcentaje(this.ventasPendientesDCobro), 22, 1);
        modelo1.setValueAt("Periodo de crédito: ", 23, 0);
        modelo1.setValueAt(uts.format2(this.RotCar) + " dias", 23, 1);
        modelo1.setValueAt("Total de ventas : ", 24, 0);
        modelo1.setValueAt(uts.formatDinero(this.montoVentas), 24, 1);
        modelo1.setValueAt("Monto en sueldos: ", 25, 0);
        modelo1.setValueAt(uts.formatDinero(this.Salarios), 25, 1);
        modelo1.setValueAt("Clientes inactivos: ", 26, 0);
        modelo1.setValueAt(this.NumCl, 26, 1);
        modelo1.setValueAt("ultimo periodo", 27, 0);
        modelo1.setValueAt(this.ultPeriodo, 27, 1);
        return modelo1;   
    }

    public Double getEfectivo() {
        return efectivo;
    }

    public Double getInversion() {
        return inversion;
    }

    public Double getPasivo() {
        return pasivo;
    }

    public Double getDeben() {
        return deben;
    }

    public Double getRendSInv() {
        return RendSInv;
    }

    public Double getRendSCap() {
        return RendSCap;
    }

    public Double getMargenGanancia() {
        return MargenGanancia;
    }

    public Double getMultiCapital() {
        return MultiCapital;
    }

    public Double getRotInventario() {
        return RotInventario;
    }

    public Double getRotAct() {
        return RotAct;
    }

    public Double getRotCarProm() {
        return RotCarProm;
    }

    public Double getRotCar() {
        return RotCar;
    }

    public Double getDiasVentaInv() {
        return DiasVentaInv;
    }

    public Double getUtProm() {
        return UtProm;
    }

    public Double getPorInv() {
        return PorInv;
    }

    public Double getPorDeb() {
        return PorDeb;
    }

    public Double getPorLiq() {
        return PorLiq;
    }

    public Double getPorPasivo() {
        return PorPasivo;
    }

    public Double getPagosCurso() {
        return PagosCurso;
    }

    public Integer getNumCl() {
        return NumCl;
    }

    public Integer getNumVen() {
        return NumVen;
    }

    public Double getSalarios() {
        return Salarios;
    }

    public Integer getNumPeriodos() {
        return NumPeriodos;
    }

    public Integer getNumClActivos() {
        return numClActivos;
    }

    public Double getMontoVentas() {
        return montoVentas;
    }

    public Double getIndDupont() {
        return indDupont;
    }

    public Double getInventarioProm() {
        return inventarioProm;
    }

    public Double getDeudaPromClientes() {
        return deudaPromClientes;
    }

    public Double getDiasParaCobrar() {
        return DiasParaCobrar;
    }

    public Double getVentasPendientesDCobro() {
        return ventasPendientesDCobro;
    }

    public Double getSumaVentas() {
        return sumaVentas;
    }

    public Double getSumaClientes() {
        return sumaClientes;
    }

    public Double getLiquidez() {
        return (efectivo - pasivo);
    }
     
     
}
