package algorithm.MST;

import lombok.Data;
import structure.graph.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * prim算法是图论中的一种算法，在加权连通图里构建最小生成树
 * prim算法也称加点法，经典问题是构成权值总和最小的连通图
 */
@Data
public class Prim {

    private List<String> vertexList;

    private int totalEdgeValue;

    public Prim() {
        vertexList = new ArrayList<>();
    }

    /**
     * 先选择一个点，放入集合。
     * 遍历这个点上的所有边，选出权值最小的一条把相连的点加入集合
     * 遍历集合中点连的所有边，选出权值最小的一条把相连的点加入集合
     * 重复上面的步骤 直到所有的点都被访问完
     */
    private void solve(Graph graph) {
        List<String> originalVertexList = graph.getVertexList();
        String startVertex = originalVertexList.get(0);
        vertexList.add(startVertex);
        graph.getIsVisited()[0] = true;
        while (vertexList.size() < originalVertexList.size()) {
            String shortestVertex = getShortestVertex(graph);
            if (shortestVertex == null) {
                return;
            }
            vertexList.add(shortestVertex);
            graph.getIsVisited()[originalVertexList.indexOf(shortestVertex)] = true;
        }
    }

    /**
     * 根据边集合获取最短的路径
     */
    private String getShortestVertex(Graph graph) {
        if (vertexList.size() == 0) {
            return null;
        }
        int path = 10000;
        String resultVertex = null;
        String startVertex = null;
        List<String> originalVertexList = graph.getVertexList();
        for (String vertex : vertexList) {
            int i = originalVertexList.indexOf(vertex);

            for (int j = 0; j < graph.getEdges()[i].length; j++) {
                //要没有访问过的节点 且不能自己到自己
                if (graph.getIsVisited()[j] || originalVertexList.get(j).equals(startVertex)) {
                    continue;
                }
                int edge = graph.getEdges()[i][j];
                if (edge != 0 && edge < path) {
                    startVertex = vertex;
                    resultVertex = originalVertexList.get(j);
                    path = edge;
                }
            }
        }
        totalEdgeValue += path;
        System.out.println(startVertex + "->" + resultVertex);
        return resultVertex;
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
        Prim prim = new Prim();
        prim.solve(graph);
        System.out.println(prim.totalEdgeValue);
    }
}
