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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author lenovo
 */
public class LibroDiario {

    private List<Operacion> operaciones;
    private Query query;
    DecimalFormat df = new DecimalFormat("###,###.##");

    public LibroDiario() {
        operaciones = new ArrayList<>();
        operaciones = obtenerOperaciones();
    }

    public List<Operacion> buscarOperaacion(String txt) {
        this.operaciones.clear();
        ResultSet res = null;
        query = new Query();
        String cond = "WHERE desc_op LIKE '%" + txt + "%'";
        query.seleccion("*", "operacion", cond);
        res = query.getRes();
        try {
            while (res.next()) {
                Operacion o = new Operacion();
                o.setId(res.getString("id_op"));
                o.setFecha(res.getString("fecha_op"));
                o.setCtaCargo(res.getString("idCtaC_op"));
                o.setCtaAbono(res.getString("idCtaA_op"));
                o.setDesc(res.getString("desc_op"));
                o.setMonto(Double.parseDouble(res.getString("monto_op")));
                this.operaciones.add(o);
            }
            query.Desconectar();
            return this.operaciones;
        } catch (SQLException ex) {
            Logger.getLogger(LibroDiario.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
    }

    public List<Operacion> obtenerOperaciones() {
        this.operaciones.clear();
        ResultSet res = null;
        query = new Query();
        query.seleccion("*", "operacion");
        res = query.getRes();
        try {
            while (res.next()) {
                Operacion o = new Operacion();
                o.setId(res.getString("id_op"));
                o.setFecha(res.getString("fecha_op"));
                o.setCtaCargo(res.getString("idCtaC_op"));
                o.setCtaAbono(res.getString("idCtaA_op"));
                o.setDesc(res.getString("desc_op"));
                o.setMonto(Double.parseDouble(res.getString("monto_op")));
                this.operaciones.add(o);
            }
            query.Desconectar();
            return this.operaciones;
        } catch (SQLException ex) {
            Logger.getLogger(LibroDiario.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
    }

    //crea modelo para la tabla de operaciones
    public TableModel creaModeloOp() {
        TablaOp modelo1 = new TablaOp(operaciones.size(), 6);  //numero de filas del ResultSet como parametro del constructor
        int i = 0;
        LibroMayor m = new LibroMayor();
        System.out.println(" - " + operaciones.size());
        query = new Query();
        for (Operacion o : operaciones) {
            Cuenta ctaA;
            Cuenta ctaC;
            if (o.getCtaAbono().length() > 6) {
                ctaA = m.obtenerCuenta(o.getCtaAbono(), "s", query);
            } else if (o.getCtaAbono().length() == 5) {
                ctaA = m.obtenerCuenta(o.getCtaAbono(), "c", query);
            } else {
                ctaA = null;
            }
            if (o.getCtaCargo().length() > 6) {
                ctaC = m.obtenerCuenta(o.getCtaCargo(), "s", query);
            } else if (o.getCtaCargo().length() == 5) {
                ctaC = m.obtenerCuenta(o.getCtaCargo(), "c", query);
            } else {
                ctaC = null;
            }
            modelo1.setValueAt(o.getId(), i, 0);
            String fe = o.getFecha().substring(8, 10) + "-" + o.getFecha().substring(5, 7) + "-" + o.getFecha().substring(2, 4);
            modelo1.setValueAt(fe, i, 1);
            modelo1.setValueAt(o.getDesc(), i, 2);

            if (ctaC != null) {
                modelo1.setValueAt(ctaC.getNombre(), i, 3);
            } else {
                modelo1.setValueAt("-----", i, 3);
            }

            if (ctaA != null) {
                modelo1.setValueAt(ctaA.getNombre(), i, 4);
            } else {
                modelo1.setValueAt("-----", i, 4);
            }

            modelo1.setValueAt(df.format(o.getMonto()), i, 5);
            i++;
        }
        query.Desconectar();
        return modelo1;
    }

    public TableModel creaModeloOp(List<Operacion> ops) {
        TablaOp modelo1 = new TablaOp(ops.size(), 6);  //numero de filas del ResultSet como parametro del constructor
        int i = 0;
        LibroMayor m = new LibroMayor();
        System.out.println(" - " + ops.size());
        for (Operacion o : ops) {
            Cuenta ctaA;
            Cuenta ctaC;
            if (o.getCtaAbono().length() > 6) {
                ctaA = m.obtenerCuenta(o.getCtaAbono(), "s");
            } else if (o.getCtaAbono().length() == 5) {
                ctaA = m.obtenerCuenta(o.getCtaAbono(), "c");
            } else {
                ctaA = null;
            }
            if (o.getCtaCargo().length() > 6) {
                ctaC = m.obtenerCuenta(o.getCtaCargo(), "s");
            } else if (o.getCtaCargo().length() == 5) {
                ctaC = m.obtenerCuenta(o.getCtaCargo(), "c");
            } else {
                ctaC = null;
            }
            modelo1.setValueAt(o.getId(), i, 0);
            String fe = o.getFecha().substring(8, 10) + "-" + o.getFecha().substring(5, 7) + "-" + o.getFecha().substring(2, 4);
            modelo1.setValueAt(fe, i, 1);
            modelo1.setValueAt(o.getDesc(), i, 2);

            if (ctaC != null) {
                modelo1.setValueAt(ctaC.getNombre(), i, 3);
            } else {
                modelo1.setValueAt("-----", i, 3);
            }

            if (ctaA != null) {
                modelo1.setValueAt(ctaA.getNombre(), i, 4);
            } else {
                modelo1.setValueAt("-----", i, 4);
            }

            modelo1.setValueAt(df.format(o.getMonto()), i, 5);
            i++;
        }
        return modelo1;
    }

    public List<Operacion> buscarPorfechaOp(String opcM, String anio) {
        String mes = "";
        ResultSet res = null;
        query = new Query();
        switch (opcM) {
            case "enero":
                mes = "01";
                break;
            case "febrero":
                mes = "02";
                break;
            case "marzo":
                mes = "03";
                break;
            case "abril":
                mes = "04";
                break;
            case "mayo":
                mes = "05";
                break;
            case "junio":
                mes = "06";
                break;
            case "julio":
                mes = "07";
                break;
            case "agosto":
                mes = "08";
                break;
            case "septiembre":
                mes = "09";
                break;
            case "octubre":
                mes = "10";
                break;
            case "noviembre":
                mes = "11";
                break;
            case "diciembre":
                mes = "12";
                break;
            default:
                mes = "00";
        }
        this.operaciones.clear();
        String cond = "WHERE fecha_op LIKE '" + anio + "-" + mes + "%'";
        query.seleccion("*", "operacion", cond);
        res = query.getRes();
        try {
            while (res.next()) {
                Operacion o = new Operacion();
                o.setId(res.getString("id_op"));
                o.setFecha(res.getString("fecha_op"));
                o.setCtaCargo(res.getString("idCtaC_op"));
                o.setCtaAbono(res.getString("idCtaA_op"));
                o.setDesc(res.getString("desc_op"));
                o.setMonto(Double.parseDouble(res.getString("monto_op")));
                this.operaciones.add(o);
            }
            query.Desconectar();
            return this.operaciones;
        } catch (SQLException ex) {
            Logger.getLogger(LibroDiario.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
    }

    public boolean borrarOps(String idCta) {
        query = new Query();
        Boolean valor;
        String sen = "DELETE FROM operacion WHERE idCtaC_op = '" + idCta + "' OR idCtaA_op = '" + idCta + "'";
        valor = query.ejecuta(sen);
        query.Desconectar();
        return valor;
    }
}
