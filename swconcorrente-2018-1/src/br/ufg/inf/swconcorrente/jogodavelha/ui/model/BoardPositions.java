package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

public enum BoardPositions {
    TOP_LEFT(0),
    TOP(1),
    TOP_RIGHT(2),
    LEFT(3),
    CENTER(4),
    RIGHT(5),
    BOTTOM_LEFT(6),
    BOTTOM(7),
    BOTTOM_RIGHT(8),
    NONE(-1);

    /* MAP:
    * TOP_LEFT    |    TOP     |    TOP_RIGHT
    * LEFT        |   CENTER   |        RIGHT
    * BTTOM_LEFT  |   BOTTOM   | BOTTOM_RIGHT
    * */

    private final int value;

    BoardPositions(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
