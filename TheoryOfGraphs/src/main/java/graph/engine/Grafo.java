package graph.engine;

import java.util.*;

/**
 * La interfaz Grafo proporciona la plantilla a definir dado el comportamiento de los digrafos y grafos no dirigidos.
 * @author:  Alejandro M.L
 * @version: 22/12/2025/A
 * @see: {@link graph.undirected.GrafoNoDirigido} {@link graph.directed.DiGrafo}
 */
public interface Grafo {

    public HashSet<Integer> getVertices();

    public HashMap<Integer, HashSet<Integer>> getAristas();

    public void setVertices(HashSet<Integer> vertices);

    public void setAristas(HashMap<Integer, HashSet<Integer>> aristas);

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

