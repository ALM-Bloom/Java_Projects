/*
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

    public boolean InsertarRec(int dato, Nodo pater) {
        if (pater.getHijoIzq() == null) {
            pater.setHijoIzq(dato);
            return true;
        }
        else if (pater.getHijoDch() == null) {
            pater.setHijoDch(dato);
            return true;
        }
        if (InsertarRec(dato, pater.getHijoIzq()) == true) {
            return true;
        }
        if (InsertarRec(dato, pater.getHijoDch()) == true) {
            return true;
        }
        return false;
    }

    public boolean Busqueda(int dato) {
        return BusquedaRec(dato, nodo_raiz);
    }

    public boolean BusquedaRec(int dato, Nodo nodo_busqueda) {
        if (nodo_busqueda.getDato() == dato) {
            return true;
        }
        if (nodo_busqueda.getHijoIzq() == null && nodo_busqueda.getHijoDch() == null) {
            return false;
        }
        if (BusquedaRec(dato, nodo_busqueda.getHijoIzq()) == true) {
            return true;
        }
        if (BusquedaRec(dato, nodo_busqueda.getHijoDch()) == true) {
            return true;
        }
        return false;
    }
}
