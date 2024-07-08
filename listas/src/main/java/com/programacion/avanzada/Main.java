package com.programacion.avanzada;

import com.programacion.avanzada.listas.Cons;
import com.programacion.avanzada.listas.Lista;

import static com.programacion.avanzada.listas.Lista.*;

public class Main {
    public static void main(String[] args) {

        //[2,7,5,1,8]
        //[2,[7,[5,[1,[8,Empty]]]]]
       /* de vez de escribir esto con el metodo  de elementos
        var tmp = Lista.Empty;
        var n8= Lista.of(8, tmp);
        var n1= Lista.of(1, n8);
        var n5= Lista.of(5, n1);
        var n7= Lista.of(7, n5);
        var n2= Lista.of(2, n7);
        */
        var lista = listOf(2,7,5,1,8);

        System.out.println("list:"+ lista);
        System.out.println("count:"+ lista.count());
        System.out.println("head:" + lista.head());
        System.out.println("tail:"+ lista.tail());
        System.out.println("prepend: "+lista.prepend(99));
        System.out.println("append: "+lista.append(98));
        System.out.println("insert: "+lista.insert(2,98));
        System.out.println("obtener 2: "+ lista.get(2));

        System.out.println("eliminar 2: "+ lista.remove(2));
        System.out.println("lista actualizada: "+ lista);
        System.out.println("obtener 2: "+ lista.get(2));
        System.out.println("recorrido");

        lista.forEach(elem -> System.out.println(elem + ""));
    }
}