package Vista;

import Modelo.Color;
import javax.swing.JFrame;

public class VAyuda extends javax.swing.JFrame {
    

    public VAyuda() {
        initComponents();
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        /*Rellenamos descripciones*/
        String anadirBBDD = "<strong>Nueva base de datos.</strong> Con este botón saldrá una ventana emergente en la que se pedirán los datos de la nueva base de datos. Una vez completado, se añadirá al conjunto.";
        String eliminarBBDD = "<strong>Eliminar base de datos.</strong> Con este botón, y habiendo seleccionado una base de datos, se podrá eliminar la base de datos y su contenido, del conjunto.";
        String editarBBDD = "<strong>Editar base de datos.</strong> Con este botón y habiendo seleccionado una base de datos, se podrán editar el nombre y la descripción de esta.";
        String listarImagenes = "<strong>Listar imagenes.</strong> Con este botón y habiendo seleccionado una de las bases de datos, aparecerá una ventana emergente en la cual se visualizarán todas las "
                + "imagenes de la base de datos. En estas, si se clica sobre ellas se podrán eliminar o visualizar en mayor tamaño.";
        String anadirImagen = "<strong>Añadir imagen.</strong> Con este botón y habiendo seleccionado una base de datos, se podrá añadir una imagen contenida en local, a la base de datos seleccionada. Se permite añadir varias imagenes.";
        String pregunta = "<strong>Calcular imagen pregunta.</strong> Esta es la funcionalidad principal de la aplicación. Se permite añadir una imagen con la que comparar todas las imagenes de la base de "
                + "datos seleccionada. Se calculará un ranking teniendo en cuenta diferentes características.";
        String calcular = "<strong>Calcular descriptores.</strong> Con este botón y habiendo selccionado una base de datos, se calcularán todos los descriptores necesarios para su posterior calculo del"
                + " ranking de semejanza.";
        String salir = "<strong>Salir.</strong> Se sale del programa, guardando todos los cambios para posteriores inicios de sesión.";
        
        /*introducimos en campos*/
        
        descripcionLabel1.setText("<html><p align=\"justify\">" + anadirBBDD.replaceAll("\\n", "<br>") + "</p></html>");
        descripcionLabel2.setText("<html><p align=\"justify\">" + eliminarBBDD.replaceAll("\\n", "<br>") + "</p></html>");
        descripcionLabel3.setText("<html><p align=\"justify\">" + editarBBDD.replaceAll("\\n", "<br>") + "</p></html>");
        descripcionLabel4.setText("<html><p align=\"justify\">" + listarImagenes.replaceAll("\\n", "<br>") + "</p></html>");
        descripcionLabel5.setText("<html><p align=\"justify\">" + anadirImagen.replaceAll("\\n", "<br>") + "</p></html>");
        descripcionLabel6.setText("<html><p align=\"justify\">" + pregunta.replaceAll("\\n", "<br>") + "</p></html>");
        descripcionLabel7.setText("<html><p align=\"justify\">" + calcular.replaceAll("\\n", "<br>") + "</p></html>");
        descripcionLabel10.setText("<html><p align=\"justify\">" + salir.replaceAll("\\n", "<br>") + "</p></html>");
        
        this.setResizable(false);
        
        /*Colores*/
        this.getContentPane().setBackground(Color.colorFondo);
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label2 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        label8 = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        label7 = new javax.swing.JLabel();
        descripcionLabel2 = new javax.swing.JLabel();
        descripcionLabel1 = new javax.swing.JLabel();
        descripcionLabel4 = new javax.swing.JLabel();
        descripcionLabel3 = new javax.swing.JLabel();
        descripcionLabel6 = new javax.swing.JLabel();
        descripcionLabel5 = new javax.swing.JLabel();
        descripcionLabel7 = new javax.swing.JLabel();
        descripcionLabel10 = new javax.swing.JLabel();
        descripcionLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana de ayuda al usuario");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        label2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/eliminarBBDD.png"))); // NOI18N

        label1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/anadirBBDD.png"))); // NOI18N

        label4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/imagenes.png"))); // NOI18N

        label3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editarBBDD.png"))); // NOI18N

        label5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/anadirImagen.png"))); // NOI18N

        label8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salir.png"))); // NOI18N

        label6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pregunta.png"))); // NOI18N

        label7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/calculadora.png"))); // NOI18N

        descripcionLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        descripcionLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        descripcionLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        descripcionLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        descripcionLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        descripcionLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        descripcionLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        descripcionLabel10.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        descripcionLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        descripcionLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descripcionLabel11.setText("Para más imformación consulte el manual de usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(label2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(descripcionLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                            .addComponent(descripcionLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(descripcionLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(descripcionLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descripcionLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(descripcionLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(descripcionLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(descripcionLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(descripcionLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descripcionLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descripcionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descripcionLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descripcionLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descripcionLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descripcionLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descripcionLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(descripcionLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.dispose();
        //cierro solo esta ventana
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel descripcionLabel1;
    private javax.swing.JLabel descripcionLabel10;
    private javax.swing.JLabel descripcionLabel11;
    private javax.swing.JLabel descripcionLabel2;
    private javax.swing.JLabel descripcionLabel3;
    private javax.swing.JLabel descripcionLabel4;
    private javax.swing.JLabel descripcionLabel5;
    private javax.swing.JLabel descripcionLabel6;
    private javax.swing.JLabel descripcionLabel7;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    // End of variables declaration//GEN-END:variables
}
