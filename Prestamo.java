import java.time.LocalDate;
import java.io.Serializable;
/**
 *
 * @author López Cruz Luis Eduardo
 * @version 20/05/26
 */
public abstract class Prestamo implements Serializable
{
    private static final long serialVersionUID = 1L;
    protected String fechaInicio;
    protected String fechaFinal;
    protected String nombreLibro;
    protected int dias; //viene de una función
    protected boolean estado; //true si ya devolvió el libro, false si no lo ha hecho
    protected long id;

    public Prestamo(String fechaInicio, String fechaFinal, String nombreLibro){
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.nombreLibro = nombreLibro;
        this.estado = false;
        this.dias = calcularDias();
        this.id = nuevaId();
    }

    protected long nuevaId(){
        return convertirFecha(this.fechaInicio)[0] + this.dias + (int)Math.floor(Math.random() * 100 - 500) + 2026;
    }
    
    public String getFechaInicio(){
        return fechaInicio;
    }
    
    public void setFechaInicio(String fechaInicio){
        this.fechaInicio = fechaInicio;
    }

    protected String diaActual(){
        LocalDate fecha = LocalDate.now();
        
        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue();
        int anio = fecha.getYear();
        return dia + "/" + mes + "/" + anio;
    }
    
    public String getFechaFinal(){
        return fechaFinal;
    }
    
    public void setFechaFinal(String fechaFinal){
        this.fechaFinal = fechaFinal;
    }
    
    protected int calcularDias(){
        int[] fechaInicio = convertirFecha(this.fechaInicio);
        int[] fechaFinal = convertirFecha(this.fechaFinal);
        int diasInicio = (fechaInicio[2] * 365) + (fechaInicio[1] * 30) + fechaInicio[0];
        int diasFinal = (fechaFinal[2] * 365) + (fechaFinal[1] * 30) + fechaFinal[0];
        int diferencia = diasFinal - diasInicio;

        if(diferencia < 0){
            System.out.println("Error: La fecha final es anterior a la fecha inicial");
            return 0;
        }

        return diferencia;
    }

    protected int[] sumarDias(int dias){
        int[] fechaFinalConvertida = convertirFecha(this.fechaFinal);
        fechaFinalConvertida[0] += dias;
        while(fechaFinalConvertida[0] > 30){
            fechaFinalConvertida[0] -= 30;
            fechaFinalConvertida[1]++;
        }
        while(fechaFinalConvertida[1] > 12){
            fechaFinalConvertida[1] -= 12;
            fechaFinalConvertida[2]++;
        }
        return fechaFinalConvertida;
    }
    
    protected int[] convertirFecha(String fecha){
        int[] fechaConvertida = new int[3];
        String[] partes = fecha.split("/");
        for(int i = 0; i < partes.length; i++){
            fechaConvertida[i] = Integer.parseInt(partes[i]);
        }
        return fechaConvertida;
    }

    public int getDias(){
        return dias;
    }

    public String getNombreLibro(){
        return nombreLibro;
    }

    public boolean devolver(String fechaDevolucion){
        int[] fechaDevolucionConvertida = convertirFecha(fechaDevolucion);
        int[] fechaFinalConvertida = convertirFecha(this.fechaFinal);
        if(fechaDevolucionConvertida[2] > fechaFinalConvertida[2]){
            if(fechaDevolucionConvertida[1] > fechaFinalConvertida[1]){
                if(!(fechaDevolucionConvertida[0] - fechaFinalConvertida[0] > 3)){
                    this.estado = true;
                    return true; 
                }
            }
        }
        //generó adeudo por días
        return false;
    }

    public boolean getEstado(){
        return estado;
    }

    public long getId(){
        return id;
    }

    public abstract boolean renovacion(String fechaFinal, int adeudos);

    public abstract String toString();
}