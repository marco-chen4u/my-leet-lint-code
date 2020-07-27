/***
* LeetCode 210. Course Schedule II
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:
    Input: 2, [[1,0]] 
    Output: [0,1]
    Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
                 course 0. So the correct course order is [0,1] .
Example 2:
    Input: 4, [[1,0],[2,0],[3,1],[3,2]]
    Output: [0,1,2,3] or [0,2,1,3]
    Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
                 courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
                 So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

Note:
    1.The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    2.You may assume that there are no duplicate edges in the input prerequisites.
***/
//solution-1: BFS + Array
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[]{0};// default value
        // check corner cases
        if (numCourses == 0) {
            return result;
        }
        
        result = new int[0];// default value(for intialized)
        
        // regular case
        // building a graph
        List<Integer>[] edges = new List[numCourses];
        int[] indegrees = new int[numCourses];
        
        Arrays.fill(indegrees, 0);
        for (int i = 0 ; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            edges[from].add(to);
            indegrees[to]++;
        }
        
        // traverse graph by BFS
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] courseOrder = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            courseOrder[index++] = current;
            
            for (int next : edges[current]) {
                indegrees[next]--;
                if (indegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        if (index == numCourses) {
            return courseOrder;
        }
        
        return result;// default value
    }
}
