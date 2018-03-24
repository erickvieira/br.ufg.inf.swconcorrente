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

    private static final MatchMap[] _ARRAY_MATCHES = {
            MatchMap.TOP_RIGHT_TO_BOTTOM_RIGHT,
            MatchMap.TOP_RIGHT_TO_BOTTOM_LEFT,
            MatchMap.TOP_TO_BOTTOM,
            MatchMap.TOP_RIGHT_TO_BOTTOM_RIGHT,
            MatchMap.TOP_RIGHT_TO_BOTTOM_LEFT,
            MatchMap.TOP_LEFT_TO_TOP_RIGHT,
            MatchMap.LEFT_TO_RIGHT,
            MatchMap.BOTTOM_LEFT_TO_BOTTOM_RIGHT,
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
        if (turnVerify()) {
            printAsBoard();
            System.out.println("<<" + currentPlayer.toString() + ">>");
            return match;
        } else {
            return MatchMap.NONE;
        }
    }

    private void printAsBoard() {
        System.out.printf("" +
                " =========== \n" +
                "| %s | %s | %s |\n" +
                "|---|---|---|\n" +
                "| %s | %s | %s |\n" +
                "|---|---|---|\n" +
                "| %s | %s | %s |\n" +
                " =========== \n",
                boardGame.get(0).toString(),
                boardGame.get(1).toString(),
                boardGame.get(2).toString(),
                boardGame.get(3).toString(),
                boardGame.get(4).toString(),
                boardGame.get(5).toString(),
                boardGame.get(6).toString(),
                boardGame.get(7).toString(),
                boardGame.get(8).toString());
    }

    @Contract(pure = true)
    private boolean checkIsMarked(int position, PlayerSign sign) {
        System.out.println("COMPARING:[ " + boardGame.get(position) + ", " + sign + " ]");
        return boardGame.get(position).compare(sign);
    }

    private boolean checkGameOver() {
        boolean foundMatch = false;

        for (int[] matches: ARRAY_MATCHES) {
            int count = 0;
            boolean tryToMatch[] = { false, false, false };
            for (int element: matches) {
                tryToMatch[count] = checkIsMarked(element, currentPlayer);
                System.out.println("[" + currentPlayer + ", " + element + "]");
                System.out.println(Arrays.toString(tryToMatch));
                System.out.println(Arrays.toString(matches));
                foundMatch = verifyPossibleMatch(tryToMatch);

                if (foundMatch) {
                    System.out.println("BOARD: {\n" +
                            "\t\t" + boardGame.get(matches[0]) + ",\n" +
                            "\t\t" + boardGame.get(matches[1]) + ",\n" +
                            "\t\t" + boardGame.get(matches[2]) + "\n" +
                            "}");
                    setMatch(matches);
                    System.out.println("\n\n\n\n<BR1>\n\n\n");
                    break;
                }

                count++;
            }

            System.out.println("BOARD: {\n" +
                    "\t\t" + boardGame.get(matches[0]) + ",\n" +
                    "\t\t" + boardGame.get(matches[1]) + ",\n" +
                    "\t\t" + boardGame.get(matches[2]) + "\n" +
                    "}");
            if (foundMatch) {
                System.out.println("\n\n\n\n<BR2>\n\n\n");
                break;
            }
        }

        return foundMatch;
    }

    private boolean turnVerify() {
        StringBuilder concatLogicTest = new StringBuilder();
        concatLogicTest.append("");
        boolean gameOver = false;

        for (MatchMap match : _ARRAY_MATCHES) {
            int[] arr = match.getArray();

            for (int index : arr) {
                concatLogicTest.append(checkIsMarked(arr[index], currentPlayer) ? "T" : "F");
            }

            gameOver = concatLogicTest.toString().equals("TTT");

            if (gameOver) {
                this.match = match;
                break;
            }
        }

        return gameOver;
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
