/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Cuenta;
import Controller.LibroMayor;
import Controller.Secretaria;
import Controller.Utilidades;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author lenovo
 */
public class DialogoPartidaDoble extends javax.swing.JDialog {

    private TableModel modelo;
    private LibroMayor m;
    private Cuenta cta1;
    private Cuenta cta2;
    private String idCuenta;
    private Boolean datosValidos = false;
    private JDialog d;
    private Frame parent;

    /**
     * Creates new form DialogoOperacionSimple
     */
    public DialogoPartidaDoble(java.awt.Frame parent, boolean modal, String idCuenta, String tipo, JDialog d) {
        super(parent, modal);
        initComponents();
        this.d = d;
        this.parent = parent;
        this.idCuenta = idCuenta;
        m = new LibroMayor();
        cta1 = m.obtenerCuenta(idCuenta, tipo);
        lblNomCta.setText(cta1.getNombre());
        modelo = m.creaModeloCtas(m.obtenerCuentasComp(idCuenta, "abono"));
        tablaCta.setModel(modelo);
        personalizaTabla();
        lblTipo.setText(cta1.obtenerTipo(idCuenta));
        obtenerSigno(idCuenta, "abono");
    }

    private void obtenerSigno(String idC, String op) {
        String opc = idC.substring(0, 2);
        String s = "";
        switch (opc) {
            case "AC":
            case "AF":
            case "AD":
                if (op.equals("abono")) {
                    s = "( - )";
                } else if (op.equals("cargo")) {
                    s = "( + )";
                }
                break;
            case "PC":
            case "PF":
            case "PD":
                if (op.equals("abono")) {
                    s = "( + )";
                } else if (op.equals("cargo")) {
                    s = "( - )";
                }
                break;
            case "CO":
            case "GA":
                if (op.equals("cargo")) {
                    s = "( + )";
                }
                break;
            case "IN":
                if (op.equals("abono")) {
                    s = "( + )";
                }
                break;
            case "CC":
                if (op.equals("abono")) {
                    s = "( + )";
                } else if (op.equals("cargo")) {
                    s = "( - )";
                }
                break;
            default:
                s = " ";
        }
        lblSigno.setText(s);
    }

    private void personalizaTabla() {
        // modificar ancho de columnas de acuerdo al contenido
        tablaCta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < tablaCta.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) tablaCta.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            switch (i) {
                case 0:
                    col.setPreferredWidth(130);
                    break;
                case 1:
                    col.setPreferredWidth(480);
                    break;
                case 2:
                    col.setPreferredWidth(150);
                    break;
                case 3:
                    col.setPreferredWidth(150);

                    break;

            }
        }
        tablaCta.setRowHeight(30);
        tablaCta.getColumn(tablaCta.getColumnName(0)).setHeaderValue("ID");
        tablaCta.getColumn(tablaCta.getColumnName(1)).setHeaderValue("Nombre");
        tablaCta.getColumn(tablaCta.getColumnName(2)).setHeaderValue("Tipo");
        tablaCta.getColumn(tablaCta.getColumnName(3)).setHeaderValue("Saldo");

        tablaCta.setDefaultRenderer(Object.class, //cambiar colores de tabla
                new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
                if (String.valueOf(table.getValueAt(row, 0)).length() == 5) {
                    setBackground(Color.decode("#FFDB98"));
                } else {
                    setBackground(Color.decode("#ADD8E6"));
                }
                if (table.getValueAt(row, 3).toString().length() > 0) {
                    this.setHorizontalAlignment(SwingConstants.RIGHT);
                }
                super.getTableCellRendererComponent(table, value, selected, focused, row, column);
                return this;
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        lblNomCta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblSigno = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCta = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblAbono = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        txtDesc = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel2.setText("Cuenta: ");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Abono", "Cargo" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        lblNomCta.setText("jLabel3");

        jLabel1.setText("Operación: ");

        lblSigno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSigno.setText("( - )");

        tablaCta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Tipo", "Saldo"
            }
        ));
        jScrollPane1.setViewportView(tablaCta);

        jLabel4.setText("Tipo:");

        lblTipo.setText("jLabel5");

        lblAbono.setText("Con cargo a:");

        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });
        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMontoKeyReleased(evt);
            }
        });

        jLabel3.setText("Monto:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel5.setText("Descripción:");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescKeyReleased(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jButton1.setText("Mostrar todo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1083, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel4))
                                                .addGap(45, 45, 45)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblNomCta)
                                                    .addComponent(lblTipo))))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblAbono)
                                            .addGap(192, 192, 192)
                                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnBuscar))))
                                .addComponent(btnCancelar))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel1))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lblSigno))
                                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jButton1)
                                            .addGap(8, 8, 8))))
                                .addComponent(btnAceptar, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(12, 12, 12)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 689, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lblNomCta)
                        .addComponent(jLabel1)
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSigno))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblTipo))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGap(23, 23, 23)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(22, 22, 22)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblAbono)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnBuscar)
                                .addComponent(jButton1))
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAceptar)
                        .addComponent(btnCancelar))
                    .addGap(50, 50, 50)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        if (cmbTipo.getSelectedIndex() == 0) {
            lblAbono.setText("Con cargo a:");
            LibroMayor m2 = new LibroMayor();
            TableModel mod = m2.creaModeloCtas(m2.obtenerCuentasComp(this.cta1.getId(), "abono"));
            tablaCta.setModel(mod);
            personalizaTabla();
            obtenerSigno(cta1.getId(), "abono");
        } else {
            lblAbono.setText("Con abono a:");
            LibroMayor m2 = new LibroMayor();
            TableModel mod = m2.creaModeloCtas(m.obtenerCuentasComp(this.cta1.getId(), "cargo"));
            tablaCta.setModel(mod);
            personalizaTabla();
            obtenerSigno(cta1.getId(), "cargo");
        }
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    private void txtMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyReleased
        if (Pattern.matches("^([0-9,.]{1,8})$", txtMonto.getText())) {
            txtMonto.setBackground(Color.GREEN);
            txtMonto.setForeground(Color.BLACK);
            this.datosValidos = true;
        } else {
            txtMonto.setForeground(Color.WHITE);
            txtMonto.setBackground(Color.RED);
            this.datosValidos = false;
        }
    }//GEN-LAST:event_txtMontoKeyReleased

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if (Pattern.matches("^([A-Z,Ñ,ñ,a-z,0-9,-, ,]{1,50})$", txtBuscar.getText())) {
            m = new LibroMayor();
            modelo = m.creaModeloCtas(m.buscarCuentasYsub(txtBuscar.getText(), idCuenta));
            tablaCta.setModel(modelo);
            personalizaTabla();
        } else {
            JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (Pattern.matches("^([A-Z,Ñ,ñ,a-z,0-9,-, ,]{1,50})$", txtBuscar.getText())) {
            m = new LibroMayor();
            modelo = m.creaModeloCtas(m.buscarCuentasYsub(txtBuscar.getText(), idCuenta)); // partida doble -pd
            tablaCta.setModel(modelo);
            personalizaTabla();
        } else {
            JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtDescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescKeyReleased
        if (Pattern.matches("^(([A-Z,Ñ,ñ,a-z]{3,30})+([ ]{0,1})){1,6}$", txtDesc.getText())) {
            txtDesc.setBackground(Color.GREEN);
            txtDesc.setForeground(Color.BLACK);
            this.datosValidos = true;
        } else {
            txtDesc.setForeground(Color.WHITE);
            txtDesc.setBackground(Color.RED);
            this.datosValidos = false;
        }
    }//GEN-LAST:event_txtDescKeyReleased

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (datosValidos) {
            if (txtMonto.getText().length() != 0 && txtDesc.getText().length() != 0) {
                if (tablaCta.getSelectedRow() != -1) {
                    Secretaria mary = new Secretaria();
                    if (cmbTipo.getSelectedItem().toString().equals("Cargo")) {
                        if (mary.registrarOperacion(cta1.getId(), tablaCta.getValueAt(tablaCta.getSelectedRow(), 0).toString(), Double.parseDouble(txtMonto.getText()), txtDesc.getText())) {
                            JOptionPane.showOptionDialog(this, "Operación realizada", "Notificación", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "}, "OK");
                            d.dispose();
                            DialogoCarga v = new DialogoCarga(parent, true);
                            v.setVisible(true);
                            v.setResizable(false);
                            v.setAlwaysOnTop(true);
                            dispose();
                        } else {
                            JOptionPane.showOptionDialog(this, "Error al insertar operación", "Error", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
                        }
                    } else if (cmbTipo.getSelectedItem().toString().equals("Abono")) {
                        if (mary.registrarOperacion(tablaCta.getValueAt(tablaCta.getSelectedRow(), 0).toString(), cta1.getId(), Double.parseDouble(txtMonto.getText()), txtDesc.getText())) {
                            JOptionPane.showOptionDialog(this, "Operación realizada", "Notificación", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "}, "OK");
                            d.dispose();
                            DialogoCarga v = new DialogoCarga(parent, true);
                            v.setVisible(true);
                            v.setResizable(false);
                            v.setAlwaysOnTop(true);
                            dispose();
                        } else {
                            JOptionPane.showOptionDialog(this, "Error al insertar operación", "Error", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
                        }
                    }

                } else {
                    JOptionPane.showOptionDialog(this, "Seleccione una cuenta", "Notificación", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
                }
            } else {
                JOptionPane.showOptionDialog(this, "Campos vacíos", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
            }
        } else {
            JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        modelo = m.creaModeloCtas(m.obtenerCuentasComp(idCuenta, "abono"));
        tablaCta.setModel(modelo);
        personalizaTabla();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAbono;
    private javax.swing.JLabel lblNomCta;
    private javax.swing.JLabel lblSigno;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTable tablaCta;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDesc;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
