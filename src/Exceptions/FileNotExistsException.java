package Exceptions;

public class FileNotExistsException extends Exception{
    
    public FileNotExistsException() {
        super("El fichero no existe");
    }
}
