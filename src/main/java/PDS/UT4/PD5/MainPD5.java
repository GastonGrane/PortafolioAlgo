package PDS.UT4.PD5;

import TDAS.ArbolesTrie.TArbolBB;

public class MainPD5 {
    public static void main(String[] args) {
        TArbolBB<Integer> arbol = new TArbolBB<>();

        arbol.insertar(30, 30);
        arbol.insertar(20, 20);
        arbol.insertar(40, 40);
        arbol.insertar(10, 10);
        arbol.insertar(5, 5);
        arbol.insertar(25, 25);
        arbol.insertar(15, 15);
        arbol.insertar(12, 12);
        arbol.insertar(17, 17);

        //Menor clave
        System.out.println("Menor clave: " + arbol.obtenerMenorClave());
        System.out.println();

        //Mayor clave
        System.out.println("Mayor clave: " + arbol.obtenerMayorClave());
        System.out.println();

        //Clave inmediata anterior
        System.out.println("Anterior a 25: " + arbol.obtenerInmediatoAnterior(25));
        System.out.println("Anterior a 10: " + arbol.obtenerInmediatoAnterior(10));
        System.out.println();

        //Nodos en nivel 2
        System.out.println("Cantidad de nodos en nivel 2: " + arbol.enNiveldelArbol(2));
        System.out.println();

        //Listar hojas con nivel
        System.out.println("Hojas con sus niveles:");
        System.out.println(arbol.listarHojasConNiveles());
        System.out.println();

        //Verificar si es ABB
        System.out.println("¿Es árbol de búsqueda?: " + arbol.esABB());
    }
}
