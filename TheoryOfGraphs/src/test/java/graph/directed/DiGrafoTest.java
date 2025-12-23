package graph.directed;

import graph.engine.Grafo;
import graph.undirected.GrafoNoDirigido;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DiGrafoTest {

    DiGrafo grafoConexo() {
        /* Lista de adyacencia del grafo para mayor claridad:
           0: 1, 3
           1: 2, 4
           2: 5
           3: 0
           4: 0
           5: 0
         */
        HashSet<Integer> vertices;
        HashMap<Integer, HashSet<Integer>> aristas;

        vertices = new HashSet<>(Set.of(0, 1, 2, 3, 4, 5));

        aristas = new HashMap<>();
        aristas.putIfAbsent(0, new HashSet<>(Set.of(1, 3)));
        aristas.putIfAbsent(1, new HashSet<>(Set.of(2, 4)));
        aristas.putIfAbsent(2, new HashSet<>(Set.of(5)));
        aristas.putIfAbsent(3, new HashSet<>(Set.of()));
        aristas.putIfAbsent(4, new HashSet<>(Set.of()));
        aristas.putIfAbsent(5, new HashSet<>(Set.of()));

        return new DiGrafo(vertices, aristas);
    }

    DiGrafo grafoNoConexo() {
        /* Lista de adyacencia del grafo para mayor claridad:
           0: 1
           1: 2, 4
           2: 0
           3: 5
           4: 0
           5: 0
         */
        HashSet<Integer> vertices;
        HashMap<Integer, HashSet<Integer>> aristas;

        vertices = new HashSet<>(Set.of(0, 1, 2, 3, 4, 5));

        aristas = new HashMap<>();
        aristas.putIfAbsent(0, new HashSet<>(Set.of(1)));
        aristas.putIfAbsent(1, new HashSet<>(Set.of(2, 4)));
        aristas.putIfAbsent(2, new HashSet<>(Set.of()));
        aristas.putIfAbsent(3, new HashSet<>(Set.of(5)));
        aristas.putIfAbsent(4, new HashSet<>(Set.of()));
        aristas.putIfAbsent(5, new HashSet<>(Set.of()));

        return new DiGrafo(vertices, aristas);
    }

    @Nested
    class GrafoConexo {

        @Test
        void testAdyacencia_GrafoConexo(){
            Grafo grafo = grafoConexo();
            boolean[][] matriz_adyacencia = new boolean[][]{
                    {false, true, false, true, false, false},
                    {false, false, true, false, true, false},
                    {false, false, false, false, false, true},
                    {false, false, false, false, false, false},
                    {false, false, false, false, false, false},
                    {false, false, false, false, false, false}
            };

            assertArrayEquals(matriz_adyacencia, grafo.convertMatrizAdyacencia());
        }

        @Test
        void testIncidencia_GrafoConexo() {
            Grafo grafo = grafoConexo();
            boolean[][] matrizIncidencia = {
                    {true, true, false, false, false},
                    {false, false, true, true, false},
                    {false, false, false, false, true},
                    {false, false, false, false, false},
                    {false, false, false, false, false},
                    {false, false, false, false, false}
            };

            assertArrayEquals(matrizIncidencia, grafo.convertirMatrizIncidencia());
        }

        @Test
        void testGrados_GrafoConexo() {
            Grafo grafo = grafoConexo();
            ArrayList<Integer> grados = new ArrayList<>();
            grados.add(2);
            grados.add(2);
            grados.add(1);
            grados.add(0);
            grados.add(0);
            grados.add(0);
            assertEquals(grados, grafo.listaGrados());
        }

        @Test
        void testLista_GrafoConexo() {
            Grafo grafo = grafoConexo();
            HashMap<Integer, HashSet<Integer>> elemento_lista = new HashMap<>();
            elemento_lista.putIfAbsent(0, new HashSet<>(Set.of(1, 3)));
            elemento_lista.putIfAbsent(1, new HashSet<>(Set.of(2, 4)));
            elemento_lista.putIfAbsent(2, new HashSet<>(Set.of(5)));
            elemento_lista.putIfAbsent(3, new HashSet<>(Set.of()));
            elemento_lista.putIfAbsent(4, new HashSet<>(Set.of()));
            elemento_lista.putIfAbsent(5, new HashSet<>(Set.of()));

            assertEquals(elemento_lista, grafo.listaAdyacencia());
        }

        @Test
        void testBFS_GrafoConexo() {
            Grafo grafo = grafoConexo();

            String resultado = grafo.BFS(0);

            assertEquals("BFS Finalizado | El grafo es conexo", resultado);
        }

        @Test
        void testDFS_GrafoConexo() {
            Grafo grafo = grafoConexo();

            String resultado = grafo.DFS(0);

            assertEquals("DFS Finalizado | Desde el vértice de partida 0 el grafo es conexo", resultado);
        }

        @Test
        void testDFS_GrafoConexoDistFuente() {
            Grafo grafo = grafoNoConexo();

            String resultado = grafo.DFS(1);

            assertEquals("DFS Finalizado | Desde el vértice de partida 1 el grafo no es conexo", resultado);
        }
    }

    @Nested
    class GrafoNoConexo {

        @Test
        void testBFS_GrafoNoConexo() {
            Grafo grafo = grafoNoConexo();

            String resultado = grafo.BFS(0);

            assertEquals("BFS Finalizado | El grafo no es conexo", resultado);
        }

        @Test
        void testDFS_GrafoNoConexo() {
            Grafo grafo = grafoNoConexo();

            String resultado = grafo.DFS(0);

            assertEquals("DFS Finalizado | Desde el vértice de partida 0 el grafo no es conexo", resultado);
        }

    }
}