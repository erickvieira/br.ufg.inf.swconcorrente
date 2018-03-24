package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

import org.jetbrains.annotations.Contract;

import java.util.Arrays;

public enum MatchMap {
    TOP_LEFT_TO_BOTTOM_RIGHT(
            BoardMap.TOP_LEFT.getValue(),
            BoardMap.CENTER.getValue(),
            BoardMap.BOTTOM_RIGHT.getValue()
    ), TOP_LEFT_TO_BOTTOM_LEFT(
            BoardMap.TOP_LEFT.getValue(),
            BoardMap.LEFT.getValue(),
            BoardMap.BOTTOM_LEFT.getValue()
    ), TOP_TO_BOTTOM(
            BoardMap.TOP.getValue(),
            BoardMap.CENTER.getValue(),
            BoardMap.BOTTOM.getValue()
    ), TOP_RIGHT_TO_BOTTOM_RIGHT(
            BoardMap.TOP_RIGHT.getValue(),
            BoardMap.RIGHT.getValue(),
            BoardMap.BOTTOM_RIGHT.getValue()
    ), TOP_RIGHT_TO_BOTTOM_LEFT(
            BoardMap.TOP_RIGHT.getValue(),
            BoardMap.CENTER.getValue(),
            BoardMap.BOTTOM_LEFT.getValue()
    ), TOP_LEFT_TO_TOP_RIGHT(
            BoardMap.TOP_LEFT.getValue(),
            BoardMap.TOP.getValue(),
            BoardMap.TOP_RIGHT.getValue()
    ), LEFT_TO_RIGHT(
            BoardMap.LEFT.getValue(),
            BoardMap.CENTER.getValue(),
            BoardMap.RIGHT.getValue()
    ), BOTTOM_LEFT_TO_BOTTOM_RIGHT(
            BoardMap.BOTTOM_LEFT.getValue(),
            BoardMap.BOTTOM.getValue(),
            BoardMap.BOTTOM_RIGHT.getValue()
    ), NONE(null);

    public final String NO_HASH = "-1";
    private final int LIMIT = 3;
    private int[] array = new int[LIMIT];
    private String hashCode;

    MatchMap(int pos1, int pos2, int pos3) {
        this.array[0] = pos1;
        this.array[1] = pos2;
        this.array[2] = pos3;
        hash();
    }

    MatchMap(int[] array) {
        this.array = array;
        hashCode = NO_HASH;
    }

    private void hash() {
        hashCode = String.format("%s%s%s", array[0], array[1], array[2]);
    }

    public String getHashCode() {
        return hashCode;
    }

    @Contract(pure = true)
    public boolean equals(int[] array) {
        return Arrays.equals(array, this.array);
    }

    @Contract(pure = true)
    public int[] getArray() {
        return array;
    }

}
