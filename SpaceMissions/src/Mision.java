import java.time.LocalDate;

/*
 * @brief: Clase Mision que representa una misión espacial con detalles como ID, nombre, descripción, destino,
 * distancia, fecha de lanzamiento, duración, combustible requerido, tripulación necesaria, carga útil y estado.
 */

enum EstadoMision {
    PENDIENTE,
    EN_CURSO,
    COMPLETADA,
    CANCELADA
}

public class Mision {
    private int id;
    private String nombre;
    private String descripcion;
    private String destino;
    private double distanciaKm;
    private LocalDate fechaLanzamiento;
    private int duracionDias;
    private double combustibleRequerido;
    private int tripulacionNecesaria;
    private double cargaUtilKg;
    private EstadoMision estado;
    private Nave naveAsignada;

    public Mision(String nombre, String descripcion, String destino, double distanciaKm, 
                  LocalDate fechaLanzamiento, int duracionDias, double combustibleRequerido, 
                  int tripulacionNecesaria, double cargaUtilKg, EstadoMision estado, Nave naveAsignada) {
        this.id = (int) (Math.random() * 10000); 
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.fechaLanzamiento = fechaLanzamiento;
        this.duracionDias = duracionDias;
        this.combustibleRequerido = combustibleRequerido;
        this.tripulacionNecesaria = tripulacionNecesaria;
        this.cargaUtilKg = cargaUtilKg;
        this.estado = estado;
        this.naveAsignada = naveAsignada;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getDestino() { return destino; }
    public double getDistanciaKm() { return distanciaKm; }
    public LocalDate getFechaLanzamiento() { return fechaLanzamiento; }
    public int getDuracionDias() { return duracionDias; }
    public double getCombustibleRequerido() { return combustibleRequerido; }
    public int getTripulacionNecesaria() { return tripulacionNecesaria; }
    public double getCargaUtilKg() { return cargaUtilKg; }
    public EstadoMision getEstado() { return estado; }
    public Nave getNaveAsignada() { return naveAsignada; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setDestino(String destino) { this.destino = destino; }
    public void setDistanciaKm(double distanciaKm) { this.distanciaKm = distanciaKm; }
    public void setFechaLanzamiento(LocalDate fechaLanzamiento) { this.fechaLanzamiento = fechaLanzamiento; }
    public void setDuracionDias(int duracionDias) { this.duracionDias = duracionDias; }
    public void setCombustibleRequerido(double combustibleRequerido) { this.combustibleRequerido = combustibleRequerido; }
    public void setTripulacionNecesaria(int tripulacionNecesaria) { this.tripulacionNecesaria = tripulacionNecesaria; }
    public void setCargaUtilKg(double cargaUtilKg) { this.cargaUtilKg = cargaUtilKg; }  
}
