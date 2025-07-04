package PDS.UT7;

import TDAS.Grafos.TArista;
import TDAS.Grafos.TGrafoDirigido;
import TDAS.Grafos.TVertice;
import TDAS.Grafos.UtilGrafos;

import java.util.Collection;
import java.util.LinkedList;

public class PD2 {
    public static void main(String[] args) {

        Collection<TVertice> vertices = new LinkedList<>();
        Collection<TArista> aristas = new LinkedList<>();

        vertices.add(new TVertice("Artigas"));
        vertices.add(new TVertice("Canelones"));
        vertices.add(new TVertice("Colonia"));
        vertices.add(new TVertice("Durazno"));
        vertices.add(new TVertice("Florida"));
        vertices.add(new TVertice("Montevideo"));
        vertices.add(new TVertice("Punta del Este"));
        vertices.add(new TVertice("Rocha"));


        aristas.add(new TArista("Artigas", "Rocha", 400.0));
        aristas.add(new TArista("Canelones", "Artigas", 500.0));
        aristas.add(new TArista("Canelones", "Colonia", 200.0));
        aristas.add(new TArista("Canelones", "Durazno", 170.0));
        aristas.add(new TArista("Canelones", "Punta del Este", 90.0));
        aristas.add(new TArista("Colonia", "Montevideo", 180.0));
        aristas.add(new TArista("Florida", "Durazno", 60.0));
        aristas.add(new TArista("Montevideo", "Artigas", 700.0));
        aristas.add(new TArista("Montevideo", "Canelones", 30.0));
        aristas.add(new TArista("Montevideo", "Punta del Este", 130.0));
        aristas.add(new TArista("Punta del Este", "Rocha", 90.0));
        aristas.add(new TArista("Rocha", "Montevideo", 270.0));
        aristas.add(new TArista("Florida", "Durazno", 60.0));

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        UtilGrafos.imprimirMatrizMejorado(UtilGrafos.obtenerMatrizCostos(grafo.getVertices()), grafo.getVertices(), "EJERCICIO 1");

        UtilGrafos.imprimirMatrizMejorado(grafo.floyd(), grafo.getVertices(), "EJERCICIO 2 CON FLOYD");

        System.out.println("El centro del grafo es: " + grafo.centroDelGrafo());
    }
}
