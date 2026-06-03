public class PrestamoEstudiante extends Prestamo
{
    public PrestamoEstudiante(String fechaInicio, String fechaFinal, String nombreLibro){
        super(fechaInicio, fechaFinal, nombreLibro);
    }

    public boolean renovacion(String fechaFinal, int adeudos){
        //verificar que no tenga adeudos.
        int[] nuevaFechaFinal = convertirFecha(fechaFinal);
        if(adeudos == 0){
            //añadir 7 dias
            nuevaFechaFinal = sumarDias(7);
            this.fechaFinal = nuevaFechaFinal[0] + "/" + nuevaFechaFinal[1] + "/" + nuevaFechaFinal[2];
            this.dias = calcularDias();
            return true;
        }
        return false;
    }

    public String toString(){
        return "Préstamo de estudiante\n" + "ID: " + this.id + "\nLibro: " + this.nombreLibro + "\nFecha de inicio: " + this.fechaInicio + "\nFecha final: " + this.fechaFinal + "\nDías restantes: " + this.dias;
    }
}
