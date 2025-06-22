package PDS.UT5;

import TDAS.TArbolGenerico;

public class PD1 {
    public static void main(String[] args) {

        TArbolGenerico arbol = new TArbolGenerico();

        arbol.insertar("RECTORIA", "");
        arbol.insertar("VICERRECTORÍA ADMINISTRATIVA","RECTORIA");
        arbol.insertar("VICERRECTORÍA ACADÉMICA","RECTORIA");
        arbol.insertar("VICERRECTORÍA DEL MEDIO UNIVERSITARIO","RECTORIA");
        arbol.insertar("FACULTAD DE PSICOLOGÍA", "VICERRECTORÍA ACADÉMICA");
        arbol.insertar("FACULTAD DE INGENIERÍA Y TECNOLOGÍAS", "VICERRECTORÍA ACADÉMICA");
        arbol.insertar("FACULTAD DE CIENCIAS HUMANAS", "VICERRECTORÍA ACADÉMICA");
        arbol.insertar("FACULTAD DE CIENCIAS EMPRESARIALES", "VICERRECTORÍA ACADÉMICA");
        arbol.insertar("DEPARTAMENTO DE MATEMATICAS", "FACULTAD DE INGENIERÍA Y TECNOLOGÍAS");
        arbol.insertar("DEPARTAMENTO DE INGENIERÍA ELÉCTRICA", "FACULTAD DE INGENIERÍA Y TECNOLOGÍAS");
        arbol.insertar("DEPARTAMENTO DE INFORMÁTICA Y  CIENCIAS DE LA COMPUTACIÓN", "FACULTAD DE INGENIERÍA Y TECNOLOGÍAS");


        System.out.println("PARTE 6");
        System.out.println();
        System.out.println(arbol.buscar("RECTORIA").getEtiqueta());
        System.out.println(arbol.buscar("FACULTAD DE INGENIERÍA Y TECNOLOGÍAS").getEtiqueta());
        System.out.println(arbol.buscar("DEPARTAMENTO DE INGENIERÍA ELÉCTRICA").getEtiqueta());
        System.out.println(arbol.buscar("DEPARTAMENTO DE MEDICINA"));
        System.out.println(arbol.buscar("DEPARTAMENTO DE DERECHO"));
        System.out.println();

        
        System.out.println("PARTE 7");
        System.out.println();
        System.out.println(arbol.listarIdentado());

    }
}
