import java.io.Serializable;

/**
 *
 * @author López Cruz Luis Eduardo
 * @version 2/06/26
 */
public class SistemaUsuario implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String password;
    private String rol;

    public SistemaUsuario(String nombre, String password, String rol){
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    public String getNombre(){
        return nombre;
    }

    public String getPassword(){
        return password;
    }

    public String getRol(){
        return rol;
    }

    public boolean esAdmin(){
        return rol.equalsIgnoreCase("admin");
    }

    public String toString(){
        return "Usuario: " + nombre + " (" + rol + ")";
    }
}
