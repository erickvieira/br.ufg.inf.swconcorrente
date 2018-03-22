package br.ufg.inf.swconcorrente.jogodavelha;

import br.ufg.inf.swconcorrente.jogodavelha.ui.controller.JogoDaVelhaController;

import javax.swing.*;

public class Runner {

    private static JogoDaVelhaController controller;

    public static void main(String[] args) {
        newGame();

        controller.showJogoDaVelha();

        controller.getBtnReset().addActionListener(e -> {
            newGame();
        });
    }

    private static void newGame() {
        controller = new JogoDaVelhaController();
    }

}
