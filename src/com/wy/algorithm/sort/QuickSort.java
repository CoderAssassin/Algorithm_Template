package com.wy.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aliyang
 * @date 18-5-1 下午4:58
 * 快速排序，时间复杂度，O(NlogN)，最坏O(N^2)
 * 基本思想：选择一个枢纽，将比枢纽小的、大的和相等的元素分别放到不同的数组，然后对于小的和大的数组分别
 * 进行同样的排序，如此递归下去
 */
public class QuickSort {

    /**
     * 快速排序主入口
     * @param items 待排序的元素列表
     */
    public static void quickSort(List<Integer> items){
        if (items.size()>1){
            List<Integer> smaller=new ArrayList<>();//保存比选择的元素小的元素
            List<Integer> same=new ArrayList<>();//保存和选择的元素一样的元素
            List<Integer> larger=new ArrayList<>();//保存比选择的元素大的元素

            Integer chosenItem=items.get(items.size()/2);//选择一个枢纽
//            遍历列表将所有的元素分类
            for (Integer i:items){
                if (i<chosenItem)
                    smaller.add(i);
                else if (i>chosenItem)
                    larger.add(i);
                else same.add(i);
            }

            quickSort(smaller);
            quickSort(larger);

            items.clear();
            items.addAll(smaller);
            items.addAll(same);
            items.addAll(larger);
        }
    }

}
