package Controlador;

import Exceptions.DeletingFileException;
import Exceptions.DirectoryNotExists;
import Exceptions.RenamingDirectoryException;
import Exceptions.RenamingFileException;
import Modelo.DirectorioRaiz;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControladorEditar {
    
    private final JFrame ventanaEditar;
    private final String nombreInicial;
    private final String descripcionInicial;
    private final DirectorioRaiz directorioRaiz;
    
    public ControladorEditar (JFrame v, String nombre, String descripcion) {
        this.ventanaEditar = v;
        this.nombreInicial = nombre;
        this.descripcionInicial = descripcion;
        this.directorioRaiz = new DirectorioRaiz();
    }
    
    public void btnCancelar() {
        cerrar();
    }
    
    public void btnGuardar(String nombreNuevo, String descripcionNuevo) {
        int resul = -1;
        if (this.nombreInicial.equals(nombreNuevo) && this.descripcionInicial.equals(descripcionNuevo)){      //si no se ha cambiado, cierro y no tengo que modificar nada
            cerrar();
        }
        else if (nombreNuevo.isEmpty()) {
            JOptionPane.showMessageDialog(this.ventanaEditar, "Debe introducir un nombre para la base de datos", "Atencion", JOptionPane.WARNING_MESSAGE);
        }
        else {      //sino debo modificar fichero y directorios
            try {
                resul = this.directorioRaiz.editarBBDD(nombreInicial, nombreNuevo, descripcionInicial, descripcionNuevo);
            } catch (IOException e){
                JOptionPane.showMessageDialog(this.ventanaEditar, "Error inesperado gestionando el directorio", "Atencion", JOptionPane.WARNING_MESSAGE);
            } catch (DirectoryNotExists | RenamingDirectoryException | DeletingFileException | RenamingFileException ex) {
                Logger.getLogger(ControladorEditar.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this.ventanaEditar, ex.getMessage(), "Atencion", JOptionPane.WARNING_MESSAGE);
            }
            
            if (resul == 0) {
                JOptionPane.showMessageDialog(this.ventanaEditar, "El directorio se ha modificado con exito");
                cerrar();
            }
        }
        
    }
    
    private void cerrar() {
        this.ventanaEditar.setVisible(false);
        this.ventanaEditar.dispose();
    }
    
    
}
