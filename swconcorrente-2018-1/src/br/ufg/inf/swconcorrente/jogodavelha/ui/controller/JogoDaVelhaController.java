package br.ufg.inf.swconcorrente.jogodavelha.ui.controller;

import br.ufg.inf.swconcorrente.jogodavelha.ui.view.JogoDaVelhaTabView;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.BoardPositions;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.JogoDaVelha;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.Matches;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.PlayerSign;

import javax.swing.*;

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
    private JButton btnTL;
    private JButton btnTop;
    private JButton btnTR;
    private JButton btnLeft;
    private JButton btnCenter;
    private JButton btnRight;
    private JButton btnBL;
    private JButton btnBottom;
    private JButton btnBR;

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
        currentPosition = BoardPositions.NONE.getValue();
    }

    private void initComponets() {
        view = new JogoDaVelhaTabView();

        btnReset =      view.getBtnJVReset();
        btnTL =         view.getBtnTL();
        btnLeft =       view.getBtnLeft();
        btnBL =         view.getBtnBL();
        btnTop =        view.getBtnTop();
        btnCenter =     view.getBtnCenter();
        btnBottom =     view.getBtnBottom();
        btnTR =         view.getBtnTR();
        btnRight =      view.getBtnRight();
        btnBR =         view.getBtnBR();
    }

    private void initButtons() {
        setSign(btnTL, true);
        setSign(btnTop, true);
        setSign(btnTR, true);
        setSign(btnLeft, true);
        setSign(btnCenter, true);
        setSign(btnRight, true);
        setSign(btnBL, true);
        setSign(btnBottom, true);
        setSign(btnBR, true);
        currentPlayer = PlayerSign.X;
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    private void initListenners() {
        btnBR.addActionListener(e -> {
            swapPlayer();
            setSign(btnBR, false);
            currentPosition = BR;
            doLogicGame();
        });

        btnRight.addActionListener(e -> {
            swapPlayer();
            setSign(btnRight, false);
            currentPosition = R;
            doLogicGame();
        });

        btnTR.addActionListener(e -> {
            swapPlayer();
            setSign(btnTR, false);
            currentPosition = TR;
            doLogicGame();
        });

        btnBottom.addActionListener(e -> {
            swapPlayer();
            setSign(btnBottom, false);
            currentPosition = B;
            doLogicGame();
        });

        btnCenter.addActionListener(e -> {
            swapPlayer();
            setSign(btnCenter, false);
            currentPosition = C;
            doLogicGame();
        });

        btnTop.addActionListener(e -> {
            swapPlayer();
            setSign(btnTop, false);
            currentPosition = T;
            doLogicGame();
        });

        btnTL.addActionListener(e -> {
            swapPlayer();
            setSign(btnTL, false);
            currentPosition = TL;
            doLogicGame();
        });

        btnLeft.addActionListener(e -> {
            swapPlayer();
            setSign(btnLeft, false);
            currentPosition = L;
            doLogicGame();
        });

        btnBL.addActionListener(e -> {
            swapPlayer();
            setSign(btnBL, false);
            currentPosition = BL;
            doLogicGame();
        });
    }

    private void doLogicGame() {
        Matches match = jogoDaVelha.setSelected(currentPosition, currentPlayer);
        turnsCount++;

        if (match != Matches.NONE) {
            JOptionPane.showMessageDialog(null, "FIM DE JOGO!\n" + getCurrentPlayerName() + " foi o vencedor.");
        } else {
            if (turnsCount == TURNS_LIMIT) {
                JOptionPane.showMessageDialog(null, "IH! DEU VELHA. :(");
            }
        }
        
        System.out.printf("" + turnsCount);
    }

    private String getCurrentPlayerName() {
        return (currentPlayer == PlayerSign.O) ? "Player 1" : "Player 2";
    }

    private String getCurrentPlayerSign() {
        return currentPlayer.toString();
    }

    private void setSign(JButton btn, boolean enabled) {
        btn.setText(getCurrentPlayerSign());
        btn.setEnabled(enabled);
    }

    private void resetSign(JButton btn) {
        btn.setText(PlayerSign.NONE.toString());
    }

    private void swapPlayer() {
        currentPlayer = (currentPlayer == PlayerSign.X) ? PlayerSign.O : PlayerSign.X;
    }

}
