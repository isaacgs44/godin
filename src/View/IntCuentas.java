/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.BalanceGeneral;
import Controller.Cuenta;
import Controller.Indicadores;
import Controller.LibroDiario;
import Controller.LibroMayor;
import Controller.Operacion;
import Controller.Secretaria;
import Controller.TablaOp;
import Controller.Utilidades;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
public class IntCuentas extends javax.swing.JFrame {
    private LibroMayor m;
    private TableModel modelo;
    private TableModel modBalGen;
    private TableModel modOp;
    private BalanceGeneral balGen;
    private LibroDiario d;
    private List  fechas;
    private TableModel modInd;
    private Boolean done;
    /**
     * Creates new form IntCuentas
     */
    public IntCuentas() {
        done = false;
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/img/29.png")).getImage());
        ((JPanel)getContentPane()).setOpaque(fal­se); 
        d = new LibroDiario();
        m = new LibroMayor();
        balGen = new BalanceGeneral();
        parte3();
        parte1();
        parte2();
        parte4();
    }
    
    private void parte1(){
        final Thread t;
        t = new Thread(new Runnable() {
        Boolean bandera = true;
        @Override
        public void run() {
            modelo = m.creaModeloCtas(m.obtenerCuentasYsub());
            tablaCta.setModel(modelo);
            personalizaTablaCts();
        }
            
        });
        t.start();
    }
    
    private void parte2(){
          final Thread t2;
        t2= new Thread(new Runnable() {
        Boolean bandera = true;
        @Override
        public void run() {
              modBalGen = balGen.creaModeloTab(balGen);
            tablaBG.setModel(modBalGen);
            personalizaTablaBG();
        }
        });
        t2.start();
    }
    
     private void parte3(){
          final Thread t3;
        t3= new Thread(new Runnable() {
        Boolean bandera = true;
        @Override
        public void run() {
          
        modOp = d.creaModeloOp();
        tablaOp.setModel(modOp);
        personalizaTablaOp();
        llenarCmbFech(obtenerAnios());
        seleccionarMes();
        }
        });
        t3.start();
    }
       private void seleccionarMes() {
                  Utilidades uts = new Utilidades();
                  switch(uts.obtMes()){
                      case 1:
                          cmbMes.setSelectedIndex(0);
                      break;
                      case 2:
                          cmbMes.setSelectedIndex(1);
                      break;
                      case 3:
                          cmbMes.setSelectedIndex(2);
                      break;
                      case 4:
                          cmbMes.setSelectedIndex(3);
                      break;
                      case 5:
                          cmbMes.setSelectedIndex(4);
                      break;
                      case 6:
                          cmbMes.setSelectedIndex(5);
                      break;
                      case 7:
                          cmbMes.setSelectedIndex(6);
                      break;
                      case 8:
                          cmbMes.setSelectedIndex(7);
                      break;
                      case 9:
                          cmbMes.setSelectedIndex(8);
                      break;
                      case 10:
                          cmbMes.setSelectedIndex(9);
                      break;
                      case 11:
                          cmbMes.setSelectedIndex(10);
                      break;
                      case 12:
                          cmbMes.setSelectedIndex(11);
                      break;
                  }
              }
      private void parte4(){
          final Thread t4;
        t4= new Thread(new Runnable() {
        Boolean bandera = true;
        @Override
        public void run() {
   
       
        Indicadores indicadores = new Indicadores();
        modInd = indicadores.creaModeloInd();
        tblInd.setModel(modInd);
        personalizaTablaInd();
        }
            
        });
        t4.start();
    }
    
    
     private void llenarCmbFech(List fecha){
       String[] cadena = new String[fecha.size()];
        int i=0;
        for(Object f : fecha){
           cadena[i]= f.toString();
            i++;
        }
        cmbAnio.setModel(new DefaultComboBoxModel((Object[]) cadena));
         done = true;
    }
      private Boolean comprobarAnios(Operacion op){
          if(this.fechas != null){
            for(Object o: this.fechas){
              if(o.toString().equals(op.getFecha().substring(0,4))){
                  return false;
              }
            } return true;
          }else{
              return true;
          }
      }
      public List obtenerAnios(){
          List <Operacion> ops;
          fechas = new ArrayList <>();
          LibroDiario d = new LibroDiario();
          ops = d.obtenerOperaciones();
          for(Operacion o: ops){
              if(comprobarAnios(o)){
                  fechas.add(o.getFecha().substring(0,4));
              }
          }
          return fechas;
      }
    
      private void personalizaTablaBG() {
         //cambiar colores de tabla
       tablaBG.setDefaultRenderer(Object.class, 
               new DefaultTableCellRenderer(){
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column){
                           if(row ==0){
                               if (row == 0 && column > 1){
                                   setBackground(Color.decode("#FFA400"));
                               }else{
                                   setBackground(Color.decode("#6E81FF"));
                               }
                               setForeground(Color.WHITE);
                               setSize(300, 20);
                           }else{
                               if(row%2 == 0){
                                   setBackground(Color.decode("#DFF7FF")); setForeground(Color.BLACK);
                               }else{
                                   setBackground(Color.decode("#CEE7EF")); setForeground(Color.BLACK);
                               }
                           }
                       super.getTableCellRendererComponent(table, value, selected, focused, row, column);
                       return this;
                    }
               });
       tablaBG.getTableHeader().setVisible(false); // ocultar titulos de columna Jtable
       tablaBG.setRowHeight(20);
    }
      
     private void personalizaTablaInd() {
         //cambiar colores de tabla
       tblInd.setDefaultRenderer(Object.class, 
               new DefaultTableCellRenderer(){
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column){
                           
                               if(row%2 == 0){
                                   setBackground(Color.decode("#DFF7FF")); setForeground(Color.BLACK);
                               }else{
                                   setBackground(Color.decode("#CEE7EF")); setForeground(Color.BLACK);
                               }
                           
                       super.getTableCellRendererComponent(table, value, selected, focused, row, column);
                       return this;
                    }
               });
       tblInd.getTableHeader().setVisible(false); // ocultar titulos de columna Jtable
       tblInd.setRowHeight(20);
      
    }
    
    private void personalizaTablaOp(){
         // modificar ancho de columnas de acuerdo al contenido
        tablaOp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         for (int i = 0; i < tablaOp.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) tablaOp.getColumnModel();
            TableColumn col = colModel.getColumn(i);           
            switch (i){
                case 0 :  col.setPreferredWidth(50); break;
                case 1 :  col.setPreferredWidth(80); break;
                case 2 :  col.setPreferredWidth(132); break;
                case 3 :  col.setPreferredWidth(150); break;
                case 4 :  col.setPreferredWidth(150); break;
                case 5 :  col.setPreferredWidth(115); break;
            }        
            
        }
         
        tablaOp.setRowHeight(15);
        tablaOp.getColumn(tablaOp.getColumnName(0)).setHeaderValue("ID");
        tablaOp.getColumn(tablaOp.getColumnName(1)).setHeaderValue("Fecha");
        tablaOp.getColumn(tablaOp.getColumnName(2)).setHeaderValue("Descripción");
        tablaOp.getColumn(tablaOp.getColumnName(3)).setHeaderValue("Cuenta Cargo");
        tablaOp.getColumn(tablaOp.getColumnName(4)).setHeaderValue("Cuenta Abono");
        tablaOp.getColumn(tablaOp.getColumnName(5)).setHeaderValue("Monto");
    }
    
    private void personalizaTablaCts(){
         // modificar ancho de columnas de acuerdo al contenido
        tablaCta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
         for (int i = 0; i < tablaCta.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) tablaCta.getColumnModel();
            TableColumn col = colModel.getColumn(i);           
            switch (i){
                case 0 :  col.setPreferredWidth(130); break;
                case 1 :  col.setPreferredWidth(267); break;
                case 2 :  col.setPreferredWidth(130); break;
                case 3 :  col.setPreferredWidth(143); break;
            }                       
        }
        tablaCta.setRowHeight(30);
        tablaCta.getColumn(tablaCta.getColumnName(0)).setHeaderValue("ID");
        tablaCta.getColumn(tablaCta.getColumnName(1)).setHeaderValue("Nombre");
        tablaCta.getColumn(tablaCta.getColumnName(2)).setHeaderValue("Tipo");
        tablaCta.getColumn(tablaCta.getColumnName(3)).setHeaderValue("Saldo");
        
        tablaCta.setDefaultRenderer(Object.class,   //cambiar colores de tabla
               new DefaultTableCellRenderer(){
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column){
                       if(String.valueOf(table.getValueAt(row,0)).length() == 5)  setBackground(Color.decode("#CAD1FF"));
                       else  setBackground(Color.decode("#DFF7FF"));
                       if(table.getValueAt(row, 3).toString().length() > 0){
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tablaOp = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCta = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cmbAnio = new javax.swing.JComboBox();
        cmbMes = new javax.swing.JComboBox();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        cmbTipo = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        btnOp = new javax.swing.JButton();
        btnAddCta = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnAddSubCta = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        btnborraCta = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtBuscar1 = new javax.swing.JTextField();
        btnBuscar1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnMostrarTodo = new javax.swing.JButton();
        mostrartodoOp = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInd = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaBG = new javax.swing.JTable();
        btnOperacion = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuAlmacen = new javax.swing.JMenu();
        sMnuGestProd = new javax.swing.JMenuItem();
        sMnuRegPed = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        mnuClientes = new javax.swing.JMenu();
        sMnuAgregarCl = new javax.swing.JMenuItem();
        sMnuRegVen = new javax.swing.JMenuItem();
        sMnuGestionarCl = new javax.swing.JMenuItem();
        mnuProveedores = new javax.swing.JMenu();
        sMnuAgregarProv = new javax.swing.JMenuItem();
        sMnuGestionarProv = new javax.swing.JMenuItem();
        mnuVendedores = new javax.swing.JMenu();
        sMnuAgVend = new javax.swing.JMenuItem();
        sMnuGestVend = new javax.swing.JMenuItem();
        mnuContabilidad = new javax.swing.JMenu();
        sMnuAgregarCta = new javax.swing.JMenuItem();
        smnuBalGen = new javax.swing.JMenuItem();
        sMnuEdoRes = new javax.swing.JMenuItem();
        sMnuFinanzas = new javax.swing.JMenuItem();
        mnuPeriodo = new javax.swing.JMenu();
        sMnuCerrPeriodo = new javax.swing.JMenuItem();
        mnuOpciones = new javax.swing.JMenu();
        sMnuSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mando Financiero");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));

        tablaOp.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        tablaOp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Concepto", "Cuenta Cargo", "Cuenta Abono", "Monto"
            }
        ));
        jScrollPane2.setViewportView(tablaOp);

        tablaCta.setBackground(new java.awt.Color(100, 255, 200));
        tablaCta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaCta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Tipo", "Saldo"
            }
        ));
        jScrollPane3.setViewportView(tablaCta);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("OPERACIONES");

        cmbAnio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2016" }));
        cmbAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAnioActionPerformed(evt);
            }
        });

        cmbMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre" }));
        cmbMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMesActionPerformed(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas", "Activo", "Pasivo", "Capital", "Egreso", "Ingreso" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        btnOp.setText("Partida doble");
        btnOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpActionPerformed(evt);
            }
        });

        btnAddCta.setText("+");
        btnAddCta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCtaActionPerformed(evt);
            }
        });

        jLabel4.setText("Cuenta");

        btnAddSubCta.setText("+");
        btnAddSubCta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSubCtaActionPerformed(evt);
            }
        });

        jLabel21.setText("SubCta");

        btnborraCta.setText("-");
        btnborraCta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnborraCtaActionPerformed(evt);
            }
        });

        jLabel22.setText("Borrar");

        txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscar1KeyReleased(evt);
            }
        });

        btnBuscar1.setText("Buscar");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("CUENTAS");

        btnMostrarTodo.setText("Mostrar todo");
        btnMostrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodoActionPerformed(evt);
            }
        });

        mostrartodoOp.setText("Mostrar todo");
        mostrartodoOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrartodoOpActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setText("INDICADORES");

        tblInd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblInd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane1.setViewportView(tblInd);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel23.setText("BALANCE GENERAL");

        tablaBG.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tablaBG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tablaBG);

        btnOperacion.setText("Operacion simple");
        btnOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOperacionActionPerformed(evt);
            }
        });

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jMenuBar1.setMargin(new java.awt.Insets(5, 0, 5, 20));

        mnuAlmacen.setText("Almacen");
        mnuAlmacen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mnuAlmacen.setMargin(new java.awt.Insets(0, 0, 0, 10));

        sMnuGestProd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        sMnuGestProd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuGestProd.setText("Existencias");
        sMnuGestProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuGestProdActionPerformed(evt);
            }
        });
        mnuAlmacen.add(sMnuGestProd);

        sMnuRegPed.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        sMnuRegPed.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuRegPed.setText("Registrar Pedido");
        sMnuRegPed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuRegPedActionPerformed(evt);
            }
        });
        mnuAlmacen.add(sMnuRegPed);

        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jMenuItem1.setText("Gestionar productos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnuAlmacen.add(jMenuItem1);

        jMenuBar1.add(mnuAlmacen);

        mnuClientes.setText("Clientes");
        mnuClientes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mnuClientes.setMargin(new java.awt.Insets(0, 0, 0, 10));

        sMnuAgregarCl.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        sMnuAgregarCl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuAgregarCl.setText("Agregar Cliente");
        sMnuAgregarCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuAgregarClActionPerformed(evt);
            }
        });
        mnuClientes.add(sMnuAgregarCl);

        sMnuRegVen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        sMnuRegVen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuRegVen.setText("Historial de ventas");
        sMnuRegVen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuRegVenActionPerformed(evt);
            }
        });
        mnuClientes.add(sMnuRegVen);

        sMnuGestionarCl.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        sMnuGestionarCl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuGestionarCl.setText("Operaciones");
        sMnuGestionarCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuGestionarClActionPerformed(evt);
            }
        });
        mnuClientes.add(sMnuGestionarCl);

        jMenuBar1.add(mnuClientes);

        mnuProveedores.setText("Proveedores");
        mnuProveedores.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mnuProveedores.setMargin(new java.awt.Insets(0, 0, 0, 10));

        sMnuAgregarProv.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuAgregarProv.setText("Agregar Proveedor");
        sMnuAgregarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuAgregarProvActionPerformed(evt);
            }
        });
        mnuProveedores.add(sMnuAgregarProv);

        sMnuGestionarProv.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        sMnuGestionarProv.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuGestionarProv.setText("Gestionar Proveedores");
        sMnuGestionarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuGestionarProvActionPerformed(evt);
            }
        });
        mnuProveedores.add(sMnuGestionarProv);

        jMenuBar1.add(mnuProveedores);

        mnuVendedores.setText("Vendedores");
        mnuVendedores.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mnuVendedores.setMargin(new java.awt.Insets(0, 0, 0, 10));

        sMnuAgVend.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuAgVend.setText("Agregar vendedor");
        sMnuAgVend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuAgVendActionPerformed(evt);
            }
        });
        mnuVendedores.add(sMnuAgVend);

        sMnuGestVend.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        sMnuGestVend.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuGestVend.setText("Gestionar vendedores");
        sMnuGestVend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuGestVendActionPerformed(evt);
            }
        });
        mnuVendedores.add(sMnuGestVend);

        jMenuBar1.add(mnuVendedores);

        mnuContabilidad.setText("Contabilidad");
        mnuContabilidad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mnuContabilidad.setMargin(new java.awt.Insets(0, 0, 0, 10));

        sMnuAgregarCta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuAgregarCta.setText("Agregar cuenta");
        sMnuAgregarCta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuAgregarCtaActionPerformed(evt);
            }
        });
        mnuContabilidad.add(sMnuAgregarCta);

        smnuBalGen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        smnuBalGen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        smnuBalGen.setText("Balance General");
        smnuBalGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smnuBalGenActionPerformed(evt);
            }
        });
        mnuContabilidad.add(smnuBalGen);

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

        mnuPeriodo.setText("Periodos");
        mnuPeriodo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mnuPeriodo.setMargin(new java.awt.Insets(0, 0, 0, 10));

        sMnuCerrPeriodo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuCerrPeriodo.setText("Cerrar periodo");
        sMnuCerrPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuCerrPeriodoActionPerformed(evt);
            }
        });
        mnuPeriodo.add(sMnuCerrPeriodo);

        jMenuBar1.add(mnuPeriodo);

        mnuOpciones.setText("Opciones");
        mnuOpciones.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mnuOpciones.setMargin(new java.awt.Insets(0, 0, 0, 10));

        sMnuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        sMnuSalir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sMnuSalir.setText("Salir");
        sMnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sMnuSalirActionPerformed(evt);
            }
        });
        mnuOpciones.add(sMnuSalir);

        jMenuBar1.add(mnuOpciones);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jLabel23)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(75, 75, 75)
                                .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar1)
                                .addGap(18, 18, 18)
                                .addComponent(btnMostrarTodo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddCta, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddSubCta, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnborraCta, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnOperacion)
                                .addGap(26, 26, 26)
                                .addComponent(btnOp))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel18))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel5)))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mostrartodoOp))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cmbAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscar1)
                                .addComponent(btnMostrarTodo))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddCta)
                            .addComponent(jLabel4)
                            .addComponent(btnAddSubCta)
                            .addComponent(jLabel21)
                            .addComponent(btnborraCta)
                            .addComponent(jLabel22)
                            .addComponent(btnOp)
                            .addComponent(btnOperacion)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23)
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mostrartodoOp)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar))
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddSubCtaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSubCtaActionPerformed
          if(tablaCta.getSelectedRow() != -1){
            Integer fila = tablaCta.getSelectedRow();
            if(tablaCta.getValueAt(fila,0).toString().length() < 6){
                if(tablaCta.getValueAt(fila, 3).toString().equals("0")){
                        Cuenta c = new Cuenta();
                        String nombre = JOptionPane.showInputDialog (this, "Nombre: ", "Agregar Sub Cuenta",JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("nombre: " + nombre);
                    if(nombre != null){
                        if(c.agregarSubCta(tablaCta.getValueAt(fila, 0).toString(), nombre)){
                            JOptionPane.showOptionDialog(this, "subcuenta agregada", "Notificación", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "},"OK");                     
                            m = new LibroMayor();
                            modelo = m.creaModeloCtas(m.obtenerCuentasYsub());
                            tablaCta.setModel(modelo);
                            personalizaTablaCts();
                            cmbTipo.setSelectedIndex(0);
                          }else{
                            JOptionPane.showOptionDialog(this, "Error al agregar subcuenta", "Error", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
                        }
                    }else{
                        System.out.println("Operacion cancelada");
                    }
                }else{
                    try{
                    if(tablaCta.getValueAt(fila+1, 0).toString().length() > 6){
                         Cuenta c = new Cuenta();
                        String nombre = JOptionPane.showInputDialog (this, "Nombre: ", "Agregar Sub Cuenta",JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Nombre-: " + nombre);
                        if(nombre != null){
                        if(c.agregarSubCta(tablaCta.getValueAt(fila, 0).toString(), nombre)){
                            JOptionPane.showOptionDialog(this, "subcuenta agregada", "Notificación", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "},"OK");                     
                            m = new LibroMayor();
                            modelo = m.creaModeloCtas(m.obtenerCuentasYsub());
                            tablaCta.setModel(modelo);
                            personalizaTablaCts();
                            cmbTipo.setSelectedIndex(0);
                        }else{
                            JOptionPane.showOptionDialog(this, "Error al agregar subcuenta", "Error", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
                        }
                        }else{
                            System.out.println("Operación cancelada");
                        }
                    }else{
                        JOptionPane.showOptionDialog(this, "La cuenta debe estar en ceros 1", "Notificación", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "},"OK");                     
                    }
                    }catch(Exception c){
                        JOptionPane.showOptionDialog(this, "La cuenta debe estar en ceros  2", "Notificación", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "},"OK");                     
                    }
                }
               
            }else{
                JOptionPane.showOptionDialog(this, "Seleccione una cuenta", "Notificación", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
            }
       }
       else{
           JOptionPane.showOptionDialog(this, "Seleccione una cuenta", "Notificación", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
       }
    }//GEN-LAST:event_btnAddSubCtaActionPerformed

    private void btnborraCtaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnborraCtaActionPerformed
        if(tablaCta.getSelectedRow() != -1){
            Integer fila = tablaCta.getSelectedRow();
            String idCta = "";
            if(Double.parseDouble(tablaCta.getValueAt(fila, 3).toString()) == 0.0){
                m = new LibroMayor();
                d = new LibroDiario();
                int confirmado = JOptionPane.showConfirmDialog(
                    this, 
                    "¿Lo confirmas?");
                if (JOptionPane.OK_OPTION == confirmado)
                    idCta = tablaCta.getValueAt(fila, 0).toString();
                    if(m.borrarCta(idCta)){
                        if (d.borrarOps(idCta)) //borra operaciones con esa cuenta eliminada
                            System.out.println("Operaciones eliminadas");
                        JOptionPane.showOptionDialog(this, "La cuenta se eliminó correctamente", "Notificación", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "},"OK");                     
                        m = new LibroMayor();
                        modelo = m.creaModeloCtas(m.obtenerCuentasYsub());
                        tablaCta.setModel(modelo);
                        personalizaTablaCts();
                        cmbTipo.setSelectedIndex(0);
                    }else{
                        JOptionPane.showOptionDialog(this, "Error al eliminar la cuenta", "Error", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
                    }
            }else{
                JOptionPane.showOptionDialog(this, "La cuenta debe estar en ceros", "Notificación", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "},"OK");                     
            }
        }else{
            JOptionPane.showOptionDialog(this, "Seleccione una cuenta", "Notificación", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
        }
             
    }//GEN-LAST:event_btnborraCtaActionPerformed

    private void btnOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpActionPerformed
        if(tablaCta.getSelectedRow() != -1){
            if(tablaCta.getValueAt(tablaCta.getSelectedRow(), 0).toString().length() < 6){ //es cuenta principal
                try{
                    if(tablaCta.getValueAt(tablaCta.getSelectedRow()+1, 0).toString().length() < 6){ 
                        IntOperacion o = new IntOperacion(tablaCta.getValueAt(tablaCta.getSelectedRow(), 0).toString(), "c");
                        o.setVisible(true);
                        dispose();         
                    }
                    else{
                    String nombre = tablaCta.getValueAt(tablaCta.getSelectedRow(), 1).toString();
                    JOptionPane.showOptionDialog(this, "Seleccione una subcuenta de: " + nombre, "Notificación", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "},"OK");                     
                }
                }catch(Exception c){
                    System.out.println("ultima cuenta");
                    IntOperacion o = new IntOperacion(tablaCta.getValueAt(tablaCta.getSelectedRow(), 0).toString(), "c");
                    o.setVisible(true);
                    dispose(); 
                }
                
            }else{ // es sub cuenta
                IntOperacion o = new IntOperacion(tablaCta.getValueAt(tablaCta.getSelectedRow(), 0).toString(), "s");
                o.setVisible(true);
                dispose();  
            }
        }else{
            JOptionPane.showOptionDialog(this, "Seleccione una cuenta", "Notificación", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
        }
    }//GEN-LAST:event_btnOpActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
         if(Pattern.matches("^(([A-Z,Ñ,ñ,a-z]{1,30})([ ]{0,1})*){1,6}$",txtBuscar1.getText())){
            m = new LibroMayor();
            modelo = m.creaModeloCtas(m.buscarCuentasYsub(txtBuscar1.getText()));
            tablaCta.setModel(modelo);
            personalizaTablaCts();
        }else{
            JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
       }
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodoActionPerformed
        m = new LibroMayor();
        modelo = m.creaModeloCtas(m.obtenerCuentasYsub());
        tablaCta.setModel(modelo);
        personalizaTablaCts();
        cmbTipo.setSelectedIndex(0);
        txtBuscar1.setText("");
    }//GEN-LAST:event_btnMostrarTodoActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
         m = new LibroMayor();
        switch(cmbTipo.getSelectedItem().toString()){
            case "Todas": 
                modelo = m.creaModeloCtas(m.obtenerCuentasYsub());
            break;
            case "Activo":
                modelo = m.creaModeloCtas(m.PorTipoCtsYsub("Activo"));
            break;
            case "Pasivo":
                modelo = m.creaModeloCtas(m.PorTipoCtsYsub("Pasivo"));
            break;
            case "Capital":
                modelo = m.creaModeloCtas(m.PorTipoCtsYsub("Capital"));
            break;
            case "Egreso":
                modelo = m.creaModeloCtas(m.PorTipoCtsYsub("Egreso"));
            break;
            case "Ingreso":
                modelo = m.creaModeloCtas(m.PorTipoCtsYsub("Ingreso"));
            break;
        }
        tablaCta.setModel(modelo);
        personalizaTablaCts();
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void btnAddCtaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCtaActionPerformed
        IntAgregarCta v = new IntAgregarCta();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnAddCtaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if(Pattern.matches("^(([A-Z,Ñ,ñ,a-z]{1,30})([ ]{0,1})*){1,6}$",txtBuscar.getText())){
            modOp = creaModeloOp(buscarOperacion(txtBuscar.getText()));
            tablaOp.setModel(modOp);
            personalizaTablaOp();
            this.txtBuscar.setText("");
        }else{
            JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
       }
    }//GEN-LAST:event_btnBuscarActionPerformed

    public TableModel creaModeloOp(List <Operacion> ops){
            TablaOp modelo1 = new TablaOp(ops.size(),6);  //numero de filas del ResultSet como parametro del constructor
            int i=0;
            for (Operacion o : ops) {
                  modelo1.setValueAt(o.getId(), i, 0);
                  modelo1.setValueAt(o.getFecha(), i, 1);
                  modelo1.setValueAt(o.getDesc(),i,2);
                  modelo1.setValueAt(o.getCtaCargo(),i,3);
                  modelo1.setValueAt(o.getCtaAbono(),i,4);
                  modelo1.setValueAt(o.getMontoS(),i,5); 
                  i++;
            }
            return modelo1;
    }

    
    private List <Operacion> buscarOperacion(String texto){
          
          Utilidades uts = new Utilidades();
          List <Operacion> ops = new ArrayList();
          TableModel modelo =tablaOp.getModel();
          for(int i=0; i<modelo.getRowCount(); i++){
              String id="";
              String fecha="";
              String desc="";
              String ctaC="";
              String ctaA="";
              String monto="";
              for(int j=0; j<modelo.getColumnCount(); j++){
                  switch(j){
                      case 0:
                          id =  modelo.getValueAt(i,j).toString();
                      break;
                      case 1:
                          fecha =  modelo.getValueAt(i,j).toString();
                      break;
                      case 2:
                          desc =  modelo.getValueAt(i,j).toString();
                      break;
                      case 3:
                          ctaC =  modelo.getValueAt(i,j).toString();
                      break;
                      case 4:
                          ctaA =  modelo.getValueAt(i,j).toString();
                      break;
                      case 5:
                          monto =  modelo.getValueAt(i,j).toString();
                      break; 
                  }
              }
              if(uts.CompararStrings(texto,fecha) || uts.CompararStrings(texto,desc) || uts.CompararStrings(texto,ctaC) || uts.CompararStrings(texto,ctaA) || uts.CompararStrings(texto,monto)){
                      Operacion o = new Operacion();
                      o.setId(id);
                      o.setFecha(fecha);
                      o.setDesc(desc);
                      o.setCtaCargo(ctaC);
                      o.setCtaAbono(ctaA);
                      o.setMontoS(monto);
                      ops.add(o);
              }
          }
          return ops;
    }
    
    private void mostrartodoOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrartodoOpActionPerformed
        LibroDiario d1 = new LibroDiario();
        modOp = d1.creaModeloOp();
        tablaOp.setModel(modOp);
        personalizaTablaOp();
        txtBuscar.setText("");
    }//GEN-LAST:event_mostrartodoOpActionPerformed

    private void cmbMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMesActionPerformed
        LibroDiario d1 = new LibroDiario();
        d1.buscarPorfechaOp(cmbMes.getSelectedItem().toString(), cmbAnio.getSelectedItem().toString());
        modOp = d1.creaModeloOp();
        tablaOp.setModel(modOp);
        personalizaTablaOp();
        txtBuscar.setText("");
    }//GEN-LAST:event_cmbMesActionPerformed

    private void txtBuscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) { 
            if(Pattern.matches("^(([A-Z,Ñ,ñ,a-z]{1,30})([ ]{0,1})*){1,6}$",txtBuscar1.getText())){
                m = new LibroMayor();
                modelo = m.creaModeloCtas(m.buscarCuentasYsub(txtBuscar1.getText()));
                tablaCta.setModel(modelo);
                personalizaTablaCts();
            }else{
                JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
            }
        }
    }//GEN-LAST:event_txtBuscar1KeyReleased

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) { 
            if(Pattern.matches("^(([A-Z,Ñ,ñ,a-z]{1,30})([ ]{0,1})*){1,6}$",txtBuscar.getText())){
                modOp = creaModeloOp(buscarOperacion(txtBuscar.getText()));
                tablaOp.setModel(modOp);
                personalizaTablaOp();
                this.txtBuscar.setText("");
            }else{
                JOptionPane.showOptionDialog(this, "No permitido", "Aviso", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
            }
            
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void cmbAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAnioActionPerformed

    private void sMnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuSalirActionPerformed
        System.exit(1);
    }//GEN-LAST:event_sMnuSalirActionPerformed

    private void sMnuCerrPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuCerrPeriodoActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(
            this, "¿Lo confirmas?");
        if (JOptionPane.OK_OPTION == confirmado){
            Utilidades uts = new Utilidades();
            String fecha = uts.obtFechaStringDMA();
            Secretaria mary = new Secretaria();

            if(mary.cierraPeriodo(fecha)){
                JOptionPane.showOptionDialog(this, "Periodo cerrado exitosamente", "Notificación", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "},"OK");
                IntCuentas c = new IntCuentas();
                c.setVisible(true);
                dispose();
            }else{
                JOptionPane.showOptionDialog(this, "Error al cerrar periodo", "Notificación", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK");
            }
        }
    }//GEN-LAST:event_sMnuCerrPeriodoActionPerformed

    private void sMnuFinanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuFinanzasActionPerformed
        IntCarga c = new IntCarga();
        c.setVisible(true);
        dispose();
    }//GEN-LAST:event_sMnuFinanzasActionPerformed

    private void sMnuAgregarCtaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuAgregarCtaActionPerformed
        IntAgregarCta v = new IntAgregarCta();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_sMnuAgregarCtaActionPerformed

    private void sMnuRegVenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuRegVenActionPerformed
        IntHistoVenta v = new IntHistoVenta();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_sMnuRegVenActionPerformed

    private void sMnuGestVendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuGestVendActionPerformed
        IntVendedores v = new IntVendedores();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_sMnuGestVendActionPerformed

    private void sMnuAgVendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuAgVendActionPerformed
        IntAgregarVend v = new IntAgregarVend();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_sMnuAgVendActionPerformed

    private void sMnuGestionarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuGestionarProvActionPerformed
        IntProveedores v = new IntProveedores();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_sMnuGestionarProvActionPerformed

    private void sMnuAgregarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuAgregarProvActionPerformed
        IntAgregarProv v = new IntAgregarProv();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_sMnuAgregarProvActionPerformed

    private void sMnuGestionarClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuGestionarClActionPerformed
        IntClientes v = new IntClientes();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_sMnuGestionarClActionPerformed

    private void sMnuAgregarClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuAgregarClActionPerformed
        IntAgregarCl v = new IntAgregarCl();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_sMnuAgregarClActionPerformed

    private void sMnuRegPedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuRegPedActionPerformed
        IntSelectProv v = new IntSelectProv();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_sMnuRegPedActionPerformed

    private void sMnuGestProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuGestProdActionPerformed
        IntAlmacen v = new IntAlmacen();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_sMnuGestProdActionPerformed

    private void btnOperacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOperacionActionPerformed
        if(tablaCta.getSelectedRow() != -1){
            if(tablaCta.getValueAt(tablaCta.getSelectedRow(), 0).toString().length() < 6){ //es cuenta principal
                try{
                    if(tablaCta.getValueAt(tablaCta.getSelectedRow()+1, 0).toString().length() < 6){ 
                        IntOperacionSimple o = new IntOperacionSimple(tablaCta.getValueAt(tablaCta.getSelectedRow(), 0).toString());
                        o.setVisible(true);
                        dispose();
                    }
                    else{
                    String nombre = tablaCta.getValueAt(tablaCta.getSelectedRow(), 1).toString();
                    JOptionPane.showOptionDialog(this, "Seleccione una subcuenta de: " + nombre, "Notificación", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{" OK "},"OK");                     
                }
                }catch(Exception c){
                    System.out.println("ultima cuenta");
                    IntOperacionSimple o = new IntOperacionSimple(tablaCta.getValueAt(tablaCta.getSelectedRow(), 0).toString());
                    o.setVisible(true);
                    dispose();
                }
                
            }else{ // es sub cuenta
                IntOperacionSimple o = new IntOperacionSimple(tablaCta.getValueAt(tablaCta.getSelectedRow(), 0).toString());
                o.setVisible(true);
                dispose();
            }
        }else{
            JOptionPane.showOptionDialog(this, "Seleccione una cuenta", "Notificación", JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, new Object[]{" OK "},"OK"); 
        }
    }//GEN-LAST:event_btnOperacionActionPerformed

    private void sMnuEdoResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sMnuEdoResActionPerformed
        IntEdoRes edo = new IntEdoRes();
        edo.setVisible(true);
        dispose();
    }//GEN-LAST:event_sMnuEdoResActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        IntProductos v = new IntProductos();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void smnuBalGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smnuBalGenActionPerformed
        IntBalanceGral bg = new IntBalanceGral();
        bg.setVisible(true);
        dispose();
    }//GEN-LAST:event_smnuBalGenActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCta;
    private javax.swing.JButton btnAddSubCta;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnMostrarTodo;
    private javax.swing.JButton btnOp;
    private javax.swing.JButton btnOperacion;
    private javax.swing.JButton btnborraCta;
    private javax.swing.JComboBox cmbAnio;
    private javax.swing.JComboBox cmbMes;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JMenu mnuAlmacen;
    private javax.swing.JMenu mnuClientes;
    private javax.swing.JMenu mnuContabilidad;
    private javax.swing.JMenu mnuOpciones;
    private javax.swing.JMenu mnuPeriodo;
    private javax.swing.JMenu mnuProveedores;
    private javax.swing.JMenu mnuVendedores;
    private javax.swing.JButton mostrartodoOp;
    private javax.swing.JMenuItem sMnuAgVend;
    private javax.swing.JMenuItem sMnuAgregarCl;
    private javax.swing.JMenuItem sMnuAgregarCta;
    private javax.swing.JMenuItem sMnuAgregarProv;
    private javax.swing.JMenuItem sMnuCerrPeriodo;
    private javax.swing.JMenuItem sMnuEdoRes;
    private javax.swing.JMenuItem sMnuFinanzas;
    private javax.swing.JMenuItem sMnuGestProd;
    private javax.swing.JMenuItem sMnuGestVend;
    private javax.swing.JMenuItem sMnuGestionarCl;
    private javax.swing.JMenuItem sMnuGestionarProv;
    private javax.swing.JMenuItem sMnuRegPed;
    private javax.swing.JMenuItem sMnuRegVen;
    private javax.swing.JMenuItem sMnuSalir;
    private javax.swing.JMenuItem smnuBalGen;
    private javax.swing.JTable tablaBG;
    private javax.swing.JTable tablaCta;
    private javax.swing.JTable tablaOp;
    private javax.swing.JTable tblInd;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    // End of variables declaration//GEN-END:variables

    public Boolean getDone() {
        return done;
    }
    
}
