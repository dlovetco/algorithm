package structure.stack;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

    private Node first;

    private int size;

    public void push(T value) {
        Node tempNode = new Node(value);
        tempNode.next = first;
        first = tempNode;
        size++;
    }

    private boolean isEmpty() {
        return first == null;
    }

    /**
     * 删除并返回栈顶的第一个元素
     */
    public T pop() {
        //如果栈空了则返回null
        if (isEmpty()) {
            System.out.println("栈空了");
            return null;
        }
        T topValue = first.item;
        first = first.next;
        size--;
        return topValue;
    }

    /**
     * 查看栈顶的值 不取出
     */
    public T top() {
        //如果栈空了则返回null
        if (isEmpty()) {
            System.out.println("栈空了");
            return null;
        }
        return first.item;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    class StackIterator implements Iterator<T> {

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
        Stack<Fruit> fruitStack = new Stack<>();
        Fruit apple = new Fruit("苹果", 5.1);
        Fruit grape = new Fruit("葡萄", 6.7);
        Fruit orange = new Fruit("橘子", 3.2);
        fruitStack.push(apple);
        fruitStack.push(grape);
        fruitStack.push(grape);
        fruitStack.push(orange);
        fruitStack.push(orange);
        fruitStack.push(orange);
        System.out.println(fruitStack.size());
        for (Fruit fruit : fruitStack) {
            System.out.println(fruit.name + " " + fruit.price);
        }
        System.out.println("开始pop数据");
        int size = fruitStack.size();
        for (int i = 0; i < size; i++) {
            System.out.println(fruitStack.pop().name);
        }
        //最后一次检查有没有空
        System.out.println(fruitStack.pop());
    }
}
