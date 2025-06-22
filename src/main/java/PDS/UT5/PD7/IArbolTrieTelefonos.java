package PDS.UT5.PD7;

import java.util.LinkedList;

public interface IArbolTrieTelefonos {

    LinkedList<TAbonado> buscarTelefonos(String pais, String area);

    void insertar(TAbonado unAbonado);
    
}
