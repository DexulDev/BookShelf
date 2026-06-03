
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 20/05/26
 */
public class ProfesorException extends ExcepcionesLibros
{
    public ProfesorException(String Mensaje){
        super(Mensaje);
    }
    
    public String toString(){
        return "Error de Profesor: " + this.getMessage();    
    }
}