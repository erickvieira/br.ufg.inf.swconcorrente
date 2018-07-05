package br.ufg.inf.swconcorrente.utils.connections;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectorService {

    private Socket tcpSocket;
    private DatagramSocket udpSocket;
    private TCPListener staticListener;
    private UDPListener dynamicListener;

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

    public ConnectorService(final String host, final int port, final boolean isUdp) {
        try {
            InetAddress address = InetAddress.getByName(host);
            tcpSocket = new Socket(address, port);
            udpSocket = new DatagramSocket(port, address);

            dynamicListener = new UDPListener(udpSocket);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public String submit(String request) {
        return staticListener.makeRequest(request, null, null);
    }

    public String submit(
            String request,
            Function1<? super String, Unit> onSuccess,
            Function1<? super String, Unit> onFailure
    ) {
        return staticListener.makeRequest(request, onSuccess, onFailure);
    }

    public void subscribe(String request) {
        dynamicListener.makeRequest(request, null, null);
    }

    public void subscribe(
            String request,
            Function1<? super String, Unit> onSuccess,
            Function1<? super String, Unit> onFailure
    ) {
        dynamicListener.makeRequest(request, onSuccess, onFailure);
    }

}
