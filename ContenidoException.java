
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 20/05/26
 */
public class ContenidoException extends ExcepcionesLibros
{
    public ContenidoException(String Mensaje){
        super(Mensaje);
    }
    
    public String toString(){
        return "Error de libro: " + this.getMessage();    
    }
}