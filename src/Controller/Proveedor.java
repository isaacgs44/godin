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
public class Proveedor {

    private String id; // RFC
    private String razonSocial;
    private String direccion;
    private String telefono;
    private String email;
    private TablaProveedores modelo;
    private Query query;
    private String ctaAlm;
    private List<Proveedor> prov;

    public Proveedor() {
        prov = new ArrayList<>();
        obtenerProveedores();
    }

    public Proveedor(String id) {
        this.id = id;
        prov = null;

    }

    public List<Proveedor> getProveedores() {
        return prov;
    }

    public void setProv(List<Proveedor> prov) {
        this.prov = prov;
    }

    public String getCtaAlm() {
        return ctaAlm;
    }

    public void setCtaAlm(String ctaAlm) {
        this.ctaAlm = ctaAlm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Proveedor(String id, String razonSocial, String direccion, String telefono, String email) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;

    }

    public boolean agregarProv(Proveedor prov) {
        query = new Query();
        String valores = "(id_prov,razonS_prov,direccion_prov,tel_prov,email_prov, ctaAlmacen_prov)"
                + " VALUES ('" + prov.getId() + "','" + prov.getRazonSocial() + "','" + prov.getDireccion() + "','" + prov.getTelefono() + "','" + prov.getEmail() + "','" + obtenerCtaAlm(prov) + "')";

        if (query.insertar("proveedor", valores)) {
            query.Desconectar();
            return true;
        } else {
            query.Desconectar();
            return false;
        }
    }

    public TableModel buscarProv(String nombre) {
        query = new Query();
        this.prov.clear();
        ResultSet res;
        String condicion = "WHERE razonS_prov LIKE '%" + nombre + "%'";
        if (!query.seleccion("*", "proveedor", condicion)) {
            System.out.println("No hay proveedores");
        } else {
            res = query.getRes();
            try {
                while (res.next()) {
                    Proveedor prov = new Proveedor();
                    prov.setId(res.getString(1));
                    prov.setRazonSocial(res.getString(2));
                    prov.setDireccion(res.getNString(3));
                    prov.setTelefono(res.getString(4));
                    prov.setEmail(res.getString(5));
                    prov.setCtaAlm(res.getString(6));
                    this.prov.add(prov);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        creaModeloProv(prov.size(), prov);
        query.Desconectar();
        return this.modelo;
    }

    public void eliminarProv(Proveedor prov) {

    }

    public void modProv(Proveedor prov) {

    }

    private TableModel obtenerProveedores() {
        query = new Query();
        prov.clear();
        ResultSet res;
        if (!query.seleccion("*", "proveedor")) {
            System.out.println("no hay proveedores");
        } else {

            try {
                res = query.getRes();

                while (res.next()) {
                    Proveedor p = new Proveedor(res.getString(1));
                    p.setRazonSocial(res.getString(2));
                    p.setDireccion(res.getNString(3));
                    p.setTelefono(res.getString(4));
                    p.setEmail(res.getString(5));
                    p.setCtaAlm(res.getString(6));
                    prov.add(p);
                }
                query.Desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
                query.Desconectar();
            }
        }
        creaModeloProv(prov.size(), prov);
        query.Desconectar();
        return modelo;
    }

    public void creaModeloProv(Integer filas, List<Proveedor> proveedores) {
        TablaProveedores modelo1 = new TablaProveedores(filas);  //numero de filas del ResultSet como parametro del constructor
        int i = 0;
        for (Proveedor p1 : proveedores) {
            modelo1.setValueAt(p1.getId(), i, 0);
            modelo1.setValueAt(p1.getRazonSocial(), i, 1);
            modelo1.setValueAt(p1.getDireccion(), i, 2);
            modelo1.setValueAt(p1.getEmail(), i, 3);
            modelo1.setValueAt(p1.getTelefono(), i, 4);
            i++;
        }
        modelo = modelo1;
    }

    private String obtenerCtaAlm(Proveedor prov) {
        Cuenta cta = new Cuenta();
        cta.agregarSubCta("AC002", prov.getRazonSocial()); // AC002 - Almacen
        return cta.getId();
    }

    public Proveedor buscarProvId(String idProv) {
        query = new Query();
        ResultSet res;
        String condicion = "WHERE id_prov = '" + idProv + "'";
        if (!query.seleccion("*", "proveedor", condicion)) {
            System.out.println("No hay proveedores");
        } else {
            res = query.getRes();
            try {
                while (res.next()) {
                    Proveedor prov = new Proveedor();
                    prov.setId(res.getString(1));
                    prov.setRazonSocial(res.getString(2));
                    prov.setDireccion(res.getNString(3));
                    prov.setTelefono(res.getString(4));
                    prov.setEmail(res.getString(5));
                    prov.setCtaAlm(res.getString(6));
                    query.Desconectar();
                    return prov;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.Desconectar();
        return null;
    }
}
