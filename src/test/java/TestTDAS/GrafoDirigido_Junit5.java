package TestTDAS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;

import TDAS.Grafos.*;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests para TGrafoDirigido - JUnit 5
 */
public class GrafoDirigido_Junit5 {

    String instanceVariable;

    @BeforeEach
    public void setUp() {
        // Inicializa recursos antes de cada test
        instanceVariable = "Valor inicial";
    }

    @AfterEach
    public void tearDown() {
        // Limpia recursos después de cada test
        instanceVariable = null;
    }

    @Test
    public void shouldAnswerWithTrueInJUnit5Test() {
        assertTrue(instanceVariable != null);
    }

    @Test
    public void testDijkstraManual() {
        // Crear vértices
        List<IVertice> vertices = Arrays.asList(
            new TVertice("A"),
            new TVertice("B"),
            new TVertice("C"),
            new TVertice("D")
        );

        // Crear aristas
        List<IArista> aristas = Arrays.asList(
            new TArista("A", "B", 2),
            new TArista("A", "C", 5),
            new TArista("B", "C", 1),
            new TArista("B", "D", 4),
            new TArista("C", "D", 1)
        );

        // Crear el grafo dirigido manualmente
        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        // Ejecutar Dijkstra desde "A"
        TVertice origen = (TVertice) grafo.getVertices().get("A");
        TGrafoDirigido.ResultadoDijkstra res = grafo.dijkstraConPredecesores(origen);

        // Obtener etiquetas ordenadas para reconstruir caminos
        List<Comparable> etiquetas = new ArrayList<>(grafo.getVertices().keySet());
        Collections.sort(etiquetas);

        // Mostrar caminos y distancias desde A
        for (int i = 0; i < etiquetas.size(); i++) {
            Comparable destino = etiquetas.get(i);
            List<Comparable> camino = grafo.reconstruirCamino(etiquetas, res.predecesores, i);
            System.out.println("Distancia A → " + destino + ": " + res.distancias[i]);
            System.out.println("Camino: " + camino);
            System.out.println();
        }

        // Aserciones
        int idxA = etiquetas.indexOf("A");
        int idxB = etiquetas.indexOf("B");
        int idxC = etiquetas.indexOf("C");
        int idxD = etiquetas.indexOf("D");

        assertEquals(0.0, res.distancias[idxA], 0.001); // A → A
        assertEquals(2.0, res.distancias[idxB], 0.001); // A → B
        assertEquals(3.0, res.distancias[idxC], 0.001); // A → B → C
        assertEquals(4.0, res.distancias[idxD], 0.001); // A → B → C → D
    }
    /* Paso el test, se probo que las distancias mínimas desde el vértice A hacia todos los demás vértices.
 y los caminos mínimos correspondientes a cada destino.

Grafo probado:
A → B (2), A → C (5), B → C (1), B → D (4), C → D (1)  */

@Test 
    public void testWarshallManual() {
        // Crear vértices
        List<IVertice> vertices = Arrays.asList(
            new TVertice("A"),
            new TVertice("B"),
            new TVertice("C"),
            new TVertice("D")
        );

        // Crear aristas
        List<IArista> aristas = Arrays.asList(
            new TArista("A", "B", 2),
            new TArista("A", "C", 5),
            new TArista("B", "C", 1),
            new TArista("B", "D", 4),
            new TArista("C", "D", 1)
        );

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        // Ejecutar Warshall
        boolean[][] matriz = grafo.warshall();

        List<Comparable> etiquetas = new ArrayList<>(grafo.getVertices().keySet());
        Collections.sort(etiquetas);

        // Mostrar la matriz de alcanzabilidad
        System.out.println("Matriz de Warshall:");
        for (int i = 0; i < etiquetas.size(); i++) {
            System.out.print(etiquetas.get(i) + ": ");
            for (int j = 0; j < etiquetas.size(); j++) {
                System.out.print(matriz[i][j] ? "1 " : "0 ");
            }
            System.out.println();
        }

        // Aserciones esperadas (alcanzabilidad desde A)
        int idxA = etiquetas.indexOf("A");
        int idxB = etiquetas.indexOf("B");
        int idxC = etiquetas.indexOf("C");
        int idxD = etiquetas.indexOf("D");

        assertTrue(matriz[idxA][idxA]); // A → A
        assertTrue(matriz[idxA][idxB]); // A → B
        assertTrue(matriz[idxA][idxC]); // A → C (por B)
        assertTrue(matriz[idxA][idxD]); // A → D (por B o C)
        assertFalse(matriz[idxD][idxA]); // D → A (no hay camino)
    }

    @Test
    public void testGrafoSinCiclo() {
        List<IVertice> vertices = new ArrayList<>();
        TVertice v1 = new TVertice("A");
        TVertice v2 = new TVertice("B");
        TVertice v3 = new TVertice("C");
        TVertice v4 = new TVertice("D");

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);


        // Aristas sin ciclo: A->B, B->C, C->D
        List<IArista> aristas = new ArrayList<>();
        TArista a = new TArista("A", "B", 2);
        TArista b = new TArista("B", "C", 5);
        TArista c = new TArista("C", "D", 1);
        aristas.add(a);
        aristas.add(b);
        aristas.add(c);

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        assertFalse(grafo.tieneCiclo());
    }

    @Test
    public void testGrafoConCiclo() {
        List<IVertice> vertices = new ArrayList<>();
        TVertice v1 = new TVertice("A");
        TVertice v2 = new TVertice("B");
        TVertice v3 = new TVertice("C");
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);

        // Aristas con ciclo: A->B, B->C, C->A
        List<IArista> aristas = new ArrayList<>();
        TArista a = new TArista("A", "B", 1);
        TArista b = new TArista("B", "C", 1);
        TArista c = new TArista("C", "A", 1);
        aristas.add(a);
        aristas.add(b);
        aristas.add(c);

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        assertTrue(grafo.tieneCiclo());
    }

    @Test
    public void testCaminoCritico() {
        List<IVertice> vertices = new ArrayList<>();
        TVertice v1 = new TVertice("A");
        TVertice v2 = new TVertice("B");
        TVertice v3 = new TVertice("C");
        TVertice v4 = new TVertice("D");
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);

        List<IArista> aristas = new ArrayList<>();
        aristas.add(new TArista("A", "B", 2.0));
        aristas.add(new TArista("B", "D", 2.0));
        aristas.add(new TArista("A", "C", 1.0));
        aristas.add(new TArista("C", "D", 1.0));

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);
        grafo.calcularCaminoCritico("A", "D");

        // El camino crítico debería ser A -> B -> D con un costo de 4
        // La otra secuencia A -> C -> D tiene costo 2, por lo tanto la holgura debe ser de 2
    }

    @Test
    public void testOrdenParcial() {
        // Crear vértices
        List<IVertice> vertices = new ArrayList<>();
        TVertice vA = new TVertice("A");
        TVertice vB = new TVertice("B");
        TVertice vC = new TVertice("C");
        TVertice vD = new TVertice("D");
        vertices.add(vA);
        vertices.add(vB);
        vertices.add(vC);
        vertices.add(vD);

        // Crear aristas que no formen ciclos (orden esperado: A, B, C, D o similar topológico)
        List<IArista> aristas = new ArrayList<>();
        aristas.add(new TArista("A", "B", 1));
        aristas.add(new TArista("B", "C", 1));
        aristas.add(new TArista("A", "C", 1));
        aristas.add(new TArista("C", "D", 1));

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        LinkedList<TVertice> orden = grafo.ordenParcial();

        // Se debe verificar que el orden respeta la relación de precedencia:
        // A tiene que estar antes que B y C, B antes que C, y C antes que D

        // Buscar índices de cada vértice en la lista resultado
        int indiceA = -1, indiceB = -1, indiceC = -1, indiceD = -1;
        for (int i = 0; i < orden.size(); i++) {
            Comparable etiqueta = orden.get(i).getEtiqueta();
            if (etiqueta.equals("A")) indiceA = i;
            else if (etiqueta.equals("B")) indiceB = i;
            else if (etiqueta.equals("C")) indiceC = i;
            else if (etiqueta.equals("D")) indiceD = i;
        }

        // Comprobaciones de orden topológico, que estos vertices existan en el grafo
        assertTrue(indiceA != -1, "Debe contener vértice A");
        assertTrue(indiceB != -1, "Debe contener vértice B");
        assertTrue(indiceC != -1, "Debe contener vértice C");
        assertTrue(indiceD != -1, "Debe contener vértice D");

        // Comprobamos que los destinos sean previos del origen
        assertTrue(indiceA < indiceB, "A debe estar antes que B");
        assertTrue(indiceA < indiceC, "A debe estar antes que C");
        assertTrue(indiceB < indiceC, "B debe estar antes que C");
        assertTrue(indiceC < indiceD, "C debe estar antes que D");
    }
    @Test
    public void testExcentricidadConCiclo() {
        List<IVertice> vertices = new ArrayList<>();
        vertices.add(new TVertice("A"));
        vertices.add(new TVertice("B"));
        vertices.add(new TVertice("C"));

        List<IArista> aristas = new ArrayList<>();
        aristas.add(new TArista("A", "B", 1));
        aristas.add(new TArista("B", "C", 1));
        aristas.add(new TArista("C", "A", 1));

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        assertEquals(2.0, (Double) grafo.obtenerExcentricidad("A"), 0.001);
        assertEquals(2.0, (Double) grafo.obtenerExcentricidad("B"), 0.001);
        assertEquals(2.0, (Double) grafo.obtenerExcentricidad("C"), 0.001);
    }



    @Test
    public void testCentroDelGrafo() {
        // Crear vértices
        List<IVertice> vertices = new ArrayList<>();
        TVertice vA = new TVertice("A");
        TVertice vB = new TVertice("B");
        TVertice vC = new TVertice("C");
        vertices.add(vA);
        vertices.add(vB);
        vertices.add(vC);

        // Crear aristas
        List<IArista> aristas = new ArrayList<>();
        aristas.add(new TArista("A", "B", 2));
        aristas.add(new TArista("B", "C", 3));

        // Crear grafo
        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        // El vértice con menor excentricidad es "A" (2.0), por lo tanto es el centro
        assertEquals("A", grafo.centroDelGrafo());
    }

    @Test
    public void testFLoydConRecupreacion_CaminoMinimo(){
        // Crear vértices
        List<IVertice> vertices = new ArrayList<>();
        TVertice vA = new TVertice("A");
        TVertice vB = new TVertice("B");
        TVertice vC = new TVertice("C");
        TVertice vD = new TVertice("D");
        vertices.add(vA);
        vertices.add(vB);
        vertices.add(vC);
        vertices.add(vD);

        // Crear aristas
        List<IArista> aristas = new ArrayList<>();
        aristas.add(new TArista("A", "B", 1));
        aristas.add(new TArista("A", "C", 5));
        aristas.add(new TArista("B", "C", 1));
        aristas.add(new TArista("A", "D", 10));
        aristas.add(new TArista("C", "D", 1));

        // Crear grafo
        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        //Ejecuto floyd con recupracion de camino
        TGrafoDirigido.ResultadoCamino resultado = grafo.floydConRecuperacion("A", "D");

        //Verificamos el camino correcto
        List<Comparable> resultadoEsperado = Arrays.asList("A", "B", "C", "D");
        assertEquals(resultadoEsperado, resultado.camino);

        //Verificamos el costo del mismo si es correcto
        assertEquals(3.0, resultado.costo);




    }






    /*Se probó que el método warshall() construye correctamente la matriz

Grafo probado:
A → B (2), A → C (5), B → C (1), B → D (4), C → D (1)

Resultados verificados desde A:
- Se puede alcanzar a A, B, C y D.
- No se puede alcanzar A desde D (ni desde C ni B).

La matriz de Warshall fue generada y evaluada correctamente. 
El test devuelve: Matriz de Warshall:
A: 1 1 1 1 
B: 0 1 1 1 
C: 0 0 1 1 
D: 0 0 0 1 
*/
}

