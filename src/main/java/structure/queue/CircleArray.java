package structure.queue;

import lombok.Data;

@Data
public class CircleArray {

    private int maxSize;
    /**
     * 指向队列的第一个元素
     */
    private int front;

    /**
     * 指向队列的最后一个元素的后一个元素 使得rear-front=有效数据  因为数组编号从0开始  当有第一个元素进来的时候front=0 rear=0+1=1
     */
    private int rear;

    private int array[];

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = 0;
        rear = 0;
    }

    /**
     * rear一直往后加 考虑到环形 (rear++)%maxSize   此外数据满了就不准覆盖，这样就就能保证在有数据的情况下不可能出现 rear==front
     */
    public void add(int value) {
        if (isFull()) {
            System.out.println("满了");
            return;
        }
        array[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        } else {
            return array[front];
        }
    }

    /**
     * front一直往后加 考虑到环形 (front++)%maxSize
     */
    public int get() {
        if (isEmpty()) {
            System.out.println("空了");
        }
        int value = array[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void show() {
        for (int i = front; i < front + effectiveValues(); i++) {
            System.out.println(String.format("array[%d]=%d", i, array[i % maxSize]));
        }
    }

    /**
     * 如果是满了则 rear+1=front 由于是环形队列 所以使用取模%的方式来判断 (rear+1)%maxSize=front
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 有效数据的个数是rear-front   考虑到环形 需要“取一下绝对值” (rear-front+maxSize)%maxSize
     */
    public int effectiveValues() {
        return (rear - front + maxSize) % maxSize;
    }
}
