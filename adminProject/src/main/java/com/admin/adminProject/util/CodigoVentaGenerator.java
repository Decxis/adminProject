package com.admin.adminProject.util;
import java.util.Random;



public class CodigoVentaGenerator {

    private static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMEROS = "0123456789";
    private static final int LONGITUD_CODIGO = 8;

    public static String generarCodigo() {
        StringBuilder codigo = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < LONGITUD_CODIGO; i++) {
            if (random.nextBoolean()) {
                codigo.append(LETRAS.charAt(random.nextInt(LETRAS.length())));
            } else {
                codigo.append(NUMEROS.charAt(random.nextInt(NUMEROS.length())));
            }
        }
        return codigo.toString();
    }
}
