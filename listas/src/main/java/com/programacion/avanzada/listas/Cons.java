package com.programacion.avanzada.listas;

public record Cons<T>(T head, Lista<T> tail) implements Lista<T> {

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]", head,tail.toString());
    }
}

