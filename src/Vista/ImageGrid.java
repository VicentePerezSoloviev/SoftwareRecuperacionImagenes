package Vista;

import Controlador.ControladorListadoImagenes;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ImageGrid {
    
    private final ControladorListadoImagenes controlador;
    
    public ImageGrid(String path, String nombre, JFrame vGestion) {
        controlador = new ControladorListadoImagenes(nombre);
        
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            }
            
            JFrame frame = new JFrame("Testing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(new TestPane(controlador, path));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(700, 600);
            frame.setTitle("Listado de imagenes de: " + nombre);
            frame.setResizable(false);
            frame.setVisible(true);
            vGestion.setEnabled(false); //inhabilitamos padre
            
            frame.addWindowListener(new WindowAdapter() {       //habilitamos padre
                @Override
                public void windowClosing(WindowEvent we) {
                    vGestion.setEnabled(true);
                    vGestion.setVisible(true);
                }
              });
            
        });
    }
}
