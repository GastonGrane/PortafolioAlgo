package TDAS;

import java.util.LinkedList;
import java.util.List;

public class TArbolBB<T> implements IArbolBB<T> {
    protected IElementoAB<T> nodoRaiz;
    private IArbolBB<T> subArbolIzquierdo;
    private IArbolBB<T> subArbolDerecho;

    public TArbolBB() {
        this.nodoRaiz = null;
    }

    @Override
    public boolean insertar(Comparable etiqueta, T unDato) {
        IElementoAB<T> nuevoElemento = new TElementoAB<>(etiqueta, unDato);
        // Verifico si el árbol está vacío. Si es así, creo un nuevo nodo raíz.
        if (esVacio()){
            nodoRaiz = nuevoElemento;
            return true;
        } else {
            // Si no está vacío, llamo al método insertar de la clase ElementoAB.
            return nodoRaiz.insertar(nuevoElemento);
        }
    }

    @Override
    public T buscar(Comparable unaEtiqueta) {
        if (esVacio()) {
            return null;
        } else {
            IElementoAB<T> nodo = nodoRaiz.buscar(unaEtiqueta);
            if (nodo != null) {
                return nodo.getDatos(); // Obtén el dato almacenado en el nodo
            } else {
                System.out.println("No se encontró el elemento con etiqueta: " + unaEtiqueta);
                return null;
            }
        }
    }


    @Override
    public void eliminar(Comparable unaEtiqueta) {
        if (nodoRaiz != null){
            nodoRaiz = nodoRaiz.eliminar(unaEtiqueta);
        } else {
            System.out.println("El árbol está vacío. No se puede eliminar el elemento.");
        }
    }

    @Override
    public List<T> preOrden() {
        LinkedList listapreOrden = new LinkedList<>();
        if (nodoRaiz != null){
            nodoRaiz.preOrden(listapreOrden);
        }else{
            listapreOrden = null;
        }
        return listapreOrden;
    }

    @Override
    public List<T> inOrden() {
        LinkedList listainOrden = new LinkedList<>();
        if (nodoRaiz != null){
            nodoRaiz.inOrden(listainOrden);
        }else{
            listainOrden = null;
        }
        return listainOrden;
    }

    @Override
    public List<T> postOrden() {
        LinkedList listapostOrden = new LinkedList<>();
        if (nodoRaiz != null){
            nodoRaiz.postOrden(listapostOrden);
        }else{
            listapostOrden = null;
        }
        return listapostOrden;
    }

    @Override
    public boolean esVacio() {
        return nodoRaiz == null;
    }

    @Override
    public boolean vaciar() {
        if (nodoRaiz == null){
            return false;
        }else {
            nodoRaiz = null;
            return true;
        }
    }

    public int obtenerTamañoEnelArbol() {
        if (nodoRaiz == null) {
            return 0; // Si el árbol está vacío, el tamaño es 0
        }
        return nodoRaiz.obtenerTamaño(); // Llama al método en la raíz
    }
   
}
