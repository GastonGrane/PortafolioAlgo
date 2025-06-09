package TestTDAS;

import TDAS.TArbolBB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestElementoBB {

    private TArbolBB<Integer> arbol;

    @BeforeEach
    void init() {
        arbol = new TArbolBB<>();
        arbol.insertar(30, 30);
        arbol.insertar(20, 20);
        arbol.insertar(40, 40);
        arbol.insertar(10, 10);
        arbol.insertar(5, 5);
        arbol.insertar(25, 25);
        arbol.insertar(15, 15);
        arbol.insertar(12, 12);
        arbol.insertar(17, 17);
        arbol.insertar(2, 2);
    }

    @Test
    public void testEliminar_hoja() {

    arbol.eliminar(12);
    List<Integer> resultado = arbol.inOrden();
    assertEquals(List.of(2, 5, 10, 15,17,20, 25, 30, 40), resultado);
    }

    @Test
    public void testEliminar_conUnHijo() {

    arbol.eliminar(5);
    List<Integer> resultado = arbol.preOrden();
    assertEquals(List.of( 30, 20, 10, 2, 15, 12, 17, 25, 40), resultado);
    }

    @Test
    public void testEliminar_conDosHijo() {

    arbol.eliminar(20);
    List<Integer> resultado = arbol.postOrden();
    assertEquals(List.of(2, 5, 12, 15,10, 25, 17, 40, 30), resultado);
    }

    @Test
    public void test_tamanio() {

    // Cuando
    Integer resultado = arbol.obtenerTamañoEnelArbol();
    // Entonces
    assertEquals((Integer) 10, resultado);
    }

}
