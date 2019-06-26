package Controlador;

import Modelo.DirectorioRaiz;
import Vista.VHistograma;
import java.io.File;
import java.io.IOException;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;


public class ControladorHistograma {
    DirectorioRaiz directorioRaiz;
    
    public ControladorHistograma (Mat img) throws IOException {
        directorioRaiz = new DirectorioRaiz();
        Imgcodecs.imwrite(directorioRaiz.obtenerDireccion() + "\\img.png", img);
        File image = new File (directorioRaiz.obtenerDireccion() + "\\img.png");
        
        VHistograma vhistograma = new VHistograma(image);
        vhistograma.setVisible(true);
        
        //image.delete();
    }
    
    
}
