package PDS.UT5.PD7;

import java.util.Collections;
import java.util.LinkedList;

public class TArbolTrieTelefonos implements IArbolTrieTelefonos {

    private TNodoTrieTelefonos raiz;

    @Override
    public LinkedList<TAbonado> buscarTelefonos(String pais, String area) {
        LinkedList<TAbonado> listaResultado = new LinkedList<>();
        if (raiz == null){
            return listaResultado;
        }
        raiz.buscarTelefonos(pais + area, listaResultado);
        Collections.sort(listaResultado);
        return listaResultado;
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        if (raiz == null){
            raiz = new TNodoTrieTelefonos();
        }
        raiz.insertar(unAbonado);
    }
}
