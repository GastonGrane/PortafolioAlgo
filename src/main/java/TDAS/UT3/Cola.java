package TDAS.UT3;

public class Cola<T> implements ICola<T> {
    private static class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente = null;

        public Nodo(T dato) {
            this.dato = dato;
        }
    }

    private Nodo<T> primero; // Principio de la cola

    public Cola() {
        primero = null;
    }

    @Override
    public void anular() {
        primero = null; // Vacía la cola
    }

    @Override
    public T frente() {
        if (vacia()) {
            return null;
        }
        return primero.dato; // Devuelve el primer elemento de la cola
    }

    @Override
    public void poneEnCola(T unElemento) {
        Nodo<T> nuevoNodo = new Nodo<>(unElemento);
        if (vacia()) {
            primero = nuevoNodo; // En el caso de que la cola sea vacía el nodo creado pasa a ser el primero de la cola
        } else {
            Nodo<T> actual = primero;
            while (actual.siguiente != null) { // Se recorre la cola hasta llegar al final
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo; // Se agrega el nuevo nodo al final
        }
    }

    @Override
    public T quitaDeCola() {
        if (vacia()) {
            return null;
        }
        T dato = primero.dato;
        primero = primero.siguiente;
        return dato;
    }

    @Override
    public boolean vacia() {
        return primero == null;
    }

    public void imprimirCola() {
        Nodo<T> actual = primero;
        while (actual != null) {
            System.out.print(actual.dato);
            if (actual.siguiente != null) {
                System.out.print(", ");
            }
            actual = actual.siguiente;
        }
        System.out.println(); // Salto de línea final
    }

}
