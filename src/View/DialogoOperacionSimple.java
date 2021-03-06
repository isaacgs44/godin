/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Secretaria;
import java.awt.Color;
import java.awt.Frame;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author lenovo
 */
public class DialogoOperacionSimple extends javax.swing.JDialog {

    private Boolean datosValidos = false;
    private String idCta;
    private Frame parent;
    private JDialog d;

    /**
     * Creates new form DialogoOperacionSimple
     */
    public DialogoOperacionSimple(Frame parent, JDialog d, boolean modal, String idCta) {
        super(parent, modal);
        initComponents();
        this.idCta = idCta;
        this.parent = parent;
        this.d = d;
        obtenerSigno(this.idCta, "cargo");
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
        txtTipo.setText(s);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbTipo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtMonto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cargo", "Abono" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo:");

        txtTipo.setText("(+)");

        jLabel3.setText("Descripción:");

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

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

        jLabel1.setText("Monto:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDescripcion)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(txtTipo)
                .addGap(112, 112, 112))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        if (cmbTipo.getSelectedIndex() == 0) {
            obtenerSigno(this.idCta, "cargo");
        } else {
            obtenerSigno(this.idCta, "abono");
        }
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        if (Pattern.matches("^(([A-Z,Ñ,ñ,a-z]{1,15})([ ]{0,1})*){1,6}$", txtDescripcion.getText())) {
            txtDescripcion.setBackground(Color.GREEN);
            txtDescripcion.setForeground(Color.BLACK);
            this.datosValidos = true;
        } else {
            txtDescripcion.setForeground(Color.WHITE);
            txtDescripcion.setBackground(Color.RED);
            this.datosValidos = false;
        }
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (datosValidos) {
            if (txtMonto.getText().length() != 0 && txtDescripcion.getText().length() != 0) {
                Secretaria mary;
                switch (cmbTipo.getSelectedItem().toString()) {
                    case "Cargo":
                        mary = new Secretaria();
                        if (mary.registrarOperacionSimple(this.idCta, Double.parseDouble(txtMonto.getText()), txtDescripcion.getText(), "c")) {
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
                        break;
                    case "Abono":
                        mary = new Secretaria();
                        if (mary.registrarOperacionSimple(this.idCta, Double.parseDouble(txtMonto.getText()), txtDescripcion.getText(), "a")) {
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
                        break;
                }
            } else {
                JOptionPane.showOptionDialog(this, "Campos vacíos", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
            }
        } else {
            JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed

    private void txtMontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyReleased
        if (Pattern.matches("^([0-9]{1,10})(([.]{1})([0-9]{1,2}))*$", txtMonto.getText())) {
            txtMonto.setBackground(Color.GREEN);
            txtMonto.setForeground(Color.BLACK);
            this.datosValidos = true;
        } else {
            txtMonto.setForeground(Color.WHITE);
            txtMonto.setBackground(Color.RED);
            this.datosValidos = false;
        }
    }//GEN-LAST:event_txtMontoKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JLabel txtTipo;
    // End of variables declaration//GEN-END:variables
}
