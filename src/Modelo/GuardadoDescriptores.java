package Modelo;

import Exceptions.MatNotExistsFileException;
import Exceptions.NoSenseMatArrayListException;
import Exceptions.TypeNotExistsException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;

public class GuardadoDescriptores {
    private final DirectorioRaiz directorioRaiz;
    
    private BufferedReader br;
    private BufferedWriter bw;
    private FileWriter fw;
    private FileReader fr;
    
    /*Estructura de ficheros:
    L nombre
    A nombre
    B nombre
    H nombre
    V nombre
    */
    
    private final String separator;
    
    public GuardadoDescriptores() {
        this.directorioRaiz = new DirectorioRaiz();
        separator = "***";
    }
    
    private File getFile(String imagenPath, String directorioNombre) throws IOException {
        File file = new File(imagenPath);
        String nombre = file.getName();
        String name = directorioRaiz.obtenerPathDirectorio(directorioNombre) + "\\" + "_FicheroDescriptores" + nombre;
        int pos = name.lastIndexOf(".");
        if (pos > 0) {
            name = name.substring(0, pos);
        }
        File ficheroDescriptores = new File(name);
        return ficheroDescriptores;
    }
    
    public boolean deleteHistogramImage(String imagenPath, String directorioNombre) throws IOException {
        File file = new File (imagenPath);
        String name = file.getName();
        String nombre = directorioRaiz.obtenerPathDirectorio(directorioNombre) + "\\_FicheroDescriptores" + name;
        int pos = nombre.lastIndexOf(".");
        if (pos > 0) {
            nombre = nombre.substring(0, pos);
        }
        File fichDes = new File (nombre);
        System.out.println(nombre);
        if (fichDes.exists()) return fichDes.delete();
        else return false;
        
    }
    
    private boolean checkIfExistHistogram (String imagenPath, String directorioNombre, TiposHistogramaLAB type) throws IOException {
        File ficheroDescriptores = getFile (imagenPath, directorioNombre);
        if (!ficheroDescriptores.exists()) return false;        //si no esta el fichero es que no existe
        
        fr = new FileReader(ficheroDescriptores);
        br = new BufferedReader(fr);
        
        String linea;
        
        while ((linea = br.readLine()) != null) {
            if (linea.equals(type.toString() + " " + imagenPath)) {
                fr.close();
                br.close();
                return true;
            }
        }
        
        fr.close();
        br.close();
        
        return false;
    }
    
    /*Dentro del histograma LAB tenemos las tres componenetes L, A y B. Esta funcion permite guradar por separado cada una de ellas*/
    
    public void saveHistogram (String imagenPath, String directorioNombre, Mat histograma, TiposHistogramaLAB type) throws IOException, TypeNotExistsException{
        File ficheroDescriptores = getFile (imagenPath, directorioNombre);
        if (ficheroDescriptores.exists()) {
            //comprobar si existe
            boolean check = checkIfExistHistogram(imagenPath, directorioNombre, type);
            
            if (!check) {       //si no esta
                writeHistogram(ficheroDescriptores, type, imagenPath, histograma);
            }
        }
        else {
            //crear fichero y guardar MAT
            ficheroDescriptores.createNewFile();
            writeHistogram(ficheroDescriptores, type, imagenPath, histograma);
        }
        
    }
    
    private void writeHistogram(File ficheroDescriptores, TiposHistogramaLAB type, String imagenPath, Mat histograma) throws IOException {
        
        /*
            ***
            type path
            1,2,3,4,5
            6,7,8,9,10...
            ***
        */
        
        fw = new FileWriter(ficheroDescriptores, true);
        bw = new BufferedWriter(fw);
        bw.write(separator); 
        bw.newLine();
        bw.write(type.toString() + " " + imagenPath);
        bw.newLine();

        double a[];

        for (int i=0; i<histograma.height(); i++) {

            for (int j=0; j<histograma.width(); j++) {
                a = histograma.get(i, j);
                bw.write(String.valueOf(a[0]) + " ");
            }
            bw.newLine();
        }
        bw.write(separator);
        bw.newLine();
        bw.close();
        fw.close();
    }
    
    /*Pasando los argumentos de la imagen y el directorio, encontramos el histograma que deseamos
    En caso de que no exista, nos devuelve una excepcion de tipo MatNotExistsFile*/
    
    /*public static void main (String args[]) throws IOException, MatNotExistsFile, NoSenseMatArrayList {
        GuardadoDescriptores d = new GuardadoDescriptores();
        
        d.getHistogram("C:\\Users\\Vicente\\Documents\\DirectorioRaiz\\Base de datos de colores\\blancoNegro.png", "Base de datos de colores", TiposHistogramaLAB.L);
    }*/
    
    public Mat getHistogram (String imagenPath, String directorioNombre, TiposHistogramaLAB type) throws IOException, MatNotExistsFileException, NoSenseMatArrayListException {
        File ficheroDescriptores = getFile (imagenPath, directorioNombre);
        
        if (!checkIfExistHistogram(imagenPath, directorioNombre, type)) throw new MatNotExistsFileException(imagenPath);
        
        fr = new FileReader(ficheroDescriptores);
        br = new BufferedReader(fr);
        
        String linea;
        ArrayList<Double> arrayValores;
        ArrayList<ArrayList<Double>> mat = new ArrayList<>();
        double a;
        
        while ((linea = br.readLine()) != null) {
            if (linea.equals(type.toString() + " " + imagenPath)) {
                
                /*************/
                /*Metemos en una matriz de ArrayList todos los valores*/
                linea = br.readLine();      //leemos la siguiente linea para no analizar strings
                
                while (!linea.equals(separator)){       //leemos todas las lineas
                    arrayValores = new ArrayList<>();
                    a = Double.valueOf(linea);
                    arrayValores.add(a);
                    mat.add(arrayValores);  //metemos la fila de valores
                    linea = br.readLine();
                }
                
                Mat hist = ArrayListToMat(mat);
                
                /*************/
                
                fr.close();
                br.close();
                
                return hist;
            }
        }
        
        return null;
    }
    
    /*Funcion para pasar de matriz de arraylist a Mat */
    
    private Mat ArrayListToMat(ArrayList<ArrayList<Double>> array) throws NoSenseMatArrayListException {
        
        for (int i=0; i<array.size(); i++) {        //comprobamos que todos los arrays tienene el mismo tamano
            if (array.get(i).size() != array.get(0).size()) throw new NoSenseMatArrayListException();
        }
        
        int w = array.get(0).size(), h = array.size();
        
        Size size = new Size(w, h);
        
        String opencvpath = System.getProperty("user.dir")+"\\dlls\\";
        String libPath = System.getProperty("java.library.path");
        
        System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
        
        Mat histogram = new Mat(size, CvType.CV_64FC1);
        
        double lista[] = {0.0f};
        
        for (int i=0; i<h; i++) {
            for (int j=0; j<w; j++) {
                lista[0] = array.get(i).get(j);
                histogram.put(i, j, lista);
            }
        }
        
        return histogram;
    }
    
    
}
