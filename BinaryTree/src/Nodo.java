/*
 * @brief: Clase que implenta el tipo de dato "Nodo", que contendrá el dato como un entero 
 */
public class Nodo {
    private int dato;
    private Nodo hijo_izq = null;
    private Nodo hijo_dch = null;

    public Nodo (int dato) { this.dato = dato; }
    // Getters
    public int getDato() { return dato; }
    public Nodo getHijoIzq() { return hijo_izq; }
    public Nodo getHijoDch() { return hijo_dch; }
    // Setters
    public void setDato(int new_dato) { dato = new_dato; } 
    public void setHijoIzq(int dato) { hijo_izq = new Nodo(dato); }
    public void setHijoDch(int dato) { hijo_dch = new Nodo(dato); }
}
