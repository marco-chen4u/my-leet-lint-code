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
    /**
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
	// cornrer case
	if (numCourses == 0) {
            return new int[0];// default value
	}

        // build up a graph with indegree and it's edge relationships
        int[] indegree = new int[numCourses];
        List<Integer>[] edges = new List[numCourses];

        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<Integer>();
        }

        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];

            indegree[to]++;
            edges[from].add(to);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;

        int index = 0;
        int[] result = new int[numCourses];

	// BFS
        while (!queue.isEmpty()) {
            int current = queue.poll();

            count += 1;

            result[index++] = current;

            for (int next : edges[current]) {

                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }

            }
        }
        
        if (count == numCourses) {
            return result;
        }

        return new int[]{0};// default value
    }
}

