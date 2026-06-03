import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.EOFException;
import java.util.Vector;

/**
 *
 * @author López Cruz Luis Eduardo
 * @version 2/06/26
 */
public class Registro
{
    public Vector<Cliente> leerClientes(){
        Vector<Cliente> clientes = new Vector<Cliente>();
        try{
            File archivo = new File("Usuarios.dat");
            if(!archivo.exists() || archivo.length() == 0) return clientes;
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(true){
                try{
                    Cliente cliente = (Cliente) ois.readObject();
                    clientes.add(cliente);
                }catch(EOFException e){
                    break;
                }
            }
            ois.close();
            fis.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return clientes;
    }

    public Vector<Contenido> leerContenidos(){
        Vector<Contenido> contenidos = new Vector<Contenido>();
        try{
            File archivo = new File("Contenidos.dat");
            if(!archivo.exists() || archivo.length() == 0) return contenidos;
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(true){
                try{
                    Contenido contenido = (Contenido) ois.readObject();
                    contenidos.add(contenido);
                }catch(EOFException e){
                    break;
                }
            }
            ois.close();
            fis.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return contenidos;
    }

    public Vector<Prestamo> leerPrestamos(){
        Vector<Prestamo> prestamos = new Vector<Prestamo>();
        try{
            File archivo = new File("Prestamos.dat");
            if(!archivo.exists() || archivo.length() == 0) return prestamos;
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(true){
                try{
                    Prestamo prestamo = (Prestamo) ois.readObject();
                    prestamos.add(prestamo);
                }catch(EOFException e){
                    break;
                }
            }
            ois.close();
            fis.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return prestamos;
    }

    public Vector<SistemaUsuario> leerUsuariosSistema(){
        Vector<SistemaUsuario> usuarios = new Vector<SistemaUsuario>();
        try{
            File archivo = new File("sistemaUsuarios.dat");
            if(!archivo.exists() || archivo.length() == 0) return usuarios;
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while(true){
                try{
                    SistemaUsuario usuario = (SistemaUsuario) ois.readObject();
                    usuarios.add(usuario);
                }catch(EOFException e){
                    break;
                }
            }
            ois.close();
            fis.close();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return usuarios;
    }

    public SistemaUsuario buscarUsuarioSistema(String nombre, String password){
        Vector<SistemaUsuario> usuarios = leerUsuariosSistema();
        for(int i = 0; i < usuarios.size(); i++){
            SistemaUsuario usuario = usuarios.get(i);
            if(usuario.getNombre().equalsIgnoreCase(nombre) && usuario.getPassword().equals(password)){
                return usuario;
            }
        }
        return null;
    }

    public Cliente buscarCliente(String busqueda) throws AlumnoException{
        try{
            int id = Integer.parseInt(busqueda);
            return buscarClientePorId(id);
        }catch(NumberFormatException e){
            Vector<Cliente> encontrados = buscarClientePorTexto(busqueda);
            if(encontrados.size() == 1) return encontrados.get(0);
            throw new AlumnoException("Cliente no encontrado.");
        }
    }

    public Vector<Cliente> buscarClientes(String busqueda) throws AlumnoException{
        try{
            int id = Integer.parseInt(busqueda);
            Vector<Cliente> resultado = new Vector<Cliente>();
            resultado.add(buscarClientePorId(id));
            return resultado;
        }catch(NumberFormatException e){
            return buscarClientePorTexto(busqueda);
        }
    }

    public Contenido buscarContenido(String busqueda, int tipo) throws ContenidoException{
        try{
            int id = Integer.parseInt(busqueda);
            return buscarContenidoPorId(id, tipo);
        }catch(NumberFormatException e){
            Vector<Contenido> encontrados = buscarContenidoPorTexto(busqueda, tipo);
            if(encontrados.size() == 1) return encontrados.get(0);
            throw new ContenidoException("Contenido no encontrado.");
        }
    }

    public Vector<Contenido> buscarContenidos(String busqueda, int tipo) throws ContenidoException{
        try{
            int id = Integer.parseInt(busqueda);
            Vector<Contenido> resultado = new Vector<Contenido>();
            resultado.add(buscarContenidoPorId(id, tipo));
            return resultado;
        }catch(NumberFormatException e){
            return buscarContenidoPorTexto(busqueda, tipo);
        }
    }

    private Cliente buscarClientePorId(int id) throws AlumnoException{
        Vector<Cliente> clientes = leerClientes();
        for(int i = 0; i < clientes.size(); i++){
            if(clientes.get(i).getId() == id){
                return clientes.get(i);
            }
        }
        throw new AlumnoException("Cliente no encontrado.");
    }

    private Vector<Cliente> buscarClientePorTexto(String texto) throws AlumnoException{
        Vector<Cliente> clientes = leerClientes();
        Vector<Cliente> encontrados = new Vector<Cliente>();
        String valor = texto.toLowerCase();
        for(int i = 0; i < clientes.size(); i++){
            Cliente cliente = clientes.get(i);
            if(cliente.getNombre().toLowerCase().contains(valor) || cliente.getApellido().toLowerCase().contains(valor) || cliente.getCorreo().toLowerCase().contains(valor) || cliente.getTelefono().toLowerCase().contains(valor)){
                encontrados.add(cliente);
            }
        }
        if(encontrados.size() == 0){
            throw new AlumnoException("Cliente no encontrado.");
        }
        return encontrados;
    }

    private Contenido buscarContenidoPorId(int id, int tipo) throws ContenidoException{
        Vector<Contenido> contenidos = leerContenidos();
        for(int i = 0; i < contenidos.size(); i++){
            Contenido contenido = contenidos.get(i);
            if(contenido.getId() == id && coincideTipo(contenido, tipo)){
                return contenido;
            }
        }
        throw new ContenidoException("Contenido no encontrado.");
    }

    private Vector<Contenido> buscarContenidoPorTexto(String texto, int tipo) throws ContenidoException{
        Vector<Contenido> contenidos = leerContenidos();
        Vector<Contenido> encontrados = new Vector<Contenido>();
        String valor = texto.toLowerCase();
        for(int i = 0; i < contenidos.size(); i++){
            Contenido contenido = contenidos.get(i);
            if(!coincideTipo(contenido, tipo)){
                continue;
            }
            if(contenido.getTitulo().toLowerCase().contains(valor) || contenido.getAutor().toLowerCase().contains(valor)){
                encontrados.add(contenido);
            }else if(contenido instanceof Libro){
                Libro libro = (Libro) contenido;
                if(libro.getEditorial().toLowerCase().contains(valor)){
                    encontrados.add(contenido);
                }
            }else if(contenido instanceof Tesis){
                Tesis tesis = (Tesis) contenido;
                if(tesis.getUniversidad().toLowerCase().contains(valor)){
                    encontrados.add(contenido);
                }
            }
        }
        if(encontrados.size() == 0){
            throw new ContenidoException("Contenido no encontrado.");
        }
        return encontrados;
    }

    private boolean coincideTipo(Contenido contenido, int tipo){
        if(tipo == 1 && contenido instanceof Libro) return true;
        if(tipo == 2 && contenido instanceof Revista) return true;
        if(tipo == 3 && contenido instanceof Tesis) return true;
        return false;
    }
}
