package structure.bag;

import java.util.Iterator;

public class BetterBag<T> implements Iterable<T> {

    /**
     * 内部的数据容器
     */
    private T[] data;

    /**
     * data数组的下标 可以初始化为0 也可以初始化为-1 这里初始化为0举例
     */
    private int size = -1;

    BetterBag(int initialSize) {
        //因为java不支持创建泛型数组 所以这么操作
        this.data = (T[]) new Object[initialSize];
    }

    /**
     * 往bag里面添加对象
     */
    public void add(T item) {
        if (isFull()) {
            //如果袋子装满了则把容量调整成两倍
            adjustBagSize();
        }
        this.size++;
        this.data[this.size] = item;
    }

    /**
     * 查看目前bag里面有多少对象了
     */
    public int size() {
        return this.size;
    }

    /**
     * 当前的bag是否已经满了
     */
    private boolean isFull() {
        return this.size == this.data.length - 1;
    }

    /**
     * 调整当前bag的容量 调整为两倍大小
     */
    private void adjustBagSize() {
        T[] newData = (T[]) new Object[this.data.length * 2];
        System.arraycopy(this.data, 0, newData, 0, this.data.length);
        this.data = newData;
    }


    @Override
    public Iterator<T> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<T> {

        /**
         * 初始化迭代时 数组的下标 因为是迭代数组 所以肯定是从0迭代到size
         */
        private int first = 0;

        @Override
        public boolean hasNext() {
            return this.first <= size;
        }

        @Override
        public T next() {
            T tempData = data[this.first];
            this.first++;
            return tempData;
        }
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
        BetterBag<Fruit> fruitBag = new BetterBag<>(3);
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
