package TDAS.UT3;

public interface IConjunto<T, K extends Comparable<K>> {
    void agregar(T elemento, K clave);
    boolean eliminar(K clave);
    boolean pertenece(K clave);
    int tamanio();
    void mostrar();

    IConjunto<T, K> union(IConjunto<T, K> otro);
    IConjunto<T, K> interseccion(IConjunto<T, K> otro);
}

