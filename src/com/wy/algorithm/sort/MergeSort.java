package com.wy.algorithm.sort;



/**
 * @author aliyang
 * @date 18-5-1 下午4:17
 * 归并排序，从小到大，时间复杂度最差O(NlogN)
 * 基本思想：将数组分成两份两份的形式，每份中进行排序，然后对这两份进行合并，
 * 分别从每一份的开始位置开始，比较两个位置的大小，小的放到第3个数组中，指针往后移动
 * 一位，最多需要N-1次比较
 */
public class MergeSort {

    /**
     * 排序分治
     * @param a
     * @param tmpArray
     * @param left
     * @param right
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType [] a,AnyType[] tmpArray,int left,int right){
        if (left<right){
            int center=(left+right)/2;
            mergeSort(a,tmpArray,left,center);
            mergeSort(a,tmpArray,center+1,right);
            merge(a,tmpArray,left,center+1,right);
        }
    }

    /**
     * 合并程序
     * @param a
     * @param tmpArray
     * @param leftPos 左边部分的第一个位置
     * @param rightPose 右边部分的第一个位置
     * @param rightEnd 右边部分的最后个位置
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void merge(AnyType[] a,AnyType[] tmpArray,int leftPos,int rightPose,int rightEnd){
        int leftEnd=rightPose-1;
        int tmpPose=leftPos;//临时数组开始位置为左边部分第一个位置
        int numElements=rightEnd-leftPos+1;//总共的元素数量

//        循环比较
        while (leftPos<=leftEnd&&rightPose<=rightEnd)
            if (a[leftPos].compareTo(a[rightPose])<=0){//如果左边部分的元素小于右边部分的元素
                tmpArray[tmpPose++]=a[leftPos++];
            }else tmpArray[tmpPose++]=a[rightPose++];

//        如果左右边还有剩余的元素，加到元素数组最后
        while (leftPos<=leftEnd)
            tmpArray[tmpPose++]=a[leftPos++];

        while (rightPose<=rightEnd)
            tmpArray[tmpPose++]=a[rightPose++];

//        从后往前复制回去
        for (int i=0;i<numElements;i++,rightEnd--)
            a[rightEnd]=tmpArray[rightEnd];
    }

    /**
     * 排序主入口
     * @param a
     * @param <AnyType>
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] a){
        AnyType[] tmpArray=(AnyType[])new Comparable[a.length];
        mergeSort(a,tmpArray,0,a.length-1);
    }
}
