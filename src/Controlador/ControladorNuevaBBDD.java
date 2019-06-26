package Controlador;

import Modelo.DirectorioRaiz;
import Vista.VNuevaBBDD;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.in;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControladorNuevaBBDD {
    JFrame VNuevaBBDD;
    DirectorioRaiz directorioRaiz;
    
    BufferedReader br;
    BufferedWriter bw;
    FileWriter fw;

    public ControladorNuevaBBDD(VNuevaBBDD aThis) {
        this.VNuevaBBDD = aThis;
        directorioRaiz = new DirectorioRaiz();
    }

    public void crear(String nombre, String descripcion) throws IOException {
        
        String direccion;
        
        if (nombre.isEmpty()) JOptionPane.showMessageDialog(VNuevaBBDD, "Debe introducir un nombre para la Base de datos");
        else if (descripcion.isEmpty()) JOptionPane.showMessageDialog(VNuevaBBDD, "Debe introducir una descripcion para la Base de datos");
        else {  //Si los campos están cubiertos
            
            direccion = directorioRaiz.obtenerDireccion();      //obtenemos la direccion del directorio raiz
            
            if (direccion == null) System.exit(0);

            File directorio = new File (direccion + "\\" + nombre);     //direccion del directorio que queremos crear

            if (directorio.exists()){       //comprobamos si existe el directorio
                JOptionPane.showMessageDialog(VNuevaBBDD, "El directorio ya existe");
            }
            else {
                directorio.mkdir();         //creamos el directorio

                //Introducimos en el fichero de informacion de Bases de datos la informacion
                
                try{    //Añadimos info al fichero
                    fw = new FileWriter(direccion + "\\FicheroInfo", true);
                    bw = new BufferedWriter(fw);

                    bw.write(nombre);
                    bw.newLine();
                    bw.write(descripcion);
                    bw.newLine();

                    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date fecha = new Date();

                    bw.write(formato.format(fecha));
                    bw.newLine();

                    bw.newLine();       //Introduzco una linea a mayores
                    
                    bw.close();
                    fw.close();

                    //more code
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                    System.out.println("error");
                    JOptionPane.showMessageDialog(VNuevaBBDD, "Ha habido un error añadiendo la informacion al fichero de informacion", "Atencion", JOptionPane.WARNING_MESSAGE);
                }finally{
                    try{ 
                        in.close();
                        fw.close();
                    }catch (IOException e){}
                }
                
                this.cerrarVentana(); //cerramos la ventana

                JOptionPane.showMessageDialog(VNuevaBBDD, "El directorio se ha creado con exito");
                
            }
        }
    }
    
    public void cerrarVentana() {
        this.VNuevaBBDD.setVisible(false);
        this.VNuevaBBDD.dispose();
    }
    
}
