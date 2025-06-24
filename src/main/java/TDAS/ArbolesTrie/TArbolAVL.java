package TDAS.ArbolesTrie;

public class TArbolAVL<T> extends TArbolBB<T> {
    public TArbolAVL() {
        super();
    }

    @Override
    public boolean insertar(Comparable etiqueta, T unDato) {
        TElementoAVL<T> nuevoElemento = new TElementoAVL<>(etiqueta, unDato);
        if (esVacio()) {
            nodoRaiz = nuevoElemento;
            return true;
        } else {
            boolean ok = nodoRaiz.insertar(nuevoElemento);
            nodoRaiz = ((TElementoAVL<T>) nodoRaiz).balancear(); // ← clave
            return ok;
        }
    }

    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (nodoRaiz != null) {
            nodoRaiz = nodoRaiz.eliminar(unaEtiqueta);
            if (nodoRaiz != null) {
                nodoRaiz = ((TElementoAVL<T>) nodoRaiz).balancear();
            }
        } else {
            System.out.println("El árbol está vacío. No se puede eliminar el elemento.");
        }
    }

    public int getAltura() { // casteo a nodoRaiz a TElementoAVL para correr test de balanceo
        if (nodoRaiz == null) {
            return -1; // árbol vacío
        } else {
            return ((TElementoAVL<T>) nodoRaiz).getAltura();
        }
    }
}
