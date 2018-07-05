package br.ufg.inf.swconcorrente.prevtemp.ui.view;

import javax.swing.*;

/**
 * Created by aluno on 03/07/18.
 */
public class PrevTempConsoleView extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 460;

    private JPanel mainPanel;
    private JLabel lblTitle;
    private JComboBox cbxCidades;
    private JTextArea txtRelatorio;

    public PrevTempConsoleView() {
        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public JComboBox getCbxCidades() {
        return cbxCidades;
    }

    public JTextArea getTxtRelatorio() {
        txtRelatorio.setRows(16);
        txtRelatorio.setRows(60);
        return txtRelatorio;
    }

}
