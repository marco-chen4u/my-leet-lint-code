/***
* LintCode 814. Shortest Path in Undirected Graph
Given an undirected graph in which each edge's length is 1, and two nodes from the graph. 
Return the length of the shortest path between two given nodes.

Example
	Example 1:
		Input: graph = {1,2,4#2,1,4#3,5#4,1,2#5,3}, node1 = 3, node2 = 5
		Output: 1
		Explanation:
		  1------2  3
		   \     |  | 
			\    |  |
			 \   |  |
			  \  |  |
				4   5
			
	Example 2:
		Input: graph = {1,2,3,4#2,1,3#3,1#4,1,5#5,4}, node1 = 1, node2 = 5
		Output: 2
	
Clarification
	About the representation of graph
***/
/**
 * Definition for graph node.
 * class GraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { 
 *         label = x; neighbors = new ArrayList<UndirectedGraphNode>(); 
 *     }
 * };
 */
//Bidirectional BFS-双向宽度优先搜索 (Bidirectional BFS) 算法
public class Solution {
    public int shortestPath(List<UndirectedGraphNode> graph, 
								UndirectedGraphNode A, UndirectedGraphNode B) {
        int defaultValue = -1, steps = 0;//default value
        // check corner case
        if (graph == null || graph.isEmpty() || A == null || B == null ||
                !graph.contains(A) || !graph.contains(B)) {
            return defaultValue;
        }
		
        if (A.equals(B)) {
            return 0;
        }
        
		// start from A
        Queue<UndirectedGraphNode> queueStart = new LinkedList<UndirectedGraphNode>(); 
        Set<UndirectedGraphNode> visitedStart = new HashSet<UndirectedGraphNode>();
        queueStart.offer(A);
        visitedStart.add(A);
        
		// start from B
        Queue<UndirectedGraphNode> queueEnd = new LinkedList<UndirectedGraphNode>(); 
        Set<UndirectedGraphNode> visitedEnd = new HashSet<UndirectedGraphNode>();
        queueEnd.offer(B);
        visitedEnd.add(B);
        
        while (!queueStart.isEmpty() || !queueEnd.isEmpty()) {
            int size = queueStart.size();
            steps++;
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode current = queueStart.poll();
                for (UndirectedGraphNode next : current.neighbors) {
                    if (visitedStart.contains(next)) {
                        continue;
                    }
                    
                    if (visitedEnd.contains(next)) {
                        return steps;
                    }
                    
                    queueStart.offer(next);
                    visitedStart.add(next);
                }
            }
            
            size = queueEnd.size();
            steps++;
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode current = queueEnd.poll();
                for (UndirectedGraphNode next : current.neighbors) {
                    if (visitedEnd.contains(next)) {
                        continue;
                    }
                    
                    if (visitedStart.contains(next)) {
                        return steps;
                    }
                    
                    queueStart.offer(next);
                    visitedEnd.add(next);
                }
            }
        }
		
        return defaultValue;        
    }
}