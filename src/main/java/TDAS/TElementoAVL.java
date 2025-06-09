package TDAS;

public class TElementoAVL<T> extends TElementoAB<T> {
    protected int altura; // Altura del nodo en el árbol AVL // se cambia a protected para que sea accesible desde la clase TArbolAVL

    public TElementoAVL(Comparable etiqueta, T dato) {
        super(etiqueta, dato);
        this.altura = 0; // Inicialmente, la altura es 0
    }

    public int getAltura() {
        return this.altura; // se cambio a this.altura para que devuelva la altura del nodo 
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
/*Se cambia el metodo calcular altura por un metodo mas sencillo usando 
 * condicionales con si verdadero si falso, dejo el metodo original comentado
 * para que quede como referencia y vean la diferencia
 *  public void calcularAltura() {
        int alturaIzq = -1;
        int alturaDer = -1;
    
        if (this.hijoIzq != null) {
            ElementoAVL<T> izquierdo = (ElementoAVL<T>) this.hijoIzq;
            alturaIzq = izquierdo.getAltura();
        }
    
        if (this.hijoDer != null) {
            ElementoAVL<T> derecho = (ElementoAVL<T>) this.hijoDer;
            alturaDer = derecho.getAltura();
        }
    
        this.altura = Math.max(alturaIzq, alturaDer) + 1;
    }
 */
    
    public void calcularAltura() {
        int altIzq = (hijoIzq != null) ? ( (TElementoAVL<T>) hijoIzq).getAltura() : -1;
        int altDer = (hijoDer != null) ? ((TElementoAVL<T>) hijoDer).getAltura() : -1;
        altura = Math.max(altIzq, altDer) + 1; // la altura del nodo es el maximo de las alturas de los hijos + 1
    }


    public int obtenerFactorBalance() { // se hizo el mismo cambio que arriba, ver el metodo en el log de git para comparar, pero escencialmente es lo mismo
        int alturaIzq = (hijoIzq != null) ? ((TElementoAVL<T>) hijoIzq).getAltura() : -1; // si hijoIzq es null, la altura es -1
        int alturaDer = (hijoDer != null) ? ((TElementoAVL<T>) hijoDer).getAltura() : -1; // si hijoDer es null, la altura es -1
        return alturaIzq - alturaDer; // el factor del balance es la altura del hijo izquierdo menos la altura del hijo derecho (osea la diff)
    }
    
    


    private TElementoAVL<T> rotarIzquierda() {
        TElementoAVL<T> nuevaRaiz = (TElementoAVL<T>) hijoDer; 
        hijoDer = nuevaRaiz.hijoIzq;  // hijoDer se convierte en el hijo izquierdo de la nueva raíz
        nuevaRaiz.hijoIzq = this; // el nodo actual se convierte en el hijo izquierdo de la nueva raíz
        this.calcularAltura(); // actualizamos la altura del nodo actual
        nuevaRaiz.calcularAltura(); // actualizamos la altura de la nueva raíz
        return nuevaRaiz; 
    }

    private TElementoAVL<T> rotarDerecha() {
        TElementoAVL<T> nuevaRaiz = (TElementoAVL<T>) hijoIzq;
        hijoIzq = nuevaRaiz.hijoDer;
        nuevaRaiz.hijoDer = this;
        this.calcularAltura();
        nuevaRaiz.calcularAltura();
        return nuevaRaiz;
    }

    private TElementoAVL<T> rotarIzqDer() {
        hijoIzq = ((TElementoAVL<T>) hijoIzq).rotarIzquierda();
        return rotarDerecha();
    }

    private TElementoAVL<T> rotarDerIzq() {
        hijoDer = ((TElementoAVL<T>) hijoDer).rotarDerecha();
        return rotarIzquierda();
    }

    protected TElementoAVL<T> balancear() { // para el balaceo se cambio la logica ya que antes se hacian las rotaciones dentro del mismo metodo
        calcularAltura(); // se obtiene la altura si no , no es posible balancear
        int balance = obtenerFactorBalance();  

        if (balance > 1) { // > 1 quiere decir que el sub izq es mas alto osea que el desbalanceo es hacia la izquierda
            if (((TElementoAVL<T>) hijoIzq).obtenerFactorBalance() < 0) { // si el hijo izquierdo tiene un factor de balance < 0, significa que el sub arbol derecho es mas alto
                return rotarIzqDer(); // se aploca LR
            } else {
                return rotarDerecha(); // Si no, se aplica R 
            }
        }

        if (balance < -1) { // < -1 quiere decir que el sub der es mas alto osea que el desbalanceo es hacia la derecha
            if (((TElementoAVL<T>) hijoDer).obtenerFactorBalance() > 0) { // si el hijo derecho tiene un factor de balance > 0, significa que el sub arbol izquierdo es mas alto
                return rotarDerIzq(); // se aplica RL
            } else {
                return rotarIzquierda(); // Si no, se aplica L
            }
        }

        return this; /*  Lo devuelve si no aplica la condicion de desbalance*/
    }
    
    public boolean insertar(IElementoAB<T> nuevo) {
        int comp = nuevo.getEtiqueta().compareTo(etiqueta); //aux comp para comparar etiquetas

        if (comp < 0) { // Si la etiqueta del nuevo elemento es menor que la del nodo actual
            if (hijoIzq == null) {  // no hay izq, se agrega
                hijoIzq = nuevo; 
            } else {
                hijoIzq = ((TElementoAVL<T>) hijoIzq).insertar(nuevo) ? ((TElementoAVL<T>) hijoIzq).balancear() : hijoIzq; // intenta insertar nuevo en hijoIzq, si no se puede, devuelve hijoIzq, si puede llama a balancear
            }
        } else if (comp > 0) { // Si la etiqueta del nuevo elemento es mayor que la del nodo actual
            if (hijoDer == null) { // no hay der, se agrega
                hijoDer = nuevo; 
            } else {
                hijoDer = ((TElementoAVL<T>) hijoDer).insertar(nuevo) ? ((TElementoAVL<T>) hijoDer).balancear() : hijoDer; // intenta insertar nuevo en hijoDer, si no se puede, devuelve hijoDer, si puede llama a balancear
            }
        } else {
            return false; // La etiqueta ya existe, no se inserta
        }

        calcularAltura(); // calcular la altura del nodo actual
        return true;
    }

    @Override
    public IElementoAB<T> eliminar(Comparable unaEtiqueta) {
        int comp = unaEtiqueta.compareTo(this.etiqueta);
    
        if (comp < 0 && hijoIzq != null) { // Si la etiqueta es menor, buscar en el subárbol izquierdo
            hijoIzq = hijoIzq.eliminar(unaEtiqueta); 
            if (hijoIzq != null) { // si hijoIzq no es null, balancear
                hijoIzq = ((TElementoAVL<T>) hijoIzq).balancear();
            }
        } else if (comp > 0 && hijoDer != null) { // Si la etiqueta es mayor, buscar en el subárbol derecho
            hijoDer = hijoDer.eliminar(unaEtiqueta);
            if (hijoDer != null) {  // si hijoDer no es null, balancear
                hijoDer = ((TElementoAVL<T>) hijoDer).balancear();
            }
        } else {
            // encuentra el nodo a eliminar
            if (hijoIzq == null) return hijoDer; //si no tiene hijo izquierdo, devuelve el hijo derecho
            if (hijoDer == null) return hijoIzq; //si no tiene hijo derecho, devuelve el hijo izquierdo
    
            // Caso reemplazar el nodo con el mayor del subárbol izquierdo
            TElementoAVL<T> maxIzq = (TElementoAVL<T>) hijoIzq; // buscar el mayor del subárbol izquierdo
            while (maxIzq.hijoDer != null) { // recorrer el subárbol izquierdo hasta encontrar el mayor
                maxIzq = (TElementoAVL<T>) maxIzq.hijoDer; 
            }
    
            this.etiqueta = maxIzq.getEtiqueta(); //remplaza nodo act con el mayot
            this.dato = maxIzq.getDatos(); 
    
            hijoIzq = hijoIzq.eliminar(maxIzq.getEtiqueta()); // eliminar el nodo mayor del subárbol izquierdo
        }
    
        calcularAltura();
        return balancear();
    }
    
    
}
