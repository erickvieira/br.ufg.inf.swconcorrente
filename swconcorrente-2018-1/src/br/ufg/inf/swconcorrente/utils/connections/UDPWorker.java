package br.ufg.inf.swconcorrente.utils.connections;

import br.ufg.inf.swconcorrente.prevtemp.ui.model.ServerInfo;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Objects;

public class UDPWorker extends SwingWorker<String, String> implements IConnector {

    private DatagramSocket sendSocket;
    private DatagramSocket receiveSocket;
    private String request;
    private Function1<? super String, Unit> onSuccess;
    private Function1<? super String, Unit> onFailure;
    private DatagramPacket sendPkt;
    private DatagramPacket receivePkt;

    public UDPWorker(
            DatagramSocket sendSocket,
            DatagramSocket receiveSocket,
            String request,
            Function1<? super String, Unit> onSuccess,
            Function1<? super String, Unit> onFailure
    ) {
        this.sendSocket = sendSocket;
        this.receiveSocket = receiveSocket;
        this.request = request;
        this.onSuccess = onSuccess;
        this.onFailure = onFailure;

        try {
            this.receiveSocket.setSoTimeout(30000);
        } catch (SocketException e) {
            failCallback(e.getMessage());
        }
    }

    @Override
    public void okCallback(String msg) {
        if (onSuccess != null) onSuccess.invoke(msg);
    }

    @Override
    public void failCallback(String msg) {
        if (onFailure != null) onFailure.invoke(msg);
    }

    @Nullable
    @Override
    public String makeRequest(
            @Nullable DatagramSocket receiveSocket,
            @NotNull String request,
            @Nullable Function1<? super String, Unit> onSuccess,
            @Nullable Function1<? super String, Unit> onFailure
    ) {
        String response = "ERROR";

        try {
            ServerInfo srv = new ServerInfo();
            InetAddress address = InetAddress.getByName(srv.getHost());

            byte[] sendData = formatRequest(request);
            sendPkt = new DatagramPacket(sendData, sendData.length, address, srv.getUdpPort());
            sendSocket.send(sendPkt);

            byte[] receiveData = new byte[1024];
            receivePkt = new DatagramPacket(receiveData, receiveData.length);
            response = new String(receivePkt.getData());
            System.out.println(response);
            okCallback(response);

            while (true) {
                Objects.requireNonNull(this.receiveSocket).receive(receivePkt);
                response = new String(receivePkt.getData());
                failCallback((response.isEmpty()) ? "[E] ERRO NA COMUNICAÇÃO" : response);

                if (response.contains("ERROR")) {
                    failCallback(response);
                } else {
                    okCallback(response);
                }
            }
        } catch (Exception e) {
            failCallback(e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    private byte[] formatRequest(String request) {
        return request.getBytes();
    }

    @Override
    public String doInBackground() {
        return makeRequest(receiveSocket, request, onSuccess, onFailure);
    }

    @Override
    public void closeConn() {
        try {
            if (!sendSocket.isClosed()) {
                sendSocket.close();
            }
            if (!receiveSocket.isClosed()) {
                receiveSocket.close();
            }
        } catch (Exception ignored) { }
    }
}
