package com.programacion.avanzada.listas;
import com.programacion.avanzada.listas.Lista;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
record Pair <T,U>(T o1, U o2) {
    static <T,U> Pair<T,U> of(T o1, U o2) {
        return new Pair<>(o1,o2);
    }
}
public class Main {
    // Método auxiliar para la suma de dos Listas utilizando tail recursion
    static Lista<Integer> addAux(Lista<Integer> tmp, Lista<Integer> x, Lista<Integer> y) {
        return x.isEmpty()
                ? tmp
                : addAux(tmp.prepend(x.head() + y.head()), x.tail(), y.tail());
    }

    // Método principal para sumar dos Listas
    static Lista<Integer> add(Lista<Integer> x, Lista<Integer> y) {
        if (x.count() != y.count()) {
            throw new RuntimeException("Las Listas deben tener la misma dimensión");
        }
        return addAux(Lista.Empty, x, y).invertFold();
    }
    public static void main(String[] args) {
        var x = Lista.listOf(1, 3, 4, 5);
        var y = Lista.listOf(3, 6, 2, 8);

        // Suma de vectores
        System.out.println("P01: " + add(x, y));
    }

}