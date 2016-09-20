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

/**
 *
 * @author lenovo
 */
public class Pedido {

    private Integer id;
    private String fecha;
    private Double neto;
    private Double envio;
    private String idProv;
    private String desc;
    private Query query;
    private List<Producto> productos;
    private List<Pedido> pedidos;

    public Pedido() {
        productos = new ArrayList<>();
        pedidos = new ArrayList<>();
        obtenerPedidos(); //obtengo lista de pedidos
    }
   
     public Pedido(Integer id) {
         this.id=id;
        productos = null;
        pedidos = null;
    }

    public void asignarID() {
        setId(obtenerUltIdPedido());
    }
    
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getNeto() {
        return neto;
    }

    public void setNeto(Double neto) {
        this.neto = neto;
    }

    public Double getEnvio() {
        return envio;
    }

    public void setEnvio(Double envio) {
        this.envio = envio;
    }

    public String getIdProv() {
        return idProv;
    }

    public void setIdProv(String idProv) {
        this.idProv = idProv;
    }

    

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Proveedor buscarProveedor(String idPed) {
        Proveedor prov = new Proveedor();
        query = new Query();
        ResultSet res;
        String cond = "WHERE id_ped = '" + idPed + "'";
        if (!query.seleccion("idProv_ped", "pedido", cond)) {
            return null;
        } else {
            res = query.getRes();
            try {
                res = query.getRes();
                while (res.next()) {
                    prov = prov.buscarProvId(res.getString("idProv_ped"));
                }
                query.Desconectar();
                return prov;
            } catch (SQLException ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
            query.Desconectar();
            return null;
        }
    }

    private String creaDescripcion() {
        String cadena = "";
        for (Producto p : this.productos) {
            cadena += "(" + p.getCodigoBarras() + "," + p.getCantidad().toString() + "," + p.getPrecioC() + ")";
        }
        return cadena;
    }

    public boolean regPedido(Pedido pedido) {
        query = new Query();
        creaDescripcion();
        String valores = "(id_ped,idProv_ped,fecha_ped,neto_ped,envio_ped,desc_ped)"
                + " VALUES ('" + pedido.getId().toString() + "','" + pedido.getIdProv() + "','" + pedido.getFecha() + "','" + pedido.getNeto().toString() + "','" + pedido.getEnvio().toString() + "','" + creaDescripcion() + "')";

        if (query.insertar("pedido", valores)) {
            query.Desconectar();
            return true;
        } else {
            query.Desconectar();
            return false;
        }
    }

    private void obtenerPedidos() {
        query = new Query();
        ResultSet res;
        if (!query.seleccion("id_ped", "pedido")) {
            query.Desconectar();
        } else {
            res = query.getRes();
            try {
                res = query.getRes();
                while (res.next()) {
                    Pedido p = new Pedido(Integer.parseInt(res.getString(1)));
                    pedidos.add(p);
                }
                query.Desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            }
            query.Desconectar();
        }
    }

    public Integer obtenerUltIdPedido() {
        query = new Query();
        ResultSet res;
        if (!query.seleccion("id_ped", "pedido")) {
            return 1;
        } else {
            res = query.getRes();
            String ultimoId = null;
            try {
                while (res.next()) {
                    ultimoId = res.getString("id_ped");
                }
                query.Desconectar();
                return Integer.parseInt(ultimoId) + 1;
            } catch (SQLException ex) {
                Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.Desconectar();
        return null;
    }
}
