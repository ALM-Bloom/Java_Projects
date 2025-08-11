/*
 * @brief: Clase Central que actúa como punto de control para gestionar naves y misiones espaciales.
 * Contiene mapas para almacenar naves y misiones, donde las claves son los IDs o nombres de las naves y misiones.
 */

import java.util.Map;

public class Central {
    private Map<Integer, Nave> naves;         
    private Map<Integer, Mision> misiones;   

    void registrarNave(Nave nave) {
        naves.put(nave.getId(), nave);
    }

    void registrarMision(Mision mision) {
        misiones.put(mision.getId(), mision);
    }

    void listarNaves() {
        int cont_disp = 0;
        for (Map.Entry<Integer, Nave> entry : naves.entrySet()) {
            Nave nave = entry.getValue();
            if (nave.getDisponibilidad()) {
            System.out.println("Modelo: " + nave.getNombre() + " con identificador: " + nave.getId()
            + " autonomía: " + nave.getAutonomia() + " y capacidad de carga de " + nave.getCapacidadCarga());
            cont_disp += 1;
            }
        }
        if (cont_disp == 0) {
            System.out.println("Todas las naves están asignadas a alguna misión");
        }
    }




}
