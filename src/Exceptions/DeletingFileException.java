package Exceptions;

public class DeletingFileException extends Exception {
    
    public DeletingFileException() {
        super("Error al borrar archivo original");
    }
    
}
