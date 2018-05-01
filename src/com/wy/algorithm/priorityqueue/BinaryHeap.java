package com.wy.algorithm.priorityqueue;

import com.wy.algorithm.UnderflowException;


/**
 * Created by AliYang on 2018/4/28.
 * 二叉堆,最小堆
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
    private static final int DEFAULT_CAPACITY = 10;//默认堆容量
    private int currentSize;      // 堆的当前大小
    private AnyType [ ] array; // 堆数组

    public BinaryHeap(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * 构造二叉堆
     * @param capacity 二叉堆的容量
     */
    public BinaryHeap(int capacity){
        currentSize=0;
        array=(AnyType[])new Comparable[capacity+1];
    }

    /**
     * 根据给定的数组构造二叉堆
     * @param items 给定的数组
     */
    public BinaryHeap(AnyType [] items){
        currentSize=items.length;
        array=(AnyType[])new Comparable[(currentSize+2)*11/10];//这里根据输入数组的大小进行扩容

        int i=1;//从堆数组下标1开始存，0空出来
        for (AnyType item:items)
            array[i++]=item;
        buildHeap();
    }

    /**
     * 插入一个元素到堆中，元素可以重复
     * @param x
     */
    public void insert(AnyType x){
        if (currentSize==array.length-1)//如果堆数组已经满了，因为0不存数据，所以是array.length-1
            enlargeArray(array.length*2+1);

//        上滤，和父节点比较并交换
        int hole=++currentSize;//先将元素放在堆数组的最后一个位置
        for (array[0]=x;x.compareTo(array[hole/2])<0;hole/=2)//堆数组下标0存插入元素x
            array[hole]=array[hole/2];//父元素若比插入元素大，那么交换位置
        array[hole]=x;
    }

    /**
     * 从堆中删除最小元素
     * @return
     */
    public AnyType deleteMin(){
        if (isEmpty())
            throw new UnderflowException();
        AnyType minItem=findMin();
        array[1]=array[currentSize--];//先将最后一个元素填充到根
        percolateDown(1);//下滤
        return minItem;
    }

    /**
     * 下滤
     * @param hole
     */
    public void percolateDown(int hole){
        int child;
        AnyType tmp=array[hole];

        for (;hole*2<=currentSize;hole=child){
            child=hole*2;//左孩子
            if (child!=currentSize&&array[child+1].compareTo(array[child])<0)//在有两个孩子的前提下，取值小的孩子
                child++;
            if (array[child].compareTo(tmp)<0)//若孩子的值比删除的位置的元素小，那么继续往下
                array[hole]=array[child];
            else break;
        }
        array[hole]=tmp;
    }

    /**
     * 返回根元素
     * @return
     */
    public AnyType findMin(){
        if (isEmpty())
            throw new UnderflowException();
        return array[1];
    }

    /**
     * 判断堆是否为空
     * @return
     */
    public boolean isEmpty(){
        return currentSize==0;
    }

    /**
     * 将堆清空
     */
    public void makeEmpty(){
        currentSize=0;
    }

    /**
     * 建立堆
     */
    private void buildHeap(){
        for (int i=currentSize/2;i>0;i--)
            percolateDown(i);//对每个父节点进行下滤
    }

    /**
     * 堆数组扩充
     * @param newSize
     */
    private void enlargeArray(int newSize){
        AnyType[] old=array;
        array=(AnyType[])new Comparable[newSize];
        for (int i=0;i<old.length;i++){
            array[i]=old[i];
        }
    }

    public static void main(String[] args){

        int numItems = 10000;
        BinaryHeap<Integer> h = new BinaryHeap<>( );
        int i = 37;

        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
            h.insert( i );
        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );
    }

}
