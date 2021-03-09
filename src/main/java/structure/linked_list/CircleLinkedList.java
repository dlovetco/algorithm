package structure.linked_list;

import lombok.Data;

/**
 * 单向环形链表 节点使用单链表的node类
 */
@Data
public class CircleLinkedList {

    private Node head = new Node(1);

    /**
     * 简单的构建出一个环形链表
     */
    public void build(int num) {
        if (num <= 1) {
            return;
        }
        Node temp = head;
        for (int i = 2; i <= num; i++) {
            Node node = new Node(i);
            temp.setNext(node);
            temp = temp.getNext();
        }
        temp.setNext(head);
    }

    public void show() {
        Node temp = head;
        while (true) {
            System.out.println(temp.getValue());
            if (temp.getNext() == head) {
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * 删除指定节点
     */
    public void delete(int value) {
        Node temp = head;
        while (true) {
            Node next = temp.getNext();
            if (next.getNext() == head) {
                break;
            } else if (next.getValue() == value) {
                if (next.getNext() == null) {
                    //如果要删除的是最后一个节点
                    temp.setNext(null);
                    temp = temp.getNext();
                } else {
                    temp.setNext(next.getNext());
                }
            } else {
                temp = temp.getNext();
            }
        }
    }

    /**
     * 解决约瑟夫问题（环状，报数，报到的人出列然后继续报  打印出所有人的出列顺序）
     *
     * @param totalNum 总人口
     * @param startNum 从第几个开始报数
     * @param interval 间隔几个人
     */
    public void resolveJosepfu(int totalNum, int startNum, int interval) {
        build(totalNum);
        //减少初始化的循环次数 但是初始化需要额外加一个总数 目的让helper跑一圈否则如果从1开始 helper就指向head了
        startNum = startNum % totalNum + totalNum;

        //单链表删除需要知道当前节点的前一个
        Node first = head;
        Node helper = head;
        //放好first的初始位置 1是自己 2是下一个。。。
        //先让helper 移动 n-2个位置 再让first=head.next()
        for (int i = 1; i < startNum - 1; i++) {
            helper = helper.getNext();
        }
        first = helper.getNext();

        //开始删除
        //因为是连续删除 不能简单的调用delete方法删除某一个节点的同时需要记录下来当前的值
        while (true) {
            //链表只剩一个节点就直接打出
            if (first == helper) {
                System.out.println(first.getValue());
                break;
            }
            //每次都移动n-1个单位
            for (int i = 0; i < interval - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println(first.getValue());
            helper.setNext(first.getNext());
            first = helper.getNext();
        }
    }
}
