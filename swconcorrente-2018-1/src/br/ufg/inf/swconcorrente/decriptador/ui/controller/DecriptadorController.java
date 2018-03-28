package br.ufg.inf.swconcorrente.decriptador.ui.controller;

import br.ufg.inf.swconcorrente.decriptador.ui.view.DecriptadorConsoleView;

import javax.swing.*;

/**
 * Created by aluno on 27/03/18.
 */
public class DecriptadorController {

    private DecriptadorConsoleView view;
    private JTextArea txtOutput;
    private JTextField txtInput;
    private JButton btnEnviar;
    private JLabel lblTitle;

    public DecriptadorController() {

    }

    private void setActions() {
        initComponents();
        initButtons();
        initListeners();
    }

    private void initComponents() {
        view = new DecriptadorConsoleView();

        btnEnviar   = view.getBtnEnviar();
        txtInput    = view.getTxtInput();
        txtOutput   = view.getTxtOutput();
        lblTitle    = view.getLblTitle();
    }

    private void initButtons() {
        // TODO: BUTTON SWAP TEXT
    }

    private void initListeners() {
        btnEnviar.addActionListener(actionEvent -> {
            // TODO: DECRIPTER
        });
    }

}
