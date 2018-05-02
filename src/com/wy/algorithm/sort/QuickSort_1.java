package com.wy.algorithm.sort;

/**
 * @author aliyang
 * @date 18-5-1 下午10:03
 * 优化后的快排
 */
public class QuickSort_1 {

    private static final int CUTOFF=10;//如果数组小于该值那么使用插入排序更快
    /**
     * 快排主入口
     * @param a
     * @param <AnyType>
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort(AnyType[] a){
        quicksort(a,0,a.length-1);
    }

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

    private static <AnyType extends Comparable<? super AnyType>>
    void quicksort(AnyType[] a,int left,int right){
        if (left+CUTOFF<=right){
            AnyType pivot=median3(a,left,right);//获取枢纽

//            int i=left,j=right-1;
            int i=left+1,j=right-2;//最左和最右分别是比枢纽小的大的元素，所以不用继续排
            for (;;){
//                while (a[++i].compareTo(pivot)<0){}
//                while (a[--j].compareTo(pivot)>0){}
//                改进，若left=pivot=right上述的代码会无限循环
                while (a[i].compareTo(pivot)<0)i++;
                while (a[j].compareTo(pivot)>0)j--;
                if (i<j)//如果这个时候i还是比j小，那么将i和j的数交换
                    swapReferences(a,i,j);
                else break;
            }

//            遍历完之后，i处的位置和枢纽交换
            swapReferences(a,i,right-1);

            quicksort(a,left,i-1);//递归从left到i-1，这一部分都是比枢纽小的数
            quicksort(a,i+1,right);//递归从i+1到right，这一部分都是比枢纽大的数
        }
        else{//使用插入排序
            InsertSort insertSort=new InsertSort();
            insertSort.insertionSort(a);
        }
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
}
