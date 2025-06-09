package TestTDAS;

import TDAS.Lista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaTest {

    private Lista<String> lista;

    @BeforeEach
    void setUp() {
        lista = new Lista<>();
    }

    @Test
    void testInsertarYBuscar() {
        lista.insertar("Uno", 1);
        lista.insertar("Dos", 2);
        lista.insertar("Tres", 3);

        assertEquals("Uno", lista.buscar(1));
        assertEquals("Dos", lista.buscar(2));
        assertEquals("Tres", lista.buscar(3));
        assertNull(lista.buscar(99));
    }

    @Test
    void testEliminar() {
        lista.insertar("A", "a");
        lista.insertar("B", "b");
        lista.insertar("C", "c");

        assertTrue(lista.eliminar("b"));   // eliminar del medio
        assertFalse(lista.eliminar("x"));  // clave inexistente
        assertTrue(lista.eliminar("a"));   // eliminar el primero
        assertTrue(lista.eliminar("c"));   // eliminar el último
        assertFalse(lista.eliminar("c"));  // ya fue eliminado
    }

    @Test
    void testesVaciaYCantElementos() {
        assertTrue(lista.esVacia());
        assertEquals(0, lista.cantElementos());

        lista.insertar("Uno", 1);
        assertFalse(lista.esVacia());
        assertEquals(1, lista.cantElementos());

        lista.insertar("Dos", 2);
        assertEquals(2, lista.cantElementos());

        lista.eliminar(1);
        assertEquals(1, lista.cantElementos());

        lista.eliminar(2);
        assertTrue(lista.esVacia());
    }

    @Test
    void testImprimirPorDefecto() {
        lista.insertar("X", 1);
        lista.insertar("Y", 2);
        lista.insertar("Z", 3);

        assertEquals("X,Y,Z", lista.imprimir());
    }

    @Test
    void testImprimirConSeparador() {
        lista.insertar("A", 1);
        lista.insertar("B", 2);
        lista.insertar("C", 3);

        assertEquals("A - B - C", lista.imprimir(" - "));
        assertEquals("A|B|C", lista.imprimir("|"));
    }

    @Test
    void testBuscarEnListaVacia() {
        assertNull(lista.buscar(1));
    }

    @Test
    void testEliminarEnListaVacia() {
        assertFalse(lista.eliminar(1));
    }

    @Test
    void testImprimirListaVacia() {
        assertEquals("", lista.imprimir());
        assertEquals("", lista.imprimir(" * "));
    }
}
