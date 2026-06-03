
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 20/05/26
 */
public class Profesor extends Cliente 
{
    public Profesor(String nombre, String apellido, String numeroTelefono, String correoElectronico, String direccion){
        super(nombre, apellido, numeroTelefono, correoElectronico, direccion);
        credencial = new CredencialProfesor(nombre, apellido, numeroTelefono, correoElectronico, direccion, diaActual());
    }

    public void solicitarPrestamo(String fechaInicio, String fechaFinal, String nombreLibro) {
        PrestamoProfesor prestamo = new PrestamoProfesor(fechaInicio, fechaFinal, nombreLibro);
        agregarPrestamo(prestamo);
    }

    public void renovarCredencial(){
        ((CredencialProfesor)credencial).renovar();
    }
    
    public void solicitarRenovacionPrestamo(String fechaFinal, int adeudos){
        if(historialPrestamos.get(prestamoActual).renovacion(fechaFinal, adeudos)){
            renovarCredencial();
        }
    }

        private String fechaFinal(String fechaInicio){
        int[] fecha = convertirFecha(fechaInicio);
        fecha[0] += 7;
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

    //solicitar libros
    public boolean solicitarLibro(Contenido libro){
        try{
            PrestamoProfesor PrestamoTemporal = new PrestamoProfesor(diaActual(), fechaFinal(diaActual()), libro.getTitulo());
            //1. Verificar vigencia credencial
            //2. Verificar que el libro esté disponible
            //3. Verificar que el profesor no tenga adeudos
            //4. Si todo está bien, solicitar el préstamo

            //1. Verificar vigencia credencial
            if(!credencial.checarVigencia()){
                throw new PrestamoException("Credencial no vigente. No se puede solicitar el libro.");
            }

            //2. Verificar que el libro esté disponible
            if(!libro.isDisponible()){
                throw new ContenidoException("Libro no disponible. No se puede solicitar el libro.");
            }

            //3. Verificar que el profesor no tenga adeudos
            if(adeudos > 0){
                throw new PrestamoException("Tienes adeudos. No se puede solicitar el libro.");
            }

            //4. Si todo está bien, solicitar el préstamo
            historialPrestamos.add(PrestamoTemporal);
            System.out.println("Libro solicitado: " + libro.getTitulo());
            System.out.println("\nInformación: " + libro.toString());
            prestamoActual++;
            return true;
            
        }catch(ContenidoException e){
            System.out.println("Error: " + e.getMessage());
            return false;

        }catch(PrestamoException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean solicitarRevista(Revista revista){
        try{
            PrestamoProfesor PrestamoTemporal = new PrestamoProfesor(diaActual(), fechaFinal(diaActual()), revista.getTitulo());
            //1. Verificar vigencia credencial
            //2. Verificar que el libro esté disponible
            //3. Verificar que el profesor no tenga adeudos
            //4. Si todo está bien, solicitar el préstamo

            //1. Verificar vigencia credencial
            if(!credencial.checarVigencia()){
                throw new PrestamoException("Credencial no vigente. No se puede solicitar la revista.");
            }

            //2. Verificar que el libro esté disponible
            if(!revista.isDisponible()){
                throw new ContenidoException("Revista no disponible. No se puede solicitar la revista.");
            }

            //3. Verificar que el profesor no tenga adeudos
            if(adeudos > 0){
                throw new PrestamoException("Tienes adeudos. No se puede solicitar la revista.");
            }

            //4. Si todo está bien, solicitar el préstamo
            historialPrestamos.add(PrestamoTemporal);
            System.out.println("Revista solicitada: " + revista.getTitulo());
            System.out.println("\nInformación: " + revista.toString());
            prestamoActual++;
            return true;
            
        }catch(ContenidoException e){
            System.out.println("Error: " + e.getMessage());
            return false;

        }catch(PrestamoException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean solicitarTesis(Tesis tesis){
        try{
            PrestamoProfesor PrestamoTemporal = new PrestamoProfesor(diaActual(), fechaFinal(diaActual()), tesis.getTitulo());
            //1. Verificar vigencia credencial
            //2. Verificar que el libro esté disponible
            //3. Verificar que el profesor no tenga adeudos
            //4. Si todo está bien, solicitar el préstamo

            //1. Verificar vigencia credencial
            if(!credencial.checarVigencia()){
                throw new PrestamoException("Credencial no vigente. No se puede solicitar la tesis.");
            }

            //2. Verificar que el libro esté disponible
            if(!tesis.isDisponible()){
                throw new ContenidoException("Tesis no disponible. No se puede solicitar la tesis.");
            }

            //3. Verificar que el profesor no tenga adeudos
            if(adeudos > 0){
                throw new PrestamoException("Tienes adeudos. No se puede solicitar la tesis.");
            }

            //4. Si todo está bien, solicitar el préstamo
            historialPrestamos.add(PrestamoTemporal);
            System.out.println("Tesis solicitada: " + tesis.getTitulo());
            System.out.println("\nInformación: " + tesis.toString());
            prestamoActual++;
            return true;
            
        }catch(ContenidoException e){
            System.out.println("Error: " + e.getMessage());
            return false;

        }catch(PrestamoException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
