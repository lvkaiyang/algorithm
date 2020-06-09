package org.algorithm.algorithm.structures.basic;

import java.util.ArrayList;

/**
 * @author KaiyangLyu
 * @version 1.0
 * @name UndirectedGraphNode
 * @date 2020/6/9 11:25
 **/
public class UndirectedGraphNode {
    public int label;
    public ArrayList<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
