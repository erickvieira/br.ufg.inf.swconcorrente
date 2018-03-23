package br.ufg.inf.swconcorrente.jogodavelha.ui.controller;

import br.ufg.inf.swconcorrente.jogodavelha.ui.view.JogoDaVelhaTabView;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.BoardMap;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.JogoDaVelha;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.MatchMap;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.PlayerSign;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JogoDaVelhaController {

    private JogoDaVelha jogoDaVelha;
    private PlayerSign currentPlayer;
    private int currentPosition;

    private static final int TURNS_LIMIT = 9;
    private int turnsCount = 0;

    private static final int TL     = BoardMap.TOP_LEFT.getValue();
    private static final int T      = BoardMap.TOP.getValue();
    private static final int TR     = BoardMap.TOP_RIGHT.getValue();
    private static final int L      = BoardMap.LEFT.getValue();
    private static final int C      = BoardMap.CENTER.getValue();
    private static final int R      = BoardMap.RIGHT.getValue();
    private static final int BL     = BoardMap.BOTTOM_LEFT.getValue();
    private static final int B      = BoardMap.BOTTOM.getValue();
    private static final int BR     = BoardMap.BOTTOM_RIGHT.getValue();

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
        currentPosition = BoardMap.NONE.getValue();
    }

    private void initComponets() {
        view = new JogoDaVelhaTabView();

        btnReset =      view.getBtnJVReset();
        boardButtons =  view.getButtons();
        player1 =       view.getLblPlayer1();
        player2 =       view.getLblPlayer2();
    }

    private void initButtons() {
        for (JButton button: boardButtons) {
            initButtonSign(button);
        }
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
                swapPlayer();
                setButtonSign(button);
                currentPosition = boardButtons.indexOf(button);
                System.out.println("[" + currentPlayer + ", " + currentPosition + "]");
                doLogicGame();
                swapPlayerColor();
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
        MatchMap match = jogoDaVelha.setSelected(currentPosition, currentPlayer);
        turnsCount++;

        if (match != MatchMap.NONE) {
            gameOver();
        } else {
            if (turnsCount == TURNS_LIMIT) {
                JOptionPane.showMessageDialog(null, "IH! DEU VELHA. :(");
            }
        }
        
        System.out.println("" + turnsCount);
    }

    @NotNull
    @Contract(pure = true)
    private String getCurrentPlayerName() {
        return (currentPlayer == PlayerSign.O) ? "Player 1" : "Player 2";
    }

    @Contract(pure = true)
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
        boolean currentPlayer_isO = (currentPlayer == PlayerSign.O);

        currentPlayer = currentPlayer_isO ? PlayerSign.X : PlayerSign.O;
    }

    private void swapPlayerColor() {
        boolean currentPlayer_isO = (currentPlayer == PlayerSign.O);

        player1.setForeground(currentPlayer_isO ? Color.RED : Color.BLACK);
        player2.setForeground(currentPlayer_isO ? Color.BLACK : Color.RED);
    }

}
