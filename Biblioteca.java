import java.util.Vector;
import java.util.Scanner;
import java.time.LocalDate;

/**
 *
 * @author López Cruz Luis Eduardo
 * @version 2/06/26
 */
public class Biblioteca
{
    private Registro registro = new Registro();
    private GenerarUsuarios generarUsuarios = new GenerarUsuarios();
    private GenerarContenidos generarContenidos = new GenerarContenidos();
    private GenerarPrestamos generarPrestamos = new GenerarPrestamos();

    public SistemaUsuario iniciarSistema(Scanner scanner){
        Vector<SistemaUsuario> usuarios = registro.leerUsuariosSistema();
        if(usuarios.size() == 0){
            System.out.print("Ingrese un nombre para nuevo usuario admin: ");
            String nombre = leerTextoNoVacio(scanner);
            System.out.print("Ingrese contraseña: ");
            String password = leerTextoNoVacio(scanner);
            SistemaUsuario nuevo = new SistemaUsuario(nombre, password, "admin");
            generarUsuarios.guardarUsuarioSistema(nuevo);
            return nuevo;
        }
        while(true){
            System.out.println("Usuarios disponibles:");
            for(int i = 0; i < usuarios.size(); i++){
                System.out.println((i+1) + ". " + usuarios.get(i).getNombre());
            }
            System.out.print("Nombre de usuario: ");
            String nombre = leerTextoNoVacio(scanner);
            System.out.print("Contraseña: ");
            String password = leerTextoNoVacio(scanner);
            SistemaUsuario usuario = registro.buscarUsuarioSistema(nombre, password);
            if(usuario != null){
                return usuario;
            }
            System.out.println("Datos incorrectos.");
        }
    }

    public Cliente iniciarCliente(Scanner scanner){
        Vector<Cliente> clientes = registro.leerClientes();
        if(clientes.size() == 0){
            System.out.println("No hay clientes registrados.");
            return null;
        }
        while(true){
            System.out.println("Clientes disponibles:");
            for(int i = 0; i < clientes.size(); i++){
                System.out.println((i+1) + ". " + clientes.get(i).getNombre() + " " + clientes.get(i).getApellido());
            }
            System.out.print("Nombre del cliente: ");
            String nombre = leerTextoNoVacio(scanner);
            System.out.print("Apellido del cliente: ");
            String apellido = leerTextoNoVacio(scanner);
            for(int i = 0; i < clientes.size(); i++){
                Cliente cliente = clientes.get(i);
                if(cliente.getNombre().equalsIgnoreCase(nombre) && cliente.getApellido().equalsIgnoreCase(apellido)){
                    return cliente;
                }
            }
            System.out.println("Cliente no encontrado.");
        }
    }

    public void mostrarInformacionGeneral(){
        Vector<Cliente> clientes = registro.leerClientes();
        Vector<Contenido> contenidos = registro.leerContenidos();
        Vector<Prestamo> prestamos = registro.leerPrestamos();

        System.out.println("=== CONTENIDOS ===");
        for(int i = 0; i < contenidos.size(); i++){
            System.out.println("\nContenido " + (i+1) + "\n" + contenidos.get(i).toString());
        }

        System.out.println("\n=== CLIENTES ===");
        for(int i = 0; i < clientes.size(); i++){
            System.out.println("\nCliente " + (i+1) + "\n" + clientes.get(i).getDatos());
        }

        System.out.println("\n=== PRESTAMOS ===");
        for(int i = 0; i < prestamos.size(); i++){
            System.out.println("\nPrestamo " + (i+1) + "\n" + prestamos.get(i).toString());
        }
    }

    public void mostrarInformacionCliente(Cliente cliente){
        System.out.println("=== CREDENCIAL ===");
        System.out.println(cliente.credencial.toString());
        System.out.println("\n=== CONTENIDOS ===");
        Vector<Contenido> contenidos = registro.leerContenidos();
        for(int i = 0; i < contenidos.size(); i++){
            System.out.println("\nContenido " + (i+1) + "\n" + contenidos.get(i).toString());
        }
    }

    public void registrarCliente(Scanner scanner){
        int tipo = leerOpcion(scanner, 1, 2, "Seleccione tipo de cliente (1. Estudiante, 2. Profesor): ");
        System.out.print("Nombre: ");
        String nombre = leerTextoNoVacio(scanner);
        System.out.print("Apellido: ");
        String apellido = leerTextoNoVacio(scanner);
        System.out.print("Teléfono: ");
        String telefono = leerTextoNoVacio(scanner);
        System.out.print("Correo: ");
        String correo = leerTextoNoVacio(scanner);
        System.out.print("Dirección: ");
        String direccion = leerTextoNoVacio(scanner);

        boolean resultado;
        if(tipo == 1){
            Estudiante estudiante = new Estudiante(nombre, apellido, telefono, correo, direccion);
            resultado = generarUsuarios.guardarCliente(estudiante);
        }else{
            Profesor profesor = new Profesor(nombre, apellido, telefono, correo, direccion);
            resultado = generarUsuarios.guardarCliente(profesor);
        }
        if(resultado){
            System.out.println("Cliente registrado.");
        }else{
            System.out.println("No se pudo registrar el cliente.");
        }
    }

    public void registrarContenido(Scanner scanner){
        int tipo = leerOpcion(scanner, 1, 3, "Seleccione tipo de contenido (1. Libro, 2. Revista, 3. Tesis): ");
        System.out.print("Título: ");
        String titulo = leerTextoNoVacio(scanner);
        System.out.print("Autor: ");
        String autor = leerTextoNoVacio(scanner);
        System.out.print("ID: ");
        int id = leerEntero(scanner);
        System.out.print("Páginas: ");
        int paginas = leerEntero(scanner);

        boolean resultado;
        if(tipo == 1){
            System.out.print("Editorial: ");
            String editorial = leerTextoNoVacio(scanner);
            Libro libro = new Libro(titulo, autor, editorial, id, paginas);
            resultado = generarContenidos.guardarContenido(libro);
        }else if(tipo == 2){
            System.out.print("Fecha de publicación: ");
            String fecha = leerTextoNoVacio(scanner);
            System.out.print("Edición: ");
            int edicion = leerEntero(scanner);
            Revista revista = new Revista(titulo, autor, fecha, edicion, id, paginas);
            resultado = generarContenidos.guardarContenido(revista);
        }else{
            System.out.print("Universidad: ");
            String universidad = leerTextoNoVacio(scanner);
            System.out.print("Fecha de publicación: ");
            String fecha = leerTextoNoVacio(scanner);
            Tesis tesis = new Tesis(titulo, autor, universidad, fecha, id, paginas);
            resultado = generarContenidos.guardarContenido(tesis);
        }
        if(resultado){
            System.out.println("Contenido registrado.");
        }else{
            System.out.println("No se pudo registrar el contenido.");
        }
    }

    public void registrarPrestamo(Scanner scanner){
        try{
            Cliente cliente = seleccionarCliente(scanner);
            if(cliente == null) return;
            Contenido contenido = seleccionarContenido(scanner);
            if(contenido == null) return;

            boolean aprobado = false;
            if(contenido instanceof Libro){
                aprobado = cliente.solicitarLibro(contenido);
            }else if(contenido instanceof Revista){
                if(cliente instanceof Estudiante){
                    aprobado = ((Estudiante)cliente).solicitarRevista((Revista)contenido);
                }else if(cliente instanceof Profesor){
                    aprobado = ((Profesor)cliente).solicitarRevista((Revista)contenido);
                }
            }else if(contenido instanceof Tesis){
                if(cliente instanceof Estudiante){
                    aprobado = ((Estudiante)cliente).solicitarTesis((Tesis)contenido);
                }else if(cliente instanceof Profesor){
                    aprobado = ((Profesor)cliente).solicitarTesis((Tesis)contenido);
                }
            }

            if(aprobado){
                Prestamo prestamo = crearPrestamo(cliente, contenido);
                if(prestamo != null){
                    generarPrestamos.guardarPrestamo(prestamo);
                }
                System.out.println("Préstamo registrado.");
            }else{
                System.out.println("No se pudo registrar el préstamo.");
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void buscarClientes(Scanner scanner){
        System.out.print("Ingrese búsqueda: ");
        String busqueda = leerTextoNoVacio(scanner);
        try{
            Vector<Cliente> clientes = registro.buscarClientes(busqueda);
            for(int i = 0; i < clientes.size(); i++){
                System.out.println("\n" + clientes.get(i).getDatos());
            }
        }catch(AlumnoException ae){
            System.out.println(ae.toString());
        }
    }

    public void buscarContenidos(Scanner scanner){
        int tipo = leerOpcion(scanner, 1, 3, "Seleccione tipo (1. Libro, 2. Revista, 3. Tesis): ");
        System.out.print("Ingrese búsqueda: ");
        String busqueda = leerTextoNoVacio(scanner);
        try{
            Vector<Contenido> contenidos = registro.buscarContenidos(busqueda, tipo);
            for(int i = 0; i < contenidos.size(); i++){
                System.out.println("\n" + contenidos.get(i).toString());
            }
        }catch(ContenidoException ce){
            System.out.println(ce.toString());
        }
    }

    private Cliente seleccionarCliente(Scanner scanner){
        System.out.print("Cliente (texto o ID): ");
        String busqueda = leerTextoNoVacio(scanner);
        try{
            Vector<Cliente> clientes = registro.buscarClientes(busqueda);
            if(clientes.size() == 1) return clientes.get(0);
            for(int i = 0; i < clientes.size(); i++){
                System.out.println((i+1) + ". " + clientes.get(i).getNombre() + " " + clientes.get(i).getApellido());
            }
            int opcion = leerOpcion(scanner, 1, clientes.size(), "Seleccione cliente: ");
            return clientes.get(opcion - 1);
        }catch(AlumnoException ae){
            System.out.println(ae.toString());
            return null;
        }
    }

    private Contenido seleccionarContenido(Scanner scanner){
        int tipo = leerOpcion(scanner, 1, 3, "Tipo (1. Libro, 2. Revista, 3. Tesis): ");
        System.out.print("Contenido (texto o ID): ");
        String busqueda = leerTextoNoVacio(scanner);
        try{
            Vector<Contenido> contenidos = registro.buscarContenidos(busqueda, tipo);
            if(contenidos.size() == 1) return contenidos.get(0);
            for(int i = 0; i < contenidos.size(); i++){
                System.out.println((i+1) + ". " + contenidos.get(i).getTitulo());
            }
            int opcion = leerOpcion(scanner, 1, contenidos.size(), "Seleccione contenido: ");
            return contenidos.get(opcion - 1);
        }catch(ContenidoException ce){
            System.out.println(ce.toString());
            return null;
        }
    }

    private Prestamo crearPrestamo(Cliente cliente, Contenido contenido){
        String inicio = diaActual();
        String fin = sumarDias(inicio, 7);
        if(cliente instanceof Estudiante){
            return new PrestamoEstudiante(inicio, fin, contenido.getTitulo());
        }
        if(cliente instanceof Profesor){
            return new PrestamoProfesor(inicio, fin, contenido.getTitulo());
        }
        return null;
    }

    private String diaActual(){
        LocalDate fecha = LocalDate.now();
        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue();
        int anio = fecha.getYear();
        return dia + "/" + mes + "/" + anio;
    }

    private String sumarDias(String fechaBase, int dias){
        int[] fecha = convertirFecha(fechaBase);
        fecha[0] += dias;
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

    private int[] convertirFecha(String fecha){
        String[] partes = fecha.split("/");
        int[] resultado = new int[3];
        for(int i = 0; i < partes.length; i++){
            resultado[i] = Integer.parseInt(partes[i]);
        }
        return resultado;
    }

    private String leerTextoNoVacio(Scanner scanner){
        String texto = scanner.nextLine();
        while(texto.length() == 0){
            System.out.println("Entrada inválida.");
            texto = scanner.nextLine();
        }
        return texto;
    }

    private int leerEntero(Scanner scanner){
        while(true){
            if(scanner.hasNextInt()){
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            }
            System.out.println("Entrada inválida.");
            scanner.nextLine();
        }
    }

    private int leerOpcion(Scanner scanner, int min, int max, String mensaje){
        int opcion;
        while(true){
            System.out.print(mensaje);
            if(scanner.hasNextInt()){
                opcion = scanner.nextInt();
                scanner.nextLine();
                if(opcion >= min && opcion <= max) return opcion;
            }else{
                scanner.nextLine();
            }
            System.out.println("Opción inválida.");
        }
    }
}
