package graph.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface GrafoPonderado {

    public HashSet<Integer> getVertices();

    public HashMap<Integer, HashSet<Arista>> getAristas();

    public void setVertices(HashSet<Integer> vertices);

    public void setAristas(HashMap<Integer, HashSet<Arista>> aristas);

    public int totalAristas();

    public boolean[][] convertMatrizAdyacencia();

    public boolean[][] convertirMatrizIncidencia();

    public ArrayList<Integer> listaGrados();

    public HashMap<Integer, HashSet<Integer>> listaAdyacencia();

    public void mostrarMatriz(boolean[][] matrix);

    public void MostrarMatrizCostos(double[][] matrix);

    public void mostrarLista(HashMap<Integer, HashSet<Integer>> adyacencias);

    public void mostrarGrados(ArrayList<Integer> grados);

    public String BFS(int fuente);

    public String DFS(int fuente);

    public double[][] convertirMatrizCostos();

    public void crearGrafo();
}
