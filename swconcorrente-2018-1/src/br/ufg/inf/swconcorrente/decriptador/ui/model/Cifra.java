package br.ufg.inf.swconcorrente.decriptador.ui.model;

import java.util.Map;

public enum Cifra {
    A(
        "F", 0
    ), B(
        "L", 1
    ), C(
        "Q", 2
    ), D(
        "H", 3
    ), E(
        "J", 4
    ), F(
        "V", 5
    ), G(
        "N", 6
    ), H(
        "Y", 7
    ), I(
        "S", 8
    ), J(
        "B", 9
    ), K(
        "T", 10
    ), L(
        "W", 11
    ), M(
        "E", 12
    ), N(
        "Z", 13
    ), O(
        "X", 14
    ), P(
        "U", 15
    ), Q(
        "C", 16
    ), R(
        "O", 17
    ), S(
        "K", 18
    ), T(
        "A", 19
    ), U(
        "R", 20
    ), V(
        "I", 21
    ), W(
        "D", 22
    ), X(
        "P", 23
    ), Y(
        "M", 24
    ), Z(
        "G", 25
    );

    private final String CIFRA;

    Cifra(String CIFRA, int POSITION) {
        this.CIFRA = CIFRA;
    }

    public String getEncripted() {
        return CIFRA;
    }

    public String getDecripted() {
        return name();
    }

    public boolean foundCifra(String cifrado) {
        return CIFRA.equalsIgnoreCase(cifrado);
    }

    public boolean foundLetter(String letter) {
        return name().equalsIgnoreCase(letter);
    }

}
