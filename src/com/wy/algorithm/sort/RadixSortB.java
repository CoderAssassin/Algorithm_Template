package com.wy.algorithm.sort;

import java.util.ArrayList;

/**
 * @author aliyang
 * @date 18-5-2 下午12:40
 * 带ArrayList的变长字符串的基数排序
 * 基本思想:先按字符串的长度存入各个桶中,这样就有了顺序,然后对于每个长度的所有的字符串
 * 再进行桶排序
 */
public class RadixSortB {

    /**
     *
     * @param arr 待排序数组
     * @param maxLen 字符串最大长度
     */
    public static void radixSort(String[] arr,int maxLen){

        final int BUCKETS=256;

        ArrayList<String>[] wordByLength=new ArrayList[maxLen+1];//记录字符串长度的桶
        ArrayList<String>[] buckets=new ArrayList[BUCKETS];//记录排序的桶

//        初始化
        for (int i=0;i<wordByLength.length;i++)
            wordByLength[i]=new ArrayList<>();

        for (int i=0;i<BUCKETS;i++)
            buckets[i]=new ArrayList<>();

//        将不同长度的字符串放到对应的桶中
        for (String s:arr)
            wordByLength[s.length()].add(s);

//        按长度排序,排好序的放在arr数组中
        int idx=0;
        for (ArrayList<String> wordList:wordByLength)
            for (String s:wordList)
                arr[idx++]=s;

        int startingIndex=arr.length;
        for (int pos=maxLen-1;pos>=0;pos--){

//            计算长度为pos+1的其实字符串索引地址
            startingIndex-=wordByLength[pos+1].size();
//            将字符串放入对应的桶中
            for (int i=startingIndex;i<arr.length;i++)
                buckets[arr[i].charAt(pos)].add(arr[i]);

            idx=startingIndex;
//            将对长度为pos+1的所有字符串排序后存回arr数组中
            for (ArrayList<String> thisBucket:buckets){
                for (String s:thisBucket)
                    arr[idx++]=s;

                thisBucket.clear();
            }
        }
    }
}
