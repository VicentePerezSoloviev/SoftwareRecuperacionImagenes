package Vista;

import Exceptions.DifferentSizesMatException;
import Exceptions.FileNotExistsException;
import Exceptions.NoSenseMatArrayListException;
import Exceptions.TypeNotExistsException;
import Modelo.Color;
import Modelo.Imagen;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class VCalculoPregunta extends javax.swing.JFrame {
    
    private final Controlador.ControladorCalculoPregunta controlador;
    private final JFrame vPrincipal;
    boolean imagenCargada;

    public VCalculoPregunta(JFrame vPrincipal, String nombre) {
        imagenCargada = false;
        
        initComponents();
        this.vPrincipal = vPrincipal;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.vPrincipal.setEnabled(false);
        this.controlador = new Controlador.ControladorCalculoPregunta(this, nombre);
        
        String texto = "Fecha de ultima\n modificación imagen";
        jLabel2.setText("<html>" + texto.replaceAll("\\n", "<br>") + "</html>");
        
        texto = "Seleccionar\n imagen";
        BtnImagenBBDD.setText("<html><center>" + texto.replaceAll("\\n", "<br>") + "</center></html>");
        
        texto = "Calcular\n descriptores";
        BtnCalcularDescriptores.setText("<html><center>" + texto.replaceAll("\\n", "<br>") + "</center></html>");
        BtnCalcularDescriptores.setEnabled(false);
        
        DefaultTableCellRenderer r;

        String headers[] = new String[5];
        headers[0] = "Posicion";
        headers[1] = "Semejanza";
        headers[2] = "Nombre de imagen";
        
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tabla.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        tabla.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        
        AbstractTableModel dtm = (AbstractTableModel) tabla.getModel();
        r = (DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        
        dtm.setValueAt(headers[0], 0, 0);
        
        TableColumn col = tabla.getColumnModel().getColumn(0);
        col.setPreferredWidth(100);
        
        col = tabla.getColumnModel().getColumn(1);
        col.setPreferredWidth(200);
        
        col = tabla.getColumnModel().getColumn(2);
        col.setPreferredWidth(354);
        
        this.LabelImagen.setText("");
        
        this.panel_seleccion.setEnabled(false);
        this.checkBoxForma.setEnabled(false);
        this.checkboxColor.setEnabled(false);
        this.BtonPropiedades.setEnabled(false);
        this.BtonCalcularRanking.setEnabled(false);
        this.jLabel1.setEnabled(false);
        this.jLabel2.setEnabled(false);
        this.jLabel3.setEnabled(false);
        
        this.tabla.setEnabled(false);
        
        this.nombre_imagen_label.setText("");
        this.fecha_imagen_label.setText("");
        this.nombre_bbdd.setText(nombre);
        
        this.LabelImagen.setEnabled(false);
        
        this.tabla.setEnabled(false);
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
        /*Colores*/
        this.getContentPane().setBackground(Color.colorFondo);
        
        this.BtnCalcularDescriptores.setBackground(Color.colorBoton);
        this.BtnImagenBBDD.setBackground(Color.colorBoton);
        this.BtonAyuda.setBackground(Color.colorBoton);
        this.BtonCalcularRanking.setBackground(Color.colorBoton);
        this.BtonPropiedades.setBackground(Color.colorBoton);
        this.BtonSalir.setBackground(Color.colorBoton);
        
        /*cabecera*/
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(Color.colorBoton);

        for (int i = 0; i < tabla.getModel().getColumnCount(); i++) {
                tabla.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        /*tabla en si*/
        
        jScrollPane1.setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        
        this.panel_seleccion.setOpaque(false);
        
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LabelImagen = new javax.swing.JLabel();
        BtnImagenBBDD = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BtnCalcularDescriptores = new javax.swing.JButton();
        panel_seleccion = new javax.swing.JPanel();
        checkboxColor = new javax.swing.JCheckBox();
        checkBoxForma = new javax.swing.JCheckBox();
        BtonCalcularRanking = new javax.swing.JButton();
        BtonPropiedades = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        nombre_imagen_label = new javax.swing.JLabel();
        fecha_imagen_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        BtonAyuda = new javax.swing.JButton();
        BtonSalir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        nombre_bbdd = new javax.swing.JLabel();
        tipoHist = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculo Imagen pregunta");
        setFocusableWindowState(false);
        setPreferredSize(new java.awt.Dimension(700, 667));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        LabelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cuestion_grande.png"))); // NOI18N
        LabelImagen.setText("Imagen");
        LabelImagen.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null), null));

        BtnImagenBBDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImagenBBDDActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre imagen: ");

        BtnCalcularDescriptores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCalcularDescriptoresActionPerformed(evt);
            }
        });

        checkboxColor.setText("Color");
        checkboxColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxColorActionPerformed(evt);
            }
        });

        checkBoxForma.setText("Forma");
        checkBoxForma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxFormaActionPerformed(evt);
            }
        });

        BtonCalcularRanking.setText("Calcular");
        BtonCalcularRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtonCalcularRankingActionPerformed(evt);
            }
        });

        BtonPropiedades.setText("Propiedades");
        BtonPropiedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtonPropiedadesActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Ranking");

        javax.swing.GroupLayout panel_seleccionLayout = new javax.swing.GroupLayout(panel_seleccion);
        panel_seleccion.setLayout(panel_seleccionLayout);
        panel_seleccionLayout.setHorizontalGroup(
            panel_seleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_seleccionLayout.createSequentialGroup()
                .addContainerGap(136, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
            .addGroup(panel_seleccionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_seleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkboxColor)
                    .addComponent(checkBoxForma)
                    .addComponent(BtonPropiedades, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtonCalcularRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_seleccionLayout.setVerticalGroup(
            panel_seleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_seleccionLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkboxColor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBoxForma)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_seleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtonCalcularRanking, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(BtonPropiedades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        nombre_imagen_label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nombre_imagen_label.setText("nombre_imagen");

        fecha_imagen_label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        fecha_imagen_label.setText("fecha_imagen");

        tabla.setModel(new ModeloTablaRanking());
        jScrollPane1.setViewportView(tabla);

        BtonAyuda.setText("Ayuda");
        BtonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtonAyudaActionPerformed(evt);
            }
        });

        BtonSalir.setText("Salir");
        BtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtonSalirActionPerformed(evt);
            }
        });

        jLabel4.setText("Base de datos:");

        nombre_bbdd.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nombre_bbdd.setText("nombre_bbdd");

        tipoHist.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tipoHist.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tipoHist.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnImagenBBDD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnCalcularDescriptores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(LabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel_seleccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fecha_imagen_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nombre_imagen_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nombre_bbdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtonAyuda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tipoHist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnImagenBBDD, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnCalcularDescriptores, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(nombre_bbdd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nombre_imagen_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha_imagen_label))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_seleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LabelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(tipoHist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtonAyuda, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(BtonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void aux_1 (Imagen imagen) {
        BtnCalcularDescriptores.setEnabled(true);
        LabelImagen.setEnabled(true);
        
        this.jLabel2.setEnabled(true);
        this.jLabel1.setEnabled(true);
        this.nombre_imagen_label.setVisible(true);
        this.fecha_imagen_label.setVisible(true);
        
        this.nombre_imagen_label.setText(imagen.getNombre());
        this.fecha_imagen_label.setText(imagen.getFecha());
        
        this.setVisible(true);
    }
    
    private void BtnImagenBBDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImagenBBDDActionPerformed
        Imagen imagen = null;
        try {
            imagen = this.controlador.imagenBBDD(this.LabelImagen);
        } catch (IOException ex) {
            Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (imagen != null) {
            aux_1 (imagen);
            imagenCargada = true;
            
            this.panel_seleccion.setEnabled(true);
            this.checkBoxForma.setEnabled(true);
            this.checkboxColor.setEnabled(true);
            this.BtonCalcularRanking.setEnabled(true);
            this.BtonPropiedades.setEnabled(true);
            this.jLabel3.setEnabled(true);
            /*SI LOS DESCRIPTORES YA ESTAN CALCULADOS ENTONCES SE DESHABILITA EL BOTON DE CALCULAR DECRIPTORES*/

            this.setEnabled(true);
            this.setVisible(true);
        }
        
        
    }//GEN-LAST:event_BtnImagenBBDDActionPerformed

    private void BtnCalcularDescriptoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCalcularDescriptoresActionPerformed
        if (imagenCargada) {

            try {
                // TODO add your handling code here:
                this.controlador.CalcularDescriptores();
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Ha habido algun problema cargando los descriptores de las imagenes", "Atencion", JOptionPane.WARNING_MESSAGE);
            }

            this.panel_seleccion.setEnabled(true);
            this.checkBoxForma.setEnabled(true);
            this.checkboxColor.setEnabled(true);
            this.BtonCalcularRanking.setEnabled(true);
            this.BtonPropiedades.setEnabled(true);
            this.jLabel3.setEnabled(true);
        }
        
        
        
    }//GEN-LAST:event_BtnCalcularDescriptoresActionPerformed

    private void checkboxColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxColorActionPerformed

    private void checkBoxFormaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxFormaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxFormaActionPerformed

    private void BtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtonSalirActionPerformed
        // TODO add your handling code here:
        this.controlador.cerrarVentana();
    }//GEN-LAST:event_BtonSalirActionPerformed

    private void BtonCalcularRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtonCalcularRankingActionPerformed
        if (imagenCargada) {
            // vamos a limpiar la tabla
        
            ModeloTablaRanking m;
            m=(ModeloTablaRanking) tabla.getModel();
            m.clean();
            m.fireTableDataChanged();

            HashMap <String, Double> Ranking;
            if (this.checkboxColor.isSelected() && !this.checkBoxForma.isSelected()){       //SOLO CALCULO DE COLOR
                tipoHist.setText("Ranking de semejanza por color");
                try {
                    Ranking = this.controlador.calcularRankingColor();
                    System.out.println("color" +Ranking);

                    /*Meto hashMap en la tabla de resultados*/
                    m=(ModeloTablaRanking) tabla.getModel();
                    m.setFilas(Ranking, this.controlador.sortHashMapByValues(Ranking));
                    m.fireTableDataChanged();

                } catch (IOException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Ha ocurrido algun error realizando el ranking. Contacte con el desarrollador", "Atencion", JOptionPane.WARNING_MESSAGE);
                } catch (DifferentSizesMatException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Ha ocurrido algun error con los tamanos de los histogramas de las imagenes", "Atencion", JOptionPane.WARNING_MESSAGE);
                } catch (FileNotExistsException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "No existe alguna de las imagenes que se estan analizando", "Atencion", JOptionPane.WARNING_MESSAGE);
                } catch (TypeNotExistsException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else if (!this.checkboxColor.isSelected() && this.checkBoxForma.isSelected()){  //SOLO CALCULO DE FORMA
                tipoHist.setText("Ranking de semejanza por forma");
                try {
                    Ranking = this.controlador.calcularRankingForma();
                    System.out.println("forma" + Ranking);

                    /*Meto hashMap en la tabla de resultados*/
                    m=(ModeloTablaRanking) tabla.getModel();
                    m.setFilas(Ranking, this.controlador.sortHashMapByValues(Ranking));
                    m.fireTableDataChanged();
                } catch (IOException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Ha ocurrido algun error realizando el ranking. Contacte con el desarrollador", "Atencion", JOptionPane.WARNING_MESSAGE);
                } catch (DifferentSizesMatException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Ha ocurrido algun error con los tamanos de los histogramas de las imagenes. Contacte con el desarrollador", "Atencion", JOptionPane.WARNING_MESSAGE);
                } catch (FileNotExistsException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "No existe alguna de las imagenes que se estan analizando. Contacte con el desarrollador", "Atencion", JOptionPane.WARNING_MESSAGE);
                } catch (NoSenseMatArrayListException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Ha habido algun problema extrayendo los datos del fichero y pasandolos de tipo de dato array a Mat. Contacte con el desarrollador", "Atencion", JOptionPane.WARNING_MESSAGE);
                } catch (TypeNotExistsException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error con la gestion de tipos de histograma. Contacte con el desarrollador", "Atencion", JOptionPane.WARNING_MESSAGE);
                }

            }
            else if (this.checkboxColor.isSelected() && this.checkBoxForma.isSelected()){  
                tipoHist.setText("Ranking de semejanza por color y forma combinados");
                try {
                    //color forma
                    Ranking = this.controlador.calcularRankingFormaColor();
                    System.out.println("formaColor" + Ranking);

                    /*Meto hashMap en la tabla de resultados*/
                    m=(ModeloTablaRanking) tabla.getModel();
                    m.setFilas(Ranking, this.controlador.sortHashMapByValues(Ranking));
                    m.fireTableDataChanged();

                } catch (IOException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Ha ocurrido algun error realizando el ranking. Contacte con el desarrollador", "Atencion", JOptionPane.WARNING_MESSAGE);
                } catch (FileNotExistsException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "No existe alguna de las imagenes que se estan analizando. Contacte con el desarrollador", "Atencion", JOptionPane.WARNING_MESSAGE);
                } catch (DifferentSizesMatException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Ha ocurrido algun error con los tamanos de los histogramas de las imagenes. Contacte con el desarrollador", "Atencion", JOptionPane.WARNING_MESSAGE);
                } catch (NoSenseMatArrayListException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Ha habido algun problema extrayendo los datos del fichero y pasandolos de tipo de dato array a Mat. Contacte con el desarrollador", "Atencion", JOptionPane.WARNING_MESSAGE);
                } catch (TypeNotExistsException ex) {
                    Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error con la gestion de tipos de histograma. Contacte con el desarrollador", "Atencion", JOptionPane.WARNING_MESSAGE);
                }

            }
            else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una caracteristica para calcular");
            }
        }

        
    }//GEN-LAST:event_BtonCalcularRankingActionPerformed

    private void BtonPropiedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtonPropiedadesActionPerformed
        if (imagenCargada) {
            try {
                this.controlador.mostrarHistograma();
            } catch (FileNotExistsException | IOException ex) {
                Logger.getLogger(VCalculoPregunta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_BtonPropiedadesActionPerformed

    private void BtonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtonAyudaActionPerformed
        // TODO add your handling code here:
        VAyudaPregunta vayuda = new VAyudaPregunta();
        vayuda.setVisible(true);
    }//GEN-LAST:event_BtonAyudaActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.vPrincipal.setEnabled(true);
        this.vPrincipal.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.vPrincipal.setEnabled(true);
        this.vPrincipal.setVisible(true);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCalcularDescriptores;
    private javax.swing.JButton BtnImagenBBDD;
    private javax.swing.JButton BtonAyuda;
    private javax.swing.JButton BtonCalcularRanking;
    private javax.swing.JButton BtonPropiedades;
    private javax.swing.JButton BtonSalir;
    private javax.swing.JLabel LabelImagen;
    private javax.swing.JCheckBox checkBoxForma;
    private javax.swing.JCheckBox checkboxColor;
    private javax.swing.JLabel fecha_imagen_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nombre_bbdd;
    private javax.swing.JLabel nombre_imagen_label;
    private javax.swing.JPanel panel_seleccion;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel tipoHist;
    // End of variables declaration//GEN-END:variables
}
