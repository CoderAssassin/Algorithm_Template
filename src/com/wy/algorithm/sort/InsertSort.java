package com.wy.algorithm.sort;

/**
 * Created by AliYang on 2018/4/18.
 * 插入排序：从小到大
 * 时间复杂度：平均O(N^2)，最好O(N)，即原先已经有序
 * 思路：在同一个数组上运行，从下标1开始，临时保存待排数字sortNum，从sortNum所在索引位置往前搜索，
 * 若搜索的数比sortNum大，那么往后移动一位，直到前边的数小于等于sortNum。即：比较---->移动
 */
public class InsertSort {
    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType [] a){
        int nowIndex;//现在排序的元素的索引下标

//        第一层循环，从下标1-a.length-1，总共O(n-1)
        for (int i=1;i<a.length;i++){
            AnyType sortNum=a[i];
            int j;
            for (j=i;j>0;j--){//第二层循环,比较大小，若大于当前排序元素，那么后移一位
                if (sortNum.compareTo(a[j-1])<0){//若当前元素比前一个元素小，那么将当前元素赋值给前一个元素
                    a[j]=a[j-1];
                }else break;
            }
            a[j]=sortNum;

            System.out.print("第"+i+"次迭代后的结果数组为：");
            StringBuilder sb=new StringBuilder();
            for (j=0;j<a.length;j++)
                sb.append(a[j]+",");
            sb.deleteCharAt(sb.lastIndexOf(","));
            System.out.println(sb.toString());
        }
     }

    public static void main(String[] args){
//        InsertSort insertSort=new InsertSort();
//        int[] a={34,8,64,51,32,21};
//        insertSort.insertionSort(a);
    }
}
