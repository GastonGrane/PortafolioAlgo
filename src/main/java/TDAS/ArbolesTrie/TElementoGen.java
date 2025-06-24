package TDAS.ArbolesTrie;

public class TElementoGen<Personas> extends TElementoAB<Personas> {

    public TElementoGen(Comparable unaEtiqueta, Personas unDato) {
        super(unaEtiqueta, unDato);
    }

    private TElementoGen<Personas> encontrarAncestroComun(IElementoAB<Personas> nodo, Personas p1, Personas p2) {
        TElementoAB<Personas> nodop1 = new TElementoAB<Personas>("p1", p1);
        TElementoAB<Personas> nodop2 = new TElementoAB<Personas>("p2", p2);
    
        if (nodo == null) return null;

        if (nodo.getEtiqueta().equals(nodop1.getEtiqueta()) || nodo.getEtiqueta().equals(nodop2.getEtiqueta())) {
            return (TElementoGen<Personas>) nodo;
        }

        IElementoAB<Personas> izquierda = encontrarAncestroComun(nodo.getHijoIzq(), p1, p2);
        IElementoAB<Personas> derecha = encontrarAncestroComun(nodo.getHijoDer(), p1, p2);

        if (izquierda != null && derecha != null) {
            return (TElementoGen<Personas>) nodo; // este nodo es el primer punto donde se cruzan
        }

        return (TElementoGen<Personas>) ((izquierda != null) ? izquierda : derecha);
    }

    public void Parientes(IElementoAB<Personas> nodo, Personas clave1, Personas clave2) {
        TElementoGen<Personas> ancestro = encontrarAncestroComun(nodo, clave1, clave2);
        if (ancestro == null) 
            System.out.println("Arbol Vacio");

        int dist1 = ancestro.distanciaDesdePersonas(clave1);
        int dist2 = ancestro.distanciaDesdePersonas(clave2);

        boolean enIzq = contiene((TElementoGen<Personas>) ancestro.getHijoIzq(), clave1);
        boolean enIzq2 = contiene((TElementoGen<Personas>) ancestro.getHijoIzq(), clave2);

        if ((enIzq && enIzq2) || (!enIzq && !enIzq2)){
            System.out.println("Son consanguineos");
        }else{
            System.out.println("Son politicos");
        }

        System.out.printf("La distancia entre los nodos es de: ", dist1 + dist2);
        
    }

    private int distanciaDesdePersonas(Personas clave) {
        if (etiqueta.equals(clave)) {
            return 0;

        } else if (clave.equals(etiqueta) && hijoIzq != null) {
            int dist = ((TElementoGen<Personas>)hijoIzq).distanciaDesdePersonas(clave);
            return (dist == -1) ? -1 : 1 + dist;

        } else if (clave.equals(etiqueta) && hijoDer != null) {
            int dist = ((TElementoGen<Personas>)hijoDer).distanciaDesdePersonas(clave);
            return (dist == -1) ? -1 : 1 + dist;
        }
        return -1; // clave no encontrada
    }

private boolean contiene(TElementoGen<Personas> nodo, Personas clave1) {
    if (nodo == null) 
        return false;
    if (nodo.getEtiqueta().equals(clave1)) 
        return true;

    return contiene((TElementoGen<Personas>) nodo.getHijoIzq(), clave1) || contiene((TElementoGen<Personas>) nodo.getHijoDer(), clave1);
}

    
}
