package TDAS.ArbolesTrie;

/**
 * Representa el nodo raíz de un árbol binario.
 * Es una especialización de ElementoAB que no tiene padre.
 */
public class ElementoRaiz<T> extends TElementoAB<T> {

    public ElementoRaiz(Comparable etiqueta, T dato) {
        super(etiqueta, dato);
        // No seteamos padre, ya que por definición, la raíz no tiene.
    }


    //Esto es útil si alguien en el código intenta hacerlo por error.
    public void setPadre(IElementoAB<T> padre) {
        throw new UnsupportedOperationException("La raíz no tiene padre.");
    }

    //Siempre retorna null, ya que la raíz no tiene padre.
    public IElementoAB<T> getPadre() {
        return null;
    }
}

