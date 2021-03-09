package structure.hashtable;

public class MyHashTableDemo {

    public static void main(String[] args) {
        MyHashTable hashTable = new MyHashTable(10);
        Fruit fruit1 = new Fruit(1, "桃子", null);
        Fruit fruit2 = new Fruit(11, "苹果", null);
        Fruit fruit3 = new Fruit(2, "葡萄", null);
        Fruit fruit4 = new Fruit(22, "石榴", null);
        Fruit fruit5 = new Fruit(3, "香蕉", null);
        Fruit fruit6 = new Fruit(13, "柚子", null);
        hashTable.add(fruit1);
        hashTable.add(fruit2);
        hashTable.add(fruit3);
        hashTable.add(fruit4);
        hashTable.add(fruit5);
        hashTable.add(fruit6);
        hashTable.list();
    }
}
