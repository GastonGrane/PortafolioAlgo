package TestTDAS;

import PDS.UT9.GeneradorDatosGenericos;
import PDS.UT9.TClasificador;
import org.junit.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TClasificadorTestt {
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

    @Test
    public void testOrdenarPorHeapSortVacio() {
        int[] array = new int[0];
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_HEAPSORT);
        assertEquals(0, result.length);
    }

    @Test
    public void testOrdenarPorHeapSortNull() {
        assertNull(clasificador.clasificar(null, TClasificador.METODO_CLASIFICACION_HEAPSORT));
    }

    @Test
    public void testOrdenarPorHeapSortSingleElement() {
        int[] array = {42};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_HEAPSORT);
        assertArrayEquals(new int[]{42}, result);
    }

    @Test
    public void testOrdenarPorHeapSortOrdenado() {
        int[] array = {1, 2, 3, 4, 5};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_HEAPSORT);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testOrdenarPorHeapSortInverso() {
        int[] array = {5, 4, 3, 2, 1};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_HEAPSORT);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testOrdenarPorHeapSortDuplicados() {
        int[] array = {4, 1, 3, 1, 5, 9, 2, 6, 5, 3};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 9};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_HEAPSORT);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testOrdenarPorHeapSortNegativosYPositivos() {
        int[] array = {-3, 1, -4, 1, -5, 9, -2, 6, -5, 3};
        int[] expected = {-5, -5, -4, -3, -2, 1, 1, 3, 6, 9};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_HEAPSORT);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testOrdenarPorHeapSortGrandeAleatorio() {
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        int[] array = gdg.generarDatosAleatorios(1000);
        int[] result = clasificador.clasificar(array.clone(), TClasificador.METODO_CLASIFICACION_HEAPSORT);
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

    //Quicksort

    @Test
    public void testQuickSortArrayAleatorio() {
        TClasificador clasificador = new TClasificador();
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        int[] datosAleatorios = gdg.generarDatosAleatorios(1000);
        int[] resultado = clasificador.clasificar(datosAleatorios, TClasificador.METODO_CLASIFICACION_QUICKSORT);
        assertTrue(estaOrdenado(resultado));
    }

    @Test
    public void testQuickSortArrayUnicoElemento() {
        TClasificador clasificador = new TClasificador();
        int[] datosUnico = {5};
        int[] resultado = clasificador.clasificar(datosUnico, TClasificador.METODO_CLASIFICACION_QUICKSORT);

        assertTrue(estaOrdenado(resultado));
        assertEquals(1, resultado.length);
        assertEquals(5, resultado[0]);
    }

    @Test
    public void testQuickSortArrayYaOrdenado() {
        TClasificador clasificador = new TClasificador();
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        int[] datosOrdenados = gdg.generarDatosAscendentes(100);
        int[] resultado = clasificador.clasificar(datosOrdenados, TClasificador.METODO_CLASIFICACION_QUICKSORT);

        assertTrue(estaOrdenado(resultado));
        assertArrayEquals(datosOrdenados, resultado);
    }

    @Test
    public void testQuickSortArrayInvertido() {
        TClasificador clasificador = new TClasificador();
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        int[] datosInvertidos = gdg.generarDatosDescendentes(100);
        int[] resultado = clasificador.clasificar(datosInvertidos, TClasificador.METODO_CLASIFICACION_QUICKSORT);
        assertTrue(estaOrdenado(resultado));
    }

    @Test
    public void testQuickSortArrayConElementosRepetidos() {
        TClasificador clasificador = new TClasificador();
        int[] datosRepetidos = {5, 2, 8, 2, 2, 5, 8, 3, 3, 4};
        int[] resultado = clasificador.clasificar(datosRepetidos, TClasificador.METODO_CLASIFICACION_QUICKSORT);
        assertTrue(estaOrdenado(resultado));
        assertEquals(datosRepetidos.length, resultado.length);
    }

    @Test
    public void testQuickSortArrayTodosIguales() {
        TClasificador clasificador = new TClasificador();
        int[] datosIguales = new int[100];
        Arrays.fill(datosIguales, 5);  // Llena el array con el valor 5
        int[] resultado = clasificador.clasificar(datosIguales, TClasificador.METODO_CLASIFICACION_QUICKSORT);
        assertTrue(estaOrdenado(resultado));
        assertArrayEquals(datosIguales, resultado);
    }

    //Test de ordenarPorShell

    @Test
    public void testOrdenarPorShellNull() {
        assertNull(clasificador.clasificar(null, TClasificador.METODO_CLASIFICACION_SHELL));
    }

    @Test
    public void testOrdenarPorShellEmpty() {
        int[] array = new int[0];
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_SHELL);
        assertEquals(0, result.length);
    }

    @Test
    public void testOrdenarPorShellSingleElement() {
        int[] array = {1};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_SHELL);
        assertArrayEquals(new int[]{1}, result);
    }

    @Test
    public void testOrdenarPorShellOrdenado() {
        int[] array = {1, 2, 3, 4, 5};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_SHELL);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testOrdenarPorShellInverso() {
        int[] array = {5, 4, 3, 2, 1};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_SHELL);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, result);
    }

    @Test
    public void testOrdenarPorShellDuplicados() {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 9};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_SHELL);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testOrdenarPorShellNegativosYPositivos() {
        int[] array = {-3, 1, -4, 1, -5, 9, -2, 6, -5, 3};
        int[] expected = {-5, -5, -4, -3, -2, 1, 1, 3, 6, 9};
        int[] result = clasificador.clasificar(array, TClasificador.METODO_CLASIFICACION_SHELL);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testOrdenarPorShellGrandeAleatorio() {
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        int[] array = gdg.generarDatosAleatorios(1000);
        int[] result = clasificador.clasificar(array.clone(), TClasificador.METODO_CLASIFICACION_SHELL);
        assertTrue(estaOrdenado(result));
    }
}
