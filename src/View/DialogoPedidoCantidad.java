/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Producto;
import java.awt.Color;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author lenovo
 */
public class DialogoPedidoCantidad extends javax.swing.JDialog {
   private Producto prod;
    private DialogoRegistrarPedido vReg;
    private Boolean datosValidos = false;
    /**
     * Creates new form DialogoPedidoCantidad
     */
    public DialogoPedidoCantidad(java.awt.Frame parent, boolean modal,Producto p, DialogoRegistrarPedido vReg) {
        super(parent, modal);
        initComponents();this.prod = p;
        this.vReg = vReg;
        this.lblNombre.setText(p.getNombre());
        this.lblMarca.setText(p.getMarca());
        this.lblTipo.setText(p.getTipo());
        this.lblPrecioV.setText(p.getPrecioV().toString());        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtCant = new javax.swing.JTextField();
        txtPrecioC = new javax.swing.JTextField();
        lblProducto = new javax.swing.JLabel();
        lblProducto1 = new javax.swing.JLabel();
        lblProducto2 = new javax.swing.JLabel();
        lblProducto3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblPrecioV = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Precio compra:");

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

        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantKeyReleased(evt);
            }
        });

        txtPrecioC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioCKeyReleased(evt);
            }
        });

        lblProducto.setText("Nombre:");

        lblProducto1.setText("Marca:");

        lblProducto2.setText("Tipo:");

        lblProducto3.setText("Precio venta:");

        jLabel1.setText("Cantidad:");

        lblMarca.setText("Nombre:");

        lblNombre.setText("Nombre:");

        lblPrecioV.setText("Nombre:");

        lblTipo.setText("Nombre:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(lblProducto)
                            .addComponent(lblProducto1)
                            .addComponent(lblProducto2)
                            .addComponent(lblProducto3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCant)
                                    .addComponent(txtPrecioC, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTipo)
                                    .addComponent(lblPrecioV)
                                    .addComponent(lblMarca)
                                    .addComponent(lblNombre))))
                        .addGap(49, 49, 49)))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto)
                    .addComponent(lblNombre))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto1)
                    .addComponent(lblMarca))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto2)
                    .addComponent(lblTipo))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto3)
                    .addComponent(lblPrecioV))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPrecioC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if(datosValidos){
            if(txtCant.getText().length()!=0 && txtPrecioC.getText().length()!=0){
                this.prod = new Producto(this.prod, this.vReg.getPedido().getId(), Integer.parseInt(txtCant.getText()), Double.parseDouble(txtPrecioC.getText()));
                vReg.addProducto(this.prod);
                vReg.actualizarTabla();
                dispose();
                vReg.setTxtCodigoB("");
                vReg.setVisible(true);
            }else{
                JOptionPane.showOptionDialog(this, "Campos vacíos", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK");
            }
        }else{
            JOptionPane.showOptionDialog(this, "Datos no válidos", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK");
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
        vReg.setTxtCodigoB("");
        vReg.setVisible(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtCantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyReleased
        if(Pattern.matches("^([0-9]{1,2})$",txtCant.getText())){
            txtCant.setBackground(Color.GREEN);
            txtCant.setForeground(Color.BLACK);
            this.datosValidos = true;
        }
        else{
            txtCant.setForeground(Color.WHITE);
            txtCant.setBackground(Color.RED);
            this.datosValidos = false;
        }
    }//GEN-LAST:event_txtCantKeyReleased

    private void txtPrecioCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCKeyReleased
        if(Pattern.matches("^([0-9]{2,4})(([.])([0-9]{2}))*$",txtPrecioC.getText())){
            txtPrecioC.setBackground(Color.GREEN);
            txtPrecioC.setForeground(Color.BLACK);
            this.datosValidos = true;
        }
        else{
            txtPrecioC.setForeground(Color.WHITE);
            txtPrecioC.setBackground(Color.RED);
            this.datosValidos = false;
        }
    }//GEN-LAST:event_txtPrecioCKeyReleased

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecioV;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblProducto1;
    private javax.swing.JLabel lblProducto2;
    private javax.swing.JLabel lblProducto3;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtPrecioC;
    // End of variables declaration//GEN-END:variables
}