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
public class Vendedor {
    private Query query;
    private Integer id;
    private String nombre;
    private Double salario;
    private Double comision;
    private List <Vendedor> vendedores;
    private Integer numVentas;
    private String cta;
    
    public Vendedor(){
        vendedores = new ArrayList <>();
    }

    public String getCta() {
        return cta;
    }

    public void setCta(String cta) {
        this.cta = cta;
    }
    
    public Integer getNumVentas() {
        return numVentas;
    }

    public void setNumVentas(Integer numVentas) {
        this.numVentas = numVentas;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(List<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }
    
    public Boolean agregarVendedor(String nombre, Double salario, Double comision){
        query = new Query();
        Boolean valor;
        String valores = "(nombre_vend, salario_vend, comision_vend, num_vent, ctaSalario_vend)"
                   + " VALUES ('" + nombre + "','" + salario + "','" + comision + "','0','" + obtenerCtaSalario(nombre) + "')";    
               
        valor = query.insertar("vendedor",valores);
        query.Desconectar();
        return valor;
    } 
    
    public List <Vendedor> obtenerVendedores(){
        query = new Query();
        this.vendedores.clear();
        ResultSet res; 
        if(!query.seleccion("*", "vendedor")){
            System.out.println("No hay vendedores en la BDD");
        }else{
            try {
                res=query.getRes();
           
                while(res.next()){
                    Vendedor v = new Vendedor();
                    v.setId(Integer.parseInt(res.getString("id")));
                    v.setNombre(res.getString("nombre_vend"));
                    v.setSalario(Double.parseDouble(res.getString("salario_vend")));
                    v.setComision(Double.parseDouble(res.getString("comision_vend")));
                    v.setNumVentas(Integer.parseInt(res.getString("num_vent")));
                    v.setCta(res.getString("ctaSalario_vend"));
                    vendedores.add(v);
                }
                query.Desconectar();
                return this.vendedores;            
            }
            catch (SQLException ex) {
                Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.Desconectar();
        return null;
    }
    
    public TableModel obtenerTablaVend() {
        this.vendedores = obtenerVendedores();
        TablaVendedores modelo1 = new TablaVendedores(this.vendedores.size());  //e - empleados, numero de filas del ResultSet como parametro del constructor
        int i=0;
        for (Vendedor v : this.vendedores) {
              modelo1.setValueAt(v.getId(), i, 0);
              modelo1.setValueAt(v.getNombre(), i, 1);
              modelo1.setValueAt(v.getSalario(),i,2);
              modelo1.setValueAt(v.getComision(),i,3);
              modelo1.setValueAt(v.getNumVentas().toString(),i,4);
              i++;
        }        
        return modelo1;
    }
    
    public void actualizaVentas(Vendedor v1){
        query = new Query();
        Integer nuevoN = null;
        ResultSet res; 
        String condicion = "WHERE id ="+ v1.getId();
        if(!query.seleccion("num_vent", "vendedor", condicion)){
            System.out.println("No hay vendedores en la BDD");
        }else{
            try {
                res=query.getRes();
           
                if(res.next()){
                    nuevoN = Integer.parseInt(res.getString("num_vent")) + 1;                    
                }                         
                
                String consulta = "UPDATE vendedor SET num_vent=" + nuevoN.toString() + " WHERE id=" + v1.getId() + ";";
                
                if(query.ejecuta(consulta)){
                    System.out.println("ventas actualizadas: " + nuevoN.toString());
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Vendedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        query.Desconectar();
    }

    private String obtenerCtaSalario(String nombre) {
        Cuenta cuenta = new Cuenta();
        cuenta.agregarSubCta("GA000", nombre); // GA000 - Sueldos
        return cuenta.getId();
    }
}
