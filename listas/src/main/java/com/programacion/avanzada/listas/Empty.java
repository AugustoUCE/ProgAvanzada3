package com.programacion.avanzada.listas;

final class Empty implements Lista{
    Empty(){
    }
    @Override
    public Object head() {
        throw new RuntimeException("Lista vacía");
    }

    @Override
    public Lista tail() {
        throw new RuntimeException("Lista vacía");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String toString(){
        return "Empty";
    }

    public Integer count(){
        return 0;
    }
//
//    public Lista<T> append(T elem){
//        return Lista.listOf(elem,Empty);
//    }

}
