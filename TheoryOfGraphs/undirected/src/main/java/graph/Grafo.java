package graph;

import java.util.*;

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

    public void mostrarMatriz(boolean[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print((matrix[i][j] ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    public void mostrarLista(HashMap<Integer, HashSet<Integer>> adyacencias) {
        for (Integer vert : adyacencias.keySet()) {
            System.out.print((vert + 1) + ": ");
            for (Integer vecino : adyacencias.get(vert)) {
                System.out.print((vecino + 1) + " ");
            }
            System.out.println();
        }
    }

    public void mostrarGrados(ArrayList<Integer> grados) {
        for (int i = 0; i < grados.size(); i++) {
            System.out.print(grados.get(i) + ", ");
        }
    }

    public String BFS(int fuente) {
        HashMap<Integer, HashSet<Integer>> list_adyacencia = listaAdyacencia();
        Queue<Integer> no_visitados = new LinkedList<>();
        String text_visual = "";
        no_visitados.add(fuente);

        HashSet<Integer> visitados = new HashSet<>();
        visitados.add(fuente);

        text_visual = text_visual + (fuente + 1) + "\n";

        int cont = 0;
        // Comienzo del algoritmo BFS
        while (!no_visitados.isEmpty()) {
            int vert_ext = no_visitados.poll();

            // Ajustes para la visualización
            System.out.println("ITERACIÓN " + cont + " | Vértice siendo analizado: " + (vert_ext + 1));
            ArrayList<Integer> visualizacion = new ArrayList<>();
            // -----------------------------

            for (int vertice : list_adyacencia.get(vert_ext)) {
                if (!visitados.contains(vertice)) {
                    visitados.add(vertice);
                    no_visitados.add(vertice);
                    visualizacion.add(vertice);
                }
            }

            // Visualización iterativa del recorrido
            if (!visualizacion.isEmpty()) {
                for (int i = 0; i < visualizacion.size(); i++) {
                    text_visual = text_visual + (visualizacion.get(i) + 1) + "    ";
                }
                text_visual = text_visual +  " padre de los nodos: " + (vert_ext + 1) + "\n";
            }
            System.out.println(text_visual);
            cont++;
        }

        if (visitados.containsAll(vertices)) {
            return "BFS Finalizado | El grafo es conexo";
        }

        return "BFS Finalizado | El grafo no es conexo";
    }

    public String DFS(int fuente) {
        HashMap<Integer, HashSet<Integer>> list_adyacencia = listaAdyacencia();
        Stack<Integer> no_visitados = new Stack<>();
        String text_visual = "";
        no_visitados.add(fuente);

        HashSet<Integer> visitados = new HashSet<>();
        visitados.add(fuente);

        int cont = 0;
        while (!no_visitados.empty()) {
            int vert_ext = no_visitados.pop();

            // Ajustes para la visualización
            System.out.println("ITERACIÓN " + cont + " | Vértice siendo analizado: " + (vert_ext + 1));
            ArrayList<Integer> visualizacion = new ArrayList<>();
            // -----------------------------

            // Para introducir los vértices en la pila de izquierda-derecha
            List<Integer> lista = new ArrayList<>(list_adyacencia.get(vert_ext));
            Collections.reverse(lista);

            for (int vertice : lista) {
                if (!visitados.contains(vertice)) {
                    no_visitados.add(vertice);
                    visitados.add(vertice);
                }
            }

            visualizacion.add(vert_ext);
            // Visualización iterativa del recorrido
            for (int i = 0; i < visualizacion.size(); i++) {
                text_visual = text_visual + (visualizacion.get(i) + 1) + "    ";
            }
            text_visual = text_visual + "\n";
            System.out.println(text_visual);
            cont++;
        }

        if (visitados.containsAll(vertices)) {
            return "DFS Finalizado | El grafo es conexo";
        }

        return "DFS Finalizado | El grafo no es conexo";
    }
}
