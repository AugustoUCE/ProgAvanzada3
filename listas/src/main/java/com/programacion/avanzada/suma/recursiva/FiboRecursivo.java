package com.programacion.avanzada.suma.recursiva;

public class FiboRecursivo {
    static int fib(int n) {

        if (n == 0 || n==1){
            return n;
        }else{
            return fib(n-1) + fib(n-2);
        }
    }

    static long fib02(long n, long acc1 , long acc2) {
        System.out.printf("fibo (%d %d %d)\n", n, acc1, acc2);
        if (n<=1){
            return acc1+acc2;

        }else{
            return fib02(n-1, acc2,acc1+acc2);
        }
    }
    public static void main(String[] args) {
        var fibo5= fib(5);
        System.out.println(fibo5);

        var fibo60= fib02(60,0,1);
        System.out.println(fibo60);



    }
}
