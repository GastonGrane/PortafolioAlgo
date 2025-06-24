package TDAS.ArbolesTrie;

public class TArbolGenerico implements IArbolGenerico {

    public TNodoArbolGenerico raiz;

    public String preOrden(){
        if(esVacio()){
            return "";
        }else{
            return raiz.preOrden();
        }
    }

    public String inOrden(){
        if(esVacio()){
            return "";
        }else{
            return raiz.inOrden();
        }
    }

    public String postOrden(){
        if(esVacio()){
            return "";
        }else{
            return raiz.postOrden();
        }
    }

    @Override
    public boolean insertar(Comparable etiqueta, Comparable etiquetaPadre) {
        TNodoArbolGenerico nodo = new TNodoArbolGenerico(etiqueta);
        if(esVacio() && !etiquetaPadre.equals("")){
            return false;
        } else if(etiquetaPadre.equals("")){
            if(!esVacio() && raiz.buscar(etiqueta) != null){
                return false;
            }
            nodo.setHijo(raiz);
            raiz = nodo;
            return true;
        }else{
            return raiz.insertar(etiqueta, etiquetaPadre);
        }
    }

    @Override
    public TNodoArbolGenerico buscar(Comparable etiqueta) {
        if (esVacio()){
            return null;
        }else{
            return raiz.buscar(etiqueta);
        }
    }

    @Override
    public String listarIdentado() {
        if (esVacio()){
            return "";
        }else{
            return raiz.listarIdentado(0);
        }
    }

    @Override
    public boolean esVacio() {
        return raiz == null;
    }

}