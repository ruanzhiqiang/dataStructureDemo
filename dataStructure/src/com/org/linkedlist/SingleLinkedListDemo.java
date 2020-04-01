package com.org.linkedlist;

/**
 * @Description //TODO
 * @Author ruanzhiqiang
 * @Date 2020/3/24 16:35
 * @ClassName SingleLinkedListDemo
 **/
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        Node n1 = new Node(1, "第1个");
        Node n2 = new Node(2, "第2个");
        Node n3 = new Node(3, "第3个");
        Node n4 = new Node(4, "第4个");
        Node n5 = new Node(5, "第5个");
        SingleLinkedList l = new SingleLinkedList();
      /*  l.add(n2);
        l.add(n1);
        l.add(n3);*/
        l.addOrderBy(n2);
        l.addOrderBy(n1);
//        l.addOrderBy(n1);
        l.addOrderBy(n3);
        l.addOrderBy(n4);
        l.addOrderBy(n5);


        System.out.println("反转前的结构~~");
        l.list();
        System.out.println("反转后的结构~~");
        reversalLinked(l.getHeadNode());
        l.list();


/*

        l.list();
        Node n4 = new Node(3, "第3个3");
        Node n5 = new Node(4, "第4个");
        l.update(n4);
        l.update(n5);
        l.list();
        System.out.println("删除~~~~~~~~~~~~~~~~~");

l.del(1);
        l.del(3);
        l.del(1);
        l.del(2);

        l.list();
        System.out.println("查找数据");
        Node n = l.queryNode(4);
        System.out.println(n);
        //查看链表有几个有效数据
        System.out.println(getNodeLength(l.getHeadNode()));
        Node node1 = getLastIndexNode(l.getHeadNode(),1);
        System.out.println("res="+node1);


*/


    }

    /** 单链表反转
     * @Author 阮志强
     * @Date 15:47
     * @Param
     * @param head
     * @return void
     **/
    public static void reversalLinked(Node head){
        //首先判断链表是否为空和是否只有一个数据
        if(head.next==null || head.next.next==null){
            return;
        }
        //当前节点node nowNode
        Node nowNode = head.next;
        Node next = null;//下一个节点
        Node newHead = new Node(0,"");//定义的一个新的头节点
        while(nowNode!=null){
            System.out.println("~~"+nowNode);
            next = nowNode.next;//存放下一个节点
            nowNode.next = newHead.next; //将新头节点的下一个节点放入目前节点的下一个
            newHead.next=nowNode;//在将当前节点放入新的节点后面
            nowNode = next;//把下一个节点放入到现在这个节点进行循环
        }
        head.next = newHead.next;
    }

    /** 获取节点倒数第几个
     * @Author 阮志强
     * @Date 17:17
     * @Param
     * @param head 头节点
     * @param index 倒数第几个节点
     * @return com.org.linkedlist.Node
     **/
    public static Node getLastIndexNode(Node head,int index){
        Node temp = head.next;
        if(temp == null){
            return null;
        }
        //获取节点有效个数
        int size = getNodeLength(head);
        //判断index值是否有效
        if(index<=0||index>size){
            return null;
        }
        for (int i = 0; i < size-index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /** 获取链表有效个数
     * @Author 阮志强
     * @Date 16:59
     * @Param
     * @param head 头节点
     * @return int
     **/
    public static int getNodeLength(Node head){
        Node temp = head.next;
        if(temp==null){
            return 0;
        }
        int n = 0;
        while(temp!=null){
            n++;
            temp =temp.next;
        }
        return n;
    }


}

//创建链表类
class SingleLinkedList {
    //定义的头节点
    private Node headNode = new Node(0, "");

    public Node getHeadNode() {
        return headNode;
    }

    //单链表添加
    public void add(Node node) {
        //首先链表头节点不能动，将添加的数据添加到头节点的后面
        //将头节点放入到一个空节点中
        Node temp = headNode;
        while (true) {
            //如果下个节点是空时跳出循环否则继续寻找下一个节点
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    //有序插入
    public void addOrderBy(Node node) {
        //获取头节点
        Node temp = headNode;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {//判断下一个节点是否为空，为空直接插入
                break;
            }
            if (temp.next.no > node.no) {
                //判断当前节点的下一个节点的序号是否大于插入节点的序号，
                // 如果当前节点的下一个节点大于当前插入节点应该在当前节点的下一个节点前插入当前节点
                break;
            } else if (temp.next.no == node.no) {
                //如果当前节点的下一个节点的序号等于插入节点的序号，说明节点序号重复，不能添加当前节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("标号已存在：" + node.no);
        } else {
            //满足条件的数据首先先把当前节点的下一个节点插入到插入节点的后面
            //然后将当前节点的下一个节点放入插入节点
            node.next = temp.next;
            temp.next = node;
        }


    }


    //显示节点中所有数据
    public void list() {
        Node temp = headNode.next;
        if (temp == null) {
            System.out.println("链表为空");

        }
        while (true) {
            //如果下个节点是空时跳出循环否则继续寻找下一个节点
            if (temp == null) {
                break;
            }

            System.out.println(temp.toString());
            temp = temp.next;
        }

    }

    //修改单链表
    public void update(Node nNode){
        //首先判断链表是不是空
        if(headNode.next==null){
            System.out.println("链表为空不能执行修改");
            return;
        }
        Node temp = headNode.next;
        boolean flag = false;//用于判断是否有可修改值
        while(true){
            if(temp==null){
                //这里是链表循环完，没找到值退出

                break;
            }
            if(temp.no==nNode.no){
                flag = true;
                break;
            }

            temp = temp.next;
        }
        if(flag){
            temp.name=nNode.name;
        }else{
            System.out.printf("链表中没有修改的值xuhaowei:%d\n",nNode.no);
        }

    }
    //单链表删除
    public void del(int no){
        //首先判读链表是否为空
        Node temp = headNode;
        if(temp.next==null){
            System.out.println("链表为空不能执行删除");
            return;
        }
        boolean flag = false;
        while(true){
            if(temp.next==null){//已经循环完成
                break;
            }
            if(temp.next.no==no){
              //条件满足，删除下一个节点
              flag = true;
              break;//找到满足条件的
            }
            temp=temp.next;
        }
        if(flag){
            //满足条件，将当前节点的next指向下下下个节点
            temp.next=temp.next.next;
        }else{
            System.out.printf("删除节点序号%d失败\n",no);
        }
    }
    //通过节点序号查询节点信息
    public Node queryNode(int no){
        Node temp = headNode.next;
        boolean flag = false;
        if(temp==null){
            throw new RuntimeException("链表为空不能执行查询");
        }
        while(true){
            if(temp.next==null){//链表已经循环完成，没有查找到对应的数据
                break;
            }
            if(temp.no==no){
                flag = true;
                break;
                //已经查到数据
            }
            //没找到数据获取下一个节点
            temp = temp.next;
        }
        if(flag){//找到数据，返回找到的数据否则,返回null
            return temp;
        }else{
            return null;
        }

    }

}

//创建节点类
class Node {
    public int no;//节点数值
    public String name;//节点名称
    public Node next;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}