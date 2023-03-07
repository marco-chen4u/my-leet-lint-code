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
     * @param source: Undirected graph node
     * @param target: Undirected graph nodes
     * @return: an integer
     */
    public int sixDegrees(List<UndirectedGraphNode> graph, UndirectedGraphNode source, UndirectedGraphNode target) {
        
        // corner case
        if (graph == null || graph.isEmpty()) {
            return -1;
        }

        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                
                UndirectedGraphNode current = queue.poll();

                if (current == target) {
                    return step;
                }

                for (UndirectedGraphNode next : current.neighbors) {

                    if (visited.contains(next)) {
                        continue;
                    }

                    queue.offer(next);
                    visited.add(next);

                }
            }

            step++;
        }

        return -1;

    }
}


//version-2: bi-direction BFS(Queue + Set), time complexity: O(n^3)
public class Solution {
    /*
     * @param graph: a list of Undirected graph node
     * @param source: Undirected graph node
     * @param target: Undirected graph nodes
     * @return: an integer
     */
    public int sixDegrees(List<UndirectedGraphNode> graph, UndirectedGraphNode source, UndirectedGraphNode target) {
        
        // corner case
        if (graph == null || graph.isEmpty()) {
            return -1;
        }

        Queue<UndirectedGraphNode> queue1 = new LinkedList<>();
        Set<UndirectedGraphNode> visited1 = new HashSet<>();
        queue1.offer(source);
        visited1.add(source);

        Queue<UndirectedGraphNode> queue2 = new LinkedList<>();
        Set<UndirectedGraphNode> visited2 = new HashSet<>();
        queue2.offer(target);
        visited2.add(target);

        int step = 0;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            
            boolean flip = queue1.size() >= queue2.size();
            Queue<UndirectedGraphNode> queue = flip ? queue1 : queue2;
            Set<UndirectedGraphNode> visited = flip ? visited1 : visited2;
            Set<UndirectedGraphNode> visitedOther = flip ? visited2 : visited1;

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                
                UndirectedGraphNode current = queue.poll();

                if (visitedOther.contains(current)) {
                    return step;
                }

                for (UndirectedGraphNode next : current.neighbors) {

                    if (visited.contains(next)) {
                        continue;
                    }

                    queue.offer(next);
                    visited.add(next);

                }
            }

            step++;
        }

        return -1;

    }
}
