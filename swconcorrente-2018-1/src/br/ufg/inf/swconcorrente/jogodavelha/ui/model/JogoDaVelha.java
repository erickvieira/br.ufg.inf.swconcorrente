package br.ufg.inf.swconcorrente.jogodavelha.ui.model;

import java.util.ArrayList;

public class JogoDaVelha {

    private static final int BOARD_LIMIT = 9;
    private ArrayList<PlayerSign> boardGame;
    private Matches match;
    private int currentPosition;
    private PlayerSign currentPlayer;

    public JogoDaVelha() {
        initBoardGame();
        System.out.println("INIT LIST: " + boardGame.toString());
        match = Matches.NONE;
        currentPlayer = PlayerSign.NONE;
        currentPosition = BoardPositions.NONE.getValue();
    }
    
    private void initBoardGame() {
        boardGame = new ArrayList<>(BOARD_LIMIT);

        for (int i = 0; i < BOARD_LIMIT; i++) {
            boardGame.add(PlayerSign.NONE);
        }
    }
    
    public Matches setSelected(int position, PlayerSign player) {
        currentPosition = position;
        currentPlayer = player;
        boardGame.set(position, player);
        System.out.println("SET LIST ELEMENT: " + boardGame.toString());
        checkGameOver();
        System.out.println("AFTER CHECK: " + boardGame.toString());

        System.out.printf("");
        return match;
    }

    public boolean checkGameOver() {
        return (!checkMatches_TLtoBR().toString().equals(Matches.NONE.toString())
                || !checkMatches_TtoB().toString().equals(Matches.NONE.toString())
                || !checkMatches_TLtoBL().toString().equals(Matches.NONE.toString())
                || !checkMatches_TRtoBR().toString().equals(Matches.NONE.toString())
                || !checkMatches_TRtoBL().toString().equals(Matches.NONE.toString())
                || !checkMatches_TLtoTR().toString().equals(Matches.NONE.toString())
                || !checkMatches_LtoR().toString().equals(Matches.NONE.toString())
                || !checkMatches_BLtoBR().toString().equals(Matches.NONE.toString()));
    }

    public boolean checkIsMarked(int position, PlayerSign sign) {
        System.out.println("[" + position + "] LIST: " + boardGame.get(position) + " CLICKED: " + sign);
        return boardGame.get(position).toString().equals(sign.toString());
    }

    private Matches checkMatches_TLtoBR() {
        int id1 = BoardPositions.TOP_LEFT.getValue();
        int id2 = BoardPositions.CENTER.getValue();
        int id3 = BoardPositions.BOTTOM_RIGHT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = (isMarked1 && isMarked2 && isMarked3) ? Matches.TOP_LEFT_TO_BOTTOM_RIGHT : Matches.NONE;
        System.out.println("IDS: " + id1 + ", " + id2 + ", " + id3);
        return match;
    }

    private Matches checkMatches_TtoB() {
        int id1 = BoardPositions.TOP.getValue();
        int id2 = BoardPositions.CENTER.getValue();
        int id3 = BoardPositions.BOTTOM.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = (isMarked1 && isMarked2 && isMarked3) ? Matches.TOP_TO_BOTTOM : Matches.NONE;
        System.out.println("IDS: " + id1 + ", " + id2 + ", " + id3);
        return match;
    }

    private Matches checkMatches_TLtoBL() {
        int id1 = BoardPositions.TOP_LEFT.getValue();
        int id2 = BoardPositions.LEFT.getValue();
        int id3 = BoardPositions.BOTTOM_LEFT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = (isMarked1 && isMarked2 && isMarked3) ? Matches.TOP_LEFT_TO_BOTTOM_LEFT : Matches.NONE;
        System.out.println("IDS: " + id1 + ", " + id2 + ", " + id3);
        return match;
    }

    private Matches checkMatches_TRtoBR() {
        int id1 = BoardPositions.TOP_RIGHT.getValue();
        int id2 = BoardPositions.RIGHT.getValue();
        int id3 = BoardPositions.BOTTOM_RIGHT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = (isMarked1 && isMarked2 && isMarked3) ? Matches.TOP_RIGHT_TO_BOTTOM_RIGHT : Matches.NONE;
        System.out.println("IDS: " + id1 + ", " + id2 + ", " + id3);
        return match;
    }

    private Matches checkMatches_TRtoBL() {
        int id1 = BoardPositions.TOP_RIGHT.getValue();
        int id2 = BoardPositions.CENTER.getValue();
        int id3 = BoardPositions.BOTTOM_LEFT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = (isMarked1 && isMarked2 && isMarked3) ? Matches.TOP_RIGHT_TO_BOTTOM_LEFT : Matches.NONE;
        System.out.println("IDS: " + id1 + ", " + id2 + ", " + id3);
        return match;
    }

    private Matches checkMatches_TLtoTR() {
        int id1 = BoardPositions.TOP_LEFT.getValue();
        int id2 = BoardPositions.TOP.getValue();
        int id3 = BoardPositions.TOP_RIGHT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = (isMarked1 && isMarked2 && isMarked3) ? Matches.TOP_LEFT_TO_TOP_RIGHT : Matches.NONE;
        System.out.println("IDS: " + id1 + ", " + id2 + ", " + id3);
        return match;
    }

    private Matches checkMatches_LtoR() {
        int id1 = BoardPositions.LEFT.getValue();
        int id2 = BoardPositions.CENTER.getValue();
        int id3 = BoardPositions.RIGHT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = (isMarked1 && isMarked2 && isMarked3) ? Matches.LEFT_TO_RIGHT : Matches.NONE;
        System.out.println("IDS: " + id1 + ", " + id2 + ", " + id3);
        return match;
    }

    private Matches checkMatches_BLtoBR() {
        int id1 = BoardPositions.BOTTOM_LEFT.getValue();
        int id2 = BoardPositions.BOTTOM.getValue();
        int id3 = BoardPositions.BOTTOM_RIGHT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = (isMarked1 && isMarked2 && isMarked3) ? Matches.BOTTOM_LEFT_TO_BOTTOM_RIGHT : Matches.NONE;
        System.out.println("IDS: " + id1 + ", " + id2 + ", " + id3);
        return match;
    }

    public Matches getMatch() {
        return match;
    }

}
