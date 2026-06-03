import java.time.LocalDate;
import java.io.Serializable;
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 20/05/26
 */
public abstract class Credencial implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String nombre;
    protected String apellido;
    protected String numeroTelefono;
    protected String correoElectronico;
    protected String direccion;
    protected String fechaEmision;
    protected String fechaVencimiento;

    public Credencial(String nombre, String apellido, String numeroTelefono, String correoElectronico, String direccion, String fechaEmision){
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.fechaEmision = fechaEmision;
        fechaVencimiento = calcularVencimiento();
    }

    private String diaActual(){
        LocalDate fecha = LocalDate.now();
        
        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue();
        int anio = fecha.getYear();
        return dia + "/" + mes + "/" + anio;
    }
    protected int[] convertirFecha(String fecha){
        String[] partes = fecha.split("/");
        int[] resultado = new int[3];
        for(int i = 0; i < partes.length; i++){
            resultado[i] = Integer.parseInt(partes[i]);
        }
        return resultado;
    }

    public String getDatos(){
        return "Nombre: " + nombre + 
        "\nApellido: " + apellido + 
        "\nNúmero de teléfono: " + numeroTelefono + 
        "\nCorreo electrónico: " + correoElectronico + 
        "\nDirección: " + direccion 
        + "\nFecha de emisión: " + fechaEmision 
        + "\nFecha de vencimiento: " + fechaVencimiento;
    }

    protected void actualizarVencimiento(int dias){
        int[] fechaVencimiento = convertirFecha(this.fechaVencimiento);
        fechaVencimiento[0] += dias;
        while(fechaVencimiento[0] > 30){
            fechaVencimiento[0] -= 30;
            fechaVencimiento[1]++;
        }
        while(fechaVencimiento[1] > 12){
            fechaVencimiento[1] -= 12;
            fechaVencimiento[2]++;
        }
        this.fechaVencimiento = fechaVencimiento[0] + "/" + fechaVencimiento[1] + "/" + fechaVencimiento[2];
    }

    public boolean checarVigencia(){
        int[] fechaVencimiento = convertirFecha(this.fechaVencimiento);
        int[] fechaActual = convertirFecha(diaActual());
        if(fechaActual[2] > fechaVencimiento[2]){
            return false;
        } else if(fechaActual[2] == fechaVencimiento[2]){
            if(fechaActual[1] > fechaVencimiento[1]){
                return false;
            } else if(fechaActual[1] == fechaVencimiento[1]){
                if(fechaActual[0] > fechaVencimiento[0]){
                    return false;
                }
            }
        }
        return true;
    }

    public abstract void renovar();
    protected abstract String calcularVencimiento();
    public abstract String toString();
}
