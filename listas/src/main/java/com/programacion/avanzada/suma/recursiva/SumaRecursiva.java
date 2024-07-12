package com.programacion.avanzada.suma.recursiva;

public class SumaRecursiva {

    static int addRec(int a, int b) {
        if (b==0) {
            return a;
        }else{
            return addRec(a+1,b-1);
        }
    }
    static void test(){
        test();
    }
    public static void main(String[] args) {
    var r1=addRec(5,3);
        System.out.println(r1);
    }
}
