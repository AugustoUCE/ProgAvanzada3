package com.programacion.avanzada.listas;

import java.util.function.Consumer;

//esta manera permite herendar de cons empty
public sealed interface Lista<T> permits Cons, Empty{

    //toda instacia en la interfaz son static
    Lista Empty = new Empty();
    T head();
    Lista<T> tail();
    boolean isEmpty();

    //creacion
    static <T> Lista<T> listOf(T head, Lista<T> tail){

        return new Cons<>(head, tail);
    }



    static <T> Lista<T> listOf(T... elems){
       var tmp= Lista.Empty;

        for (int i = elems.length-1; i >=0 ; i--) {
            tmp =Lista.listOf(elems[i],tmp);

        }
        return tmp;
    }

    default Integer count(){
       /* imperativa
        var tmp = this;
        int count = 1;
        while(tmp.isEmpty()==false){
            count ++;
            tmp=tmp.tail();
        }
        return count;
    */
        //declarativa
      /*  if (this.isEmpty()){
            return 0;
        }else{
            return 1 + tail().count();
        }

       */
        //declartiva en Empyty return 0
        return 1 + tail().count();
    }

    default Lista<T> prepend(T elem){
        return listOf(elem,this);
    }

   default Lista<T> append(T elem){
       /* if (this.isEmpty()){
            return  listOf(elem,Empty);
        }else{
            return listOf(head(),tail().append(elem));
        }
        */
      return listOf(head(),tail().append(elem));
   }

   default  Lista<T> insert(int index, T elem){
        if (index ==0){
            return Lista.listOf(elem,this);
        }else{
            return listOf(head(),tail().insert(index-1,elem));
        }
   }

   default Lista<T> remove(int index){
        if (index==0){
            return tail();
        }else{
            return listOf(head(),tail().remove(index-1));

        }
   }

   default T get(int index){
        if (index==0){
            return head();
        }else{
            return tail().get(index-1);
        }
   }

   default void forEach(Consumer<T> action){
        if (!isEmpty()){
            action.accept(head());
            tail().forEach(action);
        }
   }


}
