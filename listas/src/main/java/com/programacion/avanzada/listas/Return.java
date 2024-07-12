package com.programacion.avanzada.listas;

public final class Return<T> implements TailCall<T> {
    private T value;
    @Override
    public T eval() {
        return value;
    }

    Return(T value){
        this.value = value;
    }

    @Override
    public TailCall<T> resume() {
        throw new RuntimeException("Último nodo :)");
    }

    @Override
    public boolean isSuspend() {
        return false;
    }
}
