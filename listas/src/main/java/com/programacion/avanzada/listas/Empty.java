package com.programacion.avanzada.listas;

import java.util.function.Consumer;

final class Empty<T> implements Lista<T> {
    //crear un constructor por defecto sin argumento y no debe
    //instaciarse

    Empty(){

    }

    @Override
    public T head() {
        throw new RuntimeException("Lista vacia");
    }

    @Override
    public Lista<T> tail() {
        throw new RuntimeException("Lista vacia");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String toString() {
        return "Empty{}";
    }
    public Integer count(){
        return 0 ;
    }
    public Lista<T> append(T elem){
        return  Lista.listOf(elem,Empty);
    }

    @Override
    public Lista<T> remove(int index){
        throw new RuntimeException("No se puede eliminar de una lista vacía");
    }
    @Override
    public T get(int index){
        throw new RuntimeException("No se puede eliminar de una lista vacía");

    }
    @Override
    public void forEach(Consumer<T> actioin){

    }

}
