package br.ufg.inf.swconcorrente.decriptador.ui.controller;

import br.ufg.inf.swconcorrente.decriptador.ui.auxpkg.HintTextFieldUI;
import br.ufg.inf.swconcorrente.decriptador.ui.model.CifraDeCezar;
import br.ufg.inf.swconcorrente.decriptador.ui.view.DecriptadorConsoleView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by aluno on 27/03/18.
 */
public class DecriptadorController {

    private final String TXT_ACTION;
    private final String TXT_CLEAR;
    private final boolean ENCRIPT_MODE;

    private DecriptadorConsoleView view;
    private JTextArea txtOutput;
    private JTextField txtInput;
    private JButton btnEnviar;

    public DecriptadorController(final boolean ENCRIPT_MODE) {
        this.ENCRIPT_MODE = ENCRIPT_MODE;

        if (ENCRIPT_MODE) {
            TXT_ACTION = "ENCRIPT";
        } else {
            TXT_ACTION = "DECRIPT";
        }
        TXT_CLEAR = "CLEAR";

        setActions();
    }

    public void showDecriptador() {
        view.setVisible(true);
    }

    private void setActions() {
        initComponents();
        initUI();
        initListeners();
    }

    private void initComponents() {
        view = new DecriptadorConsoleView();

        btnEnviar   = view.getBtnEnviar();
        txtInput    = view.getTxtInput();
        txtOutput   = view.getTxtOutput();
    }

    private void initUI() {
        txtOutput.setEditable(false);
        HintTextFieldUI hint = new HintTextFieldUI("COLE O TEXTO AQUI", true);
        hint.setColor(Color.LIGHT_GRAY);
        txtInput.setUI(hint);
        btnEnviar.setText(TXT_ACTION);
    }

    private void initListeners() {
        btnEnviar.addActionListener(actionEvent -> {
            buttonAction();
        });
    }

    private void buttonAction() {
        if (btnEnviar.getText().equalsIgnoreCase(TXT_ACTION)) {
            String message = txtInput.getText();
            message = ENCRIPT_MODE ? new CifraDeCezar().decode(message) : new CifraDeCezar().encode(message);
            txtOutput.setText(message);
        } else {
            txtInput.setText("");
            txtOutput.setText("");
        }
        swapButtonText();
    }

    private void swapButtonText() {
        String txt = btnEnviar.getText();
        btnEnviar.setText(txt.equalsIgnoreCase(TXT_CLEAR) ? TXT_ACTION : TXT_CLEAR);
    }

}
