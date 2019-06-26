package Vista;

import Controlador.ControladorListadoImagenes;
import Modelo.DirectorioRaiz;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class ImagePane extends JPanel {

        private Image img;
        private JPopupMenu popup;
        private final JMenuItem ver;
        private final JMenuItem eliminar;
        private final DirectorioRaiz directorioRaiz;
        private final ControladorListadoImagenes controlador;

        public ImagePane(ControladorListadoImagenes controlador, File source, TestPane im) throws IOException {
            directorioRaiz = new DirectorioRaiz();
            this.controlador = controlador;
            
            img = ImageIO.read(source);
            if (img.getWidth(this) > 200 || img.getHeight(this) > 200) {
                int width = img.getWidth(this);
                int height = img.getWidth(this);
                float scaleWidth = 200f / width;
                float scaleHeight = 200f / height;
                if (scaleWidth > scaleHeight) {
                    width = -1;
                    height = (int)(height * scaleHeight);
                } else {
                    width = (int)(width * scaleWidth);
                    height = -1;
                }
                img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            }
            
            popup = new JPopupMenu(); //creamos el menu saliente
            
            ver = new JMenuItem("Ver imagen");
            eliminar = new JMenuItem("Eliminar imagen");
            
            popup.add(ver);         //agregamos elemento
            popup.add(eliminar);    //.. y otro elemento
            
            String s = source.getAbsolutePath();
            
            /*Escucha de accion para que se pueda eliminar y ver las imagenes*/
            
            ver.addActionListener((ActionEvent ae) -> {
                controlador.verImagen(s);
            });
            
            eliminar.addActionListener((ActionEvent ae) -> {
                try {
                    controlador.eliminarImagen(s);
                } catch (IOException ex) {
                    Logger.getLogger(ImagePane.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //cerramos ventana para que se elimine y se abrira otra actualizada
                
//                im.dispose();
//                im.setVisible(false);

                im.imagesPane.removeAll();
                im.pintarPanel();       //rehacemos 
            });
            
            /*Añado el popup a la implementacion. Cada una de las imagenes tiene el suyo*/
            
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    popup.setVisible(true);
                    popup.show(e.getComponent(), e.getX(), e.getY()); //... mostramos el menu en la ubicacion del raton
                }
            });
            
        }
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            if (img != null) {
                g2d.drawImage(img, 0, 0, this);
            }
            g2d.dispose();
        }
        
        
    }