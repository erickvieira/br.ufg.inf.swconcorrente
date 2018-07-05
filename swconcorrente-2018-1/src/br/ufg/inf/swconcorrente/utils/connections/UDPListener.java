package br.ufg.inf.swconcorrente.utils.connections;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UDPListener extends SwingWorker<Boolean, String> implements IConnector {

    private DatagramSocket socket;
    private DatagramPacket sendPkt;
    private DatagramPacket receivePkt;

    public UDPListener(DatagramSocket socket) {
        this.socket = socket;
    }

    @Nullable
    @Override
    public Unit makeRequest(
            @NotNull String request,
            @Nullable Function1<? super String, Unit> onSuccess,
            @Nullable Function1<? super String, Unit> onFailure
    ) {
        try {
            String response = "ERROR";
            while (true) {
                byte[] sendData = formatRequest(request);
                sendPkt = new DatagramPacket(sendData, sendData.length, socket.getInetAddress(), socket.getPort());
                socket.send(sendPkt);

                byte[] receiveData = new byte[1024];
                receivePkt = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePkt);

                response = Arrays.toString(receivePkt.getData()).replace(", ", "");

                if (response.substring(0, 2).equals("OK")) {
                    if (onSuccess != null) {
                        onSuccess.invoke(response);
                    }
                } else {
                    if (onSuccess != null) {
                        onFailure.invoke(response);
                    }
                }
            }
        } catch (Exception e) {
            assert onFailure != null;
            onFailure.invoke(e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private byte[] formatRequest(String reques) {
        return reques.getBytes();
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        String prevMessage = "";

        while (true) {
            System.out.println("FODA-SE");
        }
    }

    @Override
    public void closeConn() {

    }
}
