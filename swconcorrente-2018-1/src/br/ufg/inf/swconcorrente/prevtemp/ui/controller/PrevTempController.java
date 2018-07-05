package br.ufg.inf.swconcorrente.prevtemp.ui.controller;

import br.ufg.inf.swconcorrente.prevtemp.ui.model.ServerInfo;
import br.ufg.inf.swconcorrente.prevtemp.ui.view.PrevTempConsoleView;
import br.ufg.inf.swconcorrente.utils.connections.ConnectorService;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;

public class PrevTempController extends ServerInfo {

    private final String DEFAULT_CBX_VALUE = "Selecione uma Cidade:";

    private PrevTempConsoleView view;
    private JLabel lblTitle;
    private JComboBox cbxCidades;
    private JTextArea txtRelatorio;
    private DefaultComboBoxModel boxModel;

    private String[] response;
    private int ARR_TOKEN = 1;
    private int ARR_SETUP = 2;
    private int ARR_SEPARATOR = 3;
    private int ARR_UDP_PORT = 4;

    private char[] keys;
    private String token;
    private String separator;
    private String udpPort;

    private ConnectorService socketCtrl;

    private Function1<? super String, Unit> onFailureCallback = (Function1<String, Unit>) s -> {
        JOptionPane.showMessageDialog(null, s);
        return null;
    };

    public PrevTempController() {
        super();
        initComponents();
        initConnection();
        startService();
    }

    public void showFrame() {
        view.setVisible(true);
    }

    public void presentMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private void initConnection() {
        socketCtrl = new ConnectorService(super.getHost(), super.getTcpPort());
    }

    private void initComponents() {
        view = new PrevTempConsoleView();

        lblTitle        = view.getLblTitle();
        cbxCidades      = view.getCbxCidades();
        txtRelatorio    = view.getTxtRelatorio();

        clearTxtArea();
        boxModel = (DefaultComboBoxModel) cbxCidades.getModel();
        boxModel.removeAllElements();
        boxModel.addElement(DEFAULT_CBX_VALUE);
    }

    private void startService() {
        try {
            String msg = "GET LOCALS";

            String request = msg + "\n";
            String response = socketCtrl.submit(request, null, onFailureCallback);

            if (
                    response != null &&
                    response.substring(0, 2).equals("OK")
            ) {
                writeLineOnTxtArea("CONEXÃO ESTABELECIDA COM SUCESSO!");
                String s = response;
                s = s.substring(3, response.length());
                s = s.replace("|", ", ");
                String[] arr = s.split(", ");
                ArrayList<String> cities = new ArrayList<>(Arrays.asList(arr));

                DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cbxCidades.getModel();
                for (String city : cities) boxModel.addElement(city);

                CitiesItemListener listener = new CitiesItemListener();
                cbxCidades.addItemListener(listener);
            } else {
                writeLineOnTxtArea("FALHA NA CONEXÃO! TENTE NOVAMENTE MAIS TARDE.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String buildRequest(String method, String[] params) {
        return method + "|" + (Arrays.toString(params).replace(", ", "|"));
    }

    private void writeLineOnTxtArea(String text) {
        txtRelatorio.append(text + "\n");
    }

    private void writeSeparator() {
        txtRelatorio.append("=============================================\n");
    }

    private void clearTxtArea() {
        txtRelatorio.setText("");
    }

    private void subscribeCity(String city) {
        if (city.equals(DEFAULT_CBX_VALUE)) {
            JOptionPane.showMessageDialog(null, "É necessário escolher uma cidade.");
        } else {
            System.out.println("ITEM SELECIONADO: " + city);
            String msg = "REGISTER " + city;
            String request = msg + "\n";
            String response = socketCtrl.submit(request);

            if (response != null && response.substring(0, 2).equals("OK")) {
                writeLineOnTxtArea(response);
                interpretResponse(response);
                writeLineOnTxtArea("TOKEN: " + token + "\n" +
                        "KEYS: " + Arrays.toString(keys) + "\n" +
                        "SEP: " + separator + "\n" +
                        "PORT: " + udpPort
                );
            } else {
                writeLineOnTxtArea("FALHA NA CONEXÃO! TENTE NOVAMENTE MAIS TARDE.");
            }
        }
    }

    private void interpretResponse(String response) {
        String auxResp = response.replace("|", ", ");
        this.response = auxResp.split(", ");

        token = this.response[ARR_TOKEN];
        keys = this.response[ARR_SETUP].toCharArray();
        separator = this.response[ARR_SEPARATOR];
        udpPort = this.response[ARR_UDP_PORT];
    }

    private void unsubscribeCity(String city) {
        System.out.println("ITEM DESSELECIONADO: " + city);
    }

    private class CitiesItemListener implements ItemListener {

        public void itemStateChanged(ItemEvent evt) {
            String city = evt.getItem().toString();

            if (evt.getStateChange() == ItemEvent.SELECTED) {
                subscribeCity(city);
            } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
                unsubscribeCity(city);
            }
        }

    }

}
