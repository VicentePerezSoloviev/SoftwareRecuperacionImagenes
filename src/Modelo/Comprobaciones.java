
package Modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comprobaciones {
    
    private final DirectorioRaiz directorioRaiz;
    
    /*Comprobacion de que existe el directorio raiz y de que el fichero de informacion 
    existen y funcionan correctamente*/
    
    public boolean comprobar() throws IOException {
        
        /*Compriebo si existe el directorio Raiz*/
        
        File directorio = new File (directorioRaiz.obtenerDireccion());
        if(!directorio.exists()) return false;
        
        /*Compruebo si existe el fichero de informacion*/

        File fichero = new File (directorioRaiz.obtenerDireccionFichero());
        if (!fichero.exists()) {
            //El directorio existe pero el ficheor no
            
            File[] files = new File(directorioRaiz.obtenerDireccion()).listFiles((File pathname) -> {
                String name1 = pathname.getName().toLowerCase();
                return pathname.isFile();
            });
            
            if (files.length == 0) {
                /*El directorio esta vacio*/
                fichero.createNewFile();
            }
            else{       //el directorio tiene directorio
                
                /*Relleno con informacion recopilada de nuevo a dia de hoy*/
                
                FileWriter fw = new FileWriter(fichero, true);
                BufferedWriter bw = new BufferedWriter(fw);
                
                for (File file: files) {
                    bw.write(file.getName());
                    bw.newLine();
                    
                    bw.write("--- Esta base de datos ha sido recuperada en el inicio. No tiene descripcion ---");
                    bw.newLine();
                    
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date fecha = new Date();

                    bw.write(formato.format(fecha));
                    bw.newLine();
                    bw.newLine();
                    
                }
                
                bw.close();
                fw.close();
               
            }
        }
        
        return true;
    }
    
    public Comprobaciones() {
        this.directorioRaiz = new DirectorioRaiz();
    }
    
    /*Funcion para comprobar que sea un archivo imagen*/
    
    public boolean comprobarTerminacion (String name) {
        return (name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".gif") || name.endsWith(".jpeg") || name.endsWith(".bmp") 
                || name.endsWith(".JPG") || name.endsWith(".JPEG") );
    }
    
    
    

}
