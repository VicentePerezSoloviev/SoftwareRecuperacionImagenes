package Controlador;

import Modelo.DirectorioRaiz;
import Modelo.GuardadoDescriptores;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ControladorListadoImagenes {
    private final DirectorioRaiz directorioRaiz;
    private final String nombre;
    
    public ControladorListadoImagenes (String nombre) {
        directorioRaiz = new DirectorioRaiz();
        this.nombre = nombre;
    }
    
    public void verImagen (String source) {
        /*Ver imagen*/
                    
        JFrame frame = new JFrame (source);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BufferedImage img = null;

        try{
            img = ImageIO.read(new File(source));
        }catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Ha habido un error abriendo la imagen", "Atencion", JOptionPane.WARNING_MESSAGE);
        }
        
        JLabel lbl = new JLabel();
        lbl.setIcon(new ImageIcon(img.getScaledInstance(300, 300, 300)));
        frame.getContentPane().add(lbl, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
                    
    }
    
    public void eliminarImagen (String source) throws IOException {
        int conf = JOptionPane.showConfirmDialog (null, "Esta seguro de que desea eliminar la imagen? ", "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

        if (conf == JOptionPane.YES_OPTION) {           //si
            File file = new File (source);
            if (file.delete()) JOptionPane.showMessageDialog(null, "Se ha eliminado la imagen con exito");

            // Eliminamos tambien el fichero de descriptores de la imagen
            
            GuardadoDescriptores guardadoDescriptores = new GuardadoDescriptores();
            guardadoDescriptores.deleteHistogramImage(source, nombre);
            
        }
    }
   
}
