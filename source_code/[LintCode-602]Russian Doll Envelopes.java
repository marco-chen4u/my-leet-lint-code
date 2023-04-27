/***
* LintCode 602. Russian Doll Envelopes
Give a number of envelopes with widths and heights given as a pair of integers (w, h). 
One envelope can fit into another if and 
    only if both the width and height of one envelope is greater than the width and height of the other envelope.
    
Find the maximum number of nested layers of envelopes.

Example 1:
    Input：[[5,4],[6,4],[6,7],[2,3]]
    Output：3
    Explanation：
        the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

Example 2:
    Input：[[4,5],[4,6],[6,7],[2,3],[1,1]]
    Output：4
    Explanation：
        the maximum number of envelopes you can Russian doll is 4 ([1,1] => [2,3] => [4,5] / [4,6] => [6,7]).
	
Related question:
    LintCode. 1858 Set of boxes https://www.lintcode.com/problem/1858
    
    
LintCode link: https://www.lintcode.com/problem/602/
LeetCode link: https://leetcode.com/problems/russian-doll-envelopes/
***/
//version-1: dp, time complexity: O(n^2), space complexity: O(n), in leetcode testing, it will get execute time exceeded error
    private Comparator<int[]> comparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            else {
                return a[1] - b[1];
            }
        }
    };

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, (a, b) -> (a[0] != b[0]) ? (a[0] - b[0]) : (a[1] - b[1]));

        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int result = 1;
        for (int i = 1; i < n; i++) {
            int[] current = envelopes[i];
            for (int j = 0; j < i; j++) {
                int[] pre = envelopes[j];

                if (current[0] > pre[0] && current[1] > pre[1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }

        return result;
    }

 
}

//version-2: greedy, time complexity: O(nlogn), space complexity: O(n)
public class Solution {
    // fields
    private final int DEFAULT_MAX = Integer.MAX_VALUE;

    private Comparator<int[]> comparator = new Comparator<int[]>(){
        @Override
        public int compare(int[] current, int[] other) {
            if (current[0] != other[0]) {// width comparision
                return current[0] - other[0];// height comparison
            }

            // be awared at here for the LIS, also, 
            // when either width or length is the same,
            // the smaller one could not fit in the other
            return other[1] - current[1];
        }
    };
	
    /*
     * @param envelopes: a number of envelopes with widths and heights
     * @return: the maximum number of envelopes
     */
    public int maxEnvelopes(int[][] envelopes) {
        int result = 0;
        // check corner cases
        if (envelopes == null || envelopes.length == 0 ||
            envelopes[0] == null || envelopes[0].length == 0) {
            return result;
        }

        // normal case
        int n = envelopes.length;
        Arrays.sort(envelopes, comparator);

        int[] minLast = new int[n];
        Arrays.fill(minLast, DEFAULT_MAX);

        for (int[] envelope : envelopes) {
            int heightValue = envelope[1];
            int index = binarySearch(minLast, heightValue);
            minLast[index] = heightValue;
        }

        for (int i = n - 1; i >= 0; i--) {
            if (minLast[i] == DEFAULT_MAX) {
                continue;
            }

            result = i + 1;
            break;
        }

        return result;
    }

    // helper method
    /*
     * Getting the first index where the item value >= target in the sorted array.
     *
     * @param nums : sorted array.
     * @param target : integer, target value to search.
     * @return : integer, index where the first item value >= target.
     */
    private int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        if (nums[start] >= target) {
            return start;
        }

        return end;
    }
}


//version-3: greedy, time complexity: O(nlogn), space complexity: O(n)
class Solution {

    private Comparator<int[]> comparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            else {
                return b[1] - a[1];
            }
        }
    };

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, (a, b) -> (a[0] != b[0]) ? (a[0] - b[0]) : (b[1] - a[1]));

        int[] values = new int[n];

        Arrays.fill(values, Integer.MAX_VALUE);

        int result = 1;
        for (int i = 0; i < n; i++) {
            int[] current = envelopes[i];
            int currentHeight = current[1];
            int index = binarySearchFirst(values, currentHeight);
            values[index] = currentHeight;
        }

        for (int i = n - 1; i >= 0; i--) {
            if (values[i] != Integer.MAX_VALUE) {
                result = i + 1;
                break;
            }
        }

        return result;
    }

    // helper method
    private int binarySearchFirst(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) /2;
            if (nums[mid] >= target) {
                end = mid;
            }
            else {
                start = mid;
            }
        }

        if (nums[start] >= target) {
            return start;
        }

        return end;
    }

 
}
