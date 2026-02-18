package graph;

import graph.directed.DiGrafo;
import graph.directed.weighted.DiGrafoPonderado;
import graph.engine.Arista;
import graph.engine.Grafo;
import graph.engine.GrafoPonderado;
import graph.undirected.GrafoNoDirigido;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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

        Scanner scanner = new Scanner(System.in);
        Grafo grafo = new GrafoNoDirigido();
        GrafoPonderado grafo_ponderado = new DiGrafoPonderado();
        String ponderado = "error";
        String dirigido = "error";
        System.out.println("¿Desea cargar el grafo desde un fichero? (s/n)");
        String opcion_fichero = scanner.nextLine();
        opcion_fichero = opcion_fichero.toLowerCase();

        if (opcion_fichero.equals("n")) {
// ------------ Lectura del Grafo Manual -----------
            System.out.println("¿El grafo es ponderado? (s/n)");
            ponderado = scanner.nextLine();
            ponderado = ponderado.toLowerCase();
            System.out.println("El grafo es dirigido o no dirigido? (d/n)");
            dirigido = scanner.nextLine();
            dirigido = dirigido.toLowerCase();

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
        }
// -----------------------------------------------------
// ------------ Lectura del Grafo Por Fichero -----------
        else {
            try {
                System.out.println("Introduzca el nombre del fichero (Por defecto: grafo1.gr)");
                String nombre_fichero = scanner.nextLine();
                if (nombre_fichero.isEmpty()) {
                    nombre_fichero = "grafo1.gr";
                }
                String ruta = "grafos/" + nombre_fichero;

                InputStream input = Main.class.getClassLoader().getResourceAsStream(ruta);
                if (input == null) {
                    throw new FileNotFoundException("El fichero '" + ruta + "' no se ha encontrado en resources/.");
                }

                Scanner scanner_fichero = new Scanner(input);
                scanner_fichero.useLocale(Locale.US);

                // ---- Tipo de Grafo (Prímera Línea) ----
                ponderado = scanner_fichero.next(); // p o n
                dirigido = scanner_fichero.next(); // d o u

                // ---- Número de vértices (Segunda Línea) ----
                int num_vertices = scanner_fichero.nextInt();
                System.out.println("Número de vértices: " + num_vertices);
                HashSet<Integer> vertices = new HashSet<>();
                for (int i = 0; i < num_vertices; i++) {
                    vertices.add(i);
                }

                HashMap<Integer, HashSet<Arista>> aristas_ponderadas = new HashMap<>();
                HashMap<Integer, HashSet<Integer>> aristas = new HashMap<>();

                // ---- Lectura de aristas (Tercera Línea en Adelante) ----
                while (scanner_fichero.hasNext()) {
                    int origen = scanner_fichero.nextInt() - 1;
                    int destino = scanner_fichero.nextInt() - 1;
                    double peso = 0.0;
                    if (ponderado.equals("p")) {
                        peso = scanner_fichero.nextDouble();
                    }

                    if (ponderado.equals("p")) {
                        aristas_ponderadas.putIfAbsent(origen, new HashSet<>());
                        aristas_ponderadas.get(origen).add(new Arista(destino, peso));
                    } else {
                        aristas.putIfAbsent(origen, new HashSet<>());
                        aristas.get(origen).add(destino);
                    }
                }

                scanner_fichero.close();

                if (ponderado.equals("p") && dirigido.equals("d")) {
                    grafo_ponderado = new DiGrafoPonderado(vertices, aristas_ponderadas);
                } else if (ponderado.equals("p") && dirigido.equals("n")) {
                    // TODO: Implementar grafo no dirigido ponderado
                } else if (ponderado.equals("n") && dirigido.equals("d")) {
                    grafo = new DiGrafo(vertices, aristas);
                } else {
                    grafo = new GrafoNoDirigido(vertices, aristas);
                }

                System.out.println("Grafo cargado correctamente desde fichero.\n");

            } catch (FileNotFoundException e) {
                throw new RuntimeException("Error: El fichero especificado no se ha encontrado." +
                        " Asegúrese de que el nombre del fichero es correcto y que se encuentra en el directorio adecuado.", e);
            }
        }
        // Menú de Selección de Visualización
        System.out.println("El Grafo ha sido construido. Seleccione que desea visualizar a partir del menú.\n");
        boolean exit = false;
        while (!exit) {

            System.out.print("""
                    |Menu de Selección de Visualización|:
                     1) Matriz de Adyacencia
                     2) Matriz de Incidencia
                     3) Secuencia de Grados
                     4) Lista de Adyacencia
                     5) Recorrido BFS
                     6) Recorrido DFS
                    """);

            if (ponderado.equals("p")) {
                System.out.println(" 7) Matriz de Costos\n" +  " 8) Salir");
            } else {
                System.out.println(" 7) Salir");
            }
            int option = scanner.nextInt();
            if (!ponderado.equals("p")) {
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