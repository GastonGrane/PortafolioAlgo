package PDS.UT3.PD9;

public class UT3_PD9 {

    public static void main(String[] args) {

        //Instancio clase Expresion
        Expresion expresion = new Expresion();

        char[] eBien = {'{','}','{','{','}','}'};
        char[] eMal = {'{','{','}','{','{','}'};
        char[] eBien2 = {'{','}','{','{','}','}','{','{','}','}'};
        char[] eMal2 = {'}','}','{','{','}','}'};
        char[] eBien3 = {'{','{','{','}','}','}'};
        char[] eMal3 = {'{','{','}','{','{','}','{'};

        System.out.println(expresion.controlCorchetes(eBien));
        System.out.println(expresion.controlCorchetes(eMal));
        System.out.println(expresion.controlCorchetes(eBien2));
        System.out.println(expresion.controlCorchetes(eMal2));
        System.out.println(expresion.controlCorchetes(eBien3));
        System.out.println(expresion.controlCorchetes(eMal3));

    }
}
