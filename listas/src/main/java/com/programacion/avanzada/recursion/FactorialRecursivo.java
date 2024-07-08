package com.programacion.avanzada.recursion;

public class FactorialRecursivo {

    static int fact(int n){
        if (n==0){
            return 1;
        }else{
            return n * fact(n-1);
        }
    }

    static int factTRAux(int n, int acc){
        if (n==0){
            return acc;
        }
            return factTRAux(n-1, n*acc);
    }

    static int factTR(int n){
        return factTRAux(n,1);
    }

    public static void main(String[] args){
        var f10 = factTR(10);
        System.out.println(f10);
    }
}
