package br.ufg.inf.swconcorrente.jogodavelha.ui.model;


import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.ArrayList;

public class JogoDaVelha {

    private static final int BOARD_LIMIT = 9;
    private ArrayList<PlayerMark> boardGame;
    private Matches match;
    private int currentPosition;
    private PlayerMark currentPlayer;

    public JogoDaVelha() {
        initBoardGame();
        match = Matches.NONE;
        currentPlayer = PlayerMark.NONE;
        currentPosition = BoardPositions.NONE.getValue();
    }
    
    private void initBoardGame() {
        boardGame = new ArrayList<>(BOARD_LIMIT);

        for (int i = 0; i < BOARD_LIMIT; i++) {
            boardGame.add(PlayerMark.NONE);
        }
    }
    
    public Matches setSelected(int position, PlayerMark player) {
        currentPosition = position;
        currentPlayer = player;
        boardGame.set(position, player);
        checkMatches();

        return match;
    }

    public boolean checkMatches() {
        return (checkMatches_TLtoBR()       != Matches.NONE
                && checkMatches_TtoB()      != Matches.NONE
                && checkMatches_TLtoBL()    != Matches.NONE
                && checkMatches_TRtoBR()    != Matches.NONE
                && checkMatches_TRtoBL()    != Matches.NONE
                && checkMatches_TLtoTR()    != Matches.NONE
                && checkMatches_LtoR()      != Matches.NONE
                && checkMatches_BLtoBR()    != Matches.NONE);
    }

    public boolean checkIsMarked(int position, PlayerMark mark) {
        return boardGame.get(position) == mark;
    }

    private Matches checkMatches_TLtoBR() {
        int id1 = BoardPositions.TOP_LEFT.getValue();
        int id2 = BoardPositions.CENTER.getValue();
        int id3 = BoardPositions.BOTTOM_RIGHT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = Matches.TOP_LEFT_TO_BOTTOM_RIGHT;
        return (isMarked1 && isMarked2 && isMarked3) ? Matches.TOP_LEFT_TO_BOTTOM_RIGHT : Matches.NONE;
    }

    private Matches checkMatches_TtoB() {
        int id1 = BoardPositions.TOP.getValue();
        int id2 = BoardPositions.CENTER.getValue();
        int id3 = BoardPositions.BOTTOM.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = Matches.TOP_TO_BOTTOM;
        return (isMarked1 && isMarked2 && isMarked3) ? Matches.TOP_TO_BOTTOM : Matches.NONE;
    }

    private Matches checkMatches_TLtoBL() {
        int id1 = BoardPositions.TOP_LEFT.getValue();
        int id2 = BoardPositions.LEFT.getValue();
        int id3 = BoardPositions.BOTTOM_LEFT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = Matches.TOP_LEFT_TO_BOTTOM_LEFT;
        return (isMarked1 && isMarked2 && isMarked3) ? Matches.TOP_LEFT_TO_BOTTOM_LEFT : Matches.NONE;
    }

    private Matches checkMatches_TRtoBR() {
        int id1 = BoardPositions.TOP_RIGHT.getValue();
        int id2 = BoardPositions.RIGHT.getValue();
        int id3 = BoardPositions.BOTTOM_RIGHT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = Matches.TOP_RIGHT_TO_BOTTOM_RIGHT;
        return (isMarked1 && isMarked2 && isMarked3) ? Matches.TOP_RIGHT_TO_BOTTOM_RIGHT : Matches.NONE;
    }

    private Matches checkMatches_TRtoBL() {
        int id1 = BoardPositions.TOP_RIGHT.getValue();
        int id2 = BoardPositions.CENTER.getValue();
        int id3 = BoardPositions.BOTTOM_LEFT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = Matches.TOP_RIGHT_TO_BOTTOM_LEFT;
        return (isMarked1 && isMarked2 && isMarked3) ? Matches.TOP_RIGHT_TO_BOTTOM_LEFT : Matches.NONE;
    }

    private Matches checkMatches_TLtoTR() {
        int id1 = BoardPositions.TOP_LEFT.getValue();
        int id2 = BoardPositions.TOP.getValue();
        int id3 = BoardPositions.TOP_RIGHT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = Matches.TOP_LEFT_TO_TOP_RIGHT;
        return (isMarked1 && isMarked2 && isMarked3) ? Matches.TOP_LEFT_TO_TOP_RIGHT : Matches.NONE;
    }

    private Matches checkMatches_LtoR() {
        int id1 = BoardPositions.LEFT.getValue();
        int id2 = BoardPositions.CENTER.getValue();
        int id3 = BoardPositions.RIGHT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = Matches.LEFT_TO_RIGHT;
        return (isMarked1 && isMarked2 && isMarked3) ? Matches.LEFT_TO_RIGHT : Matches.NONE;
    }

    private Matches checkMatches_BLtoBR() {
        int id1 = BoardPositions.BOTTOM_LEFT.getValue();
        int id2 = BoardPositions.BOTTOM.getValue();
        int id3 = BoardPositions.BOTTOM_RIGHT.getValue();

        boolean isMarked1 = checkIsMarked(id1, currentPlayer);
        boolean isMarked2 = checkIsMarked(id2, currentPlayer);
        boolean isMarked3 = checkIsMarked(id3, currentPlayer);

        match = Matches.BOTTOM_LEFT_TO_BOTTOM_RIGHT;
        return (isMarked1 && isMarked2 && isMarked3) ? Matches.BOTTOM_LEFT_TO_BOTTOM_RIGHT : Matches.NONE;
    }

    public Matches getMatch() {
        return match;
    }

}
