/***
* LintCode 531. Six Degrees
Six degrees of separation is a philosophical problem, 
which means that everyone and everything can be connected through six steps or less.

Now give you a friendship, calculate how many steps two people can be connected through, if not, return -1.

Example 1:
    Input: {1#2,4#3,4#4,2,3} and s = 1, t = 4
    Output: -1
    Explanation:
        1      2-----4
                     /
                    /
                  3

Example 2:
    Input: {1,2,3#2,1,4#3,1,4#4,2,3} and s = 1, t = 4 
    Output: 2
    Explanation:
        1------2-----4
         \          /
          \        /
           \---3--/
***/

/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { 
 *         label = x;
 *         neighbors = new ArrayList<UndirectedGraphNode>(); 
 *     }
 * };
 */
//version-1: BFS(Queue + Set), timce complexity: O(n^3)
public class Solution {
    /*
     * @param graph: a list of Undirected graph node
     * @param s: Undirected graph node
     * @param t: Undirected graph nodes
     * @return: an integer
     */
    public int sixDegrees(List<UndirectedGraphNode> graph, UndirectedGraphNode s, UndirectedGraphNode t) {
        int result = -1;
        // check corner cases
        if (graph == null || graph.size() <= 1) {
            return 0;
        }

        if (s == null || t == null || s == t) {
            return 0;
        }

        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode current = queue.poll();
                List<UndirectedGraphNode> neighbors = current.neighbors;
                for (UndirectedGraphNode next : neighbors) {
                    if (next == t) {
                        return step;
                    }

                    if (visited.contains(next)) {
                        continue;
                    }

                    visited.add(next);
                    queue.offer(next);
                }
            }
            step++;
        }

        return result;
    }
}


//version-2: bi-direction BFS(Queue + Set), time complexity: O(n^3)
public class Solution {
    /*
     * @param graph: a list of Undirected graph node
     * @param s: Undirected graph node
     * @param t: Undirected graph nodes
     * @return: an integer
     */
    public int sixDegrees(List<UndirectedGraphNode> graph, UndirectedGraphNode s, UndirectedGraphNode t) {
        // check corner cases
        if (graph == null || graph.isEmpty()) {
            return -1;
        }

        if (s == null || t == null) {
            return -1;
        }

        if (s == t) {
            return 0;
        }

        // normal case
        int step = 0;
        Queue<UndirectedGraphNode> queue1 = new LinkedList<>();
        queue1.offer(s);
        Set<UndirectedGraphNode> visited1 = new HashSet<>();
        visited1.add(s);

        Queue<UndirectedGraphNode> queue2 = new LinkedList<>();
        queue2.offer(t);
        Set<UndirectedGraphNode> visited2 = new HashSet<>();
        visited2.add(t);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            boolean flip = queue1.size() <= queue1.size();
            Queue<UndirectedGraphNode> queue = flip ? queue1 : queue2;
            Set<UndirectedGraphNode> visited = flip ? visited1 : visited2;
            Set<UndirectedGraphNode> visitedOther = !flip ? visited1 : visited2;

            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                UndirectedGraphNode current = queue.poll();
                for (UndirectedGraphNode next : current.neighbors) {
                    if (visitedOther.contains(next)) {
                        return step;
                    }

                    if (visited.contains(next)) {
                        continue;
                    }

                    queue.offer(next);
                    visited.add(next);

                }
            }

        }

        return -1;
    }
}
