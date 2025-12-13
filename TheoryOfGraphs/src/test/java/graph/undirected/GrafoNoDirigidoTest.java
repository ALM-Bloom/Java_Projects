package graph.undirected;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;


class GrafoNoDirigidoTest {

    @Test
    void testBFS_GrafoConexo() {

        HashSet<Integer> vertices = new HashSet<>(Set.of(0, 1, 2, 3));

        HashMap<Integer, HashSet<Integer>> aristas = new HashMap<>();
        aristas.putIfAbsent(0, new HashSet<>(Set.of(1, 3)));
        aristas.putIfAbsent(1, new HashSet<>(Set.of(0, 2)));
        aristas.putIfAbsent(2, new HashSet<>(Set.of(1)));
        aristas.putIfAbsent(3, new HashSet<>(Set.of(0)));

        GrafoNoDirigido grafo = new GrafoNoDirigido(vertices, aristas);

        String resultado = grafo.BFS(0);

        assertEquals("BFS Finalizado | El grafo es conexo", resultado);
    }

    
}
