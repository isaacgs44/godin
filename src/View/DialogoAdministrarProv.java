/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Proveedor;
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
public class DialogoAdministrarProv extends javax.swing.JDialog {

    private TableModel modeloTabla;

    /**
     * Creates new form DialogoAdministrarProv
     * @param parent
     * @param modal
     */
    public DialogoAdministrarProv(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Proveedor p = new Proveedor();
        this.modeloTabla = p.obtenerProveedores();

        tablProv.setModel(modeloTabla);
        titulosTabla();

    }

    private void titulosTabla() {
        tablProv.getColumn(tablProv.getColumnName(0)).setHeaderValue("RFC");
        tablProv.getColumn(tablProv.getColumnName(1)).setHeaderValue("Razón Social");
        tablProv.getColumn(tablProv.getColumnName(2)).setHeaderValue("Dirección");
        tablProv.getColumn(tablProv.getColumnName(3)).setHeaderValue("Email");
        tablProv.getColumn(tablProv.getColumnName(4)).setHeaderValue("Teléfono");
        tablProv.setRowHeight(30);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablaProv = new javax.swing.JScrollPane();
        tablProv = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnMostrarTodo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablProv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RFC", "Razon Social", "Dirección", "Email", "Teléfono"
            }
        ));
        tablaProv.setViewportView(tablProv);

        jButton1.setText("Registrar nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar)
                            .addGap(383, 383, 383)
                            .addComponent(btnMostrarTodo))
                        .addComponent(tablaProv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscar)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnMostrarTodo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(tablaProv, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DialogoAgregarProv d = new DialogoAgregarProv(null, true);
        d.pack();
        d.setVisible(true);
        d.setResizable(true);
        d.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (Pattern.matches("^([A-Z,Ñ,ñ,a-z,0-9,-, ,]{1,50})$", txtBuscar.getText())) {
            Proveedor prov = new Proveedor();
            this.modeloTabla = prov.buscarProv(txtBuscar.getText());
            if (modeloTabla != null) {
                tablProv.setModel(modeloTabla);
                titulosTabla();
            }
        } else {
            JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Pattern.matches("^([A-Z,Ñ,ñ,a-z,0-9,-, ,]{1,50})$", txtBuscar.getText())) {
                Proveedor prov = new Proveedor();
                this.modeloTabla = prov.buscarProv(txtBuscar.getText());
                if (modeloTabla != null) {
                    tablProv.setModel(modeloTabla);
                    titulosTabla();
                }
            } else {
                JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
            }
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        Proveedor p = new Proveedor();
        this.modeloTabla = p.obtenerProveedores();
        tablProv.setModel(modeloTabla);
        titulosTabla();
        txtBuscar.setText("");
        txtBuscar.requestFocus();
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JButton jButton1;
    private javax.swing.JTable tablProv;
    private javax.swing.JScrollPane tablaProv;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
