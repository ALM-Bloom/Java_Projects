package graph.undirected;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;


class GrafoNoDirigidoTest {

    private HashMap<Integer, HashSet<Integer>> aristas;
    private  HashSet<Integer> vertices;

    void grafoConexo() {
        vertices = new HashSet<>(Set.of(0, 1, 2, 3));

        aristas = new HashMap<>();
        aristas.putIfAbsent(0, new HashSet<>(Set.of(1, 3)));
        aristas.putIfAbsent(1, new HashSet<>(Set.of(0, 2)));
        aristas.putIfAbsent(2, new HashSet<>(Set.of(1)));
        aristas.putIfAbsent(3, new HashSet<>(Set.of(0)));
    }

    @Test
    void testBFS_GrafoConexo() {
        grafoConexo();
        GrafoNoDirigido grafo = new GrafoNoDirigido(vertices, aristas);

        String resultado = grafo.BFS(0);

        assertEquals("BFS Finalizado | El grafo es conexo", resultado);
    }

    @Test
    void testDFS_GrafoConexo() {
        grafoConexo();
        GrafoNoDirigido grafo = new GrafoNoDirigido(vertices, aristas);

        String resultado = grafo.DFS(0);

        assertEquals("DFS Finalizado | El grafo es conexo", resultado);
    }

}
