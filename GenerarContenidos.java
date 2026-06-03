/**
 *
 * @author López Cruz Luis Eduardo
 * @version 2/06/26
 */
public class GenerarContenidos extends Generar
{
    public boolean guardarContenido(Contenido contenido){
        return guardarObjeto("Contenidos.dat", contenido);
    }
}
