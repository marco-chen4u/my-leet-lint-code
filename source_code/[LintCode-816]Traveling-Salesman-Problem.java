/**
* LintCode 816. Traveling Salesman Problem
Give n cities(labeled from 1 to n), 
and the undirected road's cost among the cities as a three-tuple [A, B, c]
(i.e there is a road between city A and city B and the cost is c). 
We need to find the smallest cost to travel all the cities starting from 1.

Example 1
    Input: 
        n = 3
        tuple = [[1,2,1],[2,3,2],[1,3,3]]
    Output: 3
    Explanation: The shortest path is 1->2->3

Example 2
    Input:
        n = 1
        tuple = []
    Output: 0
    
    
Related: LintCode 814. Shortest Path in Undirected Graph
**/
/**
* Graph(searching) + Hamilton problem
* 首先要建图，然后进行搜索，计算路径的各个节点的权值，然后找到最优结果。
**/
//verion-1: DFS(brute force)
public class Solution {
    // constants
    private static final int DEFAULT_MAX_VALUE = Integer.MAX_VALUE>>4;
    private static final int MAX = Integer.MAX_VALUE;

    // inner class
    class Result{
        // field
        int minCost;
        // constructor
        public Result(int minCost) {
            this.minCost = minCost;
        }

        public Result() {
            this.minCost = MAX;
        }
    }

    /**
     * @param n: an integer,denote the number of cities
     * @param roads: a list of three-tuples,denote the road between cities
     * @return: return the minimum cost to travel all cities
     */
    public int minCost(int n, int[][] roads) {
        int[][] graph = buildGraph(n, roads);
        Set<Integer> visited = new HashSet<>();
        Result result = new Result();

        visited.add(1);
        dfs(n, graph, 1, 0, visited, result);

        return result.minCost;
    }

    // helper methods
    private void dfs(int n, 
                    int[][] graph, 
                    int currentCity, 
                    int cost, 
                    Set<Integer> visited, 
                    Result result) {
        if (visited.size() == n) {
            result.minCost = Math.min(result.minCost, cost);
            return;
        }

        for(int i = 1; i < graph[currentCity].length; i++) {
            if (visited.contains(i)) {
                continue;
            }

            int newCost = cost + graph[currentCity][i];

            visited.add(i);
            dfs(n, graph, i, newCost, visited, result);
            visited.remove(i);
        }

    }

    private int getCost(Set<Integer> visited) {
        int result = 0;

        for (int cost : visited) {
            result += cost;
        }

        return result;
    }

    private int[][] buildGraph(int n, int[][] roads) {
        int[][] graph = new int[n + 1][n + 1];
        
        // initializing
        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], DEFAULT_MAX_VALUE);
        }

        // calculation
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            int edgeValue = road[2];//cost

            graph[from][to] = Math.min(graph[from][to], edgeValue);
            graph[to][from] = Math.min(graph[to][from], edgeValue);
        }

        return graph;
    }
}

//version: dfs+剪支优化处理
public class Solution {
    // constants
    private static final int DEFAULT_MAX_VALUE = Integer.MAX_VALUE>>4;
    private static final int MAX = Integer.MAX_VALUE;

    // inner class
    class Result{
        // field
        int minCost;
        // constructor
        public Result(int minCost) {
            this.minCost = minCost;
        }

        public Result() {
            this.minCost = MAX;
        }
    }

    /**
     * @param n: an integer,denote the number of cities
     * @param roads: a list of three-tuples,denote the road between cities
     * @return: return the minimum cost to travel all cities
     */
    public int minCost(int n, int[][] roads) {
        int[][] graph = buildGraph(n, roads);

        Result result = new Result();

        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();

        visited.add(1);
        path.add(1);
        dfs(n, graph, 1, 0, visited, path, result);

        return result.minCost;
    }

    // helper methods
    private void dfs(int n, 
                    int[][] graph, 
                    int currentCity, 
                    int cost, 
                    Set<Integer> visited,
                    List<Integer> path,
                    Result result) {
        if (visited.size() == n) {
            result.minCost = Math.min(result.minCost, cost);
            return;
        }

        for(int i = 1; i < graph[currentCity].length; i++) {
            if (visited.contains(i)) {
                continue;
            }

            if (hasBetterPath(graph, path, i)) {
                continue;
            }

            int newCost = cost + graph[currentCity][i];

            visited.add(i);
            path.add(i);
            dfs(n, graph, i, newCost, visited, path, result);
            path.remove(path.size() - 1);
            visited.remove(i);
        }

    }

    private boolean hasBetterPath(int[][] graph, List<Integer> path, int nextCity) {

        for (int i = 1; i < path.size(); i++) {
            int a = path.get(i - 1);
            int b = path.get(i);
            int last = path.get(path.size() - 1);

            if (graph[a][b] + graph[last][nextCity]
                > graph[a][last] + graph[b][nextCity]) {
                return true;
            }
        }

        return false;
    }

    private int[][] buildGraph(int n, int[][] roads) {
        int[][] graph = new int[n + 1][n + 1];
        
        // initializing
        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], DEFAULT_MAX_VALUE);
        }

        // calculation
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            int edgeValue = road[2];//cost

            graph[from][to] = Math.min(graph[from][to], edgeValue);
            graph[to][from] = Math.min(graph[to][from], edgeValue);
        }

        return graph;
    }
}

//version-3: dp
public class Solution {
    // constants
    private static final int DEFAULT_MAX_VALUE = Integer.MAX_VALUE>>4;
    private static final int MAX = Integer.MAX_VALUE;

    /**
     * @param n: an integer,denote the number of cities
     * @param roads: a list of three-tuples,denote the road between cities
     * @return: return the minimum cost to travel all cities
     */
    public int minCost(int n, int[][] roads) {
        int[][] graph = buildGraph(n, roads);
        int stateSize = 1 << n;

        // state
        int[][] dp = new int[stateSize][n + 1];

        // initializing
        for (int i = 0; i < stateSize; i++) {
            Arrays.fill(dp[i], DEFAULT_MAX_VALUE);
        }

        dp[1][1] = 0;
        for (int state = 0; state < stateSize; state++) {
            for (int i = 2; i < n + 1; i++) {

                if ((state & (1 <<(i -1))) == 0) {
                    continue;
                }

                int preState = state ^ (1 << (i - 1));

                for (int j = 1; j < n + 1; j++) {
                    if ((preState & (1 << (j - 1))) == 0) {
                        continue;
                    }

                    dp[state][i] = Math.min(dp[state][i],
                                            dp[preState][j] + graph[i][j]);
                }// j
            }// i
        }//state
        
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n + 1; i++) {
            result = Math.min(result, dp[stateSize - 1][i]);
        }

        return result;
    }

    // helper methods
    private int[][] buildGraph(int n, int[][] roads) {
        int[][] graph = new int[n + 1][n + 1];
        
        // initializing
        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], DEFAULT_MAX_VALUE);
        }

        // calculation
        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            int edgeValue = road[2];//cost

            graph[from][to] = Math.min(graph[from][to], edgeValue);
            graph[to][from] = Math.min(graph[to][from], edgeValue);
        }

        return graph;
    }
}
