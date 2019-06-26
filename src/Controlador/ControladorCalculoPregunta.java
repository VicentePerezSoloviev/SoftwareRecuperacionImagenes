package Controlador;

import Exceptions.DifferentSizesMatException;
import Exceptions.FileNotExistsException;
import Exceptions.NoSenseMatArrayListException;
import Exceptions.TypeNotExistsException;
import Modelo.DirectorioRaiz;
import Modelo.Comprobaciones;
import Modelo.BaseDeDatos;
import Modelo.CalculoColor;
import Modelo.CalculoForma;
import Modelo.GuardadoDescriptores;
import Modelo.Imagen;
import Modelo.TiposHistogramaLAB;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Mat;

public class ControladorCalculoPregunta {
    private final JFrame VCalculoPregunta;
    private final DirectorioRaiz directorioRaiz;
    private final CalculoColor calculoColor;
    private final CalculoForma calculoForma;
    Comprobaciones Comprobaciones;
    private String pathImagenPregunta;
    
    private final String nombreBBDD;
    
    
    public ControladorCalculoPregunta (JFrame v, String nombre) {
        this.VCalculoPregunta = v;
        this.directorioRaiz = new DirectorioRaiz();
        this.Comprobaciones = new Comprobaciones();
        this.nombreBBDD = nombre;
        this.calculoColor = new CalculoColor();
        this.calculoForma = new CalculoForma();
    }
    
    public void cerrarVentana(){
        this.VCalculoPregunta.setVisible(false);
        this.VCalculoPregunta.dispose();
    }
    
    public Imagen imagenBBDD(JLabel label) throws IOException{
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg", "bmp", "JPG", "JPEG");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        fc.setCurrentDirectory(new File(this.directorioRaiz.obtenerDireccion() + "\\" + this.nombreBBDD));
        Imagen imagen = null;
        
        int returnVal = fc.showOpenDialog(this.VCalculoPregunta);
        switch (returnVal) {
            case JFileChooser.APPROVE_OPTION:
                File file = fc.getSelectedFile();
                if (file.exists() && Comprobaciones.comprobarTerminacion(file.getName())){
                    
                    /*Comprobamos que el fichero existe en las bases de datos*/
                    
                    String path = file.getAbsolutePath(), BBDDpadre = null;
                    File ficherotemp;
                    
                    ArrayList<BaseDeDatos> listaBBDD = this.directorioRaiz.listarBases();
                    
                    for (BaseDeDatos b: listaBBDD) {        //recorro todas las bases de datos para comprobar si esta dentro de estas
                        ficherotemp = new File(this.directorioRaiz.obtenerDireccion() + "\\" + b.getNombre() + "\\" + file.getName());
                        if (ficherotemp.exists()){      //existe
                            BBDDpadre = b.getNombre();
                        }
                    }
                    
                    /*if(BBDDpadre == null) {
                        JOptionPane.showMessageDialog(VCalculoPregunta, "La imagen no pertenece a ninguna BBDD del sistema", "Atencion", JOptionPane.WARNING_MESSAGE);
                    }
                    else{*/
                        this.pathImagenPregunta = file.getAbsolutePath();
                        /*Extraigo info de imagen */
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");     //formato fecha
                        String modificacion = sdf.format(file.lastModified());
                        
                        imagen = new Imagen (file.getName(), file.getAbsolutePath(), modificacion);       //imagen para devolver y cubrir campos
                        
                        /*Cargamos la imagen en el label*/
                        
                        BufferedImage img = null;
                        try {
                            img = ImageIO.read(new File(file.getAbsolutePath()));
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(VCalculoPregunta, "Ha habido un error leyendo la imagen", "Atencion", JOptionPane.WARNING_MESSAGE);
                        }
                        
                        @SuppressWarnings("null")
                        Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon imageIcon = new ImageIcon(dimg);
                        label.setIcon(imageIcon);
                        
                    //}
                }
                
                else{
                    JOptionPane.showMessageDialog(VCalculoPregunta, "La imagen seleccionada no existe o no se acepta esa extension", "Atencion", JOptionPane.WARNING_MESSAGE);
                }   
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            default:
                JOptionPane.showMessageDialog(VCalculoPregunta, "Ha habido un error abriendo el selector de ficheros", "Atencion", JOptionPane.WARNING_MESSAGE);
                break;
        }
        
        return imagen;
    }

    public HashMap <String, Double> calcularRankingColor() throws IOException, DifferentSizesMatException, FileNotExistsException, TypeNotExistsException {
        ArrayList <String> imagenes = this.directorioRaiz.obtenerImagenes(nombreBBDD);
        HashMap <String, Double> hash = new HashMap<>();
        double valor;
        
        for (String imagenBBDD: imagenes) {
                valor = this.calculoColor.comparacionTotal(pathImagenPregunta, directorioRaiz.obtenerPathDirectorio(this.nombreBBDD) + "\\" + imagenBBDD, this.nombreBBDD);
                hash.put(imagenBBDD, valor);
            
        }
        
        return hash;      //lo devolvemos ordenado
    }
    
    public HashMap <String, Double> calcularRankingForma() throws IOException, FileNotExistsException, DifferentSizesMatException, NoSenseMatArrayListException, TypeNotExistsException {
        ArrayList <String> imagenes = this.directorioRaiz.obtenerImagenes(nombreBBDD);
        HashMap <String, Double> hash = new HashMap<>();
        double valor;
        
        for (String imagenBBDD: imagenes) {
                valor = this.calculoForma.calcularDiferenciaForma(pathImagenPregunta, directorioRaiz.obtenerPathDirectorio(this.nombreBBDD) + "\\" + imagenBBDD, this.nombreBBDD);
                hash.put(imagenBBDD, valor);
        }
        
        return hash;      //lo devolvemos ordenado
    }
    
    public HashMap<String, Double> calcularRankingFormaColor() throws IOException, FileNotExistsException, DifferentSizesMatException, NoSenseMatArrayListException, TypeNotExistsException {
        ArrayList <String> imagenes = this.directorioRaiz.obtenerImagenes(nombreBBDD);
        HashMap <String, Double> hash = new HashMap<>();
        double valor;
        
        for (String imagenBBDD: imagenes) {
                valor = this.calculoForma.calcularDiferenciaForma(pathImagenPregunta, directorioRaiz.obtenerPathDirectorio(this.nombreBBDD) + "\\" + imagenBBDD, this.nombreBBDD);
                valor = valor + this.calculoColor.comparacionTotal(pathImagenPregunta, directorioRaiz.obtenerPathDirectorio(this.nombreBBDD) + "\\" + imagenBBDD, this.nombreBBDD);
                hash.put(imagenBBDD, calculoColor.aproximarDecimales(valor/2, 3));      //se hace la media de ambos
        }
        
        return hash;      //lo devolvemos ordenado
    }
    
    public ArrayList<String> sortHashMapByValues(HashMap<String, Double> passedMap) {
        ArrayList<String> mapKeys = new ArrayList<>(passedMap.keySet());
        ArrayList<Double> mapValues = new ArrayList<>(passedMap.values());
        
        Collections.sort(mapValues);
        Collections.reverse(mapValues);     //mejor en descreciente
        
        Collections.sort(mapKeys);
        Collections.reverse(mapKeys);       //mejor en decreciente

        HashMap<String, Double> sortedMap = new HashMap<>();
        ArrayList<String> ordenados= new ArrayList<>();

        Iterator<Double> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Double val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Double comp1 = passedMap.get(key);
                Double comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    ordenados.add(key);
                    break;
                }
            }
        }
        
        return ordenados;
    }

    public void mostrarHistograma() throws FileNotExistsException, IOException {
        Mat histogram = this.calculoColor.histogramaLAB(CalculoColor.cargarImagen(this.pathImagenPregunta));
        ControladorHistograma histograma = new ControladorHistograma(histogram);
    }
    
    public void CalcularDescriptores() throws IOException, InterruptedException {
        /*Recorremos todas las imagenes de la base de datos y calculamos sus compnentes LAB y las metemos en el ficheor que creamos si no existe*/
        
        ArrayList <String> imagenes = this.directorioRaiz.obtenerImagenes(nombreBBDD);
        GuardadoDescriptores guardadoDescriptores =  new GuardadoDescriptores();
        CalculoColor calculoColor = new CalculoColor();
        Mat mat;
        
        for (String imagenBBDD: imagenes) {
            //calculamos para cada imagen las tres componentes y las guardamos en su fichero correspondiente
            try {
                /*Componente L*/
                mat = calculoColor.L_comp_LAB(directorioRaiz.obtenerPathDirectorio(nombreBBDD) + "\\" + imagenBBDD);
                guardadoDescriptores.saveHistogram(directorioRaiz.obtenerPathDirectorio(nombreBBDD) + "\\" + imagenBBDD, nombreBBDD, mat, TiposHistogramaLAB.L);
                
                /*Componente A*/
                mat = calculoColor.A_comp_LAB(directorioRaiz.obtenerPathDirectorio(nombreBBDD) + "\\" + imagenBBDD);
                guardadoDescriptores.saveHistogram(directorioRaiz.obtenerPathDirectorio(nombreBBDD) + "\\" + imagenBBDD, nombreBBDD, mat, TiposHistogramaLAB.A);
                
                /*Componente B*/
                mat = calculoColor.B_comp_LAB(directorioRaiz.obtenerPathDirectorio(nombreBBDD) + "\\" + imagenBBDD);
                guardadoDescriptores.saveHistogram(directorioRaiz.obtenerPathDirectorio(nombreBBDD) + "\\" + imagenBBDD, nombreBBDD, mat, TiposHistogramaLAB.B);
                
            } catch (FileNotExistsException | TypeNotExistsException ex) {
                Logger.getLogger(ControladorGestion.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this.VCalculoPregunta, "Ha habido algun problema cargando los descriptores de las imagenes", "Atencion", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        JOptionPane.showMessageDialog(this.VCalculoPregunta, "Se han calculado los descriptores de las imagenes con exito");
    }

    
    
}
