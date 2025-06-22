package PDS.UT5.PD7;

import Utils.ManejadorArchivosGenerico;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TArbolTrieTelefonos trieAbonados = new TArbolTrieTelefonos();

        String[] abonados = ManejadorArchivosGenerico.leerArchivo("src\\main\\java\\PDS\\UT5\\PD7\\abonados.txt");
        String[] abonadoParticular;
        for (String i : abonados){
            abonadoParticular = i.split(",");
            trieAbonados.insertar(new TAbonado(abonadoParticular[1], abonadoParticular[0]));
        }
        // CARGAR EN EL TRIE LOS TELÉFONOS Y NOMBRES A PARTIR DEL ARCHIVO ABONADOS.TXT


        String codigoPais = "054" ; // utilizar el indicado en el archivo "codigos.txt"
        String codigoArea = "90" ;// utilizar el indicado en el archivo "codigos.txt"
        LinkedList<TAbonado> ab = trieAbonados.buscarTelefonos(codigoPais, codigoArea);

        abonados = new String[ab.size()];
        for (int a = 0; a < ab.size(); a++){
            abonados[a] = ab.get(a).getNombre() + "," + ab.get(a).getTelefono();
        }
        ManejadorArchivosGenerico.escribirArchivo("src\\main\\java\\PDS\\UT5\\PD7\\salida.txt", abonados);

        // crear el archivo "salida.txt", con los abonados (1 por linea)
        // correspondientes al pais y area
        // imprimir Nombre y teléfono,
        // ordenados alfabeticamente por nombre
    }
}