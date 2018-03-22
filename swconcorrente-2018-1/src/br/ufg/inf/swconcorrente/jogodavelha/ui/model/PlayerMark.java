package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

public enum PlayerMark {
    X("X"),
    O("O"),
    NONE("");

    private final String sign;

    PlayerMark(String s) {
        sign = s;
    }

    public String toString() {
        return this.sign;
    }
}