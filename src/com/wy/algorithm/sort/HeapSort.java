//package com.wy.algorithm.sort;
//
///**
// * Created by AliYang on 2018/4/24.
// * 堆排序：
// */
//public class HeapSort {
//    /**
//     *
//     * @param i 堆中某一项的索引
//     * @return
//     */
//    private static int leftChild(int i){
//        return 2*i+1;
//    }
//
//    private static void percDown(int[] a,int i,int n){
//        int child;
//        int tmp;
//
//        for (tmp=a[i];leftChild(i)<n;i=child){
//            child=leftChild(i);
//            if (child!=n-1&&a[child]<a[child+1])
//                child++;
//            if (tmp<a[child])
//                a[i]=a[child];
//            else break;
//        }
//        a[i]=tmp;
//    }
//
//    public static void heapSort(int[] a){
//        for (int i=a.length/2-1;i>=0;i--)//构建堆
//            percDown(a,i,a.length);
//        for (int i=a.length-1;i>0;i--){
//            swapReferences(a,0,i);
//            percDown(a,0,i);
//        }
//    }
//}
