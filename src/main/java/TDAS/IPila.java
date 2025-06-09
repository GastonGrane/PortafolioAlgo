package TDAS;

public interface IPila<T> {

    /**
     * Elimina todos los elementos de la pila.
     */
    void anular();

    /**
     * Devuelve el elemento que está en el tope de la pila,
     * sin eliminarlo.
     *
     * @return el elemento en el tope de la pila
     * @throws IllegalStateException si la pila está vacía
     */
    T tope();

    /**
     * Elimina el elemento que está en el tope de la pila.
     *
     * @throws IllegalStateException si la pila está vacía
     */
    void desapilar();

    /**
     * Agrega un elemento a la pila.
     *
     * @param dato el elemento a agregar
     */
    void apilar(T dato);

    /**
     * Verifica si la pila está vacía.
     *
     * @return true si la pila no contiene elementos
     */
    boolean esVacia();
}

