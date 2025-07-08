package PDS.UT6;

import java.util.ArrayList;

public class MedicionBuscarArrayList extends Medible {

    private ArrayList<String> arrayList;

    public MedicionBuscarArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public void ejecutar(Object... params) {
        if (params.length != 2 || !(params[0] instanceof Integer) || !(params[1] instanceof String[])) {
            throw new IllegalArgumentException("Parámetros inválidos. Se esperan: (int repeticion, String[] palabras)");
        }

        int repeticion = (int) params[0];
        String[] palabras = (String[]) params[1];
        for (int i = 0; i < repeticion; i++) {
            for (String palabra : palabras) {
                arrayList.contains(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.arrayList;
    }
}
