/***
* LintCode 589. Connecting Graph
Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.
You need to support the following method:
	-connect(a, b), add an edge to connect node a and node b`.
	-query(a, b), check if two nodes are connected
	
Example
	Example 1:
		Input:
			ConnectingGraph(5)
			query(1, 2)
			connect(1, 2)
			query(1, 3) 
			connect(2, 4)
			query(1, 4) 
		Output:
			[false,false,true]

	Example 2:
		Input:
			ConnectingGraph(6)
			query(1, 2)
			query(2, 3)
			query(1, 3)
			query(5, 6)
			query(1, 4)
		Output:
			[false,false,false,false,false]

***/
public class ConnectingGraph {
    int[] fathers;
    /*
    * @param n: An integer
    */public ConnectingGraph(int n) {
        // do intialization if necessary
        fathers = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            fathers[i] = i;
        }
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
            fathers[parent] = superParent;// compress it
            parent = tmp;
        }
        
        return superParent;
    }
    
    private boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        if (isConnected(a, b)) {
            return;
        }
        
        int parentA = find(a);
        int parentB = find(b);
        
        if (parentA != parentB) {
            fathers[parentA] = parentB;// union
        }
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {
        return isConnected(a, b);
    }
}