/***
* LeetCode 1306. Jump Game III
Given an array of non-negative integers arr, you are initially positioned at start index of the array. 
When you are at index i, you can jump to i + arr[i] or i - arr[i], 
check if you can reach to any index with value 0.

Notice that you can not jump outside of the array at any time.

Example 1:
    Input: arr = [4,2,3,0,3,1,2], start = 5
    Output: true
    Explanation: 
        All possible ways to reach at index 3 with value 0 are: 
            index 5 -> index 4 -> index 1 -> index 3 
            index 5 -> index 6 -> index 4 -> index 1 -> index 3 

Example 2:
    Input: arr = [4,2,3,0,3,1,2], start = 0
    Output: true 
    Explanation: 
        One possible way to reach at index 3 with value 0 is: 
            index 0 -> index 4 -> index 1 -> index 3

Example 3:
    Input: arr = [3,0,2,1,2], start = 2
    Output: false
    Explanation: There is no way to reach at index 1 with value 0.

Constrains
    1 <= arr.length <= 5 * 10^4
    0 <= arr[i] < arr.length
    0 <= start < arr.length

***/
//version-1: Building Grap, traverse grap nodes based on BFS, it will get Time Limited Exceeded error
class Solution {
    public boolean canReach(int[] arr, int start) {
        // check coenr case
        if (arr == null || arr.length == 0) {
            return false;
        }

        int count = 0;
        for (int value : arr) {
            if(value == 0) {
                break;
            }

            count++;
        }

        if (count == arr.length) {
            return false;
        }

        if (arr[start] == 0) {
            return true;
        }
		
        // normal case
        Map<Integer, List<Integer>> graphMap = buildGrap(arr);
		
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        while(!queue.isEmpty()) {
            int current = queue.poll();

            List<Integer> neighbors = graphMap.get(current);//space consuming if there's huge amout of items/nodes to build, it will get get Time Limited Exceeded error
            for (int next : neighbors) {
                if (arr[next] == 0) {
                    return true;
                }

                if (visited.contains(next)) {
                    continue;
                }

                queue.offer(next);
            }
        }

        return false;
    }

    // helper method
    private Map<Integer, List<Integer>> buildGrap(int[] nums) {
        Map<Integer, List<Integer>> graphMap = new HashMap<>();

        int size = nums.length;
        int startPos = 0;
        int lastPos = size - 1;

        for (int i = 0; i < size; i++) {
            graphMap.putIfAbsent(i, new ArrayList<Integer>());
			
            // check corner case
            if (nums[i] == 0) {// it's a destination
                continue;
            }

            int nextLeft, nextRight;

            nextLeft = i - nums[i];
            if (nextLeft >= startPos) {
                graphMap.get(i).add(nextLeft);
            }

            nextRight = i + nums[i];
            if (nextRight <= lastPos) {
                graphMap.get(i).add(nextRight);
            }
        }
 
        return graphMap;
    }
}

//version-2: BFS(Queue + memo)
class Solution {
    public boolean canReach(int[] arr, int start) {
        // check corner case
        if (arr == null || arr.length == 0) {
            return false;
        }

        if (arr[start] == 0) {
            return true;
        }

        // normal case
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        int size = arr.length;
        int startPos = 0;
        int lastPos = size - 1;

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (arr[current] == 0) {
                return true;
            }

            // traverse its neighboring node
            int nextLeft = current - arr[current];
            if (nextLeft >= startPos && !visited.contains(nextLeft)) {
                queue.offer(nextLeft);
                visited.add(nextLeft);
            }

            int nextRight = current + arr[current];
            if (nextRight <= lastPos && !visited.contains(nextRight)) {
                queue.offer(nextRight);
                visited.add(nextRight);
            }
        }

        return false;
    }
}
