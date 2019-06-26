package Modelo;

import Exceptions.DifferentSizesMatException;
import Exceptions.FileNotExistsException;
import Exceptions.MatNotExistsFileException;
import Exceptions.NoSenseMatArrayListException;
import Exceptions.TypeNotExistsException;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2Lab;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class CalculoForma extends Calculo{
    
    public CalculoForma () {
        super();
    }
    
    /*public static void main (String args[]) throws FileNotExistsException, DifferentSizesMatException, IOException, NoSenseMatArrayListException, TypeNotExistsException {
        CalculoForma cal = new CalculoForma();
        System.out.println(cal.calcularDiferenciaForma("C:\\DirectorioRaiz\\Base de datos de colores\\blancoNegro.png", "C:\\DirectorioRaiz\\Base de datos de colores\\blancoNegro.png", "Base de datos de colores"));
    }*/
    
    /*Esta funcion contruye una Matriz a partir de otra dada. Se compara cada  uno de 
    los pixeles con el de su vecino horizaontalmetne. Obtenemos una matriz con una fila 
    menos.*/
    
    private Mat calculoHorizontalForma(Mat matrix) {
        Size size = new Size(matrix.size().width, matrix.size().height  -1);
        Mat calculoHorizontal = new Mat(size, CvType.CV_8U);
        
        double pixel[], pixel_2[];
        
        for (int j=0; j<matrix.size().width; j++) {
            for (int i=1; i<matrix.size().height; i++){
                pixel = matrix.get(i, j);
                pixel_2 = matrix.get(i-1, j);
                double [] diferencia = {0};
                diferencia[0] = Math.sqrt(Math.pow(pixel_2[0] - pixel[0], 2) + Math.pow(pixel_2[1] - pixel[1], 2) + Math.pow(pixel_2[2] - pixel[2], 2));
                calculoHorizontal.put(i-1, j, diferencia);
            }
        }
        
        //la matriz devuelta tiene una columna menos
        return calculoHorizontal;
    }
    
    
    /*Esta funcion contruye una Matriz a partir de otra dada. Se compara cada  uno de 
    los pixeles con el de su vecino verticalmente. Obtenemos una matriz con una columna 
    menos.*/
    
    private Mat calculoVerticalForma(Mat matrix) {
        Size size = new Size(matrix.size().width - 1 , matrix.size().height);
        Mat calculoVertical = new Mat(size, CvType.CV_8U);
        
        double pixel[], pixel_2[];
        
        for (int j=0; j<matrix.size().height; j++) {
            for (int i=1; i<matrix.size().width; i++){
                pixel = matrix.get(j, i);
                pixel_2 = matrix.get(j, i-1);
                double [] diferencia = {0};
                diferencia[0] = Math.sqrt(Math.pow(pixel_2[0] - pixel[0], 2) + Math.pow(pixel_2[1] - pixel[1], 2) + Math.pow(pixel_2[2] - pixel[2], 2));
                calculoVertical.put(j, i-1, diferencia);
            }
        }
        //la matriz devuelta tiene una fila menos
        
        return calculoVertical;
    }
    
    /*Esta funcion a partir de dos histogramas iguales que suponen la matriz de 
    saltos, se calcula la diferencia entre ambas. Devuelve un valor*/
    
    private double diferenciaHistogramas (Mat im_1_L, Mat im_2_L) throws DifferentSizesMatException {
        double sum = 0;
        
        if (im_1_L != null && im_2_L != null && im_1_L.size().equals(im_2_L.size())) {
            double [] a;
            double [] b;
            
            for (int i=0; i<im_1_L.size().height; i++) {
                a = im_1_L.get(i, 0);
                b = im_2_L.get(i, 0);
                sum = sum + (a[0]*a[0]) + (b[0]*b[0]) - (2*a[0]*b[0]);
            }
        }else {
            throw new DifferentSizesMatException();
        }
        System.out.println((Math.sqrt(sum)));
        return Math.abs((Math.sqrt(sum)*100)-100);
    }
    
    /*Funcion para llamar desde otras clases que agrupa por pasos las funciones anteriores. En esta se puede
    añadir posibles mejoras, como diagonales*/
    
    public double calcularDiferenciaForma (String path_1, String path_2, String nombreBBDD) throws FileNotExistsException, DifferentSizesMatException, IOException, NoSenseMatArrayListException, TypeNotExistsException {
        GuardadoDescriptores guardadoDescriptores = new GuardadoDescriptores();
        
        /*Calculamos los histogramas por componentes de la primera imagen
        Tambien lo hacemos de la imagen pregunta, porque lo vamos a usar en las siguientes imagenes, y no tener que calcularlo de nuevo*/
        
        Mat im_1_hor, im_1_ver, im_2_hor, im_2_ver;
        
        /*Conseguimos descriptores para primera imagen*/
        
        im_1_hor = calculoMatrizFormaEspecifica(path_1, nombreBBDD, TiposHistogramaLAB.H);
        im_1_ver = calculoMatrizFormaEspecifica(path_1, nombreBBDD, TiposHistogramaLAB.V);
        
        im_2_hor = calculoMatrizFormaEspecifica(path_2, nombreBBDD, TiposHistogramaLAB.H);
        im_2_ver = calculoMatrizFormaEspecifica(path_2, nombreBBDD, TiposHistogramaLAB.V);
        System.out.println(path_1 + " " + path_2 + " ");
        /*Hacemos una media de ambos resultados y acotamos decimales*/
        return aproximarDecimales((diferenciaHistogramas(im_1_hor, im_2_hor) + diferenciaHistogramas(im_1_ver, im_2_ver))/2, 3);
    }
    
    /*Se calcula la matriz de horizontales o verticales.Si existe en fichero se obtiene de fichero*/
    
    public Mat calculoMatrizFormaEspecifica (String path, String nombreBBDD, TiposHistogramaLAB type) throws FileNotExistsException, IOException, NoSenseMatArrayListException, TypeNotExistsException {
        GuardadoDescriptores guardadoDescriptores = new GuardadoDescriptores();
        Mat resul;
        
        if (!type.equals(TiposHistogramaLAB.H) && !type.equals(TiposHistogramaLAB.V)) throw new TypeNotExistsException();   //no nos interesan otros tipos de histograma 
        
        try {
            resul = guardadoDescriptores.getHistogram(path, nombreBBDD, type);     //histograma de horizontales
            
        } catch (MatNotExistsFileException ex) {
            /*Cargamos las imagenes en matrices*/
            Mat im2 = cargarImagen(path);

            /*Pasamos las imagenes a espacio LAB*/
            Imgproc.cvtColor(im2, im2, CV_BGR2Lab);

            /*Calculamos las matrices resutantes de comparaciones horizontales y verticales*/
            Mat hor_mat_2;
            
            if (type.equals(TiposHistogramaLAB.H)) hor_mat_2 = calculoHorizontalForma(im2);     //caso hor
            else hor_mat_2 = calculoVerticalForma(im2);                                         //caso ver

            /*Obtenemos los histogramas*/ 
            resul = comp_LAB(hor_mat_2, TiposHistogramaLAB.L);
            
            /*Guardamos para la proxima*/
            guardadoDescriptores.saveHistogram(path, nombreBBDD, resul, type);
        }
        
        return resul;
    }
    
}
