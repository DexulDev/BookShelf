/**
 *
 * @author López Cruz Luis Eduardo
 * @version 2/06/26
 */
public class GenerarUsuarios extends Generar
{
    public boolean guardarCliente(Cliente cliente){
        return guardarObjeto("Usuarios.dat", cliente);
    }

    public boolean guardarUsuarioSistema(SistemaUsuario usuario){
        return guardarObjeto("sistemaUsuarios.dat", usuario);
    }
}
