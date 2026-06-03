
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 25/05/26
 */
public class Libro extends Contenido
{
    private String editorial;
    
    public Libro(String titulo, String autor, String editorial, int id, int paginas){
        super(titulo, autor, id, paginas);
        this.editorial = editorial;
    }

    public String getEditorial(){
        return editorial;
    }

    public void setEditorial(String editorial){
        this.editorial = editorial;
    }

    public String toString(){
        return "Título del libro: " + this.titulo + "\nAutor: " + this.autor + "\nEditorial: " + editorial + "\nNúmero de páginas: " + this.paginas;
    }
}