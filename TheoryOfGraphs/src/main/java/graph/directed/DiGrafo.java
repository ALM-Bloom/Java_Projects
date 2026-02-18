package graph.directed;

import graph.engine.Arista;
import graph.engine.Grafo;

import java.util.*;

public class DiGrafo implements Grafo {

    private HashSet<Integer> vertices;
    private HashMap<Integer, HashSet<Integer>> aristas;

    /**
     * Constructor por defecto
     */
    public DiGrafo() {
        super();
    }

    /**
     * Constructor general de un grafo no Dirigido
     * @param vertices El conjunto de vértices (nodos) de un grafo
     * @param aristas El conjunto de pares de aristas-vértices del grafo.
     */
    public DiGrafo(HashSet<Integer> vertices, HashMap<Integer, HashSet<Integer>> aristas) {
        this.vertices = vertices;
        this.aristas = aristas;
    }

    /**
     * Método para construir el grafo a partir de la lectura de vértices y aristas.
     */
    @Override
    public void crearGrafo() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Introduzca número de vértices");
        int num_vertices = scanner.nextInt();

        HashSet<Integer> vertices = new HashSet<>();
        for (int i = 0; i < num_vertices; i++) {
            vertices.add(i);
        }

        System.out.println("¡Vértices Introducidos!\n");
        System.out.println("Para introducir las aristas y conectarlas seguirá el siguiente procedimiento:\n" +
                "Iterará sobre los vértices comenzando desde el primero ('1'), e introducirá el vértice que" +
                " desea que sea adyacente. Para continuar al siguiente vértice podrá introducir 's'.");

        HashMap<Integer, HashSet<Integer>> aristas = new HashMap<>();
        HashMap<Integer, Integer> vert_introducidos;
        int cont = 0;
        while (cont < num_vertices) {
            System.out.println("Se encuentra en el vértice " + (cont + 1));
            System.out.println("Introduzca el vértice adyacente a " + (cont + 1) + " pase al siguiente introduciendo 's'." +
                    " O finalice la construcción con 'h'");

            String option = scanner.next();
            option = option.toLowerCase();

            if (option.equals("s")) {
                cont++;
            } else if (option.equals("h")) {
                break;
            } else {
                int vert_ady = Integer.parseInt(option) - 1;
                if (vert_ady < 0 || vert_ady >= num_vertices) {
                    throw new IllegalArgumentException("Vértice introducido no incluido en el grafo");
                }
                aristas.putIfAbsent(cont, new HashSet<>());
                if (aristas.get(cont).contains(vert_ady)) {
                    System.out.println("El vértice " + (cont + 1) + " ya se encuentra adyacente a " + (vert_ady + 1) +
                            ". Introduzca otro, por favor.\n");
                    continue;
                }
                aristas.get(cont).add(vert_ady);
                System.out.println("El vértice " + (cont + 1) + " ahora es adyacente a " + (vert_ady + 1) + "\n");
            }
        }
        this.aristas = aristas;
        this.vertices = vertices;
    }

    /**
     * Metodo de obtención del conjunto de vértices
     * @return Conjunto de vértices del grafo
     */
    @Override
    public HashSet<Integer> getVertices() {
        return vertices;
    }

    /**
     * Metodo de obtención del conjunto de pares de aristas-vértices
     * @return El conjunto de pares de aristas-vértices
     */
    @Override
    public HashMap<Integer, HashSet<Integer>> getAristas() {
        return aristas;
    }

    /**
     * Metodo para la asignación manual del conjunto de vértices
     * @param vertices El conjunto de vértices a asignar
     */
    @Override
    public void setVertices(HashSet<Integer> vertices) {
        this.vertices = vertices;
    }

    /**
     * Metodo para la asignación manual del conjunto de pares aristas-vértices
     * @param aristas El conjunto de pares vértice-aristas a asignar
     */
    @Override
    public void setAristas(HashMap<Integer, HashSet<Integer>> aristas) {
        this.aristas = aristas;
    }

    /**
     * Metodo para obtener el número total de aristas conectadas dentro del grafo.
     * @return El conteo total de las aristas dentro del grafo.
     */
    @Override
    public int totalAristas() {
        int sum = 0;

        for (int i = 0; i < vertices.size(); i++) {
            HashSet<Integer> nodos = aristas.get(i);
            if (nodos == null) { continue; }
            sum = sum + nodos.size();
        }

        return sum;
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
            HashSet<Integer> nodos = aristas.get(i);

            if (nodos == null) { continue; }
            for (int j : nodos) {
                matriz_adyacencia[i][j] = true;
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
            HashSet<Integer> nodos = aristas.get(i);

            if (nodos == null) { continue; }
            for (int j : nodos) {
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
            HashSet<Integer> nodos = aristas.get(i);

            if (nodos == null) { continue; }
            for (int j : nodos) {
                adyacencias.get(i).add(j);
            }
        }

        return adyacencias;
    }

    /**
     * Metodo para imprimir por pantalla cualesquiera de las dos matrices obtenibles de la clase grafo.
     * Téngase en cuenta que para mayor clara interpretación se ha optado por imprimir los valores de la matriz como
     * 0 (falso) y 1 (verdadero).
     * @param matrix Matriz booleana a imprimir por pantalla (incidencia o adyacencia).
     * @see  DiGrafo#convertMatrizAdyacencia()
     * @see  DiGrafo#convertirMatrizIncidencia()
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
     * @see DiGrafo#listaAdyacencia()
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
     * @see DiGrafo#listaGrados()
     */
    @Override
    public void mostrarGrados(ArrayList<Integer> grados) {
        for (int i = 0; i < grados.size(); i++) {
            System.out.print(grados.get(i) + ", ");
        }
    }

    /**
     * Metodo que implementa el algoritmo BFS. Se utiliza la estructura de datos de cola para recorrer los vértices
     * encontrados. Además, define una visualización iterativa de la búsqueda.
     * El algoritmo también tiene la función de detectar mediante la finalización de la búsqueda si el grafo es conexo.
     * Para ello devuelve una cadena de texto según el resultado.
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
     * Metodo que implementa el algoritmo DFS. Se utiliza la estructura de datos de pila para recorrer los vértices
     * encontrados. Además, define una visualización iterativa de la búsqueda.
     * El algoritmo también tiene la función de detectar mediante la finalización de la búsqueda si el grafo es conexo.
     * Para ello devuelve una cadena de texto según el resultado.
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
            return "DFS Finalizado | Desde el vértice de partida " + (fuente + 1) + " el grafo es conexo";
        }

        return "DFS Finalizado | Desde el vértice de partida " + (fuente + 1) + " el grafo no es conexo";
    }
}
