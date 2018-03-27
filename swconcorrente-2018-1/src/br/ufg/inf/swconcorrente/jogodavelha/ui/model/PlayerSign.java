package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

import org.jetbrains.annotations.Contract;

public enum PlayerSign {
    X("X"),
    O("O"),
    NONE(" ");

    private final String SIGN;

    PlayerSign(final String SIGN) {
        this.SIGN = SIGN;
    }

    public boolean compare(final PlayerSign SIGN) {
        return this.SIGN.equals(SIGN.toString());
    }

    @Contract(pure = true)
    public String toString() {
        return this.SIGN;
    }
}