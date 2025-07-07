package PDS.UT6;

import java.util.HashMap;

public class MedicionBuscarHashMap extends Medible { 
    
    // Atributo que almacena el hashMap sobre la cual se realizarán las búsquedas
    private HashMap<String, String> hashMap;

    /**
     * Constructor de la clase MedicionBuscarHashMap
     * Inicializa el HashMap sobre la cual se realizarán las búsquedas.
     *
     * @param hashMap HashMAp que se utilizará para las búsquedas.
     */
    public MedicionBuscarHashMap(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void ejecutar(Object... params) {
        // La operación a medir es la búsqueda de elementos en la LinkedList.
        // Se espera que params contenga dos elementos: el número de repeticiones y un arreglo de palabras a buscar.
        
        if (params.length != 2 || !(params[0] instanceof Integer) || !(params[1] instanceof String[])) {
            throw new IllegalArgumentException("Parámetros inválidos. Se esperan: (int repeticion, String[] palabras)");
        }
        
        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];
        for (int i = 0; i < repeticion; i++) {
            for (String palabra : palabras) {
                hashMap.get(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        // El objeto cuyo tamaño en memoria se medirá es la LinkedList utilizada para las búsquedas.
        return this.hashMap;
    }
}
