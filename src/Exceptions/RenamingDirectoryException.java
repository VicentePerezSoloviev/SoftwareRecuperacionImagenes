package Exceptions;

public class RenamingDirectoryException  extends Exception{
    public RenamingDirectoryException() {
        super("Ha habido un error modificando el nombre del directorio. Es posible que el nombre ya exista");
    }
}