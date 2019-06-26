package Modelo;

import Exceptions.DifferentSizesMatException;
import Exceptions.FileNotExistsException;
import Exceptions.MatNotExistsFileException;
import Exceptions.NoSenseMatArrayListException;
import Exceptions.TypeNotExistsException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.Mat;

public class CalculoColor extends Calculo {
    
    public CalculoColor() {
        super();
    }
    
    /*Esta funcion extrae un histograma del espacio de color LAB
      Las dimensiones del histograma depende del tamano de la imagen, por lo que no es valido 
      para realizar comparafiones entre imagenes
      Para realizar comparaciones usar las auxiliares, que usan valores normalizado*/
    
    /*public static void main (String args[]) throws DifferentSizesMat, FileNotExists {
        CalculoColor cal = new CalculoColor();
        System.out.println(cal.comparacionTotal("C:\\Users\\Vicente\\Documents\\DirectorioRaiz\\Base de datos de colores\\blancoNegro.png", "C:\\Users\\Vicente\\Documents\\DirectorioRaiz\\Base de datos de colores\\negro.png", "Base de datos de colores"));
    }*/

    /*
    CASO:
        - 0 : luz
        - 1 : A
        - 2 : B
    */
    private double comparacion(String im_1, String im_2, int caso, String directorio) throws DifferentSizesMatException, FileNotExistsException, IOException, NoSenseMatArrayListException, TypeNotExistsException {
        double resul = -1, a[], b[];
        Mat imagen_1 = null;
        Mat imagen_2 = null;
        
        GuardadoDescriptores guardadoDescriptores = new GuardadoDescriptores();
        
        switch (caso) {
            case 0:     //componente L
        
                try {   //imagen 1
                    imagen_1 = guardadoDescriptores.getHistogram(im_1, directorio, TiposHistogramaLAB.L);       //intentamos recuperarlo del fichero de descriptores
                } catch (MatNotExistsFileException ex) {
                    imagen_1 = L_comp_LAB(im_1);    //calculamos el descriptor
                    guardadoDescriptores.saveHistogram(im_1, directorio, imagen_1, TiposHistogramaLAB.L);       //guardamos el descriptor
                }
                
                try {   //imagen 2
                    imagen_2 = guardadoDescriptores.getHistogram(im_2, directorio, TiposHistogramaLAB.L);       //intentamos recuperarlo del fichero de descriptores
                } catch (MatNotExistsFileException ex) {
                    imagen_2 = L_comp_LAB(im_2);    //calculamos el descriptor
                    guardadoDescriptores.saveHistogram(im_2, directorio, imagen_2, TiposHistogramaLAB.L);       //guardamos el descriptor
                }
                
                break;
                
            case 1:         //componente A
                
                try {   //imagen 1
                    imagen_1 = guardadoDescriptores.getHistogram(im_1, directorio, TiposHistogramaLAB.A);       //intentamos recuperarlo del fichero de descriptores
                } catch (MatNotExistsFileException ex) {
                    imagen_1 = A_comp_LAB(im_1);    //calculamos el descriptor
                    guardadoDescriptores.saveHistogram(im_1, directorio, imagen_1, TiposHistogramaLAB.A);       //guardamos el descriptor
                }
                
                try {   //imagen 2
                    imagen_2 = guardadoDescriptores.getHistogram(im_2, directorio, TiposHistogramaLAB.A);       //intentamos recuperarlo del fichero de descriptores
                } catch (MatNotExistsFileException ex) {
                    imagen_2 = A_comp_LAB(im_2);    //calculamos el descriptor
                    guardadoDescriptores.saveHistogram(im_2, directorio, imagen_2, TiposHistogramaLAB.A);       //guardamos el descriptor
                }
                
                break;
                
            case 2:         //componente B
                
                try {   //imagen 1
                    imagen_1 = guardadoDescriptores.getHistogram(im_1, directorio, TiposHistogramaLAB.B);       //intentamos recuperarlo del fichero de descriptores
                } catch (MatNotExistsFileException ex) {
                    imagen_1 = B_comp_LAB(im_1);    //calculamos el descriptor
                    guardadoDescriptores.saveHistogram(im_1, directorio, imagen_1, TiposHistogramaLAB.B);       //guardamos el descriptor
                }
                
                try {   //imagen 2
                    imagen_2 = guardadoDescriptores.getHistogram(im_2, directorio, TiposHistogramaLAB.B);       //intentamos recuperarlo del fichero de descriptores
                } catch (MatNotExistsFileException ex) {
                    imagen_2 = B_comp_LAB(im_2);    //calculamos el descriptor
                    guardadoDescriptores.saveHistogram(im_2, directorio, imagen_2, TiposHistogramaLAB.B);       //guardamos el descriptor
                }
                
                break;
                
        }
        
        if (imagen_1 != null && imagen_2 != null && imagen_1.size().equals(imagen_2.size())) {
            double sum = 0;
            
            for (int i=0; i<imagen_1.size().height; i++) {
                a = imagen_1.get(i, 0);
                b = imagen_2.get(i, 0);
                sum = sum + (a[0]*a[0]) + (b[0]*b[0]) - (2*a[0]*b[0]);
            }
            resul = Math.sqrt(sum);
        }else {
            throw new DifferentSizesMatException();
        }
        
        return resul;
    }
    
    public double comparacionTotal (String im_1, String im_2, String directorio) throws DifferentSizesMatException, FileNotExistsException, TypeNotExistsException {
        double resul = 0;
        
        for (int i=0; i<3; i++) {
            try {
                resul = resul + comparacion(im_1, im_2, i, directorio);
            } catch (IOException | NoSenseMatArrayListException | TypeNotExistsException ex) {
                Logger.getLogger(CalculoColor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        resul = ((resul /6.0) * 100) - 100;     //lo queremos sobre 100%, no sobre 6
        
        return Math.abs(aproximarDecimales(resul, 3));         //para aproximar decimales
    }
    
    
}
