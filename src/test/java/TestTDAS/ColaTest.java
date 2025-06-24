package TestTDAS;

import TDAS.UT3.Cola;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColaTest {

    private Cola<Integer> cola;

    @BeforeEach
    void setUp() {
        cola = new Cola<>();
    }

    @Test
    void testPoneEnColaYFrente() {
        cola.poneEnCola(5);
        assertEquals(5, cola.frente());
    }

    @Test
    void testQuitaDeCola() {
        cola.poneEnCola(1);
        cola.poneEnCola(2);
        assertEquals(1, cola.quitaDeCola());
        assertEquals(2, cola.frente());
    }

    @Test
    void testanular() {
        cola.poneEnCola(10);
        cola.poneEnCola(20);
        cola.anular();
        assertTrue(cola.vacia());
        assertNull(cola.frente());
    }

    @Test
    void testVacia() {
        assertTrue(cola.vacia());
        cola.poneEnCola(7);
        assertFalse(cola.vacia());
    }

    @Test
    void testFrenteEnColaVacia() {
        assertNull(cola.frente());
    }

    @Test
    void testQuitaDeColaEnVacia() {
        assertNull(cola.quitaDeCola());
    }
}
