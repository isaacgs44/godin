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
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class Historico {

    private List<Cuenta> cuentas;
    private Query query;
    private String[] periodos;
    private Integer numeroPeriodos;
    private List<Indicador> indicadores;

    public Historico() {
        cuentas = new ArrayList<>();
        indicadores = new ArrayList<>();
    }

    public List<Cuenta> obtenerHistorico() {
        cuentas.clear();
        ResultSet res = null;
        query = new Query();
        query.seleccion("*", "historico", "ORDER BY id ASC");
        res = query.getRes();
        try {
            while (res.next()) {
                Cuenta c = new Cuenta();
                c.setId(res.getString("idCta_his"));
                c.setNombre(res.getString("nomCta_his"));
                c.setSaldo(Double.parseDouble(res.getString("saldoCta_his")));
                c.setPeriodo(res.getString("periodo_his"));
                cuentas.add(c);
            }
            query.Desconectar();
            return this.cuentas;
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
    }

    public List<Indicador> obtenerHistoricoInd() {
        this.indicadores.clear();
        ResultSet res = null;
        query = new Query();
        query.seleccion("*", "indicadores");
        res = query.getRes();
        try {
            while (res.next()) {
                Indicador i = new Indicador();
                i.setNombre(res.getString("nom_ind"));
                i.setValor(Double.parseDouble(res.getString("valor_ind")));
                i.setPeriodo(res.getString("periodo_ind"));
                indicadores.add(i);
            }
            query.Desconectar();
            return indicadores;
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
    }

    public List<Cuenta> obtenerHistorico(String periodo) {
        this.cuentas.clear();
        ResultSet res = null;
        query = new Query();
        String cond = "WHERE periodo_his = '" + periodo + "'";
        query.seleccion("*", "historico", cond);
        res = query.getRes();
        try {
            while (res.next()) {
                Cuenta c = new Cuenta();
                c.setId(res.getString("idCta_his"));
                c.setNombre(res.getString("nomCta_his"));
                c.setSaldo(Double.parseDouble(res.getString("saldoCta_his")));
                c.setPeriodo(res.getString("periodo_his"));
                this.cuentas.add(c);
            }
            query.Desconectar();
            return this.cuentas;
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
            return null;
        }
    }

    public String getFechaI() {
        cuentas = obtenerHistorico();
        if (cuentas.size() > 0) {
            String periodo = cuentas.get(cuentas.size() - 1).getPeriodo();
            StringTokenizer st = new StringTokenizer(periodo, "/");

            while (st.hasMoreTokens()) {
                st.nextToken();
                return st.nextToken(); //fecha final del ultimo periodo
            }

        } else {
            LibroDiario d = new LibroDiario();
            String fecha = d.obtenerOperaciones().get(0).getFecha();
            return fecha.substring(8, 10) + "-" + fecha.substring(5, 7) + "-" + fecha.substring(0, 4);
        }
        return "noValido";
    }

    public Integer calculaNumPeriodos() {
        String aux = "";
        int i = 0;
        int contador = 0;
        for (Cuenta c : this.cuentas) {
            if (i == 0) {
                aux = c.getPeriodo();
                i++;
                contador++;
            } else if (aux.equals(c.getPeriodo())) {
                i++;
            } else {
                contador++;
                aux = c.getPeriodo();
            }
        }
        this.numeroPeriodos = contador;
        return contador;
    }

    public void buscaPeriodos() { // array string con todos los periodos
        obtenerHistorico();
        calculaNumPeriodos();
        periodos = new String[this.numeroPeriodos];
        String aux = "";
        int i = 0;
        for (Cuenta c : this.cuentas) {
            if (i == 0) {
                aux = c.getPeriodo();
                periodos[i] = aux;
                i++;
            } else if (!aux.equals(c.getPeriodo())) {
                aux = c.getPeriodo();
                periodos[i] = aux;
                i++;
            }
        }
    }

    public String[] getPeriodos() {
        return periodos;
    }

    public Integer getNumeroPeriodos() {
        return numeroPeriodos;
    }

}
