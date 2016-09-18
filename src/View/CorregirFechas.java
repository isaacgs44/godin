/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Cuenta;
import Controller.Historico;
import Controller.LibroMayor;
import Model.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toshiba
 */
public class CorregirFechas extends javax.swing.JDialog {

    /**
     * Creates new form CorregirFechas
     */
    public CorregirFechas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jButton1)
                .addContainerGap(196, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(95, 95, 95))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ResultSet res = null;
        Query query = new Query();
        query.seleccion("*", "indicadores");
        res = query.getRes();
        try {
            while (res.next()) {
                String id = res.getString("id_ind");
                String fecha = res.getString("periodo_ind");
                System.out.println("Fecha Vieja: " + fecha);
                /*if (fecha.length() == 17) {
                    StringTokenizer st = new StringTokenizer(fecha, "/");
                    String fechaI = null;
                    String fechaF = null;
                    while (st.hasMoreTokens()) {
                        fechaI = st.nextToken();
                        fechaF = st.nextToken();
                    }
                    if (fechaI != null) {
                        st = new StringTokenizer(fechaI, "-");
                        String dia = "";
                        String mes = "";
                        String anio = "";
                        while (st.hasMoreTokens()) {
                            dia = st.nextToken();
                            mes = st.nextToken();
                            anio = "20" + st.nextToken();
                        }
                        fechaI = dia + "-" + mes + "-" + anio;
                    }
                    if (fechaF != null) {
                        st = new StringTokenizer(fechaF, "-");
                        String dia = "";
                        String mes = "";
                        String anio = "";
                        while (st.hasMoreTokens()) {
                            dia = st.nextToken();
                            mes = st.nextToken();
                            anio = "20" + st.nextToken();
                        }
                        fechaF = dia + "-" + mes + "-" + anio;
                    }
                    String FechaNueva = fechaI + "/" + fechaF;
                    System.out.println("FechaNueva: " + FechaNueva);
                    query.ejecuta("UPDATE historico SET periodo_his = '" + FechaNueva + "' WHERE id=" + id + ";");
                }*/
                if (fecha.length() == 19) {
                    StringTokenizer st = new StringTokenizer(fecha, "/");
                    String fechaI = null;
                    String fechaF = null;
                    while (st.hasMoreTokens()) {
                        fechaI = st.nextToken();
                        fechaF = st.nextToken();
                    }
                    if (fechaI != null) {
                        st = new StringTokenizer(fechaI, "-");
                        String dia = "";
                        String mes = "";
                        String anio = "";
                        while (st.hasMoreTokens()) {
                            dia = st.nextToken();
                            mes = st.nextToken();
                            anio = "2016";
                            break;
                        }
                        fechaI = dia + "-" + mes + "-" + anio;
                    }
                    String FechaNueva = fechaI + "/" + fechaF;
                    System.out.println("FechaNueva: " + FechaNueva);
                    query.ejecuta("UPDATE indicadores SET periodo_ind = '" + FechaNueva + "' WHERE id_ind=" + id + ";");
                }
/*
                if (fecha.length() == 21) {
                    StringTokenizer st = new StringTokenizer(fecha, "/");
                    String fechaI = null;
                    String fechaF = null;
                    while (st.hasMoreTokens()) {
                        fechaI = st.nextToken();
                        fechaF = st.nextToken();
                    }
                    if (fechaI != null) {
                        st = new StringTokenizer(fechaI, "-");
                        String dia = "";
                        String mes = "";
                        String anio = "";
                        while (st.hasMoreTokens()) {
                            dia = st.nextToken();
                            mes = st.nextToken();
                            anio = st.nextToken();
                            if (anio.equals("2020")) {
                                anio = "2016";
                                fechaI = dia + "-" + mes + "-" + anio;
                                String FechaNueva = fechaI + "/" + fechaF;
                                System.out.println("FechaNueva: " + FechaNueva);
                                query.ejecuta("UPDATE historico SET periodo_his = '" + FechaNueva + "' WHERE id=" + id + ";");
                            }
                        }

                    }

                }*/

            }
            System.out.println("Prueba Finalizada");
            query.Desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(LibroMayor.class.getName()).log(Level.SEVERE, null, ex);
            query.Desconectar();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(CorregirFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CorregirFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CorregirFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CorregirFechas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CorregirFechas dialog = new CorregirFechas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}