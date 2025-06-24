package TestTDAS;

import TDAS.ArbolesTrie.TArbolAVL;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestElementoAVL {
    
    @Test
    public void testEliminarEnAVL() {
    TArbolAVL<Integer> arbol = new TArbolAVL<>();

    arbol.insertar(30, 30);
    arbol.insertar(20, 20);
    arbol.insertar(40, 40);
    arbol.insertar(10, 10);
    arbol.insertar(5, 5);

    List<Integer> resultado = arbol.inOrden();
    assertEquals(List.of(5, 10, 20, 30, 40), resultado);
    
    // Eliminar hoja
    arbol.eliminar(40);
    List<Integer> resultado2 = arbol.inOrden();
    assertEquals(List.of(5, 10, 20, 30), resultado2);

    // Eliminar nodo con un solo hijo
    arbol.eliminar(30);
    List<Integer> resultado3 = arbol.inOrden();
    assertEquals(List.of(5, 10, 20), resultado3);

    }

    @Test
    public void testBuscarEnAVL() {
        TArbolAVL<Integer> arbol = new TArbolAVL<>();

        arbol.insertar(30, 30);
        arbol.insertar(20, 20);
        arbol.insertar(40, 40);
        arbol.insertar(10, 10);
        arbol.insertar(5, 5);

        Integer resultado = arbol.buscar(20);
        assertEquals(Integer.valueOf(20), resultado);

        Integer resultado2 = arbol.buscar(50);
        assertEquals(null, resultado2);
    }

    @Test
    public void testInsertarEnAVL() {
        TArbolAVL<Integer> arbol = new TArbolAVL<>();

        arbol.insertar(30, 30);
        arbol.insertar(20, 20);
        arbol.insertar(40, 40);
        arbol.insertar(10, 10);
        arbol.insertar(5, 5);

        List<Integer> resultado = arbol.inOrden();
        assertEquals(List.of(5, 10, 20, 30, 40), resultado);
    }

    @Test
    public void testBalanceoAVL() {
        TArbolAVL<Integer> arbol = new TArbolAVL<>();

        arbol.insertar(30, 30);
        arbol.insertar(20, 20);
        arbol.insertar(40, 40);
        arbol.insertar(10, 10);
        arbol.insertar(5, 5);

        List<Integer> resultado = arbol.inOrden();
        assertEquals(List.of(5, 10, 20, 30, 40), resultado);

        // Verificar el balanceo
        assertEquals(2, arbol.getAltura());
    }

    @Test
    public void testRecorridosAVL() {
        TArbolAVL<Integer> arbol = new TArbolAVL<>();

        arbol.insertar(30, 30);
        arbol.insertar(20, 20);
        arbol.insertar(40, 40);
        arbol.insertar(10, 10);
        arbol.insertar(5, 5);

        List<Integer> preOrden = arbol.preOrden();
        assertEquals(List.of(30, 10, 5, 20, 40), preOrden); // chequeados con el simulador de avl

        List<Integer> inOrden = arbol.inOrden();
        assertEquals(List.of(5, 10, 20, 30, 40), inOrden);

        List<Integer> postOrden = arbol.postOrden();
        assertEquals(List.of(5, 20, 10, 40, 30), postOrden);
    }
}


