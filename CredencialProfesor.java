
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 20/05/26
 */
public class CredencialProfesor extends Credencial 
{
    public CredencialProfesor(String nombre, String apellido, String numeroTelefono, String correoElectronico, String direccion, String fechaEmision){
        super(nombre, apellido, numeroTelefono, correoElectronico, direccion, fechaEmision);
    }

    public void renovar(){
        //se renueva por un año, se suman 365 dias a la fecha de vencimiento
        actualizarVencimiento(365*3);
    }

    public String calcularVencimiento(){
        int[] fecha = convertirFecha(this.fechaEmision);
        //se suman tres años
        fecha[0] += 365*3;
        while(fecha[0] > 30){
            fecha[0] -= 30;
            fecha[1]++;
        }
        while(fecha[1] > 12){
            fecha[1] -= 12;
            fecha[2]++;
        }

        return fecha[0] + "/" + fecha[1] + "/" + fecha[2];
    }

    public String toString(){
        return "Credencial de profesor\n" + getDatos();
    }
}
