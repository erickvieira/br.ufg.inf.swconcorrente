package br.ufg.inf.swconcorrente;

import br.ufg.inf.swconcorrente.prevtemp.ui.controller.PrevTempController;
import br.ufg.inf.swconcorrente.utils.connections.Stuff;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        new PrevTempController().showFrame();
    }

    public static void main2(String[] args) throws Exception {

        String ip = "192.168.1.122";
        int port = 12345;
        Socket socket = new Socket(ip, port);
        Scanner scanner = new Scanner(System.in);

        String string;

        OutputStreamWriter os = new OutputStreamWriter(socket.getOutputStream());
        PrintWriter out = new PrintWriter(os);
        out.println("GET LOCALS");
        out.flush();


        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String resposta = br.readLine();

        /**
         * Separa as strings das cidades
         */
        String[] lista = resposta.split("[|]");

        /**
         * Apresenta as cidades
         */
        System.out.println("Escolha a cidade pra registrar");
        for (int i = 1; i < lista.length; i++) {
            System.out.print(i + " - " + lista[i] + "  ");
        }
        System.out.println();

        // Opção escolher
        string = "REGISTER " + lista[Integer.parseInt(scanner.nextLine())];


        /**
         * Retorno do servidor
         */
        out.println(string);
        out.flush();
        resposta = br.readLine();
        System.out.println("Retorno do Server: "+ resposta);

        lista = resposta.split("[|]");



        Stuff cudp = new Stuff("START " + lista[1], Integer.parseInt(lista[4]), lista[2], lista[3]);
        cudp.start();
        System.out.println("Caso deseje parar de receber mensagens digite algo");
        scanner.nextLine();
        cudp.cancelRegistro();

    }

}
