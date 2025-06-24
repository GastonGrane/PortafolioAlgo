package TDAS.ArbolesTrie;

public interface INodoArbolGenerico {

    public boolean insertar(Comparable etiqueta, Comparable etiquetaPadre);

    public TNodoArbolGenerico buscar(Comparable etiqueta);

    public String listarIdentado(int nivel);
}
