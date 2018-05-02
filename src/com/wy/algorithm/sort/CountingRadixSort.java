package com.wy.algorithm.sort;

/**
 * @author aliyang
 * @date 18-5-2 上午10:37
 * 计数基数排序，不使用ArrayList，使用计数器，记录每个桶里装多少个元素
 */
public class CountingRadixSort {

    public static void countingRadixSort(String[] arr,int stringLen){

        final int BUCKETS=256;

        int N=arr.length;
        String[] buffer=new String[N];

        String[] in=arr;//待排序的字符串数组
        String[] out=buffer;//缓冲字符串数组

        for (int pos=stringLen-1;pos>=0;pos--){
            int[] count=new int[BUCKETS+1];//因为只用count来计数比k小的数，要用到k+1个位置

//            用k+1位置表示桶k中元素的个数
            for (int i=0;i<N;i++)
                count[in[i].charAt(pos)+1]++;

//            再次计算count,k位置表示严格小于k的元素的个数
            for (int b=1;b<=BUCKETS;b++)
                count[b]+=count[b-1];

//            out为输出的排序数组，count[in[i].charAt(pos)]表示小于in[i]的字符串的pos位置的
//            字符的值的其他字符串数，所以in[i]的排名就是这个字符串数+1，因为out下标从0开始，所以+1不用了
            for (int i=0;i<N;i++)
                out[count[in[i].charAt(pos)]++]=in[i];

//            交换下in和out数组
            String[] tmp=in;
            in=out;
            out=tmp;
        }

//        若是奇数次，那么需要将buffer复制回array
        if (stringLen%2==1)
            for (int i=0;i<arr.length;i++)
                out[i]=in[i];
    }
}
