package com.programacion.avanzada.recursivo;

import java.util.function.Supplier;

public sealed interface TailCall<T> permits Suspend, Return {
    T eval();
    TailCall<T> resume();
    boolean isSuspend();

    public static<T> TailCall<T> tailReturn(T value){
        return new Return<>(value);
    }

    static <T> TailCall<T> tailSuspend(Supplier<TailCall<T>> next){
        return new Suspend<>(next);
    }
}
