package com.wy.algorithm;

public class test {
    public static void a(int a[]){
        int tmp=a[0];
        a[0]=a[1];
        a[1]=tmp;
    }
    public static void main(String[] args){

        int[] b={1,2};
        a(b);
        System.out.printf(b[0]+","+b[1]);
    }
}
