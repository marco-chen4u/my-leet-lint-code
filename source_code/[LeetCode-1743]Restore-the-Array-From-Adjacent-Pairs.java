/***
* LeetCode 1743. Restore the Array From Adjacent Pairs
here is an integer array nums that consists of n unique elements, but you have forgotten it. 
However, you do remember every pair of adjacent elements in nums.

You are given a 2D integer array adjacentPairs of size n - 1 
where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.

It is guaranteed that every adjacent pair of elements nums[i] and 
nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. 
The pairs can appear in any order.

Return the original array nums. If there are multiple solutions, return any of them.

Example 1
    Input: adjacentPairs = [[2,1],[3,4],[3,2]]
    Output: [1,2,3,4]
    Explanation: This array has all its adjacent pairs in adjacentPairs.
    Notice that adjacentPairs[i] may not be in left-to-right order.

Example 2
    Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
    Output: [-2,4,1,-3]
    Explanation: There can be negative numbers.
    Another solution is [-3,1,4,-2], which would also be accepted.
    
Example 3
    Input: adjacentPairs = [[100000,-100000]]
    Output: [100000,-100000]
    
Constraints:
    nums.length == n
    adjacentPairs.length == n - 1
    adjacentPairs[i].length == 2
    2 <= n <= 105
    -105 <= nums[i], ui, vi <= 105
    There exists some nums that has adjacentPairs as its pairs.

LeetCode link: https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/
***/
//version-1: graph build + BFS
class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {

        // building a graph with n ndoes from n-1 dege relationship/adjacentPairs
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int n = adjacentPairs.length + 1;
        for (int[] edge : adjacentPairs) {
            graph.putIfAbsent(edge[0], new HashSet<>());
            graph.get(edge[0]).add(edge[1]);

            graph.putIfAbsent(edge[1], new HashSet<>());
            graph.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        // finding the source/terminal node
        for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                queue.offer(entry.getKey());
                visited.add(entry.getKey());                
                break;
            }
        }

        int[] result = new int[n];
        int index = 0;
        // BFS traverse
        while (!queue.isEmpty()) {
            int current = queue.poll();
            result[index++] = current;

            for (int next : graph.get(current)) {
                if (visited.contains(next)) {
                    continue;
                }

                queue.offer(next);
                visited.add(next);
            }
        }

        return result;
    }
}

//version-2: graph build + stack for DFS
class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {

        // building a graph with n ndoes from n-1 dege relationship/adjacentPairs
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int n = adjacentPairs.length + 1;
        for (int[] edge : adjacentPairs) {
            graph.putIfAbsent(edge[0], new HashSet<>());
            graph.get(edge[0]).add(edge[1]);

            graph.putIfAbsent(edge[1], new HashSet<>());
            graph.get(edge[1]).add(edge[0]);
        }

        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        // finding the source/terminal node
        for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                stack.push(entry.getKey());
                visited.add(entry.getKey());                
                break;
            }
        }

        int[] result = new int[n];
        int index = 0;
        while (!stack.isEmpty()) {
            int current = stack.pop();
            result[index++] = current;

            for (int next : graph.get(current)) {
                if (visited.contains(next)) {
                    continue;
                }

                stack.push(next);
                visited.add(next);
            }
        }

        return result;
    }
}
