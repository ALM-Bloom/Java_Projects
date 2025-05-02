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
        if (pater.getHijoIzq() == null) {
            pater.setHijoIzq(dato);
            return true;
        }
        else if (pater.getHijoDch() == null) {
            pater.setHijoDch(dato);
            return true;
        }

        if (!NodoFull(pater.getHijoIzq())) {
            return InsertarRec(dato, pater.getHijoIzq());
        }
        if (!NodoFull(pater.getHijoDch())) {
            return InsertarRec(dato, pater.getHijoDch());
        }

        return InsertarRec(dato, pater.getHijoIzq());
    }

    private boolean NodoFull(Nodo nodo) {
        return nodo.getHijoIzq() != null && nodo.getHijoDch() != null;
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

    public void RecorridoNivel() {
        RecorridoRecursivo(nodo_raiz);
    }

    private void RecorridoRecursivo(Nodo nodo_analisis) {
        if (nodo_analisis == nodo_raiz) {
            System.out.println("Nodo raíz: " + nodo_raiz.getDato() + " !");
        }
        if (nodo_analisis.getHijoIzq() != null && nodo_analisis.getHijoDch() != null) {
            System.out.println("El nodo " + nodo_analisis.getDato() + " tiene por la izquierda a " + nodo_analisis.getHijoIzq().getDato() + " y por la derecha " + 
            nodo_analisis.getHijoDch().getDato());
            RecorridoRecursivo(nodo_analisis.getHijoIzq());
            RecorridoRecursivo(nodo_analisis.getHijoDch());
        }
        else if (nodo_analisis.getHijoIzq() != null && nodo_analisis.getHijoDch() == null) {
            System.out.println("El nodo " + nodo_analisis.getDato() + " solo tiene por la izquierda a " + nodo_analisis.getHijoIzq().getDato());
            RecorridoRecursivo(nodo_analisis.getHijoIzq());
        }
        else if (nodo_analisis.getHijoIzq() == null && nodo_analisis.getHijoDch() != null) {
            System.out.println("El nodo " + nodo_analisis.getDato() + " solo tiene por la derecha a " + nodo_analisis.getHijoDch().getDato());
            RecorridoRecursivo(nodo_analisis.getHijoDch());
        }
        else {
            System.out.println("El nodo " + nodo_analisis.getDato() + " es un nodo hoja");
        }
    }
}
