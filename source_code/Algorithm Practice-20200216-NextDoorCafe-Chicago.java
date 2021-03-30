Address: Next Door Cafe(Chicago)
Time : 2/16/2020 14:00~16:00

/*
(1)Check if N and its double exist?（LeetCode-1346）
    [5,2,10,2,0] => True
    [2,10, 30] => False
*/
public class Solution {
    public boolean checkIfDouble(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return false;
        }

        // nornmal cases
        Set<Integer> set = new HashSet<>()
        for (int num : nums) {
            set.add(num);
        }

        for (int num : nums) {
            if (num == 0) {
                continue;
            }

            if (set.contains(num * 2)) {
                return true;
            }
        }

        return false;
    }
}

/*
(2)Find A Peak
    [1,5,7,2,4,0,-1] => 7 or 4

    Peak Condition nums[i] != nums[i + 1]
*/
public class Solution {
    public int findPeak(int[] nums) {
        // check corner cases
        if (nums.length == 0) {
            return 0;
        }
		
        if (nums.length <= 3) {
            return getMax(nums);
        }

        // normal case
        int size = nums.length;
        int left = 1
        int right = size - 2;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid - 1]) {
                end = mid;
            }
            else if (nums[mid] < nums[mid + 1]) {
                start = mid;
            }
            else {
                return nums[mid];
            }
        }

        return Integer.MIN_VALUE;
    }

    private int getMax(int[] nums) {
        int result = Integer.MIN_VALUE;
        for (int value : nums) {
            result = Math.max(result, value);
        }

        return result;
    }
}

/*
(3) Find K largest elements in a List
    K = 3, [5, 10, 4, 2, 7, 8, 3]
    => 10, 8, 7
*/
public class Solution {
    Comparater<Integer> comparator = new Comparator<>{//ascending order
        @Override
        public int compare(int x, int y) {
            return x - y;
        }
    }
	
    public List<Integer> findKLargestElement(List<Integer> list, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k, comparator);//minHeap
        List<Integer> result = new ArrayList<Integer>();

        for (int value : list) {
            minHeap.offer(value);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            result.add(0, minHeap.poll());
        }

        return result;
    }
}

/*
(4)Jump Game(LeetCode 1340)
    Find Most spaces you can jump into with Rum
    From I, Can Jump anywhere in Range [i -k, i + k]
    such taht Array[[x] < Array[I] for all x B/N I and Estimated
    [1,1,5,7,3,8,2], k = 3
*/

public class Solution {
	
}
