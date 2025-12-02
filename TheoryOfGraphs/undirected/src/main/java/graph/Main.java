package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

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
                "Iterará sobre los vértices comenzando desde el primero ('0'), e introducirá el vértice que" +
                " desea que sea adyacente. Para continuar al siguiente vértice podrá introducir 's'.");

        HashMap<Integer, HashSet<Integer>> aristas = new HashMap<>();
        HashMap<Integer, Integer> vert_introducidos;
        int cont = 0;
        while (cont < num_vertices) {
            System.out.println("Se encuentra en el vértice " + cont);
            System.out.println("Introduzca el vértice adyacente a " + cont + " pase al siguiente introduciendo 's'." +
                    " O finalice la construcción con 'h'");

            String option = scanner.next();
            option = option.toLowerCase();

            if (option.equals("s")) {
                cont++;
            }
            else if (option.equals("h")) {
                break;
            } else {
                int vert_ady = Integer.parseInt(option);
                if (vert_ady < 0 || vert_ady >= num_vertices) {
                    throw new IllegalArgumentException("Vértice introducido no incluido en el grafo");
                }
                aristas.putIfAbsent(cont, new HashSet<>());
                if (aristas.get(cont).contains(vert_ady)) {
                    System.out.println("El vértice " + cont + " ya se encuentra adyacente a " + vert_ady +
                            ". Introduzca otro, por favor.\n");
                    continue;
                }
                aristas.get(cont).add(vert_ady);
                System.out.println("El vértice " + cont + " ahora es adyacente a " + vert_ady + "\n");
            }
        }

        // Construcción del Grafo
        Grafo grafo = new Grafo(vertices, aristas);

        // Menú de Selección de Visualización
        System.out.println("El grafo ha sido construido. Seleccione que desea visualizar a partir del menú.\n");
        boolean exit = false;
        while (!exit) {

            System.out.println("""
                |Menu de Selección de Visualización|:
                 1) Matriz de Adyacencia
                 2) Matriz de Incidencia
                 3) Secuencia de Grados
                 4) Lista de Adyacencia
                 5) Recorrido BFS
                 6) Salir""");

            int option = scanner.nextInt();
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
                    int fuente = scanner.nextInt();
                    System.out.println(grafo.BFS(fuente));
                    break;
                case 6:
                    exit = true;
                    break;
                }
            }
        }

    }