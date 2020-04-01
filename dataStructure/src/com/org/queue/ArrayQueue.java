package com.org.queue;

import java.util.Scanner;

/**
 * @Description //TODO
 * @Author ruanzhiqiang
 * @Date 2020/3/18 15:40
 * @ClassName ArrayQueue
 **/
public class ArrayQueue {
    public static void main(String[] args) {
        ArrQueue q = new ArrQueue(5);
        Scanner s = new Scanner(System.in);
        char ch = ' ';
        System.out.println("");

        boolean loop = true;
        while(loop){
            System.out.println("a(add),添加一个数");
            System.out.println("e(exist),退出程序");
            System.out.println("g(get),获取一个数据");
            System.out.println("h(head),获取头数据");
            System.out.println("s(show),查看队列的数据");
            ch = s.next().charAt(0);
            switch (ch){
                case 'a':{
                    System.out.println("请输入一个数");
                    int value = s.nextInt();
                    q.addQueue(value);
                    break;
                }case 'e':{
                    loop = false;
                    break;
                }case 'g':{
                    System.out.println("得到的数据="+q.getQueue());
                    break;
                }case 'h':{
                    System.out.println("得到头的数据="+q.headQueue());
                    break;
                }case 's':{
                    q.showQueue();
                    break;
                } default:{
                    break;
                }
            }

        }
        System.out.println("程序退出");
    }
}

//创建一个数组队列类
class ArrQueue{
    private int maxSize; //数组队列最大长度
    private int rear;//队列尾
    private int front;//队列头
    private int[] arr;//队列数组

    //初始化队列数据
    public ArrQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr = new int[maxSize];
        rear = -1;
        front = -1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //判断队列是否已满
    public boolean isFull(){
        return rear == maxSize -1;
    }

    //队列添加数据
    public void addQueue(int data){
        if(isFull()){
            System.out.println("队列已满，不能忘队列添加数据");
            return;
        }
        arr[++rear]=data;
    }

    //队列中取数据
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("此队列为空不能取数据");
        }
        int num = arr[++front];
        return num;
    }

    //查看队列数据
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("此队列为空");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
        System.out.println();
    }
    //查看队列头文件
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("此队列为空");
        }
        return arr[front+1];
        //System.out.printf("head{arr[%d]=%d}",front+1,arr[front+1]);
    }
}
