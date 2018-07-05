package br.ufg.inf.swconcorrente.utils.connections;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IConnector {

    @Nullable
    Object makeRequest(
            @NotNull String request,
            @Nullable Function1<? super String, Unit> onSuccess,
            @Nullable Function1<? super String, Unit> onFailure
    );

    void closeConn();

}
