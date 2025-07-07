package PDS.UT6;

import java.io.Serializable;

/**
 * Clase abstracta que define el comportamiento de los objetos medibles.
 * Permite medir el tiempo de ejecución y el consumo de memoria de una operación.
 */
public abstract class Medible implements Serializable {

    /**
     * Método que mide el tiempo de ejecución y el consumo de memoria de la operación definida en la clase hija.
     *
     * @param params Parámetros necesarios para ejecutar la operación.
     * @return Un objeto Medicion que contiene el nombre de la clase, el tamaño del objeto en memoria y el tiempo de ejecución.
     */
    public Medicion medir(Object... params) {
        // Se obtiene el tiempo de inicio (en nanosegundos)
        long init = System.nanoTime();

        // Se ejecuta la operación definida en la clase hija con los parámetros proporcionados
        ejecutar(params);

        // Se obtiene el tiempo de finalización (en nanosegundos)
        long fin = System.nanoTime();
        long tiempoEjecucion = fin - init;

        // Se crea y retorna un objeto Medicion con el nombre de la clase, el tamaño del objeto en memoria y el tiempo de ejecución
        return new Medicion(this.getClass().getSimpleName(), ObjectSizeFetcher.getObjectSize(getObjetoAMedirMemoria()), tiempoEjecucion);
    }

    /**
     * Método abstracto que debe ser implementado por las clases hijas para definir la operación a medir.
     *
     * @param params Parámetros necesarios para ejecutar la operación.
     */
    abstract public void ejecutar(Object... params);

    /**
    * Método abstracto que debe ser implementado por las clases hijas para obtener el objeto cuyo tamaño en memoria se medirá.
    *
    * @return Un objeto que representa el estado actual de la clase, cuyo tamaño en memoria será medido.
    */
    abstract public Object getObjetoAMedirMemoria();
}
