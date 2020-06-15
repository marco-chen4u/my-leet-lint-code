/***
* LintCode 139. Subarray Sum Closest
Given an integer array, find a subarray with sum closest to zero. 
Return the indexes of the first number and last number.

Example
    Input: 
        [-3,1,1,-3,5] 
    Output: 
        [0,2]
    Explanation: [0,2], [1,3], [1,1], [2,2], [0,4]

Challenge
    O(nlogn) time
***/

class SumElement {
    int sum;
    int index;
    // constructor
    public SumElement(int sum, int index) {
        this.sum = sum;
        this.index = index;
    }
}

public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        int[] result = new int[2];
        // check corner case
        if (nums == null || nums.length <= 1) {
            return result;
        }
        
        int size = nums.length;
        SumElement[] sums = new SumElement[size];
        for (int i = 0, sumToCurrent = 0; i < size; i++) {
            sumToCurrent += nums[i];
            sums[i] = new SumElement(sumToCurrent, i);
        }
        
        Arrays.sort(sums, new Comparator<SumElement>() {
            public int compare(SumElement a, SumElement b) {
                return a.sum - b.sum;
            }
        });
        
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < size; i++) {
            if (sums[i].sum - sums[i - 1].sum < min) {                
                result[0] = Math.min(sums[i].index, sums[i - 1].index) + 1;
                result[1] = Math.max(sums[i].index, sums[i - 1].index);
                
                min = sums[i].sum - sums[i - 1].sum;
            }
        }
        
        return result;
    }
}

public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public int[] subarraySumClosest(int[] nums) {
        int[] result = new int[2];//default value by {0, 0}
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>(); // map(prefixSum, index);
        int prefixSum = 0;
        int size = nums.length;
        
        // initialize
        map.put(prefixSum, -1);
        
        int minDiff = Integer.MAX_VALUE;
        int leftIndex = 0, rightIndex = 0;
        for (int index = 0; index < size; index++) {
            prefixSum += nums[index];
            
            // (1) getting current prefixSum's lower bound value
            Map.Entry<Integer, Integer> pre = map.floorEntry(prefixSum);
            if (pre != null) {
                int diff = prefixSum - pre.getKey();// Entry value is <prefixSum, index> key pair
                if (diff < minDiff) {
                    leftIndex = pre.getValue() + 1;
                    rightIndex = index;
                    
                    minDiff = diff;
                }
            }
            
            // (2) getting current prefixSum's upper bound value
            Map.Entry<Integer, Integer> next = map.ceilingEntry(prefixSum);
            if (next != null) {
                int diff = next.getKey() - prefixSum;// Entry value is <prefixSum, index> key pair
                if (diff < minDiff) {
                    leftIndex = next.getValue() + 1;
                    rightIndex = index;
                    
                    minDiff = diff;
                }
            }
            
            map.put(prefixSum, index);
        }
        
        result[0] = leftIndex;
        result[1] = rightIndex;
        
        return result;
    }
}
