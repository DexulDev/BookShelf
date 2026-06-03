/**
 *
 * @author López Cruz Luis Eduardo
 * @version 2/06/26
 */
public class GenerarPrestamos extends Generar
{
    public boolean guardarPrestamo(Prestamo prestamo){
        return guardarObjeto("Prestamos.dat", prestamo);
    }
}
