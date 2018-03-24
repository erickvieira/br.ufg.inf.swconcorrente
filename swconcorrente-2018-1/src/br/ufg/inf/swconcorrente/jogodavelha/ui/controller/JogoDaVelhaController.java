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
        currentPlayer = PlayerSign.NONE;
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
                turnsCount++;
                swapPlayer();
                setButtonSign(button);
                currentPosition = boardButtons.indexOf(button);
                System.out.println("CLICKADO [" + currentPlayer + ", " + currentPosition + "]");
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
        return (currentPlayer == PlayerSign.X) ? "Player 1" : "Player 2";
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
        System.out.println("CURRENT_PLAYER [" + currentPlayer + "]");
        currentPlayer = (currentPlayer == PlayerSign.X) ? PlayerSign.O : PlayerSign.X;
    }

    private void swapPlayerColor() {
        player1.setForeground(currentPlayer == PlayerSign.X ? Color.RED : Color.BLACK);
        player2.setForeground(currentPlayer == PlayerSign.X ? Color.BLACK : Color.RED);
    }

}
