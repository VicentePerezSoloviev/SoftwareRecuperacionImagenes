package Controlador;

import Modelo.Color;
import Modelo.DirectorioRaiz;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControladorAnadirImagen {
    
    private final JFrame vPrincipal;
    private final DirectorioRaiz directorioRaiz;
    
    public ControladorAnadirImagen (JFrame vPrin) {
        this.vPrincipal = vPrin;
        this.directorioRaiz = new DirectorioRaiz();
    }

    /*Funcion para copiar el contenido de un fichero (la imagen) a otro fichero*/
    
    void anadirImagen(String nombreBBDD) throws IOException {
        String direccionBBDD = directorioRaiz.obtenerDireccion() + "\\" + nombreBBDD;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg", "bmp", "JPG", "JPEG");
        
        JFileChooser fc = new JFileChooser();
        boolean flag = true;
        fc.setFileFilter(filter);
        fc.setCurrentDirectory(new File(directorioRaiz.obtenerDireccion()));
        fc.setMultiSelectionEnabled(true);      //multiseleccion habilitada
        fc.setBackground(Color.colorFondo);
        
        int returnVal = fc.showOpenDialog(vPrincipal);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            
            //File file = fc.getSelectedFile();
            File[] files = fc.getSelectedFiles();
            
            for (File file: files){
                if (file.exists()){
                    File fileCopia = new File (direccionBBDD + "\\" + file.getName());
                    if (!fileCopia.exists()) fileCopia.createNewFile();     //si no existe, se crea 
                    else {
                        JOptionPane.showMessageDialog(vPrincipal, "La imagen seleccionada ya existe", "Atencion", JOptionPane.WARNING_MESSAGE);
                        flag = false;
                    }
                    if (flag){
                        FileChannel source = new FileInputStream(file.getAbsolutePath()).getChannel();;
                        FileChannel destination = new FileOutputStream(fileCopia).getChannel();
                        
                        if (destination != null && source != null) {
                            destination.transferFrom(source, 0, source.size());
                        }
                        if (source != null) {
                            source.close();
                        }
                        if (destination != null) {
                            destination.close();
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(vPrincipal, "Ha habido un error abriendo el archivo que se ha seleccionado", "Atencion", JOptionPane.WARNING_MESSAGE);
                    flag = false;
                }
            }
            
            if (flag) {
                if (files.length==1) JOptionPane.showMessageDialog(vPrincipal, "Se ha importado la imagen con exito");
                else if (files.length > 1) JOptionPane.showMessageDialog(vPrincipal, "Se han importado las imagenes con exito");
            }
            
        }
        
        
    }
    
    
    
    
}
