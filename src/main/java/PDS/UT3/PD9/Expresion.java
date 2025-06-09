package PDS.UT3.PD9;

import java.util.Stack;

class Expresion {

    public boolean controlCorchetes(char[] listaDeEntrada) {
        Stack pila = new Stack();
        for (char i : listaDeEntrada) {
            if (i == '}') {
                if (pila.empty()) {
                    return false;
                } else {
                    pila.pop();
                }
            } else if (i == '{') {
                pila.push(i);
            }
        }
        return pila.empty();

    }
}
