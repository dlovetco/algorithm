package structure.recursion;

import lombok.Data;

/**
 * 八皇后问题 8*8的棋盘上 放置棋子 要求任意两个棋子不能再同一行或者同一列 也不能在同一个对角线
 * 可以使用一个一维数组表示结果 因为（任意两个棋子不能再同一行或者同一列）
 * 任意两个棋子x和y的间隔差不相等就满足不在同一对角线
 */
@Data
public class EightQueen {

    private int[] checkerboard = {-1, -1, -1, -1, -1, -1, -1, -1};

    private int methodNum;

    public boolean putChess(int x) {
        for (int s = 0; s < 8; s++) {
            checkerboard[x] = -1;
            //判断要设置的值 在棋盘上有没有了
            //有的话 就重新设置
            boolean canSet = true;
            for (int i : checkerboard) {
                if (s == i) {
                    canSet = false;
                    break;
                }
            }
            if (canSet) {
                checkerboard[x] = s;
            } else {
                continue;
            }

            //如果是第一个格子 放完之后就直接放下一个格子
            if (x == 0) {
                if (putChess(x + 1)) {
                    checkerboard[x] = -1;
                }
            } else {
                //不是第一个格子 要跟前面所有的格子比较 x的间隔与值的间隔不能相同
                boolean success = true;
                for (int i = 0; i < x; i++) {
                    if ((x - i) == Math.abs(checkerboard[x] - checkerboard[i])) {
                        success = false;
                        break;
                    }
                }
                if (success) {
                    if (x != 7) {
                        //不是最后一个格子就持续调用下一个格子的摆放
                        if (putChess(x + 1)) {
                            checkerboard[x] = -1;
                        }
                    } else {
                        //最后一个棋子摆放成功则方法数+1 且返回true
                        methodNum++;
                        checkerboard[x] = -1;
                        return true;
                    }
                }
            }
        }
        //在最后返回的时候要重置为-1
        checkerboard[x] = -1;
        return false;
    }

    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.putChess(0);
        System.out.println(eightQueen.getMethodNum());
    }
}
