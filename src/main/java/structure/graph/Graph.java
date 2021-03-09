package structure.graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 图
 * 由 顶点 边 构成
 * 其中还有路径的概念
 */
@Data
public class Graph {

    /**
     * 放置顶点
     */
    private List<String> vertexList;

    /**
     * 使用二维矩阵表示边
     */
    private int[][] edges;

    /**
     * 边的数量
     */
    private int edgeNum;

    /**
     * 某一个点是否被访问过 用于遍历图
     */
    private boolean[] isVisited;

    /**
     * 初始化图
     *
     * @param vertexNum 节点数目
     */
    public Graph(int vertexNum) {
        this.edges = new int[vertexNum][vertexNum];
        vertexList = new ArrayList<>(vertexNum);
        isVisited = new boolean[vertexNum];
    }

    /**
     * 插入节点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param vertex1 顶点1
     * @param vertex2 顶点2
     * @param weight  边的权值
     */
    public void insertEdge(String vertex1, String vertex2, int weight) {
        int v1 = vertexList.indexOf(vertex1);
        int v2 = vertexList.indexOf(vertex2);
        if (v1 == -1 || v2 == -1) {
            System.out.println("找不到节点");
            return;
        }
        //此处由于是无向图 所以矩阵是对称的
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgeNum++;
    }

    public void showGraph() {
        int size = vertexList.size();
        for (int i = 0; i < size; i++) {
            System.out.println(Arrays.toString(edges[i]));
        }
    }

    /**
     * 深度遍历图
     * 用递归的方式一直往下寻找 找不到有未访问的邻接的顶点 就回退
     *
     * @param preVertex   前一个节点（只是用来表示遍历的路径，没什么实际用处）
     * @param startVertex 开始寻找的节点
     */
    public void dfs(String preVertex, String startVertex) {
        int index = vertexList.indexOf(startVertex);
        if (!preVertex.equals(startVertex)) {
            System.out.println(preVertex + "->" + startVertex);
        }
        isVisited[index] = true;
        int size = vertexList.size();
        for (int i = 0; i < size; i++) {
            if (edges[index][i] != 0 && !isVisited[i]) {
                dfs(startVertex, vertexList.get(i));
            }
        }
    }

    /**
     * 广度遍历图
     * 把当前节点的所有连接节点都找出来 然后往下一个节点找
     *
     * @param queue       这个参数必须是上下文 需要细品
     * @param startVertex 开始寻找的节点
     */
    public void bfs(LinkedList<String> queue, String startVertex) {
        if (queue == null) {
            queue = new LinkedList<>();
        }
        int index = vertexList.indexOf(startVertex);
        isVisited[index] = true;
        int size = vertexList.size();
        for (int i = 0; i < size; i++) {
            if (edges[index][i] != 0 && !isVisited[i]) {
                queue.add(vertexList.get(i));
                System.out.println(startVertex + "->" + vertexList.get(i));
                isVisited[i] = true;
            }
        }
        while (!queue.isEmpty()) {
            bfs(queue, queue.pop());
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.insertVertex("1");
        graph.insertVertex("2");
        graph.insertVertex("3");
        graph.insertVertex("4");
        graph.insertVertex("5");
        graph.insertVertex("6");
        graph.insertVertex("7");
        graph.insertVertex("8");
        graph.insertEdge("1", "2", 1);
        graph.insertEdge("1", "3", 1);
        graph.insertEdge("2", "4", 1);
        graph.insertEdge("2", "5", 1);
        graph.insertEdge("4", "8", 1);
        graph.insertEdge("5", "8", 1);
        graph.insertEdge("3", "6", 1);
        graph.insertEdge("3", "7", 1);
        graph.insertEdge("6", "7", 1);

        graph.showGraph();
        System.out.println("深度优先");
        graph.dfs("1", "1");
        graph.setIsVisited(new boolean[8]);
        System.out.println("广度优先");
        graph.bfs(null, "1");
    }

}
