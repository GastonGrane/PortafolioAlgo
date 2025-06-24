package PDS.UT3.PD11;

import TDAS.UT3.Lista;

public class ParImpar {
    public static void main(String[] args) {
        Lista<Integer> listaDesordenada =  new Lista<>();
        listaDesordenada.insertar(4,4);
        // 4, 71, 3, 6, 12, 8, 35, 11, 22, 17
        listaDesordenada.insertar(71,71);
        listaDesordenada.insertar(3,3);
        listaDesordenada.insertar(6,6);
        listaDesordenada.insertar(12,12);
        listaDesordenada.insertar(8,8);
        listaDesordenada.insertar(35,35);
        listaDesordenada.insertar(11,11);
        listaDesordenada.insertar(22,22);
        listaDesordenada.insertar(17,17);

        System.out.println(listaDesordenada.imprimir());

        Lista<Integer> listaResultante = listaDesordenada.ordenarParImpar();

        System.out.println(listaResultante.imprimir());

    }
}
