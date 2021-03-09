package algorithm.MST;

import lombok.Data;
import structure.graph.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 克鲁斯卡尔算法
 * 又称加边法，每次往最小生成树的集合中加入最短的边，但是每次都需要保证加入的边不构成回路（不破坏生成树结构）
 */
@Data
public class Kruskal {

    private Set<String> vertexList;

    private int totalEdgeValue;

    private int edgeNum;

    /**
     * 记录某一个顶点与哪些顶点连通 用来判断是否构成回路
     */
    private Map<String, Set<String>> vertexConnected;


    public Kruskal(Graph graph) {
        vertexList = new HashSet<>();
        vertexConnected = new HashMap<>();
        graph.getVertexList().forEach((vertex) -> vertexConnected.put(vertex, new HashSet<>()));
    }

    public void solve(Graph graph) {
        //把所有的边按照权值大小 从小到大进行排序
        int[][] edges = graph.getEdges();
        List<String> originVertexList = graph.getVertexList();
        //边的数量一定是顶点个数-1
        while (edgeNum < originVertexList.size() - 1) {
            int shortestPath = 10000;
            String startVertex = null;
            String endVertex = null;
            for (int i = 0; i < edges.length; i++) {
                for (int j = 0; j < edges[i].length; j++) {
                    //两个点不能已经连通
                    if (i == j || vertexConnected.get(originVertexList.get(i)).contains(originVertexList.get(j))) {
                        continue;
                    }
                    if (edges[i][j] != 0 && edges[i][j] < shortestPath) {
                        shortestPath = edges[i][j];
                        startVertex = originVertexList.get(i);
                        endVertex = originVertexList.get(j);
                    }
                }
            }
            if (startVertex == null && endVertex == null) {
                break;
            }
            totalEdgeValue += shortestPath;
            vertexList.add(startVertex);
            vertexList.add(endVertex);

            //维护可达节点
            Set<String> startVertexConnected = vertexConnected.get(startVertex);
            Set<String> newStartVertexConnected = new HashSet<>();
            startVertexConnected.add(endVertex);
            newStartVertexConnected.add(endVertex);
            for (String s : startVertexConnected) {
                newStartVertexConnected.addAll(vertexConnected.get(s));
                vertexConnected.get(s).add(endVertex);
            }
            vertexConnected.put(startVertex, newStartVertexConnected);


            Set<String> endVertexConnected = vertexConnected.get(endVertex);
            Set<String> newEndVertexConnected = new HashSet<>();
            endVertexConnected.add(startVertex);
            newEndVertexConnected.add(startVertex);
            for (String s : endVertexConnected) {
                newEndVertexConnected.addAll(vertexConnected.get(s));
                vertexConnected.get(s).add(startVertex);
            }

            vertexConnected.put(endVertex, newEndVertexConnected);

            System.out.println(startVertex + "->" + endVertex);
            edgeNum++;
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
        Kruskal kruskal = new Kruskal(graph);
        kruskal.solve(graph);
        System.out.println(kruskal.getTotalEdgeValue());
    }
}
