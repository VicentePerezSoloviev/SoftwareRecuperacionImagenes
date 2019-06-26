package Vista;

import Modelo.Comprobaciones;
import Controlador.ControladorListadoImagenes;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public final class TestPane extends JPanel {

    JPanel imagesPane;
    
    private final String path;
    private final ControladorListadoImagenes controlador;
    
    public TestPane(ControladorListadoImagenes controlador, String path) {
        this.path = path;
        this.controlador = controlador;
        
        setLayout(new BorderLayout());
        imagesPane = new JPanel(new WrapLayout());
        add(new JScrollPane(imagesPane));
        
        imagesPane.removeAll();
        
        pintarPanel ();
        
        imagesPane.revalidate();
        imagesPane.repaint();
    }
        
    /*Funcion que usaremos para pintar el panel de nuevo.
    Cuando se borre una imagen se llama y vuelve a pintarlas todas*/
    
    public void pintarPanel(){
        Comprobaciones comprobaciones = new Comprobaciones();
        
        File[] files = new File(path).listFiles((File pathname) -> {
            String name1 = pathname.getName().toLowerCase();
            return pathname.isFile() && comprobaciones.comprobarTerminacion(name1);
        });
        
        for (File file : files) {
            ImagePane pane = null;
            
            try {
                pane = new ImagePane(controlador, file, this);
            } catch (IOException ex) {
                Logger.getLogger(TestPane.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            imagesPane.add(pane);
        }
    }
    
}
