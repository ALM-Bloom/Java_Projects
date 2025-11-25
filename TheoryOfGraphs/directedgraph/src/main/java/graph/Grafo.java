package graph;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

// TODO: GENERAR COMENTARIOS DOXYGEN

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

    // Método para averiguar el número total de aristas en el grafo.
    public int totalAristas() {
        int sum = 0;

        for (int i = 0; i < vertices.size(); i++) {
            HashSet<Integer> nodos = aristas.get(i);
            if (nodos == null) { continue; }
            sum = sum + nodos.size();
        }

        return sum;
    }

    public boolean[][] convertMatrizAdyacencia() {

        // Tamaño de la matriz es Nº vértices x Nº vértices
        int tamanio = vertices.size();
        boolean[][] matriz_adyacencia = new boolean[tamanio][tamanio];

        // Recorremos el subconjunto de aristas mediante el mapa nodo-conjunto de vértices alcanzables.
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

    public boolean[][] convertirMatrizIncidencia() {

        // Tamaño de la matriz es Nº vértices x Nº total de aristas
        int tam_vertices = vertices.size();
        int tam_aristas = totalAristas();
        boolean[][] matriz_incidencia = new boolean[tam_vertices][tam_aristas];

        // El contador establece la posición numérica de la arista en cuestión.
        int cont_aristas = 0;

        // Similar a la matriz de adyacencia salvo que en este caso se marcan las posiciones
        // basándonos en el contador
        for (int i = 0; i < vertices.size(); i++) {
            HashSet<Integer> nodos = aristas.get(i);

            if (nodos == null) { continue; }
            for (int j : nodos) {
                matriz_incidencia[i][cont_aristas] = true;
                matriz_incidencia[j][cont_aristas] = true;
                cont_aristas++;
            }
        }

        return matriz_incidencia;
    }

    public ArrayList<Integer> listaGrados() {

        // El tamaño del arreglo será el del número de vértices
        int tamanio = vertices.size();

        // Habrá que inicializar la lista de grados a 0.
        ArrayList<Integer> grados = new ArrayList<Integer>(tamanio);

        for (int k = 0; k < vertices.size(); k++) {
            grados.add(0);
        }

        for (int i = 0; i < vertices.size(); i++) {
            HashSet<Integer> nodos = aristas.get(i);

            if (nodos == null) { continue; }
            for (int j : nodos) {
                grados.set(i, grados.get(i) + 1);
                grados.set(j, grados.get(j) + 1);
            }
        }

        return grados;
    }

    public HashMap<Integer, HashSet<Integer>> listaAdyacencia() {

        // Inicializamos la lista con los vértices de nuestro conjunto.
        HashMap<Integer, HashSet<Integer>> adyacencias = new HashMap<Integer, HashSet<Integer>>();

        for (int k = 0; k < vertices.size(); k++) {
            adyacencias.putIfAbsent(k, new HashSet<Integer>());
        }

        for (int i = 0; i < vertices.size(); i++) {
            HashSet<Integer> nodos = aristas.get(i);

            if (nodos == null) { continue; }
            for (int j : nodos) {
                adyacencias.get(i).add(j);
                adyacencias.get(j).add(i);
            }
        }

            return adyacencias;
    }
}
