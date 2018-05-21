package org.songdan.tij.algorithm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Joiner;

/**
 * 带权重的最短路径算法（迪克斯特拉算法） <br/>
 * 1.找出从起点出发最便宜的节点 <br/>
 * 2.计算从此节点到它邻居的开销，如果有更小的开销，更新开销（更新父节点） <br/>
 * 3.标记此节点 <br/>
 * 4.重复1，2,3 <br/>
 * 5.计算最短路径
 *
 * @author song dan
 * @since 20 五月 2018
 */
public class WeightShortestPath {

    private Map<Node, Map<Node, Integer>> graph;

    private HashMap<String, Node> nodeMap = new HashMap<>();

    private Set<Node> computed = new HashSet<>();

    private Node start;

    private Node end;

    public WeightShortestPath(Map<Node, Map<Node, Integer>> graph, Node start, Node end) {
        this.graph = graph;
        this.start = start;
        this.end = end;
        graph.keySet().forEach(node -> {
            if (node.equals(start)) {
                node.cost = 0;
                node.parent = null;
            } else {
                node.cost = Integer.MAX_VALUE;
            }
            nodeMap.put(node.key, node);
            graph.get(node).keySet().forEach(cur -> {
                cur.cost = Integer.MAX_VALUE;
                nodeMap.put(cur.key, cur);
            });
        });

    }

    public static void main(String[] args) {
        Map<Node, Map<Node, Integer>> graph = new HashMap<>();
        Node start = new Node("start");
        Node end = new Node("end");
        Node A = new Node("A");
        Node B = new Node("B");

        graph.put(start, new HashMap<Node, Integer>() {
            {
                put(A, 6);
                put(B, 2);
            }
        });
        graph.put(A, new HashMap<Node, Integer>() {
            {
                put(end, 1);
                put(B, 1);
            }
        });
        graph.put(B, new HashMap<Node, Integer>() {
            {
                put(A, 3);
                put(end, 5);
            }
        });

        new WeightShortestPath(graph, start, end).shortestNode();

		Map<Node, Map<Node, Integer>> graph1 = new HashMap<>();
		Node start1 = new Node("start");
		Node end1 = new Node("end");
		Node A1 = new Node("A");
		Node B1 = new Node("B");
		Node C1 = new Node("C");
		Node D1 = new Node("D");

		graph1.put(start1, new HashMap<Node, Integer>() {
			{
				put(A1, 5);
				put(B1, 2);
			}
		});
		graph1.put(A1, new HashMap<Node, Integer>() {
			{
				put(C1, 2);
				put(D1, 4);
			}
		});
		graph1.put(B1, new HashMap<Node, Integer>() {
			{
				put(A1, 8);
				put(C1, 7);
			}
		});
		graph1.put(C1, new HashMap<Node, Integer>() {
			{
				put(end1, 1);
				put(D1, 6);
			}
		});
		graph1.put(D1, new HashMap<Node, Integer>() {
			{
				put(end1, 3);
			}
		});
		new WeightShortestPath(graph1, start1, end1).shortestNode();
	}

    public String shortestNode() {
        Node node;
        while (!(node = findNextNode()).equals(end)) {
            if (!computed.contains(node)) {
                // 从途中获取当前节点的邻居
                Map<Node, Integer> neighbors = graph.get(node);
                // 不能直接到达的节点开销设置为Integer.MAX，父节点设置为null
                for (Map.Entry<Node, Integer> nodeIntegerEntry : neighbors.entrySet()) {
                    Node neighbor = nodeIntegerEntry.getKey();
                    Node neighborNode = nodeMap.get(neighbor.key);
                    // 计算开销，开销设置为权重+当前节点的cost，如果是更小的开销，更新开销和父节点
                    int preCost = neighborNode.cost;
                    int newCost = node.cost + nodeIntegerEntry.getValue();
                    if (newCost < preCost) {
                        // 更新开销和父节点
                        neighborNode.cost = newCost;
                        neighborNode.parent = node;
                    }
                }
                // 标记cur节点为处理过的节点
                computed.add(node);
            }
        }
        System.out.println(node.path() + ", cost : " + node.cost);
        return node.path();

    }

    private Node findNextNode() {
        return nodeMap.entrySet().stream().filter(entry-> !computed.contains(entry.getValue())).sorted(Comparator.comparingInt(e -> e.getValue().cost)).findFirst().get()
                .getValue();
    }

    static class Node {
        private Node parent;

        private int cost;

        private String key;

        public Node(String key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Node node = (Node) o;

            return key != null ? key.equals(node.key) : node.key == null;
        }

        @Override
        public int hashCode() {
            return key != null ? key.hashCode() : 0;
        }

        public int getCost() {
            return cost;
        }

        public String path() {
            if (parent == null) {
                return key;
            }
            return Joiner.on("<-").join(key, parent.path());
        }

        @Override
        public String toString() {
            return "Node{" + "key='" + key + '\'' + '}';
        }
    }

}
