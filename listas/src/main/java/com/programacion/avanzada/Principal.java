package com.programacion.avanzada;

import com.programacion.avanzada.listas.Lista;

import java.util.function.Function;
import java.util.function.Predicate;

record Pair <T,U>(T o1, U o2) {
    static <T,U> Pair<T,U> of(T o1, U o2) {
        return new Pair<>(o1,o2);
    }
}

public class Principal {

    static Lista<String> sucesor(Lista<Pair<String,String>> grafo, String nodo) {
        if(grafo.isEmpty()) {
            return Lista.Empty;
        }
        else {
            var head = grafo.head();

            if (nodo.equals(head.o1())) {
                var tmp = sucesor(grafo.tail(), nodo);

                return Lista.listOf(head.o2(), tmp);
            } else {
                return sucesor(grafo.tail(), nodo);
            }
        }
    }

    static <T> Lista<T> takeWhile(Lista<T> ls, Predicate<T> p) {
        if(ls.isEmpty()) {
            return Lista.Empty;
        }
        else {
            if(p.test(ls.head())) {
                return Lista.listOf(ls.head(), takeWhile(ls.tail(), p));
            }
            else {
                return Lista.Empty;
            }
        }
    }

    static <T> Lista<T> dropWhile(Lista<T> ls, Predicate<T> p) {
        if(ls.isEmpty()) {
            return Lista.Empty;
        }
        else {
            if(p.test(ls.head())) {
                return dropWhile(ls.tail(), p);
            }
            else {
                return ls;
            }
        }
    }

    public static void main(String[] args) {

            var grafo = Lista.listOf(
                    Pair.of("m", "n"),
                    Pair.of("m", "p"),
                    Pair.of("m", "o"),
                    Pair.of("n", "q"),
                    Pair.of("p", "q"),
                    Pair.of("q", "s"),
                    Pair.of("o", "r"),
                    Pair.of("q", "r")
            );

            var suc = sucesor(grafo, "m");

            System.out.println(suc);

            //-----------
            var ls = Lista.listOf(2,4,6,7,4,2);
            var ls1 = dropWhile(ls, t->t%2==0);
            System.out.println(ls1);

            var ls2 = takeWhile(ls, t->t%2==0);
            System.out.println(ls2);
            System.out.println("map : " +ls.map(s->"str"));
            System.out.println("reduce : " +ls2.reduce(ls.head(),x->y->x+1));
            //String vi = "";
            //Function<String,Function<Integer,String>> fn = Str->i->str+i;
            //var ret= lista.foldleft(vi,fn);

//            System.out.println("FoldLeft: "+ ls2.FoldLeft("", str->i->str+i));
//           Lista<Integer> vi = Lista.Empty;
//
//            Function<Lista<Integer>, Function<Integer,Lista<Integer>>> fn =
//                    lis->i->lis.prepend(i);
//        System.out.println(ls2.FoldLeft(vi,fn));
//
//        Function<Integer,String> fnAux= t->"str"+t;
//        Lista<Integer> vii = Lista.Empty;
//        Function<Lista<String>, Function<Integer,Lista<String>>> fn1 =
//                lis->t->lis.append(fnAux.apply(t));
//
//        System.out.println(ls2.FoldLeft(vii,fn));
//

        String vi = "";
        Function<Integer, Function<String, String>> fn = i ->str -> str+i;
        System.out.println("foldLeft: " +ls.foldLeft("", str -> i ->str+i));
        System.out.println("foldRight: " +ls.foldRight("",i->str-> str+i));
        System.out.println("map-fold-left: " + ls.mapfoldLeft(s->"str"+s));
        System.out.println("map-fold-right" + ls.mapFoldRight(s->"str"+s));
        System.out.println("append-fold: "+ls.appendFold(99));
        System.out.println("count-fold: "+ls.countFold());

        System.out.println("takeFold: " +ls.takeFold(3));
        System.out.println("dropFold: " +ls.dropFold(3));

        System.out.println("takeWhile-fold: " +ls.takeWhileFold(x -> x < 2));

    }
}
