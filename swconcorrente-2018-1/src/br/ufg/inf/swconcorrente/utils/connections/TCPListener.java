package br.ufg.inf.swconcorrente.utils.connections;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.Socket;

/**
 * @author erickv
 * date - Jul 04, 2018
 */
public class TCPListener implements IConnector {

    private Socket socket;

    public TCPListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String makeRequest(
            @NotNull String request,
            @Nullable Function1<? super String, Unit> onSuccess,
            @Nullable Function1<? super String, Unit> onFailure
    ) {
        String response = "ERRO";

        try {
            OutputStream output = socket.getOutputStream();
            OutputStreamWriter oWriter = new OutputStreamWriter(output);
            BufferedWriter bWriter = new BufferedWriter(oWriter);

            bWriter.write(request);
            bWriter.flush();

            InputStream input = socket.getInputStream();
            InputStreamReader iReader = new InputStreamReader(input);
            BufferedReader bReader = new BufferedReader(iReader);
            response = bReader.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assert onFailure != null;
            onFailure.invoke(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }

    public void closeConn() {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ignored) { }
    }
}
