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
