package Exceptions;

public class TypeNotExistsException extends Exception{
    
    public TypeNotExistsException() {
        super("El tipo de histograma espcificado no existe");
    }
}


/*Se devuelve cuando se pide un tipo que no existe en TipoMatlab*/