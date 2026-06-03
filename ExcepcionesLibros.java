
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 20/05/26
 */
public abstract class ExcepcionesLibros extends Throwable{
    private String mensaje;
    
    public ExcepcionesLibros(String mensaje){
        super(mensaje);
        this.mensaje = mensaje;
    }
    
    public String getMessage(){
        return mensaje;
    }
    
    public abstract String toString();
}