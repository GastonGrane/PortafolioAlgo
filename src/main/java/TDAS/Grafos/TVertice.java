package TDAS.Grafos;

import java.util.Collection;
import java.util.LinkedList;

public class TVertice<T> implements IVertice {

    private final Comparable etiqueta;
    private LinkedList<IAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<IAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    public void bpf(Collection<TVertice> visitado1){
            this.visitado = true;
            visitado1.add(this);

            for (IAdyacencia ady : this.adyacentes){
                TVertice destino = (TVertice) ady.getDestino();
                if (!destino.getVisitado()){
                    destino.bpf(visitado1);
                }
            }
    }

    @Override
    public IAdyacencia buscarAdyacencia(IVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(IVertice verticeDestino) {
        IAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, IVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        IAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public IVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    public IVertice siguienteAdyacente(IVertice w) {
        IAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    @Override
    public IAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (IAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public T getDatos() {
        return datos;
    }


    /**
     * Devuelve la etiqueta del vértice como representación en texto.
     * Esto permite imprimir objetos TVertice en consola de forma legible como en el TA4.
     * Asi podemos mostrar los recorridos en una lista de una forma mas rapida.
     */
    @Override
    public String toString() {
        return this.etiqueta.toString();
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.setVisitado(true);
        for (IAdyacencia adyacencia : this.getAdyacentes()) {
            IVertice destino = (TVertice) adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia((TAdyacencia) adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    caminoPrevio.agregarAdyacencia((TAdyacencia) adyacencia);
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                    caminoPrevio.eliminarAdyacencia((TAdyacencia) adyacencia);
                }
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }

    @Override
    public boolean tieneCiclo(TCamino unCamino) {
        this.setVisitado(true);
        unCamino.getOtrosVertices().add(this.getEtiqueta());  // Agrego al camino

        for (IAdyacencia adyacencia : adyacentes) {
            TVertice destino = (TVertice) adyacencia.getDestino();
            Comparable etiquetaDestino = destino.getEtiqueta();

            if (unCamino.getOtrosVertices().contains(etiquetaDestino)) {
                // El vértice ya está en el camino actual ⇒ ciclo
                return true;
            }

            if (!destino.getVisitado()) {
                unCamino.agregarAdyacencia((TAdyacencia) adyacencia);
                boolean ciclo = destino.tieneCiclo(unCamino);
                unCamino.eliminarAdyacencia((TAdyacencia) adyacencia);
                if (ciclo) return true;
            }
        }

        unCamino.getOtrosVertices().remove(this.getEtiqueta());  // Backtrack
        this.setVisitado(false);
        return false;
    }

    public void ordenParcial(LinkedList<TVertice> orden) {
        this.setVisitado(true);
        LinkedList<IAdyacencia> adyacentes = this.getAdyacentes();
        for (IAdyacencia adyacencia : adyacentes) {
            TVertice adyacente = (TVertice) adyacencia.getDestino();
            if (!adyacente.getVisitado()) {
                adyacente.ordenParcial(orden);
            }
        }
        orden.addFirst(this);
    }


}
