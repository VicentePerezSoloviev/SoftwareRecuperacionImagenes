/*
Esta clase trata todos los accesos al directorio de bases de datos y el fichero de informacion
de las bases de datos.
Esta hecho a parte para que sea una forma mas modular de acceso
*/

package Modelo;

import Exceptions.DeletingDirectoryException;
import Exceptions.DeletingFileException;
import Exceptions.DirectoryNotExists;
import Exceptions.RenamingDirectoryException;
import Exceptions.RenamingFileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.util.ArrayList;

//OJO: cambiar direccion para que concuerde con el ordenador

public class DirectorioRaiz {
    private final String direccion = "C:\\direccionRaiz";
    private BufferedReader br;
    PrintWriter pw;
    private final String nombreFichero = "\\FicheroInfo";
    
    public String obtenerPathDirectorio(String nombreBBDD) throws IOException {
        return this.obtenerDireccion() + "\\" + nombreBBDD;
    }
    
    public String obtenerDireccion() throws IOException {
        String resul = null;
        try{
            br = new BufferedReader(new FileReader(direccion));
            String strLine;
            //Leemos fichero linea a linea
            while ((strLine = br.readLine()) != null)   {
              // copiamos el contenido en el string
              resul = strLine;
            }
            //Cerramos el fichero
            br.close();
            in.close();
            }catch (IOException e){         //capturamos excepcion
              System.err.println("Error: " + e.getMessage());
            }finally{
             in.close();
             br.close();
            }
        
        return resul;
    }
    
    public String obtenerDireccionFichero() throws IOException {
        String resul = obtenerDireccion();
        
        return resul + nombreFichero;
    }
    
    public ArrayList<BaseDeDatos> listarBases() throws IOException {
        ArrayList<BaseDeDatos> array = new ArrayList <BaseDeDatos> ();
        
        try{
            br = new BufferedReader(new FileReader(this.obtenerDireccionFichero()));
            String strLine;
            //Leemos fichero linea a linea
            String nombre, descripcion, fecha;
            BaseDeDatos bd;
            while ((strLine = br.readLine()) != null)   {
                //Leo las 4 lineas que corresponden a cada base de datos
                nombre = strLine;
                descripcion = br.readLine();
                fecha = br.readLine();
                br.readLine();      //leo linea en blanco

                bd = new BaseDeDatos (nombre, fecha, descripcion);
                array.add(bd);
            }   
            //Cerramos el fichero
            br.close();
            in.close();

        }catch (IOException e){         //capturamos excepcion
          System.err.println("Error: " + e.getMessage());
        }finally{
            try{ 
                in.close();
                br.close();
            }catch (IOException e){}
        }
        
        for (BaseDeDatos base: array) {
            base.setNumImagenes(this.obtenerImagenes(base.getNombre()).size());
            File dir = new File(this.obtenerPathDirectorio(base.getNombre()));
            String[] imageList = dir.list();
            int var = 0;
            for (String string: imageList) if (string.startsWith("_Fichero")) var++;
            base.setNumDescriptores(var);
        }

        return array;
    }
    
    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }   
    
    /*
    1. Error al borrar archivo original
    2. Error al renombrar el fichero temporar a original
    3. No se ha podido borrar el directorio
    
    0. Exito
    -1. Algun error
    */
    
    public int borrarBBDDFichero(String nombre) throws IOException, RenamingFileException, DeletingFileException, DeletingDirectoryException {
        
        /*Primero borramos el directorio y todo su contenido*/
        
        String n = this.obtenerDireccion() + "\\" + nombre;
        File file = new File (n);
        
        boolean b = deleteDirectory(file);
        if (!b) throw new DeletingDirectoryException();       //en caso de error no se borra ni el fichero ni se borra el directorio
        
        /*Despues modificamos el fichero*/
        
        String nombreFichero_ = this.obtenerDireccionFichero();
        File inFile = new File(nombreFichero_);
        
        //Construct the new file that will later be renamed to the original filename.
        File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

        br = new BufferedReader(new FileReader(inFile));
        pw = new PrintWriter(new FileWriter(tempFile));

        String line;

        //Leemos sobre el ficheor original y escribimos sobre el nuevo
        //a menos que el contenido coincida
        while ((line = br.readLine()) != null) {

            if (!line.trim().equals(nombre)) {
                pw.println(line);
                pw.flush();
            }
            else {      //entonces eliminamos las siguientes lineas con fecha y descripcion
                br.readLine();
                br.readLine();
                br.readLine();      //linea el blanco
            }
        }
        pw.close();
        br.close();
        
        /*Comprobaciones y renombrar y eliminar*/

        if (!inFile.delete()) throw new DeletingFileException();
        if (!tempFile.renameTo(inFile)) throw new RenamingFileException();
    
        return 0 ;
    }
    
    /*
    0. EXITO
    1. El directorio no existe
    3. Error al renombrar el directorio
    4. Error al abrir el fichero original de informaciones
    5. Error al renombrar el fichero temporal de informaciones
    */
    
    public int editarBBDD(String nombreIni, String nombreFin, String descripcionIni, String descripcionFin) throws FileNotFoundException, IOException, DeletingFileException, RenamingFileException, RenamingDirectoryException, DirectoryNotExists {
        //Busco el directorio y lo renombro
        
        File dir = new File (this.obtenerDireccion() + "\\" + nombreIni);
        if (! dir.exists()) throw new DirectoryNotExists();
        
        File dirNuevo = new File (this.obtenerDireccion() + "\\" + nombreFin);
        
        boolean flag = dir.renameTo(dirNuevo);
        if (!flag) throw new RenamingDirectoryException();
        
        //Abro el fichero y modifico la informacion
        
        String nombreFichero_ = this.obtenerDireccionFichero();
        File inFile = new File(nombreFichero_);
        File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
        
        br = new BufferedReader(new FileReader(inFile));
        pw = new PrintWriter(new FileWriter(tempFile));

        String line;

        //Leemos sobre el ficheor original y escribimos sobre el nuevo
        //a menos que el contenido coincida
        while ((line = br.readLine()) != null) {

            if (!line.trim().equals(nombreIni)) {
                pw.println(line);
                pw.flush();
            }
            else {      //coincide el nombre
                pw.println(nombreFin);
                pw.flush();
                
                pw.println(descripcionFin);
                pw.flush();
                
                br.readLine();
            }
        }
        pw.close();
        br.close();
        
        /*Comprobaciones*/
        
        if (!inFile.delete()) throw new DeletingFileException();       //Eliminamos el fichero original
        if (!tempFile.renameTo(inFile)) throw new RenamingFileException(); //Renombramos el nuevo fichero con el nombre de antes
            
        return 0;
    }
    
    
    
    /*Lectura de imagenes de un directorio*/
    //nombreDirectorio es relativo al diretorio raiz, no el path
    
    public ArrayList<String> obtenerImagenes(String nombreDirectorio) throws IOException {
        File file = new File(this.obtenerDireccion() + "\\" + nombreDirectorio);
        System.out.println(file.getAbsolutePath());
        String[] imageList = file.list();
        ArrayList<String> array= new ArrayList<>();
        Comprobaciones comprobaciones = new Comprobaciones();
        
        for (String imageList1 : imageList) {
            if (!imageList1.equals("FicheroInfo") && comprobaciones.comprobarTerminacion(imageList1)) {
                array.add(imageList1);
            }
        }
        
        return array;
    }
    
    
    
    
}
    
