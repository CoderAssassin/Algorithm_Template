package com.wy.algorithm.sort;

import java.util.ArrayList;

/**
 * @author aliyang
 * @date 18-5-2 上午10:14
 * 使用ArrayList的基数排序，对所有的等长度为L的字符串进行基数排序
 */
public class RadixSortA {

    /**
     * 基数排序
     * @param arr 待排序的字符串数组
     * @param stringLen 字符串的长度
     */
    public static void radixSortA(String[] arr,int stringLen){

        final int BUCKETS=256;//桶数
        ArrayList<String>[] buckets=new ArrayList[BUCKETS];

//        初始化对象
        for (int i=0;i<BUCKETS;i++)
            buckets[i]=new ArrayList<>();

//        外层遍历，从字符串的最右边开始，每一趟基数排序遍历一个位置
        for (int pos=stringLen-1;pos>=0;pos--){
            for (String s:arr)//根据字符串pos位置的字符，将字符串放入对应的桶中
                buckets[s.charAt(pos)].add(s);

            int idx=0;
            for (ArrayList<String> thisBucket:buckets){
                for (String s:thisBucket)//将桶中的元素按桶的顺序存入到原来的数组中
                    arr[idx++]=s;
                thisBucket.clear();//每趟排序后要清除桶
            }
        }
    }
}
