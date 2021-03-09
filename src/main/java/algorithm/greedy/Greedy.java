package algorithm.greedy;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 贪心算法——具有贪心性质的动态规划
 * <p>
 * 下面是一个可以试用贪心算法解的题目，贪心解的确不错，可惜不是最优解。
 * [背包问题]有一个背包，背包容量是M=150。有7个物品，物品可以分割成任意大小（如果不能分割则不能用贪心算法求解）。
 * 要求尽可能让装入背包中的物品总价值最大，但不能超过总容量。
 * 物品 A  B  C  D  E  F  G
 * 重量 35 30 60 50 40 10 25
 * 价值 10 40 30 50 35 40 30
 * 分析：
 * 目标函数： 背包内价值最大
 * 约束条件是装入的物品总重量不超过背包容量：总重量不超过背包容量
 * <p>
 * 贪心算法的关键点在于贪心策略的选择，如上题可以得出三种贪心策略
 * 1、放入重量最小的
 * 2、放入价值最大的
 * 3、放入单位重量价值最大的
 * 由于物品是可以分割的，所以很明显3是最优策略。
 * 如果不能分割，则贪心算法不能保证能将背包尽可能的塞满。部分闲置的背包容量使背包的单位重量价值降低了。此时应该使用动态规划
 */
@Data
public class Greedy {

    /**
     * 放入的物品
     */
    private List<Item> itemList;

    /**
     * 背包的总价值
     */
    private int totalValue;

    /**
     * 背包的总载重量
     */
    private int totalWeight;

    public Greedy() {
        itemList = new ArrayList<>();
    }

    @Data
    static class Item {
        public Item(int weight, int value, String name) {
            this.weight = weight;
            this.value = value;
            this.name = name;
        }

        public Item() {
        }

        private int weight;

        private int putWeight;

        private double value;

        private double unitValue;

        private String name;
    }

    /**
     * 选择单位价值最高的贪心策略
     */
    private void greedyWay(List<Item> optionalList) {
        //计算一遍所有的单位价值
        optionalList.forEach(item -> {
            item.unitValue = item.getValue() / item.getWeight();
        });
        optionalList.sort(Comparator.comparing(Item::getUnitValue).reversed());
        int tempTotalWeight = 0;
        while (tempTotalWeight < totalWeight) {
            //选出一个不在itemList的 且单位价值较高的物品
            Item tempItem = null;
            for (Item item : optionalList) {
                if (!itemList.contains(item)) {
                    tempItem = item;
                    break;
                }
            }
            if (tempItem == null) {
                break;
            }
            if (totalWeight - tempTotalWeight >= tempItem.getWeight()) {
                itemList.add(tempItem);
                tempItem.setPutWeight(tempItem.getWeight());
                tempTotalWeight += tempItem.getWeight();
            } else {
                itemList.add(tempItem);
                tempItem.setPutWeight(totalWeight - tempTotalWeight);
                tempTotalWeight = totalWeight;
                break;
            }
        }
        for (Item item : itemList) {
            totalValue += (item.getPutWeight() / item.getWeight()) * item.getValue();
        }
    }

    public static void main(String[] args) {
        Item item1 = new Item(35, 10, "A");
        Item item2 = new Item(30, 40, "B");
        Item item3 = new Item(60, 30, "C");
        Item item4 = new Item(50, 50, "D");
        Item item5 = new Item(40, 35, "E");
        Item item6 = new Item(10, 40, "F");
        Item item7 = new Item(25, 30, "G");
        Greedy greedy = new Greedy();
        greedy.setTotalWeight(150);
        List<Item> optionalList = new ArrayList<>();

        optionalList.add(item1);
        optionalList.add(item2);
        optionalList.add(item3);
        optionalList.add(item4);
        optionalList.add(item5);
        optionalList.add(item6);
        optionalList.add(item7);
        greedy.greedyWay(optionalList);

        System.out.println(greedy);
    }
}
