/***
* LintCode 629. Minimum Spanning Tree
Given a list of Connections, which is the Connection class (the city name at both ends of the edge and a cost between them), find edges that can connect all the cities and spend the least amount.
Return the connects if can connect all the cities, otherwise return empty list.

Example
	Example 1:
		Input:
			["Acity","Bcity",1]
			["Acity","Ccity",2]
			["Bcity","Ccity",3]
		Output:
			["Acity","Bcity",1]
			["Acity","Ccity",2]

	Example 2:
	Input:
			["Acity","Bcity",2]
			["Bcity","Dcity",5]
			["Acity","Dcity",4]
			["Ccity","Ecity",1]
		Output:
			[]
		Explanation:
			No way
	
Notice
	Return the connections sorted by the cost, or sorted city1 name if their cost is same, or sorted city2 if their city1 name is also same.
***/

/**
 * Definition for a Connection.
 * public class Connection {
 *   public String city1, city2;
 *   public int cost;
 *   public Connection(String city1, String city2, int cost) {
 *       this.city1 = city1;
 *       this.city2 = city2;
 *       this.cost = cost;
 *   }
 * }
 */
public class Solution {
    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    public List<Connection> lowestCost(List<Connection> connections) {
        List<Connection> result = new ArrayList<Connection>();
        List<Connection> defaultValue = new ArrayList<Connection>();//default value
        // check corner case
        if (connections == null || connections.isEmpty()) {
            return defaultValue;
        }
        
        int size = connections.size();
        
        Comparator<Connection> myComparator = new Comparator<Connection>() {
            @Override
            public int compare(Connection a, Connection b) {
                if (a.cost != b.cost) {
                    return a.cost - b.cost;
                }
                
                if (a.city1.equals(b.city1)) {
                    return a.city2.compareTo(b.city2);
                }
                
                return a.city1.compareTo(b.city1);
            }
        };
        
        Collections.sort(connections, myComparator);
        
        UnionFind uf = new UnionFind(connections);
        
        for (Connection connection : connections) {
            String city1 = connection.city1;
            String city2 = connection.city2;
            int cost = connection.cost;
            
            boolean isConnected = uf.isCityConnected(city1, city2);
            if (isConnected) {// if already connected
                continue;
            }
            
            // connect and union to point
            uf.connect2City(city1, city2);
            result.add(connection);
        }
        
        
        if (uf.getComponentCount() != 1) {
            return defaultValue;
        }
        
        return result;
    }
}

// helper class
class UnionFind {
    // fields
    private Map<String, Integer> cityMapping;
    private int[] fathers;
    private int count;
    private int size;    
    // constructor
    public UnionFind(List<Connection> connections) {
        this.cityMapping = new HashMap<String, Integer>();        
        int index = 0;
        for (Connection connection : connections) {
            if (!cityMapping.containsKey(connection.city1)) {
                cityMapping.put(connection.city1, ++index);
            }
            
            if (!cityMapping.containsKey(connection.city2)) {
                cityMapping.put(connection.city2, ++index);
            }
        }
        
        this.size = index;
        this.count = 0;
        this.fathers = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            fathers[i] = i;
            this.count++;
        }
    }
    
    // internal helper methods
    private int find(int x) {
        int superParent = fathers[x];
        while (superParent != fathers[superParent]) {
            superParent = fathers[superParent];
        }
        
        int tmp = -1;
        int parent = x;
        while (parent != fathers[parent]) {
            tmp = fathers[parent];
            fathers[parent] = superParent; //compress
            parent = tmp;
        }
        
        return superParent;
    }
    
    private boolean isConnected(int x, int y) {
        return (find(x) == find(y));
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
    
    // public method
    public int findCity(String city) {
        if (!cityMapping.containsKey(city)) {
            return 0;
        }
        
        int cityNo = cityMapping.get(city);
        
        return find(cityNo);
    }
    
    public boolean isCityConnected(String city1, String city2) {
        // check corner cases
        if (!cityMapping.containsKey(city1) || !cityMapping.containsKey(city2)) {
            return false;
        }
        
        int city1No = cityMapping.get(city1);
        int city2No = cityMapping.get(city2);
        
        boolean result = isConnected(city1No, city2No);
        
        return result;
    }
    
    public void connect2City(String city1, String city2) {
        // check corner cases
        if (!cityMapping.containsKey(city1) || !cityMapping.containsKey(city2)) {
            return;
        }
        
        int city1No = cityMapping.get(city1);
        int city2No = cityMapping.get(city2);
        this.union(city1No, city2No);
        
    }
    
    public int getComponentCount() {
        return count;
    }
}