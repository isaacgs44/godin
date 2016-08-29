/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author thinkpad
 */
public class Producto {
    private String sku;
    private String nombre;
    private Integer idPedido;
    private Integer cantidad;
    private Double precioC;
    private Double precioV;
    private String codigoBarras;
    private String tipo;   
    private String marca;
    private Integer numVentas;
    private Integer stockMin;
    private Boolean nuevo;
  
    
    public Producto(String codB, String nombre, String marca, String tipo, Double precioC, Double precioV){
        this.codigoBarras = codB;
        this.nombre = nombre;
        this.precioC = precioC;
        this.precioV = precioV;
        this.tipo = tipo;
        this.marca = marca;
        this.stockMin = 0;
        this.numVentas = 0;
        this.cantidad = null;
        this.idPedido = null;
        this.sku = null;
        nuevo = true;
    }

    public Producto(Producto p, Integer idPed, Integer cant, Double precioC) {
        this.codigoBarras = p.getCodigoBarras();
        this.nombre = p.getNombre();
        this.precioC = precioC;
        this.precioV = p.getPrecioV();
        this.tipo = p.getTipo();
        this.marca = p.getMarca();
        this.stockMin = null;
        this.numVentas = null;
        this.cantidad = cant;
        this.idPedido = idPed;
        this.sku = null;
        nuevo = false;
    }
    
     public Producto() {
        this.codigoBarras = null;
        this.nombre = null;
        this.precioC = null;
        this.precioV = null;
        this.tipo = null;
        this.marca = null;
        this.stockMin = null;
        this.numVentas = null;
        this.cantidad = null;
        this.idPedido = null;
        this.sku = null;
        nuevo = false;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getNumVentas() {
        return numVentas;
    }

    public void setNumVentas(Integer numVentas) {
        this.numVentas = numVentas;
    }

    public Integer getStockMin() {
        return stockMin;
    }

    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    public Boolean getNuevo() {
        return nuevo;
    }

    public void setNuevo(Boolean nuevo) {
        this.nuevo = nuevo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }    
    
    public String getId() {
        return sku;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Double getPrecioC() {
        return precioC;
    }

    public Double getPrecioV() {
        return precioV;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(String id) {
        this.sku = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioC(Double precioC) {
        this.precioC = precioC;
    }

    public void setPrecioV(Double precioV) {
        this.precioV = precioV;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String buscaCtaProv(Producto p) {
        Pedido ped = new Pedido();
        if(p.getIdPedido() == 9999){
            return "AC002-001"; //cuenta de devoluciones
        }
        Proveedor prov = ped.buscarProveedor(p.getIdPedido().toString());
        return prov.getCtaAlm();
    }
}
