package algorithm.divide_and_conquer;

/**
 * 汉诺塔
 * 分治算法最佳实践
 */
public class HanoiTower {

    public static void main(String[] args) {
        new HanoiTower().move(3, "A", "B", "C");
    }

    /**
     * 把num数目的块从start移动到end
     *
     * @param num
     * @param start  起始
     * @param middle 中间
     * @param end    终点
     */
    public void move(int num, String start, String middle, String end) {
        if (num == 1) {
            System.out.println("第1个盘从" + start + "移动到" + end);
        } else {
            //如果有多个盘则分成两步
            //1、先把除了最后一个盘的其他所有盘移动到middle
            //2、把最后一个盘移动到end
            //3、再把中间的所有盘移动到end
            move(num - 1, start, end, middle);
            System.out.println("第" + num + "个盘从" + start + "移动到" + end);
            move(num - 1, middle, start, end);
        }
    }
}
