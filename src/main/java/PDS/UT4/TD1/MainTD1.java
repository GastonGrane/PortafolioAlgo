package PDS.UT4.TD1;

import TDAS.ArbolesTrie.TArbolBB;

public class MainTD1 {
    public static void main(String[] args) {
        TArbolBB<Integer> arbolBB = new TArbolBB<>();

        arbolBB.insertar(30, 30);
        arbolBB.insertar(20, 20);
        arbolBB.insertar(40, 40);
        arbolBB.insertar(10, 10);
        arbolBB.insertar(5, 5);
        arbolBB.insertar(25, 25);
        arbolBB.insertar(15, 15);
        arbolBB.insertar(12, 12);
        arbolBB.insertar(17, 17);

        System.out.println("Altura: " + arbolBB.alturaArbol());
        System.out.println("Tamaño: " + arbolBB.obtenerTamañoEnelArbol());
        System.out.println("Hojas: " + arbolBB.hojasArbol());
        System.out.println("Internos: " + arbolBB.internosArbol());
        System.out.println("Completos: " + arbolBB.completosArbol());
        System.out.println("Nodos en nivel 3: " + arbolBB.enNiveldelArbol(3));
    }
}
