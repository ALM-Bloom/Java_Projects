/*
 * @brief: Clase Nave que representa una nave espacial con nombre, capacidad de carga y autonomía.
 */
public class Nave {
    private String nombre;
    private int id;
    private double cap_carga;
    private double autonomia;
    private boolean disponibilidad;

    public Nave (String nombre, double cap_carga, double autonomia) 
    { 
      this.id = (int) (Math.random() * 10000); 
      this.nombre = nombre;
      this.cap_carga = cap_carga;  
      this.autonomia = autonomia;
      this.disponibilidad = true;
    }

    //Getters
    public String getNombre() { return nombre; }
    public double getCapacidadCarga() { return cap_carga; }
    public double getAutonomia() { return autonomia; }
    public int getId() { return id; }
    public boolean getDisponibilidad() { return disponibilidad; }

    //Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCapacidadCarga(double cap_carga) { this.cap_carga = cap_carga; }
    public void setAutonomia(double autonomia) { this.autonomia = autonomia; }
    public void setDisponibilidad(boolean disponibilidad) { this.disponibilidad = disponibilidad;}
}
