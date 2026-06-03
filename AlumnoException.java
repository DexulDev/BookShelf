
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 20/05/26
 */
public class AlumnoException extends ExcepcionesLibros
{
    public AlumnoException(String Mensaje){
        super(Mensaje);
    }
    
    public String toString(){
        return "Error de Alumno: " + this.getMessage();    
    }
}