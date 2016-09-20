/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author thinkpad
 */
public class Cliente {
    private String id; 
    private String nombre;
    private String referencia;
    private String Cuenta;
    private Integer numCompras;
    private List <Cliente> clientes;
    private String direccion;
    private Query query;
    private Double saldo;
    
    public Cliente(String nombre,String referencia, String direccion, Double saldo){
        this.numCompras = 0;
        this.nombre = nombre;
        this.referencia = referencia;      
        this.direccion = direccion;
        this.Cuenta = null;
        this.saldo = saldo;
    }

    public Cliente() {
        this.numCompras = null;
        this.nombre = null;
        this.id = null;
        this.referencia=null;
        clientes = new ArrayList <>();
        this.direccion = null;
        this.Cuenta = null;
    }
    
    public Cliente(String id){
        this.query = new Query();
        ResultSet res; 
        String condicion = "WHERE id_cl = " + id ;
        if(!query.seleccion("*","cliente",condicion)){
            System.out.println("No hay clientes");
        }else{
            res=query.getRes();
            try {
                while (res.next()){
                    this.id = id;
                    this.nombre = res.getString(2);
                    this.referencia =  res.getNString(3);
                    this.direccion = res.getString(4);
                    this.numCompras = Integer.parseInt(res.getString(5));      
                    this.Cuenta = res.getString(6);
                }                      
            } catch (SQLException ex) {
                Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.Desconectar();
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getNumCompras() {
        return numCompras;
    }

    public void setNumCompras(Integer numCompras) {
        this.numCompras = numCompras;
    }        

    public String getCuenta() {
        return Cuenta;
    }

    public void setCuenta(String Cuenta) {
        this.Cuenta = Cuenta;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    private void modificarCliente(Cliente cliente, String cadena){
    
    }
    
    private void eliminarCliente(Cliente cliente){
    
    }
    
    public boolean registrarCliente(Cliente c){
       Query query1;
       query1 = new Query();
       Boolean bandera = false;
       
       String valores = "(id_cl,nombre_cl,referencia_cl,dir_cl,compras_cl, ctaClientes_cl)"
       + " VALUES ('" + obtenerId() + "','" + c.getNombre() + "','" + c.getReferencia() + "','" + c.getDireccion() + "','" +  c.getNumCompras().toString() + "','" + obtenerCtaCl(c) + "')";    
                
      if(query1.insertar("cliente",valores)){
        bandera = true;    
      }else{
        System.out.println("fallo el registro");
        bandera = false;
      }
      query1.Desconectar();
      return bandera;
   }
    
    private String obtenerId(){
        query = new Query();
        String idNuevo=null;
        String id = "";
        ResultSet res;
        if(query.seleccion("id_cl", "cliente")){
            res = query.getRes();
            try {
                while (res.next()){
                    id=res.getString("id_cl");
                }
                Integer numero = Integer.parseInt(id.substring(0,4));
                numero++;
                System.out.println("num: " + numero);
                idNuevo = obtenerNuevoNumero(numero) + obtenerFecha(); 
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }                                
        }else{
             idNuevo= "0001" + obtenerFecha();
        }
        query.Desconectar();
        return idNuevo;
    }
    
    private String obtenerFecha(){
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
        return diaS + mesS + anio.toString().substring(2,4);
    }
    
    private String obtenerFechaDate(){
        Calendar c1 = new GregorianCalendar();
        Integer dia = c1.get(Calendar.DATE);     
        Integer mes =  c1.get(Calendar.MONTH)+1; //se suma 1 por que el 0 es enero
        Integer anio = c1.get(Calendar.YEAR);
        return anio.toString() + "-" + mes.toString() + "-" + dia.toString();
    }
    
    private String obtenerNuevoNumero(Integer num){
        if(num<10){
            return "000"+num.toString();
        }else if(num<100){
            return "00"+num.toString();
        }else if(num<1000){
            return "0"+num.toString();
        }
        return null;
    }
    
    
    public List <Cliente> obtenerClientes(){
        query = new Query();
        this.clientes.clear();
        ResultSet res; 
        if(!query.seleccion("*","cliente")){
            System.out.println("no hay clientes");
        }else{
            try {
                res=query.getRes();
           
                while (res.next()){
                    Cliente c = new Cliente();
                    c.setId(res.getString(1));
                    c.setNombre(res.getString(2));
                    c.setReferencia(res.getNString(3));
                    c.setDireccion(res.getString(4));
                    c.setNumCompras(Integer.parseInt(res.getString(5)));
                    c.setCuenta(res.getString(6));
                    this.clientes.add(c);
                }    
            } catch (SQLException ex) {
                Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.Desconectar();
        return this.clientes;
    }
    
     public TableModel creaModeloClientes() {
        TablaClientes modelo1 = new TablaClientes(this.clientes.size());  //numero de filas del ResultSet como parametro del constructor
        int i=0;
        Utilidades uts = new Utilidades();
        for (Cliente c : clientes) {
              modelo1.setValueAt(c.getId(), i, 0);
              modelo1.setValueAt(c.getNombre(), i, 1);
              modelo1.setValueAt(c.getReferencia(),i,2);
              modelo1.setValueAt(c.getDireccion(),i,3);
              modelo1.setValueAt(uts.formatDinero(c.getSaldo(c)),i,4);
              modelo1.setValueAt(c.getNumCompras(),i,5);
              i++;
        }
        return modelo1;
    }
    
    public List<Cliente> buscarCliente(String nombre){
        this.query = new Query();
        this.clientes.clear();
        ResultSet res; 
        String condicion = "WHERE nombre_cl LIKE '%" + nombre + "%' OR referencia_cl LIKE '%" + nombre + "%'";
        if(!query.seleccion("*","cliente",condicion)){
            System.out.println("No hay clientes");
            System.out.println(this.clientes.size());
        }else{
            res=query.getRes();
            try {
                while (res.next()){
                    Cliente c = new Cliente();
                    c.setId(res.getString(1));
                    c.setNombre(res.getString(2));
                    c.setReferencia(res.getNString(3));
                    c.setDireccion(res.getString(4));
                    c.setNumCompras(Integer.parseInt(res.getString(5)));
                    c.setCuenta(res.getString(6));
                    this.clientes.add(c);
                }                      
            } catch (SQLException ex) {
                Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.Desconectar();
        return this.clientes;
    }

    public Boolean actualizarSaldo(Double monto,Cliente c, char opc) {
        query =new Query();
        String consulta;
        Utilidades uts = new Utilidades();
        switch(opc){
            case 'c': //Compra
                Secretaria mary = new Secretaria();
                mary.registrarOperacion(c.getCuenta(), "IN000", monto, "compra"); //IN000 - cuent Ventas
                Integer comp = c.getNumCompras()+1;
                consulta = "UPDATE cliente SET compras_cl=" + comp + " WHERE id_cl=" + c.getId() + ";";
                query.ejecuta(consulta);
                
            break;
            
            case 'd': //devolucion
                Secretaria mary1 = new Secretaria();
                mary1.registrarOperacion("CO001", c.getCuenta(), monto, "compra"); //IN000 - cuent Ventas
                Integer comp1 = c.getNumCompras()-1;
                consulta = "UPDATE cliente SET compras_cl=" + comp1 + " WHERE id_cl=" + c.getId() + ";";
                query.ejecuta(consulta);
                
            break;
             case 'p': //Pago
                Double nuevoS = c.getSaldo(c) - monto;
                if(nuevoS >= 0 ){         
                    Secretaria mary2 = new Secretaria();
                    if(mary2.registrarOperacion("AC000-000", c.getCuenta(), monto, "pago")){
                        String valores = "(idCl_pago,monto_pago,dia_pago,mes_pago,anio_pago)"
                        + " VALUES ('" + c.getId() + "','" + monto.toString() + "','" + uts.obtDia() + "','" + uts.obtMes() + "','" + uts.obtAnio() +"')";    
                        if(query.insertar("pago", valores)){
                            query.Desconectar();
                           return true;
                        }
                    }
                }else{
                    query.Desconectar();
                    return false;
                }
             break;
                 
            
        }
        query.Desconectar();
        return false;
    }

    private String obtenerCtaCl(Cliente c) {
        Cuenta cta = new Cuenta();
        cta.agregarSubCta("AC003", c.getNombre(),c.get2Saldo(c)); // AC003 - Clientes
        if(c.get2Saldo(c)>0){
            Cuenta prinC;
            LibroMayor m1 = new LibroMayor();
            prinC = m1.obtenerCuenta("AC003", "c");
            prinC.setDeber(prinC.getDeber() + c.get2Saldo(c));
            
            if(prinC.getDeber() > prinC.getHaber()){ //saldo deudor  
                prinC.setSaldo(prinC.getDeber() - prinC.getHaber());
                switch(prinC.getId().substring(0,2)){
                    case "PC": case "PD": case "PF": case "CC": 
                        prinC.setSaldo(prinC.getSaldo() * -1);
                    break;
                }
            }else{ //saldo acredor
                prinC.setSaldo(prinC.getHaber() - prinC.getDeber());
                 switch(prinC.getId().substring(0,2)){
                    case "AF": case "AC": case "AD":
                        prinC.setSaldo(prinC.getSaldo() * -1);
                    break;
                }
            }
            m1.actualizarCta(prinC);
            
        }
        return cta.getId();
    }

    private Double getSaldo(Cliente c) {
        LibroMayor m = new LibroMayor();
        Cuenta cta;
        cta = m.obtenerCuenta(c.getCuenta(), "s");
        return cta.getSaldo();
    }
    
    private Double get2Saldo(Cliente c){
        return this.saldo;
    }
}
