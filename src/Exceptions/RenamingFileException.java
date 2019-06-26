package Exceptions;

public class RenamingFileException extends Exception {
    public RenamingFileException () {
        super("Error al renombrar el fichero temporal a original");
    }
}
