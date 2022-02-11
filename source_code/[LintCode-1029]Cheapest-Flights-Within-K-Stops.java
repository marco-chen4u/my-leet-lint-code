/**
* LintCode 1029 Cheapest Flights Within K Stops
There are n cities connected by some flights. 
Each flight (u, v, w) starts from city u and arrives at v with a price w.

Given n, flights, together with starting city src and the destination dst, 
your task is to find the cheapest price from src to dst with at most K stops.

If there is no such route, return -1.

Note:
    -The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
    -The size of flights will be in range [0, n * (n - 1) / 2].
    -The format of each flight will be [src, dst, price].
    -The price of each flight will be in the range [1, 10000].
    -K is in the range of [0, n - 1].
    -There will not be any duplicated flights or self cycles.
    
Example 1:
    Input: 
        n = 3,
        flights = [[0,1,100],[1,2,100],[0,2,500]],
        src = 0, dst = 2, K = 0
    Output: 500

Example 2:
    Input: 
        n = 3,
        flights = [[0,1,100],[1,2,100],[0,2,500]],
        src = 0, dst = 2, K = 1
    Output: 200

**/

//version-1:Bellman-Ford
/**
该题目是一道加了限制的最短路题目, 即限制最多有 K + 1 条边构成.
在Bellman-Ford或者Dijkstra最短路算法的基础上做一点修改即可.
如果是Bellman-Ford(或者是由该算法优化而来的SPFA算法), 那么需要限制迭代次数, 最多 K + 1 条边, 只迭代 K + 1 次即可.
同时还需要保证每一次迭代只会让边生长一次. (Bellman-Ford需要记录并判断, 而SPFA无需关注这一点)
如果是Dijkstra算法, 则需要额外记录一下当前点距离源点的边数.
该问题数据范围不大, 同时提供的是所有的边的集合, 所以很适合使用Bellman-Ford算法.
**/
public class Solution {
    private final static int DEFAULT_MAX = Integer.MAX_VALUE >>4;

    /**
     * @param n: a integer
     * @param flights: a 2D array
     * @param src: a integer
     * @param dst: a integer
     * @param K: a integer
     * @return: return a integer
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int m = flights.length;
        int[] distance = new int[n];
        // initializing
        Arrays.fill(distance, DEFAULT_MAX);
        distance[src] = 0;

        boolean[] visited = new boolean[n];
        for (int i = 0; i <= K; i++) {
            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int w = flight[2];

                if (visited[u] || distance[u] + w >= distance[v]) {
                    continue;
                }

                distance[v] = distance[u] + w;
                visited[v] = true;
            }

            Arrays.fill(visited, false);//reset it for the next round of process
        }

        return distance[dst] == DEFAULT_MAX ? -1 : distance[dst];

    }
}

//version-2:PriorityQueue + BFS
public class Solution {

    // inner class
    class Pair implements Comparable<Pair> {
        int city; // current stop city
        int cost;// current cost
        int stops;// stop count

        // constructor
        public Pair(int city, int cost, int stops) {
            this.city = city;
            this.cost = cost;
            this.stops = stops;
        }

        public int compareTo(Pair other) {
            if (this.cost != other.cost) {
                return this.cost - other.cost;
            }

            return other.stops - this.stops;
        }
    }

    /**
     * @param n: a integer
     * @param flights: a 2D array
     * @param src: a integer
     * @param dst: a integer
     * @param K: a integer
     * @return: return a integer
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        if (src == dst) {
            return 0;
        }

        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];
            map.putIfAbsent(from, new ArrayList<int[]>());
            map.get(from).add(new int[]{to, cost});
        }

        Queue<Pair> queue = new PriorityQueue<>();
        queue.offer(new Pair(src, 0, K));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            int currentCity = current.city;
            int currentCost = current.cost;
            int stops = current.stops;

            if (currentCity == dst) {
                return currentCost;
            }

            if (!map.containsKey(currentCity) || stops < 0) {
                continue;
            }

            for (int[] next : map.get(currentCity)) {
                int nextStop = next[0];
                int newCost = currentCost + next[1];
                queue.offer(new Pair(nextStop, newCost, stops - 1));
            }
        }

        return -1;
    }
}
