package org.algorithm.algorithm.utils;

import org.algorithm.algorithm.structures.basic.UndirectedGraphNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author KaiyangLyu
 * @version 1.0
 * @name UndirectedGraphNodeUtil
 * @date 2020/6/9 11:27
 **/
public class UndirectedGraphNodeUtil {

    /*
    UndirectedGraphNode
    For example:

    {1,2,4#2,1,4#3,5#4,1,2#5,3} represents follow graph:

    1------2  3
     \     |  |
      \    |  |
       \   |  |
        \  |  |
          4   5

    we use # to split each node information.

    1,2,4 represents that 2, 4 are 1's neighbors

    2,1,4 represents that 1, 4 are 2's neighbors

    3,5 represents that 5 is 3's neighbor

    4,1,2 represents that 1, 2 are 4's neighbors

    5,3 represents that 3 is 5's neighbor
     */
    public static String serialize(List<UndirectedGraphNode> nodes) {
        if (nodes == null) return null;
        if (nodes.size() == 0) return null;

        StringBuilder sb = new StringBuilder();
        for (UndirectedGraphNode node : nodes) {
            sb.append("#".concat(String.valueOf(node.label)));
            for (UndirectedGraphNode neighbor : node.neighbors)
                sb.append(",".concat(String.valueOf(neighbor.label)));
        }

        sb.replace(0, 1, "{").append("}");

        return sb.toString();
    }

    public static List<UndirectedGraphNode> deserialize(String data) {
        List<UndirectedGraphNode> nodes = null;
        if (data == null || "".equals(data)) return null;

        data = data.substring(1, data.length() - 1);
        nodes = new ArrayList<>();
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
        String[] nodes_info = data.split("#");

        for (String node_info : nodes_info) {
            String[] neighbor_info = node_info.split(",");
            UndirectedGraphNode curr = new UndirectedGraphNode(Integer.parseInt(neighbor_info[0]));
            nodes.add(curr);
            map.put(curr.label, curr);
        }

        for (String node_info : nodes_info) {
            String[] neighbor_info = node_info.split(",");
            UndirectedGraphNode curr = map.get(Integer.parseInt(neighbor_info[0]));
            for (int i = 1; i < neighbor_info.length; i++) {
                int label = Integer.parseInt(neighbor_info[i]);
                curr.neighbors.add(map.get(label));
            }
        }

        return nodes;
    }
}
