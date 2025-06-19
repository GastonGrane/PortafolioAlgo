package TDAS;

public class Lista<T> implements ILista<T> {

    private class Nodo<T> {

        private final Comparable etiqueta;
        private T dato;
        private Nodo<T> siguiente = null;

        public Nodo(Comparable etiqueta, T dato ) {
            this.etiqueta = etiqueta;
            this.dato = dato;
        }
    }

    private Nodo<T> primero;

    public Lista() {
        primero = null;
    }

    @Override
    public void insertar(T dato, Comparable clave) {
        Nodo<T> unElemento = new Nodo<T>(clave, dato);
        if (esVacia()) {
            primero = unElemento;
        } else {
            Nodo<T> actual = primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = unElemento;
        }
    }

    //O(n)
    public void insertarOrdenado(T dato, Comparable clave) {
        Nodo<T> unElemento = new Nodo<T>(clave, dato);
        //Si clave < etiqueta = -1
        if (esVacia() || clave.compareTo(primero.etiqueta) < 0) {
            unElemento.siguiente = primero;
            primero = unElemento;
            return;
        }

        Nodo<T> actual = primero;
        //Mientras clave > etiqeuta = 1
        while (actual.siguiente != null && clave.compareTo(actual.siguiente.etiqueta) > 0) {
            actual = actual.siguiente;
        }

        //Inseretamos el nodo donde corresponde
        unElemento.siguiente = actual.siguiente;
        actual.siguiente = unElemento;
    }

    @Override
    public T buscar(Comparable clave) {
        if (esVacia()) {
            return null;
        } else {
            Nodo<T> actual = primero;
            while (actual != null) {
                if (actual.etiqueta.equals(clave)) {
                    return actual.dato;
                } else {
                    actual = actual.siguiente;
                }
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        if (primero == null) {
            return false;
        }
        if (primero.etiqueta.equals(clave)) {
            primero = primero.siguiente;
            return true;
        }
        Nodo<T> actual = primero;
        while (actual.siguiente != null && !actual.siguiente.etiqueta.equals(clave)) {
            actual = actual.siguiente;
        }
        if (actual.siguiente == null) {
            return false;
        }
        actual.siguiente = actual.siguiente.siguiente;
        return true;
    }

    //O(n2)
    public void eliminarDuplicados() {
        Nodo<T> actual = primero;
        while (actual != null) {
            Nodo<T> buscarDuplicados = actual;
            while (buscarDuplicados.siguiente != null) {
               if (buscarDuplicados.siguiente.etiqueta.equals(actual.etiqueta)) {
                   buscarDuplicados.siguiente = buscarDuplicados.siguiente.siguiente;
               } else {
                   buscarDuplicados = buscarDuplicados.siguiente;
               }
            }
            actual = actual.siguiente;
        }
    }

    @Override
    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = primero;
        while (actual != null) {
            sb.append(actual.dato.toString());
            actual = actual.siguiente;
            if (actual != null) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    @Override
    public String imprimir(String separador) {
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = primero;
        while (actual != null) {
            sb.append(actual.dato.toString());
            actual = actual.siguiente;
            if (actual != null) {
                sb.append(separador);
            }
        }
        return sb.toString();
    }

    @Override
    public int cantElementos() {
        int contador = 0;
        Nodo<T> actual = primero;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    @Override
    public boolean esVacia() {
        return (primero == null);
    }
}
