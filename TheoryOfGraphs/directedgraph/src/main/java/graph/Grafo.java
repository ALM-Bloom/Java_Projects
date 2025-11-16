package graph;

import java.util.HashSet;
import java.util.HashMap;

// Un grafo por definición es: G = {V, E} donde:
// E: Representa el conjunto de aristas formado por valores-pares no ordenados -> {x, y} pert E, por lo tanto
// x e y son adyacentes.
public class Grafo {

    // V: Representa el conjunto NO vacío de vértices ("nodos")
    private HashSet<Integer> vertices;
    // E: Representa el conjunto de aristas formado por valores-pares no ordenados -> {x, y} pert E, por lo tanto
    // x e y son adyacentes.
    private HashMap<Integer, HashSet<Integer>> aristas;

    public Grafo() {
        super();
    }

    public Grafo(HashSet<Integer> vertices, HashMap<Integer, HashSet<Integer>> aristas) {
        this.vertices = vertices;
        this.aristas = aristas;
    }

    public String toString() {
        //TODO
        return "";
    }

    public HashSet<Integer> getVertices() {
        return vertices;
    }

    public HashMap<Integer, HashSet<Integer>> getAristas() {
        return aristas;
    }

    public void setVertices(HashSet<Integer> vertices) {
        this.vertices = vertices;
    }

    public void setAristas(HashMap<Integer, HashSet<Integer>> aristas) {
        this.aristas = aristas;
    }

    public boolean[][] convertMatrizAdyacencia() {

        // Tamaño de la matriz es n x n
        int tamanio = vertices.size();
        boolean[][] matriz_adyacencia = new boolean[tamanio][tamanio];

        for (int i = 0; i < vertices.size(); i++) {
            HashSet<Integer> nodos = aristas.get(i);

            if (nodos == null) { continue; }
            for (int j : nodos) {
                matriz_adyacencia[i][j] = true;
                matriz_adyacencia[j][i] = true;
            }
        }
        return matriz_adyacencia;
    }
}
