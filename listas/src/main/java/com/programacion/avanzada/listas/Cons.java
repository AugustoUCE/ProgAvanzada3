package com.programacion.avanzada.listas;

//record por definicion constructor canonico no tiene set y es inmutable
public record Cons<T>(T head, Lista<T> tail) implements Lista<T> {

    @Override
    public boolean isEmpty() {

        return false;
    }
    //tostring

    @Override
    public String toString() {

        return String.format("[%s,%s]",head,tail.toString());
    }
}
