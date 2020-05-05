/***
* LintCode 590. Connecting Graph II
Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.

You need to support the following method:
	-connect(a, b), an edge to connect node a and node b
	-query(a), Returns the number of connected component nodes which include node a.
Example
	Example 1:
		Input:
			ConnectingGraph2(5)
			query(1)
			connect(1, 2)
			query(1)
			connect(2, 4)
			query(1)
			connect(1, 4)
			query(1)
		Output:
			[1,2,3,3]
	Example 2:
		Input:
			ConnectingGraph2(6)
			query(1)
			query(2)
			query(1)
			query(5)
			query(1)
		Output:
			[1,1,1,1,1]
***/
public class ConnectingGraph2 {
	// fields
    int[] fathers;
    int[] components;
    
    /*
    * @param n: An integer
    */public ConnectingGraph2(int n) {
        // do intialization if necessary
        fathers = new int[n + 1];
        components = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            fathers[i] = i;
            components[i] = 1;
        }
    }
    
    // helper methods
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
            fathers[parentX] = parentY;// merge
            int sum = components[parentX] + components[parentY];
            components[parentY] = sum;
        }
    }
    
    private int getCountOfCurrentComponent(int x) {
        int parent = find(x);
        return components[parent];
    }

	// public methods
    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        union(a, b);
    }

    /*
     * @param a: An integer
     * @return: An integer
     */
    public int query(int a) {
        int parent = find(a);
        return getCountOfCurrentComponent(parent);
    }
}
