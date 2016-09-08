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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author lenovo
 */
public class Productos {
    private List<Producto> productos;
    private Query query;
    private TableModel modelo;

    public Productos() {
        this.productos = new ArrayList <>();
        
    }
    
    
    
    public List<Producto> obtenerProductos(){
       this.query = new Query();
       this.productos.clear();
       ResultSet res; 
       if(!query.seleccion("*", "producto","ORDER BY ventas_prod DESC")){
           System.out.println("no hay producto");
       }else{
            try {
                res=query.getRes();
                while (res.next()){
                    Producto prod = new Producto();
                    prod.setCodigoBarras(res.getString("codB_prod"));
                    prod.setNombre(res.getNString("nombre_prod"));
                    prod.setMarca(res.getNString("marca_prod"));
                    prod.setTipo(res.getString("tipo_prod"));
                    prod.setPrecioC(Double.parseDouble(res.getString("ultPrecioC_prod")));
                    prod.setPrecioV(Double.parseDouble(res.getString("precioV_prod")));
                    prod.setNumVentas(Integer.parseInt(res.getString("ventas_prod")));
                    this.productos.add(prod);
                }
                query.Desconectar();
                return this.productos;
            
            } catch (SQLException ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
                query.Desconectar();
                
            }
        }
        return null;
   }
   
   public TableModel obtenerTablaProductos() {
       obtenerProductos();   
       return creaModelo(productos.size());
    }
   
   
      public TableModel creaModelo(Integer filas){
        TablaProductos modelo1 = new TablaProductos(filas);  //numero de filas del ResultSet como parametro del constructor
        int i=0;
        Utilidades uts = new Utilidades();
        for (Producto p1 : productos) {
              modelo1.setValueAt(p1.getCodigoBarras(), i, 0);
              modelo1.setValueAt(p1.getNombre(), i, 1);
              modelo1.setValueAt(p1.getMarca(),i,2);
              modelo1.setValueAt(p1.getTipo(),i,3);
              modelo1.setValueAt(uts.formatDinero(p1.getPrecioV()),i,4);
              modelo1.setValueAt(uts.formatDinero(p1.getPrecioC()),i,5);
              modelo1.setValueAt(p1.getNumVentas().toString(),i,6);
              i++;
        }
        this.modelo = modelo1;
        return this.modelo;
    }
   
      public void buscarPorNombre(String nombre){
       query = new Query();
       ResultSet res;
       this.productos.clear();
       String cond = "WHERE nombre_prod LIKE '%" + nombre + "%' OR marca_prod LIKE '%" + nombre + "%'";
       
       if(query.seleccion("*", "producto", cond)){     
          try {
            res=query.getRes();
            while (res.next()){
                  Producto prod = new Producto();
                    prod.setCodigoBarras(res.getString("codB_prod"));
                    prod.setNombre(res.getNString("nombre_prod"));
                    prod.setMarca(res.getNString("marca_prod"));
                    prod.setTipo(res.getString("tipo_prod"));
                    prod.setPrecioC(Double.parseDouble(res.getString("ultPrecioC_prod")));
                    prod.setPrecioV(Double.parseDouble(res.getString("precioV_prod")));
                    prod.setNumVentas(Integer.parseInt(res.getString("ventas_prod")));
                    this.productos.add(prod);
            }          
          } catch (SQLException ex) {
              Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
          }
        }         
   }
   
   //busca en los productos del almacen
   public void buscarPorCodigoB(String cod){
       query = new Query();
       ResultSet res;
       this.productos.clear();
       String cond = "WHERE codB_prod = '" + cod + "'";
       
       if(query.seleccion("*", "producto", cond)){     
          try {
            res=query.getRes();
            while (res.next()){
                 Producto prod = new Producto();
                    prod.setCodigoBarras(res.getString("codB_prod"));
                    prod.setNombre(res.getNString("nombre_prod"));
                    prod.setMarca(res.getNString("marca_prod"));
                    prod.setTipo(res.getString("tipo_prod"));
                    prod.setPrecioC(Double.parseDouble(res.getString("ultPrecioC_prod")));
                    prod.setPrecioV(Double.parseDouble(res.getString("precioV_prod")));
                    prod.setNumVentas(Integer.parseInt(res.getString("ventas_prod")));
                    this.productos.add(prod);
            }          
          } catch (SQLException ex) {
              Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
       query.Desconectar();
   }

    public List<Producto> getProductos() {
        return productos;
    }

    public Boolean actualizarProducto(String valores, String codBar) {
        query = new Query();
        Boolean bandera;
        String cond = "WHERE codB_prod = '" + codBar +"'";
        bandera = query.update("producto",valores,cond);
        query.Desconectar();
        return bandera;
    }
   
    
    
}
