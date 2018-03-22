package br.ufg.inf.swconcorrente.jogodavelha.ui;

import javax.swing.*;
import java.awt.*;

public class JogoDaVelhaTabView extends JFrame {

    private static final int WIDTH = 380;
    private static final int HEIGHT = 560;
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

    public JogoDaVelhaTabView() {
        setSize(WIDTH, HEIGHT);
        setContentPane(boardPanel);
        setLocationRelativeTo(null);
    }

    public JButton getBtnTL() {
        return btnTL;
    }

    public void setBtnTL(JButton btnTL) {
        this.btnTL = btnTL;
    }

    public JButton getBtnTop() {
        return btnTop;
    }

    public void setBtnTop(JButton btnTop) {
        this.btnTop = btnTop;
    }

    public JButton getBtnTR() {
        return btnTR;
    }

    public void setBtnTR(JButton btnTR) {
        this.btnTR = btnTR;
    }

    public JButton getBtnLeft() {
        return btnLeft;
    }

    public void setBtnLeft(JButton btnLeft) {
        this.btnLeft = btnLeft;
    }

    public JButton getBtnCenter() {
        return btnCenter;
    }

    public void setBtnCenter(JButton btnCenter) {
        this.btnCenter = btnCenter;
    }

    public JButton getBtnRight() {
        return btnRight;
    }

    public void setBtnRight(JButton btnRight) {
        this.btnRight = btnRight;
    }

    public JButton getBtnBL() {
        return btnBL;
    }

    public void setBtnBL(JButton btnBL) {
        this.btnBL = btnBL;
    }

    public JButton getBtnBottom() {
        return btnBottom;
    }

    public void setBtnBottom(JButton btnBottom) {
        this.btnBottom = btnBottom;
    }

    public JButton getBtnBR() {
        return btnBR;
    }

    public void setBtnBR(JButton btnBR) {
        this.btnBR = btnBR;
    }

    public JButton getBtnJVReset() {
        return btnJVReset;
    }

    public void setBtnJVReset(JButton btnJVReset) {
        this.btnJVReset = btnJVReset;
    }

}
