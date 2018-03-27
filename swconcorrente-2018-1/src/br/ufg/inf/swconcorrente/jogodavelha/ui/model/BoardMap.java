package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

public enum BoardMap {
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
    * TOP_LEFT     |    TOP     |    TOP_RIGHT
    * LEFT         |   CENTER   |        RIGHT
    * BOTTOM_LEFT  |   BOTTOM   | BOTTOM_RIGHT
    * */

    private final int POSITION;

    BoardMap(final int POSITION) {
        this.POSITION = POSITION;
    }

    public int getPosition() {
        return POSITION;
    }
}
