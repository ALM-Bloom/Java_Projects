/*
 * Fecha de Comienzo: 29/04/2025
 * Fecha de Finalización: 
 * Autor: Alejandro M.L, durante tardeos.
 * Este programa tienen como función la didáctica en cuanto a la implementación de una estructura de árbol binario en el lenguaje Java.
 * El tipo de datos de los nodos será unícamente de valores enteros.
 * Se ha establecido una ramificación de 2, esto es por definición, la implementación de un árbol binario.
 * Se pretende implementar por tanto, las clásicas funciones de inserción, búsqueda y eliminación. Todas de manera recursiva.
 * 
 * Dada la naturaleza de este proyecto (asentamiento del lenguaje y entretenimiento), se pretende desarrollar todo el código sin el uso de IA generativa (inclusive Copilot).
 */
public class App {
    public static void main(String[] args) throws Exception {
        Arbol vicencio = new Arbol(2);

        vicencio.Insertar(7);
        vicencio.Insertar(5);
        vicencio.Insertar(2);
        vicencio.Insertar(6);
        vicencio.Insertar(9);
        vicencio.Insertar(5);
        vicencio.Insertar(11);
        vicencio.Insertar(4);
    }
}
