package Vista;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

public class ModeloTablaRanking extends AbstractTableModel{
    private HashMap <String, Double> imagenes;
    private ArrayList<String> claves;
    
    public ModeloTablaRanking () {
        this.imagenes = new HashMap<>();
    }
    
    @Override
    public int getColumnCount () {
        return 3;
    }
    
    @Override
    public int getRowCount() {
        return imagenes.size();
    }
    
    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre = "Posicion"; break;
            case 1: nombre = "Semejanza (%)"; break;
            case 2: nombre = "Nombre de imagen"; break;
        }
        return nombre;
    }
    
    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.Integer.class; break;
            case 1: clase= java.lang.Integer.class; break;
            case 2: clase= java.lang.String.class; break;
        }
        return clase;
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object resultado=null;
        
        ArrayList<String> array = new ArrayList<>(this.claves);

        switch (col){
            case 0: 
                resultado = row + 1; 
                break;
            case 1: 
                String clave = array.get(row);
                resultado = this.imagenes.get(clave);
                break;
            case 2: 
                resultado = array.get(row); 
                break;
        }
        
        return resultado;
    }
    
    public void setFilas(HashMap<String, Double> bbdd, ArrayList<String> claves){
        this.imagenes=bbdd;
        this.claves= claves;
        fireTableDataChanged();
    }
    
    public void clean() {
        this.imagenes = new HashMap <>();
    }
    
//    public String obtenerBBDD(int i){
//        return this.imagenes.get(i);
//    }

    
    
}
