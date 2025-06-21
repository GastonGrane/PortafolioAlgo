package PDS.UT3.PD6;

import java.util.LinkedList;

public class CentroSucursales {
    LinkedList<Sucursal> listaSucursales = new LinkedList<>();

    public void cargarDesdeArchivo(String[] ciudades) {
        for (String ciudad : ciudades) {
            listaSucursales.add(new Sucursal(ciudad));
        }
    }

    public void agregarSucursal(String ciudad) {
        listaSucursales.add(new Sucursal(ciudad));
        System.out.println("Sucursal agregada: " + ciudad);
    }

    public void quitarSucursal(String ciudad) {
        boolean removida = listaSucursales.removeIf(s -> s.getNombre().equalsIgnoreCase(ciudad));
        if (removida) {
            System.out.println("Sucursal eliminada: " + ciudad);
        } else {
            System.out.println("Sucursal no encontrada: " + ciudad);
        }
    }

    public boolean buscarSucursal(String ciudad) {
        return listaSucursales.stream().anyMatch(s -> s.getNombre().equalsIgnoreCase(ciudad));
    }

    public void listarSucursales() {
        for (Sucursal s : listaSucursales) {
            System.out.println(s.getNombre());
        }
    }

    public int cantidadSucursales() {
        return listaSucursales.size();
    }

    public boolean esVacia() {
        return listaSucursales.isEmpty();
    }

    public String ciudadSiguienteA(String ciudad) {
        for (int i = 0; i < listaSucursales.size() - 1; i++) {
            if (listaSucursales.get(i).getNombre().equalsIgnoreCase(ciudad)) {
                return listaSucursales.get(i + 1).getNombre();
            }
        }
        return null;
    }

    public void imprimirConDelimitador(String delimitador) {
        for (Sucursal s : listaSucursales) {
            System.out.print(s.getNombre() + delimitador);
        }
        System.out.println();
    }
}
