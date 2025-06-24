package TDAS.UT3;

public interface ICola<T> {

    /**
     * Deja la cola vacía.
     */
    public void anular();

    /**
     * Devuelve el elemento al frente de la cola sin eliminarlo.
     * @return Elemento al frente, o null si la cola está vacía.
     */
   public T frente();

    /**
     * Inserta un nuevo elemento al final de la cola.
     * @param unElemento Elemento a encolar.
     */
    public void poneEnCola(T unElemento);

    /**
     * Elimina el primer elemento de la cola y lo devuelve.
     * @return El elemento eliminado, o null si la cola está vacía.
     */
   public T quitaDeCola();

    /**
     * Indica si la cola está vacía.
     * @return true si está vacía, false en caso contrario.
     */
    public boolean vacia();
}
