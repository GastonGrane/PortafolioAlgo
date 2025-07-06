package TestTDAS;

import PDS.UT9.GeneradorDatosGenericos;
import PDS.UT9.TClasificador;
import org.junit.Test;

import static org.junit.Assert.*;

public class TClasificadorTest {
    private TClasificador clasificador = new TClasificador();

    //Test para ordenarPorInsercion

    @Test
    public void testOrdenarPorInsercionNull() {
        assertNull(clasificador.clasificar(null, TClasificador.METODO_CLASIFICACION_INSERCION));
    }

    @Test
    public void testOrdenarPorInsercionEmpty() {
        int[] array = new int[0];
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_INSERCION);
        assertEquals(0, result.length);
    }

    @Test
    public void testOrdenarPorInsercionSingleElement() {
        int[] array = {1};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_INSERCION);
        assertArrayEquals(new int[]{1}, result);
    }

    @Test
    public void testOrdenarPorInsercionOrdenado() {
        int[] array = {1, 2, 3, 4, 5};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_INSERCION);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testOrdenarPorInsercionInverso() {
        int[] array = {5, 4, 3, 2, 1};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_INSERCION);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testOrdenarPorInsercionDuplicados() {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 9};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_INSERCION);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testOrdenarPorInsercionNegativosYPositivos() {
        int[] array = {-3, 1, -4, 1, -5, 9, -2, 6, -5, 3};
        int[] expected = {-5, -5, -4, -3, -2, 1, 1, 3, 6, 9};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_INSERCION);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testOrdenarPorInsercionGrandeAleatorio() {
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        int[] array = gdg.generarDatosAleatorios(1000);
        int[] result = clasificador.clasificar(array.clone(), TClasificador.METODO_CLASIFICACION_INSERCION);
        assertTrue(estaOrdenado(result));
    }

    //Test para ordenarPorBurbuja

    @Test
    public void testOrdenarPorBurbujaNull() {
        assertNull(clasificador.clasificar(null, TClasificador.METODO_CLASIFICACION_BURBUJA));
    }

    @Test
    public void testOrdenarPorBurbujaEmpty() {
        int[] array = new int[0];
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_BURBUJA);
        assertEquals(0, result.length);
    }

    @Test
    public void testOrdenarPorBurbujaSingleElement() {
        int[] array = {1};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_BURBUJA);
        assertArrayEquals(new int[]{1}, result);
    }

    @Test
    public void testOrdenarPorBurbujaOrdenado() {
        int[] array = {1, 2, 3, 4, 5};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_BURBUJA);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testOrdenarPorBurbujaInverso() {
        int[] array = {5, 4, 3, 2, 1};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_BURBUJA);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testOrdenarPorBurbujaDuplicados() {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 9};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_BURBUJA);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testOrdenarPorBurbujaNegativosYPositivos() {
        int[] array = {-3, 1, -4, 1, -5, 9, -2, 6, -5, 3};
        int[] expected = {-5, -5, -4, -3, -2, 1, 1, 3, 6, 9};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_BURBUJA);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testOrdenarPorBurbujaGrandeAleatorio() {
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        int[] array = gdg.generarDatosAleatorios(1000);
        int[] result = clasificador.clasificar(array.clone(), TClasificador.METODO_CLASIFICACION_BURBUJA);
        assertTrue(estaOrdenado(result));
    }

    // Metodo que uso para verificar si un array esta ordenado
    private boolean estaOrdenado(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
