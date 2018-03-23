package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

import org.omg.CORBA.MARSHAL;

import java.util.ArrayList;
import java.util.Arrays;

public class JogoDaVelha {

    private static final int BOARD_LIMIT = 9;
    private ArrayList<PlayerSign> boardGame;
    private Matches match;
    private PlayerSign currentPlayer;

    private static final int[][] ARRAY_MATCHES = {
            Matches.TOP_RIGHT_TO_BOTTOM_RIGHT.getArray(),
            Matches.TOP_RIGHT_TO_BOTTOM_LEFT.getArray(),
            Matches.TOP_TO_BOTTOM.getArray(),
            Matches.TOP_RIGHT_TO_BOTTOM_RIGHT.getArray(),
            Matches.TOP_RIGHT_TO_BOTTOM_LEFT.getArray(),
            Matches.TOP_LEFT_TO_TOP_RIGHT.getArray(),
            Matches.LEFT_TO_RIGHT.getArray(),
            Matches.BOTTOM_LEFT_TO_BOTTOM_RIGHT.getArray(),
    };

    public JogoDaVelha() {
        initBoardGame();
        System.out.println("INIT LIST: " + boardGame.toString());
        match = Matches.NONE;
        currentPlayer = PlayerSign.NONE;
    }
    
    private void initBoardGame() {
        boardGame = new ArrayList<>(BOARD_LIMIT);

        for (int i = 0; i < BOARD_LIMIT; i++) {
            boardGame.add(PlayerSign.NONE);
        }
    }
    
    public Matches setSelected(int position, PlayerSign player) {
        currentPlayer = player;
        boardGame.set(position, player);
        if (checkGameOver()) {
            return match;
        } else {
            return Matches.NONE;
        }
    }

    private boolean checkIsMarked(int position, PlayerSign sign) {
        return boardGame.get(position) == sign;
    }

    private boolean checkGameOver() {
        boolean foudMatch = false;

        for (int[] matches: ARRAY_MATCHES) {
            for (int element: matches) {
                foudMatch = checkIsMarked(element, currentPlayer);

                if (foudMatch) {
                    setMatch(matches);
                    break;
                }
            }

            if (foudMatch) {
                break;
            }
        }

        return foudMatch;
    }

    private void setMatch(int[] matches) {
        if (Matches.TOP_LEFT_TO_BOTTOM_RIGHT.equals(matches)) {
            match = Matches.TOP_LEFT_TO_BOTTOM_RIGHT;
        } else if (Matches.TOP_LEFT_TO_BOTTOM_LEFT.equals(matches)) {
            match = Matches.TOP_LEFT_TO_BOTTOM_LEFT;
        } else if (Matches.TOP_TO_BOTTOM.equals(matches)) {
            match = Matches.TOP_TO_BOTTOM;
        } else if (Matches.TOP_RIGHT_TO_BOTTOM_RIGHT.equals(matches)) {
            match = Matches.TOP_RIGHT_TO_BOTTOM_RIGHT;
        } else if (Matches.TOP_RIGHT_TO_BOTTOM_LEFT.equals(matches)) {
            match = Matches.TOP_RIGHT_TO_BOTTOM_LEFT;
        } else if (Matches.TOP_LEFT_TO_TOP_RIGHT.equals(matches)) {
            match = Matches.TOP_LEFT_TO_TOP_RIGHT;
        } else if (Matches.LEFT_TO_RIGHT.equals(matches)) {
            match = Matches.LEFT_TO_RIGHT;
        } else if (Matches.BOTTOM_LEFT_TO_BOTTOM_RIGHT.equals(matches)) {
            match = Matches.BOTTOM_LEFT_TO_BOTTOM_RIGHT;
        }
    }

}
