package structure.queue;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {

    private Node first;

    /**
     * 由于需要FIFO 所以我们需要维护last指针，每次入库都加在链表尾部
     */
    private Node last;

    private int size;

    public void enqueue(T value) {
        Node tempNode = new Node(value);
        if (isEmpty()) {
            first = tempNode;
            last = tempNode;
        } else {
            last.next = tempNode;
            last = tempNode;
        }
        size++;
    }

    private boolean isEmpty() {
        return first == null;
    }

    public T dequeue() {
        //如果队列空了则返回null
        if (isEmpty()) {
            System.out.println("队列空了");
            return null;
        }
        T topValue = first.item;
        first = first.next;
        size--;
        return topValue;
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

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T tempItem = current.item;
            current = current.next;
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
        Queue<Fruit> fruitQueue = new Queue<>();
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
    }
}
