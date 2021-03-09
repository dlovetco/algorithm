package structure.hashtable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 哈希表——数组里面套链表
 * 一个对象放进数组中 首先按照自定义的散列函数算出散列值 根据散列值放入数组的相应位置 如果散列值重复 则建立链表
 */
@Data
public class MyHashTable {

    MyHashTable(int size) {
        this.size = size;
        table = new FruitList[size];
        for (int i = 0; i < table.length; i++) {
            table[i] = new FruitList();
        }
    }

    private FruitList[] table;

    private int size;

    public void add(Fruit fruit) {
        table[calHash(fruit)].add(fruit);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            table[i].list();
        }
    }

    /**
     * 自定义的计算hash值
     */
    private int calHash(Fruit fruit) {
        return fruit.getId() % this.size;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Fruit {
    private int id;

    private String name;

    private Fruit next;
}

/**
 * 简单的链表
 */
@Data
class FruitList {

    private Fruit head = new Fruit();

    FruitList() {
    }

    public void add(Fruit fruit) {
        Fruit temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(fruit);
    }

    public void list() {
        Fruit temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
            System.out.print(temp.getName() + "->");
        }
        System.out.println();
    }
}
