/***
* LeetCode 207. Course Schedule
There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:
    Input: numCourses = 2, prerequisites = [[1,0]]
    Output: true
    Explanation: There are a total of 2 courses to take. 
                 To take course 1 you should have finished course 0. So it is possible.
Example 2:
    Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
    Output: false
    Explanation: There are a total of 2 courses to take. 
                 To take course 1 you should have finished course 0, and to take course 0 you should
                 also have finished course 1. So it is impossible.

Constraints:
    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.
    1 <= numCourses <= 10^5

Url: https://leetcode.com/problems/course-schedule/
***/
//solution-1: BFS and graph traverse with indgrees
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // check corner cases
        if (numCourses == 0 && 
            prerequisites != null && prerequisites.length > 0) {
            return false;
        }
        
        if (numCourses == 0 &&
           (prerequisites == null || prerequisites.length == 0 || prerequisites[0] == null || prerequisites[0].length == 0)) {
            return true;
        }
        
        // regular case
        // building the graph
        List<Integer>[] edges = new List[numCourses];
        int[] indegrees = new int[numCourses];
        Arrays.fill(indegrees, 0);
        
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        
        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            edges[from].add(to);
            indegrees[to]++;
        }
        
        // conduct BFS traverse to get its shortest path
        Queue<Integer> queue = new LinkedList<>();
        // looking for 0-indegree vetex to in-queue
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            int size = edges[current].size();
            count += 1;
            
            for (int next : edges[current]) {
                indegrees[next]--;
                if (indegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        return numCourses == count;
    }
}
