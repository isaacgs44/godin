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
 * @author lenovo
 */
public class Venta {
    private Query query;    
    private List<Producto> listaV;
    private String idCl;
    private Double neto;
    private Double costoVenta;
    private String vendedor; 
    private String id;    
    private String idLista;
    private String descripcion;
    private String nombreCl;
    private Double utilidad;
    private String fecha;
    private List<Venta> ventas;

    public void setId(String id) {
        this.id = id;
    }
    
    public Double getTotal(){
        return this.neto;
    }
    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public String getIdLista() {
        return idLista;
    }

    public void setIdLista(String idLista) {
        this.idLista = idLista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreCl() {
        return nombreCl;
    }

    public void setNombreCl(String nombreCl) {
        this.nombreCl = nombreCl;
    }

    public Double getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(Double utilidad) {
        this.utilidad = utilidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getIdCl() {
        return idCl;
    }

    public void setIdCl(String idCl) {
        this.idCl = idCl;
    }    
    
    public String getVendedor() {
        return vendedor;
    }
    
    public List<Producto> getListaV() {
        return listaV;
    }

    public void setListaV(List<Producto> listaV) {
        this.listaV = listaV;
    }

    public String getId() {
        return id;
    }
    
    public Double getNeto() {
        this.neto = 0.0;
        for(Producto p: listaV){
            neto += p.getCantidad() * p.getPrecioV();
        }
        return neto;
    }
    
    public void setNeto(Double neto) {
        this.neto = neto;
    }
    
    public Double getCostVen(){
        return this.costoVenta;
    }
    
    public Double getCostoVenta() {
        this.costoVenta = 0.0;
        for(Producto p: listaV){
            costoVenta += p.getCantidad() * p.getPrecioC();
        }
        return costoVenta;
    }

    public void setCostoVenta(Double costoVenta) {
        this.costoVenta = costoVenta;
    }
    
    public Venta(){
        listaV = new ArrayList <>();
        ventas = new ArrayList <>();
        this.id = obtenerId();
    }
    
    public Venta(String idComp){
        listaV = new ArrayList <>();
        ventas = new ArrayList <>();
        this.id = idComp;
    }
    
   /* 
    public List <Venta> obtenerVentas(){
        query = new Query();
    }
    */
    public Boolean registrarVenta(){
        query = new Query();
        Boolean bandera = false;
        Utilidades uts = new Utilidades();
        String valores = "(id_comp,idCl_comp,neto_comp,costoV_comp,vend_comp, dia, mes, anio)"
        + " VALUES ('" + this.id + "','" + this.idCl + "','" + this.neto.toString() + "','" + this.costoVenta.toString() + "','" + this.vendedor + "','" + uts.obtDia().toString() + "','" + uts.obtMes().toString() + "','" + uts.obtAnio().toString() + "')";    
        if(query.insertar("compra",valores)){
            bandera = true;    
            registrarLista();
            actualizaVentasProds();
        }else{
            System.out.println("fallo el registro");
            bandera = false;
        }
        query.Desconectar();
        return bandera;           
    }
    
    private void actualizaVentasProds(){
        Query query1 = new Query();
        Almacen a = new Almacen();
        for (Producto p : listaV){
            Integer num = a.obtenerNumVentas(p) + p.getCantidad();
            String consulta = "UPDATE producto SET ventas_prod=" + num.toString() + " WHERE codB_prod='" + p.getCodigoBarras() + "';"; 
            if(query1.ejecuta(consulta)){
                System.out.println("ventas actualizadas");
            }
            else{
                System.out.println("ERROR AL ACTUALIZAR VENTAS DE PRODUCTOS");
            }
        }
        query1.Desconectar();
    }
    
    private void registrarLista(){
        Query query1;
        query1 = new Query();
        
        for(Producto p : listaV){
            String valores = "(id_list,idComp_list,codB_list,cant_list,precioV_list,precioC_list)"
            + " VALUES ('" + obtenerIdLista() + "','" + this.id + "','" + p.getCodigoBarras() + "','" + p.getCantidad().toString() + "','" + p.getPrecioV().toString() + "','" + p.getPrecioC().toString() + "')";    
            
            if(!query1.insertar("listacompra",valores)){                                         
                System.out.println("fallo el registro");              
            }else{
                Secretaria mary = new Secretaria();
               
               if(p.getIdPedido() == 9999){
                   if(p.getCantidad()>1){
                       mary.registrarOperacion("CO000-001", "AC002-001" ,(p.getPrecioC()*p.getCantidad()), "salida " + p.getCantidad() + ": " + p.getNombre()); //costo de venta CO000-001 - costo de venta, AC002 - almacen
                   }else{
                       mary.registrarOperacion("CO000-001", "AC002-001" ,p.getPrecioC(), "salida: " + p.getNombre()); //costo de venta CO000-001 - costo de venta, AC002 - almacen
                   }
                   
               }else{
                    Almacen a = new Almacen();
                    a.buscarPorCodigoB(p.getCodigoBarras());
                    Producto prod = a.getProductos().get(0);
                    mary.registrarOperacion("CO000-001", prod.buscaCtaProv(prod) ,prod.getPrecioC(), "salida: " + p.getNombre()); //costo de venta CO000-001 - costo de venta, AC002 - almacen
                }
                System.out.println("correcto");
            }                    
        }
        query1.Desconectar();
    }    
    
    public void agregarAlCarrito(String id){
        Almacen a = new Almacen();
        Producto p = a.buscarPorSku(id);
        Boolean bandera = false;
        for(Producto p1 : listaV){
            if(p.getSku().equals(p1.getSku())){
               p1.setCantidad(p1.getCantidad()+1);
               bandera = true;
               break;
            }
        }
        if(!bandera){             
          p.setCantidad(1);
          listaV.add(p);
        }         
    }
    
    public void agregarAlCarrito(String id, Double precio){
        Almacen a = new Almacen();
        Producto p = a.buscarPorSku(id);
        Boolean bandera = false;
        for(Producto p1 : listaV){
            if(p.getSku().equals(p1.getSku())){
               p1.setCantidad(p1.getCantidad()+1);
               bandera = true;
               break;
            }
        }
        if(!bandera){             
          p.setCantidad(1);
          p.setPrecioV(precio);
          listaV.add(p);
        }         
    }
    
    public void quitarDlCarrito(String id){    
        Producto e = null;
        Boolean bandera = false;
        for(Producto p : listaV){
            if(p.getSku().equals(id)){
                e = p;
                if(p.getCantidad()>1){
                    p.setCantidad(p.getCantidad()-1);                      
                    bandera = false;
                    break;
                }else{
                   bandera=true;
                }
            }
        }
        
        if(bandera){
             listaV.remove(e);
        }
    }
    
    public TableModel creaModTblList(Integer filas){
        TablaVenta modelo1 = new TablaVenta(filas);  //numero de filas del ResultSet como parametro del constructor
        int i=0;
        for (Producto p1 : listaV) {
              modelo1.setValueAt(p1.getId(), i, 0);
              modelo1.setValueAt(p1.getNombre(), i, 1);
              modelo1.setValueAt(p1.getMarca(),i,2);
              modelo1.setValueAt(p1.getTipo(),i,3);
              modelo1.setValueAt(p1.getCantidad().toString(),i,4);
              modelo1.setValueAt(p1.getPrecioV().toString(),i,5);
              Double neto = p1.getPrecioV() * p1.getCantidad();
              modelo1.setValueAt(neto.toString(),i,6);
              modelo1.setValueAt(p1.getIdPedido().toString(),i,7);
              i++;
        }       
        return modelo1;
    }
   
    private String obtenerId(){
        query = new Query();
        String idNuevo=null;
        String id = "";
        ResultSet res;
        if(query.seleccion("id_comp", "compra")){
            res = query.getRes();
            try {
                while (res.next()){
                    id=res.getString("id_comp");
                }
                Integer numero = Integer.parseInt(id.substring(0,4));
                numero++;
                System.out.println("num: " + numero);
                idNuevo = obtenerNuevoNumero(numero) + obtenerFecha(); 
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }                                
        }else{
             idNuevo= "0001" + obtenerFecha();
        }
        query.Desconectar();
        return idNuevo;
    }
    
    private String obtenerIdLista(){
        query = new Query();
        String idNuevo=null;
        String id = "";
        ResultSet res;
        if(query.seleccion("id_list", "listacompra")){
            res = query.getRes();
            try {
                while (res.next()){
                    id=res.getString("id_list");
                }
                Integer numero = Integer.parseInt(id.substring(0,4));
                numero++;
               
                idNuevo = obtenerNuevoNumero(numero) + this.idCl.substring(0,4); 
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }                                
        }else{
             idNuevo= "0001" + this.idCl.substring(0,4);
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
        System.out.println(diaS + mesS + anio.toString().substring(2,4));
        return diaS + mesS + anio.toString().substring(2,4);
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
    
    public List <Venta> obtenerVentas(){
        query = new Query();
        Boolean bandera;
        ventas.clear();
        try {
            ResultSet res;
            String cons = "SELECT compra.id_comp, cliente.nombre_cl, compra.neto_comp, compra.costoV_comp, compra.vend_comp, "
                + "producto.nombre_prod, producto.marca_prod, listacompra.cant_list, listacompra.precioV_list, listacompra.precioC_list "
                + "FROM compra JOIN listacompra on compra.id_comp = listacompra.idComp_list JOIN producto on producto.codB_prod = listacompra.codB_list "
                + "JOIN cliente on compra.idCl_comp = cliente.id_cl ORDER BY compra.id_comp ASC;";
            if(query.consulta(cons)){
                res = query.getRes();
                    while(res.next()){
                        bandera = true;
                        String descripcion="";
                        Venta v1 = new Venta(res.getString("id_comp"));                       
                        if(ventas != null){
                        for (Venta v : ventas){
                            if(v1.id.equals(v.getId())){
                                descripcion += v.getDescripcion();
                                descripcion += "<br> | " + res.getString("nombre_prod");
                                descripcion += " by " + res.getString("marca_prod");
                                descripcion += " - " + res.getString("cant_list");
                                descripcion += ",  pV: " + res.getString("precioV_list");
                                descripcion += " - pc: " + res.getString("precioC_list") + " |";         
                                v.setDescripcion(descripcion);
                                bandera = false;
                            }
                        }
                        }
                        if(bandera){
                            descripcion += "<html> | " + res.getString("nombre_prod");
                            descripcion += " by " + res.getString("marca_prod");
                            descripcion += " - " + res.getString("cant_list");
                            descripcion += ",  pV: " + res.getString("precioV_list");
                            descripcion += " - pc: " + res.getString("precioC_list") + " |"; 
                            
                            v1.setDescripcion(descripcion);
                            v1.setFecha(obtenerFechaVenta(v1.getId()));
                            v1.setNombreCl(res.getString("nombre_cl"));                        
                            v1.setVendedor(res.getString("vend_comp"));
                            v1.setNeto(Double.parseDouble(res.getString("neto_comp")));
                            Double uti = Double.parseDouble(res.getString("neto_comp")) - Double.parseDouble(res.getString("costoV_comp"));
                            v1.setUtilidad(uti);                        
                            ventas.add(v1);
                        }
                    }
                }
            for(Venta v : ventas){
                v.setDescripcion(v.getDescripcion() + "<br> </html>");
            }
            query.Desconectar();
            return ventas;
            
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }                
            query.Desconectar();
            return null;
    }
    
    private String obtenerFechaVenta(String id){
        String fech = id.substring(4,6) + " - " + id.substring(6, 8) + " - " + id.substring(8, 10);
        return fech;
    }
    
    public TableModel creaModeloHventas(List <Venta> vents){
        TablaHistoVentas modelo1 = new TablaHistoVentas(vents.size());  //numero de filas del ResultSet como parametro del constructor
        int i=0;
        for (Venta ven : vents) {
              modelo1.setValueAt(ven.getId(), i, 0);
              modelo1.setValueAt(ven.getFecha(), i, 1);
              modelo1.setValueAt(ven.getNombreCl(),i,2);
              modelo1.setValueAt(ven.getDescripcion(),i,3);
              modelo1.setValueAt(ven.getVendedor(),i,4);
              modelo1.setValueAt(ven.getTotal().toString(),i,5);
              modelo1.setValueAt(ven.getUtilidad().toString(),i,6);              
              i++;
        }        
        return modelo1;        
    }

    public List<Venta> buscarVporCliente(String cliente,String orden) {
        query = new Query();
        Boolean bandera;
        ventas.clear();
        try {
            ResultSet res;
            String cons = "SELECT compra.id_comp, cliente.nombre_cl, cliente.referencia_cl, compra.neto_comp, compra.costoV_comp, compra.vend_comp, "
                + "producto.nombre_prod, producto.marca_prod, listacompra.cant_list, listacompra.precioV_list, listacompra.precioC_list "
                + "FROM compra JOIN listacompra on compra.id_comp = listacompra.idComp_list JOIN producto on producto.codB_prod = listacompra.codB_list "
                + "JOIN cliente on compra.idCl_comp = cliente.id_cl WHERE nombre_cl LIKE '%" + cliente + "%' OR referencia_cl LIKE '%" + cliente + "%'"
                    + "ORDER BY compra.id_comp " + orden + ";";
            if(query.consulta(cons)){
                res = query.getRes();
                    while(res.next()){
                        bandera = true;
                        String descripcion="";
                       Venta v1 = new Venta(res.getString("id_comp"));
                        if(ventas != null){
                        for (Venta v : ventas){                            
                            if(v1.id.equals(v.getId())){
                                descripcion += v.getDescripcion();
                                descripcion += "<br> | " + res.getString("nombre_prod");
                                descripcion += " by " + res.getString("marca_prod");
                                descripcion += " - " + res.getString("cant_list");
                                descripcion += ",  pV:" + res.getString("precioV_list");
                                descripcion += " - pC:" + res.getString("precioC_list") + " |";         
                                v.setDescripcion(descripcion);
                                bandera = false;
                            }
                        }
                        }
                        if(bandera){
                            descripcion += "<html> | " + res.getString("nombre_prod");
                            descripcion += " " + res.getString("marca_prod");
                            descripcion += " - " + res.getString("cant_list");
                            descripcion += ",  pV: " + res.getString("precioV_list");
                            descripcion += " - pC:" + res.getString("precioC_list") + " |"; 
                            
                            v1.setDescripcion(descripcion);
                            v1.setFecha(obtenerFechaVenta(v1.getId()));
                            v1.setNombreCl(res.getString("nombre_cl"));                        
                            v1.setVendedor(res.getString("vend_comp"));
                            v1.setNeto(Double.parseDouble(res.getString("neto_comp")));
                            Double uti = Double.parseDouble(res.getString("neto_comp")) - Double.parseDouble(res.getString("costoV_comp"));
                            v1.setUtilidad(uti);                        
                            ventas.add(v1);
                        }
                    }
                }
            for(Venta v : ventas){
                v.setDescripcion(v.getDescripcion() + "<br> </html>");
            }
            query.Desconectar();
            return ventas;
            
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }         
            query.Desconectar();
            return null;       
    }

    public List<Venta> buscarVporCliente(String cliente, String vendedor,String orden) {
        query = new Query();
        Boolean bandera;
        ventas.clear();
        try {
            ResultSet res;
            String cons = "SELECT compra.id_comp, cliente.nombre_cl, cliente.referencia_cl, compra.neto_comp, compra.costoV_comp, compra.vend_comp, "
                + "producto.nombre_prod, producto.marca_prod, listacompra.cant_list, listacompra.precioV_list, listacompra.precioC_list "
                + "FROM compra JOIN listacompra on compra.id_comp = listacompra.idComp_list JOIN producto on producto.codB_prod = listacompra.codB_list "
                + "JOIN cliente on compra.idCl_comp = cliente.id_cl WHERE (nombre_cl LIKE '%" + cliente + "%' OR referencia_cl LIKE '%" + cliente + "%') "
                + "AND (vend_comp = '"+ vendedor + "') ORDER BY compra.id_comp " + orden + ";";
            if(query.consulta(cons)){
                res = query.getRes();
                    while(res.next()){
                        bandera = true;
                        String descripcion="";
                       Venta v1 = new Venta(res.getString("id_comp"));
                        if(ventas != null){
                        for (Venta v : ventas){                            
                            if(v1.id.equals(v.getId())){
                                descripcion += v.getDescripcion();
                                descripcion += "<br> | " + res.getString("nombre_prod");
                                descripcion += " by " + res.getString("marca_prod");
                                descripcion += " - " + res.getString("cant_list");
                                descripcion += ",  pV:" + res.getString("precioV_list");
                                descripcion += " - pC:" + res.getString("precioC_list") + " |";         
                                v.setDescripcion(descripcion);
                                bandera = false;
                            }
                        }
                        }
                        if(bandera){
                            descripcion += "<html> | " + res.getString("nombre_prod");
                            descripcion += " " + res.getString("marca_prod");
                            descripcion += " - " + res.getString("cant_list");
                            descripcion += ",  pV: " + res.getString("precioV_list");
                            descripcion += " - pC:" + res.getString("precioC_list") + " |"; 
                            
                            v1.setDescripcion(descripcion);
                            v1.setFecha(obtenerFechaVenta(v1.getId()));
                            v1.setNombreCl(res.getString("nombre_cl"));                        
                            v1.setVendedor(res.getString("vend_comp"));
                            v1.setNeto(Double.parseDouble(res.getString("neto_comp")));
                            Double uti = Double.parseDouble(res.getString("neto_comp")) - Double.parseDouble(res.getString("costoV_comp"));
                            v1.setUtilidad(uti);                        
                            ventas.add(v1);
                        }
                    }
                }
            for(Venta v : ventas){
                v.setDescripcion(v.getDescripcion() + "<br> </html>");
            }
            query.Desconectar();
            return ventas;
            
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }                
            query.Desconectar();
            return null;       
    }

    public List<Venta> buscarVporNomProd(String nombre,String orden) {
        query = new Query();
        Boolean bandera;
        ventas.clear();
        try {
            ResultSet res;
            String cons = "SELECT compra.id_comp, cliente.nombre_cl, cliente.referencia_cl, compra.neto_comp, compra.costoV_comp, compra.vend_comp, "
                + "producto.nombre_prod, producto.marca_prod, listacompra.cant_list, listacompra.precioV_list, listacompra.precioC_list "
                + "FROM compra JOIN listacompra on compra.id_comp = listacompra.idComp_list JOIN producto on producto.codB_prod = listacompra.codB_list "
                + "JOIN cliente on compra.idCl_comp = cliente.id_cl WHERE (nombre_prod LIKE '%" + nombre + "%' OR marca_prod LIKE '%" + nombre + "%') "
                + "ORDER BY compra.id_comp " + orden + ";";
            if(query.consulta(cons)){
                res = query.getRes();
                    while(res.next()){
                        bandera = true;
                        String descripcion="";
                       Venta v1 = new Venta(res.getString("id_comp"));
                        if(ventas != null){
                        for (Venta v : ventas){                            
                            if(v1.id.equals(v.getId())){
                                descripcion += v.getDescripcion();
                                descripcion += "<br> | " + res.getString("nombre_prod");
                                descripcion += " by " + res.getString("marca_prod");
                                descripcion += " - " + res.getString("cant_list");
                                descripcion += ",  pV:" + res.getString("precioV_list");
                                descripcion += " - pC:" + res.getString("precioC_list") + " |";         
                                v.setDescripcion(descripcion);
                                bandera = false;
                            }
                        }
                        }
                        if(bandera){
                            descripcion += "<html> | " + res.getString("nombre_prod");
                            descripcion += " " + res.getString("marca_prod");
                            descripcion += " - " + res.getString("cant_list");
                            descripcion += ",  pV: " + res.getString("precioV_list");
                            descripcion += " - pC:" + res.getString("precioC_list") + " |"; 
                            
                            v1.setDescripcion(descripcion);
                            v1.setFecha(obtenerFechaVenta(v1.getId()));
                            v1.setNombreCl(res.getString("nombre_cl"));                        
                            v1.setVendedor(res.getString("vend_comp"));
                            v1.setNeto(Double.parseDouble(res.getString("neto_comp")));
                            Double uti = Double.parseDouble(res.getString("neto_comp")) - Double.parseDouble(res.getString("costoV_comp"));
                            v1.setUtilidad(uti);                        
                            ventas.add(v1);
                        }
                    }
                }
            for(Venta v : ventas){
                v.setDescripcion(v.getDescripcion() + "<br> </html>");
            }
            query.Desconectar();
            return ventas;
            
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }          
            query.Desconectar();
            return null;       
    }

    public List<Venta> buscarVporNomProd(String nombre, String vendedor,String orden) {
        query = new Query();
        Boolean bandera;
        ventas.clear();
        try {
            ResultSet res;
            String cons = "SELECT compra.id_comp, cliente.nombre_cl, cliente.referencia_cl, compra.neto_comp, compra.costoV_comp, compra.vend_comp, "
                + "producto.nombre_prod, producto.marca_prod, listacompra.cant_list, listacompra.precioV_list, listacompra.precioC_list "
                + "FROM compra JOIN listacompra on compra.id_comp = listacompra.idComp_list JOIN producto on producto.codB_prod = listacompra.codB_list "
                + "JOIN cliente on compra.idCl_comp = cliente.id_cl WHERE (nombre_prod LIKE '%" + nombre + "%' OR marca_prod LIKE '%" + nombre + "%') "
                + "AND (vend_comp = '"+ vendedor + "') ORDER BY compra.id_comp " + orden + ";";
            if(query.consulta(cons)){
                res = query.getRes();
                    while(res.next()){
                        bandera = true;
                        String descripcion="";
                       Venta v1 = new Venta(res.getString("id_comp"));
                        if(ventas != null){
                        for (Venta v : ventas){                            
                            if(v1.id.equals(v.getId())){
                                descripcion += v.getDescripcion();
                                descripcion += "<br> | " + res.getString("nombre_prod");
                                descripcion += " by " + res.getString("marca_prod");
                                descripcion += " - " + res.getString("cant_list");
                                descripcion += ",  pV:" + res.getString("precioV_list");
                                descripcion += " - pC:" + res.getString("precioC_list") + " |";         
                                v.setDescripcion(descripcion);
                                bandera = false;
                            }
                        }
                        }
                        if(bandera){
                            descripcion += "<html> | " + res.getString("nombre_prod");
                            descripcion += " " + res.getString("marca_prod");
                            descripcion += " - " + res.getString("cant_list");
                            descripcion += ",  pV: " + res.getString("precioV_list");
                            descripcion += " - pC:" + res.getString("precioC_list") + " |"; 
                            
                            v1.setDescripcion(descripcion);
                            v1.setFecha(obtenerFechaVenta(v1.getId()));
                            v1.setNombreCl(res.getString("nombre_cl"));                        
                            v1.setVendedor(res.getString("vend_comp"));
                            v1.setNeto(Double.parseDouble(res.getString("neto_comp")));
                            Double uti = Double.parseDouble(res.getString("neto_comp")) - Double.parseDouble(res.getString("costoV_comp"));
                            v1.setUtilidad(uti);                        
                            ventas.add(v1);
                        }
                    }
                }
            for(Venta v : ventas){
                v.setDescripcion(v.getDescripcion() + "<br> </html>");
            }
            query.Desconectar();
            return ventas;
            
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }       
            query.Desconectar();
            return null;       
    }

    public List<Venta> buscarVporCodProd(String codigo,String orden) {
        query = new Query();
        Boolean bandera;
        ventas.clear();
        try {
            ResultSet res;
            String cons = "SELECT compra.id_comp, cliente.nombre_cl, cliente.referencia_cl, compra.neto_comp, compra.costoV_comp, compra.vend_comp, "
                + "producto.nombre_prod, producto.marca_prod, listacompra.cant_list, listacompra.precioV_list, listacompra.precioC_list "
                + "FROM compra JOIN listacompra on compra.id_comp = listacompra.idComp_list JOIN producto on producto.codB_prod = listacompra.codB_list "
                + "JOIN cliente on compra.idCl_comp = cliente.id_cl WHERE codB_prod = '" + codigo + "' "
                + "ORDER BY compra.id_comp " + orden + ";";
            if(query.consulta(cons)){
                res = query.getRes();
                    while(res.next()){
                        bandera = true;
                        String descripcion="";
                       Venta v1 = new Venta(res.getString("id_comp"));
                        if(ventas != null){
                        for (Venta v : ventas){                            
                            if(v1.id.equals(v.getId())){
                                descripcion += v.getDescripcion();
                                descripcion += "<br> | " + res.getString("nombre_prod");
                                descripcion += " by " + res.getString("marca_prod");
                                descripcion += " - " + res.getString("cant_list");
                                descripcion += ",  pV:" + res.getString("precioV_list");
                                descripcion += " - pC:" + res.getString("precioC_list") + " |";         
                                v.setDescripcion(descripcion);
                                bandera = false;
                            }
                        }
                        }
                        if(bandera){
                            descripcion += "<html> | " + res.getString("nombre_prod");
                            descripcion += " " + res.getString("marca_prod");
                            descripcion += " - " + res.getString("cant_list");
                            descripcion += ",  pV: " + res.getString("precioV_list");
                            descripcion += " - pC:" + res.getString("precioC_list") + " |"; 
                            
                            v1.setDescripcion(descripcion);
                            v1.setFecha(obtenerFechaVenta(v1.getId()));
                            v1.setNombreCl(res.getString("nombre_cl"));                        
                            v1.setVendedor(res.getString("vend_comp"));
                            v1.setNeto(Double.parseDouble(res.getString("neto_comp")));
                            Double uti = Double.parseDouble(res.getString("neto_comp")) - Double.parseDouble(res.getString("costoV_comp"));
                            v1.setUtilidad(uti);                        
                            ventas.add(v1);
                        }
                    }
                }
            for(Venta v : ventas){
                v.setDescripcion(v.getDescripcion() + "<br> </html>");
            }
            query.Desconectar();
            return ventas;
            
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }        
            query.Desconectar();
            return null;   
    }

    public List<Venta> buscarVporCodProd(String codigo, String toString,String orden) {
        query = new Query();
        Boolean bandera;
        ventas.clear();
        try {
            ResultSet res;
            String cons = "SELECT compra.id_comp, cliente.nombre_cl, cliente.referencia_cl, compra.neto_comp, compra.costoV_comp, compra.vend_comp, "
                + "producto.nombre_prod, producto.marca_prod, listacompra.cant_list, listacompra.precioV_list, listacompra.precioC_list "
                + "FROM compra JOIN listacompra on compra.id_comp = listacompra.idComp_list JOIN producto on producto.codB_prod = listacompra.codB_list "
                + "JOIN cliente on compra.idCl_comp = cliente.id_cl WHERE codB_prod = '" + codigo + "' "
                + "AND vend_comp = '"+ vendedor + "' ORDER BY compra.id_comp " + orden + ";";
            if(query.consulta(cons)){
                res = query.getRes();
                    while(res.next()){
                        bandera = true;
                        String descripcion="";
                       Venta v1 = new Venta(res.getString("id_comp"));
                        if(ventas != null){
                        for (Venta v : ventas){                            
                            if(v1.id.equals(v.getId())){
                                descripcion += v.getDescripcion();
                                descripcion += "<br> | " + res.getString("nombre_prod");
                                descripcion += " by " + res.getString("marca_prod");
                                descripcion += " - " + res.getString("cant_list");
                                descripcion += ",  pV:" + res.getString("precioV_list");
                                descripcion += " - pC:" + res.getString("precioC_list") + " |";         
                                v.setDescripcion(descripcion);
                                bandera = false;
                            }
                        }
                        }
                        if(bandera){
                            descripcion += "<html> | " + res.getString("nombre_prod");
                            descripcion += " " + res.getString("marca_prod");
                            descripcion += " - " + res.getString("cant_list");
                            descripcion += ",  pV: " + res.getString("precioV_list");
                            descripcion += " - pC:" + res.getString("precioC_list") + " |"; 
                            
                            v1.setDescripcion(descripcion);
                            v1.setFecha(obtenerFechaVenta(v1.getId()));
                            v1.setNombreCl(res.getString("nombre_cl"));                        
                            v1.setVendedor(res.getString("vend_comp"));
                            v1.setNeto(Double.parseDouble(res.getString("neto_comp")));
                            Double uti = Double.parseDouble(res.getString("neto_comp")) - Double.parseDouble(res.getString("costoV_comp"));
                            v1.setUtilidad(uti);                        
                            ventas.add(v1);
                        }
                    }
                }
            for(Venta v : ventas){
                v.setDescripcion(v.getDescripcion() + "<br> </html>");
            }
            query.Desconectar();
            return ventas;
            
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }       
            query.Desconectar();
            return null;   
    }

    public TableModel creaModeloCompras(List<Venta> compras) {
        TablaDevoluciones modelo1 = new TablaDevoluciones(compras.size(), "compras");  //numero de filas del ResultSet como parametro del constructor
        int i=0;
        for (Venta ven : compras) {
              modelo1.setValueAt(ven.getId(), i, 0);
              modelo1.setValueAt(ven.getFecha(), i, 1);
              modelo1.setValueAt(ven.getVendedor(),i,2);
              modelo1.setValueAt(ven.getDescripcion(),i,3);            
              modelo1.setValueAt(ven.getTotal().toString(),i,4);         
              i++;
        }        
        return modelo1;    
    }
    
    public List<Producto> obtenerProd(String codigoCmp) {
        query = new Query(); 
        this.listaV.clear();
        
        try {
            ResultSet res;
            String cons = "SELECT listacompra.id_list, listacompra.codB_list, producto.nombre_prod, producto.marca_prod, producto.tipo_prod, listacompra.cant_list, "
                    + "listacompra.precioV_list, listacompra.precioC_list FROM listacompra JOIN producto on listacompra.codB_list = producto.codB_prod "
                    + "WHERE listacompra.idComp_list = '" + codigoCmp + "';";
            if(query.consulta(cons)){
                res = query.getRes();
                    while(res.next()){
                        Producto p = new Producto();
                        p.setId(res.getString("id_list")); 
                        p.setCodigoBarras(res.getString("codB_list"));
                        p.setNombre(res.getString("nombre_prod"));
                        p.setMarca(res.getString("marca_prod"));
                        p.setTipo(res.getString("tipo_prod"));
                        p.setCantidad(Integer.parseInt(res.getString("cant_list")));
                        p.setPrecioV(Double.parseDouble(res.getString("precioV_list")));
                        p.setPrecioC(Double.parseDouble(res.getString("precioC_list")));
                        listaV.add(p);
                    }
                    query.Desconectar();
                    return listaV;
                }
            
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }   
            query.Desconectar();
            return null;   
    }

    public TableModel creaModProds(List<Producto> prods) {
        TablaDevoluciones modelo1 = new TablaDevoluciones(prods.size(), "productos");  //numero de filas del ResultSet como parametro del constructor
        int i=0;
        for (Producto p : prods) {
              modelo1.setValueAt(p.getId(), i, 0);
              modelo1.setValueAt(p.getCodigoBarras(), i, 1);
              String desc = p.getNombre() + " by " + p.getMarca() + " - " + p.getTipo();
              modelo1.setValueAt(desc, i, 2);
              modelo1.setValueAt(p.getCantidad(),i,3);
              modelo1.setValueAt(p.getPrecioV().toString(),i,4);
              modelo1.setValueAt(p.getPrecioC().toString(), i, 5);
              i++;
        }        
        return modelo1;    
    }    
    
    public Venta buscarVenta(String id) {
        query = new Query();
        try {
            ResultSet res;
            String cons = "SELECT neto_comp, costoV_comp FROM compra WHERE id_comp = '" + id + "';";
            if(query.consulta(cons)){
                res = query.getRes();
                    while(res.next()){                        
                       Venta v1 = new Venta(id);
                       v1.setCostoVenta(Double.parseDouble(res.getString("costoV_comp")));
                       v1.setNeto(Double.parseDouble(res.getString("neto_comp")));
                       query.Desconectar();
                       return v1;
                    }
                }                                  
            } catch (SQLException ex) {
                Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
            }               
            query.Desconectar();
            return null;   
    }
    
    public Boolean regDevolucion(Cliente cl, String idList, String codBarras, String precioV, String precioC, String idComp) {
        Query query1;
        query1 = new Query();
        Venta v = buscarVenta(idComp);
        Double neto = v.getTotal() - Double.parseDouble(precioV);
        Double cV = v.getCostVen() - Double.parseDouble(precioC);
        Boolean valor;
        String valores = "(idComp_dev,codB_dev) VALUES " + "('" + this.id + "','"+codBarras+"')";
        String actComp = "UPDATE compra SET neto_comp = '" + neto.toString() + "', costoV_comp = '" + cV.toString() + "' WHERE id_comp = '" + idComp + "';";
        String valores2 = "(codProd_alm,idPed_alm,cant_alm,precioC_alm)"
                   + " VALUES ('" + codBarras + "','" + "9999" + "','" + "1" + "','" + precioC + "')";
        String borraList = "DELETE FROM listacompra WHERE id_list = '" + idList + "';";
        if(query1.insertar("devolucion", valores)){
            if(query1.ejecuta(actComp)){
                if(query1.ejecuta(borraList)){
                    cl.actualizarSaldo(Double.parseDouble(precioV), cl, 'd');
                    Secretaria mary = new Secretaria();
                    mary.registrarOperacion("AC002-005","CO000-001",Double.parseDouble(precioC), "devolucion cliente: " + cl.getNombre() + " Venta: " + v.getId()); //costo de venta CO000-001 - costo de venta, AC002-005 - almacen "devoluciones"
                    valor = query1.insertar("almacen", valores2);
                    query1.Desconectar();
                    return valor;
                }
            }
        }else{
            query1.Desconectar();
            return false;
        }
        return false;
    }

    public Boolean marcaRegHistorico() {
        query = new Query();
        Boolean valor;
        String sentencia = "UPDATE compra SET registro_historico = 1 WHERE registro_historico = 0;";
        valor = query.ejecuta(sentencia);
        query.Desconectar();
        return valor;
    }
}