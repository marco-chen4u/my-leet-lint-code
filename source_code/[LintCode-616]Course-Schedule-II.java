/***
* LintCode 616. Course Schedule II
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example
    Example 1:
	Input: n = 2, prerequisites = [[1,0]] 
	Output: [0,1]

	Example 2:
	Input: n = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]] 
	Output: [0,1,2,3] or [0,2,1,3]
***/
public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[0];//default value
        
        // check corner case
        if (numCourses == 0) {
            return result;
        }
        
        // initialize
        List[] edges = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
            indegree[i] = 0;
        }
        
        // building a graph
        for (int[] prerequisite : prerequisites) {
            edges[prerequisite[1]].add(prerequisite[0]);
            indegree[prerequisite[0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        // looking for 0-indegree to in-queue
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] courseOrder = new int[numCourses];
        int index = 0;
        // traverse the graph by BFS
        while (!queue.isEmpty()) {
            int current = queue.poll();
            courseOrder[index++] = current;
            int size = edges[current].size();
            for (int i = 0; i < size; i++) {
                int next = (int)edges[current].get(i);
                indegree[next]--;
                
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        if (index == numCourses) {
            return courseOrder;
        }
        
        return result;
    }
}
