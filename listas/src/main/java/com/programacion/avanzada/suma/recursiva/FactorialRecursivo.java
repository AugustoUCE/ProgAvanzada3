package com.programacion.avanzada.suma.recursiva;

public class FactorialRecursivo {

    static int factorial(int n) {
        if(n==0) {
            return 1;
        }else {
            return n*factorial(n-1);
        }
    }
    //tailrecursivo
    static int factTailAux(int n ,int acc){
        if(n==0) {
            return acc;
        }else{
            return factTailAux(n-1,n*acc);
        }
    }
    static int factTail(int n){
        return factTailAux(n,1);
    }
    public static void main(String[] args) {

        var fact = factTail(10);
        System.out.println(fact);
    }

}
