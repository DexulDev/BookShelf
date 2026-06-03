import java.util.Scanner;

/**
 *
 * @author López Cruz Luis Eduardo
 * @version 2/06/26
 */
public class MainBiblioteca
{
    public static Scanner scanner = new Scanner(System.in);

    private static void cls(){
        for(int i = 0; i < 100; i++){
            System.out.println();
        }
    }

    private static void pausar(){
        System.out.print("\n\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    private static String menuAdmin(){
        cls();
        System.out.println("=== Biblioteca - Menú Admin ===");
        System.out.println("Bienvenid@ al sistema\n");
        System.out.println("1. Información general");
        System.out.println("2. Registrar cliente");
        System.out.println("3. Registrar contenido");
        System.out.println("4. Préstamos");
        System.out.println("5. Buscar");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
        String opcion = scanner.nextLine();
        while(!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4") && !opcion.equals("5") && !opcion.equals("6")){
            System.out.println("Opción inválida.");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();
        }
        return opcion;
    }

    private static String menuCliente(){
        cls();
        System.out.println("=== Biblioteca - Menú Cliente ===");
        System.out.println("Bienvenid@\n");
        System.out.println("1. Información general");
        System.out.println("2. Buscar contenidos");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
        String opcion = scanner.nextLine();
        while(!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3")){
            System.out.println("Opción inválida.");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();
        }
        return opcion;
    }

    private static String menuBuscar(){
        cls();
        System.out.println("=== Buscar ===");
        System.out.println("1. Buscar clientes");
        System.out.println("2. Buscar contenidos");
        System.out.println("3. Volver");
        System.out.print("Seleccione una opción: ");
        String opcion = scanner.nextLine();
        while(!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3")){
            System.out.println("Opción inválida.");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();
        }
        return opcion;
    }

    private static String menuRol(){
        cls();
        System.out.println("=== Biblioteca - Inicio ===");
        System.out.println("1. Iniciar como admin");
        System.out.println("2. Iniciar como cliente");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
        String opcion = scanner.nextLine();
        while(!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3")){
            System.out.println("Opción inválida.");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();
        }
        return opcion;
    }

    public static void main(String[] args)
    {
        Biblioteca biblioteca = new Biblioteca();
        while(true){
            String rol = menuRol();
            if(rol.equals("1")){
                SistemaUsuario usuario = biblioteca.iniciarSistema(scanner);
                if(usuario == null) continue;
                while(true){
                    String opcion = menuAdmin();
                    switch(opcion){
                        case "1":
                            cls();
                            biblioteca.mostrarInformacionGeneral();
                            pausar();
                            break;
                        case "2":
                            cls();
                            biblioteca.registrarCliente(scanner);
                            pausar();
                            break;
                        case "3":
                            cls();
                            biblioteca.registrarContenido(scanner);
                            pausar();
                            break;
                        case "4":
                            cls();
                            biblioteca.registrarPrestamo(scanner);
                            pausar();
                            break;
                        case "5":
                            String buscar = menuBuscar();
                            if(buscar.equals("1")){
                                cls();
                                biblioteca.buscarClientes(scanner);
                                pausar();
                            }else if(buscar.equals("2")){
                                cls();
                                biblioteca.buscarContenidos(scanner);
                                pausar();
                            }
                            break;
                        case "6":
                            scanner.close();
                            return;
                    }
                }
            }else if(rol.equals("2")){
                Cliente cliente = biblioteca.iniciarCliente(scanner);
                if(cliente == null){
                    pausar();
                    continue;
                }
                while(true){
                    String opcion = menuCliente();
                    switch(opcion){
                        case "1":
                            cls();
                            biblioteca.mostrarInformacionCliente(cliente);
                            pausar();
                            break;
                        case "2":
                            cls();
                            biblioteca.buscarContenidos(scanner);
                            pausar();
                            break;
                        case "3":
                            break;
                    }
                    if(opcion.equals("3")) break;
                }
            }else{
                scanner.close();
                return;
            }
        }
    }
}
