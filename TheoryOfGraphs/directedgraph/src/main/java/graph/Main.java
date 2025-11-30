package graph;

import java.util.ArrayList;
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
            System.out.println("Introduzca el vértice adyacente a " + cont + " o pase al siguiente introduciendo 's'.");

            String option = scanner.next();
            option = option.toLowerCase();

            if (option.equals("s")) {
                cont++;
            } else {
                int vert_ady = Integer.parseInt(option);
                if (vert_ady < 0 || vert_ady >= num_vertices) {
                    throw new IllegalArgumentException("Vértice introducido no incluido en el grafo");
                }
                aristas.putIfAbsent(cont, new HashSet<>());
                if (aristas.get(cont).contains(vert_ady)) {
                    System.out.println("El vértice " + cont + " ya se encuentra adyacente a " + vert_ady +
                            ". Introduzca otro, por favor.");
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
        System.out.println("|Menu de Selección de Visualización|:\n 1) Matriz de Adyacencia\n 2) Matriz de Incidencia\n" +
                " 3) Secuencia de Grados\n 4) Lista de Adyacencia\n 5) Salir");
        boolean exit = false;
        while (!exit) {
            int option = scanner.nextInt();
            if (option == 1) {
                grafo.mostrarMatriz(grafo.convertMatrizAdyacencia());
            }
            else if (option == 2) {
                grafo.mostrarMatriz(grafo.convertirMatrizIncidencia());
            }
            else if (option == 3) {
                grafo.mostrarGrados(grafo.listaGrados());
            }
            else if (option == 4) {
                grafo.mostrarLista(grafo.listaAdyacencia());
            }
            else if (option == 5) {
                exit = true;
            }
        }

        }

    }