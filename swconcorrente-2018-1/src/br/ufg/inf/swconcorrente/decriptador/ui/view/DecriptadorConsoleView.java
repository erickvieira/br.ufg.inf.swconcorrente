package br.ufg.inf.swconcorrente.decriptador.ui.view;

import javax.swing.*;

/**
 * Created by aluno on 27/03/18.
 */
public class DecriptadorConsoleView extends JFrame {

    private static final int WIDTH = 650;
    private static final int HEIGHT = 560;

    private JTextArea txtOutput;
    private JTextField txtInput;
    private JButton btnEnviar;
    private JLabel lblTitle;
    private JPanel dcrptPanel;

    public DecriptadorConsoleView() {
        setSize(WIDTH, HEIGHT);
        setContentPane(dcrptPanel);
        setLocationRelativeTo(null);
    }

    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public JTextArea getTxtOutput() {
        txtOutput.setRows(16);
        txtOutput.setRows(60);
        return txtOutput;
    }

    public JTextField getTxtInput() {
        return txtInput;
    }

}
