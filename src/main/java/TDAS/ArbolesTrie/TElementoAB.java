package TDAS.ArbolesTrie;

import java.util.LinkedList;

public class TElementoAB<T> implements IElementoAB<T> {
    protected Comparable etiqueta;
    protected T dato;
    protected IElementoAB<T> hijoIzq;
    protected IElementoAB<T> hijoDer;
    private IElementoAB<T> padre;

    public TElementoAB(Comparable unaEtiqueta, T unDato) {
        this.etiqueta = unaEtiqueta;
        this.dato = unDato;
        this.hijoIzq = null;
        this.hijoDer = null;
        this.padre = null;
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public IElementoAB getHijoIzq() {
        return hijoIzq;
    }

    @Override
    public IElementoAB getHijoDer() {
        return hijoDer;
    }

    @Override
    public void setHijoIzq(IElementoAB elemento) {
        this.hijoIzq = elemento;
    }

    @Override
    public void setHijoDer(IElementoAB elemento) {
        this.hijoDer = elemento;
    }

    @Override
    public IElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (this.etiqueta.equals(unaEtiqueta)) {
            return this;
        } else if (unaEtiqueta.compareTo(this.etiqueta) < 0 && hijoIzq != null) {
            return hijoIzq.buscar(unaEtiqueta);
        } else if (hijoDer != null) {
            return hijoDer.buscar(unaEtiqueta);
        }
        return null;
    }
    
    @Override
    public boolean insertar(IElementoAB<T> elemento) {
        if (elemento.getEtiqueta().equals(this.etiqueta)) {
            return false; // Ya existe un elemento con la misma etiqueta
        } else if (elemento.getEtiqueta().compareTo(this.etiqueta) < 0) {
            if (hijoIzq == null) {
                hijoIzq = elemento;
                return true;
            } else {
                return hijoIzq.insertar(elemento);
            }
        } else {
            if (hijoDer == null) {
                hijoDer = elemento;
                return true;
            } else {
                return hijoDer.insertar(elemento);
            }
        }
    }

    @Override
    public void preOrden(LinkedList<T> unaLista){
        unaLista.add(this.dato);
        if (hijoIzq != null) {
            hijoIzq.preOrden(unaLista);
        }
        if (hijoDer != null) {
            hijoDer.preOrden(unaLista);
        }
    }

    @Override
    public void inOrden(LinkedList<T> unaLista) {
        if (hijoIzq != null) {
            hijoIzq.inOrden(unaLista);
        }
        unaLista.add(this.dato);
        if (hijoDer != null) {
            hijoDer.inOrden(unaLista);
        }
    }

    @Override
    public void postOrden(LinkedList<T> unaLista) {
        if (hijoIzq != null) {
            hijoIzq.postOrden(unaLista);
        }
        if (hijoDer != null) {
            hijoDer.postOrden(unaLista);
        }
        unaLista.add(this.dato);
    }

    @Override
    public T getDatos() {
        return dato;
    }

    @Override
    public IElementoAB<T> eliminar(Comparable unaEtiqueta) {
        if (this.etiqueta.equals(unaEtiqueta)) {

            // Caso 1: Nodo sin hijos
            if (hijoIzq == null && hijoDer == null) {
                return null; // El nodo se elimina devolviendo null
            }

            // Caso 2: Nodo con un solo hijo
            if (hijoIzq == null) {
                return hijoDer; // Se reemplaza por el hijo derecho
            }
            if (hijoDer == null) {
                return hijoIzq; // Se reemplaza por el hijo izquierdo
            }

            // Caso 3: Nodo con dos hijos
            // Reemplazar el nodo actual con el sucesor (mayor del subárbol izquierdo)
            IElementoAB<T> sucesor = obtenerMayor(hijoIzq);
            this.etiqueta = sucesor.getEtiqueta();
            this.dato = sucesor.getDatos();
            // Eliminar el sucesor del subárbol izquierdo
            hijoIzq = hijoIzq.eliminar(sucesor.getEtiqueta());
            return this; //Devolvemos el nodo actual modificado. NO el eliminado
        }
    
        // Si la etiqueta es menor, buscar en el subárbol izquierdo
        if (unaEtiqueta.compareTo(this.etiqueta) < 0 && hijoIzq != null) {
            //Actualizamos la referencia del nodo padre al nodo eliminado, lo hacemos de manera recursiva
            hijoIzq = hijoIzq.eliminar(unaEtiqueta);
        }

        // Si la etiqueta es mayor, buscar en el subárbol derecho
        else if (hijoDer != null) {
            hijoDer = hijoDer.eliminar(unaEtiqueta);
        }
        return this; // Retornar el nodo actual
    }
    
    private IElementoAB<T> obtenerMayor(IElementoAB<T> nodo) {
        while (nodo.getHijoDer() != null) {
            nodo = nodo.getHijoDer();
        }
        return nodo;
    }

    //TD1
    @Override
    public int obtenerTamaño() {
        int size = 1; // Contamos el nodo actual
        if (hijoIzq != null) {
            size += hijoIzq.obtenerTamaño();
        }
        if (hijoDer != null) {
            size += hijoDer.obtenerTamaño();
        }
        return size;
    }


    // Cast necesario porque la interfaz IElementoAB<T> no define este método.
    // Sabemos que en nuestra implementación todos los hijos son realmente TElementoAB<T>,
    // por lo que este cast es seguro en este contexto. Lo mismo con todos los metodos.

    int distanciaEntreNodos(Comparable clave1, Comparable clave2) {
        TElementoAB<T> ancestro = encontrarAncestroComun(clave1, clave2);
        if (ancestro == null) return -1;
        int dist1 = ancestro.distanciaDesde(clave1);
        int dist2 = ancestro.distanciaDesde(clave2);
        if (dist1 == -1 || dist2 == -1) return -1;
        return dist1 + dist2;
    }

    private TElementoAB<T> encontrarAncestroComun(Comparable clave1, Comparable clave2) {
        if (etiqueta.compareTo(clave1) > 0 && etiqueta.compareTo(clave2) > 0 && hijoIzq != null) {

            // Cast justificado: los hijos siempre son instancias de TElementoAB<T> en este árbol.
            // No podemos agregar el método a la interfaz porque no está permitido modificarla. Asi en todos los casteos
            if (hijoIzq != null) {
                return ((TElementoAB<T>) hijoIzq).encontrarAncestroComun(clave1, clave2);
            }
        } else if (etiqueta.compareTo(clave1) < 0 && etiqueta.compareTo(clave2) < 0 && hijoDer != null) {
            if (hijoIzq != null) {
                return ((TElementoAB<T>) hijoIzq).encontrarAncestroComun(clave1, clave2);
            }
        }
        return this;

    }

    private int distanciaDesde(Comparable clave) {
        if (etiqueta.equals(clave)) {
            return 0;
        } else if (clave.compareTo(etiqueta) < 0 && hijoIzq != null) {
            int dist = ((TElementoAB<T>)hijoIzq).distanciaDesde(clave);
            return (dist == -1) ? -1 : 1 + dist;
        } else if (clave.compareTo(etiqueta) > 0 && hijoDer != null) {
            int dist = ((TElementoAB<T>)hijoDer).distanciaDesde(clave);
            return (dist == -1) ? -1 : 1 + dist;
        }
        return -1; // clave no encontrada
    }

    //TD1
    public int contarHojas(){
        if (hijoIzq == null && hijoDer == null) return 1;
        int izq = (hijoIzq != null) ? ((TElementoAB<T>) hijoIzq).contarHojas() : 0;
        int der = (hijoDer != null) ? ((TElementoAB<T>) hijoDer).contarHojas() : 0;
        return izq + der;
    }

    public int sumarMayoresQue(Comparable clave) {
        int suma = 0;
        if (etiqueta.compareTo(clave) > 0) {
            suma += (Integer) etiqueta;
        }
        if (hijoIzq != null) suma += ((TElementoAB<T>) hijoIzq).sumarMayoresQue(clave);
        if (hijoDer != null) suma += ((TElementoAB<T>) hijoDer).sumarMayoresQue(clave);
        return suma;
    }

    //TD1
    public void imprimirNivel(int nivel) {
        if (nivel == 0) {
            System.out.println(etiqueta);
        } else {
            if (hijoIzq != null) ((TElementoAB<T>) hijoIzq).imprimirNivel(nivel - 1);
            if (hijoDer != null) ((TElementoAB<T>) hijoDer).imprimirNivel(nivel - 1);
        }
    }

    //TD1
    public int enNivel(int nivel) {
        if (nivel == 0) {
            return 1;
        } else {
            int cantidad = 0;
            if (hijoIzq != null) {
                cantidad += hijoIzq.enNivel(nivel - 1);
            }
            if (hijoDer != null) {
                cantidad += hijoDer.enNivel(nivel - 1);
            }
            return cantidad;
        }
    }

    /*
    public void recorrerPorNiveles() {
        Cola<TElementoAB<T>> cola = new Cola<>();
        cola.poneEnCola(this);
        while (!cola.vacia()) {
            TElementoAB<T> actual = cola.quitaDeCola();
            System.out.println(actual.getEtiqueta());
            if (actual.getHijoIzq() != null) 
                cola.poneEnCola((TElementoAB<T>) actual.getHijoIzq());
                
            if (actual.getHijoDer() != null) 
                cola.poneEnCola((TElementoAB<T>) actual.getHijoDer());
        }
    }
*/
    public void espejar() {
        IElementoAB<T> temp = hijoIzq;
        hijoIzq = hijoDer;
        hijoDer = temp;
        if (hijoIzq != null) ((TElementoAB<T>) hijoIzq).espejar();
        if (hijoDer != null) ((TElementoAB<T>) hijoDer).espejar();
    }

    public boolean esIdentico(TElementoAB<T> otro) {
        if (otro == null) 
            return false;
        if (!etiqueta.equals(otro.getEtiqueta())) 
            return false;
        boolean izqIgual;
        if (hijoIzq == null){
            izqIgual = otro.getHijoIzq() == null;
        }else{ 
            izqIgual = ((TElementoAB<T>) hijoIzq).esIdentico((TElementoAB<T>) otro.getHijoIzq());
        }
        boolean derIgual;
        if (hijoDer == null){ 
            derIgual = otro.getHijoDer() == null;
        } else { 
            derIgual = ((TElementoAB<T>) hijoDer).esIdentico((TElementoAB<T>) otro.getHijoDer());
        }
        return izqIgual && derIgual;
    }
    
    //DFS = recorrer el árbol yendo por las ramas hasta el fondo antes de volver
    public void dfs(LinkedList<Comparable> recorrido) {
        recorrido.add(this.etiqueta);
        if (hijoIzq != null) {
            ((TElementoAB<T>) hijoIzq).dfs(recorrido);
        }
        if (hijoDer != null) {
            ((TElementoAB<T>) hijoDer).dfs(recorrido);
        }
    }

    //TD1
    public int altura(){

        int alturaIzq = (this.hijoIzq != null) ? ((TElementoAB<T>)hijoIzq).altura() : -1;
        int alturaDer = (this.hijoDer != null) ? ((TElementoAB<T>)hijoDer).altura() : -1;

        return 1 + Math.max(alturaIzq, alturaDer);
    }

    //TD1
    public int contarInternos() {
        int internos = 0;
        if (this.hijoIzq == null && this.hijoDer == null) {
            return internos += 0;
        }

        internos += 1;
        if (this.hijoIzq != null) {
            internos += this.hijoIzq.contarInternos();
        }
        if (this.hijoDer != null) {
            internos += this.hijoDer.contarInternos();
        }

        return internos;
    }

    //TD1
    public int completos() {
        int cantidad = 0;

        if (hijoIzq != null && hijoDer != null) {
            cantidad = 1;
        }

        if (hijoIzq != null) {
            cantidad += hijoIzq.completos();
        }

        if (hijoDer != null) {
            cantidad += hijoDer.completos();
        }

        return cantidad;
    }

    public Comparable obtenerMenorClave(){
        Comparable claveMenor = this.etiqueta;
        if(hijoIzq != null){
            claveMenor = hijoIzq.obtenerMenorClave();
        }
        return claveMenor;
    }

    public Comparable obtenerMayorClave(){
        Comparable claveMayor = this.etiqueta;
        if(hijoDer != null){
            claveMayor = hijoDer.obtenerMayorClave();
        }
        return claveMayor;
    }

    public Comparable obtenerInmediatoAnterior(Comparable unaClave){
        LinkedList<Comparable> lista = new LinkedList<>();
        this.inOrden((LinkedList<T>) lista);

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).compareTo(unaClave) == 0) {
                return (i > 0) ? lista.get(i - 1) : null;
            }
        }
        return null; // si no lo encuentra
    }

    public String listarHojasConNiveles(int nivelDelNodo){
        StringBuilder lista = new StringBuilder("");
        boolean esHoja = true;
        if(hijoIzq != null){
            lista.append(hijoIzq.listarHojasConNiveles(nivelDelNodo+1));
            esHoja = false;
        }
        if(hijoDer != null){
            if (lista.equals("")){
                lista.append(hijoDer.listarHojasConNiveles(nivelDelNodo+1));
            }else{
                lista.append("\n"+hijoDer.listarHojasConNiveles(nivelDelNodo+1));
            }
            esHoja = false;
        }
        if(esHoja){
            return etiqueta.toString() + " Nivel: " + nivelDelNodo;
        }else{
            return lista.toString();
        }
    }

    public boolean esDeBusqueda(){
        boolean esBusqueda = true;
        if(hijoIzq != null){
            if(hijoIzq.getEtiqueta().compareTo(etiqueta) < 0){
                esBusqueda = hijoIzq.esDeBusqueda();
                if(!esBusqueda){
                    return false;
                }
            }else{
                return false;
            }
        }if(hijoDer != null){
            if(hijoDer.getEtiqueta().compareTo(etiqueta) > 0){
                esBusqueda = hijoDer.esDeBusqueda();
                if(!esBusqueda){
                    return false;
                }
            }else{
                return false;
            }
        }
        return esBusqueda;
    }
}