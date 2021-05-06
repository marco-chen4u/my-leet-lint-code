/***
* LintCode 60. Search Insert Position
Given a sorted array and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.

You may assume NO duplicates in the array.

Example 1:
    Input:
        array = [1,3,5,6]
        target = 5
    Output:
        2
    Explanation:
        5 is indexed to 2 in the array.

Example 2:
    Input:
        array = [1,3,5,6]
        target = 2
    Output:
        1
    Explanation:
        2 should be inserted into the position with index 1.

Example 3:
    Input:
        array = [1,3,5,6]
        target = 7
    Output:
        4
    Explanation:
        7 should be inserted into the position with index 4.

Example 4:
    Input:
array = [1,3,5,6]
target = 0
Output:
0
Explanation:
0 should be inserted into the position with index 0.

***/
public class Solution {
    /**
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: An integer
     */
    public int searchInsert(int[] A, int target) {
        // check corner case
        if (A == null || A.length == 0) {
            return 0;
        }

        int start = 0;
        int end = A.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (A[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        if (A[start] >= target) {
            return start;
        }

        if (A[end] >= target) {
            return end;
        }

        return end + 1;
    }
}
