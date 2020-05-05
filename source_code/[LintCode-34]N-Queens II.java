/***
* LintCode 34. N-Queens II
Follow up for N-Queens problem.
Now, instead outputting board configurations, return the total number of distinct solutions.

Example
	Example 1:
		Input: n=1
		Output: 1
		Explanation:
			1:
			1

	Example 2:
		Input: n=4
		Output: 2
		Explanation:
			1:
			0 0 1 0
			1 0 0 0
			0 0 0 1
			0 1 0 0
			2:
			0 1 0 0 
			0 0 0 1
			1 0 0 0
			0 0 1 0
***/
public class Solution {
    
    // helper methods
    private boolean isValid(List<Integer> rows, int col) {
        int row = rows.size();
        for (int i = 0; i < rows.size(); i++) {
            // direct attack
            if (rows.get(i) == col) {
                return false;
            }
            
            // slash attach[(x,y) <-> (x + 1, y - 1)]
            if (i + rows.get(i) == row + col) {
                return false;
            }
            
            // back-slash attack[(x,y) <-> (x + 1, y + 1)]
            if (i - rows.get(i) == row - col) {
                return false;
            }
        }
        
        return true;
    }
    
    private void search(List<Integer> rows, int n, int[] solutionCount) {
        if (rows.size() == n) {
            solutionCount[0]++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
            
            if (!isValid(rows, i)) {
                continue;
            }
            
            rows.add(i);
            search(rows, n, solutionCount);
            rows.remove(rows.size() - 1); // remove the last one
        }
    }
    
    /**
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    public int totalNQueens(int n) {
        int[] solutionCount = new int[1];
        solutionCount[0] = 0; //default value
        // check corner case 
        if (n < 1) {
            return solutionCount[0];
        }
        
        List<Integer> rows = new ArrayList<Integer>();
        search(rows, n, solutionCount);
        
        return solutionCount[0];
    }
}
