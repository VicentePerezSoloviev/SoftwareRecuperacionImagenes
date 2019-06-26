package Exceptions;

public class DirectoryNotExists extends Exception {
    public DirectoryNotExists () {
        super ("El directorio no existe");
    }
}
