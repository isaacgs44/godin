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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.plaf.basic.BasicMenuItemUI;
import javax.swing.plaf.basic.BasicMenuUI;
import javax.swing.plaf.basic.BasicPopupMenuUI;

/**
 *
 * @author lenovo
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    private Almacen almacen;
    private Pedido pedido;
    private Proveedor prov;
    
    private JLabel fondo;
    private ImageIcon imagen;

    /**
     * Creates new form IntCuentas
     */
    public VentanaPrincipal() {
        initComponents();
        // cambiando el estilo de nimbus...
        // colores: https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/_nimbusDefaults.html#primary
        try {
            // estilo según el sistema operativo: UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("nimbusBase", Color.gray); // scroll , marco ventana
            UIManager.put("nimbusBlueGrey", Color.lightGray); // botones, cabezera tabla, fondo scrolls
            UIManager.put("nimbusSelectionBackground", Color.darkGray); //seleccion en tablas o elementos de lista
            UIManager.put("nimbusSelectedText", Color.white); // color del texto de elementos de seleccion en tablas o elementos de lista
            UIManager.put("nimbusFocus", Color.gray); // color del texto de elementos de seleccion en tablas o elementos de lista
            UIManager.put("nimbusOrange", Color.BLACK); // barra de carga
            //UIManager.put("control", Color.darkGray); // fondo del panel

            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Falló la carga del tema");
            System.out.println(ex);
        }

        setIconImage(new ImageIcon(getClass().getResource("/img/29.png")).getImage());

        panelContenedor.setOpaque(false);//empieza codigo fondo 
        ((JPanel) getContentPane()).setOpaque(false);
        imagen = new ImageIcon(this.getClass().getResource("/img/godin.png"));
        fondo = new JLabel();
        fondo.setIcon(imagen);
        fondo.setHorizontalAlignment(SwingConstants.CENTER);
        fondo.setVerticalAlignment(SwingConstants.CENTER);
        getContentPane().add(fondo, JLayeredPane.CENTER_ALIGNMENT);
        fondo.setSize(imagen.getIconWidth(), imagen.getIconHeight());//termina codigo de fondo 

        personalizarMenu();
        this.setExtendedState(MAXIMIZED_BOTH); //maximizamos ventana
        
        pedido = new Pedido();
        almacen = new Almacen();
        prov = new Proveedor();
    }

    private void personalizarMenu() {
        //cambiar de color al menu
        jMenuBar1.setUI(new BasicMenuBarUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                g.setColor(Color.decode("#3c3b37")); //gris obscuro
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
                c.setSize(c.getWidth(), 40);
            }
        });

        mnuAlmacen.setUI(new BasicMenuUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#3c3b37")); //gris claro
                c.setSize(c.getWidth(), 40);
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#777570"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.                
            }
        });

        mnuAlmacen.getPopupMenu().setUI(new BasicPopupMenuUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                c.setBorder(new BorderUIResource.LineBorderUIResource(Color.black));
                super.paint(g, c); //To change body of generated methods, choose Tools | Templates.
            }

        });
        sMnuInventario.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuInventario.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuInventario.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuInventario.setForeground(Color.decode("#3c3b37"));
            }
        });
        smnuEstadisticas.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        smnuEstadisticas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                smnuEstadisticas.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                smnuEstadisticas.setForeground(Color.decode("#3c3b37"));
            }
        });
        smnuAdminP.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        smnuAdminP.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                smnuAdminP.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                smnuAdminP.setForeground(Color.decode("#3c3b37"));
            }
        });
        mnuAlmacen.setForeground(Color.white);

        mnuClientes.setUI(new BasicMenuUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#3c3b37")); //gris claro
                c.setSize(c.getWidth(), 40);
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#777570"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.                
            }
        });

        mnuClientes.getPopupMenu().setUI(new BasicPopupMenuUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                c.setBorder(new BorderUIResource.LineBorderUIResource(Color.black));
                super.paint(g, c); //To change body of generated methods, choose Tools | Templates.
            }

        });
        sMnuAgregarCl.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuAgregarCl.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuAgregarCl.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuAgregarCl.setForeground(Color.decode("#3c3b37"));
            }
        });
        sMnuHistoVenta.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuHistoVenta.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuHistoVenta.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuHistoVenta.setForeground(Color.decode("#3c3b37"));
            }
        });
        sMnuOperaciones.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuOperaciones.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuOperaciones.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuOperaciones.setForeground(Color.decode("#3c3b37"));
            }
        });

        mnuClientes.setForeground(Color.white);

        mnuContabilidad.setUI(new BasicMenuUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#3c3b37")); //gris claro
                c.setSize(c.getWidth(), 40);
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#777570"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.                
            }
        });

        mnuContabilidad.getPopupMenu().setUI(new BasicPopupMenuUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                c.setBorder(new BorderUIResource.LineBorderUIResource(Color.black));
                super.paint(g, c); //To change body of generated methods, choose Tools | Templates.
            }

        });
        sMnuAgregarCta.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuAgregarCta.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuAgregarCta.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuAgregarCta.setForeground(Color.decode("#3c3b37"));
            }
        });
        sMnuEdoRes.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuEdoRes.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuEdoRes.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuEdoRes.setForeground(Color.decode("#3c3b37"));
            }
        });
        sMnuFinanzas.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuFinanzas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuFinanzas.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuFinanzas.setForeground(Color.decode("#3c3b37"));
            }
        });
        mnuContabilidad.setForeground(Color.white);

        mnuProveedores.setUI(new BasicMenuUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#3c3b37")); //gris claro
                c.setSize(c.getWidth(), 40);
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#777570"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.                
            }
        });

        mnuProveedores.getPopupMenu().setUI(new BasicPopupMenuUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                c.setBorder(new BorderUIResource.LineBorderUIResource(Color.black));
                super.paint(g, c); //To change body of generated methods, choose Tools | Templates.
            }

        });
        sMnuAgregarProv.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuAgregarProv.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuAgregarProv.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuAgregarProv.setForeground(Color.decode("#3c3b37"));
            }
        });
        sMnuRegPed.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuRegPed.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuRegPed.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuRegPed.setForeground(Color.decode("#3c3b37"));
            }
        });
        sMnuAdminProv.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuAdminProv.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuAdminProv.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuAdminProv.setForeground(Color.decode("#3c3b37"));
            }
        });

        mnuProveedores.setForeground(Color.white);

        mnuEmpleados.setUI(new BasicMenuUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#3c3b37")); //gris claro
                c.setSize(c.getWidth(), 40);
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#777570"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.                
            }
        });

        mnuEmpleados.getPopupMenu().setUI(new BasicPopupMenuUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                c.setBorder(new BorderUIResource.LineBorderUIResource(Color.black));
                super.paint(g, c); //To change body of generated methods, choose Tools | Templates.
            }

        });
        sMnuAgEmp.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuAgEmp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuAgEmp.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuAgEmp.setForeground(Color.decode("#3c3b37"));
            }
        });

        sMnuAdminEmp.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuAdminEmp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuAdminEmp.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuAdminEmp.setForeground(Color.decode("#3c3b37"));
            }
        });
        mnuEmpleados.setForeground(Color.white);

        mnuOpciones.setUI(new BasicMenuUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#3c3b37")); //gris claro
                c.setSize(c.getWidth(), 40);
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#777570"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.                
            }
        });

        mnuOpciones.getPopupMenu().setUI(new BasicPopupMenuUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                c.setBorder(new BorderUIResource.LineBorderUIResource(Color.black));
                super.paint(g, c); //To change body of generated methods, choose Tools | Templates.
            }

        });
        mnuOpciones.setForeground(Color.white);

        mnuPeriodo.setUI(new BasicMenuUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#3c3b37")); //gris claro
                c.setSize(c.getWidth(), 40);
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#777570"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.                
            }
        });

        mnuPeriodo.getPopupMenu().setUI(new BasicPopupMenuUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                c.setBorder(new BorderUIResource.LineBorderUIResource(Color.black));
                super.paint(g, c); //To change body of generated methods, choose Tools | Templates.
            }

        });
        smnuGraficas.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        smnuGraficas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                smnuGraficas.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                smnuGraficas.setForeground(Color.decode("#3c3b37"));
            }
        });
        smnuHistorico.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        smnuHistorico.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                smnuHistorico.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                smnuHistorico.setForeground(Color.decode("#3c3b37"));
            }
        });
        sMnuCerrPeriodo.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#cccbca")); //gris claro
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#3c3b37"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.
            }
        });
        sMnuCerrPeriodo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                sMnuCerrPeriodo.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("exited");
                sMnuCerrPeriodo.setForeground(Color.decode("#3c3b37"));
            }
        });

        mnuPeriodo.setForeground(Color.white);

        mnuSalir.setUI(new BasicMenuUI() {
            @Override
            protected void paintMenuItem(Graphics g, JComponent c, Icon checkIcon, Icon arrowIcon, Color background, Color foreground, int defaultTextIconGap) {
                c.setBackground(Color.decode("#3c3b37")); //gris claro
                c.setSize(c.getWidth(), 40);
                super.paintMenuItem(g, c, checkIcon, arrowIcon, Color.decode("#777570"), Color.white, defaultTextIconGap); //To change body of generated methods, choose Tools | Templates.                
            }
        });

        mnuSalir.setForeground(Color.white);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel18 = new javax.swing.JLabel();
        panelContenedor = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuAlmacen = new javax.swing.JMenu();
        sMnuInventario = new javax.swing.JMenuItem();
        smnuEstadisticas = new javax.swing.JMenuItem();
        smnuAdminP = new javax.swing.JMenuItem();
        mnuClientes = new javax.swing.JMenu();
        sMnuAgregarCl = new javax.swing.JMenuItem();
        sMnuHistoVenta = new javax.swing.JMenuItem();
        sMnuOperaciones = new javax.swing.JMenuItem();
        mnuProveedores = new javax.swing.JMenu();
        sMnuRegPed = new javax.swing.JMenuItem();
        sMnuAgregarProv = new javax.swing.JMenuItem();
        sMnuAdminProv = new javax.swing.JMenuItem();
        mnuEmpleados = new javax.swing.JMenu();
        sMnuAgEmp = new javax.swing.JMenuItem();
        sMnuAdminEmp = new javax.swing.JMenuItem();
        mnuContabilidad = new javax.swing.JMenu();
        sMnuAgregarCta = new javax.swing.JMenuItem();
        sMnuEdoRes = new javax.swing.JMenuItem();
        sMnuFinanzas = new javax.swing.JMenuItem();
        mnuPeriodo = new javax.swing.JMenu();
        smnuGraficas = new javax.swing.JMenuItem();
        smnuHistorico = new javax.swing.JMenuItem();
        sMnuCerrPeriodo = new javax.swing.JMenuItem();
        mnuOpciones = new javax.swing.JMenu();
        mnuSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mando Financiero");
        setAutoRequestFocus(false);
        setMinimumSize(new java.awt.Dimension(1000, 600));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        panelContenedor.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        panelContenedor.setName(""); // NOI18N
        panelContenedor.setPreferredSize(new java.awt.Dimension(1000, 600));

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );

        jMenuBar1.setBorderPainted(false);
        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jMenuBar1.setMargin(new java.awt.Insets(10, 10, 10, 5));
        jMenuBar1.setBackground(Color.BLACK);

        mnuAlmacen.setBackground(Color.GRAY);
        mnuAlmacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/almacen1.png"))); // NOI18N
        mnuAlmacen.setText("Almacen");
        mnuAlmacen.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        mnuAlmacen.setMargin(new java.awt.Insets(0, 0, 0, 10));
        mnuAlmacen.setPreferredSize(new java.awt.Dimension(127, 40));

        sMnuInventario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        sMnuInventario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuInventario.setText("Inventario");
        sMnuInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuInventarioActionPerformed(evt);
            }
        });
        mnuAlmacen.add(sMnuInventario);

        smnuEstadisticas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        smnuEstadisticas.setText("Estadísticas");
        smnuEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smnuEstadisticasActionPerformed(evt);
            }
        });
        mnuAlmacen.add(smnuEstadisticas);

        smnuAdminP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        smnuAdminP.setText("Administrar productos");
        smnuAdminP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smnuAdminPActionPerformed(evt);
            }
        });
        mnuAlmacen.add(smnuAdminP);

        jMenuBar1.add(mnuAlmacen);

        mnuClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clientes.png"))); // NOI18N
        mnuClientes.setText("Clientes");
        mnuClientes.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        mnuClientes.setMargin(new java.awt.Insets(0, 0, 0, 10));
        mnuClientes.setPreferredSize(new java.awt.Dimension(117, 40));

        sMnuAgregarCl.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        sMnuAgregarCl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuAgregarCl.setText("Agregar Cliente");
        sMnuAgregarCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuAgregarClActionPerformed(evt);
            }
        });
        mnuClientes.add(sMnuAgregarCl);

        sMnuHistoVenta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        sMnuHistoVenta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuHistoVenta.setText("Historial de ventas");
        sMnuHistoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuHistoVentaActionPerformed(evt);
            }
        });
        mnuClientes.add(sMnuHistoVenta);

        sMnuOperaciones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        sMnuOperaciones.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuOperaciones.setText("Operaciones");
        sMnuOperaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuOperacionesActionPerformed(evt);
            }
        });
        mnuClientes.add(sMnuOperaciones);

        jMenuBar1.add(mnuClientes);

        mnuProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/proveedores.png"))); // NOI18N
        mnuProveedores.setText("Proveedores");
        mnuProveedores.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        mnuProveedores.setMargin(new java.awt.Insets(0, 0, 0, 10));
        mnuProveedores.setPreferredSize(new java.awt.Dimension(155, 40));

        sMnuRegPed.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        sMnuRegPed.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuRegPed.setText("Registrar Pedido");
        sMnuRegPed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuRegPedActionPerformed(evt);
            }
        });
        mnuProveedores.add(sMnuRegPed);

        sMnuAgregarProv.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuAgregarProv.setText("Agregar Proveedor");
        sMnuAgregarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuAgregarProvActionPerformed(evt);
            }
        });
        mnuProveedores.add(sMnuAgregarProv);

        sMnuAdminProv.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        sMnuAdminProv.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuAdminProv.setText("Administrar Proveedores");
        sMnuAdminProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuAdminProvActionPerformed(evt);
            }
        });
        mnuProveedores.add(sMnuAdminProv);

        jMenuBar1.add(mnuProveedores);

        mnuEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/vendedores.png"))); // NOI18N
        mnuEmpleados.setText("Empleados");
        mnuEmpleados.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        mnuEmpleados.setMargin(new java.awt.Insets(0, 0, 0, 10));
        mnuEmpleados.setPreferredSize(new java.awt.Dimension(137, 40));

        sMnuAgEmp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuAgEmp.setText("Agregar empleado");
        sMnuAgEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuAgEmpActionPerformed(evt);
            }
        });
        mnuEmpleados.add(sMnuAgEmp);

        sMnuAdminEmp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        sMnuAdminEmp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuAdminEmp.setText("Administrar empleados");
        sMnuAdminEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuAdminEmpActionPerformed(evt);
            }
        });
        mnuEmpleados.add(sMnuAdminEmp);

        jMenuBar1.add(mnuEmpleados);

        mnuContabilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/contabilidad.png"))); // NOI18N
        mnuContabilidad.setText("Contabilidad");
        mnuContabilidad.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        mnuContabilidad.setMargin(new java.awt.Insets(0, 0, 0, 10));
        mnuContabilidad.setPreferredSize(new java.awt.Dimension(149, 40));

        sMnuAgregarCta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuAgregarCta.setText("Agregar cuenta");
        sMnuAgregarCta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuAgregarCtaActionPerformed(evt);
            }
        });
        mnuContabilidad.add(sMnuAgregarCta);

        sMnuEdoRes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        sMnuEdoRes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuEdoRes.setText("Estado de Resultados");
        sMnuEdoRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuEdoResActionPerformed(evt);
            }
        });
        mnuContabilidad.add(sMnuEdoRes);

        sMnuFinanzas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        sMnuFinanzas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuFinanzas.setText("Mando financiero");
        sMnuFinanzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuFinanzasActionPerformed(evt);
            }
        });
        mnuContabilidad.add(sMnuFinanzas);

        jMenuBar1.add(mnuContabilidad);

        mnuPeriodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/estadisticas.png"))); // NOI18N
        mnuPeriodo.setText("Periodos");
        mnuPeriodo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        mnuPeriodo.setMargin(new java.awt.Insets(0, 0, 0, 10));
        mnuPeriodo.setPreferredSize(new java.awt.Dimension(123, 40));

        smnuGraficas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        smnuGraficas.setText("Estadísticas");
        smnuGraficas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smnuGraficasActionPerformed(evt);
            }
        });
        mnuPeriodo.add(smnuGraficas);

        smnuHistorico.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        smnuHistorico.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        smnuHistorico.setText("Histórico");
        smnuHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smnuHistoricoActionPerformed(evt);
            }
        });
        mnuPeriodo.add(smnuHistorico);

        sMnuCerrPeriodo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuCerrPeriodo.setText("Cerrar periodo");
        sMnuCerrPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuCerrPeriodoActionPerformed(evt);
            }
        });
        mnuPeriodo.add(sMnuCerrPeriodo);

        jMenuBar1.add(mnuPeriodo);

        mnuOpciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ajustes.png"))); // NOI18N
        mnuOpciones.setText("Ajustes");
        mnuOpciones.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        mnuOpciones.setMargin(new java.awt.Insets(0, 0, 0, 10));
        mnuOpciones.setPreferredSize(new java.awt.Dimension(125, 40));
        jMenuBar1.add(mnuOpciones);

        mnuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/encender-esquema-semicirculo_318-34691.png"))); // NOI18N
        mnuSalir.setText("Salir");
        mnuSalir.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        mnuSalir.setMargin(new java.awt.Insets(0, 0, 0, 10));
        mnuSalir.setPreferredSize(new java.awt.Dimension(81, 40));
        mnuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuSalirMouseClicked(evt);
            }
        });
        jMenuBar1.add(mnuSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(550, 550, 550)
                .addComponent(jLabel18)
                .addGap(0, 450, Short.MAX_VALUE))
            .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void sMnuCerrPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuCerrPeriodoActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(
                this, "¿Lo confirmas?");
        if (JOptionPane.OK_OPTION == confirmado) {
            Utilidades uts = new Utilidades();
            String fecha = uts.obtFechaStringDMA();
            Secretaria mary = new Secretaria();

            if (mary.cierraPeriodo(fecha)) {
                JOptionPane.showOptionDialog(this, "Periodo cerrado exitosamente", "Notificación", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "}, "OK");
                VentanaPrincipal c = new VentanaPrincipal();
                c.setVisible(true);
                dispose();
            } else {
                JOptionPane.showOptionDialog(this, "Error al cerrar periodo", "Notificación", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "}, "OK");
            }
        }
    }//GEN-LAST:event_sMnuCerrPeriodoActionPerformed

    private void sMnuFinanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuFinanzasActionPerformed
        DialogoMandoFinanciero dialog = new DialogoMandoFinanciero(this, false);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
/*
        IntCarga c = new IntCarga();
        c.setVisible(true);
        dispose();*/
    }//GEN-LAST:event_sMnuFinanzasActionPerformed

    private void sMnuHistoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuHistoVentaActionPerformed
        DialogoHistorialVentas dialog = new DialogoHistorialVentas(this, false);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_sMnuHistoVentaActionPerformed

    private void sMnuAdminEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuAdminEmpActionPerformed
        DialogoAdminEmpleados dialog = new DialogoAdminEmpleados(this, false);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_sMnuAdminEmpActionPerformed

    private void sMnuAgEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuAgEmpActionPerformed
        DialogoAgregarEmp dialog = new DialogoAgregarEmp(this, true);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_sMnuAgEmpActionPerformed

    private void sMnuAdminProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuAdminProvActionPerformed
        DialogoAdministrarProv dialog = new DialogoAdministrarProv(this, false);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_sMnuAdminProvActionPerformed

    private void sMnuAgregarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuAgregarProvActionPerformed
        DialogoAgregarProv dialog = new DialogoAgregarProv(this, true);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);

    }//GEN-LAST:event_sMnuAgregarProvActionPerformed

    private void sMnuOperacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuOperacionesActionPerformed
        DialogoClientesOp dialog = new DialogoClientesOp(this, false);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_sMnuOperacionesActionPerformed

    private void sMnuAgregarClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuAgregarClActionPerformed
        DialogoAgregarCliente dialog = new DialogoAgregarCliente(this, true);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_sMnuAgregarClActionPerformed

    private void sMnuRegPedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuRegPedActionPerformed
        DialogoSeleccionarProv dialog = new DialogoSeleccionarProv(this, true);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_sMnuRegPedActionPerformed

    private void sMnuInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuInventarioActionPerformed
        DialogoInventario dialog = new DialogoInventario(false ,this);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);

        /*JDialog dialog = new JDialog(this);
         JScrollPane scrollPane = new JScrollPane(new IntInventario());
         dialog.add(scrollPane);
         dialog.pack();
         dialog.setVisible(true);
         dialog.setResizable(true);
         dialog.setLocationRelativeTo(null);
        
        
         /*DialogoPersonalizado dialogo = new DialogoPersonalizado("Inventario",false);
         JPanel panelInventario = new IntInventario();
       
         dialogo.setjScrollPane1(panelInventario);
         dialogo.setSize(800, 500);
         dialogo.setVisible(true);
         dialogo.setLocationRelativeTo(null);
         dialogo.setResizable(true);
         dialogo.setMinimumSize(new Dimension(250, 200)); 
         dialogo.setAlwaysOnTop(true);
        
         /*
         JInternalFrame internal = new JInternalFrame("Inventario");

         // Se construye el JInternalFrame
         //creo scrollPane con Panel adentro
         JScrollPane scrollPane = new JScrollPane(new IntInventario());
         internal.add(scrollPane); //añadimos panel a internalFrame
         // configuramos tamaño del internalFrame con pack()
         internal.pack();
         panelContenedor.add(internal);// metemos internal al panel contenedor
         internal.setVisible(true);
         internal.setResizable(true);
         internal.setClosable(true);
         revalidate(); // actualizamos componentes*/

    }//GEN-LAST:event_sMnuInventarioActionPerformed

    private void sMnuEdoResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuEdoResActionPerformed
        DialogoEstadoResultados dialog = new DialogoEstadoResultados(this, false);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_sMnuEdoResActionPerformed

    private void smnuAdminPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smnuAdminPActionPerformed
        DialogoAdminProducto dialog = new DialogoAdminProducto(this, false);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_smnuAdminPActionPerformed

    private void smnuHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smnuHistoricoActionPerformed
        DialogoHistoricoFinanciero dialog = new DialogoHistoricoFinanciero(this, false);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_smnuHistoricoActionPerformed

    private void sMnuAgregarCtaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuAgregarCtaActionPerformed
        DialogoAgregarCta dialog = new DialogoAgregarCta(this, true);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setResizable(true);
        dialog.setLocationRelativeTo(null);
    }//GEN-LAST:event_sMnuAgregarCtaActionPerformed

    private void smnuGraficasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smnuGraficasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_smnuGraficasActionPerformed

    private void smnuEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smnuEstadisticasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_smnuEstadisticasActionPerformed

    private void mnuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_mnuSalirMouseClicked

    //evento para centrar a godin cada vez que se cambia el tamaño de la ventana
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        if (evt.getComponent().equals(this)) {
            fondo.setHorizontalAlignment(SwingConstants.CENTER);
            fondo.setVerticalAlignment(SwingConstants.CENTER);
            getContentPane().add(fondo, JLayeredPane.CENTER_ALIGNMENT);
            fondo.setSize(panelContenedor.getWidth(), panelContenedor.getHeight());//termina codigo de fondo 
        }
    }//GEN-LAST:event_formComponentResized

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Proveedor getProveedor() {
        return prov;
    }

    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel18;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu mnuAlmacen;
    private javax.swing.JMenu mnuClientes;
    private javax.swing.JMenu mnuContabilidad;
    private javax.swing.JMenu mnuEmpleados;
    private javax.swing.JMenu mnuOpciones;
    private javax.swing.JMenu mnuPeriodo;
    private javax.swing.JMenu mnuProveedores;
    private javax.swing.JMenu mnuSalir;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JMenuItem sMnuAdminEmp;
    private javax.swing.JMenuItem sMnuAdminProv;
    private javax.swing.JMenuItem sMnuAgEmp;
    private javax.swing.JMenuItem sMnuAgregarCl;
    private javax.swing.JMenuItem sMnuAgregarCta;
    private javax.swing.JMenuItem sMnuAgregarProv;
    private javax.swing.JMenuItem sMnuCerrPeriodo;
    private javax.swing.JMenuItem sMnuEdoRes;
    private javax.swing.JMenuItem sMnuFinanzas;
    private javax.swing.JMenuItem sMnuHistoVenta;
    private javax.swing.JMenuItem sMnuInventario;
    private javax.swing.JMenuItem sMnuOperaciones;
    private javax.swing.JMenuItem sMnuRegPed;
    private javax.swing.JMenuItem smnuAdminP;
    private javax.swing.JMenuItem smnuEstadisticas;
    private javax.swing.JMenuItem smnuGraficas;
    private javax.swing.JMenuItem smnuHistorico;
    // End of variables declaration//GEN-END:variables

}
