package com.wy.algorithm.sort;

/**
 * Created by AliYang on 2018/4/24.
 * 堆排序：使用一个数组，每次删除元素会存放在末尾，单次删除元素的时间复杂度是O(logN)，
 * 因此总的时间复杂度是O(NlogN)
 * 注意：和二叉堆不同的是，这里的下标从0开始
 * 本模板显示的是最大堆，用最大堆来排序出从小到大的序列
 */
public class HeapSort {

    /**
     * 获取左孩子，注意，这里下标从0开始，所以是2*i+1
     * @param i 堆中某一项的索引
     * @return
     */
    private static int leftChild(int i){
        return 2*i+1;
    }

    /**
     * 类似二叉堆下滤，把每个父节点进行下滤
     * @param a
     * @param i
     * @param n
     */
    private static void percDown(int[] a,int i,int n){
        int child;
        int tmp;

        for (tmp=a[i];leftChild(i)<n;i=child){
            child=leftChild(i);
            if (child!=n-1&&a[child]<a[child+1]) //取左右孩子节点中大的那个
                child++;
            if (tmp<a[child])
                a[i]=a[child];
            else break;
        }
        a[i]=tmp;
    }

    private static void swapReferences(int[] a,int heapIndex,int placeIndex){
        int tmp=a[heapIndex];
        a[heapIndex]=a[placeIndex];
        a[placeIndex]=tmp;
    }

    public static void heapSort(int[] a){
        for (int i=a.length/2-1;i>=0;i--)//构建Max堆
            percDown(a,i,a.length);
//        DeleteMax，存到i位置，最后数组是从小到大
        for (int i=a.length-1;i>0;i--){
            swapReferences(a,0,i);
            percDown(a,0,i);
        }
    }

    public static void main(String[] args){

        int a[]={1,2,3,4,5,6,7,8,9};
        heapSort(a);
        for (int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }
}
