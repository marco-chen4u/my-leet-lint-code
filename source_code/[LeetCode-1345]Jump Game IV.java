/***
* LeetCode 1345. Jump Game IV
Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:
	i + 1 where: i + 1 < arr.length.
	i - 1 where: i - 1 >= 0.
	j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

Example 1:
	Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
	Output: 3
	Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.

Example 2:
	Input: arr = [7]
	Output: 0
	Explanation: Start index is the last index. You don't need to jump.

Example 3:
	Input: arr = [7,6,9,6,9,6,9,7]
	Output: 1
	Explanation: You can jump directly from index 0 to index 7 which is last index of the array.

Example 4:
	Input: arr = [6,1,9]
	Output: 2

Example 5:
	Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
	Output: 3

Example 6:
	Input: arr = [0,4,3,9]
	Output: 3 ***2
	
Constraints
	1 <= arr.length <= 5 * 10^4
	-10^8 <= arr[i] <= 10^8
***/
class Solution {
    public int minJumps(int[] arr) {
        // check corner case
		if (arr == null || arr.length <= 1) {
			return 0;
		}
		
		int length = arr.length;
		int startPos = 0;
		int lastPos = length - 1;
		
		Map<Integer, List<Integer>> graphMap = buildGraphMap(arr, startPos, lastPos);
		
		Queue<Integer> queue = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<>();
		
		queue.offer(startPos);
		visited.add(startPos);
		
		int step = 0;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			
			// level-traversal
			for (int i = 0; i < size; i++) {
				int current = queue.poll();
				
				if (current == lastPos) {
					return step;
				}
				
				List<Integer> neighbors = graphMap.get(arr[current]);
				
				
				for (int next : neighbors) {
					
                    if (next < startPos || next > lastPos || visited.contains(next)) {
                        continue;
                    }
                    
                    visited.add(next);
                    queue.offer(next);
                    
					
				} // for next
                
                neighbors.clear();
				
			} // for i
			
			step++;
		} // while
		
		return step;
    }
	
	// helper method
	private Map<Integer, List<Integer>> buildGraphMap(int[] nums, int startPos, int endPos) {
		Map<Integer, List<Integer>> graphMap = new HashMap<>();
		
		for (int i = 0; i < nums.length; i++) {
			int current = nums[i];
			graphMap.putIfAbsent(current, new ArrayList<Integer>());
            List<Integer> neighbors = graphMap.get(current);
            
            neighbors.add(i);
            
            neighbors.add(i + 1);
			neighbors.add(i - 1);
		}
		
		return graphMap;
	}
}