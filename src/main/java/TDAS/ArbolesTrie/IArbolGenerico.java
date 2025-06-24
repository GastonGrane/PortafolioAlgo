package TDAS.ArbolesTrie;

public interface IArbolGenerico {

    public boolean insertar(Comparable etiqueta, Comparable etiquetaPadre);

    public TNodoArbolGenerico buscar(Comparable etiqueta);

    public String listarIdentado();

    public boolean esVacio();

}
