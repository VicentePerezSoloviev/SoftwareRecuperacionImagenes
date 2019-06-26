package Modelo;

public class Imagen {
    private final String nombre;
    private final String path;
    private final String fecha;
    
    public Imagen (String nombre, String path, String fecha) {
        this.nombre = nombre;
        this.path = path;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPath() {
        return path;
    }

    public String getFecha() {
        return fecha;
    }
    
    

    
}
