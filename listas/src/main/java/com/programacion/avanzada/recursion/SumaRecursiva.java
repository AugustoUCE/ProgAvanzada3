package com.programacion.avanzada.recursion;

public class SumaRecursiva {

    static int addIt(int a, int b){
        while (b>0){
            b = b-1;
            a=a+1;
        }
        return a;
    }

    static int addRec(int a, int b){
        if(b==0){
            return a;
        }else{
        return addRec(a+1,b-1);
        }
    }

    static void test(){

    }

    public static void main(String[] args) {
        var r1 = addRec(5,3);
        var r2 = addIt(5,3);
        System.out.println(r1);
        System.out.println(r2);

        var r3 = addIt(100000000,3);
        var r4 = addIt(100000000,3);

        System.out.println(r3);
        System.out.println(r4);

//        var r5 = addIt(4, 100_000_000);
//        System.out.println(r5);
//        var r6 = addIt(4,100_000_000);
//        System.out.println(r6);

        test();

    }
}
