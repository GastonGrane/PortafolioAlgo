package TDAS;

import java.util.LinkedList;
import java.util.List;


public class TArbolGenealogico<T> extends TArbolBB<T> {
    protected TElementoGen<Personas> nodoRaiz;
    private IArbolBB<T> subArbolIzquierdo;
    private IArbolBB<T> subArbolDerecho;

    public TArbolGenealogico() {
        this.nodoRaiz = null;
    }

    @Override
    public boolean insertar(Comparable etiqueta, T unDato) {
        TElementoGen<T> nuevoElemento = new TElementoGen<>(etiqueta, unDato);

        // Verifico si el árbol está vacío. Si es así, creo un nuevo nodo raíz.
        if (esVacio()){
            nodoRaiz = (TElementoGen<Personas>) nuevoElemento;
            return true;
        } else {
            // Si no está vacío, llamo al método insertar de la clase ElementoAB.
            return nodoRaiz.insertar((IElementoAB<Personas>) nuevoElemento);
        }
    }

    @Override
    public T buscar(Comparable unaEtiqueta) {
        if (esVacio()) {
            return null;
        } else {
            IElementoAB<T> nodo = (IElementoAB<T>) nodoRaiz.buscar(unaEtiqueta);
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
            nodoRaiz = (TElementoGen<Personas>) nodoRaiz.eliminar(unaEtiqueta);
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

    public void mostrarParentesco(Personas persona1, Personas persona2) {
        if (nodoRaiz == null) {
            System.out.println("El árbol está vacío");
            return;
        }

        nodoRaiz.Parientes(nodoRaiz, persona1, persona2);
    }

}
