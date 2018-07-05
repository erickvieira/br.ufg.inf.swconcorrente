package br.ufg.inf.swconcorrente.utils.connections;

import java.io.IOException;
import java.net.*;

public class Stuff extends Thread{


    private final String token;
    private final int port;
    private final String dadosSequencia;
    private final String separador;

    public Stuff(String token, int port, String dadosSequencia, String separador){
        this.token = token;
        this.port = port;
        this.dadosSequencia = dadosSequencia;
        this.separador = separador;
    }

    public void run() {
        DatagramSocket socketEnvio;

        try {
            socketEnvio = new DatagramSocket();

            byte[] bytes = this.token.getBytes();

            InetAddress inetAddress = InetAddress.getByName("192.168.1.122");
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, inetAddress, 1234) ;
            socketEnvio.send(datagramPacket);


            byte[] bytes1 = new byte[1024];
            DatagramPacket datagramPacket1 = new DatagramPacket(bytes1, bytes1.length);
            DatagramSocket socketRecebimento = new DatagramSocket(this.port);

            String mensagem = new String(datagramPacket1.getData());
            System.out.println("C: Result is " + mensagem);

            while(true){
                socketRecebimento.receive(datagramPacket1);
                mensagem = new String(datagramPacket1.getData());
                /**
                 * mostra os resultados
                 */
                printResultado(mensagem);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Ordena a mensagem de acordo com o formato recebido
     * @param dadosTemperatura
     */
    public void printResultado(String dadosTemperatura){
        System.out.println("=============================================================================");
        String lista[]  = dadosTemperatura.substring(5).split(this.separador);
        String listaOrdenada[] = new String[6];
        for (int i=0; i< 6;i++){
            if ('L'==dadosSequencia.charAt(i)){
                listaOrdenada[0]= lista[i];
            }
            if ('D' == dadosSequencia.charAt(i)){
                listaOrdenada[1]= lista[i];
            }
            if ('M'==dadosSequencia.charAt(i)){
                listaOrdenada[2]= lista[i];
            }
            if ('X'==dadosSequencia.charAt(i)){
                listaOrdenada[3]= lista[i];
            }
            if ('T'==dadosSequencia.charAt(i)){
                listaOrdenada[4]= lista[i];
            }
            if ('H'==dadosSequencia.charAt(i)){
                listaOrdenada[5]= lista[i];
            }

        }
        this.dadosSequencia.charAt(0);
        System.out.println("Localidade:" + listaOrdenada[0]);
        System.out.println("Data:"+ listaOrdenada[1]);
        System.out.println("Temperaturas máxima e mínimas previstas:[Mín: "+listaOrdenada[2]+"] e [Máx: "+listaOrdenada[3]+"]");
        System.out.println("Temperatura Atual: "+listaOrdenada[4]+" graus às "+listaOrdenada[5]+" horas");




        System.out.println("=============================================================================");
    }

    public void cancelRegistro() throws IOException {
        DatagramSocket socketEnvio = new DatagramSocket();

        String mensagem = "STOP " + this.token;

        byte[] bytes = mensagem.getBytes();

        InetAddress inetAddress = InetAddress.getByName("10.0.75.1");
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, inetAddress, 54321) ;
        socketEnvio.send(datagramPacket);
        this.interrupt();
    }

}