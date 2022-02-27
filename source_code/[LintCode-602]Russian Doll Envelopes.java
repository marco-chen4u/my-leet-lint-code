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
***/
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
