package br.ufg.inf.swconcorrente.decriptador.ui.model;

import java.util.ArrayList;
import java.util.Arrays;

public class CifraDeCezar {

    private final char ERROR_CODE = '¢';
    private final ArrayList<Cifra> CIFRAS = new ArrayList<>(Arrays.asList(Cifra.values()));

    public String decode(final String message) {
        String decoded = message;
        String d;

        for (char c : message.toCharArray()) {
            if (isALetter("" + c)) {
                d = String.valueOf(getEncoded(c)).toUpperCase();
                decoded = (d.charAt(0) != ERROR_CODE) ? decoded.replace(c, d.charAt(0)) : decoded.replace(c, '?');
            }
        }

        return  decoded;
    }

    public String encode(final String message) {
        String encoded = message;
        String d;

        for (char c : message.toCharArray()) {
            if (isALetter("" + c)) {
                d = String.valueOf(getDecoded(c)).toUpperCase();
                encoded = (d.charAt(0) != ERROR_CODE) ? encoded.replace(c, d.charAt(0)) : encoded.replace(c, '?');
            }
        }

        return  encoded;
    }

    private boolean isALetter(String letter) {
        for (Cifra CIFRA : CIFRAS) {
            if (CIFRA.foundCifra(letter)) {
                return true;
            }
        }

        return false;
    }

    private char getEncoded(char c) {
        for (Cifra CIFRA : CIFRAS) {
            if (CIFRA.foundLetter("" + c)) {
                return CIFRA.getEncripted().charAt(0);
            }
        }

        return ERROR_CODE;
    }

    private char getDecoded(char c) {
        for (Cifra CIFRA : CIFRAS) {
            if (CIFRA.foundCifra("" + c)) {
                return CIFRA.getDecripted().charAt(0);
            }
        }

        return ERROR_CODE;
    }

}
