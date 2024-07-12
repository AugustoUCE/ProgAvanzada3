package com.programacion.avanzada.listas;

import java.util.function.Function;
import java.util.function.Predicate;

record Pair <T,U>(T o1, U o2) {
    static <T,U> Pair<T,U> of(T o1, U o2) {
        return new Pair<>(o1,o2);
    }
}

public sealed interface Lista<T> permits Cons, Empty { //solo será capaz de heredar Cons y Empty

    Lista Empty = new Empty();
    T head();
    Lista<T> tail();
    boolean isEmpty();

    static <T> Lista<T> listOf(T head, Lista<T> tail){
        return new Cons<>(head, tail);
    }

    static <T> Lista<T> listOf(T...elems){
        var tmp = Lista.Empty;

        for(int i=elems.length-1;i>=0;i--){
            tmp = Lista.listOf(elems[i], tmp);
        }
        return tmp;
    }

    default Integer count(){
        return 1 + tail().count();
    }

    default Lista<T> prepend(T elems){
        return listOf(elems, this);
    }

    default Lista<T> append(T elem){
//        if(this.isEmpty()){
//            return listOf(elem,Empty);
//        }else{
//            return listOf(head(),tail().append(elem));
//        }
        return this.isEmpty()
                ? new Cons<>(elem,Lista.Empty)
                :new Cons<>(this.head(), this.tail().append(elem));
    }

    default Lista<T> insert(int index, T elem){
        if(index==0){
            return Lista.listOf(elem,this);
        }else{
            return listOf(head(),tail().insert(index,elem));
        }
    }

    /**
     * Map: L<T> => L<u>
     *
     *     f: T-> U
     *     f(t)= u
     *
     */

    default<U> Lista<U> mapFold(Function<T,U> fn){
        return  this.foldLeft(Lista.Empty, ls-> t->ls.append(fn.apply(t)));
    }


    default<U> Lista<U> map(Function<T,U> fn){
//        if(this.isEmpty()){
//            return Lista.Empty;
//        }else{
//            var h = this.head();
//            var u = fn.apply(h);
//
//            return Lista.listOf(fn.apply(this.head()),this.tail().map(fn));
//        }

        return this.isEmpty()
                ? Lista.Empty
                : Lista.listOf(fn.apply(this.head()),this.tail().map(fn));
//
    }
    /** correcursiva se utiliza las iteraccion
     * reduce: L<T> => T
     * una lista de String pasa a string
     *    {t1,t2,t3,t4}= > 0+t1+t2+t3+t4
     *      f= TxT->T
     *      1.- f(0,t1)=tmp1
     *      2.-f(tmp,t2) = tmp
     *      3.-f(tmp,t3) = tmp
     *      4.-f(tmp,t1) = res
     */

    default T reduce(T identity,Function<T,Function<T,T>> fn){
        T acum = identity;

        var tmp = this;
        while(!tmp.isEmpty()){
            acum=fn.apply(acum).apply(tmp.head());
            tmp= tmp.tail();
        }
        return acum;
    }

    /** correcursiva se utiliza las iteraccion
     * Foldleft: L<T> => U
     *
     *    {t1,t2,t3,t4}= > u
     *      f= TxT->T
     *     ((((0+t1)+t2)+t3)+t4)
     *      1.-(0+t1) = tmp
     *      2.-(tmp+t2) = tmp
     *      3.-(tmp+t3) = tmp
     *      3.-(tmp+t4) = tmp
     */
    default <U> U foldLeft(U identity, Function<U,Function<T,U>> fn){
        U acum = identity;

        var tmp = this;
        while(!tmp.isEmpty()){
            acum=fn.apply(acum).apply(tmp.head());
            tmp= tmp.tail();
        }
        return acum;

        }

        default <U> U foldRight(U identity, Function<T, Function<U,U>> fn){
            return this.isEmpty()
                    ? identity
                    :fn.apply(head()).apply(tail().foldRight(identity,fn));
//            if(this.isEmpty()){
//                return identity;
//            }else {
//                var h = this.head();
//                var t = this.tail();
//                U tmpU = t.foldRight(identity, fn);
//
//                return fn.apply(h).apply(tmpU);
//            }
        }

        default Lista<T> invertFold(){
        return this.foldLeft(Lista.Empty, ls -> i ->  ls.prepend(i));
        }

        default <U> Lista<U> mapfoldLeft(Function<T,U> fn){
        return this.foldLeft(Lista.Empty, ls -> t ->ls.append(fn.apply(t)));
        }

        default <U> Lista<U> mapFoldRight(Function<T,U> fn){
        return this.foldRight(Lista.Empty, t->ls->ls.prepend(fn.apply(t)));
        }

        default T reduceFoldLeft(T identity,Function<T,Function<T,T>> fn){
            //T vi = identity;
            //Function<T, Function<T,T>> fnFold = t1 -> t2 ->fn.apply(t1).apply(t2);
            //return this.FoldLeft(vi, fnFold);
            return this.foldLeft(identity, t1-> t2->fn.apply(t1).apply(t2));
        }

        default Lista<T> appendFold(T elem){
//            Lista<T> vi = Lista.Empty;
//            Function<T,Function<Lista<T>,Lista<T>>> fn = t->ls->ls.prepend(t);
//            return this.foldRight(vi, fn);
            return this.foldRight(Lista.listOf(elem), t->ls->ls.prepend(t));
        }

        default Integer countFold(){
            return this.foldLeft(0, i-> t->i+1);
        }

        default Lista<T> takeFold(int n){
//        Lista<T> vi = Lista.Empty;
//        Function<Lista<T>, Function<T,Lista<T>>> fn = ls->t-> {
//            if(ls.count()<n){
//                return ls.append(t);
//            }else{
//                return ls;
//            }
//            };
//
//            return this.foldLeft(vi,fn);
            return foldLeft(Lista.Empty,
                    ls->t->ls.count()<n
                            ?ls.append(t)
                            :ls
            );
        }

        default Lista<T> dropFold(int n){
            int total = this.count()-n;
            return foldRight(
                    Lista.Empty,
                    t->ls->ls.count()<total
                            ?ls.prepend(t)
                            :ls
            );
        }

        default Lista<T> takeWhileFold(Predicate<T> p){
            var vi = Pair.of(Lista.Empty, true);
            var ret = this.foldLeft(vi, u->t-> {
               if(u.o2()==false){
                   return u;
               }if(p.test(t)){
                   return Pair.of(u.o1().append(t),true);
               }else{
                   return Pair.of(u.o1(), false);
                }
            });
            return ret.o1();
        }

//    public static void  main(String[]arg){
//        List<Integer> ls = List.of(1,2,3,4,5,6);
//        List<String> ret = new ArrayList<>();
//
//        for (int i= 0; i<ls.size();i++){
//            var t= ls.get(i);
//            ret.add("str: "+ t.toString());
//        }
//        Lista<Integer> ls1 = Lista.listOf(1,2,3,4,5,6);
//
//        System.out.println(ret);
//
//
//    }
}
