/***
* LintCode 198. Permutation Index II
Given a permutation which may contain repeated numbers, find its index in all the permutations of these numbers, 
which are ordered in lexicographical order. The index begins at 1.

Example
	Example 1:
		Input :[1,4,2,2]
		Output:3

	Example 2:
		Input :[1,6,5,3,1]
		Output:24
***/
public class Solution {
    /**
     * @param A: An array of integers
     * @return: A long integer
     */
    public long permutationIndexII(int[] A) {
        long result = 0;
		if (A == null || A.length == 0) {
			return result;
		}
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (int value : A) {
			if (!map.containsKey(value)) {
			    map.put(value, 0);
			}
			
			map.put(value, map.get(value) + 1);
		}
		
		int size = A.length;
		Set<Integer> visited = new HashSet<Integer>();
		for (int i = 0; i < size; i++) {
			int current = A[i];
			visited.clear();
			for (int j = i + 1; j < size; j++) {
				if (A[j] < current && !visited.contains(A[j])) {
					visited.add(A[j]);
					map.put(A[j], map.get(A[j]) - 1);
					result += getGenerateNum(map);
					map.put(A[j], map.get(A[j]) + 1);
				}
			}
			
			map.put(current, map.get(current) - 1);
		}
		
		result += 1;
		
		return result;
    }
	
	// helper method
	private long getGenerateNum(Map<Integer, Integer> map) {
		long deminator = 1;
		int sum = 0;
		for (int value : map.values()) {
			if (value == 0) {
				continue;
			}
			
			deminator *= factorial(value);
			
			sum += value;
		}
		
		if (sum == 0) {
		    return sum;
		}
		
		return factorial(sum) / deminator;
	}
	
	private long factorial(int value) {
		if (value <= 1) {
			return (long)value;
		}
		
		long result = 1;
		
		for (int i = 1; i <= value; i++) {
			result *= i;
		}
		
		return result;
	}
}