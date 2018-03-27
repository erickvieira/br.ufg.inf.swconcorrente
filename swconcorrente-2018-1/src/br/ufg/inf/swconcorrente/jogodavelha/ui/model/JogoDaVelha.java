package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Arrays;

public class JogoDaVelha {

    private static final int BOARD_LIMIT = 9;
    private ArrayList<PlayerSign> boardGame;
    private MatchMap match;
    private PlayerSign currentPlayer;

    private static final MatchMap[] ARRAY_MATCHES = MatchMap.values();

    public JogoDaVelha() {
        initBoardGame();
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
        if (position != BoardMap.NONE.getPosition()) {
            boardGame.set(position, player);

            if (turnVerify()) {
                return match;
            } else {
                return MatchMap.NONE;
            }
        } else {
            return MatchMap.NONE;
        }
    }

    @Contract(pure = true)
    private boolean checkIsMarked(int position, PlayerSign sign) {
        return (
            position != BoardMap.NONE.getPosition()
            && boardGame.get(position).compare(sign)
        );
    }

    private boolean turnVerify() {
        final String CHECKED = "T";
        final String NOT_CHECKED = "F";
        final String END_OF_GAME = CHECKED + CHECKED + CHECKED;

        StringBuilder concatLogicTest = new StringBuilder();
        concatLogicTest.append("");
        boolean gameOver = false;

        for (MatchMap match : ARRAY_MATCHES) {
            int[] arr = match.getArray();

            for (int index : arr) {
                concatLogicTest.append(
                    checkIsMarked(index, currentPlayer) ? CHECKED : NOT_CHECKED
                );
            }

            gameOver = concatLogicTest.toString().equalsIgnoreCase(END_OF_GAME);

            if (gameOver) {
                this.match = match;
                break;
            } else {
                concatLogicTest = new StringBuilder();
            }
        }

        return gameOver;
    }

}
