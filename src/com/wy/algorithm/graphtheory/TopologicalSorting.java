package com.wy.algorithm.graphtheory;

import java.util.*;

/**
 * @author aliyang
 * @date 18-5-4 下午3:58
 * 拓扑排序：针对有向无环图的顶点的一种排序，若路径从A到B，那么排序中A必定在B前
 * Kahn算法：先找出一个没有入边的顶点，打印该顶点，将其和边从图中去除，对其余部分继续
 * 本代码借助队列来实现，时间复杂度为O(V+E)：
 * Step1：遍历图中所有顶点，将入度为0的顶点入队
 * Step2：从队列中选出一个顶点，打印顶点，更新该顶点的邻接点的入度，将入度变为0的顶点加入队列
 * Step3：重复Step2直到队列为空
 */
public class TopologicalSorting {

    /**
     * 顶点类
     */
    private class Vertex{
        private String vertexLabel;//顶点标识
        private List<Edge> adjEdges;//邻接边列表
        private int inDegree;//顶点的入度

        public Vertex(String vertexLabel){
            this.vertexLabel=vertexLabel;
            inDegree=0;
            adjEdges=new LinkedList<TopologicalSorting.Edge>();
        }
    }

    /**
     * 边类
     */
    private class Edge{
        private Vertex endVertex;//结束点

        public Edge(Vertex endVertex){
            this.endVertex=endVertex;
        }
    }

    private Map<String,Vertex> directedGraph;//有向图

    /**
     * 根据传入的图的内容构造图
     * @param graphContent
     */
    public TopologicalSorting(String graphContent){
        directedGraph=new LinkedHashMap<String, TopologicalSorting.Vertex>();
        buildGraph(graphContent);
    }

    /**
     * 构造图
     * @param graphContent 图的内容,每行代表一个节点边，信息用","隔开，即 开始节点,结束节点
     */
    private void buildGraph(String graphContent){
        String[] lines=graphContent.split("\n");
        Vertex startNode,endNode;
        String startNodeLabel,endNodeLabel;
        Edge e;
        for (int i=0;i<lines.length;i++){
            String[] nodesInfo=lines[i].split(",");
            startNodeLabel=nodesInfo[1];//开始节点标签
            endNodeLabel=nodesInfo[2];//结束节点标签
            startNode=directedGraph.get(startNodeLabel);
            if (startNode==null){//如果图中没有这个顶点，新建一个
                startNode=new Vertex(startNodeLabel);
                directedGraph.put(startNodeLabel,startNode);
            }
            endNode=directedGraph.get(endNodeLabel);
            if (endNode==null){//若结束点不存在
                endNode=new Vertex(endNodeLabel);
                directedGraph.put(endNodeLabel,endNode);
            }

//            创建新的边
            e=new Edge(endNode);
            startNode.adjEdges.add(e);//添加新的边后，开始顶点的邻接边增加
            endNode.inDegree++;//结束点的入度增加
        }
    }

    public void topologicalSorting()throws Exception{
        int count=0;

        Queue<Vertex> queue=new LinkedList<>();//这里使用栈来保存入度为0的节点，可以用队列
//        扫描一遍顶点，将入度为0的顶点入队
        Collection<Vertex> vertexes=directedGraph.values();
        for (Vertex vertex:vertexes)
            if (vertex.inDegree==0)
                queue.offer(vertex);

//        遍历每个入度为0的节点，取其邻接边，更新邻接边结束点的入度
        while (!queue.isEmpty()){
            Vertex v=queue.poll();
            System.out.println(v.vertexLabel+" ");
            count++;
            for (Edge e:v.adjEdges)
                if (--e.endVertex.inDegree==0)
                    queue.offer(e.endVertex);
        }
        if (count!=directedGraph.size())
            throw new Exception("Graphic has crecle");
    }

}
