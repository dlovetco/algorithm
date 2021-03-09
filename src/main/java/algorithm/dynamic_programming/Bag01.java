package algorithm.dynamic_programming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态规划——01背包问题
 * 01背包问题指的是所有物品只能放一次，相对的无限背包问题指物品可以重复放
 * 在物品重量不超过背包容量的前提下，怎么样让背包中的总价值最大
 */
public class Bag01 {

    /**
     * 放入的物品
     */
    private List<Item> itemList;

    private Map<Integer, List<Item>> bestPutMap;

    /**
     * 背包的总价值
     */
    private int totalValue;

    /**
     * 背包的总载重量
     */
    private int totalWeight;

    public Bag01() {
        itemList = new ArrayList<>();
        bestPutMap = new HashMap<>();
        bestPutMap.put(0, new ArrayList<>());
    }

    /**
     * 设当前袋子里面已存在w1的物品，已存在的物品总价值为v1 （此已经是当前最佳方案）
     * 如果背包的总容量扩张到w2 （w1扩张到w2 应该是单位递增形式 每次递增我们都需要记录下来最佳方案）
     * 则w2的最佳方案应该为以下两种情况的value较大者
     * 1、放置一个重量不超过w2-w1的物品中价值最大的物品A  则totalValue = v1+vA
     * 2、把包里面的东西都拿出来，放置一个价值最大的物品B。此时包里面的容量为w2-wB，
     * 2.1如果w2-wb<w1 我们已经知道这种情况下的最佳方案 则totalValue = v(w2-wB)+vB
     * 2.2如果w2-wb>=w1 则与第一种情况一样 totalValue = v1+vB
     */
    public void bestWay(List<Item> optionalList) {
        for (int i = 1; i <= totalWeight; i++) {
            //每次扩张一个单位 取出扩张前的最佳方案
            List<Item> oldItemList = bestPutMap.get(i - 1);
            int oldTotalWeight = getTotalWeight(oldItemList);
            int gapWeight = i - oldTotalWeight;

            Item itemA = null;
            Item itemB = null;
            for (Item item : optionalList) {
                //从可选列表里面选出一个包里面没有，重量<=gapWeight，价值相对最大的物品 itemA
                if (!oldItemList.contains(item) && item.weight <= gapWeight) {
                    if (itemA == null) {
                        itemA = item;
                    } else {
                        if (itemA.value < item.value) {
                            itemA = item;
                        }
                    }
                }
                //从可选列表里面选出价值最大的物品,重量<=i 但是也要保证不与曾经的重复 itemB
                if (item.weight <= i && !bestPutMap.get(i - item.weight).contains(item)) {
                    if (itemB == null) {
                        itemB = item;
                    } else {
                        if (itemB.value < item.value) {
                            itemB = item;
                        }
                    }
                }
            }
            List<Item> newBestList = new ArrayList<>(oldItemList);
            if (itemA != null && itemB != null) {
                int totalValueA = getTotalValue(oldItemList) + itemA.value;
                int totalValueB = getTotalValue(bestPutMap.get(i - itemB.weight)) + itemB.value;
                if (totalValueA > totalValueB) {
                    newBestList.add(itemA);
                } else {
                    newBestList = new ArrayList<>(bestPutMap.get(i - itemB.weight));
                    newBestList.add(itemB);
                }
            } else if (itemA != null && itemB == null) {
                newBestList.add(itemA);
            } else if (itemA == null && itemB != null) {
                newBestList = new ArrayList<>(bestPutMap.get(i - itemB.weight));
                newBestList.add(itemB);
            }
            bestPutMap.put(i, newBestList);
        }
        itemList = bestPutMap.get(totalWeight);
        totalWeight = getTotalWeight(itemList);
        totalValue = getTotalValue(itemList);
    }

    public int getTotalValue(List<Item> tempItemList) {
        int totalValue = 0;
        for (Item item : tempItemList) {
            totalValue += item.value;
        }
        return totalValue;
    }

    public int getTotalWeight(List<Item> tempItemList) {
        int totalWeight = 0;
        for (Item item : tempItemList) {
            totalWeight += item.weight;
        }
        return totalWeight;
    }

    public int getTotalValue() {
        int totalValue = 0;
        for (Item item : itemList) {
            totalValue = item.value;
        }
        return totalValue;
    }

    public int getTotalWeight() {
        int totalWeight = 0;
        for (Item item : itemList) {
            totalWeight += item.weight;
        }
        return totalWeight;
    }


    static class Item {
        public Item(int weight, int value, String name) {
            this.weight = weight;
            this.value = value;
            this.name = name;
        }

        public Item() {
        }

        private int weight;

        private int value;

        private String name;
    }

    public static void main(String[] args) {
        Item item1 = new Item(1, 10, "A");
        Item item2 = new Item(1, 25, "B");
        Item item3 = new Item(3, 40, "C");
        List<Item> optionList = new ArrayList<>();
        optionList.add(item1);
        optionList.add(item2);
        optionList.add(item3);
        Bag01 bag01 = new Bag01();
        bag01.totalWeight = 3;
        bag01.bestWay(optionList);
        System.out.println(bag01.getTotalValue());
    }
}
