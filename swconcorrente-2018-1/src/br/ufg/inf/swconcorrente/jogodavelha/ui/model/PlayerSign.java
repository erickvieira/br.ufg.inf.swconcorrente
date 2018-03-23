package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

import org.jetbrains.annotations.Contract;

public enum PlayerSign {
    X("X"),
    O("O"),
    NONE("");

    private final String sign;

    PlayerSign(String s) {
        sign = s;
    }

    @Contract(pure = true)
    public String toString() {
        return this.sign;
    }
}