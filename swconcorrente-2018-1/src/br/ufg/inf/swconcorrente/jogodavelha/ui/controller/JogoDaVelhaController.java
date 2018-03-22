package br.ufg.inf.swconcorrente.jogodavelha.ui.controller;

import br.ufg.inf.swconcorrente.jogodavelha.ui.view.JogoDaVelhaTabView;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.BoardPositions;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.JogoDaVelha;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.Matches;
import br.ufg.inf.swconcorrente.jogodavelha.ui.model.PlayerMark;

import javax.swing.*;

public class JogoDaVelhaController {

    private JogoDaVelha jogoDaVelha;
    private PlayerMark currentPlayer;
    private int currentPosition;

    private static final int TL     = BoardPositions.TOP_LEFT.getValue();
    private static final int T      = BoardPositions.TOP.getValue();
    private static final int TR     = BoardPositions.TOP_RIGHT.getValue();
    private static final int L      = BoardPositions.LEFT.getValue();
    private static final int C      = BoardPositions.CENTER.getValue();
    private static final int R      = BoardPositions.RIGHT.getValue();
    private static final int BL     = BoardPositions.BOTTOM_LEFT.getValue();
    private static final int B      = BoardPositions.BOTTOM.getValue();
    private static final int BR     = BoardPositions.BOTTOM_RIGHT.getValue();
    private static final int NONE   = BoardPositions.NONE.getValue();

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

    private void initGame() {
        jogoDaVelha = new JogoDaVelha();
        currentPlayer = PlayerMark.NONE;
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
        setSign(btnTL);
        setSign(btnTop);
        setSign(btnTR);
        setSign(btnLeft);
        setSign(btnCenter);
        setSign(btnRight);
        setSign(btnBL);
        setSign(btnBottom);
        setSign(btnBR);
        currentPlayer = PlayerMark.X;
    }

    private void initListenners() {
        btnReset.addActionListener(e -> {
            initGame();
        });

        btnBR.addActionListener(e -> {
            swapPlayer();
            setSign(btnBR);
            currentPosition = BR;
            doLogicGame();
        });

        btnRight.addActionListener(e -> {
            swapPlayer();
            setSign(btnRight);
            currentPosition = R;
            doLogicGame();
        });

        btnTR.addActionListener(e -> {
            swapPlayer();
            setSign(btnTR);
            currentPosition = TR;
            doLogicGame();
        });

        btnBottom.addActionListener(e -> {
            swapPlayer();
            setSign(btnBottom);
            currentPosition = B;
            doLogicGame();
        });

        btnCenter.addActionListener(e -> {
            swapPlayer();
            setSign(btnCenter);
            currentPosition = C;
            doLogicGame();
        });

        btnTop.addActionListener(e -> {
            swapPlayer();
            setSign(btnTop);
            currentPosition = T;
            doLogicGame();
        });

        btnTL.addActionListener(e -> {
            swapPlayer();
            setSign(btnTL);
            currentPosition = TL;
            doLogicGame();
        });

        btnLeft.addActionListener(e -> {
            swapPlayer();
            setSign(btnLeft);
            currentPosition = L;
            doLogicGame();
        });

        btnBL.addActionListener(e -> {
            swapPlayer();
            setSign(btnBL);
            currentPosition = BL;
            doLogicGame();
        });
    }

    private void doLogicGame() {
        Matches match = jogoDaVelha.setSelected(currentPosition, currentPlayer);

        if (match != Matches.NONE) {
            JOptionPane.showMessageDialog(null, "FIM DE JOGO!\n " + getCurrentPlayerName() + " foi o vencedor.");
        }
    }

    private String getCurrentPlayerName() {
        return (currentPlayer == PlayerMark.X) ? "Player 1" : "Player 2";
    }

    private String getCurrentPlayerSign() {
        return currentPlayer.toString();
    }

    private void setSign(JButton btn) {
        btn.setText(getCurrentPlayerSign());
    }

    private void swapPlayer() {
        currentPlayer = (currentPlayer == PlayerMark.X) ? PlayerMark.O : PlayerMark.X;
    }

}
