package structure.linked_list;

import lombok.Data;

/**
 * 双向链表
 */
@Data
public class DoubleLinkedList {

    private DoubleNode head = new DoubleNode(0);

    /**
     * 找到链表尾部并在最后增加一个新的节点
     */
    public void add(DoubleNode node) {
        DoubleNode temp = head;
        while (true) {
            if (temp.getNext() == null) {
                temp.setNext(node);
                node.setPre(temp);
                break;
            }
            temp = temp.getNext();
        }
    }

    /**
     * 根据数字大小来进行插入（插入排序） 从小到大
     */
    public void addByOrder(DoubleNode node) {
        DoubleNode temp = head;
        while (true) {
            if (temp.getNext() == null) {
                //如果遍历到最后都没有找到比他大的节点就放在最后
                temp.setNext(node);
                node.setPre(temp);
                break;
            } else {
                //比较插入节点，与当前节点的next的大小
                DoubleNode next = temp.getNext();
                if (node.getValue() <= next.getValue()) {
                    temp.setNext(node);
                    node.setPre(temp);
                    node.setNext(next);
                    next.setPre(node);
                    break;
                }
            }
            temp = temp.getNext();
        }
    }

    /**
     * 删除指定节点 前的后 = 后 后的前=前
     */
    public void delete(int value) {
        DoubleNode temp = head.getNext();
        while (true) {
            if (temp == null) {
                break;
            } else if (temp.getValue() == value) {
                if (temp.getNext() == null) {
                    //如果要删除的是最后一个节点 断开自身与前面的连接
                    DoubleNode pre = temp.getPre();
                    pre.setNext(null);
                    temp = null;
                } else {
                    DoubleNode pre = temp.getPre();
                    DoubleNode next = temp.getNext();
                    pre.setNext(next);
                    next.setPre(pre);
                    temp = pre; //写这句话能够删除相同数据的多个节点
                    //temp = next;
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
        DoubleNode temp = head.getNext();
        while (true) {
            System.out.println(temp.getValue());
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
    }
}

@Data
class DoubleNode {
    private int value;
    private DoubleNode pre;
    private DoubleNode next;

    DoubleNode(int value) {
        this.value = value;
    }
}