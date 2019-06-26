package Controlador;

import Exceptions.FileNotExistsException;
import Exceptions.NoSenseMatArrayListException;
import Exceptions.TypeNotExistsException;
import Modelo.DirectorioRaiz;
import Modelo.BaseDeDatos;
import Modelo.CalculoColor;
import Modelo.CalculoForma;
import Modelo.GuardadoDescriptores;
import Modelo.TiposHistogramaLAB;
import Vista.ImageGrid;
import Vista.VCalculoPregunta;
import Vista.VConfirmacion;
import Vista.VEditar;
import Vista.VNuevaBBDD;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.opencv.core.Mat;

public class ControladorGestion{
    private final JFrame VGestion;
    private final DirectorioRaiz directorioRaiz;

    public ControladorGestion(JFrame vges){
        this.VGestion = vges;
        directorioRaiz = new DirectorioRaiz();
    }

    public void NuevaBBDD () {
        //Abrimos la ventana que gestiona la nueva BBDD
        VNuevaBBDD ventanaNuevaBBDD = new VNuevaBBDD((Vista.VGestion) this.VGestion);
        ventanaNuevaBBDD.setVisible(true);
    }
    
    public void EliminarBBDD (String nombre) {
        //Abrimos la ventana que pide confirmacion
        VConfirmacion vconfirmacion = new VConfirmacion((Vista.VGestion) this.VGestion, nombre);
        vconfirmacion.setVisible(true);
    }
    
    public void EditarBBDD (String nombre, String descripcion) {
        //Abrimos la ventana de Edicion de la BBDD
        VEditar veditar = new VEditar((Vista.VGestion) this.VGestion, nombre, descripcion);
        veditar.setVisible(true);
    }
    
    public void ListadoImagenes (String nombre) throws IOException {
        //Abrimos las clases que gestionan el listado de imagenes y demas
        if (directorioRaiz.obtenerImagenes(nombre).size() > 0){
            String path = directorioRaiz.obtenerDireccion() + "\\" + nombre;    //path BBDD
            ImageGrid imagen = new ImageGrid(path, nombre, VGestion);
        }
        else {
            JOptionPane.showMessageDialog(this.VGestion, "La base de datos seleccionada no contiene imagenes");
        }
    }
    
    public ArrayList <BaseDeDatos> ListarBasesDeDatos() throws IOException {
        //Devuelvo el array con las BBDD
        return this.directorioRaiz.listarBases();
    }
    
    public void cerrarVentana() {
        this.VGestion.setVisible(false);
        this.VGestion.dispose();
        System.exit(1);
    }

    public void anadirImagen(String nombreBBDD) throws IOException {
        ControladorAnadirImagen controladorAnadir = new ControladorAnadirImagen(this.VGestion);
        controladorAnadir.anadirImagen(nombreBBDD);
    }

    public void CalcularPregunta(String nombre) throws IOException {
        String string = directorioRaiz.obtenerPathDirectorio(nombre);
        File dir = new File (string);
        if (dir.listFiles().length > 0) {
            VCalculoPregunta vCalculo = new VCalculoPregunta(this.VGestion, nombre);
            vCalculo.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(this.VGestion, "La base de datos seleccionada no contiene imagenes para realizar la comparacion");
        }
        
    }

    public void CalcularDescriptores(String nombreBBDD) throws IOException, InterruptedException {
        /*Recorremos todas las imagenes de la base de datos y calculamos sus compnentes LAB y las metemos en el ficheor que creamos si no existe*/
        String string = directorioRaiz.obtenerPathDirectorio(nombreBBDD);
        File dir = new File (string);
        if (dir.listFiles().length <= 0) {
            JOptionPane.showMessageDialog(this.VGestion, "La base de datos seleccionada no contiene imagenes para calcular sus descriptores");
            return;
        }
        
        ArrayList <String> imagenes = this.directorioRaiz.obtenerImagenes(nombreBBDD);
        GuardadoDescriptores guardadoDescriptores =  new GuardadoDescriptores();
        CalculoColor calculoColor = new CalculoColor();
        CalculoForma calculoForma = new CalculoForma();
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
                
                mat = calculoForma.calculoMatrizFormaEspecifica(directorioRaiz.obtenerPathDirectorio(nombreBBDD) + "\\" + imagenBBDD, nombreBBDD, TiposHistogramaLAB.H);
                guardadoDescriptores.saveHistogram(directorioRaiz.obtenerPathDirectorio(nombreBBDD) + "\\" + imagenBBDD, nombreBBDD, mat, TiposHistogramaLAB.H);
                
                mat = calculoForma.calculoMatrizFormaEspecifica(directorioRaiz.obtenerPathDirectorio(nombreBBDD) + "\\" + imagenBBDD, nombreBBDD, TiposHistogramaLAB.V);
                guardadoDescriptores.saveHistogram(directorioRaiz.obtenerPathDirectorio(nombreBBDD) + "\\" + imagenBBDD, nombreBBDD, mat, TiposHistogramaLAB.V);
                
            } catch (FileNotExistsException | TypeNotExistsException ex) {
                Logger.getLogger(ControladorGestion.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this.VGestion, "Ha habido algun problema cargando los descriptores de las imagenes", "Atencion", JOptionPane.WARNING_MESSAGE);
            } catch (NoSenseMatArrayListException ex) {
                Logger.getLogger(ControladorGestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        JOptionPane.showMessageDialog(this.VGestion, "Se han calculado los descriptores de las imagenes con exito");
    }
}
