package TDAS.Hashing;

public class THash<K, V> implements IHash<K, V> {

    // Implementación de la tabla hash
    // Aquí se pueden definir los atributos necesarios, como un array de buckets, etc.

    private static class Entrada<K, V> {
        K clave;
        V valor;

        Entrada(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }

    private Entrada<K, V>[] tabla;
    private int m;  // tamaño de la tabla
    private int tamanio;  // cantidad actual de elementos

    @SuppressWarnings("unchecked")
    public THash(int capacidad) {
        this.m = capacidad;
        this.tabla = new Entrada[m];
        this.tamanio = 0;
    }
    
    @Override
    public V buscar(K unaClave) {
        int i = 0;
        while (i < tamanio) {
            int indice = (funcionHashing(unaClave) + i) % tamanio;
            Entrada<K, V> entrada = tabla[indice];

            if (entrada == null) {
                return null;
            }

            if (entrada.clave.equals(unaClave)) {
                return entrada.valor;
            }

            i++;
        }

        return null;
    }

    @Override
    public boolean insertar(K unaClave, V unValor) {
        int i = 0;
        while (i < m) {
            int j = funcionHashing(unaClave);
            Entrada<K, V> entrada = tabla[j];

            if (entrada == null) {
                // Casilla vacía -- insertar nueva entrada
                tabla[j] = new Entrada<>(unaClave, unValor);
                tamanio++;
                return true;
            } else if (entrada.clave.equals(unaClave)) {
                // Clave ya existe -- actualizar valor
                entrada.valor = unValor;
                return true;
            } else {
                i++; // Sondeo lineal
            }
        }
        // No se encontró espacio -- tabla llena
        return false;
    }

    protected int funcionHashing(K unaClave) {
        // Implementar una función de hashing adecuada para las claves
        // Por ejemplo, se puede usar el método hashCode() de la clave y aplicar un módulo con el tamaño de la tabla
        int hash = unaClave.hashCode();
        return hash % tamanio;
    }
}
