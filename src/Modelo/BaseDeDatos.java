package Modelo;

public class BaseDeDatos {
    private final String nombre;
    private final String fecha;
    private final String descripcion;
    private int numImagenes;
    private int numDescriptores;
    
    
    public BaseDeDatos (String nombre, String fecha, String descripcion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public int getNumImagenes() {
        return numImagenes;
    }

    public int getNumDescriptores() {
        return numDescriptores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNumImagenes (int num) {
        this.numImagenes = num;
    }

    public void setNumDescriptores(int numDescriptores) {
        this.numDescriptores = numDescriptores;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    
}
