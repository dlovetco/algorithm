package structure.queue;

import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {
//        ArrayQueue queue = new ArrayQueue(3);
//        for (int i = 0; i < 10; i++) {
//            queue.add(i);
//        }
//        queue.showAll();
//
//        System.out.println("移除之后的队列");
//        for (int i = 0; i < 3; i++) {
//            System.out.println("i="+i+"------"+queue.get());
//        }
//
//        queue.showAll();

        ArrayQueue queue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            char key = scanner.next().charAt(0);
            int res;
            switch (key) {
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'g':
                    try {
                        res = queue.get();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        res = queue.head();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's':
                    try {
                        queue.showAll();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
            }
        }

        System.out.println("程序退出~~");
    }
}
