package structure.queue;

import lombok.Data;

/**
 * 数组模拟队列
 */
@Data
public class ArrayQueue {

    private int maxSize;
    /**
     * 指向队列的第一个元素
     */
    private int front;

    /**
     * 指向队列的最后一个元素的前一个元素
     */
    private int rear;

    private int array[];

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = 0;
        rear = -1;
    }

    public void add(int value) {
        if (isFull()) {
            maxSize = maxSize * 2;
            int[] extendArray = new int[maxSize];
            System.arraycopy(array, 0, extendArray, 0, rear + 1);
            array = extendArray;
            rear++;
            array[rear] = value;
        } else {
            rear++;
            array[rear] = value;
        }
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        } else {
            return array[front++];
        }
    }

    public boolean isFull() {
        return rear + 1 == maxSize;
    }

    public boolean isEmpty() {
        return front > rear;
    }

    public void showAll() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        int size = rear - front;
        for (int i = 0; i <= size; i++) {
            System.out.println(array[i + front]);
        }
    }

    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        } else {
            return array[front];
        }
    }
}
