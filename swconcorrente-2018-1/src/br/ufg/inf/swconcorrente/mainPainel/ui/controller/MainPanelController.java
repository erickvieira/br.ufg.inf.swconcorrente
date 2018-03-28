package br.ufg.inf.swconcorrente.mainPainel.ui.controller;

import br.ufg.inf.swconcorrente.jogodavelha.ui.controller.JogoDaVelhaController;
import br.ufg.inf.swconcorrente.mainPainel.ui.view.MainPainelListView;

import javax.swing.*;

/**
 * Created by aluno on 27/03/18.
 */
public class MainPanelController {

    private MainPainelListView view;
    private JButton btnJDV;
    private JButton btnDecript;
    private JLabel mainLabel;

    public MainPanelController() {
        setActions();
    }

    private void setActions() {
        initComponents();
        initButtons();
        initListeners();
    }

    public void showMainPainel() {
        view.setVisible(true);
    }

    private void initComponents() {
        view = new MainPainelListView();

        mainLabel =     view.getMainLabel();
        btnJDV =        view.getBtnJDV();
        btnDecript =    view.getBtnDecript();
    }

    private void initButtons() {
        // TODO: ESTILIZAR OS BOTÕES NA ENTRADA
    }

    private void initListeners() {
        btnJDV.addActionListener(actionEvent -> {
            new JogoDaVelhaController().showJogoDaVelha();
        });

        btnDecript.addActionListener(actionEvent -> {
            // TODO: DECRIPTADOR CALLER
        });
    }

}
