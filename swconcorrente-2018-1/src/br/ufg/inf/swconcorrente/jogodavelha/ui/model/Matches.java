package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

import java.util.Arrays;

public enum Matches {
    TOP_LEFT_TO_BOTTOM_RIGHT(Matches.TL_TO_BR),
    TOP_LEFT_TO_BOTTOM_LEFT(Matches.TL_TO_BL),
    TOP_TO_BOTTOM(Matches.T_TO_B),
    TOP_RIGHT_TO_BOTTOM_RIGHT(Matches.TR_TO_BR),
    TOP_RIGHT_TO_BOTTOM_LEFT(Matches.TR_TO_BL),
    TOP_LEFT_TO_TOP_RIGHT(Matches.TL_TO_TR),
    LEFT_TO_RIGHT(Matches.L_TO_R),
    BOTTOM_LEFT_TO_BOTTOM_RIGHT(Matches.BL_TO_BR),
    NONE(null);

    private static final int TL     = BoardPositions.TOP_LEFT.getValue();
    private static final int T      = BoardPositions.TOP.getValue();
    private static final int TR     = BoardPositions.TOP_RIGHT.getValue();
    private static final int L      = BoardPositions.LEFT.getValue();
    private static final int C      = BoardPositions.CENTER.getValue();
    private static final int R      = BoardPositions.RIGHT.getValue();
    private static final int BL     = BoardPositions.BOTTOM_LEFT.getValue();
    private static final int B      = BoardPositions.BOTTOM.getValue();
    private static final int BR     = BoardPositions.BOTTOM_RIGHT.getValue();

    private static final int[] TL_TO_BR = {TL, C, BR};
    private static final int[] TL_TO_BL = {TL, L, BL};
    private static final int[] T_TO_B = {T, C, B};
    private static final int[] TR_TO_BR = {TR, R, BR};
    private static final int[] TR_TO_BL = {TR, C, BL};
    private static final int[] TL_TO_TR = {TL, T, TR};
    private static final int[] L_TO_R = {L, C, R};
    private static final int[] BL_TO_BR = {BL, B, BR};

    private int[] array;

    Matches(int[] array) {
        this.array = array;
    }

    public boolean equals(int[] array) {
        if(Arrays.equals(array, this.array)) {
            return true;
        }

        return false;
    }

    public int[] getArray() {
        return array;
    }

    private enum MatchMap {

    }
}
