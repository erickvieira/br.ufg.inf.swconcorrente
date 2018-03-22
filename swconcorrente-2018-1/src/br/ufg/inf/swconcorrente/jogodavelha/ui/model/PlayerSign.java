package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

public enum PlayerSign {
    X("X"),
    O("O"),
    NONE("");

    private final String sign;

    PlayerSign(String s) {
        sign = s;
    }

    public String toString() {
        return this.sign;
    }
}