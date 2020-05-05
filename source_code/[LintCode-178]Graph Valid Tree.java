/***
* LintCode 178. Graph Valid Tree
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to check whether these edges make up a valid tree.
Example
	Example 1:
		Input: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
		Output: true.

	Example 2:
		Input: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
		Output: false.

Notice
	You can assume that no duplicate edges will appear in edges. 
	Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
***/
//version-2: union find
public class Solution {
    // inner class
    class UnionFind {
        // field
        private int[] father;        
        // construction
        public UnionFind(int size) {
            father = new int[size];
            for (int i = 0; i < size; i++) {
                father[i] = i;
            }
        }
        
        //methods
        public int find(int x) {
            if (x == father[x]) {
                return x;
            }
            
            return father[x] = find(father[x]);// compressed find
        }
        
        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            
            if (parentX != parentY) {
                father[parentX] = parentY;
            }
        }
    } 
	
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        // check corner case
        if (n == 0 || edges == null) {
            return false;
        }
        
        if (edges.length != n - 1) {
            return false;
        }
        
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            int source = edge[0];
            int destination = edge[1];
            
            int parentSource = unionFind.find(source);
            int parentDestination = unionFind.find(destination);
            
            if (parentSource == parentDestination) {// already exists
                return false;// cycle path
            }
            
            unionFind.union(parentSource, parentDestination);
        }
        
        return true;
    }
}