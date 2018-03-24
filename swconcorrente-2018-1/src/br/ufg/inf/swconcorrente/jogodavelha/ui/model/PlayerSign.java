package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

import org.jetbrains.annotations.Contract;

public enum PlayerSign {
    X("X"),
    O("O"),
    NONE(" ");

    private final String sign;

    PlayerSign(String s) {
        sign = s;
    }

    public boolean compare(String sign) {
        return this.sign.equals(sign);
    }

    public boolean compare(PlayerSign sign) {
        return this.sign.equals(sign.toString());
    }

    @Contract(pure = true)
    public String toString() {
        return this.sign;
    }
}