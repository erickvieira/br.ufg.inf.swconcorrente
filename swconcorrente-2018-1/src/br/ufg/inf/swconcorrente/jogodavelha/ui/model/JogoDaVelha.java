package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Arrays;

public class JogoDaVelha {

    private static final int BOARD_LIMIT = 9;
    private ArrayList<PlayerSign> boardGame;
    private MatchMap match;
    private PlayerSign currentPlayer;

    private static final int[][] ARRAY_MATCHES = {
            MatchMap.TOP_RIGHT_TO_BOTTOM_RIGHT.getArray(),
            MatchMap.TOP_RIGHT_TO_BOTTOM_LEFT.getArray(),
            MatchMap.TOP_TO_BOTTOM.getArray(),
            MatchMap.TOP_RIGHT_TO_BOTTOM_RIGHT.getArray(),
            MatchMap.TOP_RIGHT_TO_BOTTOM_LEFT.getArray(),
            MatchMap.TOP_LEFT_TO_TOP_RIGHT.getArray(),
            MatchMap.LEFT_TO_RIGHT.getArray(),
            MatchMap.BOTTOM_LEFT_TO_BOTTOM_RIGHT.getArray(),
    };

    public JogoDaVelha() {
        initBoardGame();
        System.out.println("INIT LIST: " + boardGame.toString());
        match = MatchMap.NONE;
        currentPlayer = PlayerSign.NONE;
    }
    
    private void initBoardGame() {
        boardGame = new ArrayList<>(BOARD_LIMIT);

        for (int i = 0; i < BOARD_LIMIT; i++) {
            boardGame.add(PlayerSign.NONE);
        }
    }
    
    public MatchMap setSelected(int position, PlayerSign player) {
        currentPlayer = player;
        boardGame.set(position, player);
        if (checkGameOver()) {
            return match;
        } else {
            return MatchMap.NONE;
        }
    }

    @Contract(pure = true)
    private boolean checkIsMarked(int position, PlayerSign sign) {
        return boardGame.get(position) == sign;
    }

    private boolean checkGameOver() {
        boolean foundMatch = false;
        boolean tryToMatch[] = { false, false, false };

        for (int[] matches: ARRAY_MATCHES) {
            int count = 0;
            for (int element: matches) {
                tryToMatch[count] = checkIsMarked(element, currentPlayer);
                System.out.println(Arrays.toString(tryToMatch));
                System.out.println(Arrays.toString(matches));
                foundMatch = verifyPossibleMatch(tryToMatch);

                if (foundMatch) {
                    setMatch(matches);
                    break;
                }

                count++;
            }

            if (foundMatch) {
                break;
            }
        }

        return foundMatch;
    }

    private boolean verifyPossibleMatch(boolean[] array) {
        return array[0] && array[1] && array[2];
    }

    private void setMatch(int[] matches) {
        if (MatchMap.TOP_LEFT_TO_BOTTOM_RIGHT.equals(matches)) {
            match = MatchMap.TOP_LEFT_TO_BOTTOM_RIGHT;
        } else if (MatchMap.TOP_LEFT_TO_BOTTOM_LEFT.equals(matches)) {
            match = MatchMap.TOP_LEFT_TO_BOTTOM_LEFT;
        } else if (MatchMap.TOP_TO_BOTTOM.equals(matches)) {
            match = MatchMap.TOP_TO_BOTTOM;
        } else if (MatchMap.TOP_RIGHT_TO_BOTTOM_RIGHT.equals(matches)) {
            match = MatchMap.TOP_RIGHT_TO_BOTTOM_RIGHT;
        } else if (MatchMap.TOP_RIGHT_TO_BOTTOM_LEFT.equals(matches)) {
            match = MatchMap.TOP_RIGHT_TO_BOTTOM_LEFT;
        } else if (MatchMap.TOP_LEFT_TO_TOP_RIGHT.equals(matches)) {
            match = MatchMap.TOP_LEFT_TO_TOP_RIGHT;
        } else if (MatchMap.LEFT_TO_RIGHT.equals(matches)) {
            match = MatchMap.LEFT_TO_RIGHT;
        } else if (MatchMap.BOTTOM_LEFT_TO_BOTTOM_RIGHT.equals(matches)) {
            match = MatchMap.BOTTOM_LEFT_TO_BOTTOM_RIGHT;
        }
    }

}
