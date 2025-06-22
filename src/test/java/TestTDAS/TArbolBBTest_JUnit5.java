package TestTDAS;

import TDAS.TArbolBB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class TArbolBBTest_JUnit5 {

    private TArbolBB<Integer> arbol;

    @BeforeEach
    void init() {
        arbol = new TArbolBB<>();
    }

    @Test
    void dadoArbolVacio_cuandoInsertarEntoncesArbolNoVacio() {
        // Dado
        assertTrue(arbol.vaciar());

        // Cuando
        boolean resultado = arbol.insertar(10, 10);

        // Entonces
        assertTrue(resultado);
        assertFalse(arbol.vaciar());
    }

    @Test
    void dadoArbolConElementos_cuandoInsertarElementoExistenteEntoncesFalso() {
        // Dado
        arbol.insertar(10, 10);
        assertFalse(arbol.vaciar());

        // Cuando
        boolean resultado = arbol.insertar(10, 10);

        // Entonces
        assertFalse(resultado);
    }

    @Test
    void dadoArbolVacio_cuandoBuscarEntoncesNull() {
        // Dado
        assertTrue(arbol.vaciar());

        // Cuando
        Integer resultado = arbol.buscar(10);

        // Entonces
        assertNull(resultado);
    }

    @Test
    void dadoArbolConElementos_cuandoBuscarElementoExistenteEntoncesElemento() {
        // Dado
        arbol.insertar(10, 10);

        // Cuando
        Integer resultado = arbol.buscar(10);

        // Entonces
        assertNotNull(resultado);
        assertEquals(Integer.valueOf(10), resultado);
    }

    @Test
    void dadoArbolConElementos_cuandoBuscarElementoNoExistenteEntoncesNull() {
        // Dado
        arbol.insertar(10, 10);

        // Cuando
        Integer resultado = arbol.buscar(20);

        // Entonces
        assertNull(resultado);
    }

    @Test
    void dadoArbolVacio_cuandoEsVacioEntoncesFalso() {
        // Dado
        assertTrue(arbol.vaciar());

        // Cuando
        boolean resultado = arbol.esVacio();

        // Entonces
        assertFalse(resultado);
    }

    @Test
    void dadoArbolConElementos_cuandoEsVacioEntoncesVerdadero() {
        // Dado
        arbol.insertar(10, 10);
        assertFalse(arbol.vaciar());

        // Cuando
        boolean resultado = arbol.esVacio();

        // Entonces
        assertTrue(resultado);
        assertTrue(arbol.vaciar());
    }

    @Test
    void dadoArbolVacio_cuandoInOrdenEntoncesListaVacia() {
        // Dado
        assertTrue(arbol.vaciar());

        // Cuando
        List<Integer> resultado = arbol.inOrden();

        // Entonces
        assertNull(resultado);
    }

    @Test
    void dadoArbolConElementos_cuandoInOrdenEntoncesListaOrdenada() {
        // Dado
        arbol.insertar(20, 20);
        arbol.insertar(10, 10);
        arbol.insertar(30, 30);

        // Cuando
        List<Integer> resultado = arbol.inOrden();

        // Entonces
        assertNotNull(resultado);
        assertArrayEquals(new Integer[]{10, 20, 30}, resultado.toArray(new Integer[0]));
    }

    @Test
    void dadoArbolConElementos_cuandoPreOrdenEntoncesListaPreOrden() {
        // Dado
        arbol.insertar(20, 20);
        arbol.insertar(10, 10);
        arbol.insertar(30, 30);

        // Cuando
        List<Integer> resultado = arbol.preOrden();

        // Entonces
        assertNotNull(resultado);
        assertArrayEquals(new Integer[]{20, 10, 30}, resultado.toArray(new Integer[0]));
    }

    @Test
    void dadoArbolVacio_cuandoPreOrdenEntoncesListaVacia() {
        // Dado
        assertTrue(arbol.vaciar());

        // Cuando
        List<Integer> resultado = arbol.preOrden();

        // Entonces
        assertNull(resultado);
    }

    @Test
    void dadoArbolConElementos_cuandoPostOrdenEntoncesListaPostOrden() {
        // Dado
        arbol.insertar(20, 20);
        arbol.insertar(10, 10);
        arbol.insertar(30, 30);

        // Cuando
        List<Integer> resultado = arbol.postOrden();

        // Entonces
        assertNotNull(resultado);
        assertArrayEquals(new Integer[]{10, 30, 20}, resultado.toArray(new Integer[0]));
    }

        @Test
    void dadoArbolVacio_cuandoPostOrdenEntoncesListaVacia() {
        // Dado
        assertTrue(arbol.vaciar());

        // Cuando
        List<Integer> resultado = arbol.postOrden();

        // Entonces
        assertNull(resultado);
    }

    @Test
    void dadoArbolVacio_cuandoEliminarEntoncesNoCambios() {
        // Dado
        assertTrue(arbol.vaciar());

        // Cuando
        arbol.eliminar(10);

        // Entonces
        assertTrue(arbol.vaciar());
    }

    @Test
    void dadoArbolConElementos_cuandoEliminarElementoNoExistenteEntoncesArbolInalterado() {
        // Dado
        arbol.insertar(10, 10);
        assertFalse(arbol.vaciar());

        // Cuando
        arbol.eliminar(20);

        // Entonces
        assertFalse(arbol.vaciar());
        assertNotNull(arbol.buscar(10));
    }

    @Test
    void dadoArbolConElementos_cuandoEliminarElementoExistenteEntoncesElementoEliminado() {
        // Dado
        arbol.insertar(10, 10);
        arbol.insertar(20, 20);
        arbol.insertar(30, 30);
        assertFalse(arbol.vaciar());

        // Cuando
        arbol.eliminar(20);

        // Entonces
        assertNull(arbol.buscar(20));
        assertNotNull(arbol.buscar(10));
        assertNotNull(arbol.buscar(30));
    }
}
