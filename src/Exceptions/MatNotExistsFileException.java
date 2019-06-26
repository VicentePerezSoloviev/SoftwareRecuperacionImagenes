package Exceptions;

public class MatNotExistsFileException extends Exception {
    
    public MatNotExistsFileException (String path) {
        super("El histograma solicitado no existe en el fichero de descriptores para la imagen " + path);
    }
}

/*Esta excepcion sucede cuando se esta buscando un histograma en el fichero de descriptores de una imagen
sin embargo no existe. Se devuelve la excepcion para que se pida calcularlo.*/
