/***
* LintCode 183. Wood Cut
Given n pieces of wood with length L[i] (integer array). 
Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. 
What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

Example
    Example 1
        Input:
            L = [232, 124, 456]
            k = 7
        Output: 114
        Explanation: We can cut it into 7 pieces if any piece is 114cm long, however we can't cut it into 7 pieces if any piece is 115cm long.
    Example 2
        Input:
            L = [1, 2, 3]
            k = 7
        Output: 0
        Explanation: It is obvious we can't make it.

Challenge
    O(n log Len), where Len is the longest length of the wood.

Notice
    You couldn't cut wood into float length.
    If you couldn't get >= k pieces, return 0.
***/
//version-1
public class Solution {
    
    // helper method
    private int getCutCount(int[] L, int length) {
        int count = 0;
        
        for (int woodItem : L) {
            count += woodItem / length;
        }
        
        return count;
    }
    
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        // check corner case
        if (L == null || L.length == 0 || k < 0) {
            return 0;
        }
        
        // getting the max length of the wood piece
        int maxLength = Integer.MIN_VALUE;
        for (int woodItem : L) {
            maxLength = Math.max(maxLength, woodItem);
        }
        
        int start = 1;// the minimum lenth of the cut
        int end = maxLength;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (getCutCount(L, mid) >= k) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (getCutCount(L, end) >= k) {
            return end;
        }
        
        if (getCutCount(L, start) >= k) {
            return start;
        }
        
        return 0;
    }
}

//version-2
public class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        // check corner cases
        if (L == null || L.length == 0) {
            return 0;
        }

        // regular case
        int start = 0;
        int end = findMax(L);

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int count = getCount(L, mid);

            if (count < k) {
                end = mid;
            }
            else {
                start = mid;
            }
        }

        if (getCount(L, end) >= k) {
            return end;
        }

        if (getCount(L, start) >= k) {
            return start;
        }

        return 0;
    }

    // helper methods
    private int findMax(int[] nums) {
        int result = nums[0];

        for (int num : nums) {
            result = Math.max(num, result);
        }

        return result;
    }

    private int getCount(int[] nums, int value) {
        // check corner case
        if (value == 0) {
            return 0;
        }

        // regular case
        int count = 0;

        for (int num : nums) {
            count += num / value;
        }

        return count;
    }
}
