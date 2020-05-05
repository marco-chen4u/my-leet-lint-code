/***
 * LintCode 431. Connected Component in Undirected Graph
Find connected component in undirected graph.
Each node in the graph contains a label and a list of its neighbors.
(A connected component of an undirected graph is a subgraph 
in which any two vertices are connected to each other by paths, 
and which is connected to no additional vertices in the supergraph.)

You need return a list of label set.

Example
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
                findConnectedSet(result, visited, nodes, node);
            }
        }
        
        return result;
    }
    
    // helper method
    private void findConnectedSet(List<List<Integer>> result, 
                                    Set<UndirectedGraphNode> visited, 
                                    List<UndirectedGraphNode> nodes, 
                                    UndirectedGraphNode node) {
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