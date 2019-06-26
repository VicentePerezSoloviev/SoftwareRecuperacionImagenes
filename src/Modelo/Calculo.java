package Modelo;

import Exceptions.FileNotExistsException;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2Lab;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import static org.opencv.core.CvType.CV_32F;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public abstract class Calculo {
    
    /*public static void main (String args[]) throws FileNotExistsException {
        Calculo calculo = new Calculo();
        calculo.histogramaLAB(Calculo.cargarImagen("C:\\Users\\Vicente\\Documents\\DirectorioRaiz\\base de datos de colores\\plana.jpg"));
        
    }*/
    
    public static Mat cargarImagen(String path) throws FileNotExistsException{
        /*Cargo la libreria de opencv .dll que esta en el directorio del proyecto*/
        File imagen = new File(path);
        if (!imagen.exists()) {
            throw new FileNotExistsException();
        } 
        
        String opencvpath = System.getProperty("user.dir")+"\\dlls\\";
        System.out.println(opencvpath);
        String libPath = System.getProperty("java.library.path");
        
        System.out.println(System.getProperty("sun.arch.data.model"));
        
        System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
        
        /*Leo la imagen y la devuelvo como una Matriz*/
        return Imgcodecs.imread(path);
    }
    
    public static double aproximarDecimales (double num, int numDecimales) {
        int entero = (int) num;
        double resta = num - entero;
        resta = Math.pow(10, numDecimales) * resta;
        int enteroResta = (int) resta;
        return (double) entero + (enteroResta/Math.pow(10, numDecimales));
    }
    
    /*Histograma RGBA*/
    
    public Mat histogramaRGBA(Mat matrix, String string) {
        List<Mat> bgr_planes = new ArrayList<Mat>();
        bgr_planes.add(matrix);
        MatOfInt histSize = new MatOfInt(256);
        MatOfFloat histRange = new MatOfFloat(0f, 256f);
        Mat b_hist = new Mat(), g_hist = new Mat(), r_hist = new Mat();
        
        Imgproc.calcHist(bgr_planes, new MatOfInt(0), new Mat(), b_hist, histSize, histRange);
        Imgproc.calcHist(bgr_planes, new MatOfInt(1), new Mat(), g_hist, histSize, histRange);
        Imgproc.calcHist(bgr_planes, new MatOfInt(2), new Mat(), r_hist, histSize, histRange);
        
        double q = matrix.height()*matrix.width();
        for (int i=0; i<b_hist.size().height; i++){
            double a[] = b_hist.get(i, 0);
            b_hist.put(i, 0, a[0]/q);
        }
        for (int i=0; i<g_hist.size().height; i++){
            double a[] = g_hist.get(i, 0);
            g_hist.put(i, 0, a[0]/q);
        }
        for (int i=0; i<r_hist.size().height; i++){
            double a[] = r_hist.get(i, 0);
            r_hist.put(i, 0, a[0]/q);
        }
        Size rgbaSize = matrix.size();
        int hist_h = (int) matrix.size().height;
        float bin_w = (float)rgbaSize.width/256;
        Mat histImage = new Mat( rgbaSize, matrix.type(), new Scalar(0,0,0));            //CV_8UC3???
        Mat h=new Mat();
        
        for (int i=0; i<256; i++) {
            Point p1 = new Point(bin_w * (i - 1), hist_h - Math.round(b_hist.get(i - 1, 0)[0]));
            Point p2 = new Point(bin_w * i, hist_h - Math.round(b_hist.get(i, 0)[0]));
            Imgproc.line(histImage, p1, p2, new Scalar(255,0, 0), 3, 8, 0);
        }
        for (int i=0; i<256; i++) {
            Point p1 = new Point(bin_w * (i - 1), hist_h - Math.round(g_hist.get(i - 1, 0)[0]));
            Point p2 = new Point(bin_w * i, hist_h - Math.round(g_hist.get(i, 0)[0]));
            Imgproc.line(histImage, p1, p2, new Scalar(0,255, 0), 3, 8, 0);
        }
        for (int i=0; i<256; i++) {
            Point p1 = new Point(bin_w * (i - 1), hist_h - Math.round(r_hist.get(i - 1, 0)[0]));
            Point p2 = new Point(bin_w * i, hist_h - Math.round(r_hist.get(i, 0)[0]));
            Imgproc.line(histImage, p1, p2, new Scalar(0,0, 255), 3, 8, 0);
        }
        histImage.convertTo(histImage, CV_32F);
        //Imgcodecs.imwrite("C:\\Users\\Vicente\\Downloads\\histogramaRGB_" + string + ".png", histImage);
        return histImage;
    }
    
    /*Histograma completo LAB*/
    
    public Mat histogramaLAB (Mat matrix) {
        
        Imgproc.cvtColor(matrix, matrix, CV_BGR2Lab);
        
        List<Mat> bgr_planes = new ArrayList<Mat>();
        bgr_planes.add(matrix);
        
        MatOfInt histSize = new MatOfInt(256);
        MatOfFloat histRange = new MatOfFloat(0f, 256f);
        
        Mat l_hist = new Mat(), a_hist = new Mat(), b_hist = new Mat();
        
        Imgproc.calcHist(bgr_planes, new MatOfInt(0), new Mat(), b_hist, histSize, histRange);                        //luz
        Imgproc.calcHist(bgr_planes, new MatOfInt(1), new Mat(), a_hist, histSize, histRange);                        //A
        Imgproc.calcHist(bgr_planes, new MatOfInt(2), new Mat(), l_hist, histSize, histRange);                        //B
        
        double q = matrix.width()* matrix.height();
        for (int i=0; i<256; i++) {
            double a[] = b_hist.get(i,0);
            b_hist.put(i, 0, (a[0]/q) * 1000);
        }
        
        for (int i=0; i<256; i++) {
            double a[] = a_hist.get(i,0);
            a_hist.put(i, 0, (a[0]/q) * 1000);
        }
        
        for (int i=0; i<256; i++) {
            double a[] = l_hist.get(i,0);
            l_hist.put(i, 0, (a[0]/q) * 1000);
        }
        
        Size rgbaSize = new Size(2560, 1000);    //matrix.size();
        int hist_h = (int) rgbaSize.height;
        float bin_w = (float)rgbaSize.width/256;
        
        Mat histImage = new Mat( rgbaSize, matrix.type(), new Scalar(255,255,255));        //CV_8UC3???

        for (int i=0; i<256; i++) { //pintamos luz
            Point p1 = new Point(bin_w * (i - 1), (double) hist_h - l_hist.get(i - 1, 0)[0]);
            Point p2 = new Point(bin_w * i, hist_h - (double) l_hist.get(i, 0)[0]);
            Imgproc.line(histImage, p1, p2, new Scalar(255,0, 0), 4, 8, 0);
        }
        
        for (int i=0; i<256; i++) {     //pintamos A
            Point p1 = new Point(bin_w * (i - 1), (double) hist_h - a_hist.get(i - 1, 0)[0]);
            Point p2 = new Point(bin_w * i, (double) hist_h - a_hist.get(i, 0)[0]);
            Imgproc.line(histImage, p1, p2, new Scalar(0,255, 0), 4, 8, 0);
        }
        
        for (int i=0; i<256; i++) {     //pintamos B
            Point p1 = new Point(bin_w * (i - 1), (double) hist_h - b_hist.get(i - 1, 0)[0]);
            Point p2 = new Point(bin_w * i, (double) hist_h - b_hist.get(i, 0)[0]);
            Imgproc.line(histImage, p1, p2, new Scalar(0,0, 255), 4, 8, 0);
        }
        
        //Imgcodecs.imwrite("C:\\Users\\Vicente\\Downloads\\histograma.png", histImage);
        
        return histImage;
        
    }
    
    /*****************
     Funciones auxiliares que se usan para la comparacion de imagenes y por tanto histogramas
     * @param path string
     * @return mat
     * @throws Exceptions.FileNotExistsException
     ******************/
    
    /*Histogramas por componentes en LAB*/
    
    public Mat L_comp_LAB (String path) throws FileNotExistsException {            //luz
        Mat resul = cargarImagen(path);
        return __aux(resul, 0);
    }
    
    public Mat A_comp_LAB (String path) throws FileNotExistsException {            //Green - red
        Mat resul = cargarImagen(path);
        return __aux(resul, 1);
    }
    
    public Mat B_comp_LAB (String path) throws FileNotExistsException {            //Blue - yellow
        Mat resul = cargarImagen(path);
        return __aux(resul, 2);
    }
    
    private Mat __aux(Mat resul, int var){
        Imgproc.cvtColor(resul, resul, CV_BGR2Lab);
        
        List<Mat> bgr_planes = new ArrayList<Mat>();
        bgr_planes.add(resul);
        
        MatOfInt histSize = new MatOfInt(256);
        MatOfFloat histRange = new MatOfFloat(0f, 256f);
        
        Mat b_hist = new Mat();
        
        Imgproc.calcHist(bgr_planes, new MatOfInt(var), new Mat(), b_hist, histSize, histRange);             //B
        
        double q = resul.width()* resul.height();
        for (int i=0; i<b_hist.size().height; i++){
                double a[] = b_hist.get(i, 0);
                b_hist.put(i, 0, a[0]/q);
        }
        
        return b_hist;
    }
    
    public Mat comp_LAB (Mat resul, TiposHistogramaLAB tipo) {
        int var = -1;
        switch (tipo) {
            case L: var = 0; break;
            case A: var = 1; break;
            case B: var = 2; break;
        }
        List<Mat> bgr_planes = new ArrayList<Mat>();
        bgr_planes.add(resul);
        
        MatOfInt histSize = new MatOfInt(256);
        MatOfFloat histRange = new MatOfFloat(0f, 256f);
        
        Mat b_hist = new Mat();
        
        Imgproc.calcHist(bgr_planes, new MatOfInt(var), new Mat(), b_hist, histSize, histRange);             //B
        
        double q = resul.width()* resul.height();
        for (int i=0; i<b_hist.size().height; i++){
                double a[] = b_hist.get(i, 0);
                b_hist.put(i, 0, a[0]/q);
        }
        
        return b_hist;
    }
    
}
