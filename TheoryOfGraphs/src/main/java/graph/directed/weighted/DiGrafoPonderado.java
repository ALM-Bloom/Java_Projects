package graph.directed;


import graph.engine.Grafo;

import java.util.HashSet;
import java.util.HashMap;

public class DiGrafoPonderado implements Grafo {

    HashSet<Integer> vertices;
    HashMap<Integer, HashSet<Arista>> aristas;

    /**
     * Constructor por defecto
     */
    public DiGrafoPonderado() {
        super();
    }

    /**
     * Constructor general para el Grafo Ponderado
     * @param vertices el conjunto de vértices del grafo
     * @param aristas el conjunto de aristas del grafo, donde cada arista lleva adjunto su peso.
     *                Es decir, el HashMap lo conforma un trío de elementos: el vértice de origen,
     *                el vértice de destino y el peso de la arista.
     */
    public DiGrafoPonderado(HashSet<Integer> vertices, HashMap<Integer, HashSet<Arista>> aristas) {

    }

}
