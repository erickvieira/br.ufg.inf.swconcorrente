package br.ufg.inf.swconcorrente.jogodavelha.ui.view;

import br.ufg.inf.swconcorrente.jogodavelha.ui.model.BoardMap;

import javax.swing.*;
import java.util.ArrayList;

public class JogoDaVelhaTabView extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 560;
    private static final int BOARD_LIMIT = 9;

    private static final int TL     = BoardMap.TOP_LEFT.getPosition();
    private static final int T      = BoardMap.TOP.getPosition();
    private static final int TR     = BoardMap.TOP_RIGHT.getPosition();
    private static final int L      = BoardMap.LEFT.getPosition();
    private static final int C      = BoardMap.CENTER.getPosition();
    private static final int R      = BoardMap.RIGHT.getPosition();
    private static final int BL     = BoardMap.BOTTOM_LEFT.getPosition();
    private static final int B      = BoardMap.BOTTOM.getPosition();
    private static final int BR     = BoardMap.BOTTOM_RIGHT.getPosition();

    private JPanel boardPanel;
    private JButton btnJVReset;
    private JButton btnTL;
    private JButton btnTop;
    private JButton btnTR;
    private JButton btnLeft;
    private JButton btnCenter;
    private JButton btnRight;
    private JButton btnBL;
    private JButton btnBottom;
    private JButton btnBR;
    private JLabel lblPlayer1;
    private JLabel lblPlayer2;
    private ArrayList<JButton> boardButtons;

    public JogoDaVelhaTabView() {
        setSize(WIDTH, HEIGHT);
        setContentPane(boardPanel);
        setLocationRelativeTo(null);
        initButtons();
    }

    private void initButtons() {
        boardButtons = new ArrayList<>(BOARD_LIMIT);

        boardButtons.add(TL, btnTL);
        boardButtons.add(T, btnTop);
        boardButtons.add(TR, btnTR);
        boardButtons.add(L, btnLeft);
        boardButtons.add(C, btnCenter);
        boardButtons.add(R, btnRight);
        boardButtons.add(BL, btnBL);
        boardButtons.add(B, btnBottom);
        boardButtons.add(BR, btnBR);
    }

    public ArrayList<JButton> getButtons() {
        return boardButtons;
    }

    public JButton getBtnJVReset() {
        return btnJVReset;
    }

    public JLabel getLblPlayer1() {
        return lblPlayer1;
    }

    public JLabel getLblPlayer2() {
        return lblPlayer2;
    }

}
