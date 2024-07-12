package com.programacion.avanzada.recursivo;

import static com.programacion.avanzada.recursivo.TailCall.tailReturn;
import static com.programacion.avanzada.recursivo.TailCall.tailSuspend;

public class TestRecursion {
    static int sumaOrg(int a, int b){
        if (b==0)
            return a;
        else
            return sumaOrg(a+1,b-1);

    }

    static TailCall<Integer> sumaRec(int a, int b){
        if (b==0){
            return tailReturn(a);
        }else {
            return tailSuspend(()-> sumaRec(a+1,b-1));
        }
    }

    public static void main(String[] args) {
        //original
        var r1 = sumaOrg(3,4);
        System.out.println(r1);

        //recursivo
//      var retTail =
//              tailSuspend(
//                tailSuspend(
//                        tailSuspend(
//                                tailSuspend(
//                                        tailReturn(7)
//                                )
//                        )
//                )
//        );
//      //[n1,n2,n3,n4,n5]
//      var n5 = tailReturn(7);
//      var n4 = tailSuspend(n5);
//      var n3= tailSuspend(n4);
//      var n2 = tailSuspend(n3);
//      var n1 = tailSuspend(n2);
//
//      var r2 = n1.eval();
//      System.out.println(r2);

        var r2 = sumaRec(3,4_000_000);
        System.out.println("r2: "+r2.eval());
    }
}
