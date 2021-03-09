package structure.sparse_array;

public class SparseArray {

    /**
     * 把普通二维规整数组变成稀疏数组
     *
     * @param array        二维规整数组
     * @param defaultValue 二维数组中默认的值（大部分坐标的值）
     * @return 稀疏数组
     */
    public static int[][] toSparseArray(int[][] array, int defaultValue) {
        if (array.length == 0) {
            return new int[0][0];
        }

        //首次遍历数组 创建稀疏数组
        int sum = 0;
        int line = array.length;
        int column = array[0].length;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                if (array[i][j] != defaultValue) {
                    sum++;
                }
            }
        }

        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = line;
        sparseArray[0][1] = column;

        //第二次遍历数组 给稀疏数组塞值
        int lineNo = 1;
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                if (array[i][j] != defaultValue) {
                    sum++;
                    sparseArray[lineNo][0] = i;
                    sparseArray[lineNo][1] = j;
                    sparseArray[lineNo][2] = array[i][j];
                    lineNo++;
                }
            }
        }
        sparseArray[0][2] = sum;
        return sparseArray;
    }

    /**
     * 把稀疏数组变成二维规整数组
     *
     * @param sparseArray  稀疏数组
     * @param defaultValue 二维数组中默认的值（大部分坐标的值）
     * @return 二维规整数组
     */
    public static int[][] toArray(int[][] sparseArray, int defaultValue) {
        int line = sparseArray[0][0];
        int column = sparseArray[0][1];
        int[][] array = new int[line][column];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < column; j++) {
                array[i][j] = defaultValue;
            }
        }

        int size = sparseArray.length;
        for (int i = 1; i < size; i++) {
            array[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return array;
    }

    public static void main(String[] args) {
        int[][] array = new int[11][11];
        array[1][2] = 1;
        array[2][3] = 2;
        array[4][5] = 2;
        System.out.println("原始的二维数组~~");
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(array[i][j]+"  ");
            }
            System.out.println();
        }
        int[][] sparseArray = SparseArray.toSparseArray(array, 0);

        System.out.println("稀疏数组~~");
        for (int i = 0; i < sparseArray.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(sparseArray[i][j]+"  ");
            }
            System.out.println();
        }
    }
}
