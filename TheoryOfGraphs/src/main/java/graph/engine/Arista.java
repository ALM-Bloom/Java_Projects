package graph.engine;

public class Arista {

    private Integer destino;
    private double peso;

    /*
     * Constructor por defecto
     */
    public Arista() {
        super();
    }

    /**
     * Constructor general para la clase Arista, que representa una arista con peso dentro de un grafo ponderado.
     * @param destino el vértice de destino al que apunta la arista.
     * @param peso el peso asociado a la arista, que puede representar una distancia, costo o cualquier otra medida relevante.
     */

    public Arista(Integer destino, double peso) {
        this.destino = destino;
        this.peso = peso;
    }

    /**
     * Método getter para obtener el vértice de destino de la arista.
     * @return el vértice de destino al que apunta la arista.
     */
    public Integer getDestino() {
        return destino;
    }

    /**
     * Método setter para establecer el vértice de destino de la arista.
     * @param destino el vértice de destino al que se desea apuntar con la arista.
     */
    public void setDestino(Integer destino) {
        this.destino = destino;
    }

    /**
     * Método getter para obtener el peso asociado a la arista.
     * @return el peso de la arista, que puede representar una distancia, costo o cualquier otra medida relevante.
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Método setter para establecer el peso de la arista.
     * @param peso el peso que se desea asignar a la arista, que puede representar una distancia, costo o cualquier otra medida relevante.
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }
}
