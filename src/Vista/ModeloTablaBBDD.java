package Vista;

import Modelo.BaseDeDatos;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTablaBBDD extends AbstractTableModel{
    private java.util.List <BaseDeDatos> BBDD;
    
    public ModeloTablaBBDD () {
        this.BBDD = new ArrayList<BaseDeDatos>();
    }
    
    @Override
    public int getColumnCount () {
        return 5;
    }
    
    @Override
    public int getRowCount() {
        return BBDD.size();
    }
    
    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Nombre"; break;
            case 1: nombre= "Nº de imagenes"; break;
            case 2: nombre= "Nº descriptores"; break;
            case 3: nombre= "Fecha"; break;
            case 4: nombre= "Descripción"; break;
        }
        return nombre;
    }
    
    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
            case 1: clase= java.lang.Integer.class; break;
            case 2: clase= java.lang.Integer.class; break;
            case 3: clase= java.lang.String.class; break;
            case 4: clase= java.lang.String.class; break;
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

        switch (col){
            case 0: resultado= BBDD.get(row).getNombre(); break;
            case 1: resultado= BBDD.get(row).getNumImagenes(); break;
            case 2: resultado= BBDD.get(row).getNumDescriptores(); break;
            case 3: resultado= BBDD.get(row).getFecha(); break;
            case 4: resultado= BBDD.get(row).getDescripcion(); break;
        }
        return resultado;
    }
    
    public void setFilas(java.util.List<BaseDeDatos> bbdd){
        this.BBDD=bbdd;
        fireTableDataChanged();
    }
    
    public BaseDeDatos obtenerBBDD(int i){
        return this.BBDD.get(i);
    }
    
    
}
