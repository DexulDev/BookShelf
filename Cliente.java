import java.time.LocalDate;
import java.util.Vector;
import java.io.Serializable;
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 20/05/26
 */
public abstract class Cliente implements Serializable
{
    private static final long serialVersionUID = 1L;
    protected String nombre;
    protected String apellido;
    protected String numeroTelefono;
    protected String correoElectronico;
    protected String direccion;
    protected Vector<Prestamo> historialPrestamos = new Vector<>();
    protected int prestamoActual = 0;
    int adeudos = 0;
    Credencial credencial;
    
    public Cliente(String nombre, String apellido, String numeroTelefono, String correoElectronico, String direccion){
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        //credencial = new Credencial(this.nombre, this.apellido, this.numeroTelefono, this.correoElectronico, this.direccion, (LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear()));
        //cada cliente necesita tener un tipo de credencial ligada al tipo de usuario entonces
        //esta declaración comentada se verá puesta en el contructor de cada usuario
    }

    protected int[] convertirFecha(String fecha){
        String[] partes = fecha.split("/");
        int[] fechaArray = {Integer.parseInt(partes[0]), Integer.parseInt(partes[1]), Integer.parseInt(partes[2])};
        return fechaArray;
    }

    protected String diaActual(){
        LocalDate fecha = LocalDate.now();
        
        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue();
        int anio = fecha.getYear();
        return dia + "/" + mes + "/" + anio;
    }

    public void devolverLibro(String fecha){
        if(!historialPrestamos.get(prestamoActual).devolver(fecha)){
            adeudos++;
        }
    }

    protected void agregarPrestamo(Prestamo prestamo){ //usar en el metodo abstracto
        historialPrestamos.add(prestamo);
        prestamoActual++;
    }

    public String getDatos(){
        return "Nombre: " + nombre + 
        "\nApellido: " + apellido + 
        "\nNúmero de teléfono: " + numeroTelefono + 
        "\nCorreo electrónico: " + correoElectronico + 
        "\nDirección: " + direccion;
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public String getTelefono(){
        return numeroTelefono;
    }

    public String getCorreo(){
        return correoElectronico;
    }

    public int getId(){
        return (nombre + apellido + numeroTelefono).hashCode();
    }

    public abstract boolean solicitarLibro(Contenido contenido);

    public abstract void solicitarPrestamo(String fechaInicio, String fechaFinal, String nombreLibro);
}