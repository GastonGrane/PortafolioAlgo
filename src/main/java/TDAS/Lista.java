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

    public Lista<Integer> ordenarParImpar(){
        Lista<Integer> listaPares = new Lista<Integer>();
        Lista<Integer> listaImpares = new Lista<Integer>();

        Nodo<Integer> actual = (Nodo<Integer>) this.primero;

        while (actual != null) {
            Integer valor = actual.dato;

            if (valor % 2 == 0) {
                listaPares.insertarOrdenado(actual.dato, actual.etiqueta);
            } else {
                listaImpares.insertarOrdenado(actual.dato, actual.etiqueta);
            }
            actual = actual.siguiente;
        }
        Lista<T> resultado = new Lista<>();

        Nodo<T> impar = (Nodo<T>) listaImpares.primero;
        while (impar != null){
            resultado.insertar(impar.dato,  impar.etiqueta);
            impar = impar.siguiente;
        }

        Nodo<T> par = (Nodo<T>) listaPares.primero;
        while (par != null){
            resultado.insertar(par.dato,  par.etiqueta);
            par = par.siguiente;
        }

        return (Lista<Integer>) resultado;
    }

    public Lista<T> mezclarCon(Lista<T> lista){
        Lista<T> resultado = new Lista<T>();

        Nodo<T> actual = (Nodo<T>) this.primero;
        while (actual != null){
            resultado.insertarOrdenado(actual.dato, actual.etiqueta);
            actual = actual.siguiente;
        }

        actual = (Nodo<T>) lista.primero;
        while (actual != null){
            resultado.insertarOrdenado(actual.dato, actual.etiqueta);
            actual = actual.siguiente;
        }

        //Dejamos vacias la listas que se nos proporcioan
        this.vaciar();
        lista.vaciar();

        return (Lista<T>) resultado;
    }

    //Metodo para vaciar las listas utilziadas
    public void vaciar() {
        primero = null;
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
