/*
 * Fecha de Comienzo: 29/04/2025
 * Fecha de Finalización: 02/05/2025
 * Autor: Alejandro M.L, durante tardeos.
 * Este programa tienen como función la didáctica en cuanto a la implementación de una estructura de árbol binario en el lenguaje Java.
 * El tipo de datos de los nodos será unícamente de valores enteros.
 * Se ha establecido una ramificación de 2, esto es por definición, la implementación de un árbol binario.
 * Se pretende implementar por tanto, las clásicas funciones de inserción, búsqueda y eliminación. Todas de manera recursiva.
 * 
 * Dada la naturaleza de este proyecto (asentamiento del lenguaje y entretenimiento), se pretende desarrollar todo el código sin el uso de IA generativa (inclusive Copilot).
 */

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        
        System.out.println("Para empezar, introduce el valor del nodo raíz: ");
        int dato = input.nextInt();
        Arbol vicencio = new Arbol(dato);

        System.out.println("A continuación introducirás los nodos.");
        boolean encendido = true;
        while (encendido) {
            System.out.println("Si deseas dejar de crear nodos introduce: 'f' | Introduce un entero que será el valor del nodo: ");
            String exit = input.next();
            if (exit.equals("f")) {
                break;
            } else {
                dato = Integer.parseInt(exit);
                vicencio.Insertar(dato);
                System.out.println("Resultado del árbol hasta ahora: ");
                vicencio.RecorridoNivel();
            } 
        }

        OUTER:
        while (encendido) {
            System.out.println("Búsqueda de un nodo: 0 | Mostrar el árbol: 1 | Insertar un Nodo: 2 | Eliminar un nodo: 3 | Salir del programa: 4");
            dato = input.nextInt();
            switch (dato) {
                case 0:
                    System.out.println("Introduce el dato del nodo que deseas encontrar: ");
                    dato = input.nextInt();
                    if (vicencio.Busqueda(dato)) {
                        System.out.println("Ese nodo existe en el árbol");
                    } else {
                        System.out.println("Ese nodo no existe en el árbol");
                    }       break;
                case 1:
                    vicencio.RecorridoNivel();
                    break;
                case 2:
                    System.out.println("Introduce el dato del nodo a crear: ");
                    dato = input.nextInt();
                    vicencio.Insertar(dato);
                    break;
                case 3:
                    System.out.println("Introduce el dato del nodo que deseas eliminar: ");
                    dato = input.nextInt();
                    if (vicencio.Eliminar(dato)) {
                        System.out.println("El Nodo ha sido eliminado");
                    } else {
                        System.out.println("Ese nodo no existe en el árbol");
                    }       break;
                case 4:
                    break OUTER;
                default:
                    System.out.println("Opción no implementada, vuelve a intentarlo.");
            }
        }
        input.close();
    }
}
