package br.ufg.inf.swconcorrente.jogodavelha;

import javax.swing.*;
import java.util.ArrayList;

public class JogoDaVelha {

    private JPanel tabuleiro;
    private JButton btntl;
    private JButton btnleft;
    private JButton btnbl;
    private JButton btncenter;
    private JButton btntop;
    private JButton btnbottom;
    private JButton btntr;
    private JButton btnright;
    private JButton btnbr;
    private JButton btnreset;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 650;

    public JogoDaVelha() {
    }

    public JButton getResetButton() {
        return btnreset;
    }

    private void createUIComponents() {
        btnreset = new JButton("");
        tabuleiro = new JPanel();
    }

    private void $$$setupUI$$$() {
        createUIComponents();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("JogoDaVelha");
        frame.setVisible(true);
    }

    private class LogicGame {

        public final int BOARD_GAME_LIMIT = 9;
        public ArrayList<Player> boardGame;

        public void LogicGame() {
            this.boardGame = new ArrayList<Player>(BOARD_GAME_LIMIT);
        }

        public void doLogicGame(final int position) {

        }

    }

    public enum Player { X, O }

}
