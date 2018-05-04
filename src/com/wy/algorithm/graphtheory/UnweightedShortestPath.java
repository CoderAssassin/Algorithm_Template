package com.wy.algorithm.graphtheory;

import java.util.*;

/**
 * @author aliyang
 * @date 18-5-4 下午5:06
 * 无权最短路径算法：时间复杂度为O(V+E)
 * 算法思想：类似广度优先搜索，开始源节点s的dist为0，其他所有的节点的dist为无穷大，遍历s的邻接节点并计算
 * dist值并且更新path值，将邻接节点全部加入到队列中，直到最后一层的节点被处理完，从后往前取path就能得到一条
 * 完整的最短路径
 */
public class UnweightedShortestPath {

    private static final int INFINITY=Integer.MAX_VALUE;

    /**
     * 顶点累
     */
    private class Vertex{
        private String vertexLabel;//顶点标签
        private int dist;//从源点出发到该顶点的距离
        private List<Edge> adjEdges;//邻接边
        private Vertex path;//最短路径的前一个顶点

        public Vertex(String vertexLabel){
            this.vertexLabel=vertexLabel;
            dist=Integer.MAX_VALUE;
            adjEdges=new LinkedList<Edge>();
        }

    }

    /**
     * 边类
     */
    private class Edge{
        Vertex endVertex;
        public Edge(Vertex endVertex){
            this.endVertex=endVertex;
        }
    }

    Map<String,Vertex> directedGraph;

    public UnweightedShortestPath(String graphContent){
        directedGraph=new HashMap<>();
        buildGraph(graphContent);
    }

    /**
     * 构造图
     * @param graphContent 图的内容,每行代表一个节点边，信息用","隔开，即 开始节点,结束节点
     */
    private void buildGraph(String graphContent){
        String[] lines=graphContent.split("\n");
        UnweightedShortestPath.Vertex startNode,endNode;
        String startNodeLabel,endNodeLabel;
        UnweightedShortestPath.Edge e;
        for (int i=0;i<lines.length;i++){
            String[] nodesInfo=lines[i].split(",");
            startNodeLabel=nodesInfo[1];//开始节点标签
            endNodeLabel=nodesInfo[2];//结束节点标签
            startNode=directedGraph.get(startNodeLabel);
            if (startNode==null){//如果图中没有这个顶点，新建一个
                startNode=new UnweightedShortestPath.Vertex(startNodeLabel);
                directedGraph.put(startNodeLabel,startNode);
            }
            endNode=directedGraph.get(endNodeLabel);
            if (endNode==null){//若结束点不存在
                endNode=new UnweightedShortestPath.Vertex(endNodeLabel);
                directedGraph.put(endNodeLabel,endNode);
            }

//            创建新的边
            e=new UnweightedShortestPath.Edge(endNode);
            startNode.adjEdges.add(e);//添加新的边后，开始顶点的邻接边增加
        }
    }

    /**
     * 无权最短路径算法
     * @param s 起始点标签
     */
    public void unWeightedShoritestPath(Vertex s){
        Queue<Vertex> queue=new LinkedList<>();

        /**
         * 每个顶点的dist初始化为INFINITY
         */
        for (Vertex vertex:directedGraph.values())
            vertex.dist=INFINITY;

//        起始点的dist设定为0
        directedGraph.get(s.vertexLabel).dist=0;
        queue.offer(s);

//        开始遍历队列
        while (!queue.isEmpty()){
            Vertex v= queue.poll();

            List<Edge> adjEdge=v.adjEdges;
            for (Edge e:adjEdge){
                Vertex endVertex=e.endVertex;
                if (endVertex.dist==INFINITY){
                    endVertex.dist=v.dist+1;//邻接的节点的dist+1
                    endVertex.path=v;//邻接的节点的父节点设为当前节点
                    queue.offer(endVertex);//将邻接节点入队
                }
            }
        }
    }
}
