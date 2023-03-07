/***
* LeetCode 444 Sequence Reconstruction
You are given an integer array nums of length n where nums is a permutation of the integers in the range [1, n]. 
You are also given a 2D integer array sequences where sequences[i] is a subsequence of nums.

Check if nums is the shortest possible and the only supersequence. 
The shortest supersequence is a sequence with the shortest length and has all sequences[i] as subsequences. 
There could be multiple valid supersequences for the given array sequences.

For example, for sequences = [[1,2],[1,3]], there are two shortest supersequences, [1,2,3] and [1,3,2].
While for sequences = [[1,2],[1,3],[1,2,3]], the only shortest supersequence possible is [1,2,3]. 
[1,2,3,4] is a possible supersequence but not the shortest.
Return true if nums is the only shortest supersequence for sequences, or false otherwise.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 

Example 1:
    Input: nums = [1,2,3], sequences = [[1,2],[1,3]]
    Output: false
    Explanation: There are two possible supersequences: [1,2,3] and [1,3,2].
        The sequence [1,2] is a subsequence of both: [1,2,3] and [1,3,2].
        The sequence [1,3] is a subsequence of both: [1,2,3] and [1,3,2].
        Since nums is not the only shortest supersequence, we return false.

Example 2:
    Input: nums = [1,2,3], sequences = [[1,2]]
    Output: false
    Explanation: The shortest possible supersequence is [1,2].
        The sequence [1,2] is a subsequence of it: [1,2].
        Since nums is not the shortest supersequence, we return false.

Example 3:
    Input: nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
    Output: true
    Explanation: The shortest possible supersequence is [1,2,3].
        The sequence [1,2] is a subsequence of it: [1,2,3].
        The sequence [1,3] is a subsequence of it: [1,2,3].
        The sequence [2,3] is a subsequence of it: [1,2,3].
        Since nums is the only shortest supersequence, we return true.
 

Constraints:
    n == nums.length
    1 <= n <= 104
    nums is a permutation of all the integers in the range [1, n].
    1 <= sequences.length <= 104
    1 <= sequences[i].length <= 104
    1 <= sum(sequences[i].length) <= 105
    1 <= sequences[i][j] <= n
    All the arrays of sequences are unique.
    sequences[i] is a subsequence of nums.
***/
//BFS
class Solution {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        // corner cases
        if ((nums == null || nums.length == 0) && (sequences == null || sequences.size() == 0)) {
            return true;
        }

        if ((nums == null || nums.length == 0) || (sequences == null || sequences.size() == 0)) {
            return false;
        }

        // regular case
        int size = nums.length;
        int[] indegree = new int[size + 1];
        List<Integer>[] edges = new List[size + 1];

        for (int i = 1; i < size + 1; i++) {
            edges[i] = new ArrayList<Integer>();
        }

        int count = 0;
        for (List<Integer> seq : sequences) {
            count += seq.size();

            int firstVertex = seq.get(0);
            int seqSize = seq.size();
            if (seqSize >= 1 && (firstVertex <= 0 || firstVertex > size)) {
                return false;
            }

            for (int i = 1; i < seq.size(); i++) {
                int current = seq.get(i);

                if (current <= 0 || current > size) {
                    return false;
                }

                int from =seq.get(i - 1);

                edges[from].add(current);
                indegree[current]++;
            }
        }

        if (count < size) {
            return false;
        }

        boolean[] visited = new boolean[size + 1];
        visited[0] = true;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < size + 1; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                visited[i] = true;
            }
        }

        // BFS
        int index = 0;
        int[] result = new int[size];
        while (!queue.isEmpty()) {
            
            if (queue.size() != 1) {
                return false;
            }

            int current = queue.poll();
            if (nums[index] != current) {
                return false;
            }
            result[index++] = current;

            boolean isCyclic = true;
            for (int next : edges[current]) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    isCyclic = false;

                    queue.offer(next);
                    visited[next] = true;
                }
            }

            if (!edges[current].isEmpty() && isCyclic) {
                return false;
            }

        }

        for (int i = 1; i < size + 1; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        return index == size;

    }

