/***
* LintCode 382. Triangle Count
Given an array of integers, how many three numbers can be found in the array, 
so that we can build an triangle whose three edges length is the three numbers that we find?

Example
	Example 1:
		Input: [3, 4, 6, 7]
		Output: 3
		Explanation:
			They are (3, 4, 6), 
					 (3, 6, 7),
					 (4, 6, 7)
	Example 2:
		Input: [4, 4, 4, 4]
		Output: 4
	Explanation:
		Any three numbers can form a triangle. 
		So the answer is C(3, 4) = 4
***/
// solution-1
public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int[] S) {
        // check corner case
        if (S == null || S.length <= 3) {
            return 0;
        }
        
        Arrays.sort(S);
        
        int result = 0;
        for (int i = 2; i < S.length; i++) {
            int left = 0;
            int right = i - 1;
            
            while (left < right) {
                int twoSum = S[left] + S[right];
                if (twoSum > S[i]) {
                    result += right - left;
                    
                    right --;
                }
                else {
                    left ++;
                }
            }
        }
        
        return result;
    }
}

// solution-2
public class Solution {
    /**
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCount(int[] S) {
		int result = 0;
		// check corner case
		if (S == null || S.length == 0) {
			return result;
		}
		
		Arrays.sort(S);
		
		int size = S.length;
		
		for (int i = 2; i < size; i++) {
			for (int left = 0, right = i - 1; left < right; right--) {
				while (left < right && S[left] + S[right] <= S[i]) {
					left++;
				}
				if (left != right) {
					result += right - left;
				}
			}
		}
		
		return result;
	}		
}