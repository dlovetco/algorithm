package algorithm.MST;

import lombok.Data;
import structure.graph.Graph;

import java.util.Arrays;
import java.util.List;

/**
 * 弗洛伊德算法
 * 能够找出所有点之间相互的最短路径 跟迪杰斯特拉算法很相似
 * 假设v0点为中介点，找到与v0相邻的两个不同点 假设是v1，v2 则计算<v1,v0>+<v0,v2> 和 <v1,v2>哪个小
 * 把这个较小值记录下来，并在另外一个地方记录下来两点间通过哪个中介点能够得到较小值。如果中介点是自己则说明没有中介点
 */
@Data
public class Floyd {

    private int[][] distance;

    private String[][] wayPoint = new String[7][7];

    public Floyd(Graph graph) {
        distance = graph.getEdges();
        //处理distance数组 使得自己到自己是0  不可达的点变成极大值
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance.length; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else if (distance[i][j] == 0) {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 0; i < wayPoint.length; i++) {
            Arrays.fill(wayPoint[i], graph.getVertexList().get(i));
        }
    }

    private void solve(Graph graph) {
        List<String> vertexList = graph.getVertexList();
        for (int i = 0; i < vertexList.size(); i++) {
            //对于中介点 找出它相连的两个不同的点
            for (int j = 0; j < vertexList.size(); j++) {
                if (i != j && distance[i][j] != Integer.MAX_VALUE) {
                    for (int k = 0; k < vertexList.size(); k++) {
                        if (j != k && i != k && distance[i][k] != Integer.MAX_VALUE) {
                            if (distance[j][k] > distance[i][j] + distance[i][k]) {
                                distance[j][k] = distance[i][j] + distance[i][k];
                                distance[k][j] = distance[i][j] + distance[i][k];
                                wayPoint[j][k] = vertexList.get(i);
                                wayPoint[k][j] = vertexList.get(i);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(7);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        graph.insertVertex("G");
        graph.insertEdge("A", "B", 5);
        graph.insertEdge("A", "C", 7);
        graph.insertEdge("A", "G", 2);
        graph.insertEdge("B", "D", 9);
        graph.insertEdge("B", "G", 3);
        graph.insertEdge("C", "E", 8);
        graph.insertEdge("D", "F", 4);
        graph.insertEdge("E", "F", 5);
        graph.insertEdge("E", "G", 4);
        graph.insertEdge("F", "G", 6);
        Floyd floyd = new Floyd(graph);
        floyd.solve(graph);
        for (int i = 0; i < floyd.distance.length; i++) {
            System.out.println(Arrays.toString(floyd.distance[i]));
        }

        for (int i = 0; i < floyd.wayPoint.length; i++) {
            System.out.println(Arrays.toString(floyd.wayPoint[i]));
        }
    }
}
