
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 20/05/26
 */
public class PrestamoException extends ExcepcionesLibros
{
    public PrestamoException(String Mensaje){
        super(Mensaje);
    }
    
    public String toString(){
        return "Error de Préstamo: " + this.getMessage();    
    }
}