package graph.engine;

import java.util.*;

// TODO: GENERAR COMENTARIOS DOXYGEN

public interface Grafo {

    public HashSet<Integer> getVertices();

    public HashMap<Integer, HashSet<Integer>> getAristas();

    public void setVertices(HashSet<Integer> vertices);

    public void setAristas(HashMap<Integer, HashSet<Integer>> aristas);

    // Método para averiguar el número total de aristas en el grafo.
    public int totalAristas();

    public boolean[][] convertMatrizAdyacencia();

    public boolean[][] convertirMatrizIncidencia();

    public ArrayList<Integer> listaGrados();

    public HashMap<Integer, HashSet<Integer>> listaAdyacencia();

    public void mostrarMatriz(boolean[][] matrix);

    public void mostrarLista(HashMap<Integer, HashSet<Integer>> adyacencias);

    public void mostrarGrados(ArrayList<Integer> grados);

    public String BFS(int fuente);

    public String DFS(int fuente);
}

