/***
* LeetCode 133. Clone Graph
Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}

Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

Example 
    Input:
        {1,2,4#2,1,4#4,1,2}
    Output: 
        {1,2,4#2,1,4#4,1,2}
    Explanation:
        1------2  
         \     |  
          \    |  
           \   |  
            \  |  
              4   
***/
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/
//version-1: BFS
class Solution {
    public Node cloneGraph(Node node) {
        // check corner cases
        if (node == null) {
            return null;
        }
        
        if (node.neighbors.isEmpty()) {
            return getClone(node);
        }
        
        // regular case
        List<Node> nodeList = traverse(node);
        
        Map<Node, Node> cloneMap = new HashMap<Node, Node>();
        // create node-clone pair and save into cloneMap
        for (Node current : nodeList) {
            cloneMap.put(current, getClone(current));
        }
        
        // build up the relationship for the cloned nodes
        for(Node current : nodeList) {
            Node cloneNode = cloneMap.get(current);
            for (Node neighbor : current.neighbors) {
                Node cloneNeighbor = cloneMap.get(neighbor);
                cloneNode.neighbors.add(cloneNeighbor);
            }
        }
        
        return cloneMap.get(node);
    }
    
    // helper method
    private Node getClone(Node node) {
        return new Node(node.val);
    }
    
    List<Node> traverse(Node node) {
        List<Node> result = new ArrayList<Node>();
        
        Queue<Node> queue = new LinkedList<Node>();
        Set<Node> visited = new HashSet<Node>();
        
        queue.offer(node);
        visited.add(node);
        
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            result.add(current);
            
            for (Node neighbor : current.neighbors) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                
                queue.offer(neighbor);
                visited.add(neighbor);
            }
        }
        
        return result;
    }
}

