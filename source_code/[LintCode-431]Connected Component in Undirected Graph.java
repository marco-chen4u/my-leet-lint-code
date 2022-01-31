/***
 * LintCode 431. Connected Component in Undirected Graph
Find connected component in undirected graph.
Each node in the graph contains a label and a list of its neighbors.

(A connected component of an undirected graph is a subgraph 
in which any two vertices are connected to each other by paths, 
and which is connected to no additional vertices in the supergraph.)

You need return a list of label set.


Example 1:
    Input: {1,2,4#2,1,4#3,5#4,1,2#5,3}
    Output: [[1,2,4],[3,5]]
    Explanation:
        1------2  3
         \     |  | 
          \    |  |
           \   |  |
            \  |  |
              4   5
	      
Example 2:
    Input: {1,2#2,1}
    Output: [[1,2]]
    Explanation:
        1--2

Clarification
    Learn more about representation of graphs

Notice
    Nodes in a connected component should sort by label in ascending order. 
    Different connected components can be in any order.
***/

/**
* Definition for Undirected graph.
* class UndirectedGraphNode {
*     int label;
*     ArrayList<UndirectedGraphNode> neighbors;
*     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
* };
*/
//version-1 : BFS
public class Solution {
    /*
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // check corner cases
        if (nodes == null || nodes.size() == 0) {
            return result;
        }
        
        Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
        
        for (UndirectedGraphNode node : nodes) {
            if (!visited.contains(node)) {
                findConnectedSet(nodes, node，visited， result);
            }
        }
        
        return result;
    }
    
    // helper method
    private void findConnectedSet(List<UndirectedGraphNode> nodes, 
                                    UndirectedGraphNode node，
				    Set<UndirectedGraphNode> visited,
				    List<List<Integer>> result) {
        List<Integer> connectedComponentSet = new ArrayList<Integer>();
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        
        queue.offer(node);
        visited.add(node);
        connectedComponentSet.add(node.label);
        
        while (!queue.isEmpty()) {
            UndirectedGraphNode current = queue.poll();
            
            for (UndirectedGraphNode neighbor : current.neighbors) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                
                visited.add(neighbor);
                connectedComponentSet.add(neighbor.label);
                queue.offer(neighbor);
            }
        }
        
        Collections.sort(connectedComponentSet);
        
        result.add(connectedComponentSet);
    }
}

// version-2: Union Find Algorithm
// helper class
class UnionFind {
    // fields
    Map<Integer, Integer> father;

    // constructor
    public UnionFind(Set<Integer> set) {
        father = new HashMap<Integer, Integer>();

        for (Integer i : set) {
            ather.put(i, i);
        }
    }

    // methods
    public int find(int x) {
        int parent = father.get(x);

        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }

        return parent;
    }

    public void union(int x, int y) {
        int parentX = father.get(x);
        int parentY = father.get(y);

        if (parentX != parentY) {
            father.put(parentX, parentY);
        }
    }
}
 
 class Solution {
    /*
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */ 
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // check corner case
        if (nodes == null || nodes.size() == 0) {
            return result;
        }

        // get all nodes inside this graph
        Set<Integer> set = new HashSet<Integer>();
        for (UndirectedGraphNode node : nodes) {
            set.add(node.label);
            for (UndirectedGraphNode neighbor : node.neighbors) {
                set.add(neighbor.label);
            }
        }

        // construct a UnionFind with all nodes
        UnionFind unionFind = new UnionFind(set);

        for (UndirectedGraphNode current : nodes) {
            for (UndirectedGraphNode neighbor : current.neighbors) {
                int currentParent = unionFind.find(current.label);
                int neighborParent = unionFind.find(neighbor.label);

                if (currentParent != neighborParent) {
                    unionFind.union(currentParent, neighborParent);
                }
            }
        }

        findConnectedComponentSet(result, set, unionFind);

        return result;
    }

    // helper methods
    private void findConnectedComponentSet(List<List<Integer>> result, 
                                            Set<Integer> set, 
                                            UnionFind unionFind) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

        for (Integer i : set) {
            int parent = unionFind.find(i);

            if (!map.containsKey(parent)) {
                map.put(parent, new ArrayList<Integer>());
            }

            List<Integer> connectedComponentSet = map.get(parent);
            connectedComponentSet.add(i);
        }

        for (List<Integer> value : map.values()) {
            Collections.sort(value);
            result.add(new ArrayList<Integer>(value));
        }		
    }
 }
