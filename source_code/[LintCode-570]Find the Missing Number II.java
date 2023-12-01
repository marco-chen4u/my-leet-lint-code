/***
* LintCode 570. Find the Missing Number II
Giving a string with number from 1-n in random order, but miss 1 number.Find that number.

Example1
    Input: n = 20 and str = 19201234567891011121314151618
    Output: 17
    Explanation:
        19'20'1'2'3'4'5'6'7'8'9'10'11'12'13'14'15'16'18
Example2
    Input: n = 6 and str = 56412
    Output: 3
    Explanation:
        5'6'4'1'2

Notice
    n <= 30
    Data guarantees have only one solution
***/
public class Solution {
    // fields
    private final int DEFAULT_VALUE = -1;
    private final int MARKED = 1;
    private final int UNMARKED = 0;

    private int size;
    private int[] nums;
    private int n;//maximum value

    private int result;
	
    /**
     * @param n: An integer
     * @param str: a string with number from 1-n in random order and miss one number
     * @return: An integer
     */
    public int findMissing2(int n, String str) {
        // check corner case
        if (n <= 0 || str == null || str.length() == 0) {
            return DEFAULT_VALUE;
        }

        // initialize
        this.n = n;
        this.size = str.length();
        this.nums = new int[n + 1];
        nums[0] = MARKED;
        result = DEFAULT_VALUE;

        helper(str, 0);

        return result;
    }

    // helper method
    private void helper(String str, int startIndex) {
        // check corner cases
        if (startIndex == size || result != DEFAULT_VALUE) {
	
            if (result == DEFAULT_VALUE) {
                for (int i = 1; i <= n; i++) {
                    if (nums[i] == UNMARKED) {
                        result = i;
                        break;
                    }
                }
            }

            return;
        }
		
        if (str.charAt(startIndex) == '0') {
            return;
        }

        for (int i = startIndex + 1; i <= startIndex + 2 & i <= size; i++) {
            int value = Integer.valueOf(str.substring(startIndex, i));

            if (value <= 0 || value > n || nums[value] == MARKED) {
                continue;
            }
			
            nums[value] = MARKED;
            helper(str, i);
            nums[value] = UNMARKED;
        }
    }
}
