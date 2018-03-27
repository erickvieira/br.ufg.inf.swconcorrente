package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

import org.jetbrains.annotations.Contract;

public enum MatchMap {
    TOP_LEFT_TO_BOTTOM_RIGHT(
        new int[] {
            BoardMap.TOP_LEFT.getPosition(),
            BoardMap.CENTER.getPosition(),
            BoardMap.BOTTOM_RIGHT.getPosition()
        }
    ), TOP_LEFT_TO_BOTTOM_LEFT(
        new int[] {
            BoardMap.TOP_LEFT.getPosition(),
            BoardMap.LEFT.getPosition(),
            BoardMap.BOTTOM_LEFT.getPosition()
        }
    ), TOP_TO_BOTTOM(
        new int[] {
            BoardMap.TOP.getPosition(),
            BoardMap.CENTER.getPosition(),
            BoardMap.BOTTOM.getPosition()
        }
    ), TOP_RIGHT_TO_BOTTOM_RIGHT(
        new int[] {
            BoardMap.TOP_RIGHT.getPosition(),
            BoardMap.RIGHT.getPosition(),
            BoardMap.BOTTOM_RIGHT.getPosition()
        }
    ), TOP_RIGHT_TO_BOTTOM_LEFT(
        new int[] {
            BoardMap.TOP_RIGHT.getPosition(),
            BoardMap.CENTER.getPosition(),
            BoardMap.BOTTOM_LEFT.getPosition()
        }
    ), TOP_LEFT_TO_TOP_RIGHT(
        new int[] {
            BoardMap.TOP_LEFT.getPosition(),
            BoardMap.TOP.getPosition(),
            BoardMap.TOP_RIGHT.getPosition()
        }
    ), LEFT_TO_RIGHT(
        new int[] {
            BoardMap.LEFT.getPosition(),
            BoardMap.CENTER.getPosition(),
            BoardMap.RIGHT.getPosition()
        }
    ), BOTTOM_LEFT_TO_BOTTOM_RIGHT(
        new int[] {
            BoardMap.BOTTOM_LEFT.getPosition(),
            BoardMap.BOTTOM.getPosition(),
            BoardMap.BOTTOM_RIGHT.getPosition()
        }
    ), NONE(
        new int[] {
            BoardMap.NONE.getPosition(),
            BoardMap.NONE.getPosition(),
            BoardMap.NONE.getPosition()
        }
    );

    private final int[] ARRAY;

    MatchMap(final int[] ARRAY) {
        this.ARRAY = ARRAY;
    }

    @Contract(pure = true)
    public int[] getArray() {
        return ARRAY;
    }

}
