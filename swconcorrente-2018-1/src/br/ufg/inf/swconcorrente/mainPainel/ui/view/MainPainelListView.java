package br.ufg.inf.swconcorrente.mainPainel.ui.view;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by aluno on 27/03/18.
 */
public class MainPainelListView extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 560;

    private JPanel mainPanel;
    private JLabel mainLabel;
    private JButton btnJDV;
    private JButton btnDecript;

    public MainPainelListView() {
        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }

    public JButton getBtnJDV() {
        return btnJDV;
    }

    public JButton getBtnDecript() {
        return btnDecript;
    }

    public JLabel getMainLabel() {
        return mainLabel;
    }

}
