/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Almacen;
import Controller.Pedido;
import Controller.Proveedor;
import Controller.Secretaria;
import Controller.Utilidades;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.TableModel;

/**
 *
 * @author lenovo
 */
public class IntAlmacen extends javax.swing.JInternalFrame {
    private TableModel modeloTabla;
    private Proveedor prov;
    /**
     * Creates new form IntGestionarProd
     */
    public IntAlmacen() {
        initComponents();
        
        ((JPanel)getContentPane()).setOpaque(fal­se); 
        Almacen a = new Almacen();
        modeloTabla = a.obtenerTablaProductos();
        tablaProd.setModel(modeloTabla);
        titulosTabla();
        Pedido p = new Pedido();
        llenarCmbPed(p.obtenerPedidos());
        chNombre.setSelected(true);    
        txtBuscar.requestFocus();
        this.prov = new Proveedor();
        this.prov.obtenerProveedores();
        llenarCmbProv(this.prov.getProv());
        
    }
    
     private void llenarCmbProv(List<Proveedor> proveedores){
       String[] cadena = new String[proveedores.size()+1];
        int i=1;
        cadena[0] = "Todos";
        for(Proveedor p : proveedores){
           cadena[i]=p.getRazonSocial();
            i++;
        }
        cmbProv.setModel(new DefaultComboBoxModel((Object[]) cadena));
    }
        
    
      private void llenarCmbPed(List<Pedido> pedidos){
       String[] cadena = new String[pedidos.size()+1];
        int i=1;
        cadena[0] = "todo";
        for(Pedido p : pedidos){
           cadena[i]=p.getId().toString();
            i++;
        }
        cmbPedido.setModel(new DefaultComboBoxModel((Object[]) cadena));
    }
    
    private void titulosTabla(){
        tablaProd.getColumn(tablaProd.getColumnName(0)).setHeaderValue(" ");
        tablaProd.getColumn(tablaProd.getColumnName(1)).setHeaderValue("Nombre");
        tablaProd.getColumn(tablaProd.getColumnName(2)).setHeaderValue("Marca");
        tablaProd.getColumn(tablaProd.getColumnName(3)).setHeaderValue("Tipo");
        tablaProd.getColumn(tablaProd.getColumnName(4)).setHeaderValue("Cantidad");
        tablaProd.getColumn(tablaProd.getColumnName(5)).setHeaderValue("Precio C");
        tablaProd.getColumn(tablaProd.getColumnName(6)).setHeaderValue("Precio V");
        tablaProd.getColumn(tablaProd.getColumnName(7)).setHeaderValue("Pedido");
        
        tablaProd.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaProd.getColumnModel().getColumn(0).setMinWidth(0);
        tablaProd.getColumnModel().getColumn(0).setPreferredWidth(0);
        tablaProd.setRowHeight(30);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProd = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnMostrarTodo = new javax.swing.JButton();
        cmbPedido = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        chNombre = new javax.swing.JRadioButton();
        chCodigo = new javax.swing.JRadioButton();
        cmbProv = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Almacén");

        tablaProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Marca", "Tipo", "Cantidad", "Precio C", "Precio V", "Pedido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaProd);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnMostrarTodo.setText("Mostrar todo");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });

        cmbPedido.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPedidoActionPerformed(evt);
            }
        });

        jLabel2.setText("Pedido:");

        chNombre.setText("Nombre");
        chNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chNombreActionPerformed(evt);
            }
        });

        chCodigo.setText("Código");
        chCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chCodigoActionPerformed(evt);
            }
        });

        cmbProv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvActionPerformed(evt);
            }
        });

        jLabel1.setText("Proveedor:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chNombre)
                        .addGap(18, 18, 18)
                        .addComponent(chCodigo)
                        .addGap(114, 114, 114)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 957, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMostrarTodo)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(cmbProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chCodigo)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)
                            .addComponent(chNombre)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMostrarTodo)
                .addGap(28, 28, 28))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chNombreActionPerformed
        chCodigo.setSelected(false);
        txtBuscar.requestFocus();
    }//GEN-LAST:event_chNombreActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
            if(chCodigo.isSelected()){
                if(Pattern.matches("^([A-Z,Ñ,ñ,a-z,0-9,-]{1,50})$",txtBuscar.getText())){
                    Almacen a = new Almacen();
                    a.buscarPorCodigoB(txtBuscar.getText());
                    this.modeloTabla = a.creaModeloProdA(a.getProductos().size());
                    tablaProd.setModel(modeloTabla);
                    titulosTabla();
                }else{
                    JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
                }

            }else if(chNombre.isSelected()){
                if(Pattern.matches("^(([A-Z,Ñ,ñ,a-z,-]{1,30})([ ]{0,1})*){1,6}$",txtBuscar.getText())){
                    Almacen a = new Almacen();
                    a.buscarPorNombre(txtBuscar.getText());
                    this.modeloTabla = a.creaModeloProdA(a.getProductos().size());
                    tablaProd.setModel(modeloTabla);
                    titulosTabla();            
                }else{
                    JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
                }
            }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        Almacen a = new Almacen();
        modeloTabla = a.obtenerTablaProductos();
        tablaProd.setModel(modeloTabla);
        titulosTabla();
        txtBuscar.setText("");
        cmbPedido.setSelectedIndex(0);
        txtBuscar.requestFocus();
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    private void chCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chCodigoActionPerformed
        chNombre.setSelected(false);    
        txtBuscar.requestFocus();
    }//GEN-LAST:event_chCodigoActionPerformed

    private void cmbPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPedidoActionPerformed
        Pedido p = new Pedido();
        System.out.println(cmbPedido.getSelectedIndex());
        if(cmbPedido.getSelectedIndex() == 0){
            Almacen a = new Almacen();
            modeloTabla = a.obtenerTablaProductos();
            tablaProd.setModel(modeloTabla);
            titulosTabla();
        }else{
            String id = p.obtenerPedidos().get(cmbPedido.getSelectedIndex()-1).getId().toString();           
            Almacen a = new Almacen();
            a.buscarPorPedido(id);
            modeloTabla = a.creaModeloProdA(a.getProductos().size());
            tablaProd.setModel(modeloTabla);
            titulosTabla();
        }
    }//GEN-LAST:event_cmbPedidoActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(chCodigo.isSelected()){
                if(Pattern.matches("^([A-Z,Ñ,ñ,a-z,0-9,-]{1,50})$",txtBuscar.getText())){
                    Almacen a = new Almacen();
                    a.buscarPorCodigoB(txtBuscar.getText());
                    this.modeloTabla = a.creaModeloProdA(a.getProductos().size());
                    tablaProd.setModel(modeloTabla);
                    titulosTabla();
                }else{
                    JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
                }

            }else if(chNombre.isSelected()){
                if(Pattern.matches("^(([A-Z,Ñ,ñ,a-z,-]{1,30})([ ]{0,1})*){1,6}$",txtBuscar.getText())){
                    Almacen a = new Almacen();
                    a.buscarPorNombre(txtBuscar.getText());
                    this.modeloTabla = a.creaModeloProdA(a.getProductos().size());
                    tablaProd.setModel(modeloTabla);
                    titulosTabla();            
                }else{
                    JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
                }
            }
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void cmbProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvActionPerformed
        if(cmbProv.getSelectedIndex()!=0){
        Almacen a = new Almacen();
            a.buscarPorProv(cmbProv.getSelectedItem().toString());
            modeloTabla = a.creaModeloProdA(a.getProductos().size());
            tablaProd.setModel(modeloTabla);
            titulosTabla();
        }
    }//GEN-LAST:event_cmbProvActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JRadioButton chCodigo;
    private javax.swing.JRadioButton chNombre;
    private javax.swing.JComboBox cmbPedido;
    private javax.swing.JComboBox cmbProv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProd;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

    private void setLocationRelativeTo(Object object) {
    }
}
