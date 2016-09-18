/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Productos;
import javax.swing.table.TableModel;
import Controller.Productos;
import Controller.Secretaria;
import Controller.Utilidades;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.TableModel;

/**
 *
 * @author lenovo
 */
public class DialogoAdminProducto extends javax.swing.JDialog {

    TableModel modeloTabla;

    /**
     * Creates new form DialogoAdminProducto
     */
    public DialogoAdminProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Productos prods = new Productos();
        modeloTabla = prods.obtenerTablaProductos();
        tablaProd.setModel(modeloTabla);
        txtBuscar.setText("");
        txtBuscar.requestFocus();
        titulosTabla();
        chNombre.setSelected(true);

    }

    private void titulosTabla() {
        tablaProd.getColumn(tablaProd.getColumnName(0)).setHeaderValue("Código Barras");
        tablaProd.getColumn(tablaProd.getColumnName(1)).setHeaderValue("Nombre");
        tablaProd.getColumn(tablaProd.getColumnName(2)).setHeaderValue("Marca");
        tablaProd.getColumn(tablaProd.getColumnName(3)).setHeaderValue("Tipo");
        tablaProd.getColumn(tablaProd.getColumnName(4)).setHeaderValue("Precio de Venta");
        tablaProd.getColumn(tablaProd.getColumnName(5)).setHeaderValue("Precio de Compra");
        tablaProd.getColumn(tablaProd.getColumnName(6)).setHeaderValue("# Ventas");
        tablaProd.setRowHeight(30);
    }

    public void actualizarDatos() {
        Productos prods = new Productos();
        modeloTabla = prods.obtenerTablaProductos();
        tablaProd.setModel(modeloTabla);
        txtBuscar.setText("");
        txtBuscar.requestFocus();
        titulosTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        chCodigo = new javax.swing.JRadioButton();
        btnActualizar = new javax.swing.JButton();
        chNombre = new javax.swing.JRadioButton();
        cmbPedido1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProd = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnMostrarTodo = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnAgregaProd = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton3.setText(">Excel");

        chCodigo.setText("Código");
        chCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chCodigoActionPerformed(evt);
            }
        });

        btnActualizar.setText("Modificar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        chNombre.setText("Nombre");
        chNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chNombreActionPerformed(evt);
            }
        });

        cmbPedido1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPedido1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPedido1ActionPerformed(evt);
            }
        });

        tablaProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Marca", "Tipo", "Precio de venta", "Precio Compra", "# ventas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProd);

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

        jButton1.setText("Productos por agotarse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnMostrarTodo.setText("Mostrar todo");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });

        jButton4.setText("PDF");

        btnAgregaProd.setText("Agregar");
        btnAgregaProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregaProdActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo:");

        jButton2.setText("*Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chCodigo)
                        .addGap(18, 18, 18)
                        .addComponent(chNombre)
                        .addGap(66, 66, 66)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbPedido1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMostrarTodo)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(36, 36, 36)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregaProd, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chNombre)
                                    .addComponent(chCodigo)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap(93, Short.MAX_VALUE)
                                    .addComponent(btnBuscar))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(36, 36, 36)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(cmbPedido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(41, 41, 41))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 22, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnMostrarTodo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregaProd)
                    .addComponent(jButton2)
                    .addComponent(btnActualizar)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addGap(52, 52, 52))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chCodigoActionPerformed
        chNombre.setSelected(false);
        txtBuscar.requestFocus();
    }//GEN-LAST:event_chCodigoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (this.tablaProd.getSelectedRow() != -1) {
            String codigo = tablaProd.getValueAt(tablaProd.getSelectedRow(), 0).toString();
            DialogoModificarProd d = new DialogoModificarProd(null, true, this, codigo);
            d.pack();
            d.setVisible(true);
            d.setResizable(true);
            d.setLocationRelativeTo(null);

            /*
             IntModProd v = new IntModProd(this,codigo);
             v.setVisible(true);
             this.setEnabled(false);*/
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void chNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chNombreActionPerformed
        chCodigo.setSelected(false);
        txtBuscar.requestFocus();
    }//GEN-LAST:event_chNombreActionPerformed

    private void cmbPedido1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPedido1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPedido1ActionPerformed

    private void tablaProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProdMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaProdMouseClicked

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (chCodigo.isSelected()) {
                if (Pattern.matches("^([A-Z,Ñ,ñ,a-z,0-9,-]{1,50})$", txtBuscar.getText())) {
                    Productos a = new Productos();
                    a.buscarPorCodigoB(txtBuscar.getText());
                    this.modeloTabla = a.creaModelo(a.getProductos().size());
                    tablaProd.setModel(modeloTabla);
                    titulosTabla();
                } else {
                    JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
                }

            } else if (chNombre.isSelected()) {
                if (Pattern.matches("^(([A-Z,Ñ,ñ,a-z,-]{1,30})([ ]{0,1})*){1,6}$", txtBuscar.getText())) {
                    Productos a = new Productos();
                    a.buscarPorNombre(txtBuscar.getText());
                    this.modeloTabla = a.creaModelo(a.getProductos().size());
                    tablaProd.setModel(modeloTabla);
                    titulosTabla();
                } else {
                    JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
                }
            }
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        Productos prods = new Productos();
        modeloTabla = prods.obtenerTablaProductos();
        tablaProd.setModel(modeloTabla);
        txtBuscar.setText("");
        txtBuscar.requestFocus();
        titulosTabla();
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    private void btnAgregaProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregaProdActionPerformed
        DialogoAgregarProd d = new DialogoAgregarProd(null, true);
        d.pack();
        d.setVisible(true);
        d.setResizable(true);
        d.setLocationRelativeTo(null);
        /*IntAgregarProd ag = new IntAgregarProd();
         ag.setVisible(true);*/
    }//GEN-LAST:event_btnAgregaProdActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (chCodigo.isSelected()) {
            if (Pattern.matches("^([A-Z,Ñ,ñ,a-z,0-9,-]{1,50})$", txtBuscar.getText())) {
                Productos a = new Productos();
                a.buscarPorCodigoB(txtBuscar.getText());
                this.modeloTabla = a.creaModelo(a.getProductos().size());
                tablaProd.setModel(modeloTabla);
                titulosTabla();
            } else {
                JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
            }

        } else if (chNombre.isSelected()) {
            if (Pattern.matches("^(([A-Z,Ñ,ñ,a-z,-]{1,30})([ ]{0,1})*){1,6}$", txtBuscar.getText())) {
                Productos a = new Productos();
                a.buscarPorNombre(txtBuscar.getText());
                this.modeloTabla = a.creaModelo(a.getProductos().size());
                tablaProd.setModel(modeloTabla);
                titulosTabla();
            } else {
                JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregaProd;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JRadioButton chCodigo;
    private javax.swing.JRadioButton chNombre;
    private javax.swing.JComboBox cmbPedido1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProd;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
