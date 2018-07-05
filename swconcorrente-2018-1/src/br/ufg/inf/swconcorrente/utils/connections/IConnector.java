package br.ufg.inf.swconcorrente.utils.connections;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.DatagramSocket;

public interface IConnector {

    void okCallback(String msg);

    void failCallback(String msg);

    @Nullable
    Object makeRequest(
            @Nullable DatagramSocket receiveSocket,
            @NotNull String request,
            @Nullable Function1<? super String, Unit> onSuccess,
            @Nullable Function1<? super String, Unit> onFailure
    );

    void closeConn();

}
