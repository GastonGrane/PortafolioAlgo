package PDS.UT3.PPP1;

import TDAS.Lista;

public class InsertarOrdenados {
    public static void main(String[] args) {

        //Probamos insertarOrdenado
        Lista<String> lista1 = new Lista<>();
        lista1.insertarOrdenado("B", 2);
        lista1.insertarOrdenado("A", 1);
        lista1.insertarOrdenado("E", 5);
        System.out.println("Lista1 luego de insertarOrdenado:");
        System.out.println(lista1.imprimir());  // Esperado: A,B,C o 1,2,3


        //Probamops eliminarDuplicados
        lista1.insertarOrdenado("B", 2);
        lista1.insertarOrdenado("A", 1);
        System.out.println();
        System.out.println("Lista1 con duplicados:");
        System.out.println(lista1.imprimir());  // Esperado: A,A,B,B,C
        lista1.eliminarDuplicados();
        System.out.println("Lista1 luego de eliminarDuplicados:");
        System.out.println(lista1.imprimir());  // Esperado: A,B,C


        //Probamos mezclarCon
        Lista<String> lista2 = new Lista<>();
        lista2.insertarOrdenado("C", 3);
        lista2.insertarOrdenado("F", 6);
        System.out.println();
        Lista<String> listaFinal = lista1.mezclarCon(lista2);
        System.out.println("Resultado de mezclar lista1 y lista2:");
        System.out.println(listaFinal.imprimir());


        // Verificamos que lista1 y lista2 quedaron vacías como pide el enunciado
        System.out.println();
        System.out.println("Verificar que las listas originales quedaron vacías:");
        System.out.println("Lista1: " + lista1.imprimir());
        System.out.println("Lista2: " + lista2.imprimir());
    }
}
