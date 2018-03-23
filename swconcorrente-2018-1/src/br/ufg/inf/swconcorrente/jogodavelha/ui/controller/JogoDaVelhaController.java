package br.ufg.inf.swconcorrente.jogodavelha.ui.controller;

import br.ufg.inf.swconcorrente.jogodavelha.ui.view.JogoDaVelhaTabView;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.BoardPositions;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.JogoDaVelha;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.Matches;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.PlayerSign;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JogoDaVelhaController {

    private JogoDaVelha jogoDaVelha;
    private PlayerSign currentPlayer;
    private int currentPosition;

    private static final int TURNS_LIMIT = 9;
    private int turnsCount = 0;

    private static final int TL     = BoardPositions.TOP_LEFT.getValue();
    private static final int T      = BoardPositions.TOP.getValue();
    private static final int TR     = BoardPositions.TOP_RIGHT.getValue();
    private static final int L      = BoardPositions.LEFT.getValue();
    private static final int C      = BoardPositions.CENTER.getValue();
    private static final int R      = BoardPositions.RIGHT.getValue();
    private static final int BL     = BoardPositions.BOTTOM_LEFT.getValue();
    private static final int B      = BoardPositions.BOTTOM.getValue();
    private static final int BR     = BoardPositions.BOTTOM_RIGHT.getValue();

    private JogoDaVelhaTabView view;
    private JButton btnReset;
    private ArrayList<JButton> boardButtons;
    private JLabel player1;
    private JLabel player2;

    public JogoDaVelhaController() {
        initGame();
        initComponets();
        initButtons();
        initListenners();
    }

    public void showJogoDaVelha() {
        view.setVisible(true);
    }

    private void restartGame() {
        new JogoDaVelhaController();
    }

    private void initGame() {
        jogoDaVelha = new JogoDaVelha();
        currentPlayer = PlayerSign.X;
        currentPosition = BoardPositions.NONE.getValue();
    }

    private void initComponets() {
        view = new JogoDaVelhaTabView();

        btnReset =      view.getBtnJVReset();
        boardButtons =  view.getButtons();
        player1 =       view.getLblPlayer1();
        player2 =       view.getLblPlayer2();
    }

    private void initButtons() {
        initButtonSign(boardButtons.get(TL));
        initButtonSign(boardButtons.get(T));
        initButtonSign(boardButtons.get(TR));
        initButtonSign(boardButtons.get(L));
        initButtonSign(boardButtons.get(C));
        initButtonSign(boardButtons.get(R));
        initButtonSign(boardButtons.get(BL));
        initButtonSign(boardButtons.get(B));
        initButtonSign(boardButtons.get(BR));
        currentPlayer = PlayerSign.X;
        player1.setForeground(Color.RED);
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    private void initListenners() {
        initIndividualButtonListener();
    }

    private void initIndividualButtonListener() {
        for (JButton button: boardButtons) {
            button.addActionListener(actionEvent -> {
                setButtonSign(button);
                currentPosition = boardButtons.indexOf(button);
                doLogicGame();
                swapPlayer();
            });
        }
    }

    private void gameOver() {
        for (JButton button: boardButtons) {
            if(button.isEnabled()) {
                button.setEnabled(false);
            }
        }
        JOptionPane.showMessageDialog(null, "FIM DE JOGO!\n" + getCurrentPlayerName() + " foi o vencedor.");
    }

    private void doLogicGame() {
        Matches match = jogoDaVelha.setSelected(currentPosition, currentPlayer);
        turnsCount++;

        if (match != Matches.NONE) {
            gameOver();
        } else {
            if (turnsCount == TURNS_LIMIT) {
                JOptionPane.showMessageDialog(null, "IH! DEU VELHA. :(");
            }
        }
        
        System.out.println("" + turnsCount);
    }

    private String getCurrentPlayerName() {
        return (currentPlayer == PlayerSign.O) ? "Player 1" : "Player 2";
    }

    private String getCurrentPlayerSign() {
        return currentPlayer.toString();
    }

    private void setButtonSign(JButton btn) {
        btn.setText(getCurrentPlayerSign());
        btn.setEnabled(false);
    }

    private void initButtonSign(JButton btn) {
        btn.setText(PlayerSign.NONE.toString());
        btn.setEnabled(true);
    }

    private void resetSign(JButton btn) {
        btn.setText(PlayerSign.NONE.toString());
    }

    private void swapPlayer() {
        boolean currentPlayer_isX = (currentPlayer == PlayerSign.O);

        player1.setForeground(currentPlayer_isX ? Color.RED : Color.BLACK);
        player2.setForeground(currentPlayer_isX ? Color.BLACK : Color.RED);

        currentPlayer = currentPlayer_isX ? PlayerSign.X : PlayerSign.O;
    }

}
