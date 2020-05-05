/***
* LintCode 388. Permutation Sequence
Given n and k, find the kth permutation of the dictionary order in the full permutation of n.
1 ≤ n ≤ 9

Example
	Example 1: 
		Input: n = 3, k = 4
		Output: "231"
		Explanation:
			For n = 3, all permutations are listed as follows:
			"123", "132", "213", "231", "312", "321"

	Example 2:
		Input: n = 1, k = 1
		Output: "1"
Challenge
	O(n*k) in time complexity is easy, can you do it in O(n^2) or less?
***/
public class Solution {
    /**
     * @param n: n
     * @param k: the k th permutation
     * @return: return the k-th permutation
     */
    public String getPermutation(int n, int k) {
        String result = null;
        if (n <= 0 || k < 1) {
            return result;
        }
        
        char[] digits = new char[n];
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        
        k--; // 0-based index
        
        for (int i = 0; i < n; i++) {
            int groupCount = (int)getFactorial(n - 1 - i);// n=[1..9],so its factorial <= max_int
            
            int index = k / groupCount;
            int value = nums.remove(index);
            digits[i] = Character.forDigit(value, 10);
            
            k %= groupCount; 
        }
        
        result = new String(digits);
        
        return result;
    }
    
    // helper method
    private long getFactorial(int n) {
        long result = 1;
        
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        
        return result;
    }
}