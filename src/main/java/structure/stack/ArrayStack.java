package structure.stack;

public class ArrayStack<T> {

    private int maxSize;

    private T[] stack;

    private int top = -1;

    ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = (T[]) new Object[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(T value) {
        if (isFull()) {
            System.out.println("满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("空的");
            return null;
        }
        T temp = stack[top];
        top--;
        return temp;
    }

    public T top() {
        return stack[top];
    }

    public int size() {
        return top + 1;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("空的");
        }
        for (int i = 0; i <= top; i++) {
            System.out.println(stack[i]);
        }
    }
}
