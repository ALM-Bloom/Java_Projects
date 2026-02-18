package graph;

import graph.directed.DiGrafo;
import graph.directed.weighted.DiGrafoPonderado;
import graph.engine.Arista;
import graph.engine.Grafo;
import graph.engine.GrafoPonderado;
import graph.undirected.GrafoNoDirigido;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

/**
 * Código correspondiente a la parte clientelar del proyecto.
 * Define la lectura de vértices y sus adyacencias para la creación del grafo y un menú correspondiente a las
 * distintas operaciones de manipulación de éstos.
 * Se valora añadir otro metodo de lectura del grafo, así como abstraer el comportamiento de lectura dentro de la clase
 * Grafo.
 * @author Alejandro M.L
 * @version 23/12/2025/A
 * @see graph.engine.Grafo
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("¿El grafo es ponderado? (s/n)");
        Scanner scanner = new Scanner(System.in);
        String ponderado = scanner.nextLine();
        ponderado = ponderado.toLowerCase();
        System.out.println("El grafo es dirigido o no dirigido? (d/n)");
        String dirigido = scanner.nextLine();
        dirigido = dirigido.toLowerCase();

        Grafo grafo = new GrafoNoDirigido();
        GrafoPonderado grafo_ponderado = new DiGrafoPonderado();

        if (dirigido.equals("d") && ponderado.equals("s")) {
            grafo_ponderado = new DiGrafoPonderado();
            grafo_ponderado.crearGrafo();
        } else if (dirigido.equals("d") && ponderado.equals("n")) {
            grafo = new DiGrafo();
            grafo.crearGrafo();
        } else if (dirigido.equals("n") && ponderado.equals("s")) {
            // TODO: Implementar grafo no dirigido ponderado
        } else if (dirigido.equals("n") && ponderado.equals("n")) {
            grafo = new GrafoNoDirigido();
            grafo.crearGrafo();
        }

        // Menú de Selección de Visualización
        System.out.println("El Grafo ha sido construido. Seleccione que desea visualizar a partir del menú.\n");
        boolean exit = false;
        while (!exit) {

            System.out.println("""
                    |Menu de Selección de Visualización|:
                     1) Matriz de Adyacencia
                     2) Matriz de Incidencia
                     3) Secuencia de Grados
                     4) Lista de Adyacencia
                     5) Recorrido BFS
                     6) Recorrido DFS
                    \s""");

            if (ponderado.equals("s")) {
                System.out.println("""
                        7) Matriz de Costos
                        8) Salir""");
            } else {
                System.out.println("7) Salir");
            }
            int option = scanner.nextInt();
            if (!ponderado.equals("s")) {
                switch (option) {
                    case 1:
                        grafo.mostrarMatriz(grafo.convertMatrizAdyacencia());
                        break;
                    case 2:
                        grafo.mostrarMatriz(grafo.convertirMatrizIncidencia());
                        break;
                    case 3:
                        grafo.mostrarGrados(grafo.listaGrados());
                        break;
                    case 4:
                        grafo.mostrarLista(grafo.listaAdyacencia());
                        break;
                    case 5:
                        System.out.println("Seleccione la raíz de búsqueda (Vértice de partida en la búsqueda)");
                        int fuente = scanner.nextInt() - 1;
                        System.out.println(grafo.BFS(fuente));
                        break;
                    case 6:
                        System.out.println("Seleccione la raíz de búsqueda (Vértice de partida en la búsqueda)");
                        int fuente_dfs = scanner.nextInt() - 1;
                        System.out.println(grafo.DFS(fuente_dfs));
                        break;
                    case 7:
                        exit = true;
                        break;
                }
            } else {
                switch (option) {
                    case 1:
                        grafo_ponderado.mostrarMatriz(grafo_ponderado.convertMatrizAdyacencia());
                        break;
                    case 2:
                        grafo_ponderado.mostrarMatriz(grafo_ponderado.convertirMatrizIncidencia());
                        break;
                    case 3:
                        grafo_ponderado.mostrarGrados(grafo_ponderado.listaGrados());
                        break;
                    case 4:
                        grafo_ponderado.mostrarLista(grafo_ponderado.listaAdyacencia());
                        break;
                    case 5:
                        System.out.println("Seleccione la raíz de búsqueda (Vértice de partida en la búsqueda)");
                        int fuente = scanner.nextInt() - 1;
                        System.out.println(grafo_ponderado.BFS(fuente));
                        break;
                    case 6:
                        System.out.println("Seleccione la raíz de búsqueda (Vértice de partida en la búsqueda)");
                        int fuente_dfs = scanner.nextInt() - 1;
                        System.out.println(grafo_ponderado.DFS(fuente_dfs));
                        break;
                    case 7:
                        grafo_ponderado.MostrarMatrizCostos(grafo_ponderado.convertirMatrizCostos());
                        break;
                    case 8:
                        exit = true;
                        break;
                }
            }
        }
    }
}