package com.wy.algorithm.sort;

/**
 * @author aliyang
 * @date 18-5-2 上午9:40
 * 基于快排的快速选择
 */
public class QuickSelect {

    /**
     * 从left，center，right中取中间值，并将三个数排序存储，right-1用来存放枢纽值，因此需要
     * 遍历排序0到right-2之间的数
     * @param a
     * @param left
     * @param right
     * @param <AnyType>
     * @return
     */
    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3(AnyType[] a,int left,int right){
        int center=(left+right)/2;
        if (a[center].compareTo(a[left])<0)//小的放在最左边
            swapReferences(a,left,center);
        if (a[right].compareTo(a[left])<0)
            swapReferences(a,left,right);
        if (a[right].compareTo(a[center])<0)
            swapReferences(a,center,right);

//        将枢纽（center）放在right-1位置
        swapReferences(a,center,right-1);
        return a[right-1];
    }

    /**
     * 这里是final方法，为了提高效率，使编译器在编译的时候就将函数硬编译进代码中
     * @param a
     * @param heapIndex
     * @param placeIndex
     * @param <AnyType>
     */
    private static final  <AnyType extends Comparable<? super AnyType>>
    void swapReferences(AnyType[] a,int heapIndex,int placeIndex){
        AnyType tmp=a[heapIndex];
        a[heapIndex]=a[placeIndex];
        a[placeIndex]=tmp;
    }

    private static final int CUTOFF=10;//小于该值那么换插入排序
    private static <AnyType extends Comparable<? super AnyType>>
    void quickSelect(AnyType[] a,int left,int right,int k){
        if (left+CUTOFF<=right){
            AnyType pivot=median3(a,left,right);

            int i=left,j=right-1;
            for (;;){
                while (a[++i].compareTo(pivot)<0){}
                while (a[++j].compareTo(pivot)>0){}
                if (i<j)
                    swapReferences(a,i,j);
                else break;
            }

            swapReferences(a,i,right-1);
            if (k<=i)//说明要选择的数在左边部分
                quickSelect(a,left,i-1,k);
            else if (k>i+1)//说明要选择的数在右边部分
                quickSelect(a,i+1,right,k);
        }
        else
            InsertSort.insertionSort(a);
    }

}
