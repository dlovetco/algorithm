package structure.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "pop":
                    int res = stack.pop();
                    System.out.printf("出栈的数据是 %d\n", res);
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "show":
                    stack.show();
                    break;
            }
        }

        System.out.println("程序退出~~~");
    }
}
