/***
* LintCode 1308. Factor Combinations
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Example
	Example1
		Input: 12
		Output: 
			[
			  [2, 6],
			  [2, 2, 3],
			  [3, 4]
			]
		Explanation:
			2*6 = 12
			2*2*3 = 12
			3*4 = 12

	Example2
		Input: 32
		Output: 
			[
			  [2, 16],
			  [2, 2, 8],
			  [2, 2, 2, 4],
			  [2, 2, 2, 2, 2],
			  [2, 4, 4],
			  [4, 8]
			]
		Explanation:
			2*16=32
			2*2*8=32
			2*2*2*4=32
			2*2*2*2*2=32
			2*4*4=32
			4*8=32

Notice
	You may assume that n is always positive.
	Factors should be greater than 1 and less than n.
***/
public class Solution {
    /**
     * @param n: a integer
     * @return: return a 2D array
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // check corner case
        if (n <= 3) {
            return result;
        }
        
        helper(result, new ArrayList<Integer>(), n, 2);
        
        return result;
    }
    
    // helper method
    private void helper(List<List<Integer>> result, List<Integer> combination, int num, int start) {
        // check corner case
        if (num <= 1) {
            if (combination.size() > 1) {
                result.add(new ArrayList<Integer>(combination));
            }
            
            return;
        }
        
        for (int i = start; i <= num; i++) {
            if (num % i != 0) {
                continue;
            }
            
            combination.add(i);
            helper(result, combination, num / i, i);
            combination.remove(combination.size() - 1);
        }
    }
}