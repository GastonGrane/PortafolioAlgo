package PDS.UT9;

import java.util.Random;

public class GeneradorDatosGenericos {
	
	public int[] generarDatosAleatorios(int size) {
		Random rnd = new Random();
		int[] datosGenerados = new int[size];
		boolean[] datosUtilizados = new boolean[size];
		for (int i = 0; i < datosGenerados.length; i++) {
			int j = rnd.nextInt(size);
			while(datosUtilizados[j]){
				j = (j + 1) % size;
			}
			datosGenerados[j] = i;
			datosUtilizados[j] = true;
		}
		return datosGenerados;
	}
	
	public int[] generarDatosAscendentes(int size) {
		int [] copiaAscendente = new int[size];
		for (int i = 0; i < size; i++) {
			copiaAscendente[i] = i;
		}
		return copiaAscendente;
	}
	
	public int[] generarDatosDescendentes(int size) {
		int [] copiaDescendente = new int[size];
		for (int i = 0; i < size; i++) {
			copiaDescendente[i] = size - i;
		}
		return copiaDescendente;
	}
	
}
