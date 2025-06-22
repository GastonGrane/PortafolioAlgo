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
        if (vaciar()){
            nodoRaiz = nuevoElemento;
            return true;
        } else {
            // Si no está vacío, llamo al método insertar de la clase ElementoAB.
            return nodoRaiz.insertar(nuevoElemento);
        }
    }

    @Override
    public T buscar(Comparable unaEtiqueta) {
        if (vaciar()) {
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
    public boolean vaciar() {
        return nodoRaiz == null;
    }

    @Override
    public boolean esVacio() {
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

    public int alturaArbol() {
        if (nodoRaiz != null) {
            return nodoRaiz.altura();
        }
        return -1; // si el árbol está vacío
    }

    public int hojasArbol() {
        return (nodoRaiz != null) ? nodoRaiz.contarHojas() : 0;
    }

    public int internosArbol() {
        return (nodoRaiz != null) ? nodoRaiz.contarInternos() : 0;
    }

    public int completosArbol() {
        return (nodoRaiz != null) ? nodoRaiz.completos() : 0;
    }

    public int enNiveldelArbol(int nivel) {
        return (nodoRaiz != null) ? nodoRaiz.enNivel(nivel) : 0;
    }

    public Comparable obtenerMenorClave() {
        return (nodoRaiz != null) ? nodoRaiz.obtenerMenorClave() : null;
    }

    public Comparable obtenerMayorClave() {
        return (nodoRaiz != null) ? nodoRaiz.obtenerMayorClave() : null;
    }

    public Comparable obtenerInmediatoAnterior(Comparable clave) {
        return (nodoRaiz != null) ? nodoRaiz.obtenerInmediatoAnterior(clave) : null;
    }

    public String listarHojasConNiveles() {
        return (nodoRaiz != null) ? nodoRaiz.listarHojasConNiveles(0) : "";
    }

    public boolean esABB() {
        return (nodoRaiz != null) ? nodoRaiz.esDeBusqueda() : true;
    }

}
