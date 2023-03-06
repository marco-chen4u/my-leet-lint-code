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
        // corenr cases
        if(nums.length != 0 && sequences.size() == 0) return false;
        if(sequences.size() != 0 && nums.length == 0)return false;
        
        // regular case
        int nodes = nums.length;
        System.out.println(nodes);
        int[] indegree = new int[nodes+1];
        boolean[] visited = new boolean[nodes+1];
        visited[0] = true;
        LinkedList<Integer>[] graph = new LinkedList[nodes+1];
        
        for(int i = 1; i <= nodes; i++){
            graph[i] = new LinkedList<>();
        }

        int count = 0;
        
        for(List<Integer> seq : sequences){
            count += seq.size();
            if (seq.size() >= 1 && (seq.get(0) <= 0 || seq.get(0) > nodes)) {
                return false;
            }
            for(int i = 1; i < seq.size(); i++){
                if (seq.get(i) <= 0 || seq.get(i) > nodes) {
                    return false;
                }
                graph[seq.get(i-1)].add(seq.get(i));
                indegree[seq.get(i)]++;
            }
        }
        
        if(count < nodes) return false;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i < indegree.length; i++){
            if(indegree[i] == 0){
                queue.offer(i);
                visited[i] = true;
            }
        }

        int index = 0;
        while(!queue.isEmpty()){
            if(queue.size()>1) return false;
            int current = queue.poll();
            if(nums[index++] != current) return false;
            boolean isCycle = true;
            for(int child : graph[current]){
                indegree[child]--;
                if(indegree[child] == 0){
                    visited[child] = true;
                    isCycle = false;
                    queue.offer(child);
                }
            }
            if(graph[current].size() != 0 && isCycle) return false;
        }

        for(int i = 1; i <= nodes; i++){
            if(!visited[i]) return false;
        }

        return index == nodes;

    }
}
