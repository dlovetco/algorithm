package structure.bag;

public class Bag {

    /**
     * 内部的数据容器
     */
    private int[] data;

    /**
     * data数组的下标 可以初始化为0 也可以初始化为-1 这里初始化为-1举例
     */
    private int size = -1;

    /**
     * 初始化bag
     *
     * @param maxSize 最大容量
     */
    Bag(int maxSize) {
        this.data = new int[maxSize];
    }

    /**
     * 往bag里面添加对象
     */
    public void add(int item) {
        //每次添加对象都应该判断一下是否满了 避免造成数组越界异常
        if (!isFull()) {
            this.size++;
            this.data[this.size] = item;
        } else {
            throw new RuntimeException("当前bag已满");
        }
    }

    /**
     * 当前的bag是否已经满了
     */
    private boolean isFull() {
        return this.size == this.data.length - 1;
    }

    /**
     * 查看目前bag里面有多少对象了
     */
    public int size() {
        return this.size;
    }

    /**
     * 展示当前bag里面拥有的数据
     */
    public void list() {
        for (int dataItem : this.data) {
            System.out.print(dataItem + " ");
        }
    }

    public static void main(String[] args) {
        //测试用例
        Bag bag = new Bag(3);
        bag.add(1);
        bag.add(2);
        bag.add(3);
        System.out.println(bag.size());
        bag.list();
        bag.add(4);
    }
}
