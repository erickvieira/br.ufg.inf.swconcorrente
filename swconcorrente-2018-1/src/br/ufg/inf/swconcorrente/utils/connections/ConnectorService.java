package br.ufg.inf.swconcorrente.utils.connections;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectorService {

    private Socket tcpSocket;
    private DatagramSocket udpPostman;
    private DatagramSocket udpMailbox;
    private TCPListener staticListener;
    private UDPWorker dynamicListener;

    public ConnectorService(final String host, final int port) {
        try {
            InetAddress address = InetAddress.getByName(host);
            tcpSocket = new Socket(address, port);

            staticListener = new TCPListener(tcpSocket);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public ConnectorService(final int receivePort) {
        try {
            udpPostman = new DatagramSocket();
            udpMailbox = new DatagramSocket(receivePort);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public String submit(
            String request,
            Function1<? super String, Unit> onSuccess,
            Function1<? super String, Unit> onFailure
    ) {
        return staticListener.makeRequest(null, request, onSuccess, onFailure);
    }

    public void subscribe(
            String request,
            Function1<? super String, Unit> onSuccess,
            Function1<? super String, Unit> onFailure
    ) {
        dynamicListener = new UDPWorker(udpMailbox, udpPostman, request, onSuccess, onFailure);
        dynamicListener.execute();
    }

    public void unsubscribe() {
        if (dynamicListener != null) {
            dynamicListener.cancel(true);
            dynamicListener = null;
        }
    }

}
