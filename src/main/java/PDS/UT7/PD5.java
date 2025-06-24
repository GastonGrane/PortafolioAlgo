package PDS.UT7;

import TDAS.Grafos.TArista;
import TDAS.Grafos.TGrafoDirigido;
import TDAS.Grafos.TVertice;
import TDAS.Grafos.UtilGrafos;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PD5 {
    public static void main(String[] args) {

        Collection<TVertice> vertices = new LinkedList<>();
        Collection<TArista> aristas = new LinkedList<>();

        vertices.add(new TVertice("Artigas"));
        vertices.add(new TVertice("Canelones"));
        vertices.add(new TVertice("Montevideo"));
        vertices.add(new TVertice("Rocha"));


        aristas.add(new TArista("Artigas", "Rocha", 400.0));
        aristas.add(new TArista("Montevideo", "Artigas", 700.0));
        aristas.add(new TArista("Montevideo", "Canelones", 30.0));
        aristas.add(new TArista("Rocha", "Montevideo", 270.0));

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        System.out.println("Tiene ciclos: " + grafo.tieneCiclo());

        List<TVertice> orden = grafo.ordenacionTopologica();
        if (orden == null) {
            System.out.println("No se generó el camino topologico debido a presencia de ciclos en el grafo.");
        }

        Collection<TVertice> vertices2 = new LinkedList<>();
        Collection<TArista> aristas2 = new LinkedList<>();

        vertices2.add(new TVertice("Artigas"));
        vertices2.add(new TVertice("Canelones"));
        vertices2.add(new TVertice("Montevideo"));
        vertices2.add(new TVertice("Rocha"));
        vertices2.add(new TVertice("Florida"));


        aristas2.add(new TArista("Artigas", "Rocha", 400.0));
        aristas2.add(new TArista("Montevideo", "Canelones", 30.0));
        aristas2.add(new TArista("Rocha", "Montevideo", 270.0));
        aristas2.add(new TArista("Rocha", "Canelones", 340.0));
        aristas2.add(new TArista("Canelones", "Florida", 340.0));

        TGrafoDirigido grafo2 = new TGrafoDirigido(vertices2, aristas2);

        System.out.println();
        System.out.println("Tiene ciclos: " + grafo2.tieneCiclo());
        System.out.println("El camino topologico es: " + grafo2.ordenacionTopologica());

    }
}
