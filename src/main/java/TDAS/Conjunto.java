package TDAS;

import java.util.ArrayList;
import java.util.List;

public class Conjunto<T, K extends Comparable<K>> implements IConjunto<T, K> {

    private Lista<T> lista;
    private List<K> claves;

    public Conjunto() {
        this.lista = new Lista<>();
        this.claves = new ArrayList<>();
    }

    @Override
    public void agregar(T elemento, K clave) {
        if (!pertenece(clave)) {
            lista.insertar(elemento, clave);
            claves.add(clave);
        }
    }

    @Override
    public boolean eliminar(K clave) {
        if (lista.eliminar(clave)) {
            claves.remove(clave);
            return true;
        }
        return false;
    }

    @Override
    public boolean pertenece(K clave) {
        return lista.buscar(clave) != null;
    }

    @Override
    public int tamanio() {
        return lista.cantElementos();
    }

    @Override
    public void mostrar() {
        System.out.println("Conjunto: " + lista.imprimir(", "));
    }
// de momento esta solucion no es la mejor, debido a que no tenemos un metodo get primero en el tda o similar por lo cual se crea una lista auxiliar
    // y se recorre para mostrar los elementos
    @Override
    public IConjunto<T, K> union(IConjunto<T, K> otro) {
        Conjunto<T, K> resultado = new Conjunto<>(); 

        List<K> clavesA = this.claves; // aux 
        List<K> clavesB = ((Conjunto<T, K>) otro).claves; // claves del otro conjunto 

        int i = 0, j = 0;
        while (i < clavesA.size() && j < clavesB.size()) { // mientras haya elementos en ambos conjuntos 
            K a = clavesA.get(i); // clave del conjunto A
            K b = clavesB.get(j);   // clave del conjunto B
            int cmp = a.compareTo(b); // comparo las claves

            if (cmp < 0) {
                resultado.agregar(this.lista.buscar(a), a); // si la clave de A es menor se  agrega el elemento de A al resultado
                i++;
            } else if (cmp > 0) {
                resultado.agregar(((Conjunto<T, K>) otro).lista.buscar(b), b); // si la clave de B es menor se agrega el elemento de B al resultado
                j++;
            } else {
                resultado.agregar(this.lista.buscar(a), a); // si son iguales se agrega el elemento de A al resultado
                i++;
                j++;
            }
        }

        while (i < clavesA.size()) { // si quedan elementos en a se toma la clave y se agregfa
            K a = clavesA.get(i++); 
            resultado.agregar(this.lista.buscar(a), a);
        }

        while (j < clavesB.size()) { // lo miusmo pero para b
            K b = clavesB.get(j++);
            resultado.agregar(((Conjunto<T, K>) otro).lista.buscar(b), b); 
        }

        return resultado;
    }

    @Override
    public IConjunto<T, K> interseccion(IConjunto<T, K> otro) { 
        Conjunto<T, K> resultado = new Conjunto<>();

        List<K> clavesA = this.claves;
        List<K> clavesB = ((Conjunto<T, K>) otro).claves;

        int i = 0, j = 0;
        while (i < clavesA.size() && j < clavesB.size()) { // mientras haya elementos en ambos conjuntos
            K a = clavesA.get(i);
            K b = clavesB.get(j);
            int cmp = a.compareTo(b); // comparo las claves
            if (cmp < 0) { // si la clave de A es menor , a  esta antes q b 
                i++; 
            } else if (cmp > 0) { // si la clave de B es menor, b esta antes que a
                j++;
            } else { // si son iguales se agrega el elemento de A al resultado
                resultado.agregar(this.lista.buscar(a), a);
                i++;
                j++;
            }
        }

        return resultado;
    }
}
