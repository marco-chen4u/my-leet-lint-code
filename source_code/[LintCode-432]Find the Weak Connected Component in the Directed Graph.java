/***
* LintCode 432. Find the Weak Connected Component in the Directed Graph
Find the number Weak Connected Component in the directed graph. 
Each node in the graph contains a label and a list of its neighbors. 
(a weak connected component of a directed graph is a maximum subgraph 
in which any two vertices are connected by direct edge path.)

Example
	Example 1:
		Input: {1,2,4#2,4#3,5#4#5#6,5}
		Output: [[1,2,4],[3,5,6]]
		Explanation:
		  1----->2    3-->5
		   \     |        ^
			\    |        |
			 \   |        6
			  \  v
			   ->4
	Example 2:
		Input: {1,2#2,3#3,1}
		Output: [[1,2,3]]

Notice
	Sort the elements of a component in ascending order.
***/
/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
 
//helper class
class UnionFind {
    // fields
    Map<Integer, Integer> father;
    
    // constructor
    public UnionFind(Set<Integer> set) {
        father = new HashMap<Integer, Integer>();
        for (Integer num : set) {
            father.put(num, num);
        }
    }
    
    //methods
    public int find(int x) {
        int parent = father.get(x);
        
        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }
        
        return parent;
    }
    
    public void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        
        if (parentX != parentY) {
            father.put(parentX, parentY);
        }
    }
}

public class Solution {    
    /*
     * @param nodes: a array of Directed graph node
     * @return: a connected set of a directed graph
     */
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // check corner case
        if (nodes == null || nodes.size() == 0) {
            return result;
        }
        
        Set<Integer> set = new HashSet<Integer>();
        for (DirectedGraphNode node : nodes) {
            set.add(node.label);
            for (DirectedGraphNode neighbor : node.neighbors) {
                set.add(neighbor.label);
            }
        }
        
        UnionFind unionFind = new UnionFind(set);
        
        for (DirectedGraphNode current : nodes) {
            for (DirectedGraphNode neighbor : current.neighbors) {
                int currentParent = unionFind.find(current.label);
                int neighborParent = unionFind.find(neighbor.label);
                
                if (currentParent != neighborParent) {
                    unionFind.union(current.label, neighbor.label);
                }
            }
        }
        
        findConnectedComponent(result, set, unionFind);
        
        return result;
    }	

    //helper method
    private void findConnectedComponent(List<List<Integer>> result, 
                                            Set<Integer> set, 
                                            UnionFind unionFind) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        
        for (Integer i : set) {
            int parent = unionFind.find(i);
            
            if (!map.containsKey(parent)) {
                map.put(parent, new ArrayList<Integer>());
            }
            
            List<Integer> connectedComponent = map.get(parent);
            connectedComponent.add(i);
        }
        
        for (List<Integer> connectedComponentList : map.values()) {
            Collections.sort(connectedComponentList);
            result.add(new ArrayList<Integer>(connectedComponentList)); // deep copy
        }
    }
}