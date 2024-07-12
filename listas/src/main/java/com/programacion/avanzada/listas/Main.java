package com.programacion.avanzada.listas;
import com.programacion.avanzada.listas.Lista;
import com.programacion.avanzada.recursivo.TailCall;
import com.sun.jdi.IntegerType;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.programacion.avanzada.recursivo.TailCall.tailReturn;
import static com.programacion.avanzada.recursivo.TailCall.tailSuspend;

interface PairInterface<T, U> {
    T e1();

    U e2();
}

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

    // Método principal para zip de dos Listas
    static <T, U> Lista<Pair<T, U>> zipAux(Lista<Pair<T, U>> tmp, Lista<T> ls1, Lista<U> ls2) {
        return ls1.isEmpty()
                ? tmp
                : zipAux(tmp.prepend(new Pair<>(ls1.head(), ls2.head())), ls1.tail(), ls2.tail());
    }

    // Método para zip de dos Listas
    static <T, U> Lista<Pair<T, U>> zip(Lista<T> ls1, Lista<U> ls2) {
        if (ls1.count() != ls2.count()) {
            throw new RuntimeException("Las Listas deben tener el mismo número de elementos");
        }
        return zipAux(Lista.Empty, ls1, ls2).invertFold();
    }
//3
// Método para calcular el producto escalar
static Integer dot(Lista<Integer> x, Lista<Integer> y) {
    return zip(x, y)
            .map(p -> p.o1() * p.o2())
            .foldLeft(0, r -> mm -> r + mm);
}

//4
   static TailCall<Lista<Integer>> countInAux(Lista<Integer> tmp, Lista<Lista<Integer>> ls, Integer val) {
        if (ls.isEmpty()) {
            return tailReturn(tmp);
        } else {
            return tailSuspend(() -> countInAux(
                    tmp.prepend(ls.head().filter(t -> t.equals(val)).count()),
                    ls.tail(),
                    val)
            );
        }
    }

    static Lista<Integer> countIn(Lista<Lista<Integer>> ls, Integer val) {
        //return ls.map(it -> it.filter(val::equals).count());
        TailCall<Lista<Integer>> tmp = Main.countInAux(Lista.Empty, ls, val);

        return tmp.eval().invertFold();
    }

    //5
 


    public static void main(String[] args) {



        var x = Lista.listOf(1, 3, 4, 5);
        var y = Lista.listOf(3, 6, 2, 8);

        // Suma de vectores
        System.out.println("P01: " + add(x, y));
        // Zip de Listas
        System.out.println("P02: " + zip(x, y));
        // Producto escalar
        System.out.println("P03: " + dot(x, y));
//4
        Lista<Lista<Integer>> p4 = Lista.listOf(
                Lista.listOf(3, 2, 3),
                Lista.listOf(3),
                Lista.Empty,
                Lista.listOf(2, 2)

        );
        System.out.println("P04: " + countIn(p4, 3));
    }
//5

}