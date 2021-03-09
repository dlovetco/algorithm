package structure.recursion;

import lombok.Data;

/**
 * 使用递归走迷宫
 */
@Data
public class Maze {

    private int[][] mazeArray =
            {
                    {1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 0, 0, 1, 0, 1},
                    {1, 0, 0, 0, 1, 0, 1},
                    {1, 0, 1, 0, 0, 0, 1},
                    {1, 0, 1, 0, 1, 0, 1},
                    {1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1}
            };

    /**
     * 递归寻找出路
     * 在寻找过程中为了不走回头路 我们需要在已经走过的路上打标记
     * 2表示走过的路
     * 3表示已经走过且已经确认过是死路的路
     */
    public boolean searchPath(int x, int y) {
        if (x == 5 && y == 5) {
            //如果找到终点了就结束
            mazeArray[x][y] = 2;
            System.out.println("找到了出口");
            return true;
        }
        if (mazeArray[x][y] == 0) {
            mazeArray[x][y] = 2;
            System.out.println(String.format("x=%d,y=%d", x, y));
            if (!searchPath(x + 1, y)) {
                if (!searchPath(x - 1, y)) {
                    if (!searchPath(x, y + 1)) {
                        if (!searchPath(x, y - 1)) {
                            mazeArray[x][y] = 3;
                            return false;
                        }
                    }
                }
            }
        } else {
            //如果1（墙壁） 2（走过的路） 3（走过确认过是死路） 则直接返回false
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.searchPath(1, 1);
        for (int[] ints : maze.getMazeArray()) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

}
