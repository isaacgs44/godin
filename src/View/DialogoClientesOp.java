/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Cliente;
import Controller.Utilidades;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author lenovo
 */
public class DialogoClientesOp extends javax.swing.JDialog {

    private TableModel modeloTabla;
    private Utilidades uts;
    private Frame parent;

    /**
     * Creates new form DialogoClientesOp
     */
    public DialogoClientesOp(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.parent=parent;
        Cliente c = new Cliente();
        c.obtenerClientes();
        this.modeloTabla = c.creaModeloClientes();
        tablaClientes.setModel(modeloTabla);
        titulosTabla();
        txtBuscar.requestFocus();
    }

    private void titulosTabla() {
        tablaClientes.getColumn(tablaClientes.getColumnName(0)).setHeaderValue(" ");
        tablaClientes.getColumn(tablaClientes.getColumnName(1)).setHeaderValue("Nombre");
        tablaClientes.getColumn(tablaClientes.getColumnName(2)).setHeaderValue("Referencia");
        tablaClientes.getColumn(tablaClientes.getColumnName(3)).setHeaderValue("Dirección");
        tablaClientes.getColumn(tablaClientes.getColumnName(4)).setHeaderValue("Saldo");
        tablaClientes.getColumn(tablaClientes.getColumnName(5)).setHeaderValue("# Compras");

        tablaClientes.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaClientes.getColumnModel().getColumn(0).setMinWidth(0);
        tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(0);
        tablaClientes.setRowHeight(30);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnRegPago = new javax.swing.JButton();
        btnMostrarTodo = new javax.swing.JButton();
        btnRegVenta = new javax.swing.JButton();
        btnRegVenta1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        btnRegPago.setText("Pago");
        btnRegPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegPagoActionPerformed(evt);
            }
        });

        btnMostrarTodo.setText("Mostrar todo");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });

        btnRegVenta.setText("Venta");
        btnRegVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegVentaActionPerformed(evt);
            }
        });

        btnRegVenta1.setText("Devolución");
        btnRegVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegVenta1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Cliente:");

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Referencia", "Saldo", "Compras"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaClientes.setToolTipText("");
        jScrollPane1.setViewportView(tablaClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(30, 30, 30)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnMostrarTodo))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnRegPago, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRegVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRegPago, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscar)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnMostrarTodo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (Pattern.matches("^([A-Z,Ñ,ñ,a-z,0-9,-]{1,50})$", txtBuscar.getText())) {
            Cliente c = new Cliente();
            c.buscarCliente(txtBuscar.getText());
            this.modeloTabla = c.creaModeloClientes();
            tablaClientes.setModel(modeloTabla);
            titulosTabla();
        } else {
            JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Pattern.matches("^([A-Z,Ñ,ñ,a-z,0-9,-]{1,50})$", txtBuscar.getText())) {
                Cliente c = new Cliente();
                c.buscarCliente(txtBuscar.getText());
                this.modeloTabla = c.creaModeloClientes();
                tablaClientes.setModel(modeloTabla);
                titulosTabla();
            } else {
                JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
            }
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnRegPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegPagoActionPerformed
        if (tablaClientes.getSelectedRow() != -1) {
            Integer fila = tablaClientes.getSelectedRow();
            try {
                Double monto = Double.parseDouble(JOptionPane.showInputDialog(this, "Monto: ", "Pago", JOptionPane.INFORMATION_MESSAGE));
                Cliente c = new Cliente(tablaClientes.getValueAt(fila, 0).toString());
                if (c.actualizarSaldo(monto, c, 'p')) {
                    Cliente c2 = new Cliente();
                    c2.obtenerClientes();
                    this.modeloTabla = c2.creaModeloClientes();
                    tablaClientes.setModel(modeloTabla);
                    titulosTabla();
                    txtBuscar.setText("");
                    uts = new Utilidades();
                    uts.ventanaInfo(new JFrame(), "Pago registrado", "Aviso");
                } else {
                    JOptionPane.showOptionDialog(this, "Monto excede saldo", "No permitido", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
                }
            } catch (Exception ex) {
                System.out.println("operacion cancelada");
            }
        } else {
            JOptionPane.showOptionDialog(this, "Seleccione un cliente", "Notificación", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
        }

    }//GEN-LAST:event_btnRegPagoActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        Cliente c = new Cliente();
        c.obtenerClientes();
        this.modeloTabla = c.creaModeloClientes();
        tablaClientes.setModel(modeloTabla);
        titulosTabla();
        txtBuscar.setText("");
        txtBuscar.requestFocus();
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    private void btnRegVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegVentaActionPerformed
        if (tablaClientes.getSelectedRow() != -1) {
            Integer fila = tablaClientes.getSelectedRow();
            DialogoSelecVendedor d = new DialogoSelecVendedor(parent, true, tablaClientes.getValueAt(fila, 0).toString(), this);
            d.pack();
            d.setVisible(true);
            d.setResizable(true);
            d.setLocationRelativeTo(null);
        } else {
            JOptionPane.showOptionDialog(this, "Seleccione un cliente", "Notificación", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
        }

    }//GEN-LAST:event_btnRegVentaActionPerformed

    private void btnRegVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegVenta1ActionPerformed
        if (tablaClientes.getSelectedRow() != -1) {
            Integer fila = tablaClientes.getSelectedRow();
            Cliente c = new Cliente(tablaClientes.getValueAt(fila, 0).toString());
            DialogoDevolucion d = new DialogoDevolucion(null, true, c);
            d.pack();
            d.setVisible(true);
            d.setResizable(true);
            d.setLocationRelativeTo(null);
        } else {
            JOptionPane.showOptionDialog(this, "Seleccione un cliente", "Notificación", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
        }

    }//GEN-LAST:event_btnRegVenta1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JButton btnRegPago;
    private javax.swing.JButton btnRegVenta;
    private javax.swing.JButton btnRegVenta1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
