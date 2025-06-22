package PDS.UT5.PD7;

import java.util.LinkedList;

public class TNodoTrieTelefonos implements INodoTrieTelefonos {

    private TAbonado abonado;
    private static final int CANT_NUM = 10;
    private TNodoTrieTelefonos[] hijos;

    public TNodoTrieTelefonos(){
        hijos = new TNodoTrieTelefonos[CANT_NUM];
    }

    @Override
    public void buscarTelefonos(String primerosDigitos, LinkedList<TAbonado> abonados) {
        TNodoTrieTelefonos nodoActual = this;
        for (int i = 0; i < primerosDigitos.length(); i++) {
            int indice = primerosDigitos.charAt(i) - '0';
            if (nodoActual.hijos[indice] == null) {
                return;
            }
            nodoActual = nodoActual.hijos[indice];
        }
        buscarTelefonos(abonados, nodoActual);
    }

    private void buscarTelefonos(LinkedList<TAbonado> abonados, TNodoTrieTelefonos unNodo){
        TNodoTrieTelefonos nodoActual = unNodo;
        if (nodoActual.getAbonado() != null){
            abonados.add(nodoActual.getAbonado());
            return;
        }
        for (int c = 0; c < CANT_NUM; c++) {
            if (nodoActual.hijos[c] != null) {
                buscarTelefonos(abonados, nodoActual.hijos[c]);
            }
        }
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        String unTelefono = unAbonado.getTelefono();
        TNodoTrieTelefonos nodo = this;
        for (int c = 0; c < unTelefono.length(); c++) {
            int indice = unTelefono.charAt(c) - '0';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrieTelefonos();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.setAbonado(unAbonado);
    }

    public TAbonado getAbonado(){
        return this.abonado;
    }

    public void setAbonado(TAbonado unAbonado){
        this.abonado = unAbonado;
    }
}