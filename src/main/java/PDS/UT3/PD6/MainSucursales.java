package PDS.UT3.PD6;

import Utils.ManejadorArchivosGenerico;

public class MainSucursales {
    public static void main(String[] args) {
        System.out.println("----- 1) Leer suc1.txt -----");
        String[] ciudades1 = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\PDS\\UT3\\resources\\suc1.txt");
        CentroSucursales centro = new CentroSucursales();
        centro.cargarDesdeArchivo(ciudades1);

        System.out.println("Cantidad de sucursales: " + centro.cantidadSucursales());

        centro.agregarSucursal("Durazno");
        System.out.println("Cantidad de sucursales: " + centro.cantidadSucursales());

        System.out.println("Durazno se encuentra dentro de las sucursales: " + centro.buscarSucursal("Durazno"));
        System.out.println("El centro esta vacio: " + centro.esVacia());

        System.out.println("\n----- 2) Eliminar 'Chicago' y buscar ciudad siguiente a 'Hong Kong' -----");
        centro.quitarSucursal("Chicago");
        centro.listarSucursales();

        String siguiente = centro.ciudadSiguienteA("Hong Kong");
        if (siguiente != null) {
            System.out.println("La ciudad que sigue a 'Hong Kong' es: " + siguiente);
        } else {
            System.out.println("No se encontró la ciudad 'Hong Kong' o no tiene ciudad siguiente.");
        }

        System.out.println("\n----- 3): Leer suc2.txt, eliminar 'Shenzhen' y 'Tokio' -----");
        String[] ciudades2 = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\PDS\\UT3\\resources\\suc2.txt");
        CentroSucursales directorio2 = new CentroSucursales();
        directorio2.cargarDesdeArchivo(ciudades2);

        directorio2.quitarSucursal("Shenzhen");
        directorio2.quitarSucursal("Tokio");

        directorio2.listarSucursales();
        System.out.println("Cantidad de sucursales restantes en suc2.txt: " + directorio2.cantidadSucursales());

        System.out.println("\n----- 4) Leer suc3.txt e imprimir con separador ';_' -----");
        String[] ciudades3 = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\PDS\\UT3\\resources\\suc3.txt");
        CentroSucursales directorio3 = new CentroSucursales();
        directorio3.cargarDesdeArchivo(ciudades3);

        directorio3.imprimirConDelimitador(";_");
    }
}