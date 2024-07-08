package com.programacion.avanzada.recursion;

public class FiboRecursivo {
    static int fibo(int n){
        if(n==0 || n==1){
            return n;
        }else{
            return fibo(n-1)+fibo(n-2);
        }
    }

    static long fibo2Aux(long n, long acc1, long acc2){
//        System.out.printf("fibo(%d,%d,%d)\n",n,acc1,acc2);
        if (n<=1){
            return acc1 + acc2;
        }else{
            return fibo2Aux(n-1, acc2, acc1+acc2);
        }
    }

    static long fibo2(long n){
        return fibo2Aux(n,0,1);
    }

    public static void main(String[] args){

        var fibo5 = fibo(5);
        System.out.println(fibo5);

        var fibo60 = fibo2(60);
        System.out.println(fibo60);
    }
}
