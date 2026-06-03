
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 22/05/26
 */
public class Revista extends Contenido
{
    private int edicion;
    
    public Revista(String titulo, String autor, String fechaPublicacion, int edicion, int id, int paginas){
        super(titulo, autor, id, paginas, fechaPublicacion);
        this.edicion = edicion;
    }
    
    public int getEdicion(){
        return edicion;
    }
    
    public void setEdicion(int edicion){
        this.edicion = edicion;
    }

    public String toString(){
        return "Título de la revista: " + this.titulo + "\nAutor: " + this.autor + "\nNúmero de edicion: " + edicion + "\nFecha de publicación: "+ this.fechaPublicacion + "\nNúmero de páginas: " + this.paginas;
    }
}