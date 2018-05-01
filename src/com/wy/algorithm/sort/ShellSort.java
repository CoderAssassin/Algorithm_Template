package com.wy.algorithm.sort;

/**
 * Created by AliYang on 2018/4/24.
 * 希尔排序：从小到大。主要受增量序列的影响
 * 时间复杂度：最坏O(N^2)
 * 思路：设定增量序列，初始为N/2的下整，每次缩小为原来的一半直到为1。对于每个增量h，
 * 从h开始往后遍历到数组尾，每个元素进行插排，插排的两个元素之间间隔h个元素，因此这里其实
 * 是对h组进行插排
 */
public class ShellSort {
    private void shellSort(int[] a){
        int j;
//        增量初始为a.length/2，
        for (int gap=a.length/2;gap>0;gap/=2){
//            接下来进行插排
            for (int i=gap;i<a.length;i++){
                int sortNum=a[i];
                for (j=i;j>=gap;j-=gap){
                    if (a[j-gap]>sortNum)
                        a[j]=a[j-gap];
                    else break;
                }
                a[j]=sortNum;
            }
            System.out.print("增量为"+gap+"的结果数组为：");
            StringBuilder sb=new StringBuilder();
            for (j=0;j<a.length;j++)
                sb.append(a[j]+",");
            sb.deleteCharAt(sb.lastIndexOf(","));
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args){
        ShellSort sort=new ShellSort();
        int[] a={81,94,11,96,12,35,17,95,28,58,41,75,15};
        sort.shellSort(a);
    }
}
