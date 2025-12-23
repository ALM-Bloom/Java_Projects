package graph;

import graph.engine.Grafo;
import graph.undirected.GrafoNoDirigido;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Código correspondiente a la parte clientelar del proyecto.
 * Define la lectura de vértices y sus adyacencias para la creación del grafo y un menú correspondiente a las
 * distintas operaciones de manipulación de éstos.
 * Se valora añadir otro metodo de lectura del grafo, así como abstraer el comportamiento de lectura dentro de la clase
 * Grafo.
 * @author: Alejandro M.L
 * @version: 23/12/2025/A
 * @see: {@link graph.engine.Grafo}
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca número de vértices");
        int num_vertices = scanner.nextInt();

        HashSet<Integer> vertices = new HashSet<>();
        for (int i = 0; i < num_vertices; i++) {
            vertices.add(i);
        }

        System.out.println("¡Vértices Introducidos!\n");
        System.out.println("Para introducir las aristas y conectarlas seguirá el siguiente procedimiento:\n" +
                "Iterará sobre los vértices comenzando desde el primero ('1'), e introducirá el vértice que" +
                " desea que sea adyacente. Para continuar al siguiente vértice podrá introducir 's'.");

        HashMap<Integer, HashSet<Integer>> aristas = new HashMap<>();
        HashMap<Integer, Integer> vert_introducidos;
        int cont = 0;
        while (cont < num_vertices) {
            System.out.println("Se encuentra en el vértice " + (cont + 1));
            System.out.println("Introduzca el vértice adyacente a " + (cont + 1) + " pase al siguiente introduciendo 's'." +
                    " O finalice la construcción con 'h'");

            String option = scanner.next();
            option = option.toLowerCase();

            if (option.equals("s")) {
                cont++;
            }
            else if (option.equals("h")) {
                break;
            } else {
                int vert_ady = Integer.parseInt(option) - 1;
                if (vert_ady < 0 || vert_ady >= num_vertices) {
                    throw new IllegalArgumentException("Vértice introducido no incluido en el grafo");
                }
                aristas.putIfAbsent(cont, new HashSet<>());
                if (aristas.get(cont).contains(vert_ady)) {
                    System.out.println("El vértice " + (cont + 1) + " ya se encuentra adyacente a " + (vert_ady + 1) +
                            ". Introduzca otro, por favor.\n");
                    continue;
                }
                aristas.get(cont).add(vert_ady);
                System.out.println("El vértice " + (cont + 1) + " ahora es adyacente a " + (vert_ady + 1) + "\n");
            }
        }

        // Construcción del Grafo
        Grafo grafoNoDirigido = new GrafoNoDirigido(vertices, aristas);

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
                 7) Salir""");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    grafoNoDirigido.mostrarMatriz(grafoNoDirigido.convertMatrizAdyacencia());
                    break;
                case 2:
                    grafoNoDirigido.mostrarMatriz(grafoNoDirigido.convertirMatrizIncidencia());
                    break;
                case 3:
                    grafoNoDirigido.mostrarGrados(grafoNoDirigido.listaGrados());
                    break;
                case 4:
                    grafoNoDirigido.mostrarLista(grafoNoDirigido.listaAdyacencia());
                    break;
                case 5:
                    System.out.println("Seleccione la raíz de búsqueda (Vértice de partida en la búsqueda)");
                    int fuente = scanner.nextInt() - 1;
                    System.out.println(grafoNoDirigido.BFS(fuente));
                    break;
                case 6:
                    System.out.println("Seleccione la raíz de búsqueda (Vértice de partida en la búsqueda)");
                    int fuente_dfs = scanner.nextInt() - 1;
                    System.out.println(grafoNoDirigido.DFS(fuente_dfs));
                    break;
                case 7:
                    exit = true;
                    break;
                }
            }
        }

    }