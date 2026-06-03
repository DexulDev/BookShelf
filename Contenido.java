
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 22/05/26
 */
import java.io.Serializable;

public abstract class Contenido implements Serializable
{
    protected static final long serialVersionUID = 1L;
    protected String titulo;
    protected String autor;
    protected String fechaPublicacion;
    protected int paginas;
    protected String[] contenido;
    protected int id;
    protected int pagActual;
    protected boolean disponible;
    
    public Contenido(String titulo, String autor, int id, int paginas){
        this.titulo = titulo;
        this.autor = autor;
        this.id = id;
        this.paginas = paginas;
        contenido = new String[paginas];
        pagActual = 0;
        disponible = true;
    }
    
    public Contenido(String titulo, String autor, int id, int paginas, String fechaPublicacion){
        this.titulo = titulo;
        this.autor = autor;
        this.id = id;
        this.paginas = paginas;
        this.fechaPublicacion = fechaPublicacion;
        contenido = new String[paginas];
        pagActual = 0;
        disponible = true;
    }
    
    protected String getFechaPublicacion(){
        return fechaPublicacion;
    }
    
    protected void setFechaPublicacion(String fechaPublicacion){
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getAutor(){
        return autor;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public int getPaginas(){
        return paginas;
    }

    public void setPaginas(int paginas){
        this.paginas = paginas;
    }

    public String getPaginaActual(){
        return contenido[pagActual];
    }

    public int getId(){
        return id;
    }

    public int getIndex(){
        return pagActual;
    }

    public boolean isDisponible(){
        return disponible;
    }

    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }
    
    public abstract String toString();
}