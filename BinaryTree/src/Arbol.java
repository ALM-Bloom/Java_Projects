
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @brief: Clase que implementa la estructura árbol y que contiene el nodo (de partida) raíz
 */
public class Arbol {
    private final Nodo nodo_raiz;

    public Arbol(int raiz) {
        nodo_raiz = new Nodo(raiz);
    }

    public boolean Insertar(int dato) {
       return InsertarRec(dato, nodo_raiz);
    }

/**
 * @brief Inserta un dato en el árbol de manera recursiva.
 * 
 * Este método intenta insertar un nuevo dato en el árbol binario
 * siguiendo las reglas de inserción. Si el nodo actual tiene un hijo
 * izquierdo o derecho vacío, el dato se inserta allí. Si ambos hijos
 * están ocupados, se realiza una llamada recursiva para intentar
 * insertar el dato en los subárboles izquierdo o derecho.
 * 
 * @param dato El valor entero que se desea insertar en el árbol.
 * @param pater El nodo actual en el que se está intentando insertar el dato.
 * @return true si el dato fue insertado exitosamente, false en caso contrario.
 */
    private boolean InsertarRec(int dato, Nodo pater) {
        if (Busqueda(dato)) {
            System.out.println("El nodo ya existe en el árbol");
            return false;
        } 
        if (pater.getHijoIzq() == null) {
            pater.setHijoIzq(dato);
            return true;
        }
        else if (pater.getHijoDch() == null) {
            pater.setHijoDch(dato);
            return true;
        }
        if (TamanioArbol(pater.getHijoIzq()) <= TamanioArbol(pater.getHijoDch())) {
            InsertarRec(dato, pater.getHijoIzq());
        } else {
            InsertarRec(dato, pater.getHijoDch());
        }
        return false;
    }

    private int TamanioArbol(Nodo nodo) {
        if (nodo == null) {
            return 0;
        } else {
            return (1 + TamanioArbol(nodo.getHijoIzq()) + TamanioArbol(nodo.getHijoDch()));
        }
    } 

    public boolean Busqueda(int dato) {
        return BusquedaRec(dato, nodo_raiz);
    }

/**
 * @brief Busca un dato en el árbol de manera recursiva.
 * 
 * Este método recorre el árbol binario de forma recursiva para buscar
 * un valor específico. Compara el dato buscado con el valor del nodo actual.
 * Si el dato coincide, retorna true. Si no, continúa la búsqueda en los
 * subárboles izquierdo y derecho. Si no encuentra el dato, retorna false.
 * 
 * @param dato El valor entero que se desea buscar en el árbol.
 * @param nodo_busqueda El nodo actual en el que se está realizando la búsqueda.
 * @return true si el dato fue encontrado, false en caso contrario.
 */
    private boolean BusquedaRec(int dato, Nodo nodo_busqueda) {
        if (nodo_busqueda == null) {
            return false;
        }
        if (nodo_busqueda.getDato() == dato) {
            return true;
        }
        return BusquedaRec(dato, nodo_busqueda.getHijoIzq()) || BusquedaRec(dato, nodo_busqueda.getHijoDch());
    }

    public boolean Eliminar(int dato) {
        return EliminarRec(dato, nodo_raiz, null);
    } 

    /**
     * Método recursivo para eliminar un nodo hoja de un árbol binario.
     *
     * @param dato El valor del nodo que se desea eliminar.
     * @param nodo_deleteado El nodo actual que se está evaluando para eliminación.
     * @param pater El nodo padre del nodo actual.
     * @return true si el nodo hoja fue eliminado exitosamente, false en caso contrario.
     *
     * @note Esta versión del método solo permite la eliminación de nodos que son hojas.
     *       Si el nodo a eliminar tiene hijos, no se realizará la eliminación y se mostrará
     *       un mensaje en la consola.
     */
    private boolean EliminarRec(int dato, Nodo nodo_deleteado, Nodo pater) {
        if (nodo_deleteado == null) {
            return false;
        }
        if (nodo_deleteado.getDato() == dato) {
            if (nodo_deleteado.getHijoIzq() == null && nodo_deleteado.getHijoDch() == null ) {
            if (pater.getHijoIzq() == nodo_deleteado) {
                pater.setHijoIzq(null);
            } 
            else if (pater.getHijoDch() == nodo_deleteado) {
                pater.setHijoDch(null);
            }
            return true;
        } else {
            System.out.println("Esta versión solo permite la eliminación de nodos hojas");
            return false;
            }
        }
        return EliminarRec(dato, nodo_deleteado.getHijoIzq(), nodo_deleteado) ||
               EliminarRec(dato, nodo_deleteado.getHijoDch(), nodo_deleteado);
    }

    /**
     * @brief Realiza un recorrido por niveles (Breadth-First Search) del árbol binario.
     * 
     * Este método utiliza una cola para recorrer el árbol binario nivel por nivel,
     * comenzando desde la raíz. Imprime el nivel actual y los datos de cada nodo
     * en el orden en que se visitan. Si un nodo es nulo, imprime "[.]".
     * 
     * @details 
     * - Se utiliza una cola de pares (nodo, nivel) para realizar el recorrido.
     * - Cuando se cambia de nivel, se imprime un mensaje indicando el nivel actual.
     * - Para cada nodo, se añaden sus hijos izquierdo y derecho a la cola con el nivel incrementado.
     * 
     * @pre El árbol debe estar inicializado y puede contener nodos nulos.
     * @post Se imprimen los datos de los nodos en orden de nivel, junto con los niveles correspondientes.
     */
    public void RecorridoNivel() {
        Queue<SimpleEntry<Nodo, Integer>> cola_nodos = new LinkedList<>();
        
        cola_nodos.add(new SimpleEntry<>(nodo_raiz, 0));

        int nivel, nivel_actual = 0;
        System.out.println("Estamos en el nivel " + nivel_actual);
        while (!cola_nodos.isEmpty()) {
            SimpleEntry<Nodo, Integer> entrada = cola_nodos.poll();
            Nodo auxiliar = entrada.getKey();
            nivel = entrada.getValue();
            if (nivel > nivel_actual) {
                nivel_actual = nivel;
                System.out.println("Estamos en el nivel " + nivel_actual);
            }

            if (auxiliar == null) {
                System.out.println("[.] ");
            } else {
                System.out.println(auxiliar.getDato() + " ");
                cola_nodos.add(new SimpleEntry<>(auxiliar.getHijoIzq(), nivel + 1));
                cola_nodos.add(new SimpleEntry<>(auxiliar.getHijoDch(), nivel + 1));
            }
        }
    }
}
