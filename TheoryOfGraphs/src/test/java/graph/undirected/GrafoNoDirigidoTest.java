package graph.undirected;
import static org.junit.jupiter.api.Assertions.*;

import graph.engine.Grafo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.*;


class GrafoNoDirigidoTest {

    private HashMap<Integer, HashSet<Integer>> aristas;
    private  HashSet<Integer> vertices;

    void grafoConexo() {
        /* Lista de adyacencia del grafo para mayor claridad:
           0: 1, 3
           1: 0, 2, 4
           2: 1, 5
           3: 0
           4: 1
           5: 2
         */
        vertices = new HashSet<>(Set.of(0, 1, 2, 3, 4, 5));

        aristas = new HashMap<>();
        aristas.putIfAbsent(0, new HashSet<>(Set.of(1, 3)));
        aristas.putIfAbsent(1, new HashSet<>(Set.of(2, 4)));
        aristas.putIfAbsent(2, new HashSet<>(Set.of(5)));
        aristas.putIfAbsent(3, new HashSet<>(Set.of()));
        aristas.putIfAbsent(4, new HashSet<>(Set.of()));
        aristas.putIfAbsent(5, new HashSet<>(Set.of()));
    }

    void grafoNoConexo() {

        /* Lista de adyacencia del grafo para mayor claridad:
           0: 1
           1: 0, 2, 4
           2: 1
           3: 5
           4: 1
           5: 3
         */
        vertices = new HashSet<>(Set.of(0, 1, 2, 3, 4, 5));

        aristas = new HashMap<>();
        aristas.putIfAbsent(0, new HashSet<>(Set.of(1)));
        aristas.putIfAbsent(1, new HashSet<>(Set.of(2, 4)));
        aristas.putIfAbsent(2, new HashSet<>(Set.of(1)));
        aristas.putIfAbsent(3, new HashSet<>(Set.of(5)));
        aristas.putIfAbsent(4, new HashSet<>(Set.of()));
        aristas.putIfAbsent(5, new HashSet<>(Set.of()));
    }

    @Nested
    class GrafoConexo {

        @Test
        void testAdyacencia_GrafoConexo() {
            grafoConexo();
            Grafo grafo = new GrafoNoDirigido(vertices, aristas);
            boolean[][] matriz_adyacencia = new boolean[][]{
                    {false, true, false, true, false, false},
                    {true, false, true, false, true, false},
                    {false, true, false, false, false, true},
                    {true, false, false, false, false, false},
                    {false, true, false, false, false, false},
                    {false, false, true, false, false, false}
            };

            assertArrayEquals(matriz_adyacencia, grafo.convertMatrizAdyacencia());
        }

    @Test
    void testBFS_GrafoConexo() {
        grafoConexo();
        Grafo grafo = new GrafoNoDirigido(vertices, aristas);

        @Test
        void testGrados_GrafoConexo() {
            grafoConexo();
            Grafo grafo = new GrafoNoDirigido(vertices, aristas);
            ArrayList<Integer> grados = new ArrayList<>();
            grados.add(2);
            grados.add(3);
            grados.add(2);
            grados.add(1);
            grados.add(1);
            grados.add(1);
            assertEquals(grados, grafo.listaGrados());
        }

        assertEquals("BFS Finalizado | El grafo es conexo", resultado);
    }

            String resultado = grafo.DFS(0);

            assertEquals("DFS Finalizado | El grafo es conexo", resultado);
        }

    }

}
