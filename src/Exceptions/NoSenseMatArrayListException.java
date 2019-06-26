
package Exceptions;


public class NoSenseMatArrayListException extends Exception{
    public NoSenseMatArrayListException() {
        super("La matriz de ArrayList creada tiene arrays con diferentes tamanos que np puede ser pasado a Mat");
    }
}


/*Se devuelve la excepcion cuando los arrays que componene una matriz tienen diferente tamano. Es incorrecto 
ya que se espera un rectangulo. 
Por ejemplo
1,2,3,4,
1,2
1,2,3
1,2,3,4,5,6
2
Diferentes tamanos, asiq ue devuelvo excepcion
*/