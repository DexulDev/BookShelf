
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 22/05/26
 */
public class Tesis extends Contenido
{
    private String universidad;
    private String fechaPublicacion;
    
    public Tesis(String titulo, String autor, String universidad, String fechaPublicacion, int id, int paginas){
        super(titulo, autor, id, paginas, fechaPublicacion);
        this.universidad = universidad;
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public String getUniversidad(){
        return universidad;
    }
    
    public void setUniversidad(String universidad){
        this.universidad = universidad;
    }
    
    public String toString(){
        return "Título de la tesis: " + this.titulo + "\nAutor: " + this.autor + "\nUniversidad: " + universidad + "\nFecha de publicacion: " + this.fechaPublicacion +"\nNúmero de páginas: " + this.paginas;
    }
}