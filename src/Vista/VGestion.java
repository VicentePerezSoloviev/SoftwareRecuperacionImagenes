package Vista;

import Modelo.Comprobaciones;
import Controlador.ControladorGestion;
import Modelo.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public final class VGestion extends javax.swing.JFrame {
    final Controlador.ControladorGestion controladorGestion;
    
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VGestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new VGestion().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(VGestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
    }

    @SuppressWarnings({"static-access", "LeakingThisInConstructor"})
    public VGestion() throws IOException {
        
        /*Comprobamos que los directorio esten correctamente para evitar fallos*/
        
        Comprobaciones comprobaciones = new Comprobaciones();
        if (!comprobaciones.comprobar()) {
            JOptionPane.showMessageDialog(this, "Ha habido un error con el directorio principal", "Atencion", JOptionPane.WARNING_MESSAGE);
            this.dispose();
            this.setVisible(false);
            System.exit(0);
        }
        
        initComponents();
        
        loading.setVisible(false);
        
        /*Diseño de colores y tabla transparentes*/
        
        this.getContentPane().setBackground(Color.colorFondo);
        BtnNuevaBBDD.setBackground(Color.colorBoton);
        BtnEliminarBBDD.setBackground(Color.colorBoton);
        BtnAnadirImagenes.setBackground(Color.colorBoton);
        BtnAyuda.setBackground(Color.colorBoton);
        BtnEditarBBDD.setBackground(Color.colorBoton);
        BtnListarImagen.setBackground(Color.colorBoton);
        BtnSalir.setBackground(Color.colorBoton);
        BtnPregunta.setBackground(Color.colorBoton);
        BtnCalcular.setBackground(Color.colorBoton);
        
        /*cabecera de tabla*/
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(Color.colorBoton);

        for (int i = 0; i < tabla.getModel().getColumnCount(); i++) {
                tabla.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        /*tabla en si*/
        
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        
        tabla.setShowGrid(false);
        
        tabla.setRowSelectionAllowed(true);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        //Inhabilito la seleccion multiple
        
        controladorGestion = new ControladorGestion (this);
        
        ModeloTablaBBDD m;
        
        //Cargo la tabla con las bases de datos actuales
        
        m=(ModeloTablaBBDD) tabla.getModel();
        m.setFilas(controladorGestion.ListarBasesDeDatos());
        m.fireTableDataChanged();
        
        desactivarActivarBotones(false);
        
        /*Metemos los titulos centrados*/
        
        DefaultTableCellRenderer r;

        String headers[] = new String[5];
        headers[0] = "Nombre";
        headers[1] = "Nº imagenes";
        headers[2] = "Nº descriptores";
        headers[3] = "Fecha";
        headers[4] = "Descripcion";
        
        AbstractTableModel dtm = (AbstractTableModel) tabla.getModel();
        r = (DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        
        dtm.setValueAt(headers[0], 0, 0);
        
        /*centrar contenido de dos columnas*/
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tabla.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        tabla.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
        
        /*Tamaño de la tabla ppal*/

        tabla.setAutoResizeMode(tabla.AUTO_RESIZE_OFF);
        
        TableColumn col = tabla.getColumnModel().getColumn(0);
        col.setPreferredWidth(245);
        
        col = tabla.getColumnModel().getColumn(1);
        col.setPreferredWidth(110);
        
        col = tabla.getColumnModel().getColumn(2);
        col.setPreferredWidth(110);
        
        col = tabla.getColumnModel().getColumn(3);
        col.setPreferredWidth(135);
        
        col = tabla.getColumnModel().getColumn(4);
        col.setPreferredWidth(375);
        
        /*Atajos de teclado*/
        
        tabla.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_DELETE){
                    eliminar();
                }
            }

        });
        
        /*Ayudas de botones
        cuando raton pasa por encima si muestra una ayuda*/
        
        BtnEliminarBBDD.setToolTipText("Eliminar BBDD");
        BtnAnadirImagenes.setToolTipText("Añadir imagenes a BBDD");
        BtnAyuda.setToolTipText("Ayuda");
        BtnEditarBBDD.setToolTipText("Editar BBDD");
        BtnListarImagen.setToolTipText("Listar imagenes de la BBDD");
        BtnNuevaBBDD.setToolTipText("Nueva BBDD");
        BtnSalir.setToolTipText("Salir");
        BtnPregunta.setToolTipText("Calcular imagen pregunta a partir de esta BBDD");
        BtnCalcular.setToolTipText("Calcular descriptores que faltan de la BBDD");
        
    }
    
    
    public void desactivarActivarBotones (boolean p) {
        this.BtnEliminarBBDD.setEnabled(p);
        this.BtnListarImagen.setEnabled(p);
        this.BtnCalcular.setEnabled(p);
        this.BtnAnadirImagenes.setEnabled(p);
        this.BtnPregunta.setEnabled(p);
        this.BtnEditarBBDD.setEnabled(p);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnNuevaBBDD = new javax.swing.JButton();
        BtnEliminarBBDD = new javax.swing.JButton();
        BtnEditarBBDD = new javax.swing.JButton();
        BtnListarImagen = new javax.swing.JButton();
        BtnAnadirImagenes = new javax.swing.JButton();
        BtnPregunta = new javax.swing.JButton();
        BtnCalcular = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        BtnAyuda = new javax.swing.JButton();
        BtnSalir = new javax.swing.JButton();
        loading = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion de BBDD");
        setPreferredSize(new java.awt.Dimension(1000, 500));
        setResizable(false);

        BtnNuevaBBDD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/anadirBBDD.png"))); // NOI18N
        BtnNuevaBBDD.setText("jButton1");
        BtnNuevaBBDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevaBBDDActionPerformed(evt);
            }
        });

        BtnEliminarBBDD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/eliminarBBDD.png"))); // NOI18N
        BtnEliminarBBDD.setText("jButton1");
        BtnEliminarBBDD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnEliminarBBDDMouseClicked(evt);
            }
        });
        BtnEliminarBBDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarBBDDActionPerformed(evt);
            }
        });

        BtnEditarBBDD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editarBBDD.png"))); // NOI18N
        BtnEditarBBDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarBBDDActionPerformed(evt);
            }
        });

        BtnListarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/imagenes.png"))); // NOI18N
        BtnListarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnListarImagenActionPerformed(evt);
            }
        });

        BtnAnadirImagenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/anadirImagen.png"))); // NOI18N
        BtnAnadirImagenes.setText("jButton1");
        BtnAnadirImagenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnadirImagenesActionPerformed(evt);
            }
        });

        BtnPregunta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pod.png"))); // NOI18N
        BtnPregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPreguntaActionPerformed(evt);
            }
        });

        BtnCalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/calculadora.png"))); // NOI18N
        BtnCalcular.setText("jButton1");
        BtnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCalcularActionPerformed(evt);
            }
        });

        tabla.setModel(new ModeloTablaBBDD());
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        BtnAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ayuda.png"))); // NOI18N
        BtnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAyudaActionPerformed(evt);
            }
        });

        BtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salir.png"))); // NOI18N
        BtnSalir.setPreferredSize(new java.awt.Dimension(165, 63));
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });

        loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/load.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnNuevaBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 336, Short.MAX_VALUE)
                        .addComponent(loading, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223)
                        .addComponent(BtnEliminarBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnEditarBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnListarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnAnadirImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(BtnNuevaBBDD)
                        .addComponent(BtnEliminarBBDD)
                        .addComponent(BtnEditarBBDD)
                        .addComponent(BtnListarImagen)
                        .addComponent(BtnAnadirImagenes)
                        .addComponent(BtnPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(BtnCalcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(loading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnEliminarBBDDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnEliminarBBDDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEliminarBBDDMouseClicked

    private void BtnNuevaBBDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevaBBDDActionPerformed
        // TODO add your handling code here:
        this.controladorGestion.NuevaBBDD();
    }//GEN-LAST:event_BtnNuevaBBDDActionPerformed
    
    @SuppressWarnings("UnusedAssignment")
    private void BtnAnadirImagenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnadirImagenesActionPerformed
        if (tabla.getSelectedRow() >= 0){
            try {
                ModeloTablaBBDD mtbd = (ModeloTablaBBDD) tabla.getModel();
                String nombre = null;
                nombre = mtbd.obtenerBBDD(tabla.getSelectedRow()).getNombre();
                
                if (nombre != null) try {
                    this.controladorGestion.anadirImagen(nombre);
                } catch (IOException ex) {
                    Logger.getLogger(VGestion.class.getName()).log(Level.SEVERE, null, ex);
                }
                else JOptionPane.showMessageDialog(this, "Debe seleccionar una BBDD");
                
                ModeloTablaBBDD m;
                m = (ModeloTablaBBDD) this.tabla.getModel();
                m.setFilas(this.controladorGestion.ListarBasesDeDatos());
                m.fireTableDataChanged();
            } catch (IOException ex) {
                Logger.getLogger(VGestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else JOptionPane.showMessageDialog(this, "Debe seleccionar una BBDD");
    }//GEN-LAST:event_BtnAnadirImagenesActionPerformed
    
    private void eliminar() {
        if (tabla.getSelectedRow() >= 0){
            ModeloTablaBBDD mtbd = (ModeloTablaBBDD) tabla.getModel();
            
            @SuppressWarnings("UnusedAssignment")
            String nombre = null;
            nombre = mtbd.obtenerBBDD(tabla.getSelectedRow()).getNombre();

            if (nombre != null) this.controladorGestion.EliminarBBDD(nombre);
            else JOptionPane.showMessageDialog(this, "Debe seleccionar una BBDD");
        }
        else JOptionPane.showMessageDialog(this, "Debe seleccionar una BBDD");
    }
    
    private void BtnEliminarBBDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarBBDDActionPerformed
        eliminar();
    }//GEN-LAST:event_BtnEliminarBBDDActionPerformed
    
    @SuppressWarnings("UnusedAssignment")
    private void BtnEditarBBDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarBBDDActionPerformed
        // TODO add your handling code here:
        if (tabla.getSelectedRow() >= 0){
            ModeloTablaBBDD mtbd = (ModeloTablaBBDD) tabla.getModel();
            String nombre = mtbd.obtenerBBDD(tabla.getSelectedRow()).getNombre();
            String descripcion = mtbd.obtenerBBDD(tabla.getSelectedRow()).getDescripcion();

            if (nombre != null) this.controladorGestion.EditarBBDD(nombre, descripcion);
            else JOptionPane.showMessageDialog(this, "Debe seleccionar una BBDD");
        }
        else JOptionPane.showMessageDialog(this, "Debe seleccionar una BBDD");
        
    }//GEN-LAST:event_BtnEditarBBDDActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        this.desactivarActivarBotones(true);
    }//GEN-LAST:event_tablaMouseClicked

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        int conf = JOptionPane.showConfirmDialog (null, "Esta seguro de que desea salir del programa? ", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

        if (conf == JOptionPane.YES_OPTION) {           //si
            //salimos del programa
            this.controladorGestion.cerrarVentana();
        }
        
    }//GEN-LAST:event_BtnSalirActionPerformed
    
    @SuppressWarnings("UnusedAssignment")
    private void BtnListarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnListarImagenActionPerformed
        
        if (tabla.getSelectedRow() >= 0){
            try {
                ModeloTablaBBDD mtbd = (ModeloTablaBBDD) tabla.getModel();
                String nombre = mtbd.obtenerBBDD(tabla.getSelectedRow()).getNombre();

                if (nombre != null) this.controladorGestion.ListadoImagenes(nombre);
                else JOptionPane.showMessageDialog(this, "Debe seleccionar una BBDD");
            } catch (IOException ex) {
                Logger.getLogger(VGestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else JOptionPane.showMessageDialog(this, "Debe seleccionar una BBDD");
        loading.setVisible(false);
    }//GEN-LAST:event_BtnListarImagenActionPerformed
    
    @SuppressWarnings("UnusedAssignment")
    private void BtnPreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPreguntaActionPerformed
        // TODO add your handling code here:
        if (tabla.getSelectedRow() >= 0){
            ModeloTablaBBDD mtbd = (ModeloTablaBBDD) tabla.getModel();
            String nombre = mtbd.obtenerBBDD(tabla.getSelectedRow()).getNombre();

            if (nombre != null) try {
                this.controladorGestion.CalcularPregunta(nombre);
            } catch (IOException ex) {
                Logger.getLogger(VGestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            else JOptionPane.showMessageDialog(this, "Debe seleccionar una BBDD");
        } else JOptionPane.showMessageDialog(this, "Debe seleccionar una BBDD");
        
    }//GEN-LAST:event_BtnPreguntaActionPerformed

    private void BtnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCalcularActionPerformed
        
        if (tabla.getSelectedRow() >= 0){
            loading.setVisible(true);
            try {
                ModeloTablaBBDD mtbd = (ModeloTablaBBDD) tabla.getModel();
                String nombre = mtbd.obtenerBBDD(tabla.getSelectedRow()).getNombre();
                
                if (nombre != null) try {
                    controladorGestion.CalcularDescriptores(nombre);
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(VGestion.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Ha habido algun problema cargando los descriptores de las imagenes", "Atencion", JOptionPane.WARNING_MESSAGE);
                }
                else JOptionPane.showMessageDialog(this, "Debe seleccionar una BBDD");
                
                ModeloTablaBBDD m;
                m = (ModeloTablaBBDD) this.tabla.getModel();
                m.setFilas(this.controladorGestion.ListarBasesDeDatos());
                m.fireTableDataChanged();
                loading.setVisible(false);
            } catch (IOException ex) {
                Logger.getLogger(VGestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else JOptionPane.showMessageDialog(this, "Debe seleccionar una BBDD");
        
    }//GEN-LAST:event_BtnCalcularActionPerformed

    private void BtnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAyudaActionPerformed
        // TODO add your handling code here:
        VAyuda vayuda = new VAyuda();
        vayuda.setVisible(true);
    }//GEN-LAST:event_BtnAyudaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BtnAnadirImagenes;
    public javax.swing.JButton BtnAyuda;
    public javax.swing.JButton BtnCalcular;
    public javax.swing.JButton BtnEditarBBDD;
    public javax.swing.JButton BtnEliminarBBDD;
    public javax.swing.JButton BtnListarImagen;
    public javax.swing.JButton BtnNuevaBBDD;
    public javax.swing.JButton BtnPregunta;
    public javax.swing.JButton BtnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel loading;
    public javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
