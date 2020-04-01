package com.org.queue;

import java.util.Scanner;

/**
 * @Description //TODO
 * @Author ruanzhiqiang
 * @Date 2020/3/23 14:50
 * @ClassName CircularArrayQueue
 **/
public class CircularArrayQueue {
    public static void main(String[] args) {

        CircularQueue q = new CircularQueue(5);
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

class CircularQueue {
    private int maxSize; //数组队列最大长度
    private int rear;//队列尾
    private int front;//队列头
    private int[] arr;//队列数组

    //初始化队列数据
    public CircularQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //队列添加数据
    public void addQueue(int data) {
        if (isFull()) {
            System.out.println("队列已满，不能忘队列添加数据");
            return;
        }
        arr[rear] = data;
        //获取当前的最后一个位置+1取模就是下个位置
        rear = (rear + 1) % maxSize;
    }

    //队列中取数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("此队列为空不能取数据");
        }
        int num = arr[front];
        //取模操作多用于循环操作
        front = (front + 1) % maxSize;
        return num;
    }

    //查看队列数据
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("此队列为空");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
        System.out.println();
    }

    //返回当前数据个数
    protected int size(){
        return (rear+maxSize-front)%maxSize;
    }

    //查看队列头文件
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("此队列为空");
        }
        return arr[front];
    }
}


