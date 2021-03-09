package structure.sparse_array;

public class Main {

    public static void main(String[] args) {
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        System.out.println("原始的二维数组~~");
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(chessArr1[i][j]+"  ");
            }
            System.out.println();
        }
        int[][] sparseArray = SparseArray.toSparseArray(chessArr1, 0);

        System.out.println("稀疏数组~~");
        for (int i = 0; i < sparseArray.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(sparseArray[i][j]+"  ");
            }
            System.out.println();
        }

        System.out.println("还原稀疏数组~~");
        int[][] chessArr2 = SparseArray.toArray(sparseArray, 0);
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(chessArr2[i][j]+"  ");
            }
            System.out.println();
        }
    }
}
