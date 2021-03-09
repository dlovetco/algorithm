package structure.linked_list;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        Node hero1 = new Node(1);
        Node hero2 = new Node(2);
        Node hero3 = new Node(3);
        Node hero4 = new Node(4);
        Node hero5 = new Node(3);

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero5);
        System.out.println("原来链表的情况~~");
        singleLinkedList.list();

        System.out.println("删除以后链表的情况~~");
        singleLinkedList.delete(3);
        singleLinkedList.list();
        System.out.println("测试逆序打印单链表, 没有改变链表的结构~~");
        singleLinkedList.reverse();
        singleLinkedList.list();
        System.out.println("测试逆序打印单链表, 没有改变链表的结构~~");
        singleLinkedList.reverse();
        singleLinkedList.list();
    }
}
