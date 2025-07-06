package PDS.UT9;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TClasificador {
    public static final int METODO_CLASIFICACION_INSERCION = 1;
    public static final int METODO_CLASIFICACION_SHELL = 2;
    public static final int METODO_CLASIFICACION_BURBUJA = 3;
    public static final int METODO_CLASIFICACION_QUICKSORT = 4;

    public static enum TipoOrden {
        DESCENDIENTE, ASCENDENTE, ALEATORIO
    }

    ;


    /**
     *
     * @param datosParaClasificar
     * @param metodoClasificacion
     * @return
     */
    public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
        switch (metodoClasificacion) {
            case METODO_CLASIFICACION_INSERCION:
                return ordenarPorInsercion(datosParaClasificar);
            case METODO_CLASIFICACION_SHELL:
                return ordenarPorShell(datosParaClasificar);
            case METODO_CLASIFICACION_BURBUJA:
                return ordenarPorBurbuja(datosParaClasificar);
            case METODO_CLASIFICACION_QUICKSORT:
                return ordenarPorQuickSort(datosParaClasificar);
            default:
                // System.err.println("Este codigo no deberia haberse ejecutado");
                break;
        }
        return datosParaClasificar;
    }

    protected int[] ordenarPorQuickSort(int[] datosParaClasificar) {
        quicksort(datosParaClasificar, 0, datosParaClasificar.length - 1);
        return datosParaClasificar;
    }

    private void quicksort(int[] entrada, int i, int j) {
        int izquierda = i;
        int derecha = j;

        // TODO:Implementar encuentraPivote a criterio de cada equipo
        int posicionPivote = 0; //encuentraPivote(izquierda, derecha, entrada);
        if (posicionPivote >= 0) {
            int pivote = posicionPivote;
            while (izquierda <= derecha) {
                while ((entrada[izquierda] < pivote) && (izquierda < j)) {
                    izquierda--;
                }

                while ((pivote < entrada[derecha]) && (derecha > i)) {
                    derecha++;
                }

                if (izquierda <= derecha) {
                    intercambiar(entrada, derecha, izquierda);
                    izquierda++;
                    derecha--;
                }
            }

            if (i < derecha)
                quicksort(entrada, i, izquierda);
            if (izquierda < j)
                quicksort(entrada, derecha, j);
        }
    }

    private void intercambiar(int[] vector, int pos1, int pos2) {
        int temp = vector[pos2];
        vector[pos2] = vector[pos1];
        vector[pos1] = temp;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    private int[] ordenarPorShell(int[] datosParaClasificar) {
        int j, inc;
        int[] incrementos = new int[]{3223, 358, 51, 10, 3, 1};

        for (int posIncrementoActual = 1; posIncrementoActual < incrementos.length; posIncrementoActual++) {
            inc = incrementos[posIncrementoActual];
            if (inc < (datosParaClasificar.length / 2)) {
                for (int i = inc; i < datosParaClasificar.length; i++) {
                    j = i - inc;
                    while (j >= 0) {
                        if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
                            intercambiar(datosParaClasificar, j, j + inc);
                            j = j--;
                        }
                    }
                }
            }
        }
        return datosParaClasificar;
    }

    /**
     * @param datosParaClasificar
     * @return
     */
    protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
        if (datosParaClasificar != null) {
            for (int i = 1; i < datosParaClasificar.length; i++) {
                int j = i - 1;
                while ((j >= 0) && (datosParaClasificar[j + 1] < datosParaClasificar[j])) {
                    intercambiar(datosParaClasificar, j, j + 1);
                    j--;
                }
            }
            return datosParaClasificar;
        }
        return null;
    }

    private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
        if (datosParaClasificar == null) {
            return null;
        }
        int n = datosParaClasificar.length - 1;
        for (int i = 0; i <= n; i++) {
            for (int j = n; j >= (i + 1); j--) {
                if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
                    intercambiar(datosParaClasificar, j - 1, j);
                }
            }
        }
        return datosParaClasificar;
    }

    public double medirTiempo(int tamanioDeDatos, int metodoClasificacion, TipoOrden tipoOrden) {
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        int[] data;
        switch (tipoOrden) {
            case DESCENDIENTE:
                data = gdg.generarDatosDescendentes(tamanioDeDatos);
                break;
            case ASCENDENTE:
                data = gdg.generarDatosAscendentes(tamanioDeDatos);
                break;
            default:
                data = gdg.generarDatosAleatorios(tamanioDeDatos);
                break;
        }
        long t1 = System.nanoTime();
        long total = 0;
        long cantLlamadas = 0;
        int tiempoResolucion = 1;

        while (total < tiempoResolucion) {
            cantLlamadas += 1;
            int[] datosCopia = Arrays.copyOf(data, tamanioDeDatos);
            clasificar(datosCopia, metodoClasificacion);
            long t2 = System.nanoTime();
            total = t2 - t1;
        }

        double tiempoMedioAlgoritmoBase = total / (double) cantLlamadas;

        t1 = System.nanoTime();
        total = 0;
        cantLlamadas = 0;
        while (total < tiempoResolucion) {
            cantLlamadas += 1;
            int[] datosCopia = Arrays.copyOf(data, tamanioDeDatos);
            clasificar(datosCopia, -1);
            long t2 = System.nanoTime();
            total = t2 - t1;
        }
        double tiempoMedioCascara = total / (double) cantLlamadas;
        // nanoseconds
        return tiempoMedioAlgoritmoBase - tiempoMedioCascara;
    }

    public static void main(String args[]) {
        TClasificador clasif = new TClasificador();
        // PRUEBAS DE MEDICIÓN
        System.out.println(
                clasif.medirTiempo(100, METODO_CLASIFICACION_INSERCION, TipoOrden.ALEATORIO)
        );

        // PRUEBAS BÁSICAS
//        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
//        int[] vectorAleatorio = gdg.generarDatosAleatorios(10);
//        int[] vectorAscendente = gdg.generarDatosAscendentes(10);
//        int[] vectorDescendente = gdg.generarDatosDescendentes(10);
//
//        int[] resAleatorio = clasif.clasificar(vectorAleatorio,
//                METODO_CLASIFICACION_INSERCION);
//        for (int i = 0; i < resAleatorio.length; i++) {
//            System.out.print(resAleatorio[i] + " ");
//        }
//        int[] resAscendente = clasif.clasificar(vectorAscendente,
//                METODO_CLASIFICACION_INSERCION);
//        for (int i = 0; i < resAscendente.length; i++) {
//            System.out.print(resAscendente[i] + " ");
//        }
//        int[] resDescendente = clasif.clasificar(vectorDescendente,
//                METODO_CLASIFICACION_INSERCION);
//        for (int i = 0; i < resDescendente.length; i++) {
//            System.out.print(resDescendente[i] + " ");
//        }
    }
}
