package algorithm.MST;

import lombok.Data;
import structure.graph.Graph;

import java.util.Arrays;
import java.util.List;

/**
 * 迪杰斯特拉算法 用于解决两点之间最短距离
 * 维护一个数组，用于表示起始点到其余可达点的最短距离
 * 每次都考察未被考察过且最短路径的点
 * 比如当前起始点是v，考察的点是v1
 * 假设v1和v2是可达的 则比较dis(v,v1)+dis(v1,v2) 和 dis(v,v2)的值 把小的值更新
 * <p>
 * 解决的问题如：6个邮差从A点出发，去BCDEFG点送信。怎么走才能使得送信路径最短
 * 这题的特点是6个邮差，不是1个邮差
 */
@Data
public class Dijkstra {

    private int[] distance;

    private boolean[] visited;

    Dijkstra() {
        visited = new boolean[7];
    }

    /**
     * 假设是从v0开始寻找
     * 1、先找到一个最近的顶点假设是v1
     * 2、看看v1跟哪些顶点连通，假设v1和v2连通,如果v0和v2也连通的话，则比较<v0,v1>+<v1,v2>和<v0,v2>的距离大小
     * 3、把相对较小的值更新dis数组
     * 4、遍历完v1连通的节点以后，可以把v1的访问属性改为已访问
     * 5、以此类推从步骤1开始，直到所有节点的访问属性都变成已访问（假设图中所有的节点都是连通）
     */
    public void solve(Graph graph, String startVertex) {
        //初始化dis数组，没有直接连通的就写0
        List<String> vertexList = graph.getVertexList();
        int startVertexIndex = vertexList.indexOf(startVertex);
        distance = graph.getEdges()[startVertexIndex];
        //邮差送信次数
        int n = 0;
        while (n <= 6) {
            int path = 9999;
            int vertexIndex = -1;
            for (int i = 0; i < distance.length; i++) {
                //自己到自己不行
                if (i == startVertexIndex) {
                    continue;
                }
                if (!visited[i] && distance[i] != 0 && distance[i] < path) {
                    path = distance[i];
                    vertexIndex = i;
                }
            }

            if (vertexIndex == -1) {
                break;
            }
            int[] tempEdges = graph.getEdges()[vertexIndex];

            //遍历tempEdges找出连通的点
            for (int i = 0; i < tempEdges.length; i++) {
                if (tempEdges[i] == 0 || i == startVertexIndex) {
                    continue;
                }
                if (distance[i] == 0 || distance[vertexIndex] + tempEdges[i] < distance[i]) {
                    System.out.println(startVertex + "->" + vertexList.get(i) + "=" + startVertex + "->" + vertexList.get(vertexIndex) + "+" + vertexList.get(vertexIndex) + "->" + vertexList.get(i));
                    distance[i] = distance[vertexIndex] + tempEdges[i];
                }
            }

            //设置已访问
            visited[vertexIndex] = true;
            n++;
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
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.solve(graph, "G");
        System.out.println(Arrays.toString(dijkstra.getDistance()));
    }
}
