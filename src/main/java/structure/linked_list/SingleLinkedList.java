package structure.linked_list;

import lombok.Data;

/**
 * 单链表
 * 链表是由一个一个的节点组成的
 * 有一个head节点表示头部 永远动
 */
@Data
public class SingleLinkedList {
    private Node head = new Node(0);

    /**
     * 找到链表尾部并在最后增加一个新的节点
     */
    public void add(Node node) {
        Node temp = head;
        while (true) {
            if (temp.getNext() == null) {
                temp.setNext(node);
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * 根据数字大小来进行插入（插入排序） 从小到大
     */
    public void addByOrder(Node node) {
        Node temp = head;
        while (true) {
            if (temp.getNext() == null) {
                //如果遍历到最后都没有找到比他大的节点就放在最后
                temp.setNext(node);
                break;
            } else {
                //比较插入节点，与当前节点的next的大小
                Node next = temp.getNext();
                if (node.getValue() <= next.getValue()) {
                    temp.setNext(node);
                    node.setNext(next);
                    break;
                }
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
            if (next == null) {
                //遍历到最后还是没有找到
                break;
            } else if (next.getValue() == value) {
                if (next.getNext() == null) {
                    //如果要删除的是最后一个节点
                    temp.setNext(null);
                    //temp = temp.getNext();  不写这句话能够删除相同数据的多个节点
                } else {
                    temp.setNext(next.getNext());
                }
            } else {
                temp = temp.getNext();
            }
        }
    }

    public void list() {
        if (head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        //头结点不应该显示
        Node temp = head.getNext();
        while (true) {
            System.out.println(temp.getValue());
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * 单链表翻转
     * 新建一个链表 使用头插法自然然而完成原链表的翻转
     */
    public void reverse() {
        //如果链表为空或者链表长度为1 则不需要翻转
        if (head.getNext() == null || head.getNext().getNext() == null) {
            return;
        }
        Node reverseHead = new Node(0);
        Node temp = head;
        while (true) {
            Node next = temp.getNext();
            if (next == null) {
                break;
            }
            temp.setNext(next.getNext());
            Node reverseHeadNext = reverseHead.getNext();
            reverseHead.setNext(next);
            next.setNext(reverseHeadNext);
            //因为这里存在断节点 链的总长度在缩短 所以不需要temp往下走
        }
        head = reverseHead;
    }
}

@Data
class Node {
    private int value;

    private Node next;

    Node(int value) {
        this.value = value;
    }
}
