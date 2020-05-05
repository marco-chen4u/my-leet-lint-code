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
        //check corner case
        if (n <= 0 || k < 1) {
            return null;
        }
        
        char[] result = new char[n];
        
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;// every nth value head digit in a group has (n-1)! permutations
        }
        
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);// initialize the available digits for assembling the result
        }
        
        k--; // 0-based index value of the permutation
        
        for (int i = 0; i < n; i++) {
            int groupCount = factorial[n - 1 - i];
            
            int index = k / groupCount; // 0-based index
            int value = nums.remove(index);
            result[i] = Character.forDigit(value, 10);
            
            k = k % groupCount; // update the k value
        }        
        
        return new String(result);
    }
}