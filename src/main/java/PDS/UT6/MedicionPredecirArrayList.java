package PDS.UT6;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Clase que mide el tiempo y memoria al predecir palabras (autocompletar)
 * usando un ArrayList.
 */
public class MedicionPredecirArrayList extends Medible {

    private ArrayList<String> arrayList;

    public MedicionPredecirArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public void ejecutar(Object... params) {
        if (params.length != 2 || !(params[0] instanceof Integer) || !(params[1] instanceof String[])) {
            throw new IllegalArgumentException("Parámetros inválidos. Se esperan: (int repeticion, String[] palabras)");
        }

        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];
        LinkedList<String> resultados = new LinkedList<>();

        for (int i = 0; i < repeticion; i++) {
            for (String palabra : palabras) {
                for (String elemento : arrayList) {
                    if (elemento.startsWith(palabra)) {
                        resultados.add(elemento);
                    }
                }
                resultados.clear(); // limpiar para siguiente iteración
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.arrayList;
    }
}
