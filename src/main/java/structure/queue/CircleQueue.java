package structure.queue;

import java.util.Iterator;

public class CircleQueue<T> implements Iterable<T> {

    private Node first;

    private Node last;

    private int size;

    /**
     * 队列的总长度
     */
    private int maxSize;

    CircleQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 循环队列的enqueue
     * 一直移动last
     * 然后要时刻保证last.next = first
     * 当队列放满了 如果要继续添加就要往下移动一个first 再增加last 相当于覆盖了第一个节点
     */
    public void enqueue(T value) {
        Node tempNode = new Node(value);
        if (isEmpty()) {
            first = tempNode;
            last = tempNode;
            first.next = last;
            size++;
            return;
        }
        boolean ifAddSize = true;
        if (isFull()) {
            first = first.next;
            ifAddSize = false;
        }
        if (ifAddSize) {
            size++;
        }
        last.next = tempNode;
        last = tempNode;
        last.next = first;
    }

    private boolean isEmpty() {
        return first == null;
    }

    private boolean isFull() {
        return size == maxSize;
    }

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
        //单项链表删除节点需要辅助节点 要删除节点的前一个节点
        Node pre = first;
        while (temp.next != temp) {
            int i = 1;
            while (i <= interval) {
                pre = temp;
                temp = temp.next;
                i++;
            }
            System.out.print(temp.item + " ");
            pre.next = temp.next;
            temp = pre.next;
        }
        System.out.println();
        System.out.println("最后留在队列中的是:" + temp.item);
    }


    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    class QueueIterator implements Iterator<T> {

        Node current = first;

        int currentSize;

        @Override
        public boolean hasNext() {
            return currentSize < maxSize;
        }

        @Override
        public T next() {
            T tempItem = current.item;
            current = current.next;
            currentSize++;
            return tempItem;
        }
    }

    class Node {
        Node(T item) {
            this.item = item;
        }

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
        CircleQueue<Fruit> fruitQueue = new CircleQueue<>(3);
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
        CircleQueue<Integer> josephusCircle = new CircleQueue<>(6);
        for (int i = 1; i <= 6; i++) {
            josephusCircle.enqueue(i);
        }
        //第5个被干掉 则说明是间隔4个
        josephusCircle.solveJosephusProblem(4);
    }
}
