package Controlador;

import Exceptions.DeletingDirectoryException;
import Exceptions.DeletingFileException;
import Exceptions.RenamingFileException;
import Modelo.DirectorioRaiz;
import Vista.VConfirmacion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControladorConfirmacion {
    JFrame VConfirmacion;
    DirectorioRaiz dirRaiz;
    String nombre;

    public ControladorConfirmacion(VConfirmacion aThis, String nombre) {
        this.VConfirmacion = aThis;
        this.dirRaiz = new DirectorioRaiz();
        this.nombre = nombre;
    }
    
    public void accionSi(){     //tratamos de eliminar la base de datos
        try {
            int conf_ = this.dirRaiz.borrarBBDDFichero(this.nombre);
            if (conf_ == 0) {
                JOptionPane.showMessageDialog(VConfirmacion, "Se ha eliminado la base de datos con exito");
            }else if (conf_ == -1) {
                JOptionPane.showMessageDialog(VConfirmacion, "Ha ocurrido un error inesperado", "Atencion", JOptionPane.WARNING_MESSAGE);
            }
                
            this.cerrarVentanaConfirmacion();   //cerramos la ventana
            
        } catch(IOException e) {
            JOptionPane.showMessageDialog(VConfirmacion, "Ha habido un error obteniendo direccion del directorio raiz: " + e.getMessage(), "Atencion", JOptionPane.WARNING_MESSAGE);
        } catch (RenamingFileException | DeletingFileException | DeletingDirectoryException ex) {
            Logger.getLogger(ControladorConfirmacion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(VConfirmacion, ex.getMessage(), "Atencion", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void accionNo(){     //cerramos la ventana
        this.cerrarVentanaConfirmacion();
    }
    
    private void cerrarVentanaConfirmacion() {
        VConfirmacion.setVisible(false);
        VConfirmacion.dispose();
    }
}
