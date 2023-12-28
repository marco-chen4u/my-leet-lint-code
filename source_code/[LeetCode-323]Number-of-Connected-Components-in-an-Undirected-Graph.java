/***
* LeetCode 323. Number of Connected Components in an Undirected Graph
You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] 
indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.

Example 1
    Input: n = 5, edges = [[0,1],[1,2],[3,4]]
    Output: 2

Example 2
    Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
    Output: 1

Constraints
    1 <= n <= 2000
    1 <= edges.length <= 5000
    edges[i].length == 2
    0 <= ai <= bi < n
    ai != bi
    There are no repeated edges.

Link: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
***/
//soultion-1: UnionFind
class Solution {
    public int countComponents(int n, int[][] edges) {
        UnionFind unionFind = new UnionFind(n);

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            unionFind.union(from, to);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int parent = unionFind.find(i);

            if (set.contains(parent)) {
                continue;
            }

            set.add(parent);
        }

        int count = set.size();

        return count;
    }
}

class UnionFind{
    private int count;
    private int[] fathers;

    public UnionFind(int n) {
        count = n;
        fathers = new int[n];
        for (int i = 0; i < n; i++) {
            fathers[i] = i;
        }
    }

    public int find(int x) {
        int parent = fathers[x];

        while (parent != fathers[parent]) {
            parent = fathers[parent];
        }

        return parent;
    }

    public void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if (parentX != parentY) {
            fathers[parentY] = parentX;
            count--;
        }
    }

    public int getComponents() {
        return count;
    }
}
