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
 * @author thinkpad
 */
public class Almacen {

    private List<Producto> productos; 
    private Query query;
    private TableModel modelo;

    // constructor - debe tener todo cargado
     public Almacen() {
        this.productos = new ArrayList<>();
        this.obtenerProductos(); // obtengo producto en inventario AQUI ME QUEDE
    }
    
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

   

    public boolean agregarProd(Producto p) {
        query = new Query();
        Boolean bandera = false;

        if (p.getNuevo()) { // es nuevo
            String valores = "(codB_prod,nombre_prod,marca_prod,tipo_prod,precioV_prod,ultPrecioC_prod,ventas_prod,stockMin_prod)"
                    + " VALUES ('" + p.getCodigoBarras() + "','" + p.getNombre() + "','" + p.getMarca() + "','" + p.getTipo() + "','" + p.getPrecioV().toString() + "','" + p.getPrecioC().toString() + "','" + p.getNumVentas().toString() + "','" + p.getStockMin().toString() + "')";

            if (query.insertar("producto", valores)) {

                bandera = true;

            } else {
                System.out.println("No se inserto el producto");
                bandera = false;
            }
        }
        return bandera;
    }

    public boolean agregarProd(List<Producto> prod) {
        query = new Query();
        Boolean bandera = false;

        for (Producto p : prod) {
            if (p.getNuevo()) { // es nuevo
                String valores = "(codB_prod,nombre_prod,marca_prod,tipo_prod,precioV_prod,ultPrecioC_prod,ventas_prod,stockMin_prod)"
                        + " VALUES ('" + p.getCodigoBarras() + "','" + p.getNombre() + "','" + p.getMarca() + "','" + p.getTipo() + "','" + p.getPrecioV().toString() + "','" + p.getPrecioC().toString() + "','" + p.getNumVentas().toString() + "','" + p.getStockMin().toString() + "')";

                String valores2 = "(codProd_alm,idPed_alm,cant_alm,precioC_alm)"
                        + " VALUES ('" + p.getCodigoBarras() + "','" + p.getIdPedido().toString() + "','" + p.getCantidad().toString() + "','" + p.getPrecioC().toString() + "')";

                if (query.insertar("producto", valores)) {
                    if (query.insertar("almacen", valores2)) {
                        System.out.println("producto insertado");
                        bandera = true;
                    }
                } else {
                    System.out.println("fallo un producto");
                    bandera = false;
                }
            } else {
                String valores2 = "(codProd_alm,idPed_alm,cant_alm,precioC_alm)"
                        + " VALUES ('" + p.getCodigoBarras() + "','" + p.getIdPedido().toString() + "','" + p.getCantidad().toString() + "','" + p.getPrecioC().toString() + "')";

                if (query.insertar("almacen", valores2)) {
                    System.out.println("producto insertado");
                    bandera = true;
                } else {
                    System.out.println("fallo un producto");
                    bandera = false;
                }
            }
        }
        return bandera;
    }

    public void retirarProd(Producto prod) {

    }

    public void modificarProd(Producto prod) {

    }

    public void buscarPorNombre(String nombre) {
        query = new Query();
        ResultSet res;
        this.productos.clear();
        String consulta = "SELECT producto.codB_prod, producto.nombre_prod, producto.marca_prod, producto.tipo_prod, "
                + "producto.precioV_prod, producto.ventas_prod, producto.stockMin_prod, almacen.idPed_alm, almacen.cant_alm, "
                + "almacen.precioC_alm, almacen.SKU FROM producto JOIN almacen ON producto.codB_prod = almacen.codProd_alm "
                + "WHERE nombre_prod LIKE '%" + nombre + "%' OR marca_prod LIKE '%" + nombre + "%';";

        if (query.consulta(consulta)) {
            try {
                res = query.getRes();
                while (res.next()) {
                    Producto prod = new Producto();
                    prod.setCodigoBarras(res.getString("codB_prod"));
                    prod.setId(res.getString("SKU"));
                    prod.setNombre(res.getNString("nombre_prod"));
                    prod.setMarca(res.getNString("marca_prod"));
                    prod.setTipo(res.getString("tipo_prod"));
                    prod.setIdPedido(Integer.parseInt(res.getString("idPed_alm")));
                    prod.setPrecioC(Double.parseDouble(res.getString("precioC_alm")));
                    prod.setPrecioV(Double.parseDouble(res.getString("precioV_prod")));
                    prod.setNumVentas(Integer.parseInt(res.getString("ventas_prod")));
                    prod.setNumVentas(Integer.parseInt(res.getString("stockMin_prod")));
                    prod.setCantidad(Integer.parseInt(res.getString("cant_alm")));
                    this.productos.add(prod);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //busca en los productos del almacen
    public void buscarPorCodigoB(String cod) {
        query = new Query();
        ResultSet res;
        this.productos.clear();
        String consulta = "SELECT producto.codB_prod, producto.nombre_prod, producto.marca_prod, producto.tipo_prod, "
                + "producto.precioV_prod, producto.ventas_prod, producto.stockMin_prod, almacen.idPed_alm, almacen.cant_alm, "
                + "almacen.precioC_alm, almacen.SKU FROM producto JOIN almacen ON producto.codB_prod = almacen.codProd_alm "
                + "WHERE codB_prod = '" + cod + "';";

        if (query.consulta(consulta)) {
            try {
                res = query.getRes();
                while (res.next()) {
                    Producto prod = new Producto();
                    prod.setCodigoBarras(res.getString("codB_prod"));
                    prod.setId(res.getString("SKU"));
                    prod.setNombre(res.getNString("nombre_prod"));
                    prod.setMarca(res.getNString("marca_prod"));
                    prod.setTipo(res.getString("tipo_prod"));
                    prod.setIdPedido(Integer.parseInt(res.getString("idPed_alm")));
                    prod.setPrecioC(Double.parseDouble(res.getString("precioC_alm")));
                    prod.setPrecioV(Double.parseDouble(res.getString("precioV_prod")));
                    prod.setNumVentas(Integer.parseInt(res.getString("ventas_prod")));
                    prod.setNumVentas(Integer.parseInt(res.getString("stockMin_prod")));
                    prod.setCantidad(Integer.parseInt(res.getString("cant_alm")));
                    this.productos.add(prod);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.Desconectar();
    }

    public void buscarPorProv(String prov) {
        query = new Query();
        ResultSet res;
        this.productos.clear();
        String consulta = "SELECT producto.codB_prod, producto.nombre_prod, producto.marca_prod, producto.tipo_prod, "
                + "producto.precioV_prod, producto.ventas_prod, almacen.idPed_alm, almacen.cant_alm, almacen.precioC_alm, "
                + "almacen.SKU, proveedor.razonS_prov FROM almacen JOIN producto ON producto.codB_prod = almacen.codProd_alm "
                + "JOIN pedido ON almacen.idPed_alm = pedido.id_ped  JOIN proveedor ON pedido.idProv_ped = proveedor.id_prov "
                + "WHERE proveedor.razonS_prov = '" + prov + "';";

        if (query.consulta(consulta)) {
            try {
                res = query.getRes();
                while (res.next()) {
                    Producto prod = new Producto();
                    prod.setCodigoBarras(res.getString("codB_prod"));
                    prod.setId(res.getString("SKU"));
                    prod.setNombre(res.getNString("nombre_prod"));
                    prod.setMarca(res.getNString("marca_prod"));
                    prod.setTipo(res.getString("tipo_prod"));
                    prod.setIdPedido(Integer.parseInt(res.getString("idPed_alm")));
                    prod.setPrecioC(Double.parseDouble(res.getString("precioC_alm")));
                    prod.setPrecioV(Double.parseDouble(res.getString("precioV_prod")));
                    prod.setNumVentas(Integer.parseInt(res.getString("ventas_prod")));
                    prod.setCantidad(Integer.parseInt(res.getString("cant_alm")));
                    this.productos.add(prod);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.Desconectar();
    }

    //Busca dentro la tabla producto
    public Producto buscarDatos(String codigo) {
        query = new Query();
        ResultSet res;
        Producto p = new Producto();
        if (query.seleccion("*", "producto", "WHERE codB_prod = '" + codigo + "'")) {
            res = query.getRes();

            try {
                if (res.next()) {
                    p.setCodigoBarras(codigo);
                    p.setNombre(res.getString("nombre_prod"));
                    p.setMarca(res.getString("marca_prod"));
                    p.setTipo(res.getString("tipo_prod"));
                    p.setPrecioC(Double.parseDouble(res.getString("ultPrecioC_prod")));
                    p.setPrecioV(Double.parseDouble(res.getString("precioV_prod")));
                    p.setNumVentas(Integer.parseInt(res.getString("ventas_prod")));
                    p.setStockMin(Integer.parseInt(res.getString("stockMin_prod")));
                    query.Desconectar();
                    return p;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        query.Desconectar();
        return null;
    }

    public List<Producto> obtenerProductos() {
        this.query = new Query();
        this.productos.clear();
        ResultSet res;

        String consulta = "SELECT producto.codB_prod, producto.nombre_prod, producto.marca_prod, producto.tipo_prod, "
                + "producto.precioV_prod, producto.ventas_prod, producto.stockMin_prod, almacen.idPed_alm, almacen.cant_alm, "
                + "almacen.precioC_alm, almacen.SKU FROM producto JOIN almacen ON producto.codB_prod = almacen.codProd_alm;";

        if (!query.consulta(consulta)) {
            System.out.println("no hay producto");
        } else {

            try {
                res = query.getRes();

                while (res.next()) {
                    Producto prod = new Producto();
                    prod.setCodigoBarras(res.getString("codB_prod"));
                    prod.setId(res.getString("SKU"));
                    prod.setNombre(res.getNString("nombre_prod"));
                    prod.setMarca(res.getNString("marca_prod"));
                    prod.setTipo(res.getString("tipo_prod"));
                    prod.setIdPedido(Integer.parseInt(res.getString("idPed_alm")));
                    prod.setPrecioC(Double.parseDouble(res.getString("precioC_alm")));
                    prod.setPrecioV(Double.parseDouble(res.getString("precioV_prod")));
                    prod.setNumVentas(Integer.parseInt(res.getString("ventas_prod")));
                    prod.setNumVentas(Integer.parseInt(res.getString("stockMin_prod")));
                    prod.setCantidad(Integer.parseInt(res.getString("cant_alm")));
                    this.productos.add(prod);
                }
                query.Desconectar();
                return this.productos;

            } catch (SQLException ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.Desconectar();
        return null;
    }

    public TableModel obtenerTablaProductos() {
        obtenerProductos();
        return creaModeloProdA(productos.size());

    }

    public TableModel creaModeloProdA(Integer filas) {
        TablaAlmacen modelo1 = new TablaAlmacen(filas);  //numero de filas del ResultSet como parametro del constructor
        int i = 0;
        Utilidades uts = new Utilidades();
        for (Producto p1 : productos) {
            modelo1.setValueAt(p1.getId(), i, 0);
            modelo1.setValueAt(p1.getNombre(), i, 1);
            modelo1.setValueAt(p1.getMarca(), i, 2);
            modelo1.setValueAt(p1.getTipo(), i, 3);
            modelo1.setValueAt(p1.getCantidad().toString(), i, 4);
            modelo1.setValueAt(uts.formatDinero(p1.getPrecioC()), i, 5);
            modelo1.setValueAt(uts.formatDinero(p1.getPrecioV()), i, 6);
            modelo1.setValueAt(p1.getIdPedido().toString(), i, 7);
            i++;
        }
        this.modelo = modelo1;
        return this.modelo;
    }

    public TableModel creaModeloProdV1(Integer filas) {
        TablaVenta modelo1 = new TablaVenta(filas);  //numero de filas del ResultSet como parametro del constructor
        int i = 0;
        for (Producto p1 : productos) {
            modelo1.setValueAt(p1.getId(), i, 0);
            modelo1.setValueAt(p1.getNombre(), i, 1);
            modelo1.setValueAt(p1.getMarca(), i, 2);
            modelo1.setValueAt(p1.getTipo(), i, 3);
            modelo1.setValueAt(p1.getCantidad().toString(), i, 4);
            modelo1.setValueAt(p1.getPrecioC().toString(), i, 5);
            modelo1.setValueAt(p1.getPrecioV().toString(), i, 6);
            modelo1.setValueAt(p1.getIdPedido().toString(), i, 7);
            i++;
        }
        this.modelo = modelo1;
        return this.modelo;
    }

    public Producto buscarPorSku(String sku) {
        query = new Query();
        ResultSet res;
        String consulta = "SELECT producto.codB_prod, producto.nombre_prod, producto.marca_prod, producto.tipo_prod, "
                + "producto.precioV_prod, almacen.idPed_alm,"
                + "almacen.precioC_alm, almacen.SKU FROM producto JOIN almacen ON producto.codB_prod = almacen.codProd_alm "
                + "WHERE SKU = '" + sku + "';";
        if (query.consulta(consulta)) {
            try {
                res = query.getRes();
                while (res.next()) {
                    Producto prod = new Producto();
                    prod.setCodigoBarras(res.getString("codB_prod"));
                    prod.setId(res.getString("SKU"));
                    prod.setNombre(res.getNString("nombre_prod"));
                    prod.setMarca(res.getNString("marca_prod"));
                    prod.setTipo(res.getString("tipo_prod"));
                    prod.setPrecioC(Double.parseDouble(res.getString("precioC_alm")));
                    prod.setPrecioV(Double.parseDouble(res.getString("precioV_prod")));
                    prod.setIdPedido(Integer.parseInt(res.getString("idPed_alm")));
                    query.Desconectar();
                    return prod;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.Desconectar();
        return null;
    }

    public void buscarPorPrecio(Double rangoI, Double rangoF) {

    }

    public void buscarPorPedido(String ped) {
        query = new Query();
        ResultSet res;
        this.productos.clear();
        String consulta = "SELECT producto.codB_prod, producto.nombre_prod, producto.marca_prod, producto.tipo_prod, "
                + "producto.precioV_prod, producto.ventas_prod, producto.stockMin_prod, almacen.idPed_alm, almacen.cant_alm, "
                + "almacen.precioC_alm, almacen.SKU FROM producto JOIN almacen ON producto.codB_prod = almacen.codProd_alm "
                + "WHERE idPed_alm = '" + ped + "';";
        if (query.consulta(consulta)) {
            try {
                res = query.getRes();
                while (res.next()) {
                    Producto prod = new Producto();
                    prod.setCodigoBarras(res.getString("codB_prod"));
                    prod.setId(res.getString("SKU"));
                    prod.setNombre(res.getNString("nombre_prod"));
                    prod.setMarca(res.getNString("marca_prod"));
                    prod.setTipo(res.getString("tipo_prod"));
                    prod.setIdPedido(Integer.parseInt(res.getString("idPed_alm")));
                    prod.setPrecioC(Double.parseDouble(res.getString("precioC_alm")));
                    prod.setPrecioV(Double.parseDouble(res.getString("precioV_prod")));
                    prod.setNumVentas(Integer.parseInt(res.getString("ventas_prod")));
                    prod.setNumVentas(Integer.parseInt(res.getString("stockMin_prod")));
                    prod.setCantidad(Integer.parseInt(res.getString("cant_alm")));
                    this.productos.add(prod);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.Desconectar();
    }

    public boolean regPedido(Pedido pedido) {
        if (pedido.regPedido(pedido)) {
            return true;
        } else {
            return false;
        }
    }

    //actualiza cantidades 
    public void actualizarProducto(List<Producto> prods) {
        this.productos = obtenerProductos();
        for (Producto p : this.productos) {
            for (Producto p2 : prods) {
                if (p.getId().equals(p2.getId())) {
                    if (!p.getCantidad().equals(p2.getCantidad())) {
                        if (p2.getCantidad() == 0) {
                            query = new Query();
                            String queryS = "DELETE FROM almacen WHERE SKU=" + p.getId() + ";";
                            query.ejecuta(queryS);
                            query.Desconectar();
                        } else {
                            query = new Query();
                            String queryS = "UPDATE almacen SET cant_alm=" + p2.getCantidad().toString() + " WHERE SKU=" + p.getId() + ";";
                            query.ejecuta(queryS);
                            query.Desconectar();
                        }
                    }
                }
            }
        }
    }

    public Integer obtenerNumVentas(Producto p) {
        Producto p1 = buscarDatos(p.getCodigoBarras());
        return p1.getNumVentas();
    }
}
