package TDAS.Grafos;

import static java.lang.Math.min;
import static uy.edu.ucu.aed.tdas.UtilGrafos.obtenerMatrizCostos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, IVertice> vertices; // vertices del grafo.-

    public TGrafoDirigido(Collection<IVertice> vertices, Collection<IArista> aristas) {
        this.vertices = new HashMap<>();
        for (IVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (IArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     */
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            IVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    
    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        IVertice vertOrigen = buscarVertice(etiquetaOrigen);
        IVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private IVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(IArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            IVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            IVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            IVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(IVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, IVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    /**
     * @return the vertices
     */
    public Map<Comparable, IVertice> getVertices() {
        return vertices;
    }

    public Comparable centroDelGrafo() {
        double menorExcentricidad = Double.POSITIVE_INFINITY;
        Comparable centro = null;

        for (Comparable etiqueta : vertices.keySet()) {
            double excentricidad = (Double) obtenerExcentricidad(etiqueta);
            if (excentricidad < menorExcentricidad) {
                menorExcentricidad = excentricidad;
                centro = etiqueta;
            }
        }
        return centro;
    }

    public Double[][] floyd() {
        int n = vertices.size();
        Double[][] dist = obtenerMatrizCostos(vertices);

        for (int k=0; k < n; k++){  //Nodo Intermedio
            for (int i =0; i < n; i++){ //Nodo Origen
                for (int j =0; j < n; j++){ //Nodo Destino
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]); //Si es menos lo guardo
                }
            }
        }

        return dist; //Devuelve la matriz con las distancias minimas
    }

    //Clase auxiliar para devolver el camino mas corto y los costos usando FLoyd
    public class ResultadoCamino{
        public List<Comparable> camino;
        public double costo;

        public ResultadoCamino(List<Comparable> camino, double costo){
            this.camino = camino;
            this.costo = costo;
        }
    }

    public ResultadoCamino floydConRecuperacion(Comparable origen, Comparable destino){
        int n = vertices.size();
        List<Comparable> etiquetas = new ArrayList<>(vertices.keySet());
        Collections.sort(etiquetas);

        Double[][] dist = obtenerMatrizCostos(vertices);
        Integer[][] camino = new Integer[n][n];

        //Inicializar matriz camino
        for (int i = 0; i < n; i++){
            for  (int j = 0; j < n; j++){
                if (i == j || dist[i][j] == Double.POSITIVE_INFINITY){
                    camino[i][j] = null;
                } else {
                    camino[i][j] = i;
                }
            }
        }

        // Aplicar Floyd
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Double.POSITIVE_INFINITY && dist[k][j] != Double.POSITIVE_INFINITY) {
                        double nuevoCosto = dist[i][k] + dist[k][j];
                        if (nuevoCosto < dist[i][j]) {
                            dist[i][j] = nuevoCosto;
                            camino[i][j] = camino[k][j];
                        }
                    }
                }
            }
        }

        //Obtenemos sus indices
        int i = etiquetas.indexOf(origen);
        int j = etiquetas.indexOf(destino);

        if (i == -1 || j == -1 || camino[i][j] == null){
            return new ResultadoCamino(null, Double.POSITIVE_INFINITY); // no hay vertices o camino valido
        }

        //Reconstruimos el camino
        LinkedList<Comparable> resultadoCamino = new LinkedList<>();
        int actual = j;
        while (actual != i){
            resultadoCamino.addFirst(etiquetas.get(actual));
            actual = camino[i][actual];
        }
        resultadoCamino.addFirst(etiquetas.get(i));

        return new ResultadoCamino(resultadoCamino, dist[i][j]);
    }

    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Double [][] matrizCostos = floyd();

        List<Comparable> etiquetas = new ArrayList<>(vertices.keySet()); // Obtiene todas las etiquetas de los vértices del grafo y guarda a estas en una lista.
        int indice = etiquetas.indexOf(etiquetaVertice); //Busca el índice de la etiqueta recibida dentro de la lista de etiquetas

        //Aquí contiene el mayor costo, es el menor valor positivo, cualquier número mayor lo sobreescribirá
        double costoMaximo = Double.NEGATIVE_INFINITY;

        //Recorre todos los vértices a los que se puede llegar desde etiquetaVertice
        for (int j = 0; j < matrizCostos.length; j++) {

            //Se fija si existe un camino desde indice hasta Double.POSITIVE_INFINITY representa infinito, quiere decir que no hay camino entre esos dos vértices
            if (matrizCostos[indice][j] != Double.POSITIVE_INFINITY) {
                costoMaximo = Math.max(costoMaximo, matrizCostos[indice][j] );
                //Si hay un camino, costoMaximo se actualiza si ese camino es mas largo que los anteriores
            }
        }
        return costoMaximo == Double.NEGATIVE_INFINITY ? Double.POSITIVE_INFINITY : costoMaximo;
        // Si el vértice no puede alcanzar a ningún otro, su excentricidad es infinita.
        // Si puede alcanzar a otros, se devuelve la distancia más larga (excentricidad real).

    }

    public boolean[][] warshall() {
        Double[][] A = obtenerMatrizCostos(this.vertices); // Obtenemos la matriz de costos
        boolean[][] W = new boolean[vertices.size()][vertices.size()]; // Matriz de adyacencia booleana

        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (A[i][j] != Double.MAX_VALUE) { // Si hay una arista entre i y j 
                    W[i][j] = true;
                } else {
                    W[i][j] = false;
                }
            }
        }
        // aca aplica warshall para actualizar los caminos indirectos
        for (int k = 0; k < vertices.size(); k++) { // Nodo intermedio
            for (int i = 0; i < vertices.size(); i++) { // Nodo origen 
                for (int j = 0; j < vertices.size(); j++) { // Nodo destino
                    if (!W[i][j]) { // si no hay camino directo verifica si hay uni por k
                        W[i][j] = (W[i][k] && W[k][j]);
                    }
                }
            }
        }
        return W;
    }

    // Clase auxiliar para encapsular resultado de Dijkstra la idea q con esto salga el camino mas corto y los predecesores
    public static class ResultadoDijkstra {
        public Double[] distancias;       // Distancia mínima desde el origen
        public int[] predecesores;        // Índice del vértice anterior en el camino

        public ResultadoDijkstra(Double[] distancias, int[] predecesores) {
            this.distancias = distancias;
            this.predecesores = predecesores;
        }
    }

    // Dijkstra que devuelve distancias mínimas y predecesores )
    public ResultadoDijkstra dijkstraConPredecesores(IVertice verticeOrigen) {
        Double[][] A = obtenerMatrizCostos(this.vertices); // Matriz de costos entre vértices
        int n = A.length;

        Double[] D = new Double[n];         // Arreglo de distancias mínimas
        Boolean[] visitado = new Boolean[n]; // Marcamos qué nodos ya se procesaron
        int[] predecesores = new int[n];     // Guardamos de dónde viene cada nodo

        List<Comparable> etiquetas = new ArrayList<>(vertices.keySet()); // Lista de etiquetas
        Collections.sort(etiquetas); // Asegura orden consistente con la matriz

        int iOrigen = etiquetas.indexOf(verticeOrigen.getEtiqueta()); // Posición del nodo origen
        if (iOrigen == -1) {
            throw new IllegalArgumentException("Vértice no encontrado: " + verticeOrigen.getEtiqueta());
        }

        for (int j = 0; j < n; j++) {
            D[j] = A[iOrigen][j];      // Inicializamos con las distancias desde el origen
            visitado[j] = false;       // Nadie ha sido visitado aún
            predecesores[j] = iOrigen; // Todos vienen del origen por ahora
        }

        D[iOrigen] = 0.0;           // Distancia al mismo origen es 0
        visitado[iOrigen] = true;  // Ya procesamos el origen
        predecesores[iOrigen] = -1; // El origen no tiene predecesor

        for (int k = 1; k < n; k++) { // Repetimos para el resto de los nodos
            double minDist = Double.MAX_VALUE;
            int indexMin = -1;

            // Buscamos el nodo no visitado con menor distancia
            for (int j = 0; j < n; j++) {
                if (!visitado[j] && D[j] < minDist) {
                    minDist = D[j];
                    indexMin = j;
                }
            }

            if (indexMin == -1) break; // Si no queda ninguno alcanzable, terminamos

            visitado[indexMin] = true; // Procesamos ese nodo

            // Intentamos  actualizar distancias a los vecinos para ver si podemos mejorar el camino
            for (int j = 0; j < n; j++) {
                if (!visitado[j] && A[indexMin][j] < Double.MAX_VALUE) {
                    double nuevaDist = D[indexMin] + A[indexMin][j];
                    if (nuevaDist < D[j]) {
                        D[j] = nuevaDist;        // Actualizamos la mejor distancia
                        predecesores[j] = indexMin; // Actualizamos de dónde viene
                    }
                }
            }
        }

        return new ResultadoDijkstra(D, predecesores);
    }

    // Método para reconstruir el camino desde el origen a un destino 
    public List<Comparable> reconstruirCamino(List<Comparable> etiquetas, int[] predecesores, int destino) {
        LinkedList<Comparable> camino = new LinkedList<>();
        int actual = destino;

        while (actual != -1) {
            camino.addFirst(etiquetas.get(actual)); // Agregamos al principio
            actual = predecesores[actual];          // Vamos hacia el anterior
        }

        return camino;
    }


    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            IVertice vertice = vertices.remove(nombreVertice);
            if (vertice != null) {
                for (IVertice v : vertices.values()) {
                    v.eliminarAdyacencia(nombreVertice);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void desvisitarVertices() {
        for (IVertice vertice : vertices.values()) {
            vertice.setVisitado(false);
        }

    }

    public Collection<TVertice> bpf(){
        Collection<TVertice> visitados = new LinkedList<>();

        //Nos aseguramos que todos los vertices no esten visitado
        for (IVertice v : vertices.values()){
            v.setVisitado(false);
        }

        //Ahora si recorremos todos los vertices una ves desmarcados todos los nodos
        for (IVertice vertice : vertices.values()){
            TVertice unVertice = (TVertice) vertice;
            if (!unVertice.getVisitado()){
                unVertice.bpf(visitados);
            }
        }

        return visitados;
    }

    public Collection<TVertice> bpf (TVertice verticeOrigen){
        Collection<TVertice> visitados = new LinkedList<>();

        //Nos aseguramos que todos los vertices no esten visitado
        for (IVertice v : vertices.values()){
            v.setVisitado(false);
        }

        verticeOrigen.bpf(visitados);

        return visitados;
    }

    public Collection<TVertice> bpf (Comparable etiquetaVertice) {
        Collection<TVertice> visitados = new LinkedList<>();

        /* Accedo directamenete por la clave al vertice que estoy buscando, me evito recorrer todo.
        esto gracias al Map<Collection, IVertices> vertices */
        TVertice vertice = (TVertice) vertices.get(etiquetaVertice);

        if (vertice == null) {
            System.out.println("No existe ningún vértice con la etiqueta dada.");
            return new LinkedList<>();
        }

        return bpf(vertice); //Llamo al otro metodo
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TCaminos todosLosCaminos = new TCaminos();
        IVertice verticeOrigen = buscarVertice(etiquetaOrigen);

        if (verticeOrigen != null) {
            TCamino caminoInicial = new TCamino((TVertice) verticeOrigen);
            verticeOrigen.todosLosCaminos(etiquetaDestino, caminoInicial, todosLosCaminos);
        }
        return todosLosCaminos;
    }

    public boolean tieneCiclo() {
        desvisitarVertices();
        for (IVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                TCamino camino = new TCamino((TVertice) vertice); // Se inicializa con el vértice de origen
                boolean hayCiclo = vertice.tieneCiclo(camino);
                if (hayCiclo) {
                    return true;
                }
            }
        }

        return false;
    }

    public void calcularCaminoCritico(Comparable origen, Comparable destino) {
        desvisitarVertices();
        TCaminos caminos = todosLosCaminos(origen, destino);

        if (caminos.getCaminos().isEmpty()) {
            System.out.println("No hay caminos desde " + origen + " hasta " + destino);
            return;
        }

        TCamino caminoCritico = null;
        double maxCosto = Double.NEGATIVE_INFINITY;

        for (TCamino camino : caminos.getCaminos()) {
            double costo = camino.getCostoTotal();
            if (costo > maxCosto) {
                maxCosto = costo;
                caminoCritico = camino;
            }
        }

        System.out.println("Camino crítico:");
        System.out.println("   " + caminoCritico.imprimirEtiquetas());
        System.out.println("   Costo total: " + maxCosto + "\n");

        System.out.println("Caminos no críticos:");
        for (TCamino camino : caminos.getCaminos()) {
            if (camino != caminoCritico) {
                double costo = camino.getCostoTotal();
                double holgura = maxCosto - costo;
                System.out.println("   Secuencia: " + camino.imprimirEtiquetas());
                System.out.println("   Costo total: " + costo);
                System.out.println("   Holgura: " + holgura + "\n");
            }
        }
    }

    public LinkedList<TVertice> ordenParcial() {
        LinkedList<TVertice> orden = new LinkedList<>();

        // Se desvisitan todos los vértices
        for (IVertice v : this.getVertices().values()) {
            v.setVisitado(false);
        }

        // Se aplica DFS en cada vértice que no haya sido visitado
        for (IVertice v : this.getVertices().values()) {
            if (!v.getVisitado()) {
                ((TVertice) v).ordenParcial(orden);
            }
        }

        return orden;
    }
}
