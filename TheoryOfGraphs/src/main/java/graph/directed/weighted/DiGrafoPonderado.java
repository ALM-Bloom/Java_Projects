package graph.directed.weighted;


import graph.directed.DiGrafo;
import graph.engine.Arista;
import graph.engine.Grafo;
import graph.engine.GrafoPonderado;

import java.util.*;

public class DiGrafoPonderado implements GrafoPonderado {

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
        this.vertices = vertices;
        this.aristas = aristas;
    }

    /**
     * Método getter para obtener el conjunto de vértices del grafo ponderado.
     * @return el conjunto de vértices del grafo ponderado.
     */
    @Override
    public HashSet<Integer> getVertices() {
        return vertices;
    }

    /**
     * Método getter para obtener el conjunto de aristas del grafo ponderado, donde cada arista lleva adjunto su peso.
     * @return el conjunto de aristas del grafo ponderado, representado como un HashMap que asocia cada vértice
     *         de origen
     *         con un conjunto de objetos Arista, donde cada objeto Arista contiene el vértice de destino y
     *         el peso de la arista.
     */
    @Override
    public HashMap<Integer, HashSet<Arista>> getAristas() {
        return aristas;
    }

    /**
     * Método setter para establecer manualmente el conjunto de vértices del grafo ponderado.
     * @param vertices el conjunto de vértices que se desea asignar al grafo ponderado.
     */
    @Override
    public void setVertices(HashSet<Integer> vertices) {
        this.vertices = vertices;
    }

    /**
     * Método setter para establecer manualmente el conjunto de aristas del grafo ponderado
     * @param aristas el conjunto de aristas que se desea asignar al grafo ponderado
     */
    @Override
    public void setAristas(HashMap<Integer, HashSet<Arista>> aristas) {
        this.aristas = aristas;
    }

    /**
     * Método para calcular el total de aristas presentes en el grafo ponderado.
     * @return el número total de aristas presentes en el grafo ponderado, contando cada arista con su peso asociado.
     */
    @Override
    public int totalAristas() {
        int total_aristas = 0;
        for (int i = 0; i < vertices.size(); i++) {
            total_aristas += aristas.get(i).size();
        }
        return total_aristas;
    }

    /**
     * Metodo para la transformación del grafo en una matriz de adyacencia.
     * Dentro de la matriz el valor 'true' corresponde a la existencia de conexión con dicho vértice y 'false' a
     * lo contrario.
     * @return Un arreglo booleano de dos dimensiones que corresponde a la matriz de adyacencia del grafo.
     * @see  <a href = "https://es.wikipedia.org/wiki/Matriz_de_adyacencia"> Véase la descripción de una matriz
     * de adyacencia en el artículo adjunto.</a>
     */
    @Override
    public boolean[][] convertMatrizAdyacencia() {

        // Tamaño de la matriz es Nº vértices x Nº vértices
        int dimension = vertices.size();
        boolean[][] matriz_adyacencia = new boolean[dimension][dimension];

        // Recorremos el subconjunto de aristas mediante el mapa nodo-conjunto de vértices alcanzables.
        for (int i = 0; i < vertices.size(); i++) {
            HashSet<Arista> nodos = aristas.get(i);

            if (nodos == null) { continue; }
            for (Arista j : nodos) {
                matriz_adyacencia[i][j.getDestino()] = true;
            }
        }
        return matriz_adyacencia;
    }

    /**
     * Metodo para la transformación del grafo en una matriz de incidencia.
     * Dentro de la matriz el valor 'true' corresponde a la existencia de conexión con dicho vértice y 'false' a
     * lo contrario.
     * @return Un arreglo booleano de dos dimensiones que corresponde a la matriz de incidencia del grafo.
     * @see  <a href = "https://es.wikipedia.org/wiki/Matriz_de_incidencia"> Véase la descripción de una matriz
     * de incidencia en el artículo adjunto.</a>
     */
    @Override
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
            HashSet<Arista> nodos = aristas.get(i);

            if (nodos == null) { continue; }
            for (Arista j : nodos) {
                matriz_incidencia[i][cont_aristas] = true;
                cont_aristas++;
            }
        }

        return matriz_incidencia;
    }

    /**
     * Metodo para la obtención de la lista de grados de los vértices del grafo
     * @return Un arreglo correspondiente a la secuencia de grados ordenada para cada vértice
     * @see  <a href = "https://es.wikipedia.org/wiki/Grado_(teor%C3%ADa_de_grafos)"> Véase la definición
     * matemática del grado de un vértice en el siguiente artículo.</a>
     */
    @Override
    public ArrayList<Integer> listaGrados() {

        // El tamaño del arreglo será el del número de vértices
        int tamanio = vertices.size();

        // Habrá que inicializar la lista de grados a 0.
        ArrayList<Integer> grados = new ArrayList<Integer>(tamanio);

        for (int k = 0; k < vertices.size(); k++) {
            grados.add(0);
        }

        for (int i = 0; i < vertices.size(); i++) {
            grados.set(i, aristas.get(i).size());
        }

        return grados;
    }

    /**
     * Metodo para la obtención de la lista de adyacencia de un grafo.
     * @return Un mapa correspondiente a los valores vértice-conjunto de vértices alcanzables del grafo.
     * @see  <a href = "https://es.wikipedia.org/wiki/Lista_de_adyacencia"> Véase la descripción de una lista
     * de adyacencia en el siguiente artículo.</a>
     */
    @Override
    public HashMap<Integer, HashSet<Integer>> listaAdyacencia() {

        // Inicializamos la lista con los vértices de nuestro conjunto.
        HashMap<Integer, HashSet<Integer>> adyacencias = new HashMap<Integer, HashSet<Integer>>();

        for (int k = 0; k < vertices.size(); k++) {
            adyacencias.putIfAbsent(k, new HashSet<Integer>());
        }

        for (int i = 0; i < vertices.size(); i++) {
            HashSet<Arista> nodos = aristas.get(i);

            if (nodos == null) { continue; }
            for (Arista j : nodos) {
                adyacencias.get(i).add(j.getDestino());
            }
        }

        return adyacencias;
    }

    /**
     * Metodo para imprimir por pantalla cualesquiera de las dos matrices obtenibles de la clase grafo.
     * Téngase en cuenta que para mayor clara interpretación se ha optado por imprimir los valores de la matriz como
     * 0 (falso) y 1 (verdadero).
     * @param matrix Matriz booleana a imprimir por pantalla (incidencia o adyacencia).
     * @see  DiGrafoPonderado#convertMatrizAdyacencia()
     * @see  DiGrafoPonderado#convertirMatrizIncidencia()
     */
    @Override
    public void mostrarMatriz(boolean[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print((matrix[i][j] ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Metodo para imprimir por pantalla la lista de adyacencia.
     * @param adyacencias Lista de adyacencia a mostrar por pantalla.
     * @see DiGrafoPonderado#listaAdyacencia()
     */
    @Override
    public void mostrarLista(HashMap<Integer, HashSet<Integer>> adyacencias) {
        for (Integer vert : adyacencias.keySet()) {
            System.out.print((vert + 1) + ": ");
            for (Integer vecino : adyacencias.get(vert)) {
                System.out.print((vecino + 1) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Metodo para mostrar la secuencia de los grados de cada vértice por pantalla.
     * @param grados La secuencia de los grados de cada vértice.
     * @see DiGrafoPonderado#listaGrados()
     */
    @Override
    public void mostrarGrados(ArrayList<Integer> grados) {
        for (int i = 0; i < grados.size(); i++) {
            System.out.print(grados.get(i) + ", ");
        }
    }

    /**
     * Metodo que implementa el algoritmo BFS para grafo ponderado.
     * @param fuente Vértice de partida para la búsqueda.
     * @return Cadena de texto que contendrá si el grafo es o no conexo.
     * @see <a href = "https://es.wikipedia.org/wiki/B%C3%BAsqueda_en_anchura"> El siguiente artículo explica en
     * profundidad el algoritmo.</a>
     */
    @Override
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

    /**
     * Metodo que implementa el algoritmo DFS para grafo ponderado.
     * @param fuente Vértice de partida para la búsqueda.
     * @return Cadena de texto que contendrá si el grafo es o no conexo.
     * @see <a href = "https://es.wikipedia.org/wiki/B%C3%BAsqueda_en_profundidad"> El siguiente artículo explica en
     * profundidad el algoritmo.</a>
     */
    @Override
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
            return "DFS Finalizado | Desde el vértice de partida " + fuente + " el grafo es conexo";
        }

        return "DFS Finalizado | Desde el vértice de partida " + fuente + " el grafo no es conexo";
    }

    /**
     * Metodo que realiza la conversión a una matriz de costos, en cada posición de la matriz se encuentra el peso
     * de la arista que conecta ambos vértices, o un valor infinito (0) en caso de que no exista conexión entre sendos
     * vértices.
     * @return Un arreglo de dos dimensiones que corresponde a la matriz de costes del grafo.
     */
    public double[][] convertirMatrizCostos() {
        // Tamaño de la matriz será Nº vértices x Nº vértices
        int dimension = vertices.size();
        double[][] matriz_costos = new double[dimension][dimension];

        for (int i = 0; i < vertices.size(); i++) {
            HashSet<Arista> nodos = aristas.get(i);
            for (Arista j : nodos) {
                matriz_costos[i][j.getDestino()] = j.getPeso();
            }
        }
        return matriz_costos;
    }
}
