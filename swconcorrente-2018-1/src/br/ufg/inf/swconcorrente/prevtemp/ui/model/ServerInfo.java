package br.ufg.inf.swconcorrente.prevtemp.ui.model;

public class ServerInfo {

    private final String HOST;
    private final int TCP_PORT;
    private final int UDP_PORT;

    public ServerInfo() {
        HOST = "192.168.1.122";
        TCP_PORT = 12345;
        UDP_PORT = 1234;
    }

    public String getHost() {
        return HOST;
    }

    public int getTcpPort() {
        return TCP_PORT;
    }

    public int getUdpPort() {
        return UDP_PORT;
    }

    public String getFullInfo() {
        return HOST + ":" + TCP_PORT;
    }

}
