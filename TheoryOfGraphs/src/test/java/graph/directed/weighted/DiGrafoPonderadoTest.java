package graph.directed.weighted;

import graph.engine.Arista;
import graph.engine.Grafo;
import graph.engine.GrafoPonderado;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DiGrafoPonderadoTest {

    DiGrafoPonderado grafoPonderado() {
        /* Lista de adyacencia del grafo para mayor claridad:
            0: (1, 2.5), (3, 1.0)
            1: (2, 3.0), (4, 4.5)
            2: (5, 1.5)
            3: (0, 2.0)
            4: (0, 3.5)
            5: (0, 2.0)
        */
        HashSet<Integer> vertices;
        HashMap<Integer, HashSet<Arista>> aristas;

        vertices = new HashSet<>(Set.of(0, 1, 2, 3, 4, 5));

        aristas = new HashMap<>();
        aristas.putIfAbsent(0, new HashSet<>(Set.of(new Arista(1, 2.5), new Arista(3, 1.0))));
        aristas.putIfAbsent(1, new HashSet<>(Set.of(new Arista(2, 3.0), new Arista(4, 4.5))));
        aristas.putIfAbsent(2, new HashSet<>(Set.of(new Arista(5, 1.5))));
        aristas.putIfAbsent(3, new HashSet<>(Set.of(new Arista(0, 2.0))));
        aristas.putIfAbsent(4, new HashSet<>(Set.of(new Arista(0, 3.5))));
        aristas.putIfAbsent(5, new HashSet<>(Set.of(new Arista(0, 2.0))));

        return new DiGrafoPonderado(vertices, aristas);
    }

    @Test
    void test_totalAristas() {
        DiGrafoPonderado grafo = grafoPonderado();
        assertEquals(8, grafo.totalAristas(),
                "El número total de aristas en el grafo ponderado debería ser 8.");
    }

    @Test
    void test_matrizAdyacencia() {
        DiGrafoPonderado grafo = grafoPonderado();
        boolean[][] matriz_adyacencia = new boolean[][] {
                {false, true, false, true, false, false},
                {false, false, true, false, true, false},
                {false, false, false, false, false, true},
                {true, false, false, false, false, false},
                {true, false, false, false, false, false},
                {true, false, false, false, false, false}
        };
        assertArrayEquals(matriz_adyacencia, grafo.convertMatrizAdyacencia(),
                "La matriz de adyacencia del grafo ponderado no coincide con la esperada.");
    }

    @Test
    void test_matrizIncidencia() {
        DiGrafoPonderado grafo = grafoPonderado();
        boolean[][] matriz_incidencia = new boolean[][] {
                {true, true, false, false, false, false, false, false},
                {false, false, true, true, false, false, false, false},
                {false, false, false, false, true, false, false, false},
                {false, false, false, false, false, true, false, false},
                {false, false, false, false, false, false, true, false},
                {false, false, false, false, false, false, false, true}
        };
        
        assertArrayEquals(matriz_incidencia, grafo.convertirMatrizIncidencia(),
                "La matriz de incidencia del grafo ponderado no coincide con la esperada.");
    }

    @Test
    void test_listaGrados() {
        DiGrafoPonderado grafo = grafoPonderado();
        ArrayList<Integer> grados = new ArrayList<>();
        grados.add(2);
        grados.add(2);
        grados.add(1);
        grados.add(1);
        grados.add(1);
        grados.add(1);
        assertEquals(grados, grafo.listaGrados());
        assertEquals(grados, grafo.listaGrados(),
                "La lista de grados del grafo ponderado no coincide con la esperada.");
    }

    @Test
    void test_listaAdyacencia() {
        DiGrafoPonderado grafo = grafoPonderado();
        HashMap<Integer, HashSet<Integer>> elemento_lista = new HashMap<>();
        elemento_lista.put(0, new HashSet<>(Set.of(1, 3)));
        elemento_lista.put(1, new HashSet<>(Set.of(2, 4)));
        elemento_lista.put(2, new HashSet<>(Set.of(5)));
        elemento_lista.put(3, new HashSet<>(Set.of(0)));
        elemento_lista.put(4, new HashSet<>(Set.of(0)));
        elemento_lista.put(5, new HashSet<>(Set.of(0)));

        assertEquals(elemento_lista, grafo.listaAdyacencia(),
                "La lista de adyacencia del grafo ponderado no coincide con la esperada.");
    }

    @Test
    void test_matrizCostos() {
        DiGrafoPonderado grafo = grafoPonderado();
        double[][] matriz_costos = new double[][] {
                {0.0, 2.5, 0.0, 1.0, 0.0, 0.0},
                {0.0, 0.0, 3.0, 0.0, 4.5, 0.0},
                {0.0, 0.0, 0.0, 0.0, 0.0, 1.5},
                {2.0, 0.0, 0.0, 0.0, 0.0, 0.0},
                {3.5, 0.0, 0.0, 0.0, 0.0, 0.0},
                {2.0, 0.0, 0.0, 0.0, 0.0, 0.0}
        };

        assertArrayEquals(matriz_costos, grafo.convertirMatrizCostos(),
                "La matriz de costos del grafo ponderado no coincide con la esperada.");
    }

    @Test
    void testBFS_GrafoNoConexo() {
        DiGrafoPonderado grafo = grafoPonderado();

        String resultado = grafo.BFS(0);

        assertEquals("BFS Finalizado | El grafo es conexo", resultado);
    }

    @Test
    void testDFS_GrafoNoConexo() {
        DiGrafoPonderado grafo = grafoPonderado();

        String resultado = grafo.DFS(0);

        assertEquals("DFS Finalizado | Desde el vértice de partida 0 el grafo es conexo", resultado);
    }
}