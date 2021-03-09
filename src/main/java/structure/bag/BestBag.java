package structure.bag;

import java.util.Iterator;

public class BestBag<T> implements Iterable<T> {

    /**
     * 每次添加都在头部 这样我们只需要维护一个头结点就行
     * 但是这样的话 遍历顺序就跟入库顺序相反
     */
    private Node first;

    private int size;

    BestBag() {
    }

    /**
     * 往bag里面添加对象
     */
    public void add(T item) {
        Node tempNode = new Node(item);
        tempNode.next = first;
        first = tempNode;
        this.size++;
    }

    /**
     * 查看目前bag里面有多少对象了
     */
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new BestBagIterator();
    }

    class BestBagIterator implements Iterator<T> {

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

    /**
     * 链表节点
     */
    class Node {
        Node(T item) {
            this.item = item;
        }

        private Node next;
        private T item;
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
        BestBag<BestBag.Fruit> fruitBag = new BestBag<>();
        Fruit apple = new Fruit("苹果", 5.1);
        Fruit grape = new Fruit("葡萄", 6.7);
        Fruit orange = new Fruit("橘子", 3.2);
        fruitBag.add(apple);
        fruitBag.add(grape);
        fruitBag.add(grape);
        fruitBag.add(orange);
        fruitBag.add(orange);
        fruitBag.add(orange);
        System.out.println(fruitBag.size());
        for (Fruit fruit : fruitBag) {
            System.out.println(fruit.name + " " + fruit.price);
        }
    }
}
