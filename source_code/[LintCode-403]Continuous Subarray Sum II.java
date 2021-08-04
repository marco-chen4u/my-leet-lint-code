/***
* LintCode 403. Continuous Subarray Sum II
Given an circular integer array (the next element of the last element is the first element), 
find a continuous subarray in it, where the sum of numbers is the biggest. 
Your code should return the index of the first number and the index of the last number.

If duplicate answers exist, return any of them.

Example 1:
    Input: [3,1,-100,-3,4]
    Output: [4, 1]

Example 2:
    Input: [1,-1]
    Output: [0, 0]

Example 3:
    Input: [4,10,13,4,-1,0,3,3,5]
    Output: [5, 3]

Example 4:
    Input: [-101,-33,-44,-55,-67,-78,-101,-33,-44,-55,-67,-78,-100,-200,-1000,-22,-100,-200,-1000,-22]
    Output: [15, 15]

Challenge
    O(n) time
***/

// helper class
class ResultType {
    // fields
    int value;
    int start;
    int end;
    // constructor
    public ResultType(int value, int start, int end) {
        this.value = value;
        this.start = start;
        this.end = end;
    }
}

public class Solution {
    /*
     * @param A: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySumII(int[] A) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner cases
        if (isEmpty(A)) {
            return result;
        }

        int sum = getSum(A);
        ResultType resultMin = findMin(A);
        ResultType resultMax = findMax(A);
        int left = 0;
        int right = 0;

        ResultType max = null;

        if (sum != resultMin.value &&
            (sum - resultMin.value) > resultMax.value) {
            max = new ResultType(sum - resultMin.value,
                                    resultMin.start,
                                    resultMin.end);
        }
        else {
            max = resultMax;
        }

        result.add(max.start);
        result.add(max.end);

        return result;
    }

    // helper method
    private boolean isEmpty(int[] nums) {
        return (nums == null || nums.length == 0);
    }

    private int getSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        return sum;
    }

    private ResultType findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;

        int size = nums.length;

        ResultType result = new ResultType(min, left, right);

        for (int i = 0; i < size; i++) {
            if (sum >= 0) {
                sum = nums[i];
                left = i;
            }
            else {
                sum += nums[i];
                right = i;
            }

            if (sum < min) {
                min = sum;
                right = (right > left) ? right : left;

                result.value = min;
                // the scope of (sum-of-array) - min[current value]				
                result.start =  (size + right + 1) % size;
                result.end =  (size + left - 1) % size;
            }
        }

        return result;
    }

    private ResultType findMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int left = 0;
        int right = 0;

        ResultType result = new ResultType(max, left, right);
        int size = nums.length;

        for (int i = 0; i < size; i++) {
            if (sum < 0) {
                sum = nums[i];
                left = i;
            }
            else {
                sum += nums[i];
                right = i;
            }

            if (sum > max) {
                max = sum;
                right = (right > left) ? right : left;

                result.value = max;
                result.start = left;
                result.end = right;
            }
        }

        return result;
    }
}
