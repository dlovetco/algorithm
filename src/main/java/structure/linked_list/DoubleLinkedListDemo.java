package structure.linked_list;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        DoubleNode hero1 = new DoubleNode(1);
        DoubleNode hero2 = new DoubleNode(2);
        DoubleNode hero3 = new DoubleNode(3);
        DoubleNode hero4 = new DoubleNode(4);
        DoubleNode hero5 = new DoubleNode(3);
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.add(hero5);
        doubleLinkedList.list();
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();
        doubleLinkedList.delete(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();
    }
}
