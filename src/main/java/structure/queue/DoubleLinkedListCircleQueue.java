package structure.queue;

import java.util.Iterator;

/**
 * 双向链表实现循环队列
 */
public class DoubleLinkedListCircleQueue<T> implements Iterable<T> {

    public DoubleLinkedListCircleQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    private Node first;

    private Node last;

    private int size;

    private int maxSize;

    /**
     * 双向链表需要额外维护一个pre指针
     */
    public void enqueue(T value) {
        Node tempNode = new Node(value);
        if (isEmpty()) {
            first = tempNode;
            last = tempNode;
            first.next = last;
            first.pre = last;
            size++;
            return;
        }
        boolean ifAddSize = true;
        if (isFull()) {
            //先把当前节点指向下一个节点 再删除当前节点的上一个节点（也就是删除自己）
            first = first.next;
            dequeue(first.pre);
            ifAddSize = false;
        }
        last.next = tempNode;
        tempNode.pre = last;
        last = tempNode;
        last.next = first;
        first.pre = last;
        if (ifAddSize) {
            size++;
        }
    }

    /**
     * 删除first
     */
    public T dequeue() {
        //如果队列空了则返回null
        if (isEmpty()) {
            System.out.println("队列空了");
            return null;
        }

        T topValue = first.item;
        //如果只剩一个了 就全部变成null
        if (first == last) {
            first = last = null;
        } else {
            first = first.next;
            first.pre = last;
            last.next = first;
        }
        size--;
        return topValue;
    }

    /**
     * 解决约瑟夫问题
     *
     * @param interval 间隔
     */
    public void solveJosephusProblem(int interval) {
        Node temp = first;
        while (temp.next != temp) {
            int i = 1;
            while (i <= interval) {
                temp = temp.next;
                i++;
            }
            System.out.print(temp.item + " ");
            //先把当前节点指向下一个节点 再删除当前节点的上一个节点（也就是删除自己）
            temp = temp.next;
            dequeue(temp.pre);
        }
        System.out.println();
        System.out.println("最后留在队列中的是:" + temp.item);
    }


    /**
     * 删除链表中的指定节点
     */
    private T dequeue(Node aimNode) {
        T result = aimNode.item;
        aimNode.next.pre = aimNode.pre;
        aimNode.pre.next = aimNode.next;
        return result;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public boolean isFull() {
        return size == maxSize;
    }

    public int size() {
        return this.size;
    }


    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListCircleQueueIterator();
    }


    private class DoubleLinkedListCircleQueueIterator implements Iterator<T> {

        private int currentSize;

        Node current = first;

        @Override
        public boolean hasNext() {
            return currentSize < size;
        }

        @Override
        public T next() {
            T tempItem = current.item;
            current = current.next;
            currentSize++;
            return tempItem;
        }
    }

    private class Node {

        public Node(T item) {
            this.item = item;
        }

        Node pre;

        Node next;

        T item;
    }

    /**
     * 用于测试泛型的自定义类
     */
    private static class Fruit {
        public Fruit(String name, Double price) {
            this.name = name;
            this.price = price;
        }

        String name;

        Double price;
    }

    public static void main(String[] args) {
        DoubleLinkedListCircleQueue<Fruit> fruitQueue = new DoubleLinkedListCircleQueue<>(3);
        Fruit apple = new Fruit("苹果", 5.1);
        Fruit grape = new Fruit("葡萄", 6.7);
        Fruit orange = new Fruit("橘子", 3.2);
        fruitQueue.enqueue(apple);
        fruitQueue.enqueue(grape);
        fruitQueue.enqueue(grape);
        fruitQueue.enqueue(orange);
        fruitQueue.enqueue(orange);
        fruitQueue.enqueue(orange);
        int size = fruitQueue.size();
        System.out.println(size);
        for (Fruit fruit : fruitQueue) {
            System.out.println(fruit.name + " " + fruit.price);
        }
        System.out.println("开始dequeue数据");
        for (int i = 0; i < size; i++) {
            System.out.println(fruitQueue.dequeue().name);
        }
        //最后一次检查有没有空
        System.out.println(fruitQueue.dequeue());

        DoubleLinkedListCircleQueue<Integer> josephusCircle = new DoubleLinkedListCircleQueue<>(6);
        for (int i = 1; i <= 6; i++) {
            josephusCircle.enqueue(i);
        }
        //第5个被干掉 则说明是间隔4个
        josephusCircle.solveJosephusProblem(4);
    }
}
