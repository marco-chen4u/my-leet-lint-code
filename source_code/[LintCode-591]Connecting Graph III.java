/***
* LintCode 591. Connecting Graph III
Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.

You need to support the following method:
	-connect(a, b), an edge to connect node a and node b
	-query(), Returns the number of connected component in the graph
	
Example
	Example 1:
		Input:
			ConnectingGraph3(5)
			query()
			connect(1, 2)
			query()
			connect(2, 4)
			query()
			connect(1, 4)
			query()
		Output:
			[5,4,3,3]

	Example 2:
		Input:
			ConnectingGraph3(6)
			query()
			query()
			query()
			query()
			query()
		Output:
			[6,6,6,6,6]
***/
public class ConnectingGraph3 {
    // fields
    private int[] fathers;
    private int count;
    
    // constructor
    public ConnectingGraph3(int n) {
        fathers = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            fathers[i] = i;
        }
        
        count = n;
    }
    
    // helper methods
    private int getComponentCount() {
        return count;
    }
    
    private int find(int x) {
        int superParent = fathers[x];
        while (superParent != fathers[superParent]) {
            superParent = fathers[superParent];
        }
        
        int tmp = -1;
        int parent = x;
        while (parent != fathers[parent]) {
            tmp = fathers[parent];
            fathers[parent] = superParent;// compressed
            parent = tmp;
        }
        
        return superParent;
    }
    
    private boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
    
    private void union(int x, int y) {
        if (isConnected(x, y)) {
            return;
        }
        
        int parentX = find(x);
        int parentY = find(y);
        
        if (parentX != parentY) {
            fathers[parentX] = parentY;
            if (count > 1) {
                count--;
            }
        }
    }
    
    /**
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        union(a, b);
    }

    /**
     * @return: An integer
     */
    public int query() {
        return getComponentCount();
    }
}