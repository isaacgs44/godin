/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author lenovo
 */
public class IntCarga extends javax.swing.JFrame {
   //IntCuentas v;
    /**
     * Creates new form IntCarga
     */
    public IntCarga() {
      /*  initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/img/29.png")).getImage());
        ((JPanel)getContentPane()).setOpaque(fal­se); 
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        barraP.setIndeterminate(true);
        barraP.setString("Cargando información");
        monitor();
        
    }
         
    
    private void monitor(){
        final Thread t;
        t = new Thread(new Runnable() {
        Boolean bandera = true;
        @Override
        public void run() {
      /*      v = new IntCuentas();
        //    v.setVisible(false);

            while(bandera){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                    if(v.getDone()){
                        bandera=false;
                        setCursor(null); //turn off the wait cursor
                        barraP.setIndeterminate(false);
        
                        //Permite mostrar el valor del progreso
                        barraP.setStringPainted(true);
                        int x = 1;
                        //Utilizamos un while para emular el valor mínimo y máximo
                        //En este caso 0 - 100
                        while(x <= 100){
                            barraP.setValue(x);
                            txtCarga.setText("Progreso " + x + "%...\n");
                            //Se incrementa el valor de x
                            x++;
                        }
                        dispose();
                        v.setVisible(true);
                        v.setEnabled(true);
                        v.toFront();
                        v.requestFocus();
                    }
                }
            }
            
        });
        t.start();*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barraP = new javax.swing.JProgressBar();
        txtCarga = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mando financiero");
        setBackground(new java.awt.Color(1, 1, 1));

        txtCarga.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtCarga.setText("Cargando . . .");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraP, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCarga))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(txtCarga)
                .addGap(18, 18, 18)
                .addComponent(barraP, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IntCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IntCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IntCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IntCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IntCarga().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barraP;
    private javax.swing.JLabel txtCarga;
    // End of variables declaration//GEN-END:variables

 /*   public void terminar(IntCuentas c) {
        setCursor(null); //turn off the wait cursor
        barraP.setIndeterminate(false);
        
          final Thread t;
        //Inicializamos
        t = new Thread(new Runnable() {
            //Implementamos el método run()
            @Override
            public void run() {
                barraP.setStringPainted(true);
                int x = 1;
                //Utilizamos un while para emular el valor mínimo y máximo
                //En este caso 0 - 100
                while(x <= 100){
                    barraP.setValue(x);
                    txtCarga.setText("Progreso " + x + "%...\n");
                  /*  try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                    x++;
                }
            }
        });
        //Se ejecuta el Thread
        t.start();
        
    }*/
}