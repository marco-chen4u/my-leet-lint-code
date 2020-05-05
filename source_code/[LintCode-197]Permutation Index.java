/***
* LintCode 197. Permutation Index
Given a permutation which contains no repeated number, 
find its index in all the permutations of these numbers, 
which are ordered in lexicographical order. 

The index begins at 1.

Example
	Example 1:
		Input:[1,2,4]
		Output:1

	Example 2:
		Input:[3,2,1]
		Output:6
***/
public class Solution {
    private final int INDEX_START = 1;
    
    /**
     * @param A: An array of integers
     * @return: A long integer
     */
    public long permutationIndex(int[] A) {
        long result = 0;
        // check corner case
        if (A == null || A.length == 0) {
            return result;
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        int size = A.length;
        
        result = INDEX_START;
        for (int i = 0; i < size; i++) {
            int current = A[i];
            map.put(current, 0);

            for (int j = i + 1; j < size; j++) {
                int value = 0;
                value += (current > A[j]) ? 1 : 0; 
                map.put(current, map.get(current) + value);
            }
            
            int steps = size - (i + 1);
            result += map.get(current) * getFactoria(steps);
        }
        
        return result;
    }
    
    // helper method
    private long getFactoria(int n) {
        long result = 1;
        if (n == 1) {
            return result;
        }
        
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        
        return result;
    }
}