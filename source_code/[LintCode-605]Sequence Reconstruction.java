/***
* LintCode 605. Sequence Reconstruction
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. 
The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 10^4. 
Reconstruction means building a shortest common supersequence of the sequences in seqs 
(i.e., a shortest sequence so that all sequences in seqs are subsequences of it). 
Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example
    Example 1:
        Input:org = [1,2,3], seqs = [[1,2],[1,3]]
        Output: false
        Explanation:
            [1,2,3] is not the only one sequence that can be reconstructed, 
            because [1,3,2] is also a valid sequence that can be reconstructed.

    Example 2:
        Input: org = [1,2,3], seqs = [[1,2]]
        Output: false
        Explanation:
            The reconstructed sequence can only be [1,2].

    Example 3:
        Input: org = [1,2,3], seqs = [[1,2],[1,3],[2,3]]
        Output: true
        Explanation:
            The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].

    Example 4:
        Input:org = [4,1,5,2,6,3], seqs = [[5,2,6,3],[4,1,5,2]]
        Output:true
***/
//version-1 BFS with HashMap
public class Solution {	

    // helper method 
    private boolean isValid(int num, int boundary) {
        return num > 0 && num <= boundary;
    }
	
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // check corner case
       if (org.length > 0 && (seqs == null || seqs.length == 0)) {
            return false;
        }
        
        if ((org == null || org.length == 0) && (seqs == null || seqs.length == 0)) {
            return true;
        }
		
        // (1) initialize
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        Map<Integer, Integer> indegree = new HashMap<Integer, Integer>();
        int boundary = org.length;

        for (int num : org) {
            map.put(num, new HashSet<Integer>());// keep records of every node's neighbors
            indegree.put(num, 0);
        }

        // (2)building a graph by the subsequences 
        int count = 0;
        for (int[] seq : seqs) {
            count += seq.length;

            if (seq.length > 0 && !isValid(seq[0], boundary)) {
                return false;
            }

            for (int i = 1; i < seq.length; i++) {
                int currentNode = seq[i];
                int preNode = seq[i - 1];
                if (!isValid(currentNode, boundary)) {
                    return false;
                }

                if (map.get(preNode).add(currentNode)) {
                    indegree.put(currentNode, indegree.get(currentNode) + 1); // increase current node's indegree
                }
            }
        }
        if (count < boundary) {
            return false;
        }

        // (3) finding the start point of the topological sort sequence, that means finding the 0-indegree of this graph
        Queue<Integer> queue = new LinkedList<Integer>();
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        // (4) generate the unique topological sort path(sequence), and check each node of this path is identical to each one of the original sequence
        int index = 0;
        while (queue.size() == 1) {
            // take it out as the node of path
            Integer current = queue.poll();

            // update its neighbor's indegree value by decreasing 1
            for (Integer neighbor : map.get(current)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1); 
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }

            // check each node of this path is identical to each one of the original sequence
            if (current != org[index]) {
                return false;
            }

            // extend the length
            index++;
        }

        return index == boundary;

    }
}

//version-2 BFS with Arrays
public class Solution {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if ((org == null || org.length == 0) && (seqs == null || seqs.length == 0 || seqs[0] == null || seqs[0].length == 0)) {
            return true;
        }

        if ((org == null || org.length == 0) || (seqs == null || seqs.length == 0 || seqs[0] == null || seqs[0].length == 0)) {
            return false;
        }

        int size = org.length;
        List<Integer>[] graph = new List[size + 1];
        int[] indegree = new int[size + 1];

        for (int i = 1; i <= size; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        int count = 0;
        for (int[] seq : seqs) {
            count += seq.length;
            int first = seq[0];

            if (seq.length > 1 && (first < 0 || first >= size)) {
                return false;
            }

            for (int i = 1; i < seq.length; i++) {
                int from = seq[i - 1];
                int current = seq[i];

                graph[from].add(current);
                indegree[current]++;
            }

        }

        if (count < size) {
            return false;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[size + 1];
        visited[0] = true;

        for (int i = 1; i <= size; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                visited[i] = true;
            }
        }

        int[] result = new int[size];
        int index = 0;

        while (queue.size() == 1) {


            int current = queue.poll();
            if (org[index] != current) {
                return false;
            }

            result[index++] = current;
            List<Integer> neighbors = graph[current];
            boolean isCyclic = true;
            for (int next : neighbors) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                    visited[next] = true;
                    isCyclic = false;
                }
            }
            if (!neighbors.isEmpty() && isCyclic) {
                return false;
            }
        }

        for (int i = 1; i <= size; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        if (index == size) {
            return true;
        }

        return false;
    }
}
